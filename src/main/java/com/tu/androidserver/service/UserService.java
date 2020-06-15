package com.tu.androidserver.service;

import com.tu.androidserver.bean.*;
import com.tu.androidserver.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private TopicMapper topicMapper;
    @Autowired
    private UserRelationMapper userRelationMapper;
    @Autowired
    private ChatRecordMapper chatRecordMapper;
    @Autowired
    private UserUntil userUntil;
    //放置邮箱和对应的验证码
    private Map<String,String> captchaMap;
    public UserService() {
        captchaMap=new HashMap<>();
    }
    /**
     * 用户注册
     * @return
     * -1:验证码错误
     * 0:注册失败
     * */
    public int register(User user,String captcha) {
        if(!captchaMap.get(user.getEmail()).equals(captcha)) {   //验证码不正确
            return -1;
        }
        captchaMap.remove(user.getEmail());
        int num=0;
        if((num=userMapper.insertUser(user))>0) {
            return num;
        }
        return 0;
    }

    /**
     * 发送验证码
     * @return
     * -1:邮箱已经被注册过
     * 0:发送邮件失败
     * 1:成功
     * */
    public int sendCaptcha(String email) {
        if(userMapper.getUserByEmail(email)!=null) {  //邮箱重复
            return -1;
        }
        String captcha= EmailUntil.getRandomCaptcha();
        if(captchaMap.get(email)!=null) {
            captchaMap.replace(email,captcha);
        }else {
            captchaMap.put(email,captcha);
        }
        try {
            EmailUntil.sendEmail(email,captcha);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    /**
     * 登陆
     * */
    public int login(String email,String password) {
        User loginUser=userMapper.getUserByEmail(email);
        if(loginUser==null) {
            return -1;
        }
        if(loginUser.getPassword().equals(password)) {
            return loginUser.getId();
        }
        return -1;
    }

    /**
     * 获取用户信息
     * */
    public User displayUserInfo(int id) {
        return userMapper.getUserById(id);
    }

    /**
     * 修改用户信息
     * */
    public boolean changeUserInfo(User user) {
        if(userMapper.updateUser(user)==1) {
            return true;
        }
        return false;
    }

    /**
     * 评论
     * */
    public boolean remark(Integer userId,Integer topicId,String content) {
        Comment comment=new Comment(content,userId,topicId,new Timestamp(System.currentTimeMillis()));
        if(commentMapper.insertComment(comment)==1) {
            return true;
        }
        return false;
    }

    /**
     * 发表话题
     * */
    public boolean postTopic(Integer userId,String title,String content) {
        Topic topic=new Topic(title,content,new Timestamp(System.currentTimeMillis()),userId);
        if(topicMapper.insertTopic(topic)==1) {
            return true;
        }
        return false;
    }

    /**
     * 查看某一话题下的所有评论
     * */
    public List<Comment> viewComment(Integer topicId) {
        return commentMapper.getCommentByTopicId(topicId);
    }

    /**
     * 查看所有话题
     * */
    public List<Topic> viewAllTopic() {
        return topicMapper.getAllTopic();
    }

    /**
     * 添加好友
     * @return
     * 0:表示已经发送过请求，但是对方还未同意
     * 1:表示已经存在好友关系
     * 2:表示发送成功
     * 3:表示发送失败
     * */
    public int addFriend(Integer senderId,String receiverEmail) {
        int receiverId=userMapper.getUserByEmail(receiverEmail).getId();
        UserRelation userRelation=userRelationMapper.getUserBySenderAndReceiverId(senderId,receiverId);
        userRelation=(userRelation!=null?userRelation:userRelationMapper.getUserBySenderAndReceiverId(receiverId,senderId));
        if(userRelation!=null) {
            if(userRelation.isAgree()==0) {
                return 0;
            }
            return 1;
        }
        userRelation=new UserRelation(senderId,receiverId,new Timestamp(System.currentTimeMillis()));
        if(userRelationMapper.insertUserRelation(userRelation)==1) {
            return 2;
        }
        return 3;
    }

    /**
     * 查看好友申请
     * */
    public List<User> displayFriendApplication(Integer receiverId) {
        List<UserRelation> userRelations=userRelationMapper.getUserByReceiverId(receiverId);
        List<User> users=new ArrayList<>();
        for(UserRelation relation:userRelations) {
            if(relation.isAgree()==0) {
                users.add(userMapper.getUserById(relation.getSenderId()));
            }
        }
        return users;
    }

    /**
     * 接受好友请求
     * */
    public boolean acceptFriendApplication(Integer receiverId,Integer senderId) {
        UserRelation userRelation=userRelationMapper.getUserBySenderAndReceiverId(senderId,receiverId);
        if(userRelation==null) {
            return false;
        }
        if(userRelation.isAgree()==1) {
            return false;
        }
        userRelation.setAgree(1);
        if(userRelationMapper.updateUserRelation(userRelation)==1){
            return true;
        }
        return false;
    }

    /**
     * 拒绝好友申请
     * */
    public boolean refuseFriendApplication(Integer receiverId,Integer senderId) {
        UserRelation userRelation=userRelationMapper.getUserBySenderAndReceiverId(senderId,receiverId);
        if(userRelation==null) {
            return false;
        }
        if(userRelation.isAgree()==0) {
            if(userRelationMapper.deleteUserRelation(senderId,receiverId)==1) {
                return true;
            }
        }
        return false;
    }

    /**
     * 删除好友
     * */
    public boolean deleteFriend(Integer senderId,Integer receiverId) {
        UserRelation userRelation=userRelationMapper.getUserBySenderAndReceiverId(senderId,receiverId);
        if(userRelation==null) {
            userRelation=userRelationMapper.getUserBySenderAndReceiverId(receiverId,senderId);
            if(userRelation==null) {
                return false;
            }
            userRelation.setIsDelete(2);
        }
        else {
            userRelation.setIsDelete(1);
        }
        if(userRelationMapper.updateUserRelation(userRelation)==1) {
            return true;
        }
        return false;
    }

    /**
     * 查看删除好友通知
     * */
    public List<User> acceptDeleteFriendMessage(Integer receiverId) {
        List<UserRelation> senderRelation=userRelationMapper.getUserBySenderId(receiverId);
        List<UserRelation> receiverRelation=userRelationMapper.getUserByReceiverId(receiverId);
        List<User> users=new ArrayList<>();
        for(int i=0;i<senderRelation.size();i++) {
            if(senderRelation.get(i).isAgree()==0) {
                senderRelation.remove(i);
                continue;
            }
            if(senderRelation.get(i).getIsDelete()==0||senderRelation.get(i).getIsDelete()==1) {
                senderRelation.remove(i);
            }
        }
        for(int i=0;i<receiverRelation.size();i++) {
            if(receiverRelation.get(i).isAgree()==0) {
                receiverRelation.remove(i);
                continue;
            }
            if(receiverRelation.get(i).getIsDelete()==0||receiverRelation.get(i).getIsDelete()==2) {
                receiverRelation.remove(i);
            }
        }
        for(UserRelation relation:senderRelation) {
            users.add(userMapper.getUserById(relation.getReceiverId()));
            userRelationMapper.deleteUserRelation(relation.getSenderId(),relation.getReceiverId());
        }
        for(UserRelation relation:receiverRelation) {
            users.add(userMapper.getUserById(relation.getSenderId()));
            userRelationMapper.deleteUserRelation(relation.getSenderId(),relation.getReceiverId());
        }
        return users;
    }

    /**
     * 显示所有的好友
     * */
    public List<User> displayAllFriend(Integer id) {
        List<UserRelation> receiverRelations=userRelationMapper.getUserBySenderId(id);
        List<UserRelation> senderRelations=userRelationMapper.getUserByReceiverId(id);
        List<User> users=new ArrayList<>();
        for(int i=0;i<receiverRelations.size();i++) {
            if(receiverRelations.get(i).isAgree()==0) {
                receiverRelations.remove(i);
                continue;
            }
            if(receiverRelations.get(i).getIsDelete()!=0) {
                receiverRelations.remove(i);
                continue;
            }
        }
        for(int i=0;i<senderRelations.size();i++) {
            if(senderRelations.get(i).isAgree()==0) {
                senderRelations.remove(i);
                continue;
            }
            if(senderRelations.get(i).getIsDelete()!=0) {
                senderRelations.remove(i);
                continue;
            }
        }

        for(UserRelation relation:receiverRelations) {
            users.add(userMapper.getUserById(relation.getReceiverId()));
        }
        for(UserRelation relation:senderRelations) {
            users.add(userMapper.getUserById(relation.getSenderId()));
        }
        return users;
    }

    /**
     * 私信
     * */
    public boolean sendMessage(Integer senderId,Integer receiverId,String content) {
        if(!userUntil.isFriend(senderId,receiverId)) {

            return false;
        }

        ChatRecord record = new ChatRecord(senderId,receiverId,content,new Timestamp(System.currentTimeMillis()));
        if(chatRecordMapper.insertChatRecord(record)==1) {
            return true;
        }
        return false;
    }

    /**
     * 查看所有私信
     * */
    public List<ChatRecord> displayAllMessage(Integer senderId,Integer receiverId) {
        if(!userUntil.isFriend(senderId,receiverId)) {
            return null;
        }
        List<ChatRecord> records = new ArrayList<>();
        records.addAll(chatRecordMapper.getChatRecordBySenderAndReceiverId(senderId,receiverId));
        records.addAll(chatRecordMapper.getChatRecordBySenderAndReceiverId(receiverId,senderId));
        for(int i=0;i<records.size();i++) {
            if(records.get(i).getIsRead()==0) {  //将消息标记为已读
                records.get(i).setIsRead(1);
                chatRecordMapper.updateChatRecordState(records.get(i));
            }
        }
        return records;
    }

    /**
     * 提示是否有未读私信
     * */
    public List<User> isUnreadMessage(Integer senderId) {
        List<User> users = new ArrayList<>();
        List<ChatRecord> records = new ArrayList<>();
        records.addAll(chatRecordMapper.getChatRecordByReceiverId(senderId));
        for(ChatRecord record:records) {
            if(record.getIsRead()==0) {
                users.add(userMapper.getUserById(record.getSenderId()));
            }
        }
        return users;
    }
}

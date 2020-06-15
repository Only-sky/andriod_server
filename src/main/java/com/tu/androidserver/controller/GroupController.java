package com.tu.androidserver.controller;

import com.tu.androidserver.bean.GroupChatRecord;
import com.tu.androidserver.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class GroupController {
    @Autowired
    GroupService groupService;

    @PostMapping("/createGroup")
    @ResponseBody
    public boolean createGroup(@RequestParam String name, @RequestParam String userId) {
        return groupService.createGroup(name,Integer.parseInt(userId));
    }

    @PostMapping("/joinGroup")
    @ResponseBody
    public int joinGroup(@RequestParam String userId,@RequestParam String groupId) {
        return groupService.joinGroup(Integer.parseInt(userId),Integer.parseInt(groupId));
    }

    @PostMapping("/sendGroupMessage")
    @ResponseBody
    public boolean sendGroupMessage(@RequestParam String groupId,@RequestParam String userId,@RequestParam String content) {
        return groupService.sendGroupMessage(Integer.parseInt(userId),Integer.parseInt(groupId),content);
    }

    @PostMapping("/displayGroupAllMessage")
    @ResponseBody
    public List<GroupChatRecord> displayGroupAllMessage(String groupId) {
        return groupService.displayGroupAllMessage(Integer.parseInt(groupId));
    }
}

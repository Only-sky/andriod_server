package com.tu.androidserver.service;


import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

public class EmailUntil {

    //QQ邮箱 授权码
    private final static String authorizationCode="xesbrbdekwdfdcjg";

    public static void sendEmail(String receiverEmail,String captcha) throws Exception {
        //设置发送邮件的主机  smtp.qq.com
        String host = "smtp.qq.com";
        //1.创建连接对象，连接到邮箱服务器
        Properties props = System.getProperties();
        //设置邮件服务器
        props.setProperty("mail.smtp.host", host);
        props.setProperty("mail.transport.protocol", "SMTP");
        props.put("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.timeout","1000");
        //SSL加密
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.enable","true");
        props.put("mail.smtp.ssl.socketFactory", sf);
        //props：用来设置服务器地址，主机名；Authenticator：认证信息
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                //new PasswordAuthentication(用户名, password);
                //这个用户名密码就可以登录到邮箱服务器了,用它给别人发送邮件
                return new PasswordAuthentication("2784216955@qq.com",authorizationCode);
            }
        });

        Message message = new MimeMessage(session);
        try {
            //防止成为垃圾邮件，披上outlook的马甲
            message.addHeader("X-Mailer","Microsoft Outlook Express 6.00.2900.2869");
            //2.1设置发件人：
            message.setFrom(new InternetAddress("2784216955@qq.com"));
            //2.2设置收件人 这个TO就是收件人
            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiverEmail));
            //2.3邮件的主题
            message.setSubject("来自TICQ的验证码邮件");
            //2.4设置邮件的正文 第一个参数是邮件的正文内容 第二个参数是：是文本还是html的连接
            message.setContent("<h1>来自TICQ的验证码邮件,请接收你的验证码：</h1><h3>你的验证码是："+captcha+"，请妥善保管好你的验证码！</h3>", "text/html;charset=UTF-8");
            //3.发送一封激活邮件
            Transport.send(message);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取四位验证码
     * */
    public static String getRandomCaptcha() {
        StringBuilder result=new StringBuilder();
        Random random = new Random();
        String range = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGJKLZXCVBNM1234567890";
        for (int i = 0; i < 4; i++) {
            result.append(range.charAt(random.nextInt(range.length())));
        }
        return result.toString();
    }
}

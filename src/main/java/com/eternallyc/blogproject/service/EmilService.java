package com.eternallyc.blogproject.service;

import com.eternallyc.blogproject.bean.Comment;
import com.eternallyc.blogproject.bean.LeaveMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmilService {

    @Autowired
    JavaMailSender jms;

    // 文章评论提供邮件服务
    public void sendEmil(Comment comment){
        //建立邮件消息
        SimpleMailMessage mainMessage = new SimpleMailMessage();
        //发送者
        mainMessage.setFrom("1306513880@qq.com");
        //接收者
        mainMessage.setTo("1306513880@qq.com");
        //发送的标题
        mainMessage.setSubject("个人博客：有人评论啦~~~");
        //发送的内容
        mainMessage.setText(
                "评论的用户名为："+ comment.getComment_username()+
                        "\n评论的内容为："+ comment.getComment_content()
                        +"\n评论的时间为："+comment.getComment_time()
        );
        jms.send(mainMessage);
    }

    // 博客留言提供邮件服务
    public void leavemessageEmil(LeaveMessage leaveMessage){
        //建立邮件消息
        SimpleMailMessage mainMessage = new SimpleMailMessage();
        //发送者
        mainMessage.setFrom("1306513880@qq.com");
        //接收者
        mainMessage.setTo("1306513880@qq.com");
        //发送的标题
        mainMessage.setSubject("个人博客：有人留言啦~~~");
        //发送的内容
        mainMessage.setText(
                "留言的用户名为："+ leaveMessage.getUsername()+
                        "\n留言的内容为："+ leaveMessage.getContent()
                        +"\n留言的时间为："+leaveMessage.getTime()
        );
        jms.send(mainMessage);
    }
}

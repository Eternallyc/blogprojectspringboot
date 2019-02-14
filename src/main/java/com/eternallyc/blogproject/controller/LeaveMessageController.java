package com.eternallyc.blogproject.controller;

import com.eternallyc.blogproject.bean.LeaveMessage;
import com.eternallyc.blogproject.service.EmilService;
import com.eternallyc.blogproject.service.LeaveMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Map;

@Controller
@RequestMapping("/blogs")
public class LeaveMessageController {
    @Autowired
    LeaveMessageService leaveMessageService;

    //获取所有留言
    @GetMapping("/getallleavemessage")
    public ModelAndView getLeaveMessageList(){
        ModelAndView mv=new ModelAndView();
        mv.addObject("leavemessagelist",leaveMessageService.getLeaveMessageList());
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }

    //用户留言
    @RequestMapping("/postleavemessage")
    public ModelAndView postLeaveMessage(@RequestBody(required = false) Map<String,String> map){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setView(new MappingJackson2JsonView());
        LeaveMessage leaveMessage=new LeaveMessage(1,map.get("username"),map.get("avatar"),map.get("content"),map.get("time"),map.get("reply"));
        leaveMessageService.postLeaveMessage(leaveMessage);//首先先增加留言
        modelAndView.addObject("leavemessagelist",leaveMessageService.getLeaveMessageList());//更新留言列表
        return modelAndView;
    }

    @Autowired
    EmilService emilService;
    //博客留言发送邮件服务
    @PostMapping("/leavemessage/sendArticleEmil")
    public ModelAndView sendArticleEmil(@RequestBody(required = false) Map<String,String> map){
        ModelAndView mv =new ModelAndView();
        LeaveMessage leaveMessage=new LeaveMessage(1,map.get("username"),map.get("avatar"),map.get("content"),map.get("time"),map.get("reply"));
        emilService.leavemessageEmil(leaveMessage);
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }
}

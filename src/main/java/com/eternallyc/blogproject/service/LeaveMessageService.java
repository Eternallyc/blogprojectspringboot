package com.eternallyc.blogproject.service;

import com.eternallyc.blogproject.bean.LeaveMessage;
import com.eternallyc.blogproject.mapper.LeaveMessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveMessageService {

    @Autowired
    LeaveMessageMapper leaveMessageMapper;

    //获得所有用户留言
    public List<LeaveMessage> getLeaveMessageList(){
        return leaveMessageMapper.getLeaveMessageList();
    }

    //保存用户的留言
    public void postLeaveMessage(LeaveMessage leaveMessage){
        //保存用户留言
        leaveMessageMapper.postLeaveMessage(leaveMessage);
    }
}

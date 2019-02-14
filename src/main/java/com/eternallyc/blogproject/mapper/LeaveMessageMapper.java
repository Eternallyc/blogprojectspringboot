package com.eternallyc.blogproject.mapper;

import com.eternallyc.blogproject.bean.LeaveMessage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface LeaveMessageMapper {

    //获得所有用户留言
    @Select("select * from leavemessage")
    public List<LeaveMessage> getLeaveMessageList();

    //保存用户的留言
    @Insert("insert into leavemessage(username,avatar,content,time) values(#{username},#{avatar},#{content},#{time}) ")
    public void postLeaveMessage(LeaveMessage leaveMessage);


}

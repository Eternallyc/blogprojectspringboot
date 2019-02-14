package com.eternallyc.blogproject.bean;

/**
 * 留言类
 */
public class LeaveMessage {

    private Integer leavemessage_id;//留言ID
    private String username;//留言者的用户名
    private String avatar;//留言者的头像

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    private String content;//留言内容
    private String time;//留言时间
    private String reply;//博主回复

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public LeaveMessage(Integer leavemessage_id, String username, String avatar, String content, String time, String reply) {
        this.leavemessage_id = leavemessage_id;
        this.username = username;
        this.avatar = avatar;
        this.content = content;
        this.time = time;
        this.reply = reply;
    }

    public LeaveMessage() {
    }

    public Integer getLeavemessage_id() {
        return leavemessage_id;
    }

    public void setLeavemessage_id(Integer leavemessage_id) {
        this.leavemessage_id = leavemessage_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

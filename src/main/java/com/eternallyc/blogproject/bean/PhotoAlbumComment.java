package com.eternallyc.blogproject.bean;

/**
 * 相册评论类
 */
public class PhotoAlbumComment {
    private Integer id;//评论ID
    private Integer photoalbum_id;//相册ID
    private String username;//评论者的用户名
    private String avatar;//评论者的头像
    private String content;//评论内容
    private String time;//评论时间

    public PhotoAlbumComment( Integer photoalbum_id, String username, String avatar, String content, String time, String reply) {

        this.photoalbum_id = photoalbum_id;
        this.username = username;
        this.avatar = avatar;
        this.content = content;
        this.time = time;
        this.reply = reply;
    }

    public PhotoAlbumComment() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPhotoalbum_id() {
        return photoalbum_id;
    }

    public void setPhotoalbum_id(Integer photoalbum_id) {
        this.photoalbum_id = photoalbum_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    private String reply;//回复内容
}

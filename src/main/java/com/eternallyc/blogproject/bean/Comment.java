package com.eternallyc.blogproject.bean;

/**
 * 评论类
 */
public class Comment {
    private Integer comment_id;//评论ID
    private Integer article_id;//文章ID
    private String comment_username;//评论者的用户名
    private String comment_avatar;//评论者的头像
    private String comment_content;//评论内容
    private String comment_time;//评论时间
    private String reply;//回复内容

    public Comment() {
    }

    public Comment( Integer article_id, String comment_username, String comment_avatar, String comment_content, String comment_time, String reply) {

        this.article_id = article_id;
        this.comment_username = comment_username;
        this.comment_avatar = comment_avatar;
        this.comment_content = comment_content;
        this.comment_time = comment_time;
        this.reply = reply;
    }

    public String getComment_avatar() {

        return comment_avatar;
    }

    public void setComment_avatar(String comment_avatar) {
        this.comment_avatar = comment_avatar;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }



    public Integer getComment_id() {
        return comment_id;
    }

    public void setComment_id(Integer comment_id) {
        this.comment_id = comment_id;
    }

    public Integer getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Integer article_id) {
        this.article_id = article_id;
    }

    public String getComment_username() {
        return comment_username;
    }

    public void setComment_username(String comment_username) {
        this.comment_username = comment_username;
    }

    public String getComment_time() {
        return comment_time;
    }

    public void setComment_time(String comment_time) {
        this.comment_time = comment_time;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }
}

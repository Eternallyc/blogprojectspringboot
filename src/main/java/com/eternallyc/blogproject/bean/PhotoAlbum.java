package com.eternallyc.blogproject.bean;

/**
 * 相册类
 */
public class PhotoAlbum {
    private Integer photoalbum_id;//相册ID
    private String title;//相册标题
    private String troduction;//相册简介（内容）
    private String time;//相册时间
    private Picture picture;//图片
    private String read_number;//相册阅读量
    private String comment_number;//相册评论量

    public String getComment_number() {
        return comment_number;
    }

    public void setComment_number(String comment_number) {
        this.comment_number = comment_number;
    }

    private String like_number;//相册点赞量

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }



    public Integer getPhotoalbum_id() {
        return photoalbum_id;
    }

    public void setPhotoalbum_id(Integer photoalbum_id) {
        this.photoalbum_id = photoalbum_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTroduction() {
        return troduction;
    }

    public void setTroduction(String troduction) {
        this.troduction = troduction;
    }


    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public String getRead_number() {
        return read_number;
    }

    public void setRead_number(String read_number) {
        this.read_number = read_number;
    }

    public String getLike_number() {
        return like_number;
    }

    public void setLike_number(String like_number) {
        this.like_number = like_number;
    }
}

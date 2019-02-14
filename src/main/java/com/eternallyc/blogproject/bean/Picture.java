package com.eternallyc.blogproject.bean;

/**
 * 图片类
 */
public class Picture {
    private Integer picture_id;//图片ID
    private String picture_url;//图片URL

    public Integer getPicture_id() {
        return picture_id;
    }

    public void setPicture_id(Integer picture_id) {
        this.picture_id = picture_id;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }
}

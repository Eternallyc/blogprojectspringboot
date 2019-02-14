package com.eternallyc.blogproject.bean;

/**
 * 相册图片关联类
 */
public class PhotoAlbumContent {

    private Integer photoalbum_id;//相册ID
    private Picture picture;//图片

    public Integer getPhotoalbum_id() {
        return photoalbum_id;
    }

    public void setPhotoalbum_id(Integer photoalbum_id) {
        this.photoalbum_id = photoalbum_id;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }
}

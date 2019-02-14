package com.eternallyc.blogproject.service;

import com.eternallyc.blogproject.bean.PhotoAlbum;
import com.eternallyc.blogproject.bean.PhotoAlbumComment;
import com.eternallyc.blogproject.bean.PhotoAlbumContent;
import com.eternallyc.blogproject.mapper.PhotoAlbumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhotoAlbumService {

    @Autowired
    PhotoAlbumMapper photoAlbumMapper;

    //获得所有相册
    public List<PhotoAlbum> getAllList() {
        return photoAlbumMapper.getAllList();
    }

    //获得推荐相册
    public List<PhotoAlbum> getRecommendList() {
        return photoAlbumMapper.getRecommendList();
    }

    //查询当前相册所有图片
    public List<PhotoAlbumContent> getAllPicture(Integer photoalbum_id) {
        return photoAlbumMapper.getAllPicture(photoalbum_id);
    }

    //查询当前相册的信息
    public PhotoAlbum getCurrentAlbum(Integer photoalbum_id) {
        return photoAlbumMapper.getCurrentAlbum(photoalbum_id);
    }

    //对当前相册进行点赞
    public void pointLike(Integer photoalbum_id){
        photoAlbumMapper.pointLike(photoalbum_id);
    }

    //查询所有的当前相册所有的评论
    public List<PhotoAlbumComment> getAllComments(Integer photoalbum_id){
        return  photoAlbumMapper.getAllComments(photoalbum_id);
    }

    //评论当前相册
    public void commentCurrent(PhotoAlbumComment photoAlbumComment){
        photoAlbumMapper.commentCurrent(photoAlbumComment);
    }

    //当前相册评论数+1
    public void addCommentNum(Integer photoalbum_id){
        photoAlbumMapper.addCommentNum(photoalbum_id);
    }

    //当前相册的访问量+1
    public void updateReadNum(Integer photoalbum_id){
        photoAlbumMapper.updateReadNum(photoalbum_id);
    }

    //得到相册图
    public List<PhotoAlbumName> getPhotoAlbumName(){
        List<PhotoAlbumName> list = new ArrayList<>();
        //获得所有相册
        List<PhotoAlbum> list1=photoAlbumMapper.getAllList();

        for(PhotoAlbum photoAlbum:list1){
            PhotoAlbumName photoAlbum1=new PhotoAlbumName();
            photoAlbum1.setName(photoAlbum.getTitle());//得到相册标题
            list.add(photoAlbum1);
        }
        return list;
    }
}


//获得相册分类图
class PhotoAlbumName{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
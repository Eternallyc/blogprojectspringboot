package com.eternallyc.blogproject.mapper;

import com.eternallyc.blogproject.bean.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PhotoAlbumMapper {

    //查询所有相册，顺便将相册的封面图片url保存在相册类中
    @Select("select * from photoalbum limit 0,8")
    @Results({
            @Result(id = true, property = "photoalbum_id", column = "photoalbum_id"),
            @Result(property = "picture", column = "picture_id", javaType = Picture.class,
                    one = @One(select = "com.eternallyc.blogproject.mapper.PictureMapper.getPicture"))
    })
    public List<PhotoAlbum> getAllList();


    //查询推荐相册
    @Select("select * from photoalbum where recommend='1'limit 0,6")
    @Results({
            @Result(id = true, property = "photoalbum_id", column = "photoalbum_id"),
            @Result(property = "picture", column = "picture_id", javaType = Picture.class,
                    one = @One(select = "com.eternallyc.blogproject.mapper.PictureMapper.getPicture"))
    })
    public List<PhotoAlbum> getRecommendList();

    //查询当前相册所有图片
    @Select("select * from photoalbum_picture where photoalbum_id=#{photoalbum_id}")
    @Results({
            @Result(id = true, property = "photoalbum_id", column = "photoalbum_id"),
            @Result(property = "picture", column = "picture_id", javaType = Picture.class,
                    one = @One(select = "com.eternallyc.blogproject.mapper.PictureMapper.getPicture"))
    })
    public List<PhotoAlbumContent> getAllPicture(Integer photoalbum_id);

    //查询当前相册的信息
    @Select("select * from photoalbum where photoalbum_id=#{photoalbum_id}")
    public PhotoAlbum getCurrentAlbum(Integer photoalbum_id);


    //对当前相册进行点赞
    @Update("update photoalbum set like_number=like_number+1 where photoalbum_id = #{photoalbum_id}")
    public void pointLike(Integer photoalbum_id);


    //查询所有的当前相册所有的评论
    @Select("select * from photoalbum_comment where photoalbum_id=#{photoalbum_id}")
    public List<PhotoAlbumComment> getAllComments(Integer photoalbum_id);

    //评论当前相册
    @Insert("insert into photoalbum_comment(photoalbum_id,username,avatar,content,time,reply) values(#{photoalbum_id}," +
            "#{username},#{avatar},#{content},#{time},#{reply})")
    public void commentCurrent(PhotoAlbumComment photoAlbumComment);

    //当前相册评论数+1
    @Update("update photoalbum set comment_number=comment_number+1 where photoalbum_id = #{photoalbum_id}")
    public void addCommentNum(Integer photoalbum_id);

    //当前相册的访问量+1
    @Update("update photoalbum set read_number=read_number+1 where photoalbum_id = #{photoalbum_id}")
    public void updateReadNum(Integer photoalbum_id);
}

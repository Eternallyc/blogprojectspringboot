package com.eternallyc.blogproject.mapper;

import com.eternallyc.blogproject.bean.Admin;
import com.eternallyc.blogproject.bean.AdminMessage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

//指定这是一个操作数据库的mapper
public interface AdminMapper {
    @Select("select username,userpwd from admin where username=#{username} and userpwd=Md5(#{userpwd})")
    public Admin login(@Param("username") String username, @Param("userpwd") String userpwd);

    //求所有博客的阅读量
    @Select("select sum(read_number) from article")
    public Integer sumArticleReadNum();

    //求所有相册的阅读量
    @Select("select sum(read_number) from photoalbum")
    public Integer sumphotoalbumReadNum();


    //求所有博客的评论量
    @Select("select sum(comment_number) from article")
    public Integer sumArticleCommentNum();

    //求所有相册的评论量
    @Select("select sum(comment_number) from photoalbum")
    public Integer sumphotoalbumCommentNum();


    //求所有博客的点赞量
    @Select("select sum(like_number) from article")
    public Integer sumArticleLikeNum();


    //求所有相册的点赞量
    @Select("select sum(like_number) from photoalbum")
    public Integer sumphotoalbumLikeNum();


    //求所有的留言量
    @Select("select count(*) from leavemessage")
    public Integer leaveMessageNum();


    //根据分类获得当前分类的总阅读量
    @Select("select sum(read_number) from article where classification_id=#{classification_id}")
    public Integer getCunrrentClassificationReadNum(@Param("classification_id") Integer classification_id);

    //根据分类获得当前分类的总评论量
    @Select("select sum(comment_number) from article where classification_id=#{classification_id}")
    public Integer getCunrrentClassificationCommentNum(@Param("classification_id") Integer classification_id);

    //根据分类获得当前分类的总点赞量
    @Select("select sum(like_number) from article where classification_id=#{classification_id}")
    public Integer getCunrrentClassificationLikeNum(@Param("classification_id") Integer classification_id);


    // 得到关于我的标题和内容
    @Select("select title,aboutme from adminmessage where id=1")
    public AdminMessage getTitleAndMessage();

    // 保存关于我的标题和内容
    @Update("update adminmessage set title=#{title},aboutme=#{aboutme} where id=1")
    public void saveTitleAndMessage(@Param("title") String title,
                                            @Param("aboutme") String aboutme);

    // 获得个人资料的姓名和简介和头像
    @Select("select name,troduction,avatar from adminmessage where id=1")
    public AdminMessage getNameAndIntroduction();

    // 保存个人资料的姓名和简介
    @Update("update adminmessage set name=#{name},troduction=#{troduction} where id=1")
    public void saveNameAndIntroduction(@Param("name") String name,
                                    @Param("troduction") String troduction);

    //更新头像
    @Update("update adminmessage set avatar=#{avatar} where id=1")
    public void updateavatar(@Param("avatar") String avatar);


    //修改密码
    @Update("update admin set userpwd=MD5(#{userpwd}) where id=1")
    public void updateuserpwd(@Param("userpwd") String userpwd);
}

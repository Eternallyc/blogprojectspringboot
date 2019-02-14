package com.eternallyc.blogproject.mapper;

import com.eternallyc.blogproject.bean.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CommentMapper {

    //查询所有的当前博客所有的评论
    @Select("select * from comment where article_id=#{article_id}")
    public List<Comment> getAllComments(Integer article_id);

    //评论当前文章
    @Insert("insert into comment(article_id,comment_username,comment_avatar,comment_content,comment_time,reply) values(#{article_id}," +
            "#{comment_username},#{comment_avatar},#{comment_content},#{comment_time},#{reply})")
    public void commentCurrent(Comment comment);
}

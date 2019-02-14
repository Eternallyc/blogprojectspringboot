package com.eternallyc.blogproject.service;

import com.eternallyc.blogproject.bean.Comment;
import com.eternallyc.blogproject.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentMapper commentMapper;

    //查询所有的当前博客所有的评论
    public List<Comment> getAllComments(Integer article_id){
        return commentMapper.getAllComments(article_id);
    }

    //评论当前文章
    public void commentCurrent(Comment comment){
        commentMapper.commentCurrent(comment);
    }


}

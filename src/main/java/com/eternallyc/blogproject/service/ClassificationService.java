package com.eternallyc.blogproject.service;

import com.eternallyc.blogproject.bean.Article;
import com.eternallyc.blogproject.bean.Classification;
import com.eternallyc.blogproject.mapper.ArticleMapper;
import com.eternallyc.blogproject.mapper.ClassificationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassificationService {

    @Autowired
    ClassificationMapper classificationMapper;

    @Autowired
    ArticleMapper articleMapper;
    //首页获得分类列表
    public List<Classification> getClassificationList(){
        return classificationMapper.getClassificationList();
    }

    //获得分类名
    public String getName(Integer id){
        return classificationMapper.getName(id);
    }

    //获得当前分类所有文章
    public List<Article> getClassList(Integer classification_id){
        return articleMapper.getClassList(classification_id);
    }

    //获得所有分类列表
    public List<Classification> getAllClassificationList(){
        return classificationMapper.getAllClassificationList();
    }

    //添加分类
    public void addNewClassification(String name){
        classificationMapper.addNewClassification(name);
    }

    //更新分类显示状态
    public void updateState(Integer classification_id,Integer isvisiable){
        classificationMapper.updateState(classification_id,isvisiable);
    }

    //根据ID删除分类
    public void deleteClassification(Integer classification_id){
        classificationMapper.deleteClassification(classification_id);
    }

    //更新分类名
    public void updateClasssification(Integer classification_id,String name){
        classificationMapper.updateClasssification(classification_id,name);
    }

    //分类列表增加一篇文章
    public void addOneArticle( Integer classification_id){
        classificationMapper.addOneArticle(classification_id);
    }

    //指定分类中的文章数量-1
    public void reduceOneArticle( Integer classification_id){
        classificationMapper.reduceOneArticle(classification_id);
    }
}

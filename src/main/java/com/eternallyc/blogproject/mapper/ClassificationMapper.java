package com.eternallyc.blogproject.mapper;

import com.eternallyc.blogproject.bean.Classification;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ClassificationMapper {
    //获得显示的分类列表
    @Select("select * from classification where isvisiable=1")
    public List<Classification> getClassificationList();

    //获得分类名
    @Select("select name from classification where classification_id=#{id}")
    public String getName(Integer id);

    //根据ID获得分类
    @Select("select * from classification where classification_id=#{id}")
    public Classification getClassification(Integer id);

    //获得所有分类列表
    @Select("select * from classification")
    public List<Classification> getAllClassificationList();

    //添加分类
    @Insert("insert into classification(name) value (#{name})")
    public void addNewClassification(String name);

    //更新分类显示状态
    @Update("update classification set isvisiable=#{isvisiable} where classification_id=#{classification_id}")
    public void updateState(@Param("classification_id") Integer classification_id,@Param("isvisiable") Integer isvisiable);

    //根据ID删除分类
    @Delete("delete from classification where classification_id=#{classification_id}")
    public void deleteClassification(@Param("classification_id") Integer classification_id);

    //更新分类名
    @Update("update classification set name=#{name} where classification_id=#{classification_id}")
    public void updateClasssification(@Param("classification_id") Integer classification_id,@Param("name") String name);


    //分类列表增加一篇文章
    @Update("update classification set num=num+1 where classification_id=#{classification_id}")
    public void addOneArticle(@Param("classification_id") Integer classification_id);

    //指定分类中的文章数量-1
    @Update("update classification set num=num-1 where classification_id=#{classification_id}")
    public void reduceOneArticle(@Param("classification_id") Integer classification_id);
}

package com.eternallyc.blogproject.mapper;

import com.eternallyc.blogproject.bean.Article;
import com.eternallyc.blogproject.bean.Classification;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ArticleMapper {

    //根据是否置顶来获取博客列表
    @Select("select * from article where topping=#{topping} and state=1")
    public List<Article> getArticles(Integer topping);

    //根据是否是推荐文章来获取推荐博客列表
    @Select("select * from article where recommend=#{recommend} and state=1")
    public List<Article> getRecommendArticles(Integer recommend);

    //获取所有文章
    @Select("select * from article where state=1")
    public List<Article> getAllArticles();

    //根据分类ID来获取文章
    @Select("select * from article where classification_id=#{classification_id} and state=1")
    public List<Article> getClassList(Integer classification_id);

    //获取点击排行榜前8位的
    @Select("select * from article where state=1 order by article.read_number desc limit 0,8")
    public List<Article> getClickRankList();

    //根据更新时间排序所有博客
    @Select("select * from article where state=1 order by article_time desc")
    public List<Article> getAllArticlesTime();

    //根据阅读量排序所有博客
    @Select("select * from article where state=1 order by article.read_number desc")
    public List<Article> getAllArticlesReadNum();




    //根据阅读量对当前分类文章排序
    @Select("select * from article where classification_id=#{classification_id} and state=1 order by read_number desc")
    public List<Article> getReadNumRank(Integer classification_id);

    //根据时间对当前分类文章排序
    @Select("select * from article where classification_id=#{classification_id} and state=1 order by article_time desc")
    public List<Article> getTimeRank(Integer classification_id);




    //搜索文章
    @Select("select * from article where state=1 and article_title like binary #{keyword} or article_content like binary #{keyword} or article_time like binary #{keyword}")
    public List<Article> searchArticles(String keyword);




    //根据当前文章ID获取文章
    @Select("select * from article where article_id=#{article_id} ")
    @Results({
            @Result(id=true,property="article_id",column="article_id"),
            @Result(property="classification",column="classification_id",javaType=Classification.class,
                    one=@One(select="com.eternallyc.blogproject.mapper.ClassificationMapper.getClassification"))
    })
    public Article getCurrentBlog(Integer article_id);




    //当前文章的访问量+1
    @Update("update article set read_number=read_number+1 where article_id = #{article_id}")
    public void updateReadNum(Integer article_id);

    //对当前文章进行点赞
    @Update("update article set like_number=like_number+1 where article_id = #{article_id}")
    public void pointLike(Integer article_id);

    //对当前文章进行评论
    @Update("update article set comment_number=comment_number+1 where article_id = #{article_id}")
    public void addCommentNum(Integer article_id);

    //发表文章
    @Insert("insert into article(article_title,article_content," +
            "article_time,classification_id,topping,state) values (" +
            "#{article_title},#{article_content},#{article_time},#{classification_id},#{topping},#{state})")
    public void publishBlog(@Param("article_title") String article_title,
                         @Param("article_content") String article_content,
                         @Param("article_time")String article_time,
                         @Param("classification_id")String classification_id,
                         @Param("topping")String topping,
                         @Param("state")String state);


    //后台获得当前页文章
    @Select("select * from article limit #{locate},8")
    @Results({
            @Result(id=true,property="article_id",column="article_id"),
            @Result(property="classification",column="classification_id",javaType=Classification.class,
                    one=@One(select="com.eternallyc.blogproject.mapper.ClassificationMapper.getClassification"))
    })
    public List<Article> getBackManageAllArticle(@Param("locate") Integer locate);





    //修改指定文章的置顶状态
    @Update("update article set topping=#{topping} where article_id=#{article_id}")
    public void changeTopState(@Param("article_id") Integer article_id,
                               @Param("topping") Integer topping);

    //删除指定文章
    @Delete("delete from article where article_id=#{article_id}")
    public void delete(@Param("article_id") Integer article_id);

    //获取所有文章的数量
    @Select("select count(*) from article")
    public Integer getArticleCount();

    //修改指定文章的推荐状态
    @Update("update article set recommend=#{recommend} where article_id=#{article_id}")
    public void changeRecommendState(@Param("article_id") Integer article_id,
                               @Param("recommend") Integer recommend);

    //后台搜索文章(通过标题)
    @Select("select * from article where article_title like #{keyword} limit #{locate},8")
    public List<Article> searchBackManageArticle(@Param("keyword")String keyword,
                                                 @Param("locate")Integer locate);
    //获取搜索出的文章的数量
    @Select("select count(*) from article where article_title like #{keyword}")
    public Integer searchBackManageArticleNum(@Param("keyword")String keyword);

}
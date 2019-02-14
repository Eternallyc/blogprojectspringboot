package com.eternallyc.blogproject.service;


import com.eternallyc.blogproject.bean.Article;
import com.eternallyc.blogproject.bean.Classification;
import com.eternallyc.blogproject.mapper.ArticleMapper;
import com.eternallyc.blogproject.mapper.ClassificationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    ClassificationMapper classificationMapper;

    //根据是否置顶来获取博客列表
    public List<Article> getArticles(Integer topping) {
        return articleMapper.getArticles(topping);
    }

    //根据是否是推荐文章来获取推荐博客列表
    public List<Article> getRecommendArticles(Integer recommend) {
        return articleMapper.getRecommendArticles(recommend);
    }


    //根据阅读量进行排序
    public List<Article> getAllArticlesReadNum() {
        return articleMapper.getAllArticlesReadNum();
    }

    //根据时间进行排序
    public List<Article> getAllArticlesTime() {
        return articleMapper.getAllArticlesTime();
    }

    //获取点击排行
    public List<Article> getClickRankList() {
        return articleMapper.getClickRankList();
    }

    //根据阅读量对当前分类文章排序
    public List<Article> getReadNumRank(Integer classification_id) {
        return articleMapper.getReadNumRank(classification_id);
    }

    //根据时间对当前分类文章排序
    public List<Article> getTimeRank(Integer classification_id) {
        return articleMapper.getTimeRank(classification_id);
    }

    //搜索文章
    public List<Article> searchArticles(String keyword) {
        return articleMapper.searchArticles(keyword);
    }

    //根据当前文章ID获取文章
    public Article getCurrentBlog(Integer article_id) {
        return articleMapper.getCurrentBlog(article_id);
    }

    //当前文章的访问量+1
    public void updateReadNum(Integer article_id) {
        articleMapper.updateReadNum(article_id);
    }

    //对当前文章进行点赞
    public void pointLike(Integer article_id) {
        articleMapper.pointLike(article_id);
    }

    //评论当前文章
    public void addCommentNum(Integer article_id) {
        articleMapper.addCommentNum(article_id);
    }

    //发表文章
    public void publishBlog(String article_title,
                            String article_content,
                            String article_time,
                            String classification_id,
                            String topping,
                            String state) {
        articleMapper.publishBlog(article_title, article_content, article_time, classification_id
                , topping, state);
    }

    //保存文章
    public void saveBlog(String article_title,
                         String article_content,
                         String article_time,
                         String classification_id,
                         String topping,
                         String state) {
        articleMapper.publishBlog(article_title, article_content, article_time, classification_id
                , topping, state);
    }

    //获取博客分类图
    public List<ClassificationBlog> getBlogClassificationMap() {
        //首先获取所有分类列表
        List<Classification> list = classificationMapper.getAllClassificationList();

        List<ClassificationBlog> list1 = new ArrayList<>();
        //得到分类名并且得到当前分类的所有博客名
        for (Classification classification : list) {

            ClassificationBlog classificationBlog = new ClassificationBlog();//得到分类实例

            classificationBlog.setName(classification.getName());//设置分类名

            //得到当前分类的所有博客
            List<Article> list2 = articleMapper.getClassList(classification.getClassification_id());

            List<BlogName> list3 = new ArrayList<>();

            for(Article article:list2){
                BlogName blogName = new BlogName();
                blogName.setName(article.getArticle_title());
                list3.add(blogName);
            }
            classificationBlog.setChildren(list3);

            list1.add(classificationBlog);
        }
        return list1;
    }

    //后台获得当前页文章
    public List<Article> getBackManageAllArticle(Integer locate) {
        return articleMapper.getBackManageAllArticle(locate);
    }

    //修改指定文章的置顶状态
    public void changeTopState(Integer article_id,
                               Integer topping) {
        articleMapper.changeTopState(article_id, topping);
    }

    //删除指定文章
    public void delete(Integer article_id) {
        articleMapper.delete(article_id);
    }

    //获取所有文章的数量
    public Integer getArticleCount() {
        return articleMapper.getArticleCount();
    }

    //修改指定文章的推荐状态
    public void changeRecommendState(Integer article_id,
                               Integer recommend) {
        articleMapper.changeRecommendState(article_id, recommend);
    }

    //后台搜索文章(通过标题)
    public List<Article> searchBackManageArticle(String keyword,Integer locate) {
        return articleMapper.searchBackManageArticle(keyword,locate);
    }

    //获取搜索出的文章的数量
    public Integer searchBackManageArticleNum(String keyword) {
        return articleMapper.searchBackManageArticleNum(keyword);
    }
}



//用来获取博客分类图
class ClassificationBlog {
    private String name;
    private List<BlogName> children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BlogName> getChildren() {
        return children;
    }

    public void setChildren(List<BlogName> children) {
        this.children = children;
    }
}

//存储博客名
class BlogName {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
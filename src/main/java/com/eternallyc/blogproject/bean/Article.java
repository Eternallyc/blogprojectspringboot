package com.eternallyc.blogproject.bean;


/**
 * 博客类
 */
public class Article {
    private Integer article_id;//文章id
    private String article_title;//标题
    private String article_time;//时间
    private String article_content;//内容
    private Integer like_number;//点赞量
    private Integer read_number;//阅读量
    private Integer comment_number;//评论量
    private Classification classification;//所属分类

    public Article() {
    }

    public Article(Integer article_id, String article_title, String article_time, String article_content, Integer like_number, Integer read_number, Integer comment_number, Classification classification, Integer state, Integer topping, Integer recommend) {
        this.article_id = article_id;

        this.article_title = article_title;
        this.article_time = article_time;
        this.article_content = article_content;
        this.like_number = like_number;
        this.read_number = read_number;
        this.comment_number = comment_number;
        this.classification = classification;
        this.state = state;
        this.topping = topping;
        this.recommend = recommend;
    }

    private Integer state;//0表示草稿，1表示发表
    private Integer topping;//0表示不置顶，1表示置顶
    private Integer recommend;//0表示不推荐，1表示推荐

    public Integer getComment_number() {
        return comment_number;
    }

    public void setComment_number(Integer comment_number) {
        this.comment_number = comment_number;
    }



    public Integer getRecommend() {
        return recommend;
    }

    public void setRecommend(Integer recommend) {
        this.recommend = recommend;
    }

    public Integer getTopping() {
        return topping;
    }

    public void setTopping(Integer topping) {
        this.topping = topping;
    }



    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Integer article_id) {
        this.article_id = article_id;
    }

    public String getArticle_title() {
        return article_title;
    }

    public void setArticle_title(String article_title) {
        this.article_title = article_title;
    }

    public String getArticle_time() {
        return article_time;
    }

    public void setArticle_time(String article_time) {
        this.article_time = article_time;
    }

    public String getArticle_content() {
        return article_content;
    }

    public void setArticle_content(String article_content) {
        this.article_content = article_content;
    }

    public Integer getLike_number() {
        return like_number;
    }

    public void setLike_number(Integer like_number) {
        this.like_number = like_number;
    }

    public Integer getRead_number() {
        return read_number;
    }

    public void setRead_number(Integer read_number) {
        this.read_number = read_number;
    }

    public Classification getClassification() {
        return classification;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }
}

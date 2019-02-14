package com.eternallyc.blogproject.controller;

import com.eternallyc.blogproject.bean.AdminMessage;
import com.eternallyc.blogproject.bean.Article;
import com.eternallyc.blogproject.bean.Comment;
import com.eternallyc.blogproject.service.AdminService;
import com.eternallyc.blogproject.service.ArticleService;
import com.eternallyc.blogproject.service.CommentService;
import com.eternallyc.blogproject.service.EmilService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
@RequestMapping("/blogs/article")
@Controller
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @Autowired
    CommentService commentService;

    @Autowired
    AdminService adminService;
    /**
     * 获取所有博客列表(默认排序)
     * @return
     */
    private static Logger log = Logger.getLogger(ArticleController.class);
    @GetMapping("/getallarticles/defaultsort")
    public ModelAndView getAllArticles(HttpServletRequest request){
        /*log.debug(request.getRequestURL());*/

        List<Article> toppinglist=articleService.getArticles(1);//置顶博客
        List<Article> commonlist=articleService.getArticles(0);//不置顶博客
        ModelAndView mv =new ModelAndView();
        mv.addObject("toppinglist",toppinglist);
        mv.addObject("commonlist",commonlist);
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }


    /**
     * 获取所有博客列表(时间排序)
     * @return
     */
    @GetMapping("/getallarticles/timesort")
    public ModelAndView getAllArticlesTime(){
        ModelAndView mv =new ModelAndView();
        mv.addObject("list",articleService.getAllArticlesTime());
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }

    /**
     * 获取所有博客列表(访问量排序)
     * @return
     */
    @GetMapping("/getallarticles/readnumsort")
    public ModelAndView getAllArticlesReadNum(){
        ModelAndView mv =new ModelAndView();
        mv.addObject("list",articleService.getAllArticlesReadNum());
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }


    /**
     * 搜索文章
     */
    @PostMapping("/searcharticles")
    public ModelAndView searchArticles(@RequestBody(required = false) Map<String,String> map){
        ModelAndView modelAndView = new ModelAndView();
        String keyword="%"+map.get("keyword")+"%";
        modelAndView.addObject("list",articleService.searchArticles(keyword));
        modelAndView.setView(new MappingJackson2JsonView());
        return modelAndView;
    }

    /**
     * 根据当前文章ID获取文章
     */
    @PostMapping("/getCurrentBlog")
    public ModelAndView getCurrentBlog(@RequestBody(required = false) Map<String,String> map){
        ModelAndView mv =new ModelAndView();
        //博客访问量加一
        articleService.updateReadNum(Integer.valueOf(map.get("id")));
        //获得当前所有文章的评论
        mv.addObject("commentlist",commentService.getAllComments(Integer.valueOf(map.get("id"))));
        //得到当前文章
        mv.addObject("article",articleService.getCurrentBlog(Integer.valueOf(map.get("id"))));
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }


    /**
     * 对当前文章进行点赞
     */
    @PostMapping("/pointlike")
    public ModelAndView pointLike(@RequestBody(required = false) Map<String,String> map){
        ModelAndView mv =new ModelAndView();
        //对当前文章进行点赞
        articleService.pointLike(Integer.valueOf(map.get("id")));
        mv.addObject("article",articleService.getCurrentBlog(Integer.valueOf(map.get("id"))));//得到当前文章
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }

    /**
     * 评论当前文章
     */
    @PostMapping("/commentcurrent")
    public ModelAndView commentCurrent(@RequestBody(required = false) Map<String,String> map){
        ModelAndView mv =new ModelAndView();
        Comment comment =  new Comment(Integer.valueOf(map.get("currentblogid")),map.get("username"),map.get("avatar"),map.get("content"),map.get("time"),map.get("reply"));
        //对当前文章进行评论
        commentService.commentCurrent(comment);

        //当前文章的评论数+1
        articleService.addCommentNum(Integer.valueOf(map.get("currentblogid")));
        //获得当前所有文章的评论
        mv.addObject("commentlist",commentService.getAllComments(Integer.valueOf(map.get("currentblogid"))));
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }

    @Autowired
    EmilService emilService;
    //文章评论发送邮件服务
    @PostMapping("/sendArticleEmil")
    public ModelAndView sendArticleEmil(@RequestBody(required = false) Map<String,String> map){
        ModelAndView mv =new ModelAndView();
        Comment comment =  new Comment(Integer.valueOf(map.get("currentblogid")),map.get("username"),map.get("avatar"),map.get("content"),map.get("time"),map.get("reply"));
        emilService.sendEmil(comment);
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }


    // 得到关于我的标题和内容
    @RequestMapping("/aboutme")
    public ModelAndView getTitleAndMessage(){
        ModelAndView mv =new ModelAndView();
        AdminMessage adminMessage=adminService.getTitleAndMessage();
        mv.addObject("title",adminMessage.getTitle());
        mv.addObject("content",adminMessage.getAboutme());
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }

}

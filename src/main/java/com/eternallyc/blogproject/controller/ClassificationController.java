package com.eternallyc.blogproject.controller;

import com.eternallyc.blogproject.service.ArticleService;
import com.eternallyc.blogproject.service.ClassificationService;
import com.eternallyc.blogproject.service.PhotoAlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Map;

@Controller
@RequestMapping("/blogs/classification")
public class ClassificationController {

    @Autowired
    ClassificationService classificationService;

    @Autowired
    ArticleService articleService;

    @Autowired
    PhotoAlbumService photoAlbumService;

    // 分类列表中的左侧栏
    @GetMapping("/classificationleft")
    public ModelAndView getSimpleList(){
        ModelAndView mv = new ModelAndView();
        //获取左侧栏目的分类列表
        mv.addObject("simplelist",classificationService.getClassificationList());
        //获取左侧栏目的点击排行
        mv.addObject("clickranklist",articleService.getClickRankList());
        //获取左侧栏目的推荐博客
        mv.addObject("recommendlist",articleService.getRecommendArticles(1));

        mv.setView(new MappingJackson2JsonView());
        return mv;
    }

    // 分类列表中的左侧栏
    @GetMapping("/indexleftlist")
    public ModelAndView getIndexList(){
        ModelAndView mv = new ModelAndView();

        //获取首页左侧栏目的分类列表
        mv.addObject("simplelist",classificationService.getClassificationList());

        //获取首页左侧栏目的推荐博客
        mv.addObject("recommendlist",articleService.getRecommendArticles(1));

        //获取首页左侧栏目的推荐相册
        mv.addObject("photoAlbumList",photoAlbumService.getRecommendList());
        mv.setView(new MappingJackson2JsonView());

        return mv;
    }
    //通过ID获取当前的分类名和分类中存在的文章
    @RequestMapping("/getNameAndList")
    public ModelAndView getNameAndList(@RequestBody(required = false) Map<String,String> map){
        ModelAndView mv = new ModelAndView();
        //获得分类中的文章
        mv.addObject("list",classificationService.getClassList(Integer.valueOf(map.get("classification_id"))));
        //获得分类名
        mv.addObject("name",classificationService.getName(Integer.valueOf(map.get("classification_id"))));
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }


    /**
     * 获取当前分类博客列表(时间排序)
     * @return
     */
    @RequestMapping("/timesort")
    public ModelAndView getClassificationArticlesTime(@RequestBody(required = false) Map<String,String> map){
        ModelAndView mv =new ModelAndView();
      /*  System.out.println("当前分类时间排序");*/
        mv.addObject("list",articleService.getTimeRank(Integer.valueOf(map.get("classification_id"))));
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }

    /**
     * 获取当前分类博客列表(访问量排序)
     * @return
     */
    @RequestMapping("/readnumsort")
    public ModelAndView getClassificationArticlesReadNum(@RequestBody(required = false) Map<String,String> map){
        ModelAndView mv =new ModelAndView();
        mv.addObject("list",articleService.getReadNumRank(Integer.valueOf(map.get("classification_id"))));
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }


    /**
     * 获取当前分类博客列表(默认)
     * @return
     */
    @RequestMapping("/defaultsort")
    public ModelAndView getClassificationArticles(@RequestBody(required = false) Map<String,String> map){
        ModelAndView mv =new ModelAndView();
        mv.addObject("list",classificationService.getClassList(Integer.valueOf(map.get("classification_id"))));
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }

    /**
     * 后台获取所有分类列表
     * @return
     */
    @RequestMapping("/admin/getAllClassificationList")
    public ModelAndView getAllClassificationList(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list",classificationService.getAllClassificationList());
        modelAndView.setView(new MappingJackson2JsonView());
        return modelAndView;
    }

    /**
     * 后台新增分类
     */
    @RequestMapping("/admin/addNewClassification")
    public ModelAndView addNewClassification(@RequestBody(required = false) Map<String,String> map){
        classificationService.addNewClassification(map.get("name"));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list",classificationService.getAllClassificationList());
        modelAndView.setView(new MappingJackson2JsonView());
        return modelAndView;
    }

    /**
     * 更新显示状态
     */
    @RequestMapping("/admin/updateState")
    public ModelAndView updateState(@RequestBody(required = false) Map<String,String> map){
        classificationService.updateState(Integer.valueOf(map.get("id")),Integer.valueOf(map.get("isvisiable")));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list",classificationService.getAllClassificationList());
        modelAndView.setView(new MappingJackson2JsonView());
        return modelAndView;
    }

    /**
     * 删除分类
     */
    @RequestMapping("/admin/deleteClassification")
    public ModelAndView deleteClassification(@RequestBody(required = false) Map<String,String> map){
        classificationService.deleteClassification(Integer.valueOf(map.get("id")));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list",classificationService.getAllClassificationList());
        modelAndView.setView(new MappingJackson2JsonView());
        return modelAndView;
    }
    /**
     * 修改分类名
     */
    @RequestMapping("/admin/updateClasssification")
    public ModelAndView updateClasssification(@RequestBody(required = false) Map<String,String> map){
        classificationService.updateClasssification(Integer.valueOf(map.get("id")),map.get("name"));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list",classificationService.getAllClassificationList());
        modelAndView.setView(new MappingJackson2JsonView());
        return modelAndView;
    }
}

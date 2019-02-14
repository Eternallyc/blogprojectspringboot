package com.eternallyc.blogproject.controller;

import com.eternallyc.blogproject.bean.PhotoAlbumComment;
import com.eternallyc.blogproject.service.PhotoAlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Map;

@Controller
@RequestMapping("/blogs/photoalbum")
public class PhotoAlbumController {
    @Autowired
    PhotoAlbumService photoAlbumService;

    @GetMapping("/getalllist")
    public ModelAndView getAllList() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("photoAlbumList", photoAlbumService.getAllList());
        modelAndView.setView(new MappingJackson2JsonView());
        return modelAndView;
    }

    @GetMapping("/getRecommendList")
    public ModelAndView getRecommendList() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("photoAlbumList", photoAlbumService.getRecommendList());
        modelAndView.setView(new MappingJackson2JsonView());
        return modelAndView;
    }

    @PostMapping("/getAllPicture")
    public ModelAndView getAllPicture(@RequestBody(required = false) Map<String, String> map) {
        ModelAndView modelAndView = new ModelAndView();
        //得到当前相册的信息
        modelAndView.addObject("currrentalbum", photoAlbumService.getCurrentAlbum(Integer.valueOf(map.get("id"))));

        //得到当前相册所有图片
        modelAndView.addObject("picturelist", photoAlbumService.getAllPicture(Integer.valueOf(map.get("id"))));

        // 得到评论列表
        modelAndView.addObject("commentlist", photoAlbumService.getAllComments(Integer.valueOf(map.get("id"))));

       // 阅读量+1
        photoAlbumService.updateReadNum(Integer.valueOf(map.get("id")));
        modelAndView.setView(new MappingJackson2JsonView());
        return modelAndView;
    }

    //对当前相册进行点赞
    @PostMapping("/pointlike")
    public ModelAndView pointLike(@RequestBody(required = false) Map<String, String> map){
        ModelAndView modelAndView = new ModelAndView();
        //点赞
        photoAlbumService.pointLike(Integer.valueOf(map.get("id")));
        //得到当前相册的信息
        modelAndView.addObject("currrentalbum", photoAlbumService.getCurrentAlbum(Integer.valueOf(map.get("id"))));
        modelAndView.setView(new MappingJackson2JsonView());
        return modelAndView;
    }


    /**
     * 评论当前相册
     */
    @PostMapping("/commentcurrent")
    public ModelAndView commentCurrent(@RequestBody(required = false) Map<String,String> map){
        ModelAndView mv =new ModelAndView();
        PhotoAlbumComment photoAlbumComment = new PhotoAlbumComment(Integer.valueOf(map.get("albumid")),map.get("username"),map.get("avatar"),map.get("content"),map.get("time"),map.get("reply"));

        //对当前相册进行评论
        photoAlbumService.commentCurrent(photoAlbumComment);

        //当前文章的评论数+1
        photoAlbumService.addCommentNum(Integer.valueOf(map.get("albumid")));

        // 得到评论列表
        mv.addObject("commentlist", photoAlbumService.getAllComments(Integer.valueOf(map.get("albumid"))));
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }
}

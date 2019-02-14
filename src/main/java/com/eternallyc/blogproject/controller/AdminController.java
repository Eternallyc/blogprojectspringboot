package com.eternallyc.blogproject.controller;

import com.eternallyc.blogproject.bean.AdminMessage;
import com.eternallyc.blogproject.bean.ResultMessage;
import com.eternallyc.blogproject.service.AdminService;
import com.eternallyc.blogproject.service.ArticleService;
import com.eternallyc.blogproject.service.ClassificationService;
import com.eternallyc.blogproject.service.PhotoAlbumService;
import com.eternallyc.blogproject.util.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/blogs")
public class AdminController {
    @Autowired
    ClassificationService classificationService;

    @Autowired
    AdminService adminService;

    @Autowired
    ArticleService articleService;

    @Autowired
    PhotoAlbumService photoAlbumService;

    @RequestMapping("/login")
    public ModelAndView login(@RequestBody(required = false) Map<String, String> map, HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView();
        ResultMessage resultMessage = adminService.login(map.get("name"), map.get("passwd"), httpServletRequest);//得到认证信息
        modelAndView.addObject("flag", resultMessage.isSuccess());
        modelAndView.addObject("message", resultMessage.getMessage());
        modelAndView.setView(new MappingJackson2JsonView());
        return modelAndView;
    }

    //数据显示
    @RequestMapping("/admin/dateShow")
    public ModelAndView dateShow() {
        ModelAndView mv = new ModelAndView();
        //总阅读量
        mv.addObject("readnum", adminService.sumReadNum());
        //总评论量
        mv.addObject("commentnum", adminService.sumCommentNum());
        //总点赞量
        mv.addObject("likenum", adminService.sumLikeNum());
        //总留言量
        mv.addObject("leavemessagenum", adminService.sumLeaveMessage());

        //获取所有分类
        mv.addObject("classificationlist", classificationService.getAllClassificationList());

        //获取所有分类的阅读量
        mv.addObject("classificationlistreadnum", adminService.getAllClassificationReadNum());

        //获取所有分类的点赞量
        mv.addObject("classificationlistlikenum", adminService.getAllClassificationLikeNum());

        //获取所有分类的评论量
        mv.addObject("classificationlistcommentnum", adminService.getAllClassificationCommentNum());

        //获得所有相册列表
        mv.addObject("albumlist", photoAlbumService.getPhotoAlbumName());

        //得到博客的分类图
        mv.addObject("blogmap", articleService.getBlogClassificationMap());

        mv.setView(new MappingJackson2JsonView());
        return mv;
    }

    //得到所有分类
    @RequestMapping("/admin/blogwrite/getallclassification")
    public ModelAndView getAllClassifications() {
        ModelAndView mv = new ModelAndView();
        mv.setView(new MappingJackson2JsonView());
        mv.addObject("list", classificationService.getAllClassificationList());
        return mv;
    }

    //发表文章
    @RequestMapping("/admin/blogwrite/publishblog")
    public ModelAndView publishBlog(@RequestBody(required = false) Map<String, String> map) {
        ModelAndView mv = new ModelAndView();
        mv.setView(new MappingJackson2JsonView());
        //所在分类的文章+1
        classificationService.addOneArticle(Integer.valueOf(map.get("type")));
        //发表文章
        articleService.publishBlog(map.get("title"), map.get("content"), map.get("time"),
                map.get("type"), map.get("top"), map.get("state"));
        return mv;
    }

    //保存文章
    @RequestMapping("/admin/blogwrite/saveblog")
    public ModelAndView saveBlog(@RequestBody(required = false) Map<String, String> map) {
        ModelAndView mv = new ModelAndView();
        mv.setView(new MappingJackson2JsonView());
        //发表文章
        articleService.saveBlog(map.get("title"), map.get("content"), map.get("time"),
                map.get("type"), map.get("top"), map.get("state"));
        return mv;
    }

    //获得当前页的文章
    @RequestMapping("/admin/blogmanage/getcurrentblog")
    public ModelAndView getCurrentPage(@RequestBody(required = false) Map<String, String> map) {
        ModelAndView mv = new ModelAndView();
        mv.setView(new MappingJackson2JsonView());
        mv.addObject("list", articleService.getBackManageAllArticle(Integer.valueOf(map.get("locate"))));
        mv.addObject("count", articleService.getArticleCount());
        return mv;
    }

    //修改指定文章的置顶状态
    @RequestMapping("/admin/blogmanage/changetopstate")
    public ModelAndView changeTopState(@RequestBody(required = false) Map<String, String> map) {
        ModelAndView mv = new ModelAndView();
        mv.setView(new MappingJackson2JsonView());
        articleService.changeTopState(Integer.valueOf(map.get("id")), Integer.valueOf(map.get("top")));
        return mv;
    }

    // 删除文章
    @RequestMapping("/admin/blogmanage/delete")
    public ModelAndView delete(@RequestBody(required = false) Map<String, String> map) {
        ModelAndView mv = new ModelAndView();
        mv.setView(new MappingJackson2JsonView());
        articleService.delete(Integer.valueOf(map.get("id")));//删除文章
        classificationService.reduceOneArticle(Integer.valueOf(map.get("classificationid")));//更新分类中文章的数量
        mv.addObject("list", articleService.getBackManageAllArticle(Integer.valueOf(map.get("locate"))));
        return mv;
    }

    //修改指定文章的推荐状态
    @RequestMapping("/admin/blogmanage/changerecommendstate")
    public ModelAndView changeRecommendState(@RequestBody(required = false) Map<String, String> map) {
        ModelAndView mv = new ModelAndView();
        mv.setView(new MappingJackson2JsonView());
        articleService.changeRecommendState(Integer.valueOf(map.get("id")), Integer.valueOf(map.get("recommendstate")));
        return mv;
    }

    //搜索文章
    @RequestMapping("/admin/blogmanage/searchblog")
    public ModelAndView searchBlog(@RequestBody(required = false) Map<String, String> map) {
        ModelAndView mv = new ModelAndView();
        mv.setView(new MappingJackson2JsonView());

        String keyword = "%" + map.get("keyword") + "%";

        mv.addObject("list", articleService.searchBackManageArticle(keyword, Integer.valueOf(map.get("locate"))));

        mv.addObject("count", articleService.searchBackManageArticleNum(keyword));
        return mv;
    }

    // 得到关于我的标题和内容
    @RequestMapping("/admin/getTitleAndMessage")
    public ModelAndView getTitleAndMessage() {
        ModelAndView mv = new ModelAndView();
        mv.setView(new MappingJackson2JsonView());
        AdminMessage adminMessage = adminService.getTitleAndMessage();
        mv.addObject("title", adminMessage.getTitle());
        mv.addObject("content", adminMessage.getAboutme());
        return mv;
    }

    // 保存关于我的标题和内容
    @RequestMapping("/admin/saveTitleAndMessage")
    public ModelAndView saveTitleAndMessage(@RequestBody(required = false) Map<String, String> map) {
        ModelAndView mv = new ModelAndView();
        mv.setView(new MappingJackson2JsonView());
        adminService.saveTitleAndMessage(map.get("title"), map.get("content"));
        return mv;
    }

    //获得个人资料的姓名和简介和头像
    @RequestMapping("/admin/getNameAndIntroduction")
    public ModelAndView getNameAndIntroduction() {
        ModelAndView mv = new ModelAndView();
        mv.setView(new MappingJackson2JsonView());
        AdminMessage adminMessage = adminService.getNameAndIntroduction();
        mv.addObject("name", adminMessage.getName());
        mv.addObject("introduction", adminMessage.getTroduction());
        mv.addObject("avatar", adminMessage.getAvatar());
        return mv;
    }

    //获得个人资料的姓名和简介
    @RequestMapping("/admin/saveNameAndIntroduction")
    public ModelAndView saveNameAndIntroduction(@RequestBody(required = false) Map<String, String> map) {
        ModelAndView mv = new ModelAndView();
        mv.setView(new MappingJackson2JsonView());
        adminService.saveNameAndIntroduction(map.get("name"), map.get("introduction"));
        return mv;
    }

    //上传头像
    @PostMapping("/uploadClient")
    @ResponseBody
    public String testUpload(@RequestParam("avatar") MultipartFile file) throws IOException {
//        System.out.println("后台文件上传函数");
//        System.out.println("获取到的文件名称为：" + file);
        ModelAndView mv = new ModelAndView();
        mv.setView(new MappingJackson2JsonView());
        String filePath = file.getOriginalFilename(); // 获取文件的名称

        System.out.println(filePath);
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath));
        outputStream.write(file.getBytes());
        outputStream.flush();
        outputStream.close();




        String url = new UploadFile().geturl(new File( filePath), "avatar.jpg");
        //保存到数据库
        File file1 = new File(filePath);
        if(file1.exists()){
            file1.delete();
        }
        adminService.updateavatar(url);
        System.out.println(url);

        return url;

    }

    //修改密码
    @PostMapping("/admin/changeuserpwd")
    public ModelAndView updateuserpwd( Map<String,String> map) {
        ModelAndView mv = new ModelAndView();
        mv.setView(new MappingJackson2JsonView());
        adminService.updateuserpwd(map.get("passwd"));
        mv.addObject("flag","修改成功！！");
        return mv;
    }

}

package com.eternallyc.blogproject.service;

import com.eternallyc.blogproject.bean.Admin;
import com.eternallyc.blogproject.bean.AdminMessage;
import com.eternallyc.blogproject.bean.Classification;
import com.eternallyc.blogproject.bean.ResultMessage;
import com.eternallyc.blogproject.mapper.AdminMapper;
import com.eternallyc.blogproject.util.Const;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    AdminMapper adminMapper;

    @Autowired
    ClassificationService classificationService;

   public ResultMessage login(String username, String passwd, HttpServletRequest request){
       Admin admin = adminMapper.login(username,passwd);
       //如果登录失败
       if(admin==null){
           return new ResultMessage(false, "登录失败");
       }
       //登录成功
       String jwtToken = Jwts.builder().setSubject(username)
               .setExpiration(new Date(Const.JWT_TOKEN_EXP))
               .signWith(SignatureAlgorithm.HS256, Const.JWT_SECRET_KEY)
               .compact();
       /*System.out.println("token=" + "Bearer " + jwtToken);*/
       return new ResultMessage(true, "Bearer " + jwtToken);
   }

    //总阅读量
   public Integer sumReadNum(){
       return adminMapper.sumArticleReadNum()+adminMapper.sumphotoalbumReadNum();
   }

   //总评论量
    public Integer sumCommentNum(){
       return adminMapper.sumArticleCommentNum()+adminMapper.sumphotoalbumCommentNum();
    }

    //总点赞量
    public Integer sumLikeNum(){
        return adminMapper.sumArticleLikeNum()+adminMapper.sumphotoalbumLikeNum();
    }

    //总留言量
    public Integer sumLeaveMessage(){
       return adminMapper.leaveMessageNum();
    }


    //获取所有分类的阅读量
    public List<Integer> getAllClassificationReadNum(){
        List<Classification> list=classificationService.getAllClassificationList();
        List<Integer> list1 = new ArrayList<>();
        for(Classification classification:list){
         Integer num =adminMapper.getCunrrentClassificationReadNum(classification.getClassification_id());
            if(num==null){//如果为null
                list1.add(0);
            }else{
                list1.add(num);
            }
        }
        return  list1;
    }

    //获取所有分类的点赞量
    public List<Integer> getAllClassificationLikeNum(){
        List<Classification> list=classificationService.getAllClassificationList();
        List<Integer> list1 = new ArrayList<>();
        for(Classification classification:list){
            Integer num =adminMapper.getCunrrentClassificationLikeNum(classification.getClassification_id());
            if(num==null){//如果为null
                list1.add(0);
            }else{
                list1.add(num);
            }
        }
        return  list1;
    }

    //获取所有分类的评论量
    public List<Integer> getAllClassificationCommentNum(){
        List<Classification> list=classificationService.getAllClassificationList();
        List<Integer> list1 = new ArrayList<>();
        for(Classification classification:list){
            Integer num = adminMapper.getCunrrentClassificationCommentNum(classification.getClassification_id());
            if(num==null){//如果为null
                list1.add(0);
            }else{
                list1.add(num);
            }
        }
        return  list1;
    }

    // 得到关于我的标题和内容
    public AdminMessage getTitleAndMessage(){
       return adminMapper.getTitleAndMessage();
    }

    // 保存关于我的标题和内容
    public void saveTitleAndMessage(String title,String content){
         adminMapper.saveTitleAndMessage(title,content);
    }

    // 获得个人资料的姓名和简介和头像
    public AdminMessage getNameAndIntroduction(){
        return adminMapper.getNameAndIntroduction();
    }

    // 保存关于我的姓名和简介
    public void saveNameAndIntroduction(String name,String troduction){
        adminMapper.saveNameAndIntroduction(name,troduction);
    }

    //更新头像
    public void updateavatar(String avatar){
       adminMapper.updateavatar(avatar);
    }

    //修改密码
    public void updateuserpwd(String userpwd){
        adminMapper.updateuserpwd(userpwd);
    }
}

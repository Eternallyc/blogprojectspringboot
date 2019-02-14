package com.eternallyc.blogproject.util;

import com.aliyun.oss.OSSClient;

import java.io.File;
import java.util.Date;

public class UploadFile {
    /**
     * 上传文件获得url
     * @return
     */
    public String geturl(File file,String name){
        // 创建ClientConfiguration实例
        // 配置代理为本地8080端口
        String endpoint = "oss-cn-shenzhen.aliyuncs.com";
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录
        // https://ram.console.aliyun.com 创建
        String accessKeyId = "*****"; //阿里云的accessKeyId
        String accessKeySecret = "****";//阿里云的accessKeySecret
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 上传到指定目录
        name="blog/img/"+name;
        ossClient.putObject("backage", name, file);
        System.out.println(name);
        Date expiration = new Date(new Date().getTime() + 3600 * 1000 * 24 * 365 * 10);
        String url = ossClient.generatePresignedUrl("backage", name, expiration).toString();
       /* System.out.println("上传成功");*/
        // 关闭client
        ossClient.shutdown();
        return url;
    }
}

package com.eternallyc.blogproject.bean;

/**
 * 博主类
 */
public class Admin {
    private Integer id;//博主ID
    private String username;//博主帐号
    private String userpwd;//博主密码

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }
}

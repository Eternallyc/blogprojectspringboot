package com.eternallyc.blogproject.bean;

import java.io.Serializable;

public class ResultMessage implements Serializable {

    private boolean success;//是否成功
    private String message;//返回信息
    public ResultMessage(boolean success, String message) {
        super();
        this.success = success;
        this.message = message;
    }
    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

}


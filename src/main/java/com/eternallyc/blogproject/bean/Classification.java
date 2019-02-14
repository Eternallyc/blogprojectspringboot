package com.eternallyc.blogproject.bean;

/**
 * 分类 类
 */
public class Classification {
    private Integer classification_id;
    private String name;
    private Integer num;
    private Integer isvisiable;//是否显示

    public Integer getIsvisiable() {
        return isvisiable;
    }

    public void setIsvisiable(Integer isvisiable) {
        this.isvisiable = isvisiable;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getClassification_id() {
        return classification_id;
    }

    public void setClassification_id(Integer classification_id) {
        this.classification_id = classification_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

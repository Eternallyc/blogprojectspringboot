package com.eternallyc.blogproject.bean;

/**
 * 草稿类
 */
public class Draft {

    private Integer draft_id;//草稿ID
    private String draft_title;//
    private String draft_time;
    private String draft_content;
    private String draft_classification;

    public Integer getDraft_id() {
        return draft_id;
    }

    public void setDraft_id(Integer draft_id) {
        this.draft_id = draft_id;
    }

    public String getDraft_title() {
        return draft_title;
    }

    public void setDraft_title(String draft_title) {
        this.draft_title = draft_title;
    }

    public String getDraft_time() {
        return draft_time;
    }

    public void setDraft_time(String draft_time) {
        this.draft_time = draft_time;
    }

    public String getDraft_content() {
        return draft_content;
    }

    public void setDraft_content(String draft_content) {
        this.draft_content = draft_content;
    }

    public String getDraft_classification() {
        return draft_classification;
    }

    public void setDraft_classification(String draft_classification) {
        this.draft_classification = draft_classification;
    }
}

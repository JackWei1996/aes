package com.aisino.pojo;

public class ReMail {
    private String reMail;

    private String desc;

    private String title;

    public String getReMail() {
        return reMail;
    }

    public void setReMail(String reMail) {
        this.reMail = reMail == null ? null : reMail.trim();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }
}
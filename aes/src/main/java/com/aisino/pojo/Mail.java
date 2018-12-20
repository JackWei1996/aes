package com.aisino.pojo;

import java.util.Date;

public class Mail {
    private Integer mId;

    private String proName;

    private String seMail;

    private String reMail;

    private String coMail;

    private String title;

    private String file;

    private Date seTime;

    private Integer statu;

    public Integer getmId() {
        return mId;
    }

    public void setmId(Integer mId) {
        this.mId = mId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName == null ? null : proName.trim();
    }

    public String getSeMail() {
        return seMail;
    }

    public void setSeMail(String seMail) {
        this.seMail = seMail == null ? null : seMail.trim();
    }

    public String getReMail() {
        return reMail;
    }

    public void setReMail(String reMail) {
        this.reMail = reMail == null ? null : reMail.trim();
    }

    public String getCoMail() {
        return coMail;
    }

    public void setCoMail(String coMail) {
        this.coMail = coMail == null ? null : coMail.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file == null ? null : file.trim();
    }

    public Date getSeTime() {
        return seTime;
    }

    public void setSeTime(Date seTime) {
        this.seTime = seTime;
    }

    public Integer getStatu() {
        return statu;
    }

    public void setStatu(Integer statu) {
        this.statu = statu;
    }
}
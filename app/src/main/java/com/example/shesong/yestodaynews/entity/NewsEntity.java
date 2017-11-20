package com.example.shesong.yestodaynews.entity;

/**
 * Created by shesong on 2017/11/16.
 */

public class NewsEntity {
//"ctime": "2017-11-14 21:25",
//        "title": "今天，习近平在老挝见了一些很特别的朋友",
//        "description": "搜狐国内",
//        "picUrl": "http://photocdn.sohu.com/20171114/Img522403934_ss.jpeg",
//        "url": "http://news.sohu.com/20171114/n522403931.shtml"
    private String ctime;
    private String title;
    private String description;
    private String picUrl;
    private String url;

    public String getCtime() {
        return ctime;
    }

    public String getDescription() {
        return description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

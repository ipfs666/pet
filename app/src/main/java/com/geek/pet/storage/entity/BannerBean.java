package com.geek.pet.storage.entity;

public class BannerBean {
    private String articleId;//banner的id
    private String title;//banner标题
    private String path;//banner图片地址
    private String url;//活动网页地址
    private int linkcategory;//banner类型

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setLinkcategory(int linkcategory) {
        this.linkcategory = linkcategory;
    }

    public int getLinkcategory() {
        return linkcategory;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
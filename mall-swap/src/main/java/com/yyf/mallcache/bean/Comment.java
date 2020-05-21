package com.yyf.mallcache.bean;

import java.util.Date;

public class Comment {
    private Integer id;
    private User user;
    private Integer productId;
    private String words;
    private Date time;


    public Comment() {
    }

    public Comment(User user, Integer productId, String words, Date time) {
        this.user = user;
        this.productId = productId;
        this.words = words;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", user=" + user +
                ", productId=" + productId +
                ", words='" + words + '\'' +
                ", time=" + time +
                '}';
    }
}

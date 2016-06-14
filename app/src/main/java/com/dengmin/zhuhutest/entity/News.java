package com.dengmin.zhuhutest.entity;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * 这是新闻列表
 */
public class News extends DataSupport implements Serializable{

    private int id;//这个对应了自增长的id
    private int news_id; //这个才是新闻的唯一的id 没有更改版本 还会对应了异步加载对应的具体的新闻页面
    private String title;
    private String image;


    public News(){
    }

    public News(int news_id,String title,String image){
        this.news_id = news_id;
        this.title = title;
        this.image = image;
    }

    public int getNews_id() {
        return news_id;
    }

    public void setNews_id(int news_id) {
        this.news_id = news_id;
    }

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

package com.dengmin.zhuhutest.entity;

import java.io.Serializable;

/**
 * Created by dmin on 2016/5/23.
 * 这是新闻列表
 */
public class News implements Serializable{

    private int id;
    private String title;
    private String image;


    public News(){
    }

    public News(int id,String title,String image){
        this.id = id;
        this.title = title;
        this.image = image;
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

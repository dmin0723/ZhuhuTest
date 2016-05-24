package com.dengmin.zhuhutest.entity;

import java.util.ArrayList;

/**
 * Created by dmin on 2016/5/23.
 */
public class NewsDetail {
    //去查看相应的HTML页面，给到相应的解析 ?还没有去看
    //与loadNewsDeailTask 有关联

    private String body;
    private String image_source;
    private String title;
    private String image;//头部图片
    private String share_url;

    private ArrayList<String> js;
    private int type;
    private String ga_prefix;
    private long id;
    private ArrayList<String> css;

    public String getBody() {
        return body;
    }

    public ArrayList<String> getCss() {
        return css;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public long getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getImage_source() {
        return image_source;
    }

    public ArrayList<String> getJs() {
        return js;
    }

    public String getShare_url() {
        return share_url;
    }

    public String getTitle() {
        return title;
    }

    public int getType() {
        return type;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setCss(ArrayList<String> css) {
        this.css = css;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }

    public void setJs(ArrayList<String> js) {
        this.js = js;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(int type) {
        this.type = type;
    }
}

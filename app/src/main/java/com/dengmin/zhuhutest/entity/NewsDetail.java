package com.dengmin.zhuhutest.entity;

import java.util.ArrayList;

/**
 * Created by dmin on 2016/5/23.
 */
public class NewsDetail {
    //去查看相应的HTML页面，给到相应的解析 ?还没有去看
    //与loadNewsDeailTask 有关联
    /**
     * body : <div class="main-wrap content-wrap"><p>致敬是对某个桥段，某几个镜头，某个造型，某段对话高度复制，属于表达导演对自己偶像的敬仰，一般只有资深影迷才会发现。</p>blah blah</div>
     * image_source : 《一步之遥》
     * title : 致敬、恶搞、借鉴、模仿、抄袭，到底怎么区分？
     * image : http://pic1.zhimg.com/930cf6f414db290556cd068235ff8f1c.jpg
     * share_url : http://daily.zhihu.com/story/7815067
     * js : []
     * ga_prefix : 013010
     * type : 0
     * id : 7815067
     * css : ["http://news-at.zhihu.com/css/news_qa.auto.css?v=77778"]
     *
     *
     *  private String body;
     private String image_source;
     private String title;
     private String image;
     private String share_url;
     @PrimaryKey
     private int id;
     private RealmList<RealmString> css;
     */

    private String body;
    private String image_source;
    private String title;
    private String image;//头部图片
    private String share_url;

    private ArrayList<String> js;//可以不用
    private int type;//可以不用
    private String ga_prefix;//可以不用

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

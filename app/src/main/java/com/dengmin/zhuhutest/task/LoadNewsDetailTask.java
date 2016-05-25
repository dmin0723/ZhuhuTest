package com.dengmin.zhuhutest.task;

import android.os.AsyncTask;
import android.webkit.WebView;

import com.dengmin.zhuhutest.entity.NewsDetail;
import com.dengmin.zhuhutest.http.Http;
import com.dengmin.zhuhutest.http.JsonHelper;

import org.json.JSONException;

import java.io.IOException;

/**
 * Created by dmin on 2016/5/24.
 * 与Http对应，解析相应的HTML代码
 */
public class LoadNewsDetailTask extends AsyncTask<Integer,Void,NewsDetail>{

    private WebView mWebView;

    public LoadNewsDetailTask(WebView mWebView) {
        this.mWebView = mWebView;
    }

    //必须具备的 解析和下载json
    @Override
    protected NewsDetail doInBackground(Integer... params) {
        NewsDetail mNewsDetail = null;
        try{
           mNewsDetail = JsonHelper.parseJsonToDetail(Http.getNewsDetail(params[0])) ;//为什么是0？
        }catch (IOException | JSONException e){

        }finally {
            return mNewsDetail;
        }
    }
    //复制过来
    @Override
    protected void onPostExecute(NewsDetail mNewsDetail) {
        String headerImage;
        if (mNewsDetail.getImage() == null || mNewsDetail.getImage() == "") {
            headerImage = "file:///android_asset/news_detail_header_image.jpg";//？怎么找到这个文件

        } else {
            headerImage = mNewsDetail.getImage();
        }

        //ava.lang.StringBuilder 类是可变的字符序列。程序对附加字符串的需求很频繁
        StringBuilder sb = new StringBuilder();
        //头部的解析
        sb.append("<div class=\"img-wrap\">")
                .append("<h1 class=\"headline-title\">")
                .append(mNewsDetail.getTitle()).append("</h1>")
                .append("<span class=\"img-source\">")
                .append(mNewsDetail.getImage_source()).append("</span>")
                .append("<img src=\"").append(headerImage)
                .append("\" alt=\"\">")
                .append("<div class=\"img-mask\"></div>");

        //内容的解析
        String mNewsContent = "<link rel=\"stylesheet\" type=\"text/css\" href=\"news_content_style.css\"/>"
                + "<link rel=\"stylesheet\" type=\"text/css\" href=\"news_header_style.css\"/>"
                + mNewsDetail.getBody().replace("<div class=\"img-place-holder\">", sb.toString());
        mWebView.loadDataWithBaseURL("file:///android_asset/", mNewsContent, "text/html", "UTF-8", null);
    }

    //暂时不写先，5.24
//    @Override
//    protected void onPostExecute(NewsDetail newsDetail) {
//        super.onPostExecute(newsDetail);
//    }

}

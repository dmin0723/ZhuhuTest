package com.dengmin.zhuhutest.task;

import android.os.AsyncTask;
import android.webkit.WebView;

import com.dengmin.zhuhutest.R;
import com.dengmin.zhuhutest.entity.NewsDetail;
import com.dengmin.zhuhutest.http.Http;
import com.dengmin.zhuhutest.http.JsonHelper;

import org.json.JSONException;

import java.io.IOException;

/**
 * 与Http对应，解析相应的HTML代码
 */
public class LoadNewsDetailTask extends AsyncTask<Integer,Void,NewsDetail>{ //报错2-2

    private WebView mWebView;

    public LoadNewsDetailTask(WebView mWebView) {
        this.mWebView = mWebView;
    }

    //必须具备的 解析和下载json
    @Override
    protected NewsDetail doInBackground(Integer... params) {
        NewsDetail mNewsDetail = null;
        try{
           mNewsDetail = JsonHelper.parseJsonToDetail(Http.getNewsDetail(params[0])) ;//传入当前的id
        }catch (IOException | JSONException e){

        }finally {
            return mNewsDetail;
        }
    }
    //复制过来 收尾工作
    @Override
    protected void onPostExecute(NewsDetail mNewsDetail) {
        String headerImage;
        if (mNewsDetail.getImage() == null || mNewsDetail.getImage() == "") {  //报错2-1
            //已改变
            headerImage = String.valueOf(R.drawable.fav_active);
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

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
 */
public class LoadNewsDetailTask extends AsyncTask<Integer,Void,NewsDetail>{

    private WebView mWebView;

    public LoadNewsDetailTask(WebView webView) {
        mWebView = webView;
    }

    //必须具备的
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

    //暂时不写先，5.24
    @Override
    protected void onPostExecute(NewsDetail newsDetail) {
        super.onPostExecute(newsDetail);
    }
}

package com.dengmin.zhuhutest.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import com.dengmin.zhuhutest.R;
import com.dengmin.zhuhutest.db.DailyNewsLitePal;
import com.dengmin.zhuhutest.entity.News;
import com.dengmin.zhuhutest.task.LoadNewsDetailTask;
import com.dengmin.zhuhutest.utilzhihu.NetworkUtils;

/**
 * 具体的新闻内容的详情
 */
public class NewsDetailActivity extends Activity{
    private WebView mWebView;
    private boolean isFavorite = false;
    private News news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_detail);

        //设置点击返回图标，可以返回
        getActionBar().setDisplayHomeAsUpEnabled(true);

        mWebView = (WebView) findViewById(R.id.webView);
        setWebView(mWebView);//设置相应的参数

        //使用Intent来传递对象 第一行代码 Serializable
        //与下面的startActivity（）有关
        //得到具体的页面
        news = (News) getIntent().getSerializableExtra("news");

        //加载相应的页面 使用异步加载
        new LoadNewsDetailTask(mWebView).execute(news.getNews_id());//需要对应的修改

        //是否为喜欢的 得到具体的参数，用来是否收藏
        isFavorite = DailyNewsLitePal.getInstance(this).isFavourite(news); //报错3-2
    }

    private void setWebView(WebView mWebView) {
        mWebView.getSettings().setJavaScriptEnabled(true);//支持JavaScript脚本
        mWebView.setVerticalScrollBarEnabled(false);//取消Vertical ScrollBar显示
        mWebView.setHorizontalScrollBarEnabled(false);//取消Horizontal ScrollBar显示
    }

    //点击item 后跳转相应的具体页面，自行了封装了
    public static void startActivity(Context context,News news){
        if(NetworkUtils.checkNetworkConnection(context)){
            Intent i = new Intent(context,NewsDetailActivity.class);
            i.putExtra("news",news);
            context.startActivity(i);
        }else{
            NetworkUtils.notNetworkAlert(context);
        }
    }

    //添加menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main,menu);
        //改变相应的图标
        if(isFavorite){
            menu.findItem(R.id.action_favorite).setIcon(R.drawable.fav_active);
        }
        return true;
    }

    //点击相应的图标
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == R.id.action_about){
            //跳转到AboutActivity
            Intent i = new Intent(this,AboutActivity.class);
            startActivity(i);
            return true;
        }

        if(id == R.id.action_favorite){
            if(!isFavorite){
                DailyNewsLitePal.getInstance(this).saveFavourite(news);
                item.setIcon(R.drawable.fav_active);
                isFavorite = true;
            }else{
                DailyNewsLitePal.getInstance(this).deleteFavourite(news);
                item.setIcon(R.drawable.fav_normal);
                isFavorite = false;
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //解决不了一旦点击就不能取消的问题
//    @Override
//    protected void onDestroy() {
//        if(!isFavorite){
//            DailyNewsLitePal.getInstance(this).deleteFavourite(news);
//        }
//        super.onDestroy();
//    }
}

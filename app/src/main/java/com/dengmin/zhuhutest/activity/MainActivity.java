package com.dengmin.zhuhutest.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dengmin.zhuhutest.R;
import com.dengmin.zhuhutest.adapter.NewsAdapter;
import com.dengmin.zhuhutest.task.LoadNewsTask;
import com.dengmin.zhuhutest.utils.T;
import com.dengmin.zhuhutest.utilzhihu.NetworkUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends Activity implements
        SwipeRefreshLayout.OnRefreshListener,AdapterView.OnItemClickListener{

    private SwipeRefreshLayout refreshLayout;
    private ListView lv;
    private NewsAdapter adapter;
    private boolean isConnected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //判断网络连接
        isConnected = NetworkUtils.checkNetworkConnection(this);

        //refreshLayout
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        refreshLayout.setOnRefreshListener(this);
        //设置刷新时动画的颜色，可以设置4个
        refreshLayout.setColorSchemeColors(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        //列表
        lv = (ListView) findViewById(R.id.lv);

        //设置toolbar的标题，显示当前时间 ok
        setTitle(getTime());

        //添加Adapter 加入单个item
        adapter = new NewsAdapter(this,R.layout.listview_item);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);

        //执行异步操作，加载图片,显示时间
        if(isConnected){
            new LoadNewsTask(adapter).execute();
        }else{
            NetworkUtils.notNetworkAlert(this);
        }
    }

    //添加menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    //点击menu子项目
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == R.id.action_setting){
            return true;//没有设置相应的跳转
        }

        if(id == R.id.action_favorite){
            //跳转到FavoriteActivity
            Intent i = new Intent(this,FavouriteActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //显示当前时间
    public String getTime() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat(getString(R.string.date_format));
        return format.format(c.getTime());
    }

    //implements SwipeRefreshLayout.OnRefreshListener
    //刷新页面
    @Override
    public void onRefresh() {
        if(isConnected){
            //执行异步操作，使用adapter加载图片，显示列表刷新完成
            new LoadNewsTask(adapter, new LoadNewsTask.onFinishListener() {
                @Override
                public void afterTaskFinish() {
                    //刷新成功
                    //这是progress的图标的显示，false为成功就消失了。true为不消失。
                    refreshLayout.setRefreshing(false);
                    T.showShort(MainActivity.this,R.string.refresh);
                }
            }).execute();
        }else{
            NetworkUtils.notNetworkAlert(this);
            refreshLayout.setRefreshing(false);
        }
    }

    //implements AdapterView.OnItemClickListener
    //Adapter View 的item 相应点击
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        NewsDetailActivity.startActivity(this,adapter.getItem(position));
    }
}

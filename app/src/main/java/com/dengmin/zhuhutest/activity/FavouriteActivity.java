package com.dengmin.zhuhutest.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dengmin.zhuhutest.R;
import com.dengmin.zhuhutest.adapter.NewsAdapter;
import com.dengmin.zhuhutest.db.DailyNewsDB;
import com.dengmin.zhuhutest.entity.News;

import java.util.List;

/**
 * Created by dmin on 2016/5/24.
 */
public class FavouriteActivity extends Activity implements AdapterView.OnItemClickListener {

    private NewsAdapter adapter;
    private List<News> favouriteList;
    private ListView lvFavourite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favourite);

        lvFavourite = (ListView) findViewById(R.id.lv_favourite);

        //显示收藏列表
        favouriteList = DailyNewsDB.getInstance(this).loadFavourite();//报错1 这是添加收藏列表

        //添加adapter
        adapter = new NewsAdapter(this,R.layout.listview_item,favouriteList);
        lvFavourite.setAdapter(adapter);
        lvFavourite.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        NewsDetailActivity.startActivity(this,adapter.getItem(position));
    }
}

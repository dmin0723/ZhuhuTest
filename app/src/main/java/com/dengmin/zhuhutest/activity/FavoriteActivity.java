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
public class FavoriteActivity extends Activity implements AdapterView.OnItemClickListener {

    private NewsAdapter adapter;
    private List<News> favoriteList;
    private ListView lvFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite);

        lvFavorite = (ListView) findViewById(R.id.lv_favorite);

        //显示收藏列表
        favoriteList = DailyNewsDB.getInstance(this).loadFavorite();//报错1

        //添加adapter
        adapter = new NewsAdapter(this,R.layout.listview_item,favoriteList);
        lvFavorite.setAdapter(adapter);
        lvFavorite.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        NewsDetailActivity.startActivity(this,adapter.getItem(position));
    }
}

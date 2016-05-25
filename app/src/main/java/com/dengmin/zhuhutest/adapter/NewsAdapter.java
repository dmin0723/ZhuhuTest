package com.dengmin.zhuhutest.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dengmin.zhuhutest.R;
import com.dengmin.zhuhutest.entity.News;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by dmin on 2016/5/23.
 */

    //相应的定义，与选用的适配器有关 为了使用泛型
public class NewsAdapter extends ArrayAdapter<News>{

    private LayoutInflater mInflater;//根据构造函数来定义
    private int resource;//根据构造函数来定义
    private ImageLoader imageLoader = ImageLoader.getInstance();

    //使用DisplayImageOption来加载图片
    private DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.drawable.no_image)
            .showImageOnFail(R.drawable.no_image)
            .showImageForEmptyUri(R.drawable.no_image)
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .considerExifParams(true)// 保留图片文件头信息
            .build();

    //构造函数为必须的 使用在新闻列表中
    public NewsAdapter(Context context, int resource) {
        super(context, resource);
        mInflater = LayoutInflater.from(context);//没有使用this
        this.resource = resource;
    }

    //构造函数为必须的 使用在收藏列表中
    public NewsAdapter(Context context, int resource, List<News> objects) {
        super(context, resource, objects);
        mInflater = LayoutInflater.from(context);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = mInflater.inflate(resource,null);
            holder = new ViewHolder();
            holder.newsTitle = (TextView) convertView.findViewById(R.id.news_title);
            holder.newsImage = (ImageView) convertView.findViewById(R.id.news_image);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        News news = getItem(position);

        holder.newsTitle.setText(news.getTitle());
        Log.d("Image URL", news.getImage());//此处没有显示Image 的 URL
        imageLoader.displayImage(news.getImage(),holder.newsImage,options);

        return convertView;
    }

    class ViewHolder{
        ImageView newsImage;
        TextView newsTitle;
    }

    //刷新列表
    public void refreshNewsList(List<News> newsList){
        clear();
        addAll(newsList);//此处报错
        notifyDataSetChanged();
    }
}

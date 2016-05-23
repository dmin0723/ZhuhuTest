package com.dengmin.zhuhutest.task;

import android.os.AsyncTask;

import com.dengmin.zhuhutest.adapter.NewsAdapter;
import com.dengmin.zhuhutest.entity.News;

import java.util.List;

/**
 * Created by dmin on 2016/5/23.
 */
public class LoadNewsTask extends AsyncTask<Void,Void,List<News>>{

    private NewsAdapter adapter;
    private onFinishListener listener;//接口

    public LoadNewsTask(NewsAdapter adapter){
        super();//调用super
        this.adapter = adapter;
    }

    public LoadNewsTask(NewsAdapter adapter,onFinishListener listener){
        super();//调用super
        this.adapter = adapter;
        this.listener = listener;
    }


    @Override
    protected List<News> doInBackground(Void... params) {
        return null;
    }

    @Override
    protected void onPostExecute(List<News> newses) {
        super.onPostExecute(newses);
    }

    public interface onFinishListener{
        void afterTaskFinish();
    }
}

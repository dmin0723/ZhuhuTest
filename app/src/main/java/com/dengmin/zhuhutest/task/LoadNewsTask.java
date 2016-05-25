package com.dengmin.zhuhutest.task;

import android.os.AsyncTask;

import com.dengmin.zhuhutest.adapter.NewsAdapter;
import com.dengmin.zhuhutest.entity.News;
import com.dengmin.zhuhutest.http.Http;
import com.dengmin.zhuhutest.http.JsonHelper;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * Created by dmin on 2016/5/23.
 * 下载图片是使用了异步操作 ，执行了网络方面的解析
 */
//此处报错
public class LoadNewsTask extends AsyncTask<Void,Void,List<News>>{

    private NewsAdapter adapter;
    private onFinishListener listener;//接口

    //执行异步操作，使用adapter加载图片
    public LoadNewsTask(NewsAdapter adapter){
        super();//调用super
        this.adapter = adapter;
    }

    //执行异步操作，使用adapter加载图片，显示列表刷新完成
    public LoadNewsTask(NewsAdapter adapter,onFinishListener listener){
        super();//调用super
        this.adapter = adapter;
        this.listener = listener;
    }

    //在子线程中运行耗时工作 更新UI的话要调用publishProgress（Progress…）来完成
    //doInBackground 的与开头的泛型一致
    @Override
    protected List<News> doInBackground(Void... params) {
        List<News> newsList = null;

        try{
            newsList = JsonHelper.parseJsonToList(Http.getLastNewsList());
        }catch (IOException | JSONException e){

        }finally {
            return newsList;//这已经是解析好的列表
        }
    }

    //执行项目的收尾工作
    //onPostExecute(List<News> newsList) 的与开头的泛型一致
    @Override
    protected void onPostExecute(List<News> newsList) {
        //  //空也报错 ？此处报错
        adapter.refreshNewsList(newsList);//这个没有写 此处报错
        //有的构造函数不存在接口的使用，故需要加上判断
        if (listener != null){
            listener.afterTaskFinish();
        }
    }

    //接口的定义，目的是提示列表已刷新完成，更改列表的状态
    public interface onFinishListener{
       void afterTaskFinish();
    }
}

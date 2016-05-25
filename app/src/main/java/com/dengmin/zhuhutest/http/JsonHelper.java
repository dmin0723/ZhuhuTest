package com.dengmin.zhuhutest.http;

import com.dengmin.zhuhutest.entity.News;
import com.dengmin.zhuhutest.entity.NewsDetail;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmin on 2016/5/23.
 * 将Http解析成的HTML语句看作成json，使用JsonHelper来解析json，从而显示出来
 * 没有问题
 */
public class JsonHelper {
    //与第一行代码有点不一样，后续继续试验
    //没有使用到Gson这个第三方库，后续进行优化
    public static List<News> parseJsonToList(String json) throws JSONException{
        // json 对象 和 数组
        JSONObject newsContent = new JSONObject(json);
        JSONArray newsArray = newsContent.getJSONArray("stories");

        //列表
        List<News> newsList = new ArrayList<News>();

        for (int i = 0;i < newsArray.length();i++){
            //int id = newsInJson.getInt("id");//第一行代码使用的
            JSONObject newsInJson = newsArray.getJSONObject(i);
            int id = newsInJson.optInt("id");
            String title = newsInJson.optString("title");
            String image = "";
            if(newsInJson.has("images")){ //原来为images
                image = (String)newsInJson.getJSONArray("images").get(0);
            }
            //单个新闻的初始化
            News news = new News(id,title,image);
            newsList.add(news);
        }
        return newsList;
    }

    //解析news detail
    public static NewsDetail parseJsonToDetail(String json) throws JSONException{
        Gson gson = new Gson();
        return gson.fromJson(json,NewsDetail.class);
    }
}

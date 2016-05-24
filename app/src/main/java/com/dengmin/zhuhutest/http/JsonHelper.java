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
 */
public class JsonHelper {

    //与第一行代码有点不一样，后续继续试验
    //没有使用到Gson这个第三方库，后续进行优化
    public static List<News> parseJsonToList(String json) throws JSONException{
        JSONObject newsContent = new JSONObject(json);
        JSONArray newsArray = newsContent.getJSONArray("stories");

        List<News> newsList = new ArrayList<News>();

        for (int i = 0;i < newsArray.length();i++){
            JSONObject newsInJson = newsArray.getJSONObject(i);

            int id = newsInJson.optInt("id");
//            int id = newsInJson.getInt("id");//第一行代码使用的
            String title = newsInJson.optString("title");
            String image = "";
            if(newsInJson.has("image")){
                image = (String)newsInJson.getJSONArray("image").get(0);
            }

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

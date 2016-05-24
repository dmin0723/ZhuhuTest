package com.dengmin.zhuhutest.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by dmin on 2016/5/23.
 * 将存入的网址，解析成HTML格式
 */

    //这是参考了第一行代码的写法，也好比较好的封装类，可以直接使用
public class Http {

    //接口的api
    public static String NEWSLIST_LATEST = "";
    public static String NEWDETAIL = "";

    //进行修改和优化，一般不要直接使用throws 来抛出异常
    // 原来是使用了throws IOException ,没有使用catch
    //与第一行代码有所区别
    public static String get(String address) {
        HttpURLConnection con = null;

        try{
            URL url = new URL(address);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent","Mozilla/5.0");

            if (con.getResponseCode() == HttpURLConnection.HTTP_OK){
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null){
                    response.append(inputLine);
                }

                in.close();
                return  response.toString();
            }else{
                throw new IOException("Network Error - response code: " + con.getResponseCode());
            }

        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }finally {
            if (con != null){
                con.disconnect();
            }
        }
    }

    public static String getLastNewsList()throws IOException{
        return get(NEWSLIST_LATEST);
    }

    public static String getNewsDetail(int id) throws IOException{
        return get(NEWDETAIL + id);
    }
}

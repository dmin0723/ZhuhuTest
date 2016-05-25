package com.dengmin.zhuhutest.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dengmin.zhuhutest.entity.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmin on 2016/5/24.
 * 对数据库进行增删改查
 */
public class DailyNewsDB {
    private DBHelper dbHelper;
    private SQLiteDatabase db;
    private static DailyNewsDB mDailyNewsDB;//使用了静态变量

    private String[] allColumns = {DBHelper.COLUMN_ID,
            DBHelper.COLUMN_NEWS_ID,
            DBHelper.COLUMN_NEWS_TITLE,
            DBHelper.COLUMN_NEWS_IMAGE};

    //private的构造函数，使得不能用new来实例化对象
    private DailyNewsDB(Context context){
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();//统一在这里初始化 //报错1
    }

    //这是单例模式，一般用于比较大，复杂的对象，只初始化一次
    public synchronized static DailyNewsDB getInstance(Context context){
        if(mDailyNewsDB == null){
            mDailyNewsDB = new DailyNewsDB(context);//报错1
        }
        return mDailyNewsDB;
    }

    //添加数据 使用了类表新闻类
    public void saveFavourite(News news){
        if(news != null){
            ContentValues values = new ContentValues();
            values.put(DBHelper.COLUMN_NEWS_ID,news.getId());//键值对
            values.put(DBHelper.COLUMN_NEWS_TITLE,news.getTitle());
            values.put(DBHelper.COLUMN_NEWS_IMAGE,news.getImage());
            db.insert(DBHelper.TABLE_NAME,null,values);
        }
    }

    //删除相应的数据
    public void deleteFavourite(News news){
        if(news != null){
            db.delete(DBHelper.TABLE_NAME,DBHelper.COLUMN_NEWS_ID + "=?"
                    ,new String[]{news.getId() +""});
        }
    }

    //使用了查询数据库 这是添加收藏列表 之前漏了这个
    public List<News> loadFavourite(){
        List<News> favouriteList = new ArrayList<News>();
        Cursor cursor = db.query(DBHelper.TABLE_NAME,null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                News news = new News();
                news.setId(cursor.getInt(1));
                news.setTitle(cursor.getString(2));
                news.setImage(cursor.getString(3));
                favouriteList.add(news);//漏了这个 这是添加收藏列表
            }while(cursor.moveToNext());
        }
        cursor.close();
        return favouriteList;//书写错误
    }

    //是否喜欢，加入相应的数列
    //暂时不理解作用？
    public boolean isFavourite(News news){
        Cursor cursor = db.query(DBHelper.TABLE_NAME,null,
                DBHelper.COLUMN_NEWS_ID + "=?", new String[]{news.getId() + ""},
                null,null,null);
        //光标的位置的最后
        if(cursor.moveToNext()){
            cursor.close();
            return true;
        }else{
            return false;
        }
    }

    //关闭数据库
    public synchronized void closeDB(){
        if(mDailyNewsDB != null){
            db.close();
        }
    }
}

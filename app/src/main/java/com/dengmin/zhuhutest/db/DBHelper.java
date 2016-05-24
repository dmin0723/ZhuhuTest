package com.dengmin.zhuhutest.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dmin on 2016/5/23.
 * 数据库的简单使用
 */
public class DBHelper extends SQLiteOpenHelper{

    public static final String DB_NAME = "daily_news.db"; //数据库的名字
    public static final String TABLE_NAME = "daily_news_fav"; //表名
    public static final int DB_VERSION = 1; //版本

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NEWS_ID = "news_id";
    public static final String COLUMN_NEWS_TITLE = "news_title";
    public static final String COLUMN_NEWS_IMAGE ="news_image";

    //建表语句 报错 因为格式有误
    public static final String DATABASE_CREATE
            = "CREATE TABLE " + TABLE_NAME
            + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT， "
            + COLUMN_NEWS_ID + " INTEGER UNIQUE,"
            + COLUMN_NEWS_TITLE + " TEXT, "
            + COLUMN_NEWS_IMAGE + " TEXT);";

    //必要 直接进行修改，调用super
    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //必要
    //创建数据库
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);//报错1
    }

    //必要
    //更新数据库
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);//清除原来的表格
        onCreate(db);
    }
}

package com.dengmin.zhuhutest.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dengmin.zhuhutest.entity.News;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

/**
 * 使用数据库框架LitePal
 *
 * 问题一：点击收藏的页面没有唯一性，可以多次收藏，只要点击过收藏界面，点击一次收藏一次：已解决
 *
 * 问题二：当前页面点击了收藏，退出后，重新打开时，没有显示已经收藏页面：已解决
 * 问题三：点击收藏的页面，不能直接打开网页，闪屏了几次后，回到main页面：已解决
 * 问题四：按照当前的效果来看，删除功能没有使用到，一旦收藏就取消不了
 *      ：现在可以删除，但只能在收藏页面中点击进去才能删除，在main页面不能解决这个问题，算是解决了
 * 问题五：收藏夹里有时可以点击，但不是对应的页面，就是说更改了页面的id：已解决
 *
 * 解决思路：一、是要进行表单的更新和升级，先删除原来的表格，加入了db.execSQL("drop table if exists news");
 *                出现更多的BUG。后来发现这与表格的升级没有关系，表格并没有发生变化，只是进行了数据的增删而已。
 *           二、是否可以使用一个判断标示来进行数据的唯一性？这样避免了数据的重复收藏：利用三解决了
 *           三、是不是要在News中增加一个new_id，因为当前的getId，与之前的子增加的id相对应。
 *              ：这个有效果，要对应改完全部的数据，之前有一个异步加载数据的，要确保原来的id不与其他的数据绑定
 *
 *           四、解决最好的问题：1、每次点击收藏或者取消收藏，先对应修改删除的id：有效果，还有一点bug
 *
 *最后的BUG：点击取消收藏，在本页面点击完，不能取消收藏，即一旦点击收藏就会有记录；
 *           而进入了收藏列表中，点击进入页面，就能取消收藏。
 *
 */
public class DailyNewsLitePal {
    private SQLiteDatabase db;
    private static DailyNewsLitePal mDailyNewsLitePal;

    private DailyNewsLitePal(Context context){
        //是要进行表单的更新和升级，先删除原来的表格
//        db.execSQL("drop table if exists news");//出现更多的bug 不在这里升级
        //已经创建数据库
        db = Connector.getDatabase();
    }

    //这是单例模式，一般用于比较大，复杂的对象，只初始化一次
    public synchronized static DailyNewsLitePal getInstance(Context context){
        if(mDailyNewsLitePal == null){
            mDailyNewsLitePal = new DailyNewsLitePal(context);//报错1 报错3-1
        }
        return mDailyNewsLitePal;
    }

    //原来是存储数据
//    public void saveFavourite(News news){
//        if(news != null){
//            ContentValues values = new ContentValues();
//            values.put(DBHelper.COLUMN_NEWS_ID,news.getId());//键值对
//            values.put(DBHelper.COLUMN_NEWS_TITLE,news.getTitle());
//            values.put(DBHelper.COLUMN_NEWS_IMAGE,news.getImage());
//            db.insert(DBHelper.TABLE_NAME,null,values);
//        }
//  }

//    user.saveIfNotExist("username = ?", "Tom")
    //存储数据
    //这个功能没有问题
    public void saveFavourite(News inputNews) {
        if (inputNews != null) {
            News news = new News();
            news.setNews_id(inputNews.getNews_id()); //已改
            news.setTitle(inputNews.getTitle());
            news.setImage(inputNews.getImage());
//            news.save();//这个存在收藏的自增长的问题
            //使用news_id出现，不能收藏的情况，使用title也一样：原因是格式问题,符号也有格式问题
            //只是解决了收藏的唯一性，还是不能解决点击收藏后在本页面的取消
            news.saveIfNotExist("news_id = ?", String.valueOf(inputNews.getNews_id()));
        }
    }

    //删除相应的数据
//    public void deleteFavourite(News news){
//        if(news != null){
//            db.delete(DBHelper.TABLE_NAME,DBHelper.COLUMN_NEWS_ID + "=?"
//                    ,new String[]{news.getId() +""});
//        }
//    }

    //删除相应的数据
    //没有体现到，删除对应的数据，不然不会出现重复的数据：已解决了一部分工作
    public void deleteFavourite(News inputNews) {
        if (inputNews != null) {
            //修改为删对应的自增长的id
            //有效果，现在可以删除数据了
            DataSupport.delete(News.class, inputNews.getId());
//            DataSupport.delete(News.class, inputNews.getNews_id());//增加删除部分：没有效果
        }
    }

    //使用了查询数据库 这是添加收藏列表 之前漏了这个
//    public List<News> loadFavourite(){
//        List<News> favouriteList = new ArrayList<News>();
//        Cursor cursor = db.query(DBHelper.TABLE_NAME,null,null,null,null,null,null);
//        if(cursor.moveToFirst()){
//            do{
//                News news = new News();
//                news.setId(cursor.getInt(1));
//                news.setTitle(cursor.getString(2));
//                news.setImage(cursor.getString(3));
//                favouriteList.add(news);//漏了这个 这是添加收藏列表
//            }while(cursor.moveToNext());
//        }
//        cursor.close();
//        return favouriteList;//书写错误
//    }

//    List<News> allNews = DataSupport.findAll(News.class); //查询所有数据

    //显示的收藏列表没有问题
    public List<News> loadFavourite() {
        List<News> favouriteList = DataSupport.findAll(News.class);
        return favouriteList;
    }

    //是否喜欢，加入相应的数列
    //暂时不理解作用？ 在数据库中判断此页面是否为收藏的
//    public boolean isFavourite(News news){
//        Cursor cursor = db.query(DBHelper.TABLE_NAME,null,
//                DBHelper.COLUMN_NEWS_ID + "=?", new String[]{news.getId() + ""},
//                null,null,null);
//        //光标的位置的最后
//        if(cursor.moveToNext()){
//            cursor.close();
//            return true;
//        }else{
//            return false;
//        }
//    }

    //List<News> newsList = DataSupport.where("commentcount > ?", "0").find(News.class);
    //没有作用相应的作用
    public boolean isFavourite(News inputNews) {
//        List<News> newsList = DataSupport.where("id=?", "inputNews.getId()").find(News.class);
        //改成了getNews_id,报错了，显示找不到news_id这个列 原因是没有更改版本
        Cursor cursor = DataSupport.findBySQL("select * from news where news_id=?", String.valueOf(inputNews.getNews_id()));

        if (cursor.moveToNext()) {
            cursor.close();
            return true;
        } else {
            return false;
        }
    }
}
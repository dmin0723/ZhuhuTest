package com.dengmin.zhuhutest.utilzhihu;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.dengmin.zhuhutest.R;
import com.dengmin.zhuhutest.utils.T;

/**
 * Created by dmin on 2016/5/23.
 */
public class NetworkUtils {

    //已经有专门的封装好的工具类，来进行代替
    public static boolean checkNetworkConnection(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activityNetwork = cm.getActiveNetworkInfo();
        return activityNetwork != null && activityNetwork.isConnected();
    }


    public static void notNetworkAlert(Context context){
        T.showShort(context, R.string.not_network);
    }
}

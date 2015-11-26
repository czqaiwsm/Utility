package com.utility;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * @desc 获取屏幕高度，宽度
 * @creator caozhiqing
 * @data 2015/11/26
 */
public class ScreenUitlity {


    /**
     * 获取屏幕宽、高
     * @param context
     * @return
     */
    public static int[] getScreenSize(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager mWm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mWm.getDefaultDisplay().getMetrics(dm);
//        dm.density;
        int size[] = {dm.widthPixels,dm.heightPixels,};
        return size;
    }



}

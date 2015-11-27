package com.utility;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.TextView;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:需要用到Context 上下文的工具方法；如：获取meta_data、屏幕宽度等；
 * </p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p/>
 * @author 曹智青
 * @version Revision: 1.0 Date: 2015/1/2
 */
public class ContextUtils {

    /**
     *获取在Activity中注册的meta_data注册值
     * @param key
     * @return
     */
    public static String getActivityMetaValues(Context context,String key){
        ActivityInfo info= null;
        String msg = "";
        if(context instanceof Activity){
            try {
                info = context.getPackageManager()
                        .getActivityInfo(((Activity)context).getComponentName(),
                                PackageManager.GET_META_DATA);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            msg =info.metaData.getString(key);
        }

        return  msg;
    }

    /**
     *获取在Application中注册的meta_data注册值
     * @param key
     * @return
     */
    public static String getAppMetaValues(Context context,String key){
        String msg = "";
        ApplicationInfo appInfo = getApplicationInfo(context);
        if(appInfo.metaData.containsKey(key)){
            msg = appInfo.metaData.getString(key);
        }
        return  msg;
    }

    public static Integer getAppMetaIntValues(Context context,String key){
        Integer integer = 0;
        ApplicationInfo appInfo = getApplicationInfo(context);
        if(appInfo.metaData.containsKey(key)){
            integer = appInfo.metaData.getInt(key);
        }
        return integer;
    }

    private static  ApplicationInfo getApplicationInfo(Context context){
        ApplicationInfo appInfo = null;
        try {
            appInfo = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return  appInfo;
    }



    /**
     * 获取正在运行的进程
     * @return
     */
    private static List<ActivityManager.RunningAppProcessInfo> getAllRunningPr(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

        List<ActivityManager.RunningAppProcessInfo> rais = am.getRunningAppProcesses();
        return  rais;
    }

    public static ArrayList<String> getAllRunningPN(Context context){
        List<ActivityManager.RunningAppProcessInfo> rais = getAllRunningPr(context);
        ArrayList<String> pnNames = new ArrayList<String>();
        for (ActivityManager.RunningAppProcessInfo rapi : rais) {
            String pN = rapi.processName;
           pnNames.add(pN);
        }
        return  pnNames;
    }

    /* *
     * 获取软件版本号
     */
    public static String getVersionCode(Context context){
        String versionCode ="0";
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo;
        try {
            PackageManager packageManager = context.getPackageManager();
            packInfo = packageManager.getPackageInfo(context.getPackageName(),0);
            versionCode =  packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 把对象保存到以String形式保存到sp中
     * @param context 上下文
     * @param t  泛型参数
     * @param spName  sp文件名
     * @param keyName  字段名
     * @param <T>
     */
    public static <T> void saveObj2SP(Context context, T t, String spName, String keyName) {

        SharedPreferences preferences = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        ByteArrayOutputStream bos;
        ObjectOutputStream oos = null;
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(t);
            byte[] bytes = bos.toByteArray();
//           String ObjStr = new String(bytes,0,bytes.length);
            String ObjStr = Base64.encodeToString(bytes, Base64.DEFAULT);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(keyName, ObjStr);
            editor.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭流
            if (oos != null) {
                try {
                    oos.flush();
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }

    }

    /**
     * sp文件名 和 字段名相同
     * @param context
     * @param t
     * @param spName
     * @param <T>
     */
    public static <T> void saveObj2SP(Context context, T t, String spName) {
        saveObj2SP(context, t, spName, spName);
    }


    /**
     *从sp中读取对象
     * @param context
     * @param spName sp文件名
     * @param keyNme 字段名
     * @param <T>    泛型参数
     * @return
     */
    public static <T extends Object> T getObjFromSp(Context context, String spName, String keyNme) {
        SharedPreferences preferences = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        byte[] bytes = Base64.decode(preferences.getString(keyNme, ""), Base64.DEFAULT);
        ByteArrayInputStream bis;
        ObjectInputStream ois = null;
        T obj = null;
        try {
            bis = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bis);
            obj = (T) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return obj;
    }

    public static <T extends Object> T getObjFromSp(Context context, String spName){
        return  getObjFromSp(context,spName,spName);
    }
}




package com.utility.waiterLayer;

import android.content.Context;

/**
 * @desc 加载等待层集中调用
 * @creator caozhiqing
 * @data 2015/11/26
 */
public class WaitUtility {


   /*start**********************UILoadingDialog  数据加载等待层*************************************/
    public static void showBomb(Context context){
        UILoadingDialog.show(context);
    }
    public static void dismissBomb(){
        UILoadingDialog.dismmis();
    }
    /*end*********************UILoadingDialog  数据加载等待层*************************************/

   /*start**********************WaitLayer  数据加载等待层*************************************/
    private static WaitLayer waitLayer;
    public static void showWaitLayer(Context context){
        waitLayer = WaitLayer.getWaitLayer(context);
        waitLayer.show();
    }
    public static void dismissWaitLayer(Context context){
       if (waitLayer != null){
           waitLayer.close();
           waitLayer = null;
       }
    }
    /*end*********************WaitLayer  数据加载等待层*************************************/

}

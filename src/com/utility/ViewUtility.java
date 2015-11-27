package com.utility;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * @desc 视图控件
 * @creator caozhiqing
 * @data 2015/11/26
 */
public class ViewUtility {

    /**
     * 重新绘制View 的高度
     * @param calculatedview
     * @param resizedView
     * @param scale
     */
    public static void resizeViewHeight(View calculatedview, View resizedView,float scale) {
        resizedView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                int width = calculatedview.getWidth();
                int height = (int) (width * scale);
                ViewGroup.LayoutParams   params = resizedView.getLayoutParams();
                params.height = height;
                resizedView.setLayoutParams(params);
                return true;
            }
        });
    }


    /**
     * 重新计算高度 RelativeLayout除外？(ListView 的Item必须为根布局必须不能是RelativeLayout).
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView){
        if(listView == null) return;
        ListAdapter listAdapter = listView.getAdapter();
        if(listAdapter == null) return;
        int totalHeight = 0;
        for(int i=0;i<listAdapter.getCount();i++){
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight+(listView.getDividerHeight()*(listAdapter.getCount()-1));
        listView.setLayoutParams(params);
    }
}

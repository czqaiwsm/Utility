package com.utility;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author 曹智青
 * @version Revision 1.0
 * @description 图片的基本操作
 * @data 2015/5/27.
 * @CopyRith:
 */
public class ImagUtil {




    /** 保存方法 */
    public static String saveBitmap(Bitmap bmp,String savePath,boolean isDelete) {

        File f = FileUtility.createFile(savePath);
        if (f.exists() && isDelete) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
            return f.getCanonicalPath();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }

    public static String saveBitmap(Resources resources,int id,String savePath,Boolean isDelete){
        try {
            Bitmap bitmap = BitmapFactory.decodeResource(resources,id);
            ImagUtil.saveBitmap(bitmap,savePath,isDelete);
        }catch (OutOfMemoryError error){
            error.printStackTrace();
        }

        return  savePath;
    }


    public static void  recycleBitmap(Bitmap bitmap){
        if(bitmap != null && !bitmap.isRecycled()){
            // 回收并且置为null
//            bitmap.recycle();
            bitmap = null;
            }
        System.gc();
    }


    /**
     * 清空不可见图片的内存图片的内存
     */
    public static void clearImgMemory(ImageView view,boolean isGC)
    {
        if(view!=null)
        {
                if(view.getVisibility()==View.GONE && view instanceof ImageView)
                {
                    Drawable d=((ImageView)view).getDrawable();
                    if(d!=null&&d instanceof BitmapDrawable)
                    {
                        Bitmap bmp=((BitmapDrawable)d).getBitmap();
                        if(bmp != null){
                            bmp.recycle();
                            bmp=null;
                        }

                    }
                    view.setImageBitmap(null);
                    if (d != null){
                        d.setCallback(null);
                    }
                }
            if(isGC){
                System.gc();
            }
        }
    }

}

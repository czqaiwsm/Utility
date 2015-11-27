package com.utility;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;

import java.io.File;

/**
 * @desc SDcard 操作的工具类
 * @creator caozhiqing
 * @data 2015/11/26
 */
public class SDCardUtility {

    /**
     * 当前设备是否sdcard
     * @return
     */
    public static boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * 获取SDcard基本路径
     * @param context
     * @return
     */
    public static String getSDPath(Context context) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) ) {
            File sdcardDir = Environment.getExternalStorageDirectory();
            String path = sdcardDir.getPath();
            return path;
        } else {
            return "";
        }
    }
    private static boolean hasExternalStoragePermission(Context context) {
        int perm = context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE");
        return perm == 0;
    }

    /**
     * 获取SDCard空闲大小
     */
    public static long getSdCardAvailableSize() {
        File sdcardDir = android.os.Environment.getExternalStorageDirectory();
        StatFs sf = null;
        try {
            sf = new StatFs(sdcardDir.getPath());
        } catch (Exception e) {
            return 0;
        }
        long blockSize = sf.getBlockSize();
        long availCount = sf.getAvailableBlocks();
        return availCount * blockSize;
    }

}

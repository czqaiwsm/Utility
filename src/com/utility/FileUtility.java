package com.utility;

import java.io.File;

/**
 * @desc 文件操作工具类
 * @creator caozhiqing
 * @data 2015/11/26
 */
public class FileUtility {

    /**
     * 创建目录
     * @param path
     */
    public static File createDir(String path) {
        File file = new File(path);
        if (!file.exists()) { // 不存在
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) { // 父目录不存在
                createDir(parentFile.getAbsolutePath());
            }
            file.mkdir();
        }
        return file;
    }

    /**
     * 创建文件
     * @param path
     */
    public static File createFile(String path) {
        File file = null;
        try {
            file = new File(path);
            if (!file.exists()) { // 不存在
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) { // 父目录不存在
                    createDir(parentFile.getAbsolutePath());
                }
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }



    /**
     * 删除文件和目录
     * @param file
     * @return
     */
    public static boolean deleteFile(File file) {

        if (!file.exists()) {// 文件不存在
            return false;
        }

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                File temp = files[i];
                boolean result = deleteFile(temp);
                if (!result)
                    return false;
            }
        }
        return file.delete();
    }

    /**
     * delete file 删除文件
     * <ul>
     * <li>if path is null or empty, return true</li>
     * <li>if path not exist, return true</li>
     * <li>if path exist, delete recursion. return true</li>
     * <ul>
     *
     * @param path
     * @return
     */
    public static boolean deleteFile(String path) {
        if (path == null || path.length()==0) {
            return true;
        }

        File file = new File(path);
        if (!file.exists()) {
            return true;
        }
        if (file.isFile()) {
            return file.delete();
        }
        if (file.isDirectory()) {
            return false;
        }
        return file.delete();
    }

    /**
     * 获取文件格式
     * @param filepath
     * @return
     */
    public static String getFormat(String filepath) {
        int index = filepath.lastIndexOf(".");
        if (index != -1) {
            return filepath.substring(index);
        }
        return null;
    }

}

package com.utility;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by caozhiqing on 2015/7/9.
 * 取小数点。
 */
public class DecimalUtil {

    /**
     * 四舍五入保留有效数字
     * @param ft
     * @param scale 需要保留的位数
     * @return
     */
    public static float getFloat(float ft,int scale){
        BigDecimal bd  =   new  BigDecimal((double)ft);
        bd   =  bd.setScale(scale,BigDecimal.ROUND_HALF_UP);
        ft   =  bd.floatValue();
        return ft;
    }

    public static float getFloat(String value,int scale){
        float ft = 0f;
        if(value != null && value.trim().length()>0){
            ft = Float.valueOf(value);
            ft = getFloat(ft,scale);
        }
        return ft;
    }

    /**
     * 四舍五入保留有效数字
     * @param ft
     * @param scale 需要保留的位数
     * @return
     */
    public static double getDouble(double ft,int scale){
//        int   roundingMode  =  4;//???????????????????????????????????��?????.
        BigDecimal bd  =   new  BigDecimal((double)ft);
        bd   =  bd.setScale(scale,BigDecimal.ROUND_HALF_UP);
        ft   =  bd.doubleValue();
        return ft;
    }

    public static double getDouble(String value,int scale){
        double ft = 0f;
        if(value != null && value.trim().length()>0){
            ft = Double.valueOf(value);
            ft = getDouble(ft, scale);
        }
        return ft;
    }

    /**
     * 保留小数点数
     * @param vl
     * @param ft = "0.00"
     * @return
     */
    public static String getDecimal(String vl,String ft){
        if(vl == null || vl.trim().length()==0){
            return "";
        }
        DecimalFormat df = new DecimalFormat(ft);
        Double dl = Double.valueOf(vl);
        return  df.format(dl);
    }

    public static void main(String[] arg){
        float f = 0.9f;
        System.out.println(DecimalUtil.getDecimal(f+"","0.0"));
        f=10.98f;
        System.out.println(DecimalUtil.getDecimal(f+"","0.0"));

    }

}

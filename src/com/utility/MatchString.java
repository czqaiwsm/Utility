package com.utility;

import android.widget.EditText;

import java.util.regex.Pattern;

/**
 * @author 曹智青
 * @version Revision: 1.0 Date: 2015/4/22
 * @Description:正则表达式匹配string
 * @Copyright: 2015
 * @Company:
 */
public class MatchString {


    public static boolean matchMobilePhoneNumber(String phoneNumber){
        String regexStr = "^((13[0-9])|(15[^4,\\D])|(18[0-9])|(17[0-9]|(14[0-9])))\\d{8}$";
        Pattern pattern = Pattern.compile(regexStr);
        return pattern.matcher(phoneNumber).find();
    }

    public static boolean matchMobilePhoneNumber(EditText editText){
        String str = "";
        if(editText!=null){
            str = editText.getText().toString();
        }
        return matchMobilePhoneNumber(str);
    }


}

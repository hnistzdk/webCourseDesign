package com.zdk.utils;

/**
 * @author zdk
 * @date 2021/12/23 17:37
 */
public class JspUtil {
    public static String convertBalance(Integer money){
        return (money/100)+"."+(money/10%10)+(money%10);
    }
}

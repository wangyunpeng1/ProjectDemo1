package com.wyp.utils;

import java.util.Map;

/**
 * @author: wyp
 * @date: 2020/2/11
 * @description: 常用工具类
 */
public class CommonUtil {
    /**
     * 随机数
     * @return
     */
    public static Long randomNumber(){
        double number = 89999999*Math.random()+10000000;
        Long result = new Double(number).longValue();
        return result;
    }
}

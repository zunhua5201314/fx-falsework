package com.epri.fx.server.util;

/**
 *
 * @Description:
 *
 * @param:
 * @return:
 * @auther: liwen
 * @date: 2020/8/2 10:56 上午
 */
public class StringHelper {
    public static String getObjectValue(Object obj){
        return obj==null?"":obj.toString();
    }
}

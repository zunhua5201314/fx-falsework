package com.epri.fx.client.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 汉字转换成拼音方法
 *
 * @author 蔡龙
 */

public class Pinyin4jUtil {

    static HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();

    public static enum Type {
        UPPERCASE,              //全部大写
        LOWERCASE,              //全部小写
        FIRSTUPPER            //首字母大写
    }

    static {

        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
    }

    /**
     * 转换全部大写
     *
     * @param str 字符串
     * @return str为颐和园 ,return获取到的是YHY
     */
    public static String toPinYinUppercase(String str) {
        return toPinYin(str, "", Type.UPPERCASE);
    }

    /**
     * 转换全部大写
     *
     * @param str   字符串
     * @param spera 转换字母间隔加的字符串,如果不需要为""
     * @return str为颐和园 ,spera为** return获取到的是Y**H**Y
     */
    public static String toPinYinUppercase(String str, String spera) {
        return toPinYin(str, spera, Type.UPPERCASE);
    }

    /**
     * 转换全部小写
     *
     * @param str 字符串
     * @return str为颐和园 ,return获取到的是yhy
     * @
     */
    public static String toPinYinLowercase(String str) {
        return toPinYin(str, "", Type.LOWERCASE);
    }

    /**
     * 转换全部小写
     *
     * @param str   字符串
     * @param spera 转换字母间隔加的字符串,如果不需要为""
     * @return str为颐和园 ,spera为** return获取到的是y**h**y
     * @
     */
    public static String toPinYinLowercase(String str, String spera) {
        return toPinYin(str, spera, Type.LOWERCASE);
    }

    /**
     * 获取拼音首字母(大写)
     *
     * @param str 字符串
     * @return str为颐和园 ,return获取到的是Y
     * @ 异常信息
     */
    public static String toPinYinUppercaseInitials(String str) {
        String initials = null;
        String py = toPinYinUppercase(str);
        if (py.length() > 1) {
            initials = py.substring(0, 1);
        }
        if (py.length() <= 1) {
            initials = py;
        }
        return initials.trim();
    }

    /**
     * 获取拼音首字母(小写)
     *
     * @param str 字符串
     * @return str为颐和园 ,return获取到的是y
     * @ 异常信息
     */
    public static String toPinYinLowercaseInitials(String str) {
        String initials = null;
        String py = toPinYinLowercase(str);
        if (py.length() > 1) {
            initials = py.substring(0, 1);
        }
        if (py.length() <= 1) {
            initials = py;
        }
        return initials.trim();
    }

    /**
     * 将str转换成拼音，如果不是汉字或者没有对应的拼音，则不作转换
     *
     * @param str   字符串
     * @param spera 默认,可为""
     * @param type  转换格式
     * @return 按照转换格式转换成字符串
     */
    public static String toPinYin(String str, String spera, Type type) {
        if (str == null || str.trim().length() == 0) {
            return "";
        }
        if (type == Type.UPPERCASE) {
            format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        } else {
            format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        }
        String py = "";
        String temp = "";
        String[] t = null;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if ((int) c <= 128) {
                py += c;
            } else {
                try {
                    t = PinyinHelper.toHanyuPinyinStringArray(c, format);
                } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                    badHanyuPinyinOutputFormatCombination.printStackTrace();
                }
                if (t == null) {
                    py += c;
                } else {
                    temp = t[0];
                    if (type == Type.FIRSTUPPER) {
                        temp = t[0].toUpperCase().charAt(0) + temp.substring(1);
                    }
                    if (temp.length() >= 1) {
                        temp = temp.substring(0, 1);
                    }
                    py += temp + (i == str.length() - 1 ? "" : spera);
                }
            }
        }
        return py.trim();
    }
}



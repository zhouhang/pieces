package com.pieces.tools.utils;

import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;

/**
 * 拼音工具
 * Created by wangbin on 2016/8/4.
 */
public class PinyinUtil {


    public static String field2Pinyin(String field) {
        String pinyin = PinyinHelper.convertToPinyinString(field, "", PinyinFormat.WITHOUT_TONE);
        return pinyin;
    }



}

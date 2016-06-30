package com.pieces.tools.utils;

import java.util.Random;

/**
 * 序列数工具类
 * Created by wangbin on 2016/6/30.
 */
public class SeqNoUtil {

    private SeqNoUtil(){};

    /**
     * 生成指定位数的随机数
     * @param length
     * @return
     */
    public static String getRandomNum(int length){
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<length;i++){
            sb.append( random.nextInt(10));
        }
        return sb.toString();
    }



}

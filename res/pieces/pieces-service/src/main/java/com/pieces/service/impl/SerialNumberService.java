package com.pieces.service.impl;

import com.pieces.service.redis.RedisManager;
import com.pieces.tools.utils.SeqNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangbin on 2016/9/8.
 */
@Service
public class SerialNumberService {


    @Autowired
    private RedisManager redisManager;


    public  String generateOrderCode(){
        String randomNum = null;
        synchronized (this){
            randomNum =  SeqNoUtil.getRandomNum(2);
            if(redisManager.exists(randomNum)){
                return generateOrderCode();
            }
        }
        redisManager.set(randomNum,"null",1);
        return getTensTimestamp()+randomNum;
    }


    /**
     * 获取十位的时间戳
     * @return
     */
    public long getTensTimestamp(){
        long  timestamp = System.currentTimeMillis();
        return timestamp/1000;
    }




}

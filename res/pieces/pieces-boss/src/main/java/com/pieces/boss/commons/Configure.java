package com.pieces.boss.commons;

import com.pieces.tools.utils.Configuration;

/**
 * Created by wangbin on 2016/8/5.
 */
public class Configure {

    private static final String CONF_FILE= "com/pieces/boss/properties/system.properties";

    public static String getUploadUrl(){
        Configuration configuration = new com.pieces.tools.utils.Configuration(CONF_FILE);
        return configuration.getString("boss.upload.url");
    }
}

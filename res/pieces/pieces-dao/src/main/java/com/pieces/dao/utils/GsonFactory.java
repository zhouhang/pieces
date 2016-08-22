package com.pieces.dao.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Author: koabs
 * 8/22/16.
 */
public class GsonFactory {
    public static Gson getInstance() {
        GsonBuilder builder = new GsonBuilder();
        builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
//        builder.registerTypeAdapter(NucleonEventType.class, new NucleonEventTypeSerializer());
        Gson gson = builder.create();
        return gson;
    }
}

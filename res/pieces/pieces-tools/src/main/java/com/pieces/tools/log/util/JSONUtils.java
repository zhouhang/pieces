package com.pieces.tools.log.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by kevin1 on 7/11/16.
 */
public class JSONUtils {

    private static Logger logger = LoggerFactory.getLogger(JSONUtils.class);

    public static String toJson(Object object) {
        String returnValue = null;
        Gson gson = new GsonBuilder().create();

        if(object != null) {
            try {
                returnValue = gson.toJson(object);
            } catch (Exception e){
                logger.warn("JSONException occurs for JSONObject.toJSONString(). So use instance of that simple class name instead. object\'s class=" + object.getClass());
            }
        }

        return returnValue;
    }
}

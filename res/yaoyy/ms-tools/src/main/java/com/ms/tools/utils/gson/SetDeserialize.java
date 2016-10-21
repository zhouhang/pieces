package com.ms.tools.utils.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wangbin on 2016/7/22.
 */
public class SetDeserialize implements JsonDeserializer<Set<Object>> {

    public Set<Object> deserialize(JsonElement json, Type type,
                                   JsonDeserializationContext context) throws JsonParseException {
        //得到数组里面的所有值
        Iterator<JsonElement> iterator= json.getAsJsonArray().iterator();
        Set<Object> set = new HashSet<>();
        while (iterator.hasNext()) {
            JsonElement element =  iterator.next();
            if(isInteger(element.getAsString())){
                set.add(element.getAsInt());
                continue;
            }
            set.add(element);
        }

        return set;
    }
    /**
     * 判断是不是int类型的数字
     * @param str
     * @return
     *  是int类型返回true
     */
    public  boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
}

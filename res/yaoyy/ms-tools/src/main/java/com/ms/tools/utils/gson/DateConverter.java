package com.ms.tools.utils.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by miles on 13-12-10.
 * 时间转换器，默认只支持yyyy-MM-dd、yyyy-MM-dd HH:mm:ss类型的数据
 * 将json字符串中的时间字符串转为时间，将时间按照指定格式(此格式可以在构建该转换器时指定）转为字符串
 */
public class DateConverter extends TypeAdapter<Date> {
    private String pattern = "yyyy-MM-dd HH:mm:ss";

    public DateConverter() {

    }

    public DateConverter(String pattern) {
        if (pattern != null) {
            this.pattern = pattern;
        }
    }

    @Override
    //将date转为字符串
    public void write(JsonWriter out, Date value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        String dateFormatAsString = format.format(value);
        out.value(dateFormatAsString);
    }

    @Override
    /**
     * 将字符串转为date
     * 默认使用yyy-MM-dd HH:mm:ss的正则规则
     */
    public Date read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        }
        String json = in.nextString();
        if (json.matches("\\d{4}-\\d{1,2}-\\d{1,2}")) {
            pattern = "yyyy-MM-dd";
        }
        Date date = null;
        try {
            date = new SimpleDateFormat(pattern).parse(json);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
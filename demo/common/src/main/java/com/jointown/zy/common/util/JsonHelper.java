package com.jointown.zy.common.util;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;

public class JsonHelper {

	/**
	 * 将对象转换成json串 为空的属性转化成null
	 * @param obj
	 * @return
	 */
	public static String objToJson(Object obj){
		Gson gson = new Gson();
		Writer writer = new StringWriter();
		JsonWriter out = new JsonWriter(writer);
		out.setSerializeNulls(true);
		TypeAdapter< ? > adapter = gson.getAdapter(TypeToken.get(Object.class));
		//WmsBreedDto a = new WmsBreedDto();
		try {
		((TypeAdapter) adapter).write(out, obj);
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		return writer.toString();
	}
}

package com.jointown.zy.common.util;

import java.lang.reflect.Type;
import java.util.Date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class DateSerializerUtil implements JsonSerializer<Date>, JsonDeserializer<Date> {

	@Override
	public JsonElement serialize(Date date, Type type, JsonSerializationContext context) {
		return new JsonPrimitive(date.getTime());
	}

	@Override
	public Date deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
		if (element == null || element.getAsJsonPrimitive() == null || element.getAsJsonPrimitive().equals("null")) {
			return null;
		}
		return new Date(element.getAsJsonPrimitive().getAsLong());
	}

}

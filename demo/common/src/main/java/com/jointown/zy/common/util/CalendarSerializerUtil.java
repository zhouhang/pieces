package com.jointown.zy.common.util;

import java.lang.reflect.Type;
import java.util.Calendar;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class CalendarSerializerUtil implements JsonSerializer<Calendar>, JsonDeserializer<Calendar> {

	@Override
	public JsonElement serialize(Calendar cal, Type type, JsonSerializationContext context) {
		return new JsonPrimitive(Long.valueOf(cal.getTimeInMillis()));
	}

	@Override
	public Calendar deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
		if (element == null || element.getAsJsonPrimitive() == null || element.getAsJsonPrimitive().equals("null")) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(element.getAsJsonPrimitive().getAsLong());
		return cal;
	}

}
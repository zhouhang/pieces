package com.jointown.zy.common.processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ExampleEntry extends BaseProcessor {

	@Override
	protected List<Object> doProcess(List<Object> objects) {
		System.out.println("#################process 1#################");
		System.out.println(objects.get(0).toString());
		System.out.println(getContext().get("one"));
		List<Object> array = new ArrayList<Object>();
		array.add(objects.get(0).toString()+"\n"+getContext().get("one").toString());
		return array;
	}

	@Override
	protected boolean support(List<Object> objects ) {
		return ((String)getValue()).equals("ok");
	}
	
	public static void main(String[] args) {
		Map map = new HashMap();
		map.put("one", "one's value");
		map.put("two", "two's value");
		BaseProcessor processor = new ExampleEntry().setValue("ok").setContext(map)
		.setNext(new ExampleProcessTwo().setValue(true).setContext(map));
		List params = new ArrayList();
		params.add("init");
		List<Object> list = processor.process(params);
		System.out.println("#################Last Return is:#################");
		for(Object o:list){
			System.out.println(o);
		}
	}

}

package com.jointown.zy.common.processor;

import java.util.List;

public class ExampleProcessTwo extends BaseProcessor {

	@Override
	protected List<Object> doProcess(List<Object> objects) {
		System.out.println("#################process 2#################");
		System.out.println(objects.get(0).toString());
		System.out.println(getContext().get("two"));
		objects.add(objects.get(0).toString()+getContext().get("two").toString());
		return objects;
	}

	@Override
	protected boolean support(List<Object> objects ) {
		return (Boolean)getValue();
	}

}

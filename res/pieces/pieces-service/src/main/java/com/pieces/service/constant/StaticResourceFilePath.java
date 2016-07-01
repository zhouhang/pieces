package com.pieces.service.constant;

import com.pieces.service.utils.SpringUtil;

public enum StaticResourceFilePath {
	
	BIZ_RESOURCE_JS("src/webapp/assets/js"),
	BIZ_RESOURCE_CSS("src/webapp/assets/css"),
	BIZ_RESOURCE_IMAGES("src/webapp/assets/images"),
	BOSS_RESOURCE_IMAGES("src/webapp/assets/images"),
	BOSS_RESOURCE_CSS("src/webapp/assets/css"),
	BOSS_RESOURCE_JS("src/webapp/assets/js");

	private String messageKey;
	private String value;
	
	private StaticResourceFilePath(String messageKey) {
		this.messageKey = messageKey;
	}

	public String getMessageKey() {
		return messageKey;
	}
	

	public StaticResourceFilePath setMessageKey(String messageKey) {
		this.messageKey = messageKey;
		return this;
	}

	public String getValue() {
		return value==null?value=SpringUtil.getConfigProperties().getProperty(getMessageKey()):value;
	}
	
	public Integer intValue() {
		return Integer.parseInt(getValue());
	}

	public StaticResourceFilePath setValue(String value) {
		this.value = value;
		return this;
	}
}

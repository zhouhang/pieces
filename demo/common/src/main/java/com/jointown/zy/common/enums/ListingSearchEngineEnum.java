package com.jointown.zy.common.enums;

public enum ListingSearchEngineEnum {
	
	SEARCH_MODE_WAREHOUSE("WAREHOUSE"),
	SEARCH_MODE_LINK("LINK"),
	
	SEARCH_RESULT_TYPE_BREED("BREED"),
	SEARCH_RESULT_TYPE_CATEGORY("CATEGORY"),
	SEARCH_RESULT_TYPE_KEYWORDS("KEYWORDS"),
	SEARCH_RESULT_TYPE_SUGGEST("SUGGEST");
	
	private ListingSearchEngineEnum(String value) {
		this.value = value;
	}
	
	private String value;
	
	public String getValue(){
		return this.value;
	}
	
}

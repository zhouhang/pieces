/*
 * CustomTransferSymbolEditor.java
 * Copyright (c) 2011,融众网络技术有限公司(www.11186.com)
 * All rights reserved.
 * ---------------------------------------------------------------------
 * 2011-5-6 Created
 */
package com.jointown.zy.common.propertyeditor;

import java.beans.PropertyEditorSupport;

import com.jointown.zy.common.util.HtmlSymbolsUtil;

/**
 * 对输入字符进行转换处理
 * @ClassName:CustomTransferSymbolEditor
 * @author:Calvin.Wangh
 * @date:2015-7-24上午10:39:55
 * @version V1.0
 * @Description:
 */
public class StringTransferSymbolEditor extends PropertyEditorSupport {

	public void setAsText(String text) {
		setValue(HtmlSymbolsUtil.htmlSymbols(text)); 
	}
	
}

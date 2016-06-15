/**
 * Copyright © 2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.logback;

import java.util.Properties;

import org.xml.sax.Attributes;

import ch.qos.logback.core.joran.action.ActionUtil;
import ch.qos.logback.core.joran.action.PropertyAction;
import ch.qos.logback.core.joran.action.ActionUtil.Scope;
import ch.qos.logback.core.joran.spi.InterpretationContext;
import ch.qos.logback.core.util.OptionHelper;

import com.jointown.zy.common.util.SpringUtil;

/**
 * @ClassName: JointownPropertyAction
 * @Description: 扩展logback关于properties属性的处理类
 * @Author: robin.liu
 * @Date: 2015年9月1日
 * @Version: 1.0
 */
class JointownPropertyAction extends PropertyAction{
	  static final String RESOURCE_ATTRIBUTE = "resource";
	  static final String USE_EXISTED_ATTRIBUTE = "useExisted";
	  /**
	   * Set a new property for the execution context by name, value pair, or adds
	   * all the properties found in the given file
	   * or adds all the properties found from existed properties which was loaded by spring framework.
	   * 
	   */
	  public void begin(InterpretationContext ec, String localName,
	      Attributes attributes) {
	    super.begin(ec, localName, attributes);
	    String scopeStr = attributes.getValue(SCOPE_ATTRIBUTE);
	    Scope scope = ActionUtil.stringToScope(scopeStr);
	    if(checkUseExistedAttributeSanity(attributes)){
	    	  String propertiesFlag = attributes.getValue(USE_EXISTED_ATTRIBUTE);
		      boolean isPropertiesFromSpring = "true".equalsIgnoreCase(propertiesFlag)?true:false;
		      if(isPropertiesFromSpring){
		    	Properties props = SpringUtil.getConfigProperties();
		  	    ActionUtil.setProperties(ec, props, scope);
		      }
	    }
	  }

	  boolean checkUseExistedAttributeSanity(Attributes attributes) {
	    String file = attributes.getValue(FILE_ATTRIBUTE);
	    String name = attributes.getValue(NAME_ATTRIBUTE);
	    String value = attributes.getValue(VALUE_ATTRIBUTE);
	    String resource = attributes.getValue(RESOURCE_ATTRIBUTE);
	    String useExisted = attributes.getValue(USE_EXISTED_ATTRIBUTE);

	    return !(OptionHelper.isEmpty(useExisted))
	        && (OptionHelper.isEmpty(name) && OptionHelper.isEmpty(value) && OptionHelper
	            .isEmpty(file) && (OptionHelper.isEmpty(resource)));
	  }

}
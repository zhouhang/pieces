/**
 * Copyright © 2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.logback;

import java.net.URL;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.classic.joran.action.ConfigurationAction;
import ch.qos.logback.classic.joran.action.ConsolePluginAction;
import ch.qos.logback.classic.joran.action.ContextNameAction;
import ch.qos.logback.classic.joran.action.EvaluatorAction;
import ch.qos.logback.classic.joran.action.InsertFromJNDIAction;
import ch.qos.logback.classic.joran.action.JMXConfiguratorAction;
import ch.qos.logback.classic.joran.action.LevelAction;
import ch.qos.logback.classic.joran.action.LoggerAction;
import ch.qos.logback.classic.joran.action.LoggerContextListenerAction;
import ch.qos.logback.classic.joran.action.RootLoggerAction;
import ch.qos.logback.classic.sift.SiftAction;
import ch.qos.logback.classic.spi.PlatformInfo;
import ch.qos.logback.core.joran.action.AppenderAction;
import ch.qos.logback.core.joran.action.AppenderRefAction;
import ch.qos.logback.core.joran.action.ContextPropertyAction;
import ch.qos.logback.core.joran.action.ConversionRuleAction;
import ch.qos.logback.core.joran.action.DefinePropertyAction;
import ch.qos.logback.core.joran.action.IncludeAction;
import ch.qos.logback.core.joran.action.NOPAction;
import ch.qos.logback.core.joran.action.NewRuleAction;
import ch.qos.logback.core.joran.action.ParamAction;
import ch.qos.logback.core.joran.action.StatusListenerAction;
import ch.qos.logback.core.joran.action.TimestampAction;
import ch.qos.logback.core.joran.conditional.ElseAction;
import ch.qos.logback.core.joran.conditional.IfAction;
import ch.qos.logback.core.joran.conditional.ThenAction;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.joran.spi.Pattern;
import ch.qos.logback.core.joran.spi.RuleStore;

/**
 * @ClassName: JointownLogbackConfigurator
 * @Description: 扩展logback的配置类
 * @Author: robin.liu
 * @Date: 2015年9月1日
 * @Version: 1.0
 */
public class JointownLogbackConfigurator extends JoranConfigurator {
	
	/**
	 * 初始化logback
	 */
	public void init(URL url){
        try {  
            LoggerContext loggerContext = (LoggerContext)LoggerFactory.getILoggerFactory();  
            loggerContext.reset();  
            setContext(loggerContext);  
            doConfigure(url);  
//            logger.debug("loaded slf4j configure file from {}", resource);  
        }catch (JoranException e) {  
//            logger.error("can't loading slf4j configure file from " + resource, e);  
        }
	}
	
	@Override
	  public void addInstanceRules(RuleStore rs) {
		
	    rs.addRule(new Pattern("configuration/variable"), new JointownPropertyAction());
	    rs.addRule(new Pattern("configuration/property"), new JointownPropertyAction());

	    rs.addRule(new Pattern("configuration/substitutionProperty"),
	        new JointownPropertyAction());

	    rs.addRule(new Pattern("configuration/timestamp"), new TimestampAction());

	    rs.addRule(new Pattern("configuration/define"), new DefinePropertyAction());

	    // the contextProperty pattern is deprecated. It is undocumented
	    // and will be dropped in future versions of logback
	    rs.addRule(new Pattern("configuration/contextProperty"),
	        new ContextPropertyAction());

	    rs.addRule(new Pattern("configuration/conversionRule"),
	        new ConversionRuleAction());

	    rs.addRule(new Pattern("configuration/statusListener"),
	        new StatusListenerAction());

	    rs.addRule(new Pattern("configuration/appender"), new AppenderAction());
	    rs.addRule(new Pattern("configuration/appender/appender-ref"),
	        new AppenderRefAction());
	    rs.addRule(new Pattern("configuration/newRule"), new NewRuleAction());
	    rs.addRule(new Pattern("*/param"), new ParamAction());
	    
	    

	    rs.addRule(new Pattern("configuration"), new ConfigurationAction());

	    rs.addRule(new Pattern("configuration/contextName"),
	        new ContextNameAction());
	      rs.addRule(new Pattern("configuration/contextListener"),
	        new LoggerContextListenerAction());
	    rs.addRule(new Pattern("configuration/insertFromJNDI"),
	        new InsertFromJNDIAction());
	    rs.addRule(new Pattern("configuration/evaluator"), new EvaluatorAction());

	    rs.addRule(new Pattern("configuration/appender/sift"), new SiftAction());
	    rs.addRule(new Pattern("configuration/appender/sift/*"), new NOPAction());

	    rs.addRule(new Pattern("configuration/logger"), new LoggerAction());
	    rs.addRule(new Pattern("configuration/logger/level"), new LevelAction());

	    rs.addRule(new Pattern("configuration/root"), new RootLoggerAction());
	    rs.addRule(new Pattern("configuration/root/level"), new LevelAction());
	    rs.addRule(new Pattern("configuration/logger/appender-ref"),
	        new AppenderRefAction());
	    rs.addRule(new Pattern("configuration/root/appender-ref"),
	        new AppenderRefAction());
	    
	    // add if-then-else support
	    rs.addRule(new Pattern("*/if"), new IfAction());
	    rs.addRule(new Pattern("*/if/then"), new ThenAction());
	    rs.addRule(new Pattern("*/if/then/*"), new NOPAction());
	    rs.addRule(new Pattern("*/if/else"), new ElseAction());
	    rs.addRule(new Pattern("*/if/else/*"), new NOPAction());   
	    
	    // add jmxConfigurator only if we have JMX available.
	    // If running under JDK 1.4 (retrotranslateed logback) then we
	    // might not have JMX.
	    if (PlatformInfo.hasJMXObjectName()) {
	      rs.addRule(new Pattern("configuration/jmxConfigurator"),
	          new JMXConfiguratorAction());
	    }
	    rs.addRule(new Pattern("configuration/include"), new IncludeAction());

	    rs.addRule(new Pattern("configuration/consolePlugin"),
	        new ConsolePluginAction());
	  }
}


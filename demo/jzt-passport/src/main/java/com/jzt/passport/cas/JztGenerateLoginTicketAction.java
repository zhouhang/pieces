package com.jzt.passport.cas;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.BooleanUtils;
import org.jasig.cas.util.UniqueTicketIdGenerator;
import org.jasig.cas.web.support.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.webflow.execution.RequestContext;

/**
 * @ClassName: JztGenerateLoginTicketAction
 * @Description: 重写loginticket的分发机制
 * @Author: guoyb
 * @Date: 2015年5月21日
 * @Version: 1.0
 */
public class JztGenerateLoginTicketAction {
	
    private static final String PREFIX = "LT";

    /** Logger instance. */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @NotNull
    private UniqueTicketIdGenerator ticketIdGenerator;

    /**
     * @Description: generateLoginTicket的流程
     * @Author: guoyb
     * @Date: 2015年5月21日
     * @param context 
     * @return
     */
    public final String generate(final RequestContext context) {
    	HttpServletRequest request = WebUtils.getHttpServletRequest(context);  
		boolean isAjax = BooleanUtils.toBoolean(request.getParameter("isajax"));
		
		final String loginTicket = this.ticketIdGenerator.getNewTicketId(PREFIX);
		if (!isAjax){
			logger.debug("Generated login ticket {}", loginTicket);
			WebUtils.putLoginTicket(context, loginTicket);
			return "generated";
		}else {
			Boolean isRefreshLt = true;
			request.setAttribute("isRefreshLt", isRefreshLt);
			request.setAttribute("loginTicket", loginTicket);
			logger.debug("Generated for ajax login ticket {}", loginTicket);
			WebUtils.putLoginTicket(context, loginTicket);
			boolean isPopLogin = BooleanUtils.toBoolean(request.getParameter("isPopLogin"));
			if(isPopLogin){
				return "popGenerated";
			}else {
				return "ajaxLoginTicket";
			}
		}
    }

    /**
     * @Description: 注入TicketIdGenerator
     * @Author: guoyb
     * @Date: 2015年5月21日
     * @param generator 
     */
    public void setTicketIdGenerator(final UniqueTicketIdGenerator generator) {
        this.ticketIdGenerator = generator;
    }
}

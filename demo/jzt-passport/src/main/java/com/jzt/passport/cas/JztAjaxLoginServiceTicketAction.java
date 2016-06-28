package com.jzt.passport.cas;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasig.cas.authentication.principal.Service;
import org.jasig.cas.ticket.Ticket;
import org.jasig.cas.ticket.TicketGrantingTicket;
import org.jasig.cas.web.support.WebUtils;
import org.springframework.webflow.action.AbstractAction;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.FlowExecutionContext;
import org.springframework.webflow.execution.FlowSession;
import org.springframework.webflow.execution.RequestContext;

/**
 * @ClassName: JztAjaxLoginServiceTicketAction
 * @Description: ajax登录请求
 * @Author: guoyb
 * @Date: 2015年5月21日
 * @Version: 1.0
 */
public class JztAjaxLoginServiceTicketAction extends AbstractAction {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	private JztRedisTicketRegistry ticketRegistry;

	@Override
	protected Event doExecute(RequestContext context) throws Exception {
		HttpServletRequest request = WebUtils.getHttpServletRequest(context);  
		Event event = context.getCurrentEvent();  
		boolean isAjax = BooleanUtils.toBoolean(request.getParameter("isajax"));  
		if (!isAjax){  // 非 ajax/iframe 方式登录，返回当前 event.  
			logger.info("isAjax=====================>"+isAjax);
		    return event;
		}
		
		//判断是否是异步登陆，用于自动登陆
		boolean isLoginSuccess;
		
		//是否登录成功
		if ("success".equals(event.getId())){   
		    final Service service = WebUtils.getService(context);  
		    final String serviceTicket = WebUtils.getServiceTicketFromRequestScope(context);
		    
		    //通过tgt获得username,同时确定tgt存在保证用户是登陆状态
		    final FlowExecutionContext flowExecutionContext = context.getFlowExecutionContext();
		    final FlowSession flowSession = flowExecutionContext.getActiveSession();
		    final MutableAttributeMap map = flowSession.getScope();
		    final String ticketGrantingTicketId = (String) map.get("ticketGrantingTicketId");
		    Ticket ticket = ticketRegistry.getRawTicket(ticketGrantingTicketId);
		    if(ticket instanceof TicketGrantingTicket){
		    	final TicketGrantingTicket tgt = (TicketGrantingTicket) ticket;
		    	String username = tgt.getAuthentication().getPrincipal().getId();  
		    	request.setAttribute("username", username);
		    	request.setAttribute("type", request.getParameter("type"));
	        }
		    
		    logger.debug(flowSession.toString());
		    // serviceTicket.getGrantingTicket().getAuthentication().getPrincipal().getId();
		    if (service != null){  //设置登录成功之后 跳转的地址  
		        request.setAttribute("service", service.getId());  
		    }
		    logger.debug("serviceTicket==>"+serviceTicket);
		    request.setAttribute("ticket", serviceTicket);
		    isLoginSuccess = true;
		} else { // Login Fails..
		    isLoginSuccess = false;
		}
		
		//用于完成弹窗登陆
		boolean isPopLogin = BooleanUtils.toBoolean(request.getParameter("isPopLogin"));
		
		request.setAttribute("isLogin", isLoginSuccess);
		request.setAttribute("isPopLogin", isPopLogin);
		return new Event(this, "local"); // 转入 ajaxLogin.jsp 页面
	}

	public void setTicketRegistry(JztRedisTicketRegistry ticketRegistry) {
		this.ticketRegistry = ticketRegistry;
	}

}

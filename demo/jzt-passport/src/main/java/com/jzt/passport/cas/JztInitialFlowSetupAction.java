package com.jzt.passport.cas;

import java.util.List;

import org.jasig.cas.web.flow.InitialFlowSetupAction;
import org.jasig.cas.web.support.ArgumentExtractor;
import org.jasig.cas.web.support.CookieRetrievingCookieGenerator;
import org.springframework.webflow.action.AbstractAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

import com.jointown.zy.common.constant.MessageConstant;

public class JztInitialFlowSetupAction extends AbstractAction {

	private InitialFlowSetupAction core;

	public void setTicketGrantingTicketCookieGenerator(
			CookieRetrievingCookieGenerator ticketGrantingTicketCookieGenerator) {
		core.setTicketGrantingTicketCookieGenerator(ticketGrantingTicketCookieGenerator);
	}

	public void setWarnCookieGenerator(
			CookieRetrievingCookieGenerator warnCookieGenerator) {
		core.setWarnCookieGenerator(warnCookieGenerator);
	}

	public void setArgumentExtractors(List<ArgumentExtractor> argumentExtractors) {
		core.setArgumentExtractors(argumentExtractors);
	}

	public InitialFlowSetupAction getCore() {
		return core;
	}

	public void setCore(InitialFlowSetupAction core) {
		this.core = core;
	}

	@Override
	protected Event doExecute(RequestContext context) throws Exception {
		Event result = getCore().execute(context);
		MessageConstant.URL_SERVER_PREFIX_UC.getValue();
		context.getFlowScope().put("registerUrl",  MessageConstant.URL_SERVER_PREFIX_UC.getValue()+MessageConstant.URL_REGISTER_UC.getValue());
		context.getFlowScope().put("findPasswordUrl", MessageConstant.URL_SERVER_PREFIX_UC.getValue()+MessageConstant.URL_FIND_PASSWORD_UC.getValue());
		context.getFlowScope().put("JOINTOWNURL", MessageConstant.JOINTOWNURL.getValue());
		return result;
	}

}

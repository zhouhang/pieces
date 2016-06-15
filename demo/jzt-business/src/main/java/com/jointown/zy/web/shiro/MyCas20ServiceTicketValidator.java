package com.jointown.zy.web.shiro;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;

import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.authentication.AttributePrincipalImpl;
import org.jasig.cas.client.proxy.Cas20ProxyRetriever;
import org.jasig.cas.client.proxy.ProxyGrantingTicketStorage;
import org.jasig.cas.client.proxy.ProxyRetriever;
import org.jasig.cas.client.util.CommonUtils;
import org.jasig.cas.client.util.XmlUtils;
import org.jasig.cas.client.validation.AbstractCasProtocolUrlBasedTicketValidator;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.AssertionImpl;
import org.jasig.cas.client.validation.TicketValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MyCas20ServiceTicketValidator
extends AbstractCasProtocolUrlBasedTicketValidator
{
	private Logger log = LoggerFactory.getLogger(getClass());
	
	private String proxyCallbackUrl;
	private ProxyGrantingTicketStorage proxyGrantingTicketStorage;
	private ProxyRetriever proxyRetriever;

	
	public MyCas20ServiceTicketValidator(String casServerUrlPrefix)
	{
		super(casServerUrlPrefix);
		log.debug("###########MyCas20ServiceTicketValidator:"+casServerUrlPrefix+"#############");
		this.proxyRetriever = new Cas20ProxyRetriever(
				casServerUrlPrefix, getEncoding());
		}

	
	protected final void populateUrlAttributeMap(
			Map<String, String> urlParameters)
	{
		urlParameters.put("pgtUrl",
				encodeUrl(this.proxyCallbackUrl));
		}

	
	protected String getUrlSuffix() {
		return "serviceValidate";
		}

	
	protected final Assertion parseResponseFromServer(String response)
			throws TicketValidationException {
		String error = XmlUtils.getTextForElement(response,
				"authenticationFailure");
		
		if (CommonUtils.isNotBlank(error)) {
			throw new TicketValidationException(error);
			}
		
		String principal = XmlUtils.getTextForElement(response,
				"user");
		String proxyGrantingTicketIou = XmlUtils.getTextForElement(
				response, "proxyGrantingTicket");
		String proxyGrantingTicket = this.proxyGrantingTicketStorage != null ? this.proxyGrantingTicketStorage
				.retrieve(proxyGrantingTicketIou) : null;
		
		if (CommonUtils.isEmpty(principal)) {
			throw new TicketValidationException(
					"No principal was found in the response from the CAS server.");
			}
		
		Map<String, Object> attributes = extractCustomAttributes(response);
		Assertion assertion;
		if (CommonUtils.isNotBlank(proxyGrantingTicket)) {
			AttributePrincipal attributePrincipal = new AttributePrincipalImpl(
					principal, attributes, proxyGrantingTicket,
					this.proxyRetriever);
			assertion = new AssertionImpl(attributePrincipal);
			} else {
			assertion = new AssertionImpl(
					new AttributePrincipalImpl(principal, attributes));
			}
		
		customParseResponse(response, assertion);
		
		return assertion;
		}

	
	protected Map<String, Object> extractCustomAttributes(String xml)
	{
		int pos1 = xml.indexOf("<cas:attributes>");
		int pos2 = xml.indexOf("</cas:attributes>");
		
		if (pos1 == -1) {
			return Collections.emptyMap();
			}
		
		String attributesText = xml.substring(pos1 + 16, pos2);
		
		Map<String, Object> attributes = new HashMap();
		BufferedReader br = new BufferedReader(new StringReader(
				attributesText));
		
		List<String> attributeNames = new ArrayList();
		try {
			String line;
			while ((line = br.readLine()) != null) {
				String trimmedLine = line.trim();
				if (trimmedLine.length() > 0) {
					int leftPos = trimmedLine.indexOf(":");
					int rightPos = trimmedLine.indexOf(">");
					attributeNames.add(trimmedLine.substring(
							leftPos + 1, rightPos));
					}
				}
			br.close();
			}
		catch (IOException e) {
		}
		
		for (String name : attributeNames) {
			List<String> values = XmlUtils.getTextForElements(xml,
					name);
			
			if (values.size() == 1) {
				attributes.put(name, values.get(0));
				} else {
				attributes.put(name, values);
				}
			}
		
		return attributes;
		}

	
	protected void customParseResponse(String response,
			Assertion assertion)
	throws TicketValidationException
	{
	}

	
	public final void setProxyCallbackUrl(String proxyCallbackUrl)
	{
		this.proxyCallbackUrl = proxyCallbackUrl;
		}

	
	public final void setProxyGrantingTicketStorage(
			ProxyGrantingTicketStorage proxyGrantingTicketStorage) {
		this.proxyGrantingTicketStorage = proxyGrantingTicketStorage;
		}

	
	public final void setProxyRetriever(ProxyRetriever proxyRetriever) {
		this.proxyRetriever = proxyRetriever;
		}

	
	public Assertion validate(String ticket, String service)
			throws TicketValidationException {
		String validationUrl = constructValidationUrl(ticket,
				service);
		if (this.log.isDebugEnabled()) {
			this.log
					.debug("##########Constructing validation url: "
							+ validationUrl);
			}
		try
		{
			this.log
					.debug("#####Retrieving response from server.");
			String serverResponse = retrieveMyResponseFromServer(
					new URL(validationUrl), ticket);
			
			
			this.log
			.debug("##########serverResponse is: "
					+ serverResponse);
			
			if (serverResponse == null) {
				throw new TicketValidationException(
						"The CAS server returned no response.");
				}
			
			if (this.log.isDebugEnabled()) {
				this.log.debug("#####Server response: "
						+ serverResponse);
				}
			
			return parseResponseFromServer(serverResponse);
			} catch (MalformedURLException e) {
			throw new TicketValidationException(e);
			}
		}
	
	
	
	
	 protected  String retrieveMyResponseFromServer(final URL validationUrl, final String ticket) {
		 this.log
			.debug("##########retrieveMyResponseFromServer validationUrl: "
					+ validationUrl+"; ticket is:"+ticket+"; hostnameVerifier is:"+hostnameVerifier);
	        if (this.hostnameVerifier != null) {
		        return getResponseFromServer(validationUrl, this.hostnameVerifier, getEncoding());
	        } else {
		        return getResponseFromServer(validationUrl, getEncoding());
	        }
	    }
	 
	 
	 
	 
	 public String getResponseFromServer(final URL constructedUrl, final String encoding) {
		 	this.log.debug("#########getResponseFromServer no verifier,verifier is :"+HttpsURLConnection.getDefaultHostnameVerifier()+"encoding is"+encoding+"#########");
	        return getResponseFromServer(constructedUrl, HttpsURLConnection.getDefaultHostnameVerifier(), encoding);
	 }
	 
	 
	 
	 public  String getResponseFromServer(final URL constructedUrl, final HostnameVerifier hostnameVerifier, final String encoding) {
		 this.log.debug("#########getResponseFromServer verifier,verifier is :"+hostnameVerifier+"encoding is"+encoding+"#########");
	        URLConnection conn = null;
	        try {
	            conn = constructedUrl.openConnection();
	            if (conn instanceof HttpsURLConnection) {
	                ((HttpsURLConnection)conn).setHostnameVerifier(hostnameVerifier);
	            }
	            final BufferedReader in;

	            if (CommonUtils.isEmpty(encoding)) {
	                in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            } else {
	                in = new BufferedReader(new InputStreamReader(conn.getInputStream(), encoding));
	            }

	            String line;
	            final StringBuilder stringBuffer = new StringBuilder(255);

	            while ((line = in.readLine()) != null) {
	                stringBuffer.append(line);
	                stringBuffer.append("\n");
	            }
	            return stringBuffer.toString();
	        } catch (final Exception e) {
	        	this.log.debug("#########validate connection error occurs,error is :"+e.getMessage()+"#########");
	        	this.log.debug("#########validate connection error occurs,error is :"+e+"#########");
	        	e.printStackTrace();
	            this.log.error(e.getMessage(), e);
	            throw new RuntimeException(e);
	        } finally {
	            if (conn != null && conn instanceof HttpURLConnection) {
	                ((HttpURLConnection)conn).disconnect();
	            }
	        }

	    }
	 
	 
	 
	
}


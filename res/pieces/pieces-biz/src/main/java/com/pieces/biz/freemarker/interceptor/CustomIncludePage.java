package com.pieces.biz.freemarker.interceptor;

import freemarker.core.Environment;
import freemarker.template.*;
import freemarker.template.utility.Collections12;
import freemarker.template.utility.DeepUnwrap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.util.*;

/***
 * A model that when invoked with a 'path' parameter will perform a servlet
 * include. It also support an optional hash named 'params' which specifies
 * request parameters for the include - its keys are strings, its values should
 * be either strings or sequences of strings (for multiple valued parameters). A
 * third optional parameter 'inherit_params' should be a boolean when specified,
 * and it defaults to true when not specified. A value of true means that the
 * include inherits the request parameters from the current request. In this
 * case values in 'params' will get prepended to the existing values of
 * parameters.
 * 
 * @author Attila Szegedi
 * @version $Id: $
 */
public class CustomIncludePage implements TemplateDirectiveModel {
	private final HttpServletRequest request;
	private final HttpServletResponse response;

	public CustomIncludePage(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public void execute(final Environment env, Map params,
						TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		
		// Determine the path
		final TemplateModel path = (TemplateModel) params.get("path");
		if (path == null) {
			throw new TemplateException("Missing required parameter 'path'",
					env);
		}
		if (!(path instanceof TemplateScalarModel)) {
			throw new TemplateException(
					"Expected a scalar model. 'path' is instead "
							+ path.getClass().getName(), env);
		}
		final String strPath = ((TemplateScalarModel) path).getAsString();
		if (strPath == null) {
			throw new TemplateException(
					"String value of 'path' parameter is null", env);
		}

		
		// See whether we need to use a custom response (if we're inside a TTM
		// or TDM or macro nested body, we'll need to as then the current
		// FM environment writer is not identical to HTTP servlet response
		// writer.
		final Writer envOut = env.getOut();
		final ResponseWrapper wrappedResponse =new ResponseWrapper(response);  

		// Determine inherit_params value
		final boolean inheritParams;
		final TemplateModel inheritParamsModel = (TemplateModel) params
				.get("inherit_params");
		if (inheritParamsModel == null) {
			// defaults to true when not specified
			inheritParams = true;
		} else {
			if (!(inheritParamsModel instanceof TemplateBooleanModel)) {
				throw new TemplateException(
						"'inherit_params' should be a boolean but it is "
								+ inheritParamsModel.getClass().getName()
								+ " instead", env);
			}
			inheritParams = ((TemplateBooleanModel) inheritParamsModel)
					.getAsBoolean();
		}

		// Get explicit params, if any
		final TemplateModel paramsModel = (TemplateModel) params.get("params");

		// Determine whether we need to wrap the request
		final HttpServletRequest wrappedRequest;
		if (paramsModel == null && inheritParams) {
			// Inherit original request params & no params explicitly
			// specified, so use the original request
			wrappedRequest = request;
		} else {
			// In any other case, use a custom request wrapper
			final Map paramsMap;
			if (paramsModel != null) {
				// Convert params to a Map
				final Object unwrapped = DeepUnwrap.unwrap(paramsModel);
				if (!(unwrapped instanceof Map)) {
					throw new TemplateException("Expected 'params' to unwrap "
							+ "into a java.util.Map. It unwrapped into "
							+ unwrapped.getClass().getName() + " instead.", env);
				}
				paramsMap = (Map) unwrapped;
			} else {
				paramsMap = Collections12.EMPTY_MAP;
			}
			wrappedRequest = new CustomParamsRequest(request, paramsMap, inheritParams);
		}
		String qs=wrappedRequest.getQueryString();
		
		final PrintWriter printWriter = (envOut instanceof PrintWriter) ? (PrintWriter) envOut : new PrintWriter(envOut);

	}

	
	//局部块缓存
	private String genHtml(final HttpServletRequest wrappedRequest, final ResponseWrapper wrappedResponse, 
			final PrintWriter printWriter, final String baseUrl, final String url, final String qs) throws ServletException, IOException{

		request.getRequestDispatcher(url).include(wrappedRequest, wrappedResponse);
		
		String html = wrappedResponse.getResult();

		return html;
	}

	public static class ResponseWrapper extends HttpServletResponseWrapper {
		private PrintWriter cachedWriter;  
	    private ByteArrayOutputStream outputStream;  
	  
	    public ResponseWrapper(HttpServletResponse response) {  
	        super(response);  
	        // 这个是我们保存返回结果的地方  
	        outputStream = new ByteArrayOutputStream();  
	        // 这个是包装PrintWriter的，让所有结果通过这个PrintWriter写入到bufferedWriter中  
	        cachedWriter = new PrintWriter(outputStream);  
	    }  
	  
	    @Override  
	    public PrintWriter getWriter() {  
	        return cachedWriter;  
	    }  
	  
	    /** 
	     * 获取原始的HTML页面内容。 
	     *  
	     * @return 
	     */  
	    public String getResult() {  
	    	int UTF8_BOM_LENGTH=3;
	    	byte[] rByte=outputStream.toByteArray();
	    	if (rByte!=null && rByte.length>2 &&
	    			(rByte[0] & 0xFF) == 0xEF && 
	                (rByte[1] & 0xFF) == 0xBB && 
	                (rByte[2] & 0xFF) == 0xBF) {
	    		//EF BB BF ï»¿ 去除文本头的BOM字符
	    		int length = rByte.length - UTF8_BOM_LENGTH;
	    		byte[] barray = new byte[length];
	            System.arraycopy(rByte, UTF8_BOM_LENGTH, barray, 0, barray.length);
	    		return new String(barray);
	        }else{
	        	return new String(rByte);  
	        }
	        
	    }  

	}

	private static final class CustomParamsRequest extends
			HttpServletRequestWrapper {
		private final HashMap paramsMap;

		private CustomParamsRequest(HttpServletRequest request, Map paramMap,
				boolean inheritParams) {
			super(request);
			paramsMap = inheritParams ? new HashMap(request.getParameterMap())
					: new HashMap();
			for (Iterator it = paramMap.entrySet().iterator(); it.hasNext();) {
				Map.Entry entry = (Map.Entry) it.next();
				String name = String.valueOf(entry.getKey());
				Object value = entry.getValue();
				final String[] valueArray;
				if (value == null) {
					// Null values are explicitly added (so, among other
					// things, we can hide inherited param values).
					valueArray = new String[] { null };
				} else if (value instanceof String[]) {
					// String[] arrays are just passed through
					valueArray = (String[]) value;
				} else if (value instanceof Collection) {
					// Collections are converted to String[], with
					// String.valueOf() used on elements
					Collection col = (Collection) value;
					valueArray = new String[col.size()];
					int i = 0;
					for (Iterator it2 = col.iterator(); it2.hasNext();) {
						valueArray[i++] = String.valueOf(it2.next());
					}
				} else if (value.getClass().isArray()) {
					// Other array types are too converted to String[], with
					// String.valueOf() used on elements
					int len = Array.getLength(value);
					valueArray = new String[len];
					for (int i = 0; i < len; ++i) {
						valueArray[i] = String.valueOf(Array.get(value, i));
					}
				} else {
					// All other values (including strings) are converted to a
					// single-element String[], with String.valueOf applied to
					// the value.
					valueArray = new String[] { String.valueOf(value) };
				}
				String[] existingParams = (String[]) paramsMap.get(name);
				int el = existingParams == null ? 0 : existingParams.length;
				if (el == 0) {
					// No original params, just put our array
					paramsMap.put(name, valueArray);
				} else {
					int vl = valueArray.length;
					if (vl > 0) {
						// Both original params and new params, prepend our
						// params to original params
						String[] newValueArray = new String[el + vl];
						System.arraycopy(valueArray, 0, newValueArray, 0, vl);
						System.arraycopy(existingParams, 0, newValueArray, vl,
								el);
						paramsMap.put(name, newValueArray);
					}
				}
			}
		}

		@Override
		public String[] getParameterValues(String name) {
			String[] value = ((String[]) paramsMap.get(name));
			return value != null ? (String[]) value.clone() : null;
		}

		@Override
		public String getParameter(String name) {
			String[] values = (String[]) paramsMap.get(name);
			return values != null && values.length > 0 ? values[0] : null;
		}

		@Override
		public Enumeration getParameterNames() {
			return Collections.enumeration(paramsMap.keySet());
		}

		@Override
		public Map getParameterMap() {
			HashMap clone = (HashMap) paramsMap.clone();
			for (Iterator it = clone.entrySet().iterator(); it.hasNext();) {
				Map.Entry entry = (Map.Entry) it.next();
				entry.setValue(((String[]) entry.getValue()).clone());
			}
			return Collections.unmodifiableMap(clone);
		}

	}
}

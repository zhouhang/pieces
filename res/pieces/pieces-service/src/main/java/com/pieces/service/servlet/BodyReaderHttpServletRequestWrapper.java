package com.pieces.service.servlet;

import org.apache.commons.httpclient.auth.AuthenticationException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 * Created by wangbin on 2016/12/29.
 */
public class BodyReaderHttpServletRequestWrapper extends HttpServletRequestWrapper implements Cloneable{


    private  String payload;

    public BodyReaderHttpServletRequestWrapper(HttpServletRequest request) throws AuthenticationException {
        super(request);
        // read the original payload into the payload variable
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            // read the payload into the StringBuilder
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                // make an empty string since there is no payload
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw new AuthenticationException("Error reading the request payload", ex);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException iox) {
                    // ignore
                }
            }
        }
        payload = stringBuilder.toString();
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(payload.getBytes());
        ServletInputStream inputStream = new ServletInputStream() {
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        };
        return inputStream;
    }



}

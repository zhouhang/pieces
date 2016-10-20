package com.ms.tools.upload;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.UUID;

/**
 * Author: koabs
 * 8/10/16.
 */
@Component
public class TempUploadFile extends AbstractUploadFile {

    @Override
    public String customImageName(String fileName) {
        Calendar now = Calendar.getInstance();
        int year=now.get(Calendar.YEAR);
        int month=now.get(Calendar.MONTH)+1;
        StringBuffer sb = new StringBuffer();
        sb.append("temp").append("/").append(year).append("/").append(month).append("/").append(UUID.randomUUID() + fileName);
        return sb.toString();
    }

    @Override
    public InputStream addWatermark(InputStream inputStream, String ext) throws IOException {
        return inputStream;
    }
}

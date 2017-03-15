package com.pieces.tools.bean;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * Author: koabs
 * 12/29/16.
 */
public class BASE64DecodedMultipartFile implements MultipartFile {

    private final byte[] imgContent;
    private String fileName;

    public BASE64DecodedMultipartFile(byte[] imgContent, String fileName) {
        this.imgContent = imgContent;
        this.fileName = fileName;
    }

    @Override
    public String getName() {
        // TODO - implementation depends on your requirements
        return fileName;
    }

    @Override
    public String getOriginalFilename() {
        // TODO - implementation depends on your requirements
        return fileName;
    }

    @Override
    public String getContentType() {
        // TODO - implementation depends on your requirements
        return null;
    }

    @Override
    public boolean isEmpty() {
        return imgContent == null || imgContent.length == 0;
    }

    @Override
    public long getSize() {
        return imgContent.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return imgContent;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(imgContent);
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        new FileOutputStream(dest).write(imgContent);
    }
}

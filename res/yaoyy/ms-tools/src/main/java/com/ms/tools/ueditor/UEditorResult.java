package com.ms.tools.ueditor;

/**
 * Author: koabs
 * 7/20/16.
 */
public class UEditorResult {
    private String name;
    private String originalName;
    private String size;
    private String state; //SUCCESS
    private String type;
    private String url;


    public static UEditorResult success(String name, String originalName, String url) {
        return new UEditorResult(name, originalName, "10", "SUCCESS", ".jpg",url);
    }

    public UEditorResult(String name, String originalName, String size, String state, String type, String url) {
        this.name = name;
        this.originalName = originalName;
        this.size = size;
        this.state = state;
        this.type = type;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

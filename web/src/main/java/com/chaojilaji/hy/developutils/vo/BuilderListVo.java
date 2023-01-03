package com.chaojilaji.hy.developutils.vo;

public class BuilderListVo {

    private String id;
    private String className;
    private String fields;
    private String textMd5;
    private String fileName;
    private String createdTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public String getTextMd5() {
        return textMd5;
    }

    public void setTextMd5(String textMd5) {
        this.textMd5 = textMd5;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }
}

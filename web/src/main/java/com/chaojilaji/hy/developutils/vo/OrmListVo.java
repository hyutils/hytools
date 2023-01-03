package com.chaojilaji.hy.developutils.vo;

public class OrmListVo {
    private String id;
    private String tableNumber;
    private String textMd5;
    private String fileName;
    private String createTime;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private OrmListVo target;

        public Builder() {
            this.target = new OrmListVo();
        }

        public OrmListVo build() {
            return target;
        }

        public Builder fileName(String fileName) {
            this.target.fileName = fileName;
            return this;
        }

        public Builder createTime(String createTime) {
            this.target.createTime = createTime;
            return this;
        }

        public Builder id(String id) {
            this.target.id = id;
            return this;
        }

        public Builder textMd5(String textMd5) {
            this.target.textMd5 = textMd5;
            return this;
        }

        public Builder tableNumber(String tableNumber) {
            this.target.tableNumber = tableNumber;
            return this;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}

package com.chaojilaji.hy.developutils.filemodel;

public class OrmStruct {
    private String id;
    private String resultPath;
    private String sourceCode;
    private String md5;
    private String fileName;
    private String createTime;
    private Integer tableNumber;
    private String packageName;
    private String baseName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResultPath() {
        return resultPath;
    }

    public void setResultPath(String resultPath) {
        this.resultPath = resultPath;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }

    public Integer getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }



    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private OrmStruct target;

        public Builder() {
            this.target = new OrmStruct();
        }

        public OrmStruct build() {
            return target;
        }

        public Builder sourceCode(String sourceCode) {
            this.target.sourceCode = sourceCode;
            return this;
        }

        public Builder fileName(String fileName) {
            this.target.fileName = fileName;
            return this;
        }

        public Builder createTime(String createTime) {
            this.target.createTime = createTime;
            return this;
        }

        public Builder resultPath(String resultPath) {
            this.target.resultPath = resultPath;
            return this;
        }

        public Builder id(String id) {
            this.target.id = id;
            return this;
        }

        public Builder packageName(String packageName) {
            this.target.packageName = packageName;
            return this;
        }

        public Builder baseName(String baseName) {
            this.target.baseName = baseName;
            return this;
        }

        public Builder tableNumber(Integer tableNumber) {
            this.target.tableNumber = tableNumber;
            return this;
        }

        public Builder md5(String md5) {
            this.target.md5 = md5;
            return this;
        }
    }

}

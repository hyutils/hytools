package com.chaojilaji.hy.developutils.filemodel;

public class Json2DtoStruct {
    private String id;
    private String resultPath;
    private String sourceCode;
    private String md5;
    private String fileName;
    private String createTime;
    private String className;
    private Integer classNumber;

    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {
        private Json2DtoStruct target;

        public Builder() {
            this.target = new Json2DtoStruct();
        }

        public Json2DtoStruct build() {
            return target;
        }public Builder sourceCode(String sourceCode) {
            this.target.sourceCode=sourceCode;
            return this;
        }
        public Builder fileName(String fileName) {
            this.target.fileName=fileName;
            return this;
        }
        public Builder createTime(String createTime) {
            this.target.createTime=createTime;
            return this;
        }
        public Builder resultPath(String resultPath) {
            this.target.resultPath=resultPath;
            return this;
        }
        public Builder className(String className) {
            this.target.className=className;
            return this;
        }
        public Builder id(String id) {
            this.target.id=id;
            return this;
        }
        public Builder classNumber(Integer classNumber) {
            this.target.classNumber=classNumber;
            return this;
        }
        public Builder md5(String md5) {
            this.target.md5=md5;
            return this;
        }
    }

    public Integer getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(Integer classNumber) {
        this.classNumber = classNumber;
    }

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}

package com.chaojilaji.hy.developutils.vo;

public class Json2DtoDetailVo {
    private String id;
    private String fileName;
    private String fileMd5;
    private String code;
    private String className;
    private String createTime;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Json2DtoDetailVo target;

        public Builder() {
            this.target = new Json2DtoDetailVo();
        }

        public Json2DtoDetailVo build() {
            return target;
        }

        public Builder fileName(String fileName) {
            this.target.fileName = fileName;
            return this;
        }

        public Builder code(String code) {
            this.target.code = code;
            return this;
        }

        public Builder createTime(String createTime) {
            this.target.createTime = createTime;
            return this;
        }

        public Builder className(String className) {
            this.target.className = className;
            return this;
        }

        public Builder id(String id) {
            this.target.id = id;
            return this;
        }

        public Builder fileMd5(String fileMd5) {
            this.target.fileMd5 = fileMd5;
            return this;
        }
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileMd5() {
        return fileMd5;
    }

    public void setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}

package com.chaojilaji.hy.developutils.vo;

public class Json2SqlListVo {
    private String id;
    private String classNumber;
    private String jsonCode;
    private String baseName;
    private String createTime;

    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {
        private Json2SqlListVo target;

        public Builder() {
            this.target = new Json2SqlListVo();
        }

        public Json2SqlListVo build() {
            return target;
        }public Builder jsonCode(String jsonCode) {
            this.target.jsonCode=jsonCode;
            return this;
        }
        public Builder createTime(String createTime) {
            this.target.createTime=createTime;
            return this;
        }
        public Builder id(String id) {
            this.target.id=id;
            return this;
        }
        public Builder classNumber(String classNumber) {
            this.target.classNumber=classNumber;
            return this;
        }
        public Builder baseName(String baseName) {
            this.target.baseName=baseName;
            return this;
        }
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    public String getJsonCode() {
        return jsonCode;
    }

    public void setJsonCode(String jsonCode) {
        this.jsonCode = jsonCode;
    }

    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}

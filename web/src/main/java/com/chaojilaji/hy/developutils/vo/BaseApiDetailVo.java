package com.chaojilaji.hy.developutils.vo;

public class BaseApiDetailVo {
    private String id;
    private String method;
    private String type;
    private String url;
    private String name;
    private String fatherName;
    private String fatherPath;
    private String createdTime;

    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {
        private BaseApiDetailVo target;

        public Builder() {
            this.target = new BaseApiDetailVo();
        }

        public BaseApiDetailVo build() {
            return target;
        }public Builder fatherName(String fatherName) {
            this.target.fatherName=fatherName;
            return this;
        }
        public Builder method(String method) {
            this.target.method=method;
            return this;
        }
        public Builder fatherPath(String fatherPath) {
            this.target.fatherPath=fatherPath;
            return this;
        }
        public Builder name(String description) {
            this.target.name=description;
            return this;
        }
        public Builder createdTime(String createdTime) {
            this.target.createdTime=createdTime;
            return this;
        }
        public Builder id(String id) {
            this.target.id=id;
            return this;
        }
        public Builder type(String type) {
            this.target.type=type;
            return this;
        }
        public Builder url(String url) {
            this.target.url=url;
            return this;
        }
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getFatherPath() {
        return fatherPath;
    }

    public void setFatherPath(String fatherPath) {
        this.fatherPath = fatherPath;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }
}

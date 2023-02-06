package com.chaojilaji.hy.developutils.utils;

import java.util.Map;

public class GenerateDto {
    public String createSql;
    public String exchange;
    public String dto;
    public String name;
    public Map<String,Object> params;

    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {
        private GenerateDto target;

        public Builder() {
            this.target = new GenerateDto();
        }

        public GenerateDto build() {
            return target;
        }public Builder createSql(String createSql) {
            this.target.createSql=createSql;
            return this;
        }
        public Builder name(String name) {
            this.target.name=name;
            return this;
        }
        public Builder exchange(String exchange) {
            this.target.exchange=exchange;
            return this;
        }
        public Builder params(Map<String,Object> params) {
            this.target.params=params;
            return this;
        }
        public Builder dto(String dto) {
            this.target.dto=dto;
            return this;
        }
    }


    public String getCreateSql() {
        return createSql;
    }

    public void setCreateSql(String createSql) {
        this.createSql = createSql;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getDto() {
        return dto;
    }

    public void setDto(String dto) {
        this.dto = dto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}

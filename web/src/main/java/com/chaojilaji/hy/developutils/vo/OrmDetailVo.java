package com.chaojilaji.hy.developutils.vo;

public class OrmDetailVo {
    private String id;
    private String fileName;
    private String fileMd5;
    private String tableNumber;
    private String metadataNumber;
    private String code;


    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private OrmDetailVo target;

        public Builder() {
            this.target = new OrmDetailVo();
        }

        public OrmDetailVo build() {
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

        public Builder id(String id) {
            this.target.id = id;
            return this;
        }

        public Builder fileMd5(String fileMd5) {
            this.target.fileMd5 = fileMd5;
            return this;
        }

        public Builder tableNumber(String tableNumber) {
            this.target.tableNumber = tableNumber;
            return this;
        }

        public Builder metadataNumber(String metadataNumber) {
            this.target.metadataNumber = metadataNumber;
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

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getMetadataNumber() {
        return metadataNumber;
    }

    public void setMetadataNumber(String metadataNumber) {
        this.metadataNumber = metadataNumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

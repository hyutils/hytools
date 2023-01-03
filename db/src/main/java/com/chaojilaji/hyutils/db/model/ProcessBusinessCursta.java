package com.chaojilaji.hyutils.db.model;

import com.chaojilaji.hyutils.dbcore.extension.like.LikeParamExtension;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.List;

//业务流程-描述某个业务在某个流程下的当前流程节点
public class ProcessBusinessCursta {
    private Long id;         //记录标识 
    private List<Long> idList; //orm层需要，无实际意义
    private String processName;         //流程名 
    private Long businessId;         //业务id
    private List<Long> businessIdList; //orm层需要，无实际意义
    private String curHistoryId;         //当前历史id列表 
    private Long createdId;         //创建人标识 
    private String createdName;         //创建人姓名 
    private LocalDateTime createdTime;         //创建时间 
    private LocalDateTime createdTimeBiggerThan; // orm层需要，无实际意义
    private LocalDateTime createdTimeLowerThan; // orm层需要，无实际意义
    private Long modifiedId;         //修改人标识 
    private String modifiedName;         //修改人姓名 
    private LocalDateTime modifiedTime;         //修改时间 
    private LocalDateTime modifiedTimeBiggerThan; // orm层需要，无实际意义
    private LocalDateTime modifiedTimeLowerThan; // orm层需要，无实际意义
    private Long deletedId;         //删除人标识 
    private String deletedName;         //删除人姓名 
    private LocalDateTime deletedTime;         //删除时间 
    private LocalDateTime deletedTimeBiggerThan; // orm层需要，无实际意义
    private LocalDateTime deletedTimeLowerThan; // orm层需要，无实际意义
    private Boolean deletedMark;         //删除标记 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getIdList() {
        return idList;
    }

    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public String getCurHistoryId() {
        return curHistoryId;
    }

    public void setCurHistoryId(String curHistoryId) {
        this.curHistoryId = curHistoryId;
    }

    public Long getCreatedId() {
        return createdId;
    }

    public void setCreatedId(Long createdId) {
        this.createdId = createdId;
    }

    public String getCreatedName() {
        return createdName;
    }

    public void setCreatedName(String createdName) {
        this.createdName = createdName;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getCreatedTimeBiggerThan() {
        return createdTimeBiggerThan;
    }

    public void setCreatedTimeBiggerThan(LocalDateTime createdTimeBiggerThan) {
        this.createdTimeBiggerThan = createdTimeBiggerThan;
    }

    public LocalDateTime getCreatedTimeLowerThan() {
        return createdTimeLowerThan;
    }

    public void setCreatedTimeLowerThan(LocalDateTime createdTimeLowerThan) {
        this.createdTimeLowerThan = createdTimeLowerThan;
    }

    public Long getModifiedId() {
        return modifiedId;
    }

    public void setModifiedId(Long modifiedId) {
        this.modifiedId = modifiedId;
    }

    public String getModifiedName() {
        return modifiedName;
    }

    public void setModifiedName(String modifiedName) {
        this.modifiedName = modifiedName;
    }

    public LocalDateTime getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(LocalDateTime modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public LocalDateTime getModifiedTimeBiggerThan() {
        return modifiedTimeBiggerThan;
    }

    public void setModifiedTimeBiggerThan(LocalDateTime modifiedTimeBiggerThan) {
        this.modifiedTimeBiggerThan = modifiedTimeBiggerThan;
    }

    public LocalDateTime getModifiedTimeLowerThan() {
        return modifiedTimeLowerThan;
    }

    public void setModifiedTimeLowerThan(LocalDateTime modifiedTimeLowerThan) {
        this.modifiedTimeLowerThan = modifiedTimeLowerThan;
    }

    public Long getDeletedId() {
        return deletedId;
    }

    public void setDeletedId(Long deletedId) {
        this.deletedId = deletedId;
    }

    public String getDeletedName() {
        return deletedName;
    }

    public void setDeletedName(String deletedName) {
        this.deletedName = deletedName;
    }

    public LocalDateTime getDeletedTime() {
        return deletedTime;
    }

    public void setDeletedTime(LocalDateTime deletedTime) {
        this.deletedTime = deletedTime;
    }

    public LocalDateTime getDeletedTimeBiggerThan() {
        return deletedTimeBiggerThan;
    }

    public void setDeletedTimeBiggerThan(LocalDateTime deletedTimeBiggerThan) {
        this.deletedTimeBiggerThan = deletedTimeBiggerThan;
    }

    public LocalDateTime getDeletedTimeLowerThan() {
        return deletedTimeLowerThan;
    }

    public void setDeletedTimeLowerThan(LocalDateTime deletedTimeLowerThan) {
        this.deletedTimeLowerThan = deletedTimeLowerThan;
    }

    public Boolean getDeletedMark() {
        return deletedMark;
    }

    public void setDeletedMark(Boolean deletedMark) {
        this.deletedMark = deletedMark;
    }

    public List<Long> getBusinessIdList() {
        return businessIdList;
    }

    public void setBusinessIdList(List<Long> businessIdList) {
        this.businessIdList = businessIdList;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private ProcessBusinessCursta target;

        public Builder() {
            this.target = new ProcessBusinessCursta();
        }

        public ProcessBusinessCursta build() {
            return target;
        }

        public Builder id(Long id) {
            this.target.setId(id);
            return this;
        }

        public Builder idNot(Long id) {
            if (Objects.isNull(id)) {
                this.target.setId(id);
                return this;
            }
            this.target.setId(-1L * id);
            return this;
        }

        public Builder idList(List<Long> idList) {
            this.target.setIdList(idList);
            return this;
        }

        public Builder businessIdList(List<Long> idList) {
            this.target.businessIdList=idList;
            return this;
        }

        public Builder processName(String processName) {
            this.target.setProcessName(processName);
            return this;
        }

        public Builder processNameLike(String processName) {
            if (Objects.isNull(processName)) {
                this.target.setProcessName(processName);
                return this;
            }
            this.target.setProcessName(LikeParamExtension.PARAM_LEFT_LIKE + processName + LikeParamExtension.PARAM_RIGHT_LIKE);
            return this;
        }

        public Builder processNameLeftLike(String processName) {
            if (Objects.isNull(processName)) {
                this.target.setProcessName(processName);
                return this;
            }
            this.target.setProcessName(LikeParamExtension.PARAM_LEFT_LIKE + processName);
            return this;
        }

        public Builder processNameRightLike(String processName) {
            if (Objects.isNull(processName)) {
                this.target.setProcessName(processName);
                return this;
            }
            this.target.setProcessName(processName + LikeParamExtension.PARAM_RIGHT_LIKE);
            return this;
        }

        public Builder processNameNot(String processName) {
            if (Objects.isNull(processName)) {
                this.target.setProcessName(processName);
                return this;
            }
            this.target.setProcessName("-1*" + processName);
            return this;
        }

        public Builder businessId(Long businessId) {
            this.target.setBusinessId(businessId);
            return this;
        }

        public Builder businessIdNot(Long businessId) {
            if (Objects.isNull(businessId)) {
                this.target.setBusinessId(businessId);
                return this;
            }
            this.target.setBusinessId(-1L * businessId);
            return this;
        }

        public Builder curHistoryId(String curHistoryId) {
            this.target.setCurHistoryId(curHistoryId);
            return this;
        }

        public Builder curHistoryIdLike(String curHistoryId) {
            if (Objects.isNull(curHistoryId)) {
                this.target.setCurHistoryId(curHistoryId);
                return this;
            }
            this.target.setCurHistoryId(LikeParamExtension.PARAM_LEFT_LIKE + curHistoryId + LikeParamExtension.PARAM_RIGHT_LIKE);
            return this;
        }

        public Builder curHistoryIdLeftLike(String curHistoryId) {
            if (Objects.isNull(curHistoryId)) {
                this.target.setCurHistoryId(curHistoryId);
                return this;
            }
            this.target.setCurHistoryId(LikeParamExtension.PARAM_LEFT_LIKE + curHistoryId);
            return this;
        }

        public Builder curHistoryIdRightLike(String curHistoryId) {
            if (Objects.isNull(curHistoryId)) {
                this.target.setCurHistoryId(curHistoryId);
                return this;
            }
            this.target.setCurHistoryId(curHistoryId + LikeParamExtension.PARAM_RIGHT_LIKE);
            return this;
        }

        public Builder curHistoryIdNot(String curHistoryId) {
            if (Objects.isNull(curHistoryId)) {
                this.target.setCurHistoryId(curHistoryId);
                return this;
            }
            this.target.setCurHistoryId("-1*" + curHistoryId);
            return this;
        }

        public Builder createdId(Long createdId) {
            this.target.setCreatedId(createdId);
            return this;
        }

        public Builder createdIdNot(Long createdId) {
            if (Objects.isNull(createdId)) {
                this.target.setCreatedId(createdId);
                return this;
            }
            this.target.setCreatedId(-1L * createdId);
            return this;
        }

        public Builder createdName(String createdName) {
            this.target.setCreatedName(createdName);
            return this;
        }

        public Builder createdNameLike(String createdName) {
            if (Objects.isNull(createdName)) {
                this.target.setCreatedName(createdName);
                return this;
            }
            this.target.setCreatedName(LikeParamExtension.PARAM_LEFT_LIKE + createdName + LikeParamExtension.PARAM_RIGHT_LIKE);
            return this;
        }

        public Builder createdNameLeftLike(String createdName) {
            if (Objects.isNull(createdName)) {
                this.target.setCreatedName(createdName);
                return this;
            }
            this.target.setCreatedName(LikeParamExtension.PARAM_LEFT_LIKE + createdName);
            return this;
        }

        public Builder createdNameRightLike(String createdName) {
            if (Objects.isNull(createdName)) {
                this.target.setCreatedName(createdName);
                return this;
            }
            this.target.setCreatedName(createdName + LikeParamExtension.PARAM_RIGHT_LIKE);
            return this;
        }

        public Builder createdNameNot(String createdName) {
            if (Objects.isNull(createdName)) {
                this.target.setCreatedName(createdName);
                return this;
            }
            this.target.setCreatedName("-1*" + createdName);
            return this;
        }

        public Builder createdTime(LocalDateTime createdTime) {
            this.target.setCreatedTime(createdTime);
            return this;
        }

        public Builder createdTimeBiggerThan(LocalDateTime createdTime) {
            this.target.setCreatedTimeBiggerThan(createdTime);
            return this;
        }

        public Builder createdTimeLowerThan(LocalDateTime createdTime) {
            this.target.setCreatedTimeLowerThan(createdTime);
            return this;
        }

        public Builder modifiedId(Long modifiedId) {
            this.target.setModifiedId(modifiedId);
            return this;
        }

        public Builder modifiedIdNot(Long modifiedId) {
            if (Objects.isNull(modifiedId)) {
                this.target.setModifiedId(modifiedId);
                return this;
            }
            this.target.setModifiedId(-1L * modifiedId);
            return this;
        }

        public Builder modifiedName(String modifiedName) {
            this.target.setModifiedName(modifiedName);
            return this;
        }

        public Builder modifiedNameLike(String modifiedName) {
            if (Objects.isNull(modifiedName)) {
                this.target.setModifiedName(modifiedName);
                return this;
            }
            this.target.setModifiedName(LikeParamExtension.PARAM_LEFT_LIKE + modifiedName + LikeParamExtension.PARAM_RIGHT_LIKE);
            return this;
        }

        public Builder modifiedNameLeftLike(String modifiedName) {
            if (Objects.isNull(modifiedName)) {
                this.target.setModifiedName(modifiedName);
                return this;
            }
            this.target.setModifiedName(LikeParamExtension.PARAM_LEFT_LIKE + modifiedName);
            return this;
        }

        public Builder modifiedNameRightLike(String modifiedName) {
            if (Objects.isNull(modifiedName)) {
                this.target.setModifiedName(modifiedName);
                return this;
            }
            this.target.setModifiedName(modifiedName + LikeParamExtension.PARAM_RIGHT_LIKE);
            return this;
        }

        public Builder modifiedNameNot(String modifiedName) {
            if (Objects.isNull(modifiedName)) {
                this.target.setModifiedName(modifiedName);
                return this;
            }
            this.target.setModifiedName("-1*" + modifiedName);
            return this;
        }

        public Builder modifiedTime(LocalDateTime modifiedTime) {
            this.target.setModifiedTime(modifiedTime);
            return this;
        }

        public Builder modifiedTimeBiggerThan(LocalDateTime modifiedTime) {
            this.target.setModifiedTimeBiggerThan(modifiedTime);
            return this;
        }

        public Builder modifiedTimeLowerThan(LocalDateTime modifiedTime) {
            this.target.setModifiedTimeLowerThan(modifiedTime);
            return this;
        }

        public Builder deletedId(Long deletedId) {
            this.target.setDeletedId(deletedId);
            return this;
        }

        public Builder deletedIdNot(Long deletedId) {
            if (Objects.isNull(deletedId)) {
                this.target.setDeletedId(deletedId);
                return this;
            }
            this.target.setDeletedId(-1L * deletedId);
            return this;
        }

        public Builder deletedName(String deletedName) {
            this.target.setDeletedName(deletedName);
            return this;
        }

        public Builder deletedNameLike(String deletedName) {
            if (Objects.isNull(deletedName)) {
                this.target.setDeletedName(deletedName);
                return this;
            }
            this.target.setDeletedName(LikeParamExtension.PARAM_LEFT_LIKE + deletedName + LikeParamExtension.PARAM_RIGHT_LIKE);
            return this;
        }

        public Builder deletedNameLeftLike(String deletedName) {
            if (Objects.isNull(deletedName)) {
                this.target.setDeletedName(deletedName);
                return this;
            }
            this.target.setDeletedName(LikeParamExtension.PARAM_LEFT_LIKE + deletedName);
            return this;
        }

        public Builder deletedNameRightLike(String deletedName) {
            if (Objects.isNull(deletedName)) {
                this.target.setDeletedName(deletedName);
                return this;
            }
            this.target.setDeletedName(deletedName + LikeParamExtension.PARAM_RIGHT_LIKE);
            return this;
        }

        public Builder deletedNameNot(String deletedName) {
            if (Objects.isNull(deletedName)) {
                this.target.setDeletedName(deletedName);
                return this;
            }
            this.target.setDeletedName("-1*" + deletedName);
            return this;
        }

        public Builder deletedTime(LocalDateTime deletedTime) {
            this.target.setDeletedTime(deletedTime);
            return this;
        }

        public Builder deletedTimeBiggerThan(LocalDateTime deletedTime) {
            this.target.setDeletedTimeBiggerThan(deletedTime);
            return this;
        }

        public Builder deletedTimeLowerThan(LocalDateTime deletedTime) {
            this.target.setDeletedTimeLowerThan(deletedTime);
            return this;
        }

        public Builder deletedMark(Boolean deletedMark) {
            this.target.setDeletedMark(deletedMark);
            return this;
        }


    }
}
package com.chaojilaji.hyutils.db.model;

import com.chaojilaji.hyutils.dbcore.extension.like.LikeParamExtension;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.List;

//业务流程-发展历史
public class ProcessDevHistory {
    private Long id;         //记录标识 
 private List<Long> idList; //orm层需要，无实际意义
    private Long businessId;         //业务id 
    private Long processId;         //流程id 
    private Long curNodeId;         //当前节点id 
    private Integer curNodeSta;         //当前节点状态 
    private Long fromNodeId;         //出度节点（如果有） 
    private Long toNodeId;         //入度节点（如果有） 
    private Long sideId;         //边id 
    private Long fatherDevId;         //从哪个id传过来的 
    private Long beginTime;         //开始时间 
    private Long updateTime;         //更新时间 
    private Long endTime;         //结束时间 
    private String businessMetadata;         //业务元数据 
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
    public Long getBusinessId() {
        return businessId;
    }
    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }
    public Long getProcessId() {
        return processId;
    }
    public void setProcessId(Long processId) {
        this.processId = processId;
    }
    public Long getCurNodeId() {
        return curNodeId;
    }
    public void setCurNodeId(Long curNodeId) {
        this.curNodeId = curNodeId;
    }
    public Integer getCurNodeSta() {
        return curNodeSta;
    }
    public void setCurNodeSta(Integer curNodeSta) {
        this.curNodeSta = curNodeSta;
    }
    public Long getFromNodeId() {
        return fromNodeId;
    }
    public void setFromNodeId(Long fromNodeId) {
        this.fromNodeId = fromNodeId;
    }
    public Long getToNodeId() {
        return toNodeId;
    }
    public void setToNodeId(Long toNodeId) {
        this.toNodeId = toNodeId;
    }
    public Long getSideId() {
        return sideId;
    }
    public void setSideId(Long sideId) {
        this.sideId = sideId;
    }
    public Long getFatherDevId() {
        return fatherDevId;
    }
    public void setFatherDevId(Long fatherDevId) {
        this.fatherDevId = fatherDevId;
    }
    public Long getBeginTime() {
        return beginTime;
    }
    public void setBeginTime(Long beginTime) {
        this.beginTime = beginTime;
    }
    public Long getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
    public Long getEndTime() {
        return endTime;
    }
    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }
    public String getBusinessMetadata() {
        return businessMetadata;
    }
    public void setBusinessMetadata(String businessMetadata) {
        this.businessMetadata = businessMetadata;
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
    }    public Long getModifiedId() {
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
    }    public Long getDeletedId() {
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
    }    public Boolean getDeletedMark() {
        return deletedMark;
    }
    public void setDeletedMark(Boolean deletedMark) {
        this.deletedMark = deletedMark;
    }


public static Builder builder(){
        return new Builder();
    }

public static class Builder {
private ProcessDevHistory target;

public Builder() {
            this.target = new ProcessDevHistory();
        }

public ProcessDevHistory build() {
            return target;
        }public Builder id(Long id) {
            this.target.setId(id);
            return this;
        }
public Builder idNot(Long id) {
                        if (Objects.isNull(id)){
                                        this.target.setId(id);
                                        return this;
                                    }
                                    this.target.setId(-1L*id);
                                    return this;
                                }
public Builder idList(List<Long> idList) {
            this.target.setIdList(idList);
            return this;
        }
public Builder businessId(Long businessId) {
            this.target.setBusinessId(businessId);
            return this;
        }
public Builder businessIdNot(Long businessId) {
                        if (Objects.isNull(businessId)){
                                        this.target.setBusinessId(businessId);
                                        return this;
                                    }
                                    this.target.setBusinessId(-1L*businessId);
                                    return this;
                                }
public Builder processId(Long processId) {
            this.target.setProcessId(processId);
            return this;
        }
public Builder processIdNot(Long processId) {
                        if (Objects.isNull(processId)){
                                        this.target.setProcessId(processId);
                                        return this;
                                    }
                                    this.target.setProcessId(-1L*processId);
                                    return this;
                                }
public Builder curNodeId(Long curNodeId) {
            this.target.setCurNodeId(curNodeId);
            return this;
        }
public Builder curNodeIdNot(Long curNodeId) {
                        if (Objects.isNull(curNodeId)){
                                        this.target.setCurNodeId(curNodeId);
                                        return this;
                                    }
                                    this.target.setCurNodeId(-1L*curNodeId);
                                    return this;
                                }
public Builder curNodeSta(Integer curNodeSta) {
            this.target.setCurNodeSta(curNodeSta);
            return this;
        }
public Builder curNodeStaNot(Integer curNodeSta) {
                        if (Objects.isNull(curNodeSta)){
                                        this.target.setCurNodeSta(curNodeSta);
                                        return this;
                                    }
                                    this.target.setCurNodeSta(-1*curNodeSta);
                                    return this;
                                }
public Builder fromNodeId(Long fromNodeId) {
            this.target.setFromNodeId(fromNodeId);
            return this;
        }
public Builder fromNodeIdNot(Long fromNodeId) {
                        if (Objects.isNull(fromNodeId)){
                                        this.target.setFromNodeId(fromNodeId);
                                        return this;
                                    }
                                    this.target.setFromNodeId(-1L*fromNodeId);
                                    return this;
                                }
public Builder toNodeId(Long toNodeId) {
            this.target.setToNodeId(toNodeId);
            return this;
        }
public Builder toNodeIdNot(Long toNodeId) {
                        if (Objects.isNull(toNodeId)){
                                        this.target.setToNodeId(toNodeId);
                                        return this;
                                    }
                                    this.target.setToNodeId(-1L*toNodeId);
                                    return this;
                                }
public Builder sideId(Long sideId) {
            this.target.setSideId(sideId);
            return this;
        }
public Builder sideIdNot(Long sideId) {
                        if (Objects.isNull(sideId)){
                                        this.target.setSideId(sideId);
                                        return this;
                                    }
                                    this.target.setSideId(-1L*sideId);
                                    return this;
                                }
public Builder fatherDevId(Long fatherDevId) {
            this.target.setFatherDevId(fatherDevId);
            return this;
        }
public Builder fatherDevIdNot(Long fatherDevId) {
                        if (Objects.isNull(fatherDevId)){
                                        this.target.setFatherDevId(fatherDevId);
                                        return this;
                                    }
                                    this.target.setFatherDevId(-1L*fatherDevId);
                                    return this;
                                }
public Builder beginTime(Long beginTime) {
            this.target.setBeginTime(beginTime);
            return this;
        }
public Builder beginTimeNot(Long beginTime) {
                        if (Objects.isNull(beginTime)){
                                        this.target.setBeginTime(beginTime);
                                        return this;
                                    }
                                    this.target.setBeginTime(-1L*beginTime);
                                    return this;
                                }
public Builder updateTime(Long updateTime) {
            this.target.setUpdateTime(updateTime);
            return this;
        }
public Builder updateTimeNot(Long updateTime) {
                        if (Objects.isNull(updateTime)){
                                        this.target.setUpdateTime(updateTime);
                                        return this;
                                    }
                                    this.target.setUpdateTime(-1L*updateTime);
                                    return this;
                                }
public Builder endTime(Long endTime) {
            this.target.setEndTime(endTime);
            return this;
        }
public Builder endTimeNot(Long endTime) {
                        if (Objects.isNull(endTime)){
                                        this.target.setEndTime(endTime);
                                        return this;
                                    }
                                    this.target.setEndTime(-1L*endTime);
                                    return this;
                                }
public Builder businessMetadata(String businessMetadata) {
            this.target.setBusinessMetadata(businessMetadata);
            return this;
        }
public Builder businessMetadataLike(String businessMetadata) {
if (Objects.isNull(businessMetadata)){
                this.target.setBusinessMetadata(businessMetadata);
                return this;
            }
            this.target.setBusinessMetadata(LikeParamExtension.PARAM_LEFT_LIKE + businessMetadata + LikeParamExtension.PARAM_RIGHT_LIKE);
            return this;
        }

        public Builder businessMetadataLeftLike(String businessMetadata) {
if (Objects.isNull(businessMetadata)){
                this.target.setBusinessMetadata(businessMetadata);
                return this;
            }
            this.target.setBusinessMetadata(LikeParamExtension.PARAM_LEFT_LIKE + businessMetadata);
            return this;
        }

        public Builder businessMetadataRightLike(String businessMetadata) {
if (Objects.isNull(businessMetadata)){
                this.target.setBusinessMetadata(businessMetadata);
                return this;
            }
            this.target.setBusinessMetadata(businessMetadata + LikeParamExtension.PARAM_RIGHT_LIKE);
            return this;
        }
public Builder businessMetadataNot(String businessMetadata) {
                        if (Objects.isNull(businessMetadata)){
                                        this.target.setBusinessMetadata(businessMetadata);
                                        return this;
                                    }
                                    this.target.setBusinessMetadata("-1*"+businessMetadata);
                                    return this;
                                }
public Builder createdId(Long createdId) {
            this.target.setCreatedId(createdId);
            return this;
        }
public Builder createdIdNot(Long createdId) {
                        if (Objects.isNull(createdId)){
                                        this.target.setCreatedId(createdId);
                                        return this;
                                    }
                                    this.target.setCreatedId(-1L*createdId);
                                    return this;
                                }
public Builder createdName(String createdName) {
            this.target.setCreatedName(createdName);
            return this;
        }
public Builder createdNameLike(String createdName) {
if (Objects.isNull(createdName)){
                this.target.setCreatedName(createdName);
                return this;
            }
            this.target.setCreatedName(LikeParamExtension.PARAM_LEFT_LIKE + createdName + LikeParamExtension.PARAM_RIGHT_LIKE);
            return this;
        }

        public Builder createdNameLeftLike(String createdName) {
if (Objects.isNull(createdName)){
                this.target.setCreatedName(createdName);
                return this;
            }
            this.target.setCreatedName(LikeParamExtension.PARAM_LEFT_LIKE + createdName);
            return this;
        }

        public Builder createdNameRightLike(String createdName) {
if (Objects.isNull(createdName)){
                this.target.setCreatedName(createdName);
                return this;
            }
            this.target.setCreatedName(createdName + LikeParamExtension.PARAM_RIGHT_LIKE);
            return this;
        }
public Builder createdNameNot(String createdName) {
                        if (Objects.isNull(createdName)){
                                        this.target.setCreatedName(createdName);
                                        return this;
                                    }
                                    this.target.setCreatedName("-1*"+createdName);
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
                        if (Objects.isNull(modifiedId)){
                                        this.target.setModifiedId(modifiedId);
                                        return this;
                                    }
                                    this.target.setModifiedId(-1L*modifiedId);
                                    return this;
                                }
public Builder modifiedName(String modifiedName) {
            this.target.setModifiedName(modifiedName);
            return this;
        }
public Builder modifiedNameLike(String modifiedName) {
if (Objects.isNull(modifiedName)){
                this.target.setModifiedName(modifiedName);
                return this;
            }
            this.target.setModifiedName(LikeParamExtension.PARAM_LEFT_LIKE + modifiedName + LikeParamExtension.PARAM_RIGHT_LIKE);
            return this;
        }

        public Builder modifiedNameLeftLike(String modifiedName) {
if (Objects.isNull(modifiedName)){
                this.target.setModifiedName(modifiedName);
                return this;
            }
            this.target.setModifiedName(LikeParamExtension.PARAM_LEFT_LIKE + modifiedName);
            return this;
        }

        public Builder modifiedNameRightLike(String modifiedName) {
if (Objects.isNull(modifiedName)){
                this.target.setModifiedName(modifiedName);
                return this;
            }
            this.target.setModifiedName(modifiedName + LikeParamExtension.PARAM_RIGHT_LIKE);
            return this;
        }
public Builder modifiedNameNot(String modifiedName) {
                        if (Objects.isNull(modifiedName)){
                                        this.target.setModifiedName(modifiedName);
                                        return this;
                                    }
                                    this.target.setModifiedName("-1*"+modifiedName);
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
                        if (Objects.isNull(deletedId)){
                                        this.target.setDeletedId(deletedId);
                                        return this;
                                    }
                                    this.target.setDeletedId(-1L*deletedId);
                                    return this;
                                }
public Builder deletedName(String deletedName) {
            this.target.setDeletedName(deletedName);
            return this;
        }
public Builder deletedNameLike(String deletedName) {
if (Objects.isNull(deletedName)){
                this.target.setDeletedName(deletedName);
                return this;
            }
            this.target.setDeletedName(LikeParamExtension.PARAM_LEFT_LIKE + deletedName + LikeParamExtension.PARAM_RIGHT_LIKE);
            return this;
        }

        public Builder deletedNameLeftLike(String deletedName) {
if (Objects.isNull(deletedName)){
                this.target.setDeletedName(deletedName);
                return this;
            }
            this.target.setDeletedName(LikeParamExtension.PARAM_LEFT_LIKE + deletedName);
            return this;
        }

        public Builder deletedNameRightLike(String deletedName) {
if (Objects.isNull(deletedName)){
                this.target.setDeletedName(deletedName);
                return this;
            }
            this.target.setDeletedName(deletedName + LikeParamExtension.PARAM_RIGHT_LIKE);
            return this;
        }
public Builder deletedNameNot(String deletedName) {
                        if (Objects.isNull(deletedName)){
                                        this.target.setDeletedName(deletedName);
                                        return this;
                                    }
                                    this.target.setDeletedName("-1*"+deletedName);
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
package com.chaojilaji.hyutils.db.query;

import com.chaojilaji.hyutils.db.model.ProcessSide;
import com.chaojilaji.hyutils.dbcore.utils.Json;
import com.chaojilaji.hyutils.dbcore.instance.PostgreSQLBaseQuery;
import org.javatuples.Triplet;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class  ProcessSideQuery extends PostgreSQLBaseQuery<ProcessSide> {
    public ProcessSideQuery() {
        super();
        this.primaryKey = "id";
        this.fieldOrgName = "created_org_id";
    }

    public Long count(ProcessSide processSide) {
        return this.countModelBySimpleAnd(Json.toMap(Json.toJson(processSide)));
    }

    public Long count(List<Triplet<String, String, Object>> processSide) {
        return this.countModelByOperateSimpleAnd(processSide);
    }

    public List<ProcessSide> page(ProcessSide processSide, Integer page, Integer size) {
        return this.findListModelBySimpleAnd(Json.toMap(Json.toJson(processSide)), page, size);
    }
    
    public Integer updateByCondition(ProcessSide condition, ProcessSide value) {
        return this.update(Json.toMap(Json.toJson(condition)), Json.toMap(Json.toJson(value)));
    }
    public List<ProcessSide> findByCondition(ProcessSide condition) {
        return this.findListModelBySimpleAnd(Json.toMap(Json.toJson(condition)));
    }
// TODO: 2021/12/29 数据组相关的
    public List<ProcessSide> page(ProcessSide processSide, List<Long> orgIds, Integer page, Integer size) {
        return this.findListModelByOperateSimpleAnd(mergeCondition(Json.toMap(Json.toJson(processSide)), orgIds, this.fieldOrgName), page, size);
    }

    public Long count(ProcessSide processSide, List<Long> orgIds) {
        return this.countModelByOperateSimpleAnd(mergeCondition(Json.toMap(Json.toJson(processSide)), orgIds, this.fieldOrgName));
    }

    public Integer updateByCondition(ProcessSide condition, List<Long> orgIds, ProcessSide value) {
        return this.update(mergeCondition(Json.toMap(Json.toJson(condition)),orgIds,this.fieldOrgName), Json.toMap(Json.toJson(value)));
    }

    public List<ProcessSide> findByCondition(ProcessSide condition,List<Long> orgIds) {
        return this.findListModelByOperateSimpleAnd(mergeCondition(Json.toMap(Json.toJson(condition)),orgIds,this.fieldOrgName));
    }
// TODO: 2022/1/5 数据组 + owner_id 来实现
    public List<ProcessSide> findByCondition(ProcessSide condition, List<Long> orgIds, Long userId) {
        return this.findListModelByOperateSimpleAndWithOrgIds(Json.toMap(Json.toJson(condition)), orgIds, userId, this.fieldUserName, this.fieldOrgName, "*", null, null);
    }

    public List<ProcessSide> page(ProcessSide condition, List<Long> orgIds, Long userId, Integer page, Integer size) {
        return this.findListModelByOperateSimpleAndWithOrgIds(Json.toMap(Json.toJson(condition)), orgIds, userId, this.fieldUserName, this.fieldOrgName, "*", page, size);
    }

    public Long count(ProcessSide condition, List<Long> orgIds, Long userId) {
        return this.countByOperateSimpleAndWithOrgIds(Json.toMap(Json.toJson(condition)), orgIds, userId, this.fieldUserName, this.fieldOrgName);
    }

    public Integer updateByCondition(ProcessSide condition, List<Long> orgIds, Long userId, ProcessSide value) {
        return this.updateWithOrgIdsAndUserId(Json.toMap(Json.toJson(condition)), orgIds, userId, this.fieldUserName, this.fieldOrgName, Json.toMap(Json.toJson(value)));
    }

}
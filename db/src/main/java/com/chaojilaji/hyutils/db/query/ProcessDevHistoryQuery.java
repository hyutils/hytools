package com.chaojilaji.hyutils.db.query;

import com.chaojilaji.hyutils.db.model.ProcessDevHistory;
import com.chaojilaji.hyutils.dbcore.utils.Json;
import com.chaojilaji.hyutils.dbcore.instance.PostgreSQLBaseQuery;
import org.javatuples.Triplet;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class  ProcessDevHistoryQuery extends PostgreSQLBaseQuery<ProcessDevHistory> {
    public ProcessDevHistoryQuery() {
        super();
        this.primaryKey = "id";
        this.fieldOrgName = "created_org_id";
    }

    public Long count(ProcessDevHistory processDevHistory) {
        return this.countModelBySimpleAnd(Json.toMap(Json.toJson(processDevHistory)));
    }

    public Long count(List<Triplet<String, String, Object>> processDevHistory) {
        return this.countModelByOperateSimpleAnd(processDevHistory);
    }

    public List<ProcessDevHistory> page(ProcessDevHistory processDevHistory, Integer page, Integer size) {
        return this.findListModelBySimpleAnd(Json.toMap(Json.toJson(processDevHistory)), page, size);
    }
    
    public Integer updateByCondition(ProcessDevHistory condition, ProcessDevHistory value) {
        return this.update(Json.toMap(Json.toJson(condition)), Json.toMap(Json.toJson(value)));
    }
    public List<ProcessDevHistory> findByCondition(ProcessDevHistory condition) {
        return this.findListModelBySimpleAnd(Json.toMap(Json.toJson(condition)));
    }
// TODO: 2021/12/29 数据组相关的
    public List<ProcessDevHistory> page(ProcessDevHistory processDevHistory, List<Long> orgIds, Integer page, Integer size) {
        return this.findListModelByOperateSimpleAnd(mergeCondition(Json.toMap(Json.toJson(processDevHistory)), orgIds, this.fieldOrgName), page, size);
    }

    public Long count(ProcessDevHistory processDevHistory, List<Long> orgIds) {
        return this.countModelByOperateSimpleAnd(mergeCondition(Json.toMap(Json.toJson(processDevHistory)), orgIds, this.fieldOrgName));
    }

    public Integer updateByCondition(ProcessDevHistory condition, List<Long> orgIds, ProcessDevHistory value) {
        return this.update(mergeCondition(Json.toMap(Json.toJson(condition)),orgIds,this.fieldOrgName), Json.toMap(Json.toJson(value)));
    }

    public List<ProcessDevHistory> findByCondition(ProcessDevHistory condition,List<Long> orgIds) {
        return this.findListModelByOperateSimpleAnd(mergeCondition(Json.toMap(Json.toJson(condition)),orgIds,this.fieldOrgName));
    }
// TODO: 2022/1/5 数据组 + owner_id 来实现
    public List<ProcessDevHistory> findByCondition(ProcessDevHistory condition, List<Long> orgIds, Long userId) {
        return this.findListModelByOperateSimpleAndWithOrgIds(Json.toMap(Json.toJson(condition)), orgIds, userId, this.fieldUserName, this.fieldOrgName, "*", null, null);
    }

    public List<ProcessDevHistory> page(ProcessDevHistory condition, List<Long> orgIds, Long userId, Integer page, Integer size) {
        return this.findListModelByOperateSimpleAndWithOrgIds(Json.toMap(Json.toJson(condition)), orgIds, userId, this.fieldUserName, this.fieldOrgName, "*", page, size);
    }

    public Long count(ProcessDevHistory condition, List<Long> orgIds, Long userId) {
        return this.countByOperateSimpleAndWithOrgIds(Json.toMap(Json.toJson(condition)), orgIds, userId, this.fieldUserName, this.fieldOrgName);
    }

    public Integer updateByCondition(ProcessDevHistory condition, List<Long> orgIds, Long userId, ProcessDevHistory value) {
        return this.updateWithOrgIdsAndUserId(Json.toMap(Json.toJson(condition)), orgIds, userId, this.fieldUserName, this.fieldOrgName, Json.toMap(Json.toJson(value)));
    }

}
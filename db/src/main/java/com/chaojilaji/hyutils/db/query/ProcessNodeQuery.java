package com.chaojilaji.hyutils.db.query;

import com.chaojilaji.hyutils.db.model.ProcessNode;
import com.chaojilaji.hyutils.dbcore.utils.Json;
import com.chaojilaji.hyutils.dbcore.instance.PostgreSQLBaseQuery;
import org.javatuples.Triplet;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class  ProcessNodeQuery extends PostgreSQLBaseQuery<ProcessNode> {
    public ProcessNodeQuery() {
        super();
        this.primaryKey = "id";
        this.fieldOrgName = "created_org_id";
    }

    public Long count(ProcessNode processNode) {
        return this.countModelBySimpleAnd(Json.toMap(Json.toJson(processNode)));
    }

    public Long count(List<Triplet<String, String, Object>> processNode) {
        return this.countModelByOperateSimpleAnd(processNode);
    }

    public List<ProcessNode> page(ProcessNode processNode, Integer page, Integer size) {
        return this.findListModelBySimpleAnd(Json.toMap(Json.toJson(processNode)), page, size);
    }
    
    public Integer updateByCondition(ProcessNode condition, ProcessNode value) {
        return this.update(Json.toMap(Json.toJson(condition)), Json.toMap(Json.toJson(value)));
    }
    public List<ProcessNode> findByCondition(ProcessNode condition) {
        return this.findListModelBySimpleAnd(Json.toMap(Json.toJson(condition)));
    }
// TODO: 2021/12/29 数据组相关的
    public List<ProcessNode> page(ProcessNode processNode, List<Long> orgIds, Integer page, Integer size) {
        return this.findListModelByOperateSimpleAnd(mergeCondition(Json.toMap(Json.toJson(processNode)), orgIds, this.fieldOrgName), page, size);
    }

    public Long count(ProcessNode processNode, List<Long> orgIds) {
        return this.countModelByOperateSimpleAnd(mergeCondition(Json.toMap(Json.toJson(processNode)), orgIds, this.fieldOrgName));
    }

    public Integer updateByCondition(ProcessNode condition, List<Long> orgIds, ProcessNode value) {
        return this.update(mergeCondition(Json.toMap(Json.toJson(condition)),orgIds,this.fieldOrgName), Json.toMap(Json.toJson(value)));
    }

    public List<ProcessNode> findByCondition(ProcessNode condition,List<Long> orgIds) {
        return this.findListModelByOperateSimpleAnd(mergeCondition(Json.toMap(Json.toJson(condition)),orgIds,this.fieldOrgName));
    }
// TODO: 2022/1/5 数据组 + owner_id 来实现
    public List<ProcessNode> findByCondition(ProcessNode condition, List<Long> orgIds, Long userId) {
        return this.findListModelByOperateSimpleAndWithOrgIds(Json.toMap(Json.toJson(condition)), orgIds, userId, this.fieldUserName, this.fieldOrgName, "*", null, null);
    }

    public List<ProcessNode> page(ProcessNode condition, List<Long> orgIds, Long userId, Integer page, Integer size) {
        return this.findListModelByOperateSimpleAndWithOrgIds(Json.toMap(Json.toJson(condition)), orgIds, userId, this.fieldUserName, this.fieldOrgName, "*", page, size);
    }

    public Long count(ProcessNode condition, List<Long> orgIds, Long userId) {
        return this.countByOperateSimpleAndWithOrgIds(Json.toMap(Json.toJson(condition)), orgIds, userId, this.fieldUserName, this.fieldOrgName);
    }

    public Integer updateByCondition(ProcessNode condition, List<Long> orgIds, Long userId, ProcessNode value) {
        return this.updateWithOrgIdsAndUserId(Json.toMap(Json.toJson(condition)), orgIds, userId, this.fieldUserName, this.fieldOrgName, Json.toMap(Json.toJson(value)));
    }

}
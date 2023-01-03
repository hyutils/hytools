package com.chaojilaji.hyutils.db.query;

import com.chaojilaji.hyutils.db.model.ProcessTree;
import com.chaojilaji.hyutils.dbcore.utils.Json;
import com.chaojilaji.hyutils.dbcore.instance.PostgreSQLBaseQuery;
import org.javatuples.Triplet;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class  ProcessTreeQuery extends PostgreSQLBaseQuery<ProcessTree> {
    public ProcessTreeQuery() {
        super();
        this.primaryKey = "id";
        this.fieldOrgName = "created_org_id";
    }

    public Long count(ProcessTree processTree) {
        return this.countModelBySimpleAnd(Json.toMap(Json.toJson(processTree)));
    }

    public Long count(List<Triplet<String, String, Object>> processTree) {
        return this.countModelByOperateSimpleAnd(processTree);
    }

    public List<ProcessTree> page(ProcessTree processTree, Integer page, Integer size) {
        return this.findListModelBySimpleAnd(Json.toMap(Json.toJson(processTree)), page, size);
    }
    
    public Integer updateByCondition(ProcessTree condition, ProcessTree value) {
        return this.update(Json.toMap(Json.toJson(condition)), Json.toMap(Json.toJson(value)));
    }
    public List<ProcessTree> findByCondition(ProcessTree condition) {
        return this.findListModelBySimpleAnd(Json.toMap(Json.toJson(condition)));
    }
// TODO: 2021/12/29 数据组相关的
    public List<ProcessTree> page(ProcessTree processTree, List<Long> orgIds, Integer page, Integer size) {
        return this.findListModelByOperateSimpleAnd(mergeCondition(Json.toMap(Json.toJson(processTree)), orgIds, this.fieldOrgName), page, size);
    }

    public Long count(ProcessTree processTree, List<Long> orgIds) {
        return this.countModelByOperateSimpleAnd(mergeCondition(Json.toMap(Json.toJson(processTree)), orgIds, this.fieldOrgName));
    }

    public Integer updateByCondition(ProcessTree condition, List<Long> orgIds, ProcessTree value) {
        return this.update(mergeCondition(Json.toMap(Json.toJson(condition)),orgIds,this.fieldOrgName), Json.toMap(Json.toJson(value)));
    }

    public List<ProcessTree> findByCondition(ProcessTree condition,List<Long> orgIds) {
        return this.findListModelByOperateSimpleAnd(mergeCondition(Json.toMap(Json.toJson(condition)),orgIds,this.fieldOrgName));
    }
// TODO: 2022/1/5 数据组 + owner_id 来实现
    public List<ProcessTree> findByCondition(ProcessTree condition, List<Long> orgIds, Long userId) {
        return this.findListModelByOperateSimpleAndWithOrgIds(Json.toMap(Json.toJson(condition)), orgIds, userId, this.fieldUserName, this.fieldOrgName, "*", null, null);
    }

    public List<ProcessTree> page(ProcessTree condition, List<Long> orgIds, Long userId, Integer page, Integer size) {
        return this.findListModelByOperateSimpleAndWithOrgIds(Json.toMap(Json.toJson(condition)), orgIds, userId, this.fieldUserName, this.fieldOrgName, "*", page, size);
    }

    public Long count(ProcessTree condition, List<Long> orgIds, Long userId) {
        return this.countByOperateSimpleAndWithOrgIds(Json.toMap(Json.toJson(condition)), orgIds, userId, this.fieldUserName, this.fieldOrgName);
    }

    public Integer updateByCondition(ProcessTree condition, List<Long> orgIds, Long userId, ProcessTree value) {
        return this.updateWithOrgIdsAndUserId(Json.toMap(Json.toJson(condition)), orgIds, userId, this.fieldUserName, this.fieldOrgName, Json.toMap(Json.toJson(value)));
    }

}
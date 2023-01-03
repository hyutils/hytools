package com.chaojilaji.hyutils.db.query;

import com.chaojilaji.hyutils.db.model.ProcessBusinessCursta;
import com.chaojilaji.hyutils.dbcore.utils.Json;
import com.chaojilaji.hyutils.dbcore.instance.PostgreSQLBaseQuery;
import org.javatuples.Triplet;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class  ProcessBusinessCurstaQuery extends PostgreSQLBaseQuery<ProcessBusinessCursta> {
    public ProcessBusinessCurstaQuery() {
        super();
        this.primaryKey = "id";
        this.fieldOrgName = "created_org_id";
    }

    public Long count(ProcessBusinessCursta processBusinessCursta) {
        return this.countModelBySimpleAnd(Json.toMap(Json.toJson(processBusinessCursta)));
    }

    public Long count(List<Triplet<String, String, Object>> processBusinessCursta) {
        return this.countModelByOperateSimpleAnd(processBusinessCursta);
    }

    public List<ProcessBusinessCursta> page(ProcessBusinessCursta processBusinessCursta, Integer page, Integer size) {
        return this.findListModelBySimpleAnd(Json.toMap(Json.toJson(processBusinessCursta)), page, size);
    }
    
    public Integer updateByCondition(ProcessBusinessCursta condition, ProcessBusinessCursta value) {
        return this.update(Json.toMap(Json.toJson(condition)), Json.toMap(Json.toJson(value)));
    }
    public List<ProcessBusinessCursta> findByCondition(ProcessBusinessCursta condition) {
        return this.findListModelBySimpleAnd(Json.toMap(Json.toJson(condition)));
    }
// TODO: 2021/12/29 数据组相关的
    public List<ProcessBusinessCursta> page(ProcessBusinessCursta processBusinessCursta, List<Long> orgIds, Integer page, Integer size) {
        return this.findListModelByOperateSimpleAnd(mergeCondition(Json.toMap(Json.toJson(processBusinessCursta)), orgIds, this.fieldOrgName), page, size);
    }

    public Long count(ProcessBusinessCursta processBusinessCursta, List<Long> orgIds) {
        return this.countModelByOperateSimpleAnd(mergeCondition(Json.toMap(Json.toJson(processBusinessCursta)), orgIds, this.fieldOrgName));
    }

    public Integer updateByCondition(ProcessBusinessCursta condition, List<Long> orgIds, ProcessBusinessCursta value) {
        return this.update(mergeCondition(Json.toMap(Json.toJson(condition)),orgIds,this.fieldOrgName), Json.toMap(Json.toJson(value)));
    }

    public List<ProcessBusinessCursta> findByCondition(ProcessBusinessCursta condition,List<Long> orgIds) {
        return this.findListModelByOperateSimpleAnd(mergeCondition(Json.toMap(Json.toJson(condition)),orgIds,this.fieldOrgName));
    }
// TODO: 2022/1/5 数据组 + owner_id 来实现
    public List<ProcessBusinessCursta> findByCondition(ProcessBusinessCursta condition, List<Long> orgIds, Long userId) {
        return this.findListModelByOperateSimpleAndWithOrgIds(Json.toMap(Json.toJson(condition)), orgIds, userId, this.fieldUserName, this.fieldOrgName, "*", null, null);
    }

    public List<ProcessBusinessCursta> page(ProcessBusinessCursta condition, List<Long> orgIds, Long userId, Integer page, Integer size) {
        return this.findListModelByOperateSimpleAndWithOrgIds(Json.toMap(Json.toJson(condition)), orgIds, userId, this.fieldUserName, this.fieldOrgName, "*", page, size);
    }

    public Long count(ProcessBusinessCursta condition, List<Long> orgIds, Long userId) {
        return this.countByOperateSimpleAndWithOrgIds(Json.toMap(Json.toJson(condition)), orgIds, userId, this.fieldUserName, this.fieldOrgName);
    }

    public Integer updateByCondition(ProcessBusinessCursta condition, List<Long> orgIds, Long userId, ProcessBusinessCursta value) {
        return this.updateWithOrgIdsAndUserId(Json.toMap(Json.toJson(condition)), orgIds, userId, this.fieldUserName, this.fieldOrgName, Json.toMap(Json.toJson(value)));
    }

}
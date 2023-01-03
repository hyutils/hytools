package com.chaojilaji.hyutils.db.repository;

import com.chaojilaji.hyutils.db.model.ProcessDevHistory;
import com.chaojilaji.hyutils.db.query.ProcessDevHistoryQuery;
import com.chaojilaji.hyutils.dbcore.utils.Json;
import org.javatuples.Triplet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;
import com.chaojilaji.hyutils.dbcore.syntaxtree.AndWhereSyntaxTree;
import com.chaojilaji.hyutils.dbcore.syntaxtree.WhereSyntaxTree;
import org.springframework.util.StringUtils;
import com.chaojilaji.hyutils.dbcore.extension.visible.VisibleUser;

public interface ProcessDevHistoryRepository {

    default ProcessDevHistory findProcessDevHistoryById(Long id) {
        ProcessDevHistoryQuery query = new ProcessDevHistoryQuery();
        return query.findModelById(id);
    }

    default ProcessDevHistory findProcessDevHistoryByCondition(ProcessDevHistory condition) {
        String json = Json.toJson(condition);
        Map<String, Object> andCondition = Json.toMap(json);
        ProcessDevHistoryQuery query = new ProcessDevHistoryQuery();
        return query.findModelBySimpleAnd(andCondition);
    }

    default Long save(ProcessDevHistory processDevHistory) {
        ProcessDevHistoryQuery query = new ProcessDevHistoryQuery();
        return query.insert(Json.toMap(Json.toJson(processDevHistory)));
    }


    default Integer saveAll(List<ProcessDevHistory> processDevHistorys) {
        List<Map<String, Object>> processDevHistoryMaps = new ArrayList<>();
        for (ProcessDevHistory processDevHistory : processDevHistorys) {
            processDevHistoryMaps.add(Json.toMap(Json.toJson(processDevHistory)));
        }
        ProcessDevHistoryQuery query = new ProcessDevHistoryQuery();
        return query.batchInsert(processDevHistoryMaps);
    }

    default List<ProcessDevHistory> findAll() {
        ProcessDevHistoryQuery query = new ProcessDevHistoryQuery();
        List<Map<String, Object>> tmp = query.findAll();
        List<ProcessDevHistory> ans = new ArrayList<>();
        for (Map<String, Object> x : tmp) {
            ans.add(Json.toObject(Json.toJson(x), ProcessDevHistory.class));
        }
        return ans;
    }

    default Integer update(ProcessDevHistory condition, ProcessDevHistory value) {
        ProcessDevHistoryQuery query = new ProcessDevHistoryQuery();
        return query.updateByCondition(condition, value);
    }
    default Integer updateById(Long id, ProcessDevHistory value) {
        ProcessDevHistoryQuery query = new ProcessDevHistoryQuery();
        return query.updateById(id, Json.toMap(Json.toJson(value)));
    }

    default Long count(ProcessDevHistory processDevHistory) {
        ProcessDevHistoryQuery processDevHistoryQuery = new ProcessDevHistoryQuery();
        return processDevHistoryQuery.count(processDevHistory);
    }

    default Long count(List<Triplet<String, String, Object>> processDevHistory) {
        ProcessDevHistoryQuery processDevHistoryQuery = new ProcessDevHistoryQuery();
        return processDevHistoryQuery.count(processDevHistory);
    }

    default List<ProcessDevHistory> page(ProcessDevHistory processDevHistory, Integer page, Integer size) {
        ProcessDevHistoryQuery processDevHistoryQuery = new ProcessDevHistoryQuery();
        return processDevHistoryQuery.page(processDevHistory, page, size);
    }

    default List<ProcessDevHistory> page(List<Triplet<String, String, Object>> processDevHistory, Integer page, Integer size) {
        ProcessDevHistoryQuery processDevHistoryQuery = new ProcessDevHistoryQuery();
        return processDevHistoryQuery.findListModelByOperateSimpleAnd(processDevHistory, page, size);
    }
    default List<ProcessDevHistory> findAllByCondition(ProcessDevHistory condition){
        ProcessDevHistoryQuery query = new ProcessDevHistoryQuery();
        return query.findByCondition(condition);
    }
    default List<ProcessDevHistory> findAllWithIn(Map<String, List<Triplet<String, String, Object>>> inMaps) {
        ProcessDevHistoryQuery query = new ProcessDevHistoryQuery();
        Map<String, Object> andCondition = new HashMap<>();
        andCondition.put("tag", 0);
        WhereSyntaxTree whereSyntaxTree = new WhereSyntaxTree();
        for (Map.Entry<String, List<Triplet<String, String, Object>>> map : inMaps.entrySet()) {
            List<Triplet<String, String, Object>> orCondition = map.getValue();
            andCondition.put(map.getKey(), whereSyntaxTree.createOrTreeByOperate(orCondition));
        }
        AndWhereSyntaxTree andWhereSyntaxTree = whereSyntaxTree.createAndTree(andCondition);
        List<Map<String, Object>> ans = query.find("*").where(andWhereSyntaxTree).listMapGet();
        List<ProcessDevHistory> processDevHistorys = new ArrayList<>();
        for (Map<String, Object> x : ans) {
            processDevHistorys.add(Json.toObject(Json.toJson(x),ProcessDevHistory.class));
        }
        return processDevHistorys;
    }
    default ProcessDevHistory findProcessDevHistoryByIdWithCache(Long id) {
        ProcessDevHistoryQuery query = new ProcessDevHistoryQuery();
        String tmp = query.getFromCache("ProcessDevHistoryRepository", "findProcessDevHistoryByIdWithCache", new HashMap<String, Object>() {
            {
                put("id", id);
            }
        });
        if (StringUtils.hasText(tmp)) {
            return Json.toObject(tmp, ProcessDevHistory.class);
        } else {
            ProcessDevHistory processDevHistory = query.findModelById(id);
            if (Objects.nonNull(processDevHistory)) {
                query.putToCache("ProcessDevHistoryRepository", "findProcessDevHistoryByIdWithCache", new HashMap<String, Object>() {
                    {
                        put("id", id);
                    }
                }, Json.toJson(processDevHistory));
            }
            return processDevHistory;
        }
    }

    default ProcessDevHistory findProcessDevHistoryByConditionWithCache(ProcessDevHistory condition) {
        String json = Json.toJson(condition);
        Map<String, Object> andCondition = Json.toMap(json);
        ProcessDevHistoryQuery query = new ProcessDevHistoryQuery();
        String tmp = query.getFromCache("ProcessDevHistoryRepository", "findProcessDevHistoryByConditionWithCache", andCondition);
        if (StringUtils.hasText(tmp)) {
            return Json.toObject(tmp, ProcessDevHistory.class);
        } else {
            ProcessDevHistory processDevHistory = query.findModelBySimpleAnd(andCondition);
            if (Objects.nonNull(processDevHistory)) {
                query.putToCache("processDevHistoryRepository","findProcessDevHistoryByConditionWithCache",andCondition,Json.toJson(processDevHistory));
            }
            return processDevHistory;
        }
    }
// TODO: 2021/12/29 数据组相关
    default List<ProcessDevHistory> findAllByCondition(ProcessDevHistory condition, List<Long> orgIds) {
        ProcessDevHistoryQuery query = new ProcessDevHistoryQuery();
        return query.findByCondition(condition, orgIds);
    }

    default ProcessDevHistory findSysDataAuthByCondition(ProcessDevHistory condition, List<Long> orgIds) {
        String json = Json.toJson(condition);
        Map<String, Object> andCondition = Json.toMap(json);
        ProcessDevHistoryQuery query = new ProcessDevHistoryQuery();
        return query.findModelByOperateSimpleAnd(query.mergeCondition(andCondition, orgIds, query.fieldOrgName));
    }

    default List<ProcessDevHistory> findAll(List<Long> orgIds) {
        ProcessDevHistoryQuery query = new ProcessDevHistoryQuery();
        return query.findByCondition(new ProcessDevHistory(), orgIds);
    }

    default Integer update(ProcessDevHistory condition, List<Long> orgIds, ProcessDevHistory value) {
        ProcessDevHistoryQuery query = new ProcessDevHistoryQuery();
        return query.updateByCondition(condition, orgIds, value);
    }

    default Long count(ProcessDevHistory processDevHistory, List<Long> orgIds) {
        ProcessDevHistoryQuery processDevHistoryQuery = new ProcessDevHistoryQuery();
        return processDevHistoryQuery.count(processDevHistory, orgIds);
    }

    default List<ProcessDevHistory> page(ProcessDevHistory processDevHistory, List<Long> orgIds, Integer page, Integer size) {
        ProcessDevHistoryQuery processDevHistoryQuery = new ProcessDevHistoryQuery();
        return processDevHistoryQuery.page(processDevHistory, orgIds, page, size);
    }
    // TODO: 2022/1/6 数据组 + owner_id作为条件 
    default List<ProcessDevHistory> findAllByCondition(ProcessDevHistory condition, List<Long> orgIds, Long ownerId) {
        ProcessDevHistoryQuery query = new ProcessDevHistoryQuery();
        return query.findByCondition(condition, orgIds, ownerId);
    }

    default ProcessDevHistory findProcessDevHistoryByCondition(ProcessDevHistory condition, List<Long> orgIds, Long ownerId) {
        ProcessDevHistoryQuery query = new ProcessDevHistoryQuery();
        List<ProcessDevHistory> processDevHistorys = query.page(condition, orgIds, ownerId, 1, 1);
        if (processDevHistorys.size() != 0) {
            return processDevHistorys.get(0);
        }
        return null;
    }

    default List<ProcessDevHistory> findAll(List<Long> orgIds, Long ownerId) {
        ProcessDevHistoryQuery query = new ProcessDevHistoryQuery();
        return query.findByCondition(new ProcessDevHistory(), orgIds, ownerId);
    }

    default Integer update(ProcessDevHistory condition, List<Long> orgIds, Long ownerId, ProcessDevHistory value) {
        ProcessDevHistoryQuery query = new ProcessDevHistoryQuery();
        return query.updateByCondition(condition, orgIds, ownerId, value);
    }

    default Long count(ProcessDevHistory processDevHistory, List<Long> orgIds, Long ownerId) {
        ProcessDevHistoryQuery processDevHistoryQuery = new ProcessDevHistoryQuery();
        return processDevHistoryQuery.count(processDevHistory, orgIds, ownerId);
    }

    default List<ProcessDevHistory> page(ProcessDevHistory processDevHistory, List<Long> orgIds, Long ownerId, Integer page, Integer size) {
        ProcessDevHistoryQuery processDevHistoryQuery = new ProcessDevHistoryQuery();
        return processDevHistoryQuery.page(processDevHistory, orgIds, ownerId, page, size);
    }

// todo -- 可见范围相关，与数据组类似
    default List<ProcessDevHistory> page(ProcessDevHistory processDevHistory, VisibleUser visibleUser, Integer page, Integer size) {
        ProcessDevHistoryQuery query = new ProcessDevHistoryQuery();
        return query.page(processDevHistory,visibleUser.getOrgIds(),visibleUser.getId(),page,size);
    }

    default Long count(ProcessDevHistory processDevHistory, VisibleUser visibleUser) {
        ProcessDevHistoryQuery processDevHistoryQuery = new ProcessDevHistoryQuery();
        return processDevHistoryQuery.count(processDevHistory, visibleUser.getOrgIds(),visibleUser.getId());
    }

    default List<ProcessDevHistory> findAll(VisibleUser visibleUser) {
        ProcessDevHistoryQuery query = new ProcessDevHistoryQuery();
        return query.findByCondition(new ProcessDevHistory(), visibleUser.getOrgIds(), visibleUser.getId());
    }

    default ProcessDevHistory findProcessDevHistoryByCondition(ProcessDevHistory condition, VisibleUser visibleUser) {
        ProcessDevHistoryQuery query = new ProcessDevHistoryQuery();
        List<ProcessDevHistory> processDevHistory = query.page(condition, visibleUser.getOrgIds(),visibleUser.getId(), 1, 1);
        if (processDevHistory.size() != 0) {
            return processDevHistory.get(0);
        }
        return null;
    }

    default List<ProcessDevHistory> findAllByCondition(ProcessDevHistory condition, VisibleUser visibleUser) {
        ProcessDevHistoryQuery query = new ProcessDevHistoryQuery();
        return query.findByCondition(condition, visibleUser.getOrgIds(),visibleUser.getId());
    }
// like 相关 
default ProcessDevHistory findProcessDevHistoryByLikeCondition(ProcessDevHistory condition) {
        ProcessDevHistoryQuery query = new ProcessDevHistoryQuery();
        String json = Json.toJson(condition);
        Map<String, Object> andCondition = Json.toMap(json);
        List<Triplet<String, String, Object>> params = new ArrayList<>();
        for (Map.Entry<String, Object> x : andCondition.entrySet()) {
            if (Objects.nonNull(x.getValue())) {
                params.add(new Triplet<>(x.getKey(), "like", "%" + x.getValue() + "%"));
            }
        }
        return query.findModelByOperateSimpleAnd(params);
    }

    default ProcessDevHistory findProcessDevHistoryByLikeConditionAndEqualCondition(ProcessDevHistory likeCondition, ProcessDevHistory equalCondition) {
        ProcessDevHistoryQuery query = new ProcessDevHistoryQuery();
        String json = Json.toJson(likeCondition);
        Map<String, Object> andCondition = Json.toMap(json);
        List<Triplet<String, String, Object>> params = new ArrayList<>();
        for (Map.Entry<String, Object> x : andCondition.entrySet()) {
            if (Objects.nonNull(x.getValue())) {
                params.add(new Triplet<>(x.getKey(), "like", "%" + x.getValue() + "%"));
            }
        }
        Map<String, Object> andCondition2 = Json.toMap(Json.toJson(equalCondition));
        for (Map.Entry<String, Object> x : andCondition2.entrySet()) {
            if (Objects.nonNull(x.getValue())) {
                params.add(new Triplet<>(x.getKey(), "=", x.getValue()));
            }
        }
        return query.findModelByOperateSimpleAnd(params);
    }

    default List<ProcessDevHistory> pageWithLike(ProcessDevHistory processDevHistory, Integer page, Integer size) {
        ProcessDevHistoryQuery query = new ProcessDevHistoryQuery();
        String json = Json.toJson(processDevHistory);
        Map<String, Object> andCondition = Json.toMap(json);
        List<Triplet<String, String, Object>> params = new ArrayList<>();
        for (Map.Entry<String, Object> x : andCondition.entrySet()) {
            if (Objects.nonNull(x.getValue())) {
                params.add(new Triplet<>(x.getKey(), "like", "%" + x.getValue() + "%"));
            }
        }
        return query.findListModelByOperateSimpleAnd(params, page, size);
    }

    default List<ProcessDevHistory> pageWithLikeAndEqual(ProcessDevHistory likeProcessDevHistory, ProcessDevHistory equalProcessDevHistory, Integer page, Integer size) {
        ProcessDevHistoryQuery query = new ProcessDevHistoryQuery();
        String json = Json.toJson(likeProcessDevHistory);
        Map<String, Object> andCondition = Json.toMap(json);
        List<Triplet<String, String, Object>> params = new ArrayList<>();
        for (Map.Entry<String, Object> x : andCondition.entrySet()) {
            if (Objects.nonNull(x.getValue())) {
                params.add(new Triplet<>(x.getKey(), "like", "%" + x.getValue() + "%"));
            }
        }
        Map<String, Object> andCondition2 = Json.toMap(Json.toJson(equalProcessDevHistory));
        for (Map.Entry<String, Object> x : andCondition2.entrySet()) {
            if (Objects.nonNull(x.getValue())) {
                params.add(new Triplet<>(x.getKey(), "=", x.getValue()));
            }
        }
        return query.findListModelByOperateSimpleAnd(params, page, size);
    }

    default Long countWithLike(ProcessDevHistory processDevHistory) {
        ProcessDevHistoryQuery query = new ProcessDevHistoryQuery();
        String json = Json.toJson(processDevHistory);
        Map<String, Object> andCondition = Json.toMap(json);
        List<Triplet<String, String, Object>> params = new ArrayList<>();
        for (Map.Entry<String, Object> x : andCondition.entrySet()) {
            if (Objects.nonNull(x.getValue())) {
                params.add(new Triplet<>(x.getKey(), "like", "%" + x.getValue() + "%"));
            }
        }
        return query.count(params);
    }

    default Long countWithLikeAndEqual(ProcessDevHistory likeProcessDevHistory, ProcessDevHistory equalProcessDevHistory) {
        ProcessDevHistoryQuery query = new ProcessDevHistoryQuery();
        String json = Json.toJson(likeProcessDevHistory);
        Map<String, Object> andCondition = Json.toMap(json);
        List<Triplet<String, String, Object>> params = new ArrayList<>();
        for (Map.Entry<String, Object> x : andCondition.entrySet()) {
            if (Objects.nonNull(x.getValue())) {
                params.add(new Triplet<>(x.getKey(), "like", "%" + x.getValue() + "%"));
            }
        }
        Map<String, Object> andCondition2 = Json.toMap(Json.toJson(equalProcessDevHistory));
        for (Map.Entry<String, Object> x : andCondition2.entrySet()) {
            if (Objects.nonNull(x.getValue())) {
                params.add(new Triplet<>(x.getKey(), "=", x.getValue()));
            }
        }
        return query.count(params);
    }}
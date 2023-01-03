package com.chaojilaji.hyutils.db.repository;

import com.chaojilaji.hyutils.db.model.ProcessTree;
import com.chaojilaji.hyutils.db.query.ProcessTreeQuery;
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

public interface ProcessTreeRepository {

    default ProcessTree findProcessTreeById(Long id) {
        ProcessTreeQuery query = new ProcessTreeQuery();
        return query.findModelById(id);
    }

    default ProcessTree findProcessTreeByCondition(ProcessTree condition) {
        String json = Json.toJson(condition);
        Map<String, Object> andCondition = Json.toMap(json);
        ProcessTreeQuery query = new ProcessTreeQuery();
        return query.findModelBySimpleAnd(andCondition);
    }

    default Long save(ProcessTree processTree) {
        ProcessTreeQuery query = new ProcessTreeQuery();
        return query.insert(Json.toMap(Json.toJson(processTree)));
    }


    default Integer saveAll(List<ProcessTree> processTrees) {
        List<Map<String, Object>> processTreeMaps = new ArrayList<>();
        for (ProcessTree processTree : processTrees) {
            processTreeMaps.add(Json.toMap(Json.toJson(processTree)));
        }
        ProcessTreeQuery query = new ProcessTreeQuery();
        return query.batchInsert(processTreeMaps);
    }

    default List<ProcessTree> findAll() {
        ProcessTreeQuery query = new ProcessTreeQuery();
        List<Map<String, Object>> tmp = query.findAll();
        List<ProcessTree> ans = new ArrayList<>();
        for (Map<String, Object> x : tmp) {
            ans.add(Json.toObject(Json.toJson(x), ProcessTree.class));
        }
        return ans;
    }

    default Integer update(ProcessTree condition, ProcessTree value) {
        ProcessTreeQuery query = new ProcessTreeQuery();
        return query.updateByCondition(condition, value);
    }
    default Integer updateById(Long id, ProcessTree value) {
        ProcessTreeQuery query = new ProcessTreeQuery();
        return query.updateById(id, Json.toMap(Json.toJson(value)));
    }

    default Long count(ProcessTree processTree) {
        ProcessTreeQuery processTreeQuery = new ProcessTreeQuery();
        return processTreeQuery.count(processTree);
    }

    default Long count(List<Triplet<String, String, Object>> processTree) {
        ProcessTreeQuery processTreeQuery = new ProcessTreeQuery();
        return processTreeQuery.count(processTree);
    }

    default List<ProcessTree> page(ProcessTree processTree, Integer page, Integer size) {
        ProcessTreeQuery processTreeQuery = new ProcessTreeQuery();
        return processTreeQuery.page(processTree, page, size);
    }

    default List<ProcessTree> page(List<Triplet<String, String, Object>> processTree, Integer page, Integer size) {
        ProcessTreeQuery processTreeQuery = new ProcessTreeQuery();
        return processTreeQuery.findListModelByOperateSimpleAnd(processTree, page, size);
    }
    default List<ProcessTree> findAllByCondition(ProcessTree condition){
        ProcessTreeQuery query = new ProcessTreeQuery();
        return query.findByCondition(condition);
    }
    default List<ProcessTree> findAllWithIn(Map<String, List<Triplet<String, String, Object>>> inMaps) {
        ProcessTreeQuery query = new ProcessTreeQuery();
        Map<String, Object> andCondition = new HashMap<>();
        andCondition.put("tag", 0);
        WhereSyntaxTree whereSyntaxTree = new WhereSyntaxTree();
        for (Map.Entry<String, List<Triplet<String, String, Object>>> map : inMaps.entrySet()) {
            List<Triplet<String, String, Object>> orCondition = map.getValue();
            andCondition.put(map.getKey(), whereSyntaxTree.createOrTreeByOperate(orCondition));
        }
        AndWhereSyntaxTree andWhereSyntaxTree = whereSyntaxTree.createAndTree(andCondition);
        List<Map<String, Object>> ans = query.find("*").where(andWhereSyntaxTree).listMapGet();
        List<ProcessTree> processTrees = new ArrayList<>();
        for (Map<String, Object> x : ans) {
            processTrees.add(Json.toObject(Json.toJson(x),ProcessTree.class));
        }
        return processTrees;
    }
    default ProcessTree findProcessTreeByIdWithCache(Long id) {
        ProcessTreeQuery query = new ProcessTreeQuery();
        String tmp = query.getFromCache("ProcessTreeRepository", "findProcessTreeByIdWithCache", new HashMap<String, Object>() {
            {
                put("id", id);
            }
        });
        if (StringUtils.hasText(tmp)) {
            return Json.toObject(tmp, ProcessTree.class);
        } else {
            ProcessTree processTree = query.findModelById(id);
            if (Objects.nonNull(processTree)) {
                query.putToCache("ProcessTreeRepository", "findProcessTreeByIdWithCache", new HashMap<String, Object>() {
                    {
                        put("id", id);
                    }
                }, Json.toJson(processTree));
            }
            return processTree;
        }
    }

    default ProcessTree findProcessTreeByConditionWithCache(ProcessTree condition) {
        String json = Json.toJson(condition);
        Map<String, Object> andCondition = Json.toMap(json);
        ProcessTreeQuery query = new ProcessTreeQuery();
        String tmp = query.getFromCache("ProcessTreeRepository", "findProcessTreeByConditionWithCache", andCondition);
        if (StringUtils.hasText(tmp)) {
            return Json.toObject(tmp, ProcessTree.class);
        } else {
            ProcessTree processTree = query.findModelBySimpleAnd(andCondition);
            if (Objects.nonNull(processTree)) {
                query.putToCache("processTreeRepository","findProcessTreeByConditionWithCache",andCondition,Json.toJson(processTree));
            }
            return processTree;
        }
    }
// TODO: 2021/12/29 数据组相关
    default List<ProcessTree> findAllByCondition(ProcessTree condition, List<Long> orgIds) {
        ProcessTreeQuery query = new ProcessTreeQuery();
        return query.findByCondition(condition, orgIds);
    }

    default ProcessTree findSysDataAuthByCondition(ProcessTree condition, List<Long> orgIds) {
        String json = Json.toJson(condition);
        Map<String, Object> andCondition = Json.toMap(json);
        ProcessTreeQuery query = new ProcessTreeQuery();
        return query.findModelByOperateSimpleAnd(query.mergeCondition(andCondition, orgIds, query.fieldOrgName));
    }

    default List<ProcessTree> findAll(List<Long> orgIds) {
        ProcessTreeQuery query = new ProcessTreeQuery();
        return query.findByCondition(new ProcessTree(), orgIds);
    }

    default Integer update(ProcessTree condition, List<Long> orgIds, ProcessTree value) {
        ProcessTreeQuery query = new ProcessTreeQuery();
        return query.updateByCondition(condition, orgIds, value);
    }

    default Long count(ProcessTree processTree, List<Long> orgIds) {
        ProcessTreeQuery processTreeQuery = new ProcessTreeQuery();
        return processTreeQuery.count(processTree, orgIds);
    }

    default List<ProcessTree> page(ProcessTree processTree, List<Long> orgIds, Integer page, Integer size) {
        ProcessTreeQuery processTreeQuery = new ProcessTreeQuery();
        return processTreeQuery.page(processTree, orgIds, page, size);
    }
    // TODO: 2022/1/6 数据组 + owner_id作为条件 
    default List<ProcessTree> findAllByCondition(ProcessTree condition, List<Long> orgIds, Long ownerId) {
        ProcessTreeQuery query = new ProcessTreeQuery();
        return query.findByCondition(condition, orgIds, ownerId);
    }

    default ProcessTree findProcessTreeByCondition(ProcessTree condition, List<Long> orgIds, Long ownerId) {
        ProcessTreeQuery query = new ProcessTreeQuery();
        List<ProcessTree> processTrees = query.page(condition, orgIds, ownerId, 1, 1);
        if (processTrees.size() != 0) {
            return processTrees.get(0);
        }
        return null;
    }

    default List<ProcessTree> findAll(List<Long> orgIds, Long ownerId) {
        ProcessTreeQuery query = new ProcessTreeQuery();
        return query.findByCondition(new ProcessTree(), orgIds, ownerId);
    }

    default Integer update(ProcessTree condition, List<Long> orgIds, Long ownerId, ProcessTree value) {
        ProcessTreeQuery query = new ProcessTreeQuery();
        return query.updateByCondition(condition, orgIds, ownerId, value);
    }

    default Long count(ProcessTree processTree, List<Long> orgIds, Long ownerId) {
        ProcessTreeQuery processTreeQuery = new ProcessTreeQuery();
        return processTreeQuery.count(processTree, orgIds, ownerId);
    }

    default List<ProcessTree> page(ProcessTree processTree, List<Long> orgIds, Long ownerId, Integer page, Integer size) {
        ProcessTreeQuery processTreeQuery = new ProcessTreeQuery();
        return processTreeQuery.page(processTree, orgIds, ownerId, page, size);
    }

// todo -- 可见范围相关，与数据组类似
    default List<ProcessTree> page(ProcessTree processTree, VisibleUser visibleUser, Integer page, Integer size) {
        ProcessTreeQuery query = new ProcessTreeQuery();
        return query.page(processTree,visibleUser.getOrgIds(),visibleUser.getId(),page,size);
    }

    default Long count(ProcessTree processTree, VisibleUser visibleUser) {
        ProcessTreeQuery processTreeQuery = new ProcessTreeQuery();
        return processTreeQuery.count(processTree, visibleUser.getOrgIds(),visibleUser.getId());
    }

    default List<ProcessTree> findAll(VisibleUser visibleUser) {
        ProcessTreeQuery query = new ProcessTreeQuery();
        return query.findByCondition(new ProcessTree(), visibleUser.getOrgIds(), visibleUser.getId());
    }

    default ProcessTree findProcessTreeByCondition(ProcessTree condition, VisibleUser visibleUser) {
        ProcessTreeQuery query = new ProcessTreeQuery();
        List<ProcessTree> processTree = query.page(condition, visibleUser.getOrgIds(),visibleUser.getId(), 1, 1);
        if (processTree.size() != 0) {
            return processTree.get(0);
        }
        return null;
    }

    default List<ProcessTree> findAllByCondition(ProcessTree condition, VisibleUser visibleUser) {
        ProcessTreeQuery query = new ProcessTreeQuery();
        return query.findByCondition(condition, visibleUser.getOrgIds(),visibleUser.getId());
    }
// like 相关 
default ProcessTree findProcessTreeByLikeCondition(ProcessTree condition) {
        ProcessTreeQuery query = new ProcessTreeQuery();
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

    default ProcessTree findProcessTreeByLikeConditionAndEqualCondition(ProcessTree likeCondition, ProcessTree equalCondition) {
        ProcessTreeQuery query = new ProcessTreeQuery();
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

    default List<ProcessTree> pageWithLike(ProcessTree processTree, Integer page, Integer size) {
        ProcessTreeQuery query = new ProcessTreeQuery();
        String json = Json.toJson(processTree);
        Map<String, Object> andCondition = Json.toMap(json);
        List<Triplet<String, String, Object>> params = new ArrayList<>();
        for (Map.Entry<String, Object> x : andCondition.entrySet()) {
            if (Objects.nonNull(x.getValue())) {
                params.add(new Triplet<>(x.getKey(), "like", "%" + x.getValue() + "%"));
            }
        }
        return query.findListModelByOperateSimpleAnd(params, page, size);
    }

    default List<ProcessTree> pageWithLikeAndEqual(ProcessTree likeProcessTree, ProcessTree equalProcessTree, Integer page, Integer size) {
        ProcessTreeQuery query = new ProcessTreeQuery();
        String json = Json.toJson(likeProcessTree);
        Map<String, Object> andCondition = Json.toMap(json);
        List<Triplet<String, String, Object>> params = new ArrayList<>();
        for (Map.Entry<String, Object> x : andCondition.entrySet()) {
            if (Objects.nonNull(x.getValue())) {
                params.add(new Triplet<>(x.getKey(), "like", "%" + x.getValue() + "%"));
            }
        }
        Map<String, Object> andCondition2 = Json.toMap(Json.toJson(equalProcessTree));
        for (Map.Entry<String, Object> x : andCondition2.entrySet()) {
            if (Objects.nonNull(x.getValue())) {
                params.add(new Triplet<>(x.getKey(), "=", x.getValue()));
            }
        }
        return query.findListModelByOperateSimpleAnd(params, page, size);
    }

    default Long countWithLike(ProcessTree processTree) {
        ProcessTreeQuery query = new ProcessTreeQuery();
        String json = Json.toJson(processTree);
        Map<String, Object> andCondition = Json.toMap(json);
        List<Triplet<String, String, Object>> params = new ArrayList<>();
        for (Map.Entry<String, Object> x : andCondition.entrySet()) {
            if (Objects.nonNull(x.getValue())) {
                params.add(new Triplet<>(x.getKey(), "like", "%" + x.getValue() + "%"));
            }
        }
        return query.count(params);
    }

    default Long countWithLikeAndEqual(ProcessTree likeProcessTree, ProcessTree equalProcessTree) {
        ProcessTreeQuery query = new ProcessTreeQuery();
        String json = Json.toJson(likeProcessTree);
        Map<String, Object> andCondition = Json.toMap(json);
        List<Triplet<String, String, Object>> params = new ArrayList<>();
        for (Map.Entry<String, Object> x : andCondition.entrySet()) {
            if (Objects.nonNull(x.getValue())) {
                params.add(new Triplet<>(x.getKey(), "like", "%" + x.getValue() + "%"));
            }
        }
        Map<String, Object> andCondition2 = Json.toMap(Json.toJson(equalProcessTree));
        for (Map.Entry<String, Object> x : andCondition2.entrySet()) {
            if (Objects.nonNull(x.getValue())) {
                params.add(new Triplet<>(x.getKey(), "=", x.getValue()));
            }
        }
        return query.count(params);
    }}
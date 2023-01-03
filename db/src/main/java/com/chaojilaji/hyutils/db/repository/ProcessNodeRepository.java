package com.chaojilaji.hyutils.db.repository;

import com.chaojilaji.hyutils.db.model.ProcessNode;
import com.chaojilaji.hyutils.db.query.ProcessNodeQuery;
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

public interface ProcessNodeRepository {

    default ProcessNode findProcessNodeById(Long id) {
        ProcessNodeQuery query = new ProcessNodeQuery();
        return query.findModelById(id);
    }

    default ProcessNode findProcessNodeByCondition(ProcessNode condition) {
        String json = Json.toJson(condition);
        Map<String, Object> andCondition = Json.toMap(json);
        ProcessNodeQuery query = new ProcessNodeQuery();
        return query.findModelBySimpleAnd(andCondition);
    }

    default Long save(ProcessNode processNode) {
        ProcessNodeQuery query = new ProcessNodeQuery();
        return query.insert(Json.toMap(Json.toJson(processNode)));
    }


    default Integer saveAll(List<ProcessNode> processNodes) {
        List<Map<String, Object>> processNodeMaps = new ArrayList<>();
        for (ProcessNode processNode : processNodes) {
            processNodeMaps.add(Json.toMap(Json.toJson(processNode)));
        }
        ProcessNodeQuery query = new ProcessNodeQuery();
        return query.batchInsert(processNodeMaps);
    }

    default List<ProcessNode> findAll() {
        ProcessNodeQuery query = new ProcessNodeQuery();
        List<Map<String, Object>> tmp = query.findAll();
        List<ProcessNode> ans = new ArrayList<>();
        for (Map<String, Object> x : tmp) {
            ans.add(Json.toObject(Json.toJson(x), ProcessNode.class));
        }
        return ans;
    }

    default Integer update(ProcessNode condition, ProcessNode value) {
        ProcessNodeQuery query = new ProcessNodeQuery();
        return query.updateByCondition(condition, value);
    }
    default Integer updateById(Long id, ProcessNode value) {
        ProcessNodeQuery query = new ProcessNodeQuery();
        return query.updateById(id, Json.toMap(Json.toJson(value)));
    }

    default Long count(ProcessNode processNode) {
        ProcessNodeQuery processNodeQuery = new ProcessNodeQuery();
        return processNodeQuery.count(processNode);
    }

    default Long count(List<Triplet<String, String, Object>> processNode) {
        ProcessNodeQuery processNodeQuery = new ProcessNodeQuery();
        return processNodeQuery.count(processNode);
    }

    default List<ProcessNode> page(ProcessNode processNode, Integer page, Integer size) {
        ProcessNodeQuery processNodeQuery = new ProcessNodeQuery();
        return processNodeQuery.page(processNode, page, size);
    }

    default List<ProcessNode> page(List<Triplet<String, String, Object>> processNode, Integer page, Integer size) {
        ProcessNodeQuery processNodeQuery = new ProcessNodeQuery();
        return processNodeQuery.findListModelByOperateSimpleAnd(processNode, page, size);
    }
    default List<ProcessNode> findAllByCondition(ProcessNode condition){
        ProcessNodeQuery query = new ProcessNodeQuery();
        return query.findByCondition(condition);
    }
    default List<ProcessNode> findAllWithIn(Map<String, List<Triplet<String, String, Object>>> inMaps) {
        ProcessNodeQuery query = new ProcessNodeQuery();
        Map<String, Object> andCondition = new HashMap<>();
        andCondition.put("tag", 0);
        WhereSyntaxTree whereSyntaxTree = new WhereSyntaxTree();
        for (Map.Entry<String, List<Triplet<String, String, Object>>> map : inMaps.entrySet()) {
            List<Triplet<String, String, Object>> orCondition = map.getValue();
            andCondition.put(map.getKey(), whereSyntaxTree.createOrTreeByOperate(orCondition));
        }
        AndWhereSyntaxTree andWhereSyntaxTree = whereSyntaxTree.createAndTree(andCondition);
        List<Map<String, Object>> ans = query.find("*").where(andWhereSyntaxTree).listMapGet();
        List<ProcessNode> processNodes = new ArrayList<>();
        for (Map<String, Object> x : ans) {
            processNodes.add(Json.toObject(Json.toJson(x),ProcessNode.class));
        }
        return processNodes;
    }
    default ProcessNode findProcessNodeByIdWithCache(Long id) {
        ProcessNodeQuery query = new ProcessNodeQuery();
        String tmp = query.getFromCache("ProcessNodeRepository", "findProcessNodeByIdWithCache", new HashMap<String, Object>() {
            {
                put("id", id);
            }
        });
        if (StringUtils.hasText(tmp)) {
            return Json.toObject(tmp, ProcessNode.class);
        } else {
            ProcessNode processNode = query.findModelById(id);
            if (Objects.nonNull(processNode)) {
                query.putToCache("ProcessNodeRepository", "findProcessNodeByIdWithCache", new HashMap<String, Object>() {
                    {
                        put("id", id);
                    }
                }, Json.toJson(processNode));
            }
            return processNode;
        }
    }

    default ProcessNode findProcessNodeByConditionWithCache(ProcessNode condition) {
        String json = Json.toJson(condition);
        Map<String, Object> andCondition = Json.toMap(json);
        ProcessNodeQuery query = new ProcessNodeQuery();
        String tmp = query.getFromCache("ProcessNodeRepository", "findProcessNodeByConditionWithCache", andCondition);
        if (StringUtils.hasText(tmp)) {
            return Json.toObject(tmp, ProcessNode.class);
        } else {
            ProcessNode processNode = query.findModelBySimpleAnd(andCondition);
            if (Objects.nonNull(processNode)) {
                query.putToCache("processNodeRepository","findProcessNodeByConditionWithCache",andCondition,Json.toJson(processNode));
            }
            return processNode;
        }
    }
// TODO: 2021/12/29 数据组相关
    default List<ProcessNode> findAllByCondition(ProcessNode condition, List<Long> orgIds) {
        ProcessNodeQuery query = new ProcessNodeQuery();
        return query.findByCondition(condition, orgIds);
    }

    default ProcessNode findSysDataAuthByCondition(ProcessNode condition, List<Long> orgIds) {
        String json = Json.toJson(condition);
        Map<String, Object> andCondition = Json.toMap(json);
        ProcessNodeQuery query = new ProcessNodeQuery();
        return query.findModelByOperateSimpleAnd(query.mergeCondition(andCondition, orgIds, query.fieldOrgName));
    }

    default List<ProcessNode> findAll(List<Long> orgIds) {
        ProcessNodeQuery query = new ProcessNodeQuery();
        return query.findByCondition(new ProcessNode(), orgIds);
    }

    default Integer update(ProcessNode condition, List<Long> orgIds, ProcessNode value) {
        ProcessNodeQuery query = new ProcessNodeQuery();
        return query.updateByCondition(condition, orgIds, value);
    }

    default Long count(ProcessNode processNode, List<Long> orgIds) {
        ProcessNodeQuery processNodeQuery = new ProcessNodeQuery();
        return processNodeQuery.count(processNode, orgIds);
    }

    default List<ProcessNode> page(ProcessNode processNode, List<Long> orgIds, Integer page, Integer size) {
        ProcessNodeQuery processNodeQuery = new ProcessNodeQuery();
        return processNodeQuery.page(processNode, orgIds, page, size);
    }
    // TODO: 2022/1/6 数据组 + owner_id作为条件 
    default List<ProcessNode> findAllByCondition(ProcessNode condition, List<Long> orgIds, Long ownerId) {
        ProcessNodeQuery query = new ProcessNodeQuery();
        return query.findByCondition(condition, orgIds, ownerId);
    }

    default ProcessNode findProcessNodeByCondition(ProcessNode condition, List<Long> orgIds, Long ownerId) {
        ProcessNodeQuery query = new ProcessNodeQuery();
        List<ProcessNode> processNodes = query.page(condition, orgIds, ownerId, 1, 1);
        if (processNodes.size() != 0) {
            return processNodes.get(0);
        }
        return null;
    }

    default List<ProcessNode> findAll(List<Long> orgIds, Long ownerId) {
        ProcessNodeQuery query = new ProcessNodeQuery();
        return query.findByCondition(new ProcessNode(), orgIds, ownerId);
    }

    default Integer update(ProcessNode condition, List<Long> orgIds, Long ownerId, ProcessNode value) {
        ProcessNodeQuery query = new ProcessNodeQuery();
        return query.updateByCondition(condition, orgIds, ownerId, value);
    }

    default Long count(ProcessNode processNode, List<Long> orgIds, Long ownerId) {
        ProcessNodeQuery processNodeQuery = new ProcessNodeQuery();
        return processNodeQuery.count(processNode, orgIds, ownerId);
    }

    default List<ProcessNode> page(ProcessNode processNode, List<Long> orgIds, Long ownerId, Integer page, Integer size) {
        ProcessNodeQuery processNodeQuery = new ProcessNodeQuery();
        return processNodeQuery.page(processNode, orgIds, ownerId, page, size);
    }

// todo -- 可见范围相关，与数据组类似
    default List<ProcessNode> page(ProcessNode processNode, VisibleUser visibleUser, Integer page, Integer size) {
        ProcessNodeQuery query = new ProcessNodeQuery();
        return query.page(processNode,visibleUser.getOrgIds(),visibleUser.getId(),page,size);
    }

    default Long count(ProcessNode processNode, VisibleUser visibleUser) {
        ProcessNodeQuery processNodeQuery = new ProcessNodeQuery();
        return processNodeQuery.count(processNode, visibleUser.getOrgIds(),visibleUser.getId());
    }

    default List<ProcessNode> findAll(VisibleUser visibleUser) {
        ProcessNodeQuery query = new ProcessNodeQuery();
        return query.findByCondition(new ProcessNode(), visibleUser.getOrgIds(), visibleUser.getId());
    }

    default ProcessNode findProcessNodeByCondition(ProcessNode condition, VisibleUser visibleUser) {
        ProcessNodeQuery query = new ProcessNodeQuery();
        List<ProcessNode> processNode = query.page(condition, visibleUser.getOrgIds(),visibleUser.getId(), 1, 1);
        if (processNode.size() != 0) {
            return processNode.get(0);
        }
        return null;
    }

    default List<ProcessNode> findAllByCondition(ProcessNode condition, VisibleUser visibleUser) {
        ProcessNodeQuery query = new ProcessNodeQuery();
        return query.findByCondition(condition, visibleUser.getOrgIds(),visibleUser.getId());
    }
// like 相关 
default ProcessNode findProcessNodeByLikeCondition(ProcessNode condition) {
        ProcessNodeQuery query = new ProcessNodeQuery();
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

    default ProcessNode findProcessNodeByLikeConditionAndEqualCondition(ProcessNode likeCondition, ProcessNode equalCondition) {
        ProcessNodeQuery query = new ProcessNodeQuery();
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

    default List<ProcessNode> pageWithLike(ProcessNode processNode, Integer page, Integer size) {
        ProcessNodeQuery query = new ProcessNodeQuery();
        String json = Json.toJson(processNode);
        Map<String, Object> andCondition = Json.toMap(json);
        List<Triplet<String, String, Object>> params = new ArrayList<>();
        for (Map.Entry<String, Object> x : andCondition.entrySet()) {
            if (Objects.nonNull(x.getValue())) {
                params.add(new Triplet<>(x.getKey(), "like", "%" + x.getValue() + "%"));
            }
        }
        return query.findListModelByOperateSimpleAnd(params, page, size);
    }

    default List<ProcessNode> pageWithLikeAndEqual(ProcessNode likeProcessNode, ProcessNode equalProcessNode, Integer page, Integer size) {
        ProcessNodeQuery query = new ProcessNodeQuery();
        String json = Json.toJson(likeProcessNode);
        Map<String, Object> andCondition = Json.toMap(json);
        List<Triplet<String, String, Object>> params = new ArrayList<>();
        for (Map.Entry<String, Object> x : andCondition.entrySet()) {
            if (Objects.nonNull(x.getValue())) {
                params.add(new Triplet<>(x.getKey(), "like", "%" + x.getValue() + "%"));
            }
        }
        Map<String, Object> andCondition2 = Json.toMap(Json.toJson(equalProcessNode));
        for (Map.Entry<String, Object> x : andCondition2.entrySet()) {
            if (Objects.nonNull(x.getValue())) {
                params.add(new Triplet<>(x.getKey(), "=", x.getValue()));
            }
        }
        return query.findListModelByOperateSimpleAnd(params, page, size);
    }

    default Long countWithLike(ProcessNode processNode) {
        ProcessNodeQuery query = new ProcessNodeQuery();
        String json = Json.toJson(processNode);
        Map<String, Object> andCondition = Json.toMap(json);
        List<Triplet<String, String, Object>> params = new ArrayList<>();
        for (Map.Entry<String, Object> x : andCondition.entrySet()) {
            if (Objects.nonNull(x.getValue())) {
                params.add(new Triplet<>(x.getKey(), "like", "%" + x.getValue() + "%"));
            }
        }
        return query.count(params);
    }

    default Long countWithLikeAndEqual(ProcessNode likeProcessNode, ProcessNode equalProcessNode) {
        ProcessNodeQuery query = new ProcessNodeQuery();
        String json = Json.toJson(likeProcessNode);
        Map<String, Object> andCondition = Json.toMap(json);
        List<Triplet<String, String, Object>> params = new ArrayList<>();
        for (Map.Entry<String, Object> x : andCondition.entrySet()) {
            if (Objects.nonNull(x.getValue())) {
                params.add(new Triplet<>(x.getKey(), "like", "%" + x.getValue() + "%"));
            }
        }
        Map<String, Object> andCondition2 = Json.toMap(Json.toJson(equalProcessNode));
        for (Map.Entry<String, Object> x : andCondition2.entrySet()) {
            if (Objects.nonNull(x.getValue())) {
                params.add(new Triplet<>(x.getKey(), "=", x.getValue()));
            }
        }
        return query.count(params);
    }}
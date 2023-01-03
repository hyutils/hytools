package com.chaojilaji.hyutils.db.repository;

import com.chaojilaji.hyutils.db.model.ProcessSide;
import com.chaojilaji.hyutils.db.query.ProcessSideQuery;
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

public interface ProcessSideRepository {

    default ProcessSide findProcessSideById(Long id) {
        ProcessSideQuery query = new ProcessSideQuery();
        return query.findModelById(id);
    }

    default ProcessSide findProcessSideByCondition(ProcessSide condition) {
        String json = Json.toJson(condition);
        Map<String, Object> andCondition = Json.toMap(json);
        ProcessSideQuery query = new ProcessSideQuery();
        return query.findModelBySimpleAnd(andCondition);
    }

    default Long save(ProcessSide processSide) {
        ProcessSideQuery query = new ProcessSideQuery();
        return query.insert(Json.toMap(Json.toJson(processSide)));
    }


    default Integer saveAll(List<ProcessSide> processSides) {
        List<Map<String, Object>> processSideMaps = new ArrayList<>();
        for (ProcessSide processSide : processSides) {
            processSideMaps.add(Json.toMap(Json.toJson(processSide)));
        }
        ProcessSideQuery query = new ProcessSideQuery();
        return query.batchInsert(processSideMaps);
    }

    default List<ProcessSide> findAll() {
        ProcessSideQuery query = new ProcessSideQuery();
        List<Map<String, Object>> tmp = query.findAll();
        List<ProcessSide> ans = new ArrayList<>();
        for (Map<String, Object> x : tmp) {
            ans.add(Json.toObject(Json.toJson(x), ProcessSide.class));
        }
        return ans;
    }

    default Integer update(ProcessSide condition, ProcessSide value) {
        ProcessSideQuery query = new ProcessSideQuery();
        return query.updateByCondition(condition, value);
    }
    default Integer updateById(Long id, ProcessSide value) {
        ProcessSideQuery query = new ProcessSideQuery();
        return query.updateById(id, Json.toMap(Json.toJson(value)));
    }

    default Long count(ProcessSide processSide) {
        ProcessSideQuery processSideQuery = new ProcessSideQuery();
        return processSideQuery.count(processSide);
    }

    default Long count(List<Triplet<String, String, Object>> processSide) {
        ProcessSideQuery processSideQuery = new ProcessSideQuery();
        return processSideQuery.count(processSide);
    }

    default List<ProcessSide> page(ProcessSide processSide, Integer page, Integer size) {
        ProcessSideQuery processSideQuery = new ProcessSideQuery();
        return processSideQuery.page(processSide, page, size);
    }

    default List<ProcessSide> page(List<Triplet<String, String, Object>> processSide, Integer page, Integer size) {
        ProcessSideQuery processSideQuery = new ProcessSideQuery();
        return processSideQuery.findListModelByOperateSimpleAnd(processSide, page, size);
    }
    default List<ProcessSide> findAllByCondition(ProcessSide condition){
        ProcessSideQuery query = new ProcessSideQuery();
        return query.findByCondition(condition);
    }
    default List<ProcessSide> findAllWithIn(Map<String, List<Triplet<String, String, Object>>> inMaps) {
        ProcessSideQuery query = new ProcessSideQuery();
        Map<String, Object> andCondition = new HashMap<>();
        andCondition.put("tag", 0);
        WhereSyntaxTree whereSyntaxTree = new WhereSyntaxTree();
        for (Map.Entry<String, List<Triplet<String, String, Object>>> map : inMaps.entrySet()) {
            List<Triplet<String, String, Object>> orCondition = map.getValue();
            andCondition.put(map.getKey(), whereSyntaxTree.createOrTreeByOperate(orCondition));
        }
        AndWhereSyntaxTree andWhereSyntaxTree = whereSyntaxTree.createAndTree(andCondition);
        List<Map<String, Object>> ans = query.find("*").where(andWhereSyntaxTree).listMapGet();
        List<ProcessSide> processSides = new ArrayList<>();
        for (Map<String, Object> x : ans) {
            processSides.add(Json.toObject(Json.toJson(x),ProcessSide.class));
        }
        return processSides;
    }
    default ProcessSide findProcessSideByIdWithCache(Long id) {
        ProcessSideQuery query = new ProcessSideQuery();
        String tmp = query.getFromCache("ProcessSideRepository", "findProcessSideByIdWithCache", new HashMap<String, Object>() {
            {
                put("id", id);
            }
        });
        if (StringUtils.hasText(tmp)) {
            return Json.toObject(tmp, ProcessSide.class);
        } else {
            ProcessSide processSide = query.findModelById(id);
            if (Objects.nonNull(processSide)) {
                query.putToCache("ProcessSideRepository", "findProcessSideByIdWithCache", new HashMap<String, Object>() {
                    {
                        put("id", id);
                    }
                }, Json.toJson(processSide));
            }
            return processSide;
        }
    }

    default ProcessSide findProcessSideByConditionWithCache(ProcessSide condition) {
        String json = Json.toJson(condition);
        Map<String, Object> andCondition = Json.toMap(json);
        ProcessSideQuery query = new ProcessSideQuery();
        String tmp = query.getFromCache("ProcessSideRepository", "findProcessSideByConditionWithCache", andCondition);
        if (StringUtils.hasText(tmp)) {
            return Json.toObject(tmp, ProcessSide.class);
        } else {
            ProcessSide processSide = query.findModelBySimpleAnd(andCondition);
            if (Objects.nonNull(processSide)) {
                query.putToCache("processSideRepository","findProcessSideByConditionWithCache",andCondition,Json.toJson(processSide));
            }
            return processSide;
        }
    }
// TODO: 2021/12/29 数据组相关
    default List<ProcessSide> findAllByCondition(ProcessSide condition, List<Long> orgIds) {
        ProcessSideQuery query = new ProcessSideQuery();
        return query.findByCondition(condition, orgIds);
    }

    default ProcessSide findSysDataAuthByCondition(ProcessSide condition, List<Long> orgIds) {
        String json = Json.toJson(condition);
        Map<String, Object> andCondition = Json.toMap(json);
        ProcessSideQuery query = new ProcessSideQuery();
        return query.findModelByOperateSimpleAnd(query.mergeCondition(andCondition, orgIds, query.fieldOrgName));
    }

    default List<ProcessSide> findAll(List<Long> orgIds) {
        ProcessSideQuery query = new ProcessSideQuery();
        return query.findByCondition(new ProcessSide(), orgIds);
    }

    default Integer update(ProcessSide condition, List<Long> orgIds, ProcessSide value) {
        ProcessSideQuery query = new ProcessSideQuery();
        return query.updateByCondition(condition, orgIds, value);
    }

    default Long count(ProcessSide processSide, List<Long> orgIds) {
        ProcessSideQuery processSideQuery = new ProcessSideQuery();
        return processSideQuery.count(processSide, orgIds);
    }

    default List<ProcessSide> page(ProcessSide processSide, List<Long> orgIds, Integer page, Integer size) {
        ProcessSideQuery processSideQuery = new ProcessSideQuery();
        return processSideQuery.page(processSide, orgIds, page, size);
    }
    // TODO: 2022/1/6 数据组 + owner_id作为条件 
    default List<ProcessSide> findAllByCondition(ProcessSide condition, List<Long> orgIds, Long ownerId) {
        ProcessSideQuery query = new ProcessSideQuery();
        return query.findByCondition(condition, orgIds, ownerId);
    }

    default ProcessSide findProcessSideByCondition(ProcessSide condition, List<Long> orgIds, Long ownerId) {
        ProcessSideQuery query = new ProcessSideQuery();
        List<ProcessSide> processSides = query.page(condition, orgIds, ownerId, 1, 1);
        if (processSides.size() != 0) {
            return processSides.get(0);
        }
        return null;
    }

    default List<ProcessSide> findAll(List<Long> orgIds, Long ownerId) {
        ProcessSideQuery query = new ProcessSideQuery();
        return query.findByCondition(new ProcessSide(), orgIds, ownerId);
    }

    default Integer update(ProcessSide condition, List<Long> orgIds, Long ownerId, ProcessSide value) {
        ProcessSideQuery query = new ProcessSideQuery();
        return query.updateByCondition(condition, orgIds, ownerId, value);
    }

    default Long count(ProcessSide processSide, List<Long> orgIds, Long ownerId) {
        ProcessSideQuery processSideQuery = new ProcessSideQuery();
        return processSideQuery.count(processSide, orgIds, ownerId);
    }

    default List<ProcessSide> page(ProcessSide processSide, List<Long> orgIds, Long ownerId, Integer page, Integer size) {
        ProcessSideQuery processSideQuery = new ProcessSideQuery();
        return processSideQuery.page(processSide, orgIds, ownerId, page, size);
    }

// todo -- 可见范围相关，与数据组类似
    default List<ProcessSide> page(ProcessSide processSide, VisibleUser visibleUser, Integer page, Integer size) {
        ProcessSideQuery query = new ProcessSideQuery();
        return query.page(processSide,visibleUser.getOrgIds(),visibleUser.getId(),page,size);
    }

    default Long count(ProcessSide processSide, VisibleUser visibleUser) {
        ProcessSideQuery processSideQuery = new ProcessSideQuery();
        return processSideQuery.count(processSide, visibleUser.getOrgIds(),visibleUser.getId());
    }

    default List<ProcessSide> findAll(VisibleUser visibleUser) {
        ProcessSideQuery query = new ProcessSideQuery();
        return query.findByCondition(new ProcessSide(), visibleUser.getOrgIds(), visibleUser.getId());
    }

    default ProcessSide findProcessSideByCondition(ProcessSide condition, VisibleUser visibleUser) {
        ProcessSideQuery query = new ProcessSideQuery();
        List<ProcessSide> processSide = query.page(condition, visibleUser.getOrgIds(),visibleUser.getId(), 1, 1);
        if (processSide.size() != 0) {
            return processSide.get(0);
        }
        return null;
    }

    default List<ProcessSide> findAllByCondition(ProcessSide condition, VisibleUser visibleUser) {
        ProcessSideQuery query = new ProcessSideQuery();
        return query.findByCondition(condition, visibleUser.getOrgIds(),visibleUser.getId());
    }
// like 相关 
default ProcessSide findProcessSideByLikeCondition(ProcessSide condition) {
        ProcessSideQuery query = new ProcessSideQuery();
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

    default ProcessSide findProcessSideByLikeConditionAndEqualCondition(ProcessSide likeCondition, ProcessSide equalCondition) {
        ProcessSideQuery query = new ProcessSideQuery();
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

    default List<ProcessSide> pageWithLike(ProcessSide processSide, Integer page, Integer size) {
        ProcessSideQuery query = new ProcessSideQuery();
        String json = Json.toJson(processSide);
        Map<String, Object> andCondition = Json.toMap(json);
        List<Triplet<String, String, Object>> params = new ArrayList<>();
        for (Map.Entry<String, Object> x : andCondition.entrySet()) {
            if (Objects.nonNull(x.getValue())) {
                params.add(new Triplet<>(x.getKey(), "like", "%" + x.getValue() + "%"));
            }
        }
        return query.findListModelByOperateSimpleAnd(params, page, size);
    }

    default List<ProcessSide> pageWithLikeAndEqual(ProcessSide likeProcessSide, ProcessSide equalProcessSide, Integer page, Integer size) {
        ProcessSideQuery query = new ProcessSideQuery();
        String json = Json.toJson(likeProcessSide);
        Map<String, Object> andCondition = Json.toMap(json);
        List<Triplet<String, String, Object>> params = new ArrayList<>();
        for (Map.Entry<String, Object> x : andCondition.entrySet()) {
            if (Objects.nonNull(x.getValue())) {
                params.add(new Triplet<>(x.getKey(), "like", "%" + x.getValue() + "%"));
            }
        }
        Map<String, Object> andCondition2 = Json.toMap(Json.toJson(equalProcessSide));
        for (Map.Entry<String, Object> x : andCondition2.entrySet()) {
            if (Objects.nonNull(x.getValue())) {
                params.add(new Triplet<>(x.getKey(), "=", x.getValue()));
            }
        }
        return query.findListModelByOperateSimpleAnd(params, page, size);
    }

    default Long countWithLike(ProcessSide processSide) {
        ProcessSideQuery query = new ProcessSideQuery();
        String json = Json.toJson(processSide);
        Map<String, Object> andCondition = Json.toMap(json);
        List<Triplet<String, String, Object>> params = new ArrayList<>();
        for (Map.Entry<String, Object> x : andCondition.entrySet()) {
            if (Objects.nonNull(x.getValue())) {
                params.add(new Triplet<>(x.getKey(), "like", "%" + x.getValue() + "%"));
            }
        }
        return query.count(params);
    }

    default Long countWithLikeAndEqual(ProcessSide likeProcessSide, ProcessSide equalProcessSide) {
        ProcessSideQuery query = new ProcessSideQuery();
        String json = Json.toJson(likeProcessSide);
        Map<String, Object> andCondition = Json.toMap(json);
        List<Triplet<String, String, Object>> params = new ArrayList<>();
        for (Map.Entry<String, Object> x : andCondition.entrySet()) {
            if (Objects.nonNull(x.getValue())) {
                params.add(new Triplet<>(x.getKey(), "like", "%" + x.getValue() + "%"));
            }
        }
        Map<String, Object> andCondition2 = Json.toMap(Json.toJson(equalProcessSide));
        for (Map.Entry<String, Object> x : andCondition2.entrySet()) {
            if (Objects.nonNull(x.getValue())) {
                params.add(new Triplet<>(x.getKey(), "=", x.getValue()));
            }
        }
        return query.count(params);
    }}
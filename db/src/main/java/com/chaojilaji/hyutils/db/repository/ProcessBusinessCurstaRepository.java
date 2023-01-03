package com.chaojilaji.hyutils.db.repository;

import com.chaojilaji.hyutils.db.model.ProcessBusinessCursta;
import com.chaojilaji.hyutils.db.model.ProcessDevHistory;
import com.chaojilaji.hyutils.db.model.ProcessNode;
import com.chaojilaji.hyutils.db.model.ProcessSide;
import com.chaojilaji.hyutils.db.query.ProcessBusinessCurstaQuery;
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

public interface ProcessBusinessCurstaRepository {

    default ProcessBusinessCursta findProcessBusinessCurstaById(Long id) {
        ProcessBusinessCurstaQuery query = new ProcessBusinessCurstaQuery();
        return query.findModelById(id);
    }

    default ProcessBusinessCursta findProcessBusinessCurstaByCondition(ProcessBusinessCursta condition) {
        String json = Json.toJson(condition);
        Map<String, Object> andCondition = Json.toMap(json);
        ProcessBusinessCurstaQuery query = new ProcessBusinessCurstaQuery();
        return query.findModelBySimpleAnd(andCondition);
    }

    default Long save(ProcessBusinessCursta processBusinessCursta) {
        ProcessBusinessCurstaQuery query = new ProcessBusinessCurstaQuery();
        return query.insert(Json.toMap(Json.toJson(processBusinessCursta)));
    }


    default Integer saveAll(List<ProcessBusinessCursta> processBusinessCurstas) {
        List<Map<String, Object>> processBusinessCurstaMaps = new ArrayList<>();
        for (ProcessBusinessCursta processBusinessCursta : processBusinessCurstas) {
            processBusinessCurstaMaps.add(Json.toMap(Json.toJson(processBusinessCursta)));
        }
        ProcessBusinessCurstaQuery query = new ProcessBusinessCurstaQuery();
        return query.batchInsert(processBusinessCurstaMaps);
    }

    default List<ProcessBusinessCursta> findAll() {
        ProcessBusinessCurstaQuery query = new ProcessBusinessCurstaQuery();
        List<Map<String, Object>> tmp = query.findAll();
        List<ProcessBusinessCursta> ans = new ArrayList<>();
        for (Map<String, Object> x : tmp) {
            ans.add(Json.toObject(Json.toJson(x), ProcessBusinessCursta.class));
        }
        return ans;
    }

    default Integer update(ProcessBusinessCursta condition, ProcessBusinessCursta value) {
        ProcessBusinessCurstaQuery query = new ProcessBusinessCurstaQuery();
        return query.updateByCondition(condition, value);
    }
    default Integer updateById(Long id, ProcessBusinessCursta value) {
        ProcessBusinessCurstaQuery query = new ProcessBusinessCurstaQuery();
        return query.updateById(id, Json.toMap(Json.toJson(value)));
    }

    default Long count(ProcessBusinessCursta processBusinessCursta) {
        ProcessBusinessCurstaQuery processBusinessCurstaQuery = new ProcessBusinessCurstaQuery();
        return processBusinessCurstaQuery.count(processBusinessCursta);
    }

    default Long count(List<Triplet<String, String, Object>> processBusinessCursta) {
        ProcessBusinessCurstaQuery processBusinessCurstaQuery = new ProcessBusinessCurstaQuery();
        return processBusinessCurstaQuery.count(processBusinessCursta);
    }

    default List<ProcessBusinessCursta> page(ProcessBusinessCursta processBusinessCursta, Integer page, Integer size) {
        ProcessBusinessCurstaQuery processBusinessCurstaQuery = new ProcessBusinessCurstaQuery();
        return processBusinessCurstaQuery.page(processBusinessCursta, page, size);
    }

    default List<ProcessBusinessCursta> page(List<Triplet<String, String, Object>> processBusinessCursta, Integer page, Integer size) {
        ProcessBusinessCurstaQuery processBusinessCurstaQuery = new ProcessBusinessCurstaQuery();
        return processBusinessCurstaQuery.findListModelByOperateSimpleAnd(processBusinessCursta, page, size);
    }
    default List<ProcessBusinessCursta> findAllByCondition(ProcessBusinessCursta condition){
        ProcessBusinessCurstaQuery query = new ProcessBusinessCurstaQuery();
        return query.findByCondition(condition);
    }
    default List<ProcessBusinessCursta> findAllWithIn(Map<String, List<Triplet<String, String, Object>>> inMaps) {
        ProcessBusinessCurstaQuery query = new ProcessBusinessCurstaQuery();
        Map<String, Object> andCondition = new HashMap<>();
        andCondition.put("tag", 0);
        WhereSyntaxTree whereSyntaxTree = new WhereSyntaxTree();
        for (Map.Entry<String, List<Triplet<String, String, Object>>> map : inMaps.entrySet()) {
            List<Triplet<String, String, Object>> orCondition = map.getValue();
            andCondition.put(map.getKey(), whereSyntaxTree.createOrTreeByOperate(orCondition));
        }
        AndWhereSyntaxTree andWhereSyntaxTree = whereSyntaxTree.createAndTree(andCondition);
        List<Map<String, Object>> ans = query.find("*").where(andWhereSyntaxTree).listMapGet();
        List<ProcessBusinessCursta> processBusinessCurstas = new ArrayList<>();
        for (Map<String, Object> x : ans) {
            processBusinessCurstas.add(Json.toObject(Json.toJson(x),ProcessBusinessCursta.class));
        }
        return processBusinessCurstas;
    }
    default ProcessBusinessCursta findProcessBusinessCurstaByIdWithCache(Long id) {
        ProcessBusinessCurstaQuery query = new ProcessBusinessCurstaQuery();
        String tmp = query.getFromCache("ProcessBusinessCurstaRepository", "findProcessBusinessCurstaByIdWithCache", new HashMap<String, Object>() {
            {
                put("id", id);
            }
        });
        if (StringUtils.hasText(tmp)) {
            return Json.toObject(tmp, ProcessBusinessCursta.class);
        } else {
            ProcessBusinessCursta processBusinessCursta = query.findModelById(id);
            if (Objects.nonNull(processBusinessCursta)) {
                query.putToCache("ProcessBusinessCurstaRepository", "findProcessBusinessCurstaByIdWithCache", new HashMap<String, Object>() {
                    {
                        put("id", id);
                    }
                }, Json.toJson(processBusinessCursta));
            }
            return processBusinessCursta;
        }
    }

    default ProcessBusinessCursta findProcessBusinessCurstaByConditionWithCache(ProcessBusinessCursta condition) {
        String json = Json.toJson(condition);
        Map<String, Object> andCondition = Json.toMap(json);
        ProcessBusinessCurstaQuery query = new ProcessBusinessCurstaQuery();
        String tmp = query.getFromCache("ProcessBusinessCurstaRepository", "findProcessBusinessCurstaByConditionWithCache", andCondition);
        if (StringUtils.hasText(tmp)) {
            return Json.toObject(tmp, ProcessBusinessCursta.class);
        } else {
            ProcessBusinessCursta processBusinessCursta = query.findModelBySimpleAnd(andCondition);
            if (Objects.nonNull(processBusinessCursta)) {
                query.putToCache("processBusinessCurstaRepository","findProcessBusinessCurstaByConditionWithCache",andCondition,Json.toJson(processBusinessCursta));
            }
            return processBusinessCursta;
        }
    }
// TODO: 2021/12/29 数据组相关
    default List<ProcessBusinessCursta> findAllByCondition(ProcessBusinessCursta condition, List<Long> orgIds) {
        ProcessBusinessCurstaQuery query = new ProcessBusinessCurstaQuery();
        return query.findByCondition(condition, orgIds);
    }

    default ProcessBusinessCursta findSysDataAuthByCondition(ProcessBusinessCursta condition, List<Long> orgIds) {
        String json = Json.toJson(condition);
        Map<String, Object> andCondition = Json.toMap(json);
        ProcessBusinessCurstaQuery query = new ProcessBusinessCurstaQuery();
        return query.findModelByOperateSimpleAnd(query.mergeCondition(andCondition, orgIds, query.fieldOrgName));
    }

    default List<ProcessBusinessCursta> findAll(List<Long> orgIds) {
        ProcessBusinessCurstaQuery query = new ProcessBusinessCurstaQuery();
        return query.findByCondition(new ProcessBusinessCursta(), orgIds);
    }

    default Integer update(ProcessBusinessCursta condition, List<Long> orgIds, ProcessBusinessCursta value) {
        ProcessBusinessCurstaQuery query = new ProcessBusinessCurstaQuery();
        return query.updateByCondition(condition, orgIds, value);
    }

    default Long count(ProcessBusinessCursta processBusinessCursta, List<Long> orgIds) {
        ProcessBusinessCurstaQuery processBusinessCurstaQuery = new ProcessBusinessCurstaQuery();
        return processBusinessCurstaQuery.count(processBusinessCursta, orgIds);
    }

    default List<ProcessBusinessCursta> page(ProcessBusinessCursta processBusinessCursta, List<Long> orgIds, Integer page, Integer size) {
        ProcessBusinessCurstaQuery processBusinessCurstaQuery = new ProcessBusinessCurstaQuery();
        return processBusinessCurstaQuery.page(processBusinessCursta, orgIds, page, size);
    }
    // TODO: 2022/1/6 数据组 + owner_id作为条件 
    default List<ProcessBusinessCursta> findAllByCondition(ProcessBusinessCursta condition, List<Long> orgIds, Long ownerId) {
        ProcessBusinessCurstaQuery query = new ProcessBusinessCurstaQuery();
        return query.findByCondition(condition, orgIds, ownerId);
    }

    default ProcessBusinessCursta findProcessBusinessCurstaByCondition(ProcessBusinessCursta condition, List<Long> orgIds, Long ownerId) {
        ProcessBusinessCurstaQuery query = new ProcessBusinessCurstaQuery();
        List<ProcessBusinessCursta> processBusinessCurstas = query.page(condition, orgIds, ownerId, 1, 1);
        if (processBusinessCurstas.size() != 0) {
            return processBusinessCurstas.get(0);
        }
        return null;
    }

    default List<ProcessBusinessCursta> findAll(List<Long> orgIds, Long ownerId) {
        ProcessBusinessCurstaQuery query = new ProcessBusinessCurstaQuery();
        return query.findByCondition(new ProcessBusinessCursta(), orgIds, ownerId);
    }

    default Integer update(ProcessBusinessCursta condition, List<Long> orgIds, Long ownerId, ProcessBusinessCursta value) {
        ProcessBusinessCurstaQuery query = new ProcessBusinessCurstaQuery();
        return query.updateByCondition(condition, orgIds, ownerId, value);
    }

    default Long count(ProcessBusinessCursta processBusinessCursta, List<Long> orgIds, Long ownerId) {
        ProcessBusinessCurstaQuery processBusinessCurstaQuery = new ProcessBusinessCurstaQuery();
        return processBusinessCurstaQuery.count(processBusinessCursta, orgIds, ownerId);
    }

    default List<ProcessBusinessCursta> page(ProcessBusinessCursta processBusinessCursta, List<Long> orgIds, Long ownerId, Integer page, Integer size) {
        ProcessBusinessCurstaQuery processBusinessCurstaQuery = new ProcessBusinessCurstaQuery();
        return processBusinessCurstaQuery.page(processBusinessCursta, orgIds, ownerId, page, size);
    }

// todo -- 可见范围相关，与数据组类似
    default List<ProcessBusinessCursta> page(ProcessBusinessCursta processBusinessCursta, VisibleUser visibleUser, Integer page, Integer size) {
        ProcessBusinessCurstaQuery query = new ProcessBusinessCurstaQuery();
        return query.page(processBusinessCursta,visibleUser.getOrgIds(),visibleUser.getId(),page,size);
    }

    default Long count(ProcessBusinessCursta processBusinessCursta, VisibleUser visibleUser) {
        ProcessBusinessCurstaQuery processBusinessCurstaQuery = new ProcessBusinessCurstaQuery();
        return processBusinessCurstaQuery.count(processBusinessCursta, visibleUser.getOrgIds(),visibleUser.getId());
    }

    default List<ProcessBusinessCursta> findAll(VisibleUser visibleUser) {
        ProcessBusinessCurstaQuery query = new ProcessBusinessCurstaQuery();
        return query.findByCondition(new ProcessBusinessCursta(), visibleUser.getOrgIds(), visibleUser.getId());
    }

    default ProcessBusinessCursta findProcessBusinessCurstaByCondition(ProcessBusinessCursta condition, VisibleUser visibleUser) {
        ProcessBusinessCurstaQuery query = new ProcessBusinessCurstaQuery();
        List<ProcessBusinessCursta> processBusinessCursta = query.page(condition, visibleUser.getOrgIds(),visibleUser.getId(), 1, 1);
        if (processBusinessCursta.size() != 0) {
            return processBusinessCursta.get(0);
        }
        return null;
    }

    default List<ProcessBusinessCursta> findAllByCondition(ProcessBusinessCursta condition, VisibleUser visibleUser) {
        ProcessBusinessCurstaQuery query = new ProcessBusinessCurstaQuery();
        return query.findByCondition(condition, visibleUser.getOrgIds(),visibleUser.getId());
    }
// like 相关 
default ProcessBusinessCursta findProcessBusinessCurstaByLikeCondition(ProcessBusinessCursta condition) {
        ProcessBusinessCurstaQuery query = new ProcessBusinessCurstaQuery();
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

    default ProcessBusinessCursta findProcessBusinessCurstaByLikeConditionAndEqualCondition(ProcessBusinessCursta likeCondition, ProcessBusinessCursta equalCondition) {
        ProcessBusinessCurstaQuery query = new ProcessBusinessCurstaQuery();
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

    default List<ProcessBusinessCursta> pageWithLike(ProcessBusinessCursta processBusinessCursta, Integer page, Integer size) {
        ProcessBusinessCurstaQuery query = new ProcessBusinessCurstaQuery();
        String json = Json.toJson(processBusinessCursta);
        Map<String, Object> andCondition = Json.toMap(json);
        List<Triplet<String, String, Object>> params = new ArrayList<>();
        for (Map.Entry<String, Object> x : andCondition.entrySet()) {
            if (Objects.nonNull(x.getValue())) {
                params.add(new Triplet<>(x.getKey(), "like", "%" + x.getValue() + "%"));
            }
        }
        return query.findListModelByOperateSimpleAnd(params, page, size);
    }

    default List<ProcessBusinessCursta> pageWithLikeAndEqual(ProcessBusinessCursta likeProcessBusinessCursta, ProcessBusinessCursta equalProcessBusinessCursta, Integer page, Integer size) {
        ProcessBusinessCurstaQuery query = new ProcessBusinessCurstaQuery();
        String json = Json.toJson(likeProcessBusinessCursta);
        Map<String, Object> andCondition = Json.toMap(json);
        List<Triplet<String, String, Object>> params = new ArrayList<>();
        for (Map.Entry<String, Object> x : andCondition.entrySet()) {
            if (Objects.nonNull(x.getValue())) {
                params.add(new Triplet<>(x.getKey(), "like", "%" + x.getValue() + "%"));
            }
        }
        Map<String, Object> andCondition2 = Json.toMap(Json.toJson(equalProcessBusinessCursta));
        for (Map.Entry<String, Object> x : andCondition2.entrySet()) {
            if (Objects.nonNull(x.getValue())) {
                params.add(new Triplet<>(x.getKey(), "=", x.getValue()));
            }
        }
        return query.findListModelByOperateSimpleAnd(params, page, size);
    }

    default Long countWithLike(ProcessBusinessCursta processBusinessCursta) {
        ProcessBusinessCurstaQuery query = new ProcessBusinessCurstaQuery();
        String json = Json.toJson(processBusinessCursta);
        Map<String, Object> andCondition = Json.toMap(json);
        List<Triplet<String, String, Object>> params = new ArrayList<>();
        for (Map.Entry<String, Object> x : andCondition.entrySet()) {
            if (Objects.nonNull(x.getValue())) {
                params.add(new Triplet<>(x.getKey(), "like", "%" + x.getValue() + "%"));
            }
        }
        return query.count(params);
    }

    default Long countWithLikeAndEqual(ProcessBusinessCursta likeProcessBusinessCursta, ProcessBusinessCursta equalProcessBusinessCursta) {
        ProcessBusinessCurstaQuery query = new ProcessBusinessCurstaQuery();
        String json = Json.toJson(likeProcessBusinessCursta);
        Map<String, Object> andCondition = Json.toMap(json);
        List<Triplet<String, String, Object>> params = new ArrayList<>();
        for (Map.Entry<String, Object> x : andCondition.entrySet()) {
            if (Objects.nonNull(x.getValue())) {
                params.add(new Triplet<>(x.getKey(), "like", "%" + x.getValue() + "%"));
            }
        }
        Map<String, Object> andCondition2 = Json.toMap(Json.toJson(equalProcessBusinessCursta));
        for (Map.Entry<String, Object> x : andCondition2.entrySet()) {
            if (Objects.nonNull(x.getValue())) {
                params.add(new Triplet<>(x.getKey(), "=", x.getValue()));
            }
        }
        return query.count(params);
    }

    /**
     * 获取流程的当前节点情况
     *
     * @param processName
     * @param businessId
     * @return
     */
    List<ProcessDevHistory> getCurDevNode(String processName, Long businessId);

    /**
     * 判断某流程的当前节点是否可以流转到下一个节点
     *
     * @param historyId
     * @return
     */
    Boolean checkCirculation(Long historyId);

    Boolean checkCirculation(String processName, Long business);


    /**
     * 进入到流程
     */
    Boolean beginProcess(String processName, Long businessId);

    /**
     * 获取某个节点的所有下级节点
     */
    List<ProcessSide> getAllChildrenProcessSide(Long processNodeId);

    /**
     * 修改当前流程点
     */
    Boolean changeCurProcessHistoryIds(String processName, Long businessId, String hisIds);


    /**
     * 获取某个历史节点的所有下级节点的id
     */
    List<Long> getChildrenHis(Long historyId);

    /**
     * 获取当前状态的历史id列表
     *
     * @param businessId
     * @return
     */
    List<Long> getCurrentHisIds(String processName, Long businessId);

    /**
     * 获取当前状态的节点id列表
     * z
     *
     * @param businessId
     * @return
     */
    List<Long> getCurrentHisProcessNodeIds(String processName, Long businessId);

    /**
     * 获取当前所处的节点
     *
     * @param auditId
     * @return
     */
    List<ProcessNode> getCurrentHisProcessNodes(String processName, Long auditId);

    List<ProcessDevHistory> getLinkedProcessHistory(Long historyId);
//    List<ProcessDevHistory> getLinkedProcessHistoryByBusinessId(Long businessId);

    ProcessDevHistory getLastTargetProcessDevHistory(Long historyId, String name);

    /**
     * 从节点中获取流转的原始数据
     * @param processDevHistory
     * @return
     */
    String getMetaDataOfDevHistory(ProcessDevHistory processDevHistory);

    <T> T getMetaDataOfDevHistory(ProcessDevHistory processDevHistory,Class<T> t);

    /**
     * 更新节点的元数据
     * @param historyId
     * @param metadata
     */
    void updateMetaDataOfDevHistory(Long historyId, ProcessDevHistory processDevHistory, String metadata);

}
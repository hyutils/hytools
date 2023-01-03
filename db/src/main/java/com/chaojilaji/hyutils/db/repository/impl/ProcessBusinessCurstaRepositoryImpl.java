package com.chaojilaji.hyutils.db.repository.impl;

import com.chaojilaji.hyutils.db.accessinformation.AccessInformation;
import com.chaojilaji.hyutils.db.model.ProcessBusinessCursta;
import com.chaojilaji.hyutils.db.model.ProcessDevHistory;
import com.chaojilaji.hyutils.db.model.ProcessNode;
import com.chaojilaji.hyutils.db.model.ProcessSide;
import com.chaojilaji.hyutils.db.repository.ProcessBusinessCurstaRepository;
import com.chaojilaji.hyutils.db.repository.ProcessDevHistoryRepository;
import com.chaojilaji.hyutils.db.repository.ProcessNodeRepository;
import com.chaojilaji.hyutils.db.repository.ProcessSideRepository;
import com.chaojilaji.hyutils.dbcore.utils.DatetimeUtil;
import com.chaojilaji.hyutils.dbcore.utils.Json;
import org.javatuples.Triplet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Repository
public class ProcessBusinessCurstaRepositoryImpl implements ProcessBusinessCurstaRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    /**
     * 根据业务id和processName获取当前业务的最新流程节点信息
     *
     * @param processName
     * @param businessId
     * @return
     */
    @Override
    public List<ProcessDevHistory> getCurDevNode(String processName, Long businessId) {
        String sql = "select b.*\n" +
                "from process_business_cursta as a\n" +
                "         left join process_dev_history as b on string_to_array(b.id::varchar,',') <@ string_to_array(a.cur_history_id,',')\n" +
                "where a.process_name = :process_name\n" +
                "  and a.business_id = :business_id;";
        Map<String, Object> params = new HashMap<>();
        params.put("process_name", processName);
        params.put("business_id", businessId);
        List<ProcessDevHistory> processDevHistories = new ArrayList<>();
        List<Map<String, Object>> tmp = namedParameterJdbcTemplate.queryForList(sql, params);
        for (Map<String, Object> tmpA : tmp) {
            processDevHistories.add(Json.toObject(Json.toJson(tmpA), ProcessDevHistory.class));
        }
        return processDevHistories;
    }

    @Autowired
    private ProcessDevHistoryRepository processDevHistoryRepository;


    @Autowired
    private ProcessSideRepository processSideRepository;

    @Autowired
    private ProcessNodeRepository processNodeRepository;


    @Override
    public Boolean checkCirculation(Long historyId) {
        ProcessDevHistory processDevHistory = processDevHistoryRepository.findProcessDevHistoryById(historyId);
        if (Objects.nonNull(processDevHistory)) {
            // TODO: 2022/1/10 检查当前节点的情况，是否结束，只有上一个状态结束了才能接着往下走
            if (processDevHistory.getCurNodeSta().equals(2)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean checkCirculation(String processName, Long business) {
        List<ProcessDevHistory> processDevHistories = getCurDevNode(processName, business);
        int flag = 0;
        for (ProcessDevHistory processDevHistory : processDevHistories) {
            if (Objects.nonNull(processDevHistory)) {
                // TODO: 2022/1/10 检查当前节点的情况，是否结束，只有上一个状态结束了才能接着往下走
                if (!processDevHistory.getCurNodeSta().equals(2)) {
                    flag = 1;
                    break;
                }
            }
        }
        if (flag == 0) {
            return true;
        }
        return false;
    }


    /**
     * 开始一个流程
     * 就是把当前流程的开始节点加入到流程历史中
     *
     * @param processName
     * @param businessId
     * @return
     */
    @Override
    public Boolean beginProcess(String processName, Long businessId) {
        String sql = "select node.*\n" +
                "from process_tree as tree\n" +
                "         left join process_node as node on tree.id = node.process_id\n" +
                "where tree.name = :name and node.process_start = true;";
        Map<String, Object> params = new HashMap<>();
        params.put("name", processName);
        List<Map<String, Object>> tmp = namedParameterJdbcTemplate.queryForList(sql, params);
        ProcessNode processNode = Json.toObject(Json.toJson(tmp.get(0)), ProcessNode.class);
        if (Objects.nonNull(processNode)) {
            ProcessDevHistory processDevHistory = new ProcessDevHistory();
            Long time = DatetimeUtil.getTimestampOfDatetime(LocalDateTime.now());
            processDevHistory.setBeginTime(time);
            processDevHistory.setBusinessId(businessId);
            processDevHistory.setCurNodeId(processNode.getId());
            processDevHistory.setProcessId(processNode.getProcessId());
            processDevHistory.setCurNodeSta(0);
            processDevHistory.setUpdateTime(time);
            Long hisId = processDevHistoryRepository.save(processDevHistory);
            changeCurProcessHistoryIds(processName, businessId, hisId + "");
            return true;
        }
        return false;
    }

    @Override
    public List<ProcessSide> getAllChildrenProcessSide(Long processNodeId) {
        ProcessSide processSideCondition = new ProcessSide();
        processSideCondition.setFromNodeId(processNodeId);
        return processSideRepository.findAllByCondition(processSideCondition);
    }

    @Override
    public Boolean changeCurProcessHistoryIds(String processName, Long businessId, String hisIds) {
        // TODO: 2022/1/11 修改当前流程表
        ProcessBusinessCursta processBusinessCurstaCondition = new ProcessBusinessCursta();
        processBusinessCurstaCondition.setBusinessId(businessId);
        processBusinessCurstaCondition.setProcessName(processName);
        ProcessBusinessCursta processBusinessCursta = findProcessBusinessCurstaByCondition(processBusinessCurstaCondition);
        if (Objects.nonNull(processBusinessCursta)) {
            ProcessBusinessCursta processBusinessCursta1 = new ProcessBusinessCursta();
            processBusinessCursta1.setCurHistoryId(hisIds);
            update(processBusinessCurstaCondition, processBusinessCursta1);
        } else {
            processBusinessCurstaCondition.setCurHistoryId(hisIds);
            save(processBusinessCurstaCondition);
        }
        return true;
    }

    @Override
    public List<Long> getChildrenHis(Long historyId) {
        ProcessDevHistory condition = new ProcessDevHistory();
        condition.setFatherDevId(historyId);
        List<ProcessDevHistory> processDevHistories = processDevHistoryRepository.findAllByCondition(condition);
        List<Long> ans = new ArrayList<>();
        for (ProcessDevHistory processDevHistory : processDevHistories) {
            ans.add(processDevHistory.getId());
        }
        return ans;
    }


    @Override
    public List<Long> getCurrentHisIds(String processName, Long auditId) {
        List<Long> ans = new ArrayList<>();
        List<ProcessDevHistory> processDevHistories = getCurDevNode(processName, auditId);
        for (ProcessDevHistory processDevHistory : processDevHistories) {
            ans.add(processDevHistory.getId());
        }
        return ans;
    }

    @Override
    public List<Long> getCurrentHisProcessNodeIds(String processName, Long auditId) {
        List<Long> ans = new ArrayList<>();
        List<ProcessDevHistory> processDevHistories = getCurDevNode(processName, auditId);
        for (ProcessDevHistory processDevHistory : processDevHistories) {
            ans.add(processDevHistory.getCurNodeId());
        }
        return ans;
    }

    @Override
    public List<ProcessNode> getCurrentHisProcessNodes(String processName, Long auditId) {
        List<ProcessDevHistory> processDevHistories = getCurDevNode(processName, auditId);
        Map<String, List<Triplet<String, String, Object>>> condition = new HashMap<>();
        condition.put("id", new ArrayList<>());
        for (ProcessDevHistory processDevHistory : processDevHistories) {
            condition.get("id").add(new Triplet<>("id", "=", processDevHistory.getCurNodeId()));
        }
        return processNodeRepository.findAllWithIn(condition);
    }

    @Override
    public List<ProcessDevHistory> getLinkedProcessHistory(Long historyId) {
        String sql = "WITH RECURSIVE tree(id, process_id,from_node_id,to_node_id,father_dev_id,business_metadata) AS\n" +
                "                   (\n" +
                "                       SELECT a.id, a.process_id,a.from_node_id,a.to_node_id,a.father_dev_id,a.business_metadata\n" +
                "                       FROM process_dev_history a\n" +
                "                       WHERE id = :id\n" +
                "                       UNION ALL\n" +
                "                       SELECT k.id, k.process_id,k.from_node_id,k.to_node_id,k.father_dev_id,k.business_metadata\n" +
                "                       FROM process_dev_history k,\n" +
                "                            tree c\n" +
                "                       WHERE k.id = c.father_dev_id\n" +
                "                   )\n" +
                "SELECT *\n" +
                "FROM tree;";
        Map<String, Object> params = new HashMap<>();
        params.put("id", historyId);
        List<ProcessDevHistory> processDevHistories = new ArrayList<>();
        List<Map<String, Object>> tmp = namedParameterJdbcTemplate.queryForList(sql, params);
        for (Map<String, Object> tmpA : tmp) {
            processDevHistories.add(Json.toObject(Json.toJson(tmpA), ProcessDevHistory.class));
        }
        return processDevHistories;
    }

    /**
     * 获取最近一个目标状态
     *
     * @param historyId
     * @param name
     * @return
     */
    @Override
    public ProcessDevHistory getLastTargetProcessDevHistory(Long historyId, String name) {
        ProcessNode condition = new ProcessNode();
        condition.setNodeName(name);
        ProcessNode processNode = processNodeRepository.findProcessNodeByConditionWithCache(condition);
        if (Objects.isNull(processNode)) {
            return null;
        }
        return getLinkedProcessHistory(historyId).parallelStream().filter(processDevHistory -> {
            if (processDevHistory.getToNodeId().equals(processNode.getId())) {
                return true;
            }
            return false;
        }).collect(Collectors.toList()).get(0);
    }

    @Override
    public String getMetaDataOfDevHistory(ProcessDevHistory processDevHistory) {
        return Json.toObject(processDevHistory.getBusinessMetadata(), AccessInformation.class).getEnterInformation().getMessage();
    }

    @Override
    public <T> T getMetaDataOfDevHistory(ProcessDevHistory processDevHistory, Class<T> t) {
        return Json.toObject(getMetaDataOfDevHistory(processDevHistory), t);
    }

    @Override
    public void updateMetaDataOfDevHistory(Long historyId, ProcessDevHistory processDevHistory, String metadata) {
        if (Objects.isNull(processDevHistory)) {
            processDevHistory = processDevHistoryRepository.findProcessDevHistoryById(historyId);
        }
        if (Objects.isNull(historyId)) {
            historyId = processDevHistory.getId();
        }
        AccessInformation accessInformation = Json.toObject(processDevHistory.getBusinessMetadata(), AccessInformation.class);
        accessInformation.getEnterInformation().setMessage(metadata);
        ProcessDevHistory newHis = new ProcessDevHistory();
        newHis.setBusinessMetadata(Json.toJson(accessInformation));
        processDevHistoryRepository.updateById(historyId, newHis);
    }
}
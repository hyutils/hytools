package com.chaojilaji.hy.developutils.controller;

import com.chaojilaji.hy.developutils.query.process.ProcessTreeQuery;
import com.chaojilaji.hy.developutils.response.CommonResultStatus;
import com.chaojilaji.hy.developutils.response.Result;
import com.chaojilaji.hy.developutils.response.ResultV2;
import com.chaojilaji.hyutils.db.model.*;
import com.chaojilaji.hyutils.db.repository.*;
import com.chaojilaji.hyutils.dbcore.utils.ArrayStrUtil;
import com.chaojilaji.hyutils.dbcore.utils.DatetimeUtil;
import com.chaojilaji.hyutils.dbcore.utils.Json;
import com.chaojilaji.hyutils.dbcore.utils.StringFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 流程
 */
@RestController
@RequestMapping("/api/v2")
public class ProcessController {
    @Autowired
    private ProcessTreeRepository processTreeRepository;

    @Autowired
    private ProcessNodeRepository processNodeRepository;

    @Autowired
    private ProcessSideRepository processSideRepository;

    @Autowired
    private ProcessDevHistoryRepository processDevHistoryRepository;

    @Autowired
    private ProcessBusinessCurstaRepository processBusinessCurstaRepository;

    @GetMapping("/processes")
    public ResultV2 getExistProcess(@RequestParam("page") Integer page,
                                    @RequestParam("size") Integer size,
                                    @RequestParam("keyword") @Nullable String keyword) {
        ProcessTree.Builder builder = ProcessTree.builder();
        if (StringUtils.hasText(keyword)) {
            builder.descriptionLike(keyword);
        }
        return ResultV2.success(
                processTreeRepository.page(builder.build(), page, size).stream().map(processTree -> {
                    Map<String, Object> ans = new HashMap<>();
                    ans.put("id", processTree.getId().toString());
                    ans.put("description", processTree.getDescription());
                    ans.put("created_time", DatetimeUtil.getViewStrOfDatetime(processTree.getCreatedTime()));
                    ans.put("name", processTree.getName());
                    return ans;
                }).collect(Collectors.toList()),
                processTreeRepository.count(builder.build()).intValue());
    }

    @PostMapping("/process/add")
    public Result addProcessTree(@RequestBody ProcessTreeQuery query) {
        processTreeRepository.save(ProcessTree.builder()
                .description(query.getProcessDescription())
                .name(query.getName())
                .build());
        return Result.success(CommonResultStatus.OK);
    }

    @GetMapping("/process/nodes")
    public ResultV2 getProcessNodes(@RequestParam("page") Integer page,
                                    @RequestParam("size") Integer size,
                                    @RequestParam("keyword") @Nullable String keyword) {
        ProcessNode.Builder builder = ProcessNode.builder();
        if (StringUtils.hasText(keyword)) {
            ProcessTree processTree = processTreeRepository.findProcessTreeByCondition(ProcessTree.builder()
                    .name(keyword)
                    .build());
            if (Objects.nonNull(processTree)) {
                builder.processId(processTree.getId());
            }
        }
        return ResultV2.success(processNodeRepository.page(builder.build(), page, size).stream().map(processNode -> {
                    Map<String, Object> ans = Json.toMap(Json.toJson(processNode));
                    ProcessTree tree = processTreeRepository.findProcessTreeByIdWithCache(processNode.getProcessId());
                    ans.put("process_tree", tree.getName() + "-" + tree.getDescription());
                    return ans;
                }).collect(Collectors.toList()),
                processNodeRepository.count(builder.build()).intValue());
    }

    private static String exchangeNodeType(String type){
        if (type.equalsIgnoreCase("control")){
            return "流程控制节点";
        }else {
            return "业务节点";
        }

    }

    @GetMapping("/process/sides")
    public ResultV2 getProcessSides(@RequestParam("page") Integer page,
                                    @RequestParam("size") Integer size,
                                    @RequestParam("keyword") @Nullable String keyword) {
        ProcessSide.Builder builder = ProcessSide.builder();
        if (StringUtils.hasText(keyword)) {
            ProcessTree processTree = processTreeRepository.findProcessTreeByCondition(ProcessTree.builder()
                    .name(keyword)
                    .build());
            if (Objects.nonNull(processTree)) {
                builder.processId(processTree.getId());
            }
        }
        return ResultV2.success(processSideRepository.page(builder.build(), page, size).stream().map(processSide -> {
                    Map<String, Object> ans = Json.toMap(Json.toJson(processSide));
                    ProcessTree tree = processTreeRepository.findProcessTreeByIdWithCache(processSide.getProcessId());
                    ans.put("process_tree", tree.getName() + "-" + tree.getDescription());
                    ProcessNode fromNode = processNodeRepository.findProcessNodeByIdWithCache(processSide.getFromNodeId());
                    if (Objects.nonNull(fromNode)) {
                        ans.put("from_node",StringFormatUtils.formatByName("{node_name}（{type}）",new HashMap<String, Object>(){
                            {
                                put("node_name",fromNode.getNodeName());
                                put("type",exchangeNodeType(fromNode.getNodeType()));
                            }
                        }));
                    }
                    ProcessNode toNode = processNodeRepository.findProcessNodeByIdWithCache(processSide.getToNodeId());
                    if (Objects.nonNull(toNode)) {
                        ans.put("to_node",StringFormatUtils.formatByName("{node_name}（{type}）",new HashMap<String, Object>(){
                            {
                                put("node_name",toNode.getNodeName());
                                put("type",exchangeNodeType(toNode.getNodeType()));
                            }
                        }));
                    }
                    return ans;
                }).collect(Collectors.toList()),
                processSideRepository.count(builder.build()).intValue());
    }


    @GetMapping("/process/history")
    public ResultV2 getProcessHistory(@RequestParam("page") Integer page,
                                    @RequestParam("size") Integer size,
                                    @RequestParam("keyword") @Nullable String keyword) {
        ProcessBusinessCursta.Builder builder = ProcessBusinessCursta.builder();
        List<Long> businessIds = new ArrayList<>();
        if (StringUtils.hasText(keyword)) {
            ProcessTree processTree = processTreeRepository.findProcessTreeByCondition(ProcessTree.builder()
                    .name(keyword)
                    .build());
            if (Objects.nonNull(processTree)) {
                businessIds = processDevHistoryRepository.findAllByCondition(ProcessDevHistory.builder().processId(processTree.getId()).build()).stream().map(ProcessDevHistory::getId).collect(Collectors.toList());
            }
            if (businessIds.size()==0){
                businessIds.add(-1L);
            }
            builder.businessIdList(businessIds);
        }
//        if (businessIds.size()>0){
//        }
        return ResultV2.success(processBusinessCurstaRepository.page(builder.build(), page, size).stream().map(processBusinessCursta -> {
                    Map<String, Object> ans = Json.toMap(Json.toJson(processBusinessCursta));
                    ProcessDevHistory processDevHistory = processDevHistoryRepository.findProcessDevHistoryByIdWithCache(ArrayStrUtil.str2LArray(processBusinessCursta.getCurHistoryId()).get(0));
                    ProcessTree tree = processTreeRepository.findProcessTreeByIdWithCache(processDevHistory.getProcessId());
                    ans.put("process_tree", tree.getName() + "-" + tree.getDescription());
                    String currentNodes = processNodeRepository.findAllByCondition(ProcessNode.builder()
                            .idList(processBusinessCurstaRepository.getCurrentHisProcessNodeIds(processBusinessCursta.getProcessName(),processBusinessCursta.getBusinessId()))
                            .build()).stream().map(ProcessNode::getNodeName).collect(Collectors.joining(","));
                    ans.put("current_nodes",currentNodes);
                    return ans;
                }).collect(Collectors.toList()),
                processBusinessCurstaRepository.count(builder.build()).intValue());
    }


}

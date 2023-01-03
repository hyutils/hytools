package com.chaojilaji.hy.developutils.controller;

import com.chaojilaji.hy.developutils.FileUtils;
import com.chaojilaji.hy.developutils.FolderUtils;
import com.chaojilaji.hy.developutils.GenerateOrm;
import com.chaojilaji.hy.developutils.query.table.AddTableSql;
import com.chaojilaji.hy.developutils.response.CommonResultStatus;
import com.chaojilaji.hy.developutils.response.Result;
import com.chaojilaji.hy.developutils.response.ResultV2;
import com.chaojilaji.hyutils.dbcore.extension.cache.LruCacheWithTTLDateBase;
import com.chaojilaji.hyutils.dbcore.utils.ArrayStrUtil;
import com.chaojilaji.hyutils.dbcore.utils.Json;
import com.chaojilaji.hyutils.dbcore.utils.MD5Utils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v2")
public class TableController {

    private static final Log logger = LogFactory.getLog(TableController.class);

    @Autowired
    private LruCacheWithTTLDateBase lruCacheWithTTLDateBase;

    @PostMapping("/importtable")
    public Result importTable(@RequestBody AddTableSql addTableSql) {
        String content = addTableSql.getSql();
        String md5 = MD5Utils.compMd5(content);
//        String xxx = FileUtils.getFileContent2("table/" + md5 + ".txt", true);
//        if (StringUtils.hasText(xxx)){
//            lruCacheWithTTLDateBase.putCacheJsonWithTTL("TableController", "importTable", new HashMap<String, Object>() {
//                {
//                    put("md5", md5);
//                }
//            }, xxx, 3600 * 24);
//        }else {
        String tables = GenerateOrm.getTableStrsStr(ArrayStrUtil.str2Array(addTableSql.getSql(), "\n"));
        FileUtils.createFileWithRelativePath("table", md5 + ".txt", tables);
        lruCacheWithTTLDateBase.putCacheJsonWithTTL("TableController", "importTable", new HashMap<String, Object>() {
            {
                put("md5", md5);
            }
        }, tables, 3600 * 24);
//        }
        return Result.success(md5);
    }

    @GetMapping("/import/tables")
    public ResultV2 getTables(@RequestParam("md5") String md5) {
        String tmp = FileUtils.getFileContent2("table/" + md5 + ".txt", true);
        if (!StringUtils.hasText(tmp)) {
            tmp = lruCacheWithTTLDateBase.getCacheJsonWithTTl("TableController", "importTable", new HashMap<String, Object>() {
                {
                    put("md5", md5);
                }
            });
        }
        if (StringUtils.hasText(tmp)) {
            return ResultV2.success(Json.toObject(tmp, List.class).stream().map(a -> {
                Map<String, Object> x = Json.toMap(Json.toJson(a));
                if (!StringUtils.hasText(x.getOrDefault("name", "").toString())) {
                    return null;
                }
                x.put("children", x.getOrDefault("fields", new ArrayList<>()));
                x.put("type", "表名");
                return x;
            }).filter(a->{
                if (Objects.isNull(a))return false;
                return true;
            }).collect(Collectors.toList()));
        }
        return ResultV2.failure("请选择导入的md5值");
    }

    @GetMapping("/imports")
    public ResultV2 getAllImports() {
        return ResultV2.success(FolderUtils.getAllTxtNameInFolder("table", true));
    }

}

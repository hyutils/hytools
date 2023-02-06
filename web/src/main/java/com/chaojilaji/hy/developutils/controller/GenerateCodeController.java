package com.chaojilaji.hy.developutils.controller;

import com.chaojilaji.hy.developutils.FileUtils;
import com.chaojilaji.hy.developutils.FolderUtils;
import com.chaojilaji.hy.developutils.GenerateOrm;
import com.chaojilaji.hy.developutils.HttpUtils;
import com.chaojilaji.hy.developutils.filemodel.BuilderStruct;
import com.chaojilaji.hy.developutils.filemodel.OrmStruct;
import com.chaojilaji.hy.developutils.query.generate.EditBuilderQuery;
import com.chaojilaji.hy.developutils.response.CommonResultStatus;
import com.chaojilaji.hy.developutils.response.Result;
import com.chaojilaji.hy.developutils.response.ResultV2;
import com.chaojilaji.hy.developutils.utils.GenerateBuilder;
import com.chaojilaji.hy.developutils.vo.BuilderDetailVo;
import com.chaojilaji.hy.developutils.vo.BuilderListVo;
import com.chaojilaji.hy.developutils.vo.OrmDetailVo;
import com.chaojilaji.hy.developutils.vo.OrmListVo;
import com.chaojilaji.hyutils.dbcore.utils.ArrayStrUtil;
import com.chaojilaji.hyutils.dbcore.utils.DatetimeUtil;
import com.chaojilaji.hyutils.dbcore.utils.Json;
import com.chaojilaji.hyutils.dbcore.utils.MD5Utils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class GenerateCodeController {


    private static final Log logger = LogFactory.getLog(GenerateCodeController.class);


    @PostMapping("/api/v2/generatecode/builder/add")
    @ResponseBody
    public Result addBuilder(@Nullable @RequestParam("code_file") MultipartFile file,
                             @Nullable @RequestParam("code") String code,
                             @RequestParam("class_name") String className, HttpServletRequest request) {
        logger.info("addBuilder " + HttpUtils.getIpAddr(request));
        String content = "";
        if (Objects.nonNull(file)) {
            try {
                content = FileUtils.getFileContent(file.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            content = code;
        }
        String ans = GenerateBuilder.getBuilderStr(content);
        BuilderStruct builderStruct = new BuilderStruct();
        builderStruct.sourceContent = content;
        builderStruct.uuid = UUID.randomUUID().toString();
        builderStruct.time = DatetimeUtil.getViewStrOfDatetime(LocalDateTime.now());
        builderStruct.targetContent = ans;
        builderStruct.className = className;
        builderStruct.id = DatetimeUtil.getTimeStampOfViewStr(builderStruct.time).toString();
        if (Objects.nonNull(file)) {
            builderStruct.fileName = file.getOriginalFilename();
        } else {
            builderStruct.fileName = className;
        }
        FileUtils.createFileWithRelativePath("builder", builderStruct.id + ".json", Json.toJson(builderStruct));
        return Result.success(CommonResultStatus.OK);
    }


    @PostMapping("/api/v2/generatecode/builder")
    @ResponseBody
    public Result editBuilder(@RequestBody EditBuilderQuery query, HttpServletRequest request) {
        logger.info("editBuilder " + HttpUtils.getIpAddr(request));
        String id = query.getId();
        String code = query.getCode();
        String tmp = FileUtils.getFileContent2("builder/" + id + ".json", true);
        BuilderStruct old = Json.toObject(tmp, BuilderStruct.class);
        if (Objects.nonNull(old)) {
            String ans = GenerateBuilder.getBuilderStr(code);
            BuilderStruct builderStruct = new BuilderStruct();
            builderStruct.id = id;
            builderStruct.className = old.className;
            builderStruct.time = DatetimeUtil.getStrofTimestamp(Long.parseLong(id));
            builderStruct.sourceContent = code;
            builderStruct.uuid = UUID.randomUUID().toString();
            builderStruct.targetContent = ans;
            builderStruct.fileName = old.fileName;
            FileUtils.createFileWithRelativePath("builder", builderStruct.id + ".json", Json.toJson(builderStruct));
            return Result.success(new HashMap<String, Object>() {
                {
                    put("target_code", ans);
                    put("id", id);
                    put("name", old.className);
                }
            });
        }
        return Result.failure(CommonResultStatus.PARAM_WRONG);
    }

    @GetMapping("/api/v2/generatecode/builder")
    @ResponseBody
    public Result builderDetail(@RequestParam("id") String id, HttpServletRequest request) {
        logger.info("builderDetail " + HttpUtils.getIpAddr(request));

        String ans = FileUtils.getFileContent2("builder/" + id + ".json", true);
        if (StringUtils.hasText(ans)) {
            BuilderStruct builderStruct = Json.toObject(ans, BuilderStruct.class);
            if (Objects.nonNull(builderStruct)) {
                BuilderDetailVo vo = new BuilderDetailVo();
                vo.setCode(builderStruct.sourceContent);
                vo.setTargetCode(builderStruct.targetContent);
                vo.setName(builderStruct.className);
                vo.setId(builderStruct.id);
                return Result.success(vo);
            }
        }
        return Result.failure(CommonResultStatus.FAIL);
    }

    @GetMapping("/api/v2/generatecode/builder/download/{id}")
    public void buildDownload(@PathVariable("id") String id, HttpServletResponse resp, HttpServletRequest request) throws UnsupportedEncodingException {
        logger.info("buildDownload " + HttpUtils.getIpAddr(request));

        String ans = FileUtils.getFileContent2("builder/" + id + ".json", true);
        BuilderStruct builderStruct = Json.toObject(ans, BuilderStruct.class);
        if (Objects.nonNull(builderStruct)) {

            resp.setContentType("application/octet-stream");
            resp.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            resp.setContentType("application/octet-stream;charset=UTF-8;");
            resp.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(builderStruct.className, "UTF-8") + ".java");
            InputStream stream = new ByteArrayInputStream(builderStruct.getTargetContent().getBytes("UTF-8"));
            try {
                BufferedInputStream bis = new BufferedInputStream(stream);
                byte[] buff = new byte[1024];
                OutputStream os = resp.getOutputStream();
                int i = 0;
                while ((i = bis.read(buff)) != -1) {
                    os.write(buff, 0, i);
                    os.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            resp.setContentType("application/octet-stream");
            resp.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            resp.setContentType("application/octet-stream;charset=UTF-8;");
        }
    }


    @GetMapping("/api/v2/generatecode/builders")
    @ResponseBody
    public ResultV2 getBuilders(@Nullable @RequestParam("search_key") String searchKey, @RequestParam Integer page,
                                @RequestParam Integer size, HttpServletRequest request) {
        logger.info("getBuilders " + HttpUtils.getIpAddr(request));
        // TODO: 2022/11/15 做成前端分页
        List<String> fileNames = FolderUtils.getAllTypeNameInFolder("builder", true, "json");
        fileNames.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return -1*o1.compareTo(o2);
            }
        });
        List<BuilderListVo> vos = new ArrayList<>();
        for (String fileName : fileNames) {
            String content = FileUtils.getFileContent2("builder/" + fileName + ".json", true);
            BuilderStruct builderStruct = Json.toObject(content, BuilderStruct.class);
            BuilderListVo vo = new BuilderListVo();
            vo.setClassName(builderStruct.className);
            vo.setCreatedTime(builderStruct.time);
            vo.setFields(ArrayStrUtil.slist2Str(GenerateBuilder.getBuilderFields(builderStruct.sourceContent).keySet().stream().sorted().collect(Collectors.toList()), ","));
            vo.setFileName(builderStruct.fileName);
            vo.setId(builderStruct.id);
            vo.setTextMd5(MD5Utils.compMd5(builderStruct.sourceContent));
            vos.add(vo);
        }
        return ResultV2.success(vos, vos.size());
    }

    @DeleteMapping("/api/v2/generatecode/builder/{id}")
    @ResponseBody
    public Result deleteBuilder(@PathVariable("id") String id, HttpServletRequest request) {
        logger.info("deleteBuilder " + HttpUtils.getIpAddr(request));
        FileUtils.removeFile("builder", id + ".json", true);
        return Result.success(CommonResultStatus.OK);
    }


    @PostMapping("/api/v2/generatecode/orm/add")
    @ResponseBody
    public Result addOrm(@Nullable @RequestParam("code_file") MultipartFile file,
                         @Nullable @RequestParam("code") String code,
                         @RequestParam("base_jar_name") String baseJarName,
                         @RequestParam("db_jar_name") String dbJarName,
                         HttpServletRequest request) {
        logger.info("addOrm " + HttpUtils.getIpAddr(request));
        String content = "";
        if (Objects.nonNull(file)) {
            try {
                content = FileUtils.getFileContent(file.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            content = code;
        }
        String md5 = MD5Utils.compMd5(content);
        Long id = DatetimeUtil.getTimestampOfDatetime(LocalDateTime.now());
        String folderName = "orm/" + id;
        Integer size = GenerateOrm.generateOrmClass(content, false, dbJarName, baseJarName, folderName);
        OrmStruct.Builder builder = OrmStruct.builder()
                .id(id.toString())
                .md5(md5)
                .tableNumber(size)
                .resultPath(folderName + "/result.zip")
                .baseName(baseJarName)
                .packageName(dbJarName)
                .sourceCode(content).createTime(DatetimeUtil.getStrofTimestamp(id));
        if (Objects.nonNull(file)) {
            builder.fileName(file.getOriginalFilename());
        }
        FileUtils.createFileWithRelativePath("orm/result", id + ".json", Json.toJson(builder.build()));
        return Result.success(CommonResultStatus.OK);
    }


    @GetMapping("/api/v2/generatecode/orms")
    @ResponseBody
    public ResultV2 getOrms(@Nullable @RequestParam("search_key") String searchKey, @RequestParam Integer page,
                            @RequestParam Integer size, HttpServletRequest request) {
        logger.info("getOrms " + HttpUtils.getIpAddr(request));
        // TODO: 2022/11/15 做成前端分页
        List<String> fileNames = FolderUtils.getAllTypeNameInFolder("orm/result", true, "json");
        List<OrmListVo> vos = new ArrayList<>();
        for (String fileName : fileNames) {
            String content = FileUtils.getFileContent2("orm/result/" + fileName + ".json", true);
            OrmStruct ormStruct = Json.toObject(content, OrmStruct.class);
            vos.add(OrmListVo.builder()
                    .createTime(ormStruct.getCreateTime())
                    .fileName(ormStruct.getFileName())
                    .tableNumber(ormStruct.getTableNumber().toString())
                    .id(ormStruct.getId())
                    .textMd5(ormStruct.getMd5())
                    .build());
        }
        return ResultV2.success(vos, vos.size());
    }

    @PostMapping("/api/v2/generatecode/orm")
    @ResponseBody
    public Result editOrm(@RequestBody EditBuilderQuery query, HttpServletRequest request) {
        logger.info("editOrm " + HttpUtils.getIpAddr(request));
        String id = query.getId();
        String code = query.getCode();
        String tmp = FileUtils.getFileContent2("orm/result/" + id + ".json", true);
        OrmStruct old = Json.toObject(tmp, OrmStruct.class);
        if (Objects.nonNull(old)) {
            FileUtils.removeFile("orm/" + id + "/", "", true);
            Integer number = GenerateOrm.generateOrmClass(code,false,old.getPackageName(),old.getBaseName(),"orm/"+id);
            FileUtils.createFileWithRelativePath("orm/result", id + ".json", Json.toJson(OrmStruct.builder()
                    .tableNumber(number)
                    .fileName(old.getFileName())
                    .baseName(old.getBaseName())
                    .createTime(DatetimeUtil.getViewStrOfDatetime(LocalDateTime.now()))
                    .id(old.getId())
                    .md5(MD5Utils.compMd5(code))
                    .packageName(old.getPackageName())
                    .resultPath(old.getResultPath())
                    .sourceCode(code)
                    .build()));
            return Result.success(CommonResultStatus.OK);
        }
        return Result.failure(CommonResultStatus.PARAM_WRONG);
    }

    @GetMapping("/api/v2/generatecode/orm")
    @ResponseBody
    public Result ormDetail(@RequestParam("id") String id, HttpServletRequest request) {
        logger.info("ormDetail " + HttpUtils.getIpAddr(request));

        String ans = FileUtils.getFileContent2("orm/result/" + id + ".json", true);
        if (StringUtils.hasText(ans)) {
            OrmStruct ormStruct = Json.toObject(ans, OrmStruct.class);
            if (Objects.nonNull(ormStruct)) {
                return Result.success(OrmDetailVo.builder()
                        .code(ormStruct.getSourceCode())
                        .fileMd5(ormStruct.getMd5())
                        .fileName(ormStruct.getFileName())
                        .id(ormStruct.getId())
                        .metadataNumber("0")
                        .tableNumber(ormStruct.getTableNumber().toString())
                        .build());
            }
        }
        return Result.failure(CommonResultStatus.FAIL);
    }

    @GetMapping("/api/v2/generatecode/orm/download/{id}")
    public void ormDownload(@PathVariable("id") String id, HttpServletResponse resp, HttpServletRequest request) throws UnsupportedEncodingException {
        logger.info("ormDownload " + HttpUtils.getIpAddr(request));

        String ans = FileUtils.getFileContent2("orm/result/" + id + ".json", true);
        OrmStruct ormStruct = Json.toObject(ans, OrmStruct.class);
        if (Objects.nonNull(ormStruct)) {
            resp.setContentType("application/octet-stream");
            resp.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            resp.setContentType("application/octet-stream;charset=UTF-8;");

            resp.setHeader("Content-Disposition", "attachment;filename=result.zip");
            try {
                InputStream stream = new FileInputStream(new File(ormStruct.getResultPath()));
                BufferedInputStream bis = new BufferedInputStream(stream);
                byte[] buff = new byte[1024];
                OutputStream os = resp.getOutputStream();
                int i = 0;
                while ((i = bis.read(buff)) != -1) {
                    os.write(buff, 0, i);
                    os.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            resp.setContentType("application/octet-stream");
            resp.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            resp.setContentType("application/octet-stream;charset=UTF-8;");
        }
    }

    @DeleteMapping("/api/v2/generatecode/orm/{id}")
    @ResponseBody
    public Result deleteOrm(@PathVariable("id") String id, HttpServletRequest request) {
        logger.info("deleteOrm " + HttpUtils.getIpAddr(request));
        FileUtils.removeFile("orm/result", id + ".json", true);
        return Result.success(CommonResultStatus.OK);
    }

}

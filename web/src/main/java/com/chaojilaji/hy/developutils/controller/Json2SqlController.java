package com.chaojilaji.hy.developutils.controller;

import com.chaojilaji.hy.developutils.CompressionAndDecompressionUtils;
import com.chaojilaji.hy.developutils.FileUtils;
import com.chaojilaji.hy.developutils.FolderUtils;
import com.chaojilaji.hy.developutils.HttpUtils;
import com.chaojilaji.hy.developutils.filemodel.Json2SqlStruct;
import com.chaojilaji.hy.developutils.query.generate.EditBuilderQuery;
import com.chaojilaji.hy.developutils.response.CommonResultStatus;
import com.chaojilaji.hy.developutils.response.Result;
import com.chaojilaji.hy.developutils.response.ResultV2;
import com.chaojilaji.hy.developutils.utils.DuiJieUtils;
import com.chaojilaji.hy.developutils.utils.GenerateDto;
import com.chaojilaji.hy.developutils.utils.GenerateFile;
import com.chaojilaji.hy.developutils.vo.Json2SqlDetailVo;
import com.chaojilaji.hy.developutils.vo.Json2SqlListVo;
import com.chaojilaji.hyutils.dbcore.utils.DatetimeUtil;
import com.chaojilaji.hyutils.dbcore.utils.Json;
import com.chaojilaji.hyutils.dbcore.utils.MD5Utils;
import com.chaojilaji.hyutils.dbcore.utils.StringFormatUtils;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Controller
public class Json2SqlController {

    private static final Log logger = LogFactory.getLog(Json2SqlController.class);


    @PostMapping("/api/v2/generatecode/json2sql/add")
    @ResponseBody
    public Result addJson2Sql(@Nullable @RequestParam("code_file") MultipartFile file,
                              @Nullable @RequestParam("code") String code,
                              @RequestParam("base_name") String baseName,
                              HttpServletRequest request) {
        logger.info("addJson2Sql " + HttpUtils.getIpAddr(request));
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
        String folderName = "json2sql/" + id;
        List<GenerateDto> generateDtos = DuiJieUtils.recursionJson(content, baseName);
        for (GenerateDto dto : generateDtos) {
            FileUtils.appendFileWithRelativePath(folderName, "create_table.sql", dto.createSql + "\n");
        }

        Integer size = generateDtos.size();
        Json2SqlStruct.Builder builder = Json2SqlStruct.builder()
                .id(id.toString())
                .md5(md5)
                .resultPath(folderName + "/create_table.sql")
                .classNumber(size)
                .className(baseName)
                .sourceCode(content).createTime(DatetimeUtil.getStrofTimestamp(id));
        if (Objects.nonNull(file)) {
            builder.fileName(file.getOriginalFilename());
        }
        FileUtils.createFileWithRelativePath("json2sql/result", id + ".json", Json.toJson(builder.build()));
        return Result.success(CommonResultStatus.OK);
    }


    @GetMapping("/api/v2/generatecode/json2sqls")
    @ResponseBody
    public ResultV2 getJson2Sqls(@Nullable @RequestParam("search_key") String searchKey, @RequestParam Integer page,
                                 @RequestParam Integer size, HttpServletRequest request) {
        logger.info("getJson2Sqls " + HttpUtils.getIpAddr(request));
        // TODO: 2022/11/15 做成前端分页
        List<String> fileNames = FolderUtils.getAllTypeNameInFolder("json2sql/result", true, "json");
        List<Json2SqlListVo> vos = new ArrayList<>();
        for (String fileName : fileNames) {
            String content = FileUtils.getFileContent2("json2sql/result/" + fileName + ".json", true);
            Json2SqlStruct json2sqlStruct = Json.toObject(content, Json2SqlStruct.class);
            vos.add(Json2SqlListVo.builder()
                    .createTime(json2sqlStruct.getCreateTime())
                    .baseName(json2sqlStruct.getClassName())
                    .classNumber(json2sqlStruct.getClassNumber().toString())
                    .id(json2sqlStruct.getId())
                    .jsonCode(json2sqlStruct.getSourceCode())
                    .build());
        }
        return ResultV2.success(vos, vos.size());
    }

    @PostMapping("/api/v2/generatecode/json2sql")
    @ResponseBody
    public Result editJson2Sql(@RequestBody EditBuilderQuery query, HttpServletRequest request) {
        logger.info("editJson2Sql " + HttpUtils.getIpAddr(request));
        String id = query.getId();
        String code = query.getCode();
        String tmp = FileUtils.getFileContent2("json2sql/result/" + id + ".json", true);
        Json2SqlStruct old = Json.toObject(tmp, Json2SqlStruct.class);
        if (Objects.nonNull(old)) {
            FileUtils.removeFile("json2sql/" + id + "/", "", true);

            String folderName = "json2sql/" + id;
            List<GenerateDto> generateDtos = DuiJieUtils.recursionJson(code, old.getClassName());
            List<String> files = new ArrayList<>();
            try {
                String exchangeName = GenerateFile.createExchange(generateDtos, folderName, StringFormatUtils.snake(old.getClassName().toLowerCase(), true));
                files.add(exchangeName);
                for (GenerateDto dto : generateDtos) {
                    String dtoName = GenerateFile.createDto(dto, folderName);
                    files.add(dtoName);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                CompressionAndDecompressionUtils.zipCompression(files, folderName + "/result.zip", true);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Integer size = files.size();
            Json2SqlStruct.Builder builder = Json2SqlStruct.builder()
                    .id(id.toString())
                    .md5(old.getMd5())
                    .resultPath(folderName + "/result.zip")
                    .classNumber(size)
                    .sourceCode(code).createTime(DatetimeUtil.getStrofTimestamp(Long.parseLong(id)));
            FileUtils.createFileWithRelativePath("json2sql/result", id + ".json", Json.toJson(builder.build()));
            return Result.success(CommonResultStatus.OK);
        }
        return Result.failure(CommonResultStatus.PARAM_WRONG);
    }

    @GetMapping("/api/v2/generatecode/json2sql")
    @ResponseBody
    public Result json2sqlDetail(@RequestParam("id") String id, HttpServletRequest request) {
        logger.info("json2sqlDetail " + HttpUtils.getIpAddr(request));

        String ans = FileUtils.getFileContent2("json2sql/result/" + id + ".json", true);
        if (StringUtils.hasText(ans)) {
            Json2SqlStruct json2sqlStruct = Json.toObject(ans, Json2SqlStruct.class);
            if (Objects.nonNull(json2sqlStruct)) {
                return Result.success(Json2SqlDetailVo.builder()
                        .code(json2sqlStruct.getSourceCode())
                        .fileMd5(json2sqlStruct.getMd5())
                        .fileName(json2sqlStruct.getFileName())
                        .id(json2sqlStruct.getId())
                        .className(json2sqlStruct.getClassName())
                        .createTime(json2sqlStruct.getCreateTime())
                        .sql(FileUtils.getFileContent2(json2sqlStruct.getResultPath(),true))
                        .build());
            }
        }
        return Result.failure(CommonResultStatus.FAIL);
    }

    @GetMapping("/api/v2/generatecode/json2sql/download/{id}")
    public void json2sqlDownload(@PathVariable("id") String id, HttpServletResponse resp, HttpServletRequest request) throws UnsupportedEncodingException {
        logger.info("json2sqlDownload " + HttpUtils.getIpAddr(request));

        String ans = FileUtils.getFileContent2("json2sql/result/" + id + ".json", true);
        Json2SqlStruct json2sqlStruct = Json.toObject(ans, Json2SqlStruct.class);
        if (Objects.nonNull(json2sqlStruct)) {
            resp.setContentType("application/octet-stream");
            resp.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            resp.setContentType("application/octet-stream;charset=UTF-8;");
            String tmp = FileUtils.getFileContent2("json2sql/result/" + id + ".json", true);
            Json2SqlStruct old = Json.toObject(tmp, Json2SqlStruct.class);
            resp.setHeader("Content-Disposition", StringFormatUtils.formatByName("attachment;filename={className}_result.zip",new HashMap<String, Object>(){
                {
                    put("className",old.getClassName());
                }
            }));
            try {
                InputStream stream = new FileInputStream(new File(json2sqlStruct.getResultPath()));
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

    @DeleteMapping("/api/v2/generatecode/json2sql/{id}")
    @ResponseBody
    public Result deleteJson2Sql(@PathVariable("id") String id, HttpServletRequest request) {
        logger.info("deleteJson2Sql " + HttpUtils.getIpAddr(request));
        FileUtils.removeFile("json2sql/result", id + ".json", true);
        return Result.success(CommonResultStatus.OK);
    }


}

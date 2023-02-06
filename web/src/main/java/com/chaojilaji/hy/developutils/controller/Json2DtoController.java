package com.chaojilaji.hy.developutils.controller;

import com.chaojilaji.hy.developutils.CompressionAndDecompressionUtils;
import com.chaojilaji.hy.developutils.FileUtils;
import com.chaojilaji.hy.developutils.FolderUtils;
import com.chaojilaji.hy.developutils.HttpUtils;
import com.chaojilaji.hy.developutils.filemodel.Json2DtoStruct;
import com.chaojilaji.hy.developutils.query.generate.EditBuilderQuery;
import com.chaojilaji.hy.developutils.response.CommonResultStatus;
import com.chaojilaji.hy.developutils.response.Result;
import com.chaojilaji.hy.developutils.response.ResultV2;
import com.chaojilaji.hy.developutils.utils.DuiJieUtils;
import com.chaojilaji.hy.developutils.utils.GenerateDto;
import com.chaojilaji.hy.developutils.utils.GenerateFile;
import com.chaojilaji.hy.developutils.vo.Json2DtoDetailVo;
import com.chaojilaji.hy.developutils.vo.Json2DtoListVo;
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
public class Json2DtoController {

    private static final Log logger = LogFactory.getLog(Json2DtoController.class);


    @PostMapping("/api/v2/generatecode/json2dto/add")
    @ResponseBody
    public Result addJson2Dto(@Nullable @RequestParam("code_file") MultipartFile file,
                              @Nullable @RequestParam("code") String code,
                              @RequestParam("base_name") String baseName,
                              HttpServletRequest request) {
        logger.info("addJson2Dto " + HttpUtils.getIpAddr(request));
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
        String folderName = "json2dto/" + id;
        List<GenerateDto> generateDtos = DuiJieUtils.recursionJson(content, baseName);
        List<String> files = new ArrayList<>();
        try {
            String exchangeName = GenerateFile.createExchange(generateDtos, folderName, StringFormatUtils.snake(baseName.toLowerCase(), true));
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
        Json2DtoStruct.Builder builder = Json2DtoStruct.builder()
                .id(id.toString())
                .md5(md5)
                .resultPath(folderName + "/result.zip")
                .classNumber(size)
                .className(baseName)
                .sourceCode(content).createTime(DatetimeUtil.getStrofTimestamp(id));
        if (Objects.nonNull(file)) {
            builder.fileName(file.getOriginalFilename());
        }
        FileUtils.createFileWithRelativePath("json2dto/result", id + ".json", Json.toJson(builder.build()));
        return Result.success(CommonResultStatus.OK);
    }


    @GetMapping("/api/v2/generatecode/json2dtos")
    @ResponseBody
    public ResultV2 getJson2Dtos(@Nullable @RequestParam("search_key") String searchKey, @RequestParam Integer page,
                                 @RequestParam Integer size, HttpServletRequest request) {
        logger.info("getJson2Dtos " + HttpUtils.getIpAddr(request));
        // TODO: 2022/11/15 做成前端分页
        List<String> fileNames = FolderUtils.getAllTypeNameInFolder("json2dto/result", true, "json");
        List<Json2DtoListVo> vos = new ArrayList<>();
        for (String fileName : fileNames) {
            String content = FileUtils.getFileContent2("json2dto/result/" + fileName + ".json", true);
            Json2DtoStruct json2dtoStruct = Json.toObject(content, Json2DtoStruct.class);
            vos.add(Json2DtoListVo.builder()
                    .createTime(json2dtoStruct.getCreateTime())
                    .baseName(json2dtoStruct.getClassName())
                    .classNumber(json2dtoStruct.getClassNumber().toString())
                    .id(json2dtoStruct.getId())
                    .jsonCode(json2dtoStruct.getSourceCode())
                    .build());
        }
        return ResultV2.success(vos, vos.size());
    }

    @PostMapping("/api/v2/generatecode/json2dto")
    @ResponseBody
    public Result editJson2Dto(@RequestBody EditBuilderQuery query, HttpServletRequest request) {
        logger.info("editJson2Dto " + HttpUtils.getIpAddr(request));
        String id = query.getId();
        String code = query.getCode();
        String tmp = FileUtils.getFileContent2("json2dto/result/" + id + ".json", true);
        Json2DtoStruct old = Json.toObject(tmp, Json2DtoStruct.class);
        if (Objects.nonNull(old)) {
            FileUtils.removeFile("json2dto/" + id + "/", "", true);

            String folderName = "json2dto/" + id;
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
            Json2DtoStruct.Builder builder = Json2DtoStruct.builder()
                    .id(id.toString())
                    .md5(old.getMd5())
                    .resultPath(folderName + "/result.zip")
                    .classNumber(size)
                    .sourceCode(code).createTime(DatetimeUtil.getStrofTimestamp(Long.parseLong(id)));
            FileUtils.createFileWithRelativePath("json2dto/result", id + ".json", Json.toJson(builder.build()));
            return Result.success(CommonResultStatus.OK);
        }
        return Result.failure(CommonResultStatus.PARAM_WRONG);
    }

    @GetMapping("/api/v2/generatecode/json2dto")
    @ResponseBody
    public Result json2dtoDetail(@RequestParam("id") String id, HttpServletRequest request) {
        logger.info("json2dtoDetail " + HttpUtils.getIpAddr(request));

        String ans = FileUtils.getFileContent2("json2dto/result/" + id + ".json", true);
        if (StringUtils.hasText(ans)) {
            Json2DtoStruct json2dtoStruct = Json.toObject(ans, Json2DtoStruct.class);
            if (Objects.nonNull(json2dtoStruct)) {
                return Result.success(Json2DtoDetailVo.builder()
                        .code(json2dtoStruct.getSourceCode())
                        .fileMd5(json2dtoStruct.getMd5())
                        .fileName(json2dtoStruct.getFileName())
                        .id(json2dtoStruct.getId())
                        .className(json2dtoStruct.getClassName())
                        .createTime(json2dtoStruct.getCreateTime())
                        .build());
            }
        }
        return Result.failure(CommonResultStatus.FAIL);
    }

    @GetMapping("/api/v2/generatecode/json2dto/download/{id}")
    public void json2dtoDownload(@PathVariable("id") String id, HttpServletResponse resp, HttpServletRequest request) throws UnsupportedEncodingException {
        logger.info("json2dtoDownload " + HttpUtils.getIpAddr(request));

        String ans = FileUtils.getFileContent2("json2dto/result/" + id + ".json", true);
        Json2DtoStruct json2dtoStruct = Json.toObject(ans, Json2DtoStruct.class);
        if (Objects.nonNull(json2dtoStruct)) {
            resp.setContentType("application/octet-stream");
            resp.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            resp.setContentType("application/octet-stream;charset=UTF-8;");
            String tmp = FileUtils.getFileContent2("json2dto/result/" + id + ".json", true);
            Json2DtoStruct old = Json.toObject(tmp, Json2DtoStruct.class);
            resp.setHeader("Content-Disposition", StringFormatUtils.formatByName("attachment;filename={className}_result.zip",new HashMap<String, Object>(){
                {
                    put("className",old.getClassName());
                }
            }));
            try {
                InputStream stream = new FileInputStream(new File(json2dtoStruct.getResultPath()));
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

    @DeleteMapping("/api/v2/generatecode/json2dto/{id}")
    @ResponseBody
    public Result deleteJson2Dto(@PathVariable("id") String id, HttpServletRequest request) {
        logger.info("deleteJson2Dto " + HttpUtils.getIpAddr(request));
        FileUtils.removeFile("json2dto/result", id + ".json", true);
        return Result.success(CommonResultStatus.OK);
    }


}

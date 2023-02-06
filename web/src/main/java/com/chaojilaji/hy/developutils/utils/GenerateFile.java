package com.chaojilaji.hy.developutils.utils;




import com.chaojilaji.hy.developutils.FileUtils;
import com.chaojilaji.hyutils.dbcore.utils.StringFormatUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GenerateFile {


    /**
     * 生成对应的dto
     *
     * @param generateDto
     */
    public static String createDto(GenerateDto generateDto, String folder) throws IOException {
        String dtoFields = generateDto.dto;


        String ans = "import java.util.Map;\n" +
                "import java.util.List;\n";
        ans = ans + "\npublic class " + generateDto.name + " {\n" +
                dtoFields
                + "}";
        return FileUtils.createFileWithRelativePath(folder, generateDto.name + ".java", ans);
    }


    public static void createFile2(String folder, String fileName, String value) throws IOException {
        File directory = new File(".");
        FileUtils.createFile(directory.getCanonicalPath() + "/" + folder, fileName, value);
    }

    /**
     * 生成对应的insertSql
     *
     * @param generateDto
     */
    public static void createSql(GenerateDto generateDto, String folder, String name) {

        FileUtils.createFileWithRelativePath(folder, name, createSqlStr(generateDto));
    }

    public static void appendCreateSql(GenerateDto generateDto, String folder, String name) {
        FileUtils.appendFileWithRelativePath(folder, name, createSqlStr(generateDto));
    }

    public static Set<String> normalManagerField = new HashSet<String>() {
        {
            add("id");
            add("created_id");
            add("created_name");
            add("created_time");
            add("tag");
            add("deleted_mark");
            add("deleted_name");
            add("deleted_id");
            add("deleted_time");
            add("modified_time");
            add("modified_name");
            add("modified_id");
        }
    };

    public static String createSqlStr(GenerateDto generateDto) {
        String body = generateDto.createSql;
        String ans = "CREATE TABLE IF NOT EXISTS " + StringFormatUtils.camel(generateDto.name).toLowerCase() + "\n" +
                "(\n" +
                "    id                    bigint PRIMARY KEY   DEFAULT random_id(),\n" +
                body +
                "    --通用管理信息\n" +
                "    created_id            bigint       NOT NULL DEFAULT '0',         --创建人标识\n" +
                "    created_name          varchar(64)  NOT NULL DEFAULT '--',        --创建人姓名\n" +
                "    created_time          timestamp    NOT NULL DEFAULT now(),       --创建时间\n" +
                "    modified_id           bigint       NULL,                         --修改人标识\n" +
                "    modified_name         varchar(64)  NULL,                         --修改人姓名\n" +
                "    modified_time         timestamp    NULL,                         --修改时间\n" +
                "    deleted_id            bigint       NULL,                         --删除人标识\n" +
                "    deleted_name          varchar(64)  NULL,                         --删除人姓名\n" +
                "    deleted_time          timestamp    NULL,                         --删除时间\n" +
                "    deleted_mark          boolean      NOT NULL DEFAULT false,       --删除标记\n" +
                "    tag                   int4         NOT NULL DEFAULT '0'          --删除标记\n" +
                "    created_org_id   bigint      NOT NULL DEFAULT '0',         --创建组织标识\n" +
                "    created_org_name varchar(512) NOT NULL DEFAULT '--',       --创建组织名\n" +
                "    modified_org_id  bigint      NOT NULL DEFAULT '0',         --更新组织标识\n" +
                "    modified_org_name varchar(512) NOT NULL DEFAULT '--',      --更新组织名\n" +
                "    deleted_org_id   bigint      NOT NULL DEFAULT '0',         --删除组织标识\n" +
                "    deleted_org_name varchar(512) NOT NULL DEFAULT '--',       --删除组织名\n" +
                ");\n\n";
        return ans;
    }

    public static void createSql(List<GenerateDto> generateDtos, String folder, String name) {
        StringBuilder ans = new StringBuilder();
        for (GenerateDto generateDto : generateDtos) {
            ans.append(createSqlStr(generateDto)).append("\n");
        }
        FileUtils.createFileWithRelativePath(folder, name, ans.toString());
    }

    /**
     * 生成对应的exchange
     *
     * @param generateDtos
     */
    public static String createExchange(List<GenerateDto> generateDtos, String folder, String name) throws IOException {
        StringBuilder ans = new StringBuilder();
        ans.append("import java.util.ArrayList;\n")
                .append("import java.util.HashMap;\n")
                .append("import java.util.List;\n")
                .append("import java.util.Map;\n")
                .append("\n")
                .append("public class ").append(name).append("Exchange {\n");
        for (GenerateDto generateDto : generateDtos) {
            ans.append(generateDto.exchange).append("\n");
        }
        ans.append("}\n");
        return FileUtils.createFileWithRelativePath(folder, name + "Exchange.java", ans.toString());
    }
}

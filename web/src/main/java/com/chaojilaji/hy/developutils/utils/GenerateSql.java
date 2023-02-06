package com.chaojilaji.hy.developutils.utils;

import com.chaojilaji.hyutils.dbcore.utils.ArrayStrUtil;
import com.chaojilaji.hyutils.dbcore.utils.DataStructUtils;
import com.chaojilaji.hyutils.dbcore.utils.Json;
import com.chaojilaji.hyutils.dbcore.utils.StringFormatUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;

public class GenerateSql {

    public static String createSql() {
        return "    {key} {type} NOT NULL DEFAULT '{default}',         --";
    }

    /**
     * 转换参数类型
     *
     * @param y
     * @param tmp
     * @return
     */
    public static Map<String, Object> exchangeParamType(String y, Map<String, Object> tmp) {
        Map<String, Object> params = new HashMap<>();
        String a = StringFormatUtils.camel(y);
        if (GenerateFile.normalManagerField.contains(a)) {
            return null;
        }
        params.put("key", a);
        if (tmp.get(y) instanceof String) {
            params.put("type", "varchar(200)");
            params.put("default", "");
        } else if (tmp.get(y) instanceof Integer) {
            params.put("type", "int");
            params.put("default", "0");
        } else if (tmp.get(y) instanceof Map) {
            params.put("type", "text");
            params.put("default", "{}");
        } else if (tmp.get(y) instanceof List) {
            params.put("type", "text");
            params.put("default", "");
        } else if (tmp.get(y) instanceof Boolean) {
            params.put("type", "boolean");
            params.put("default", "false");
        } else if (tmp.get(y) instanceof Long) {
            params.put("type", "bigint");
            params.put("default", "0");
        } else if (tmp.get(y) instanceof Float || tmp.get(y) instanceof Double) {
        } else {
        }
        return params;
    }

    /**
     * 生成insertSql
     *
     * @param tmp
     * @return
     */
    public static String generateCreateSql(Map<String, Object> tmp) {
        Set<String> x = tmp.keySet();
        List<String> tmp123 = new ArrayList<>();
        tmp123.addAll(x);
        Collections.sort(tmp123);
        StringBuilder ans = new StringBuilder();
        for (String y : tmp123) {
            Map<String, Object> params = exchangeParamType(y, tmp);
            if (Objects.isNull(params)) {
                continue;
            }
            String a = StringFormatUtils.formatByName(createSql(), params);
            if (StringUtils.hasText(a)) {
                ans.append(a).append("\n");
            }
        }
        ans.append("\n");
        return ans.toString();
    }

    /**
     * 根据单个json生成对应的sql
     *
     * @param json
     * @return
     */
    public static String createCreateSql(String json) {
        Map<String, Object> a = Json.toMap(json);
        if (Objects.nonNull(a)) {
            return generateCreateSql(a);
        }
        return "";
    }

    /**
     * 根据一系列json生成对应的插入sql
     *
     * @param jsons
     * @return
     */
    public static String createCreateSqlWithListSql(List<String> jsons) {
        Map<String, Object> a = new HashMap<>();
        for (String json : jsons) {
            Map<String, Object> b = Json.toMap(json);
            if (Objects.nonNull(b)) {
                a = DataStructUtils.heBIngMap(a, b);
            }
        }
        return generateCreateSql(a);
    }

    /**
     * 根据map里面的键生成insertSql
     *
     * @param map
     * @return
     */
    public static String createCreateSqlWithMap(Map<String, Object> map) {
        return generateCreateSql(map);
    }

    /**
     * 根据一系列map里面键生成 insertSql
     *
     * @param maps
     * @return
     */
    public static String createCreateSqlWithListMaps(List<Map<String, Object>> maps) {
        return generateCreateSql(DataStructUtils.heBingListMap(maps));
    }


    public static String getInListMapSql(List<String> paramOrders, List<Map<String, Object>> a) {
        List<String> ans = new ArrayList<>();
        for (Map<String, Object> b : a) {
            StringBuilder d = new StringBuilder("(");
            int cnt = 0;
            for (String param : paramOrders) {
                if (cnt != 0) {
                    d.append(",");
                }
                if (b.get(param) instanceof Integer
                        || b.get(param) instanceof Long
                        || b.get(param) instanceof Float
                        || b.get(param) instanceof Double
                        || b.get(param) instanceof BigDecimal) {
                    d.append(b.get(param));
                } else {
                    d.append("'").append(b.get(param)).append("'");
                }
                cnt++;
            }
            d.append(")");
            ans.add(d.toString());
        }
        return ArrayStrUtil.slist2Str(ans, ",");
    }

    public static void getAlterTableSql(String table, String sql) {
        String[] a = sql.split("\n");
        for (String b : a) {
            b = b.trim();
            if (StringUtils.hasText(b)) {
                if (!b.startsWith("--")) {
                    String column = b.split(" +")[0];
                    String type = b.split(" +")[1];
                    String defaultValue = b.replace(column, "").replace(type,"").trim().split(",")[0];
                    String x = StringFormatUtils.formatByName("ALTER TABLE {table}\n" +
                            "    ADD COLUMN {column} {type} {default};", new HashMap<String, Object>() {
                        {
                            put("column", column);
                            put("type", type);
                            put("default", defaultValue);
                            put("table", table);
                        }
                    });
                    System.out.println(x);
                }
            }
        }
    }

}

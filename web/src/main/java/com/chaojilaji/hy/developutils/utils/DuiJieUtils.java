package com.chaojilaji.hy.developutils.utils;


import com.chaojilaji.hyutils.dbcore.utils.DataStructUtils;
import com.chaojilaji.hyutils.dbcore.utils.Json;
import com.chaojilaji.hyutils.dbcore.utils.StringFormatUtils;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.*;

/**
 * 对接
 */
public class DuiJieUtils {

    /**
     * 递归遍历一个对接返回的Json响应值
     *
     * @param json
     * @param name
     */
    public static List<GenerateDto> recursionJson(String json, String name) {
        List<GenerateDto> ans = new ArrayList<>();
        Set<String> names = new HashSet<>();
        getAllDtoNames(Json.toMap(json), name, names);
        recursionMap(Json.toMap(json), name, ans, names);
        return ans;
    }

    public static List<GenerateDto> recursionJsons(List<String> jsons, String name) {
        if (jsons.size() == 1){
            return recursionJson(jsons.get(0),name);
        }
        List<Map<String, Object>> a = new ArrayList<>();
        for (String json : jsons) {
            try {
                Map<String, Object> b = Json.toMap(json);
                a.add(b);
            } catch (Exception e) {

            }
        }
        List<GenerateDto> ans = new ArrayList<>();
        Set<String> names = new HashSet<>();
        getAllDtoNames(DataStructUtils.heBingListMap(a), name, names);
        recursionMap(DataStructUtils.heBingListMap(a), name, ans, names);
        return ans;
    }

    /**
     * 获取所有dto的名字
     *
     * @param params
     * @param name
     * @param names
     */
    public static void getAllDtoNames(Map<String, Object> params, String name, Set<String> names) {
        names.add(name);
        if (Objects.nonNull(params)){
            for (Map.Entry<String, Object> a : params.entrySet()) {
                if (a.getValue() instanceof Map) {
                    getAllDtoNames((Map<String, Object>) a.getValue(), name + StringFormatUtils.snake(a.getKey().toLowerCase(), true), names);
                }
                if (a.getValue() instanceof List) {
                    try {
                        List<Map<String, Object>> value = (List<Map<String, Object>>) a.getValue();
                        getAllDtoNames(DataStructUtils.heBingListMap(value), name + StringFormatUtils.snake(a.getKey().toLowerCase(), true), names);
                    } catch (Exception e) {

                    }
                }
            }
        }
    }


    /**
     * 递归遍历一个对接返回的Map响应值
     *
     * @param params
     * @param name
     */
    public static void recursionMap(Map<String, Object> params, String name, List<GenerateDto> dtos, Set<String> names) {
        GenerateDto ans = new GenerateDto();
        ans.dto = StringFormatUtils.generateDto(params, name, names);
        ans.exchange = StringFormatUtils.getExchangeFromMap(params, name);
        ans.createSql = GenerateSql.generateCreateSql(params);
        ans.name = name;
        ans.params = StringFormatUtils.getKeyTypeFromMap(params);
        dtos.add(ans);
        for (Map.Entry<String, Object> a : params.entrySet()) {
            if (a.getValue() instanceof Map) {
                recursionMap((Map<String, Object>) a.getValue(), name + StringFormatUtils.snake(a.getKey().replace(" ","").toLowerCase(), true), dtos, names);
            }
            if (a.getValue() instanceof List) {
                try {
                    List<Map<String, Object>> value = (List<Map<String, Object>>) a.getValue();
                    recursionMap(DataStructUtils.heBingListMap(value), name + StringFormatUtils.snake(a.getKey().replace(" ","").toLowerCase(), true), dtos, names);
                } catch (Exception e) {

                }
            }
        }
    }

    public static String getApiStr() {
        return "    {name}(\"{url}\", \"{method}\",\"{info}\"),\n";
    }






    private static String generateApiServerStr(String body) {
        String apiServerStr = "import java.util.*;\n" +
                "\n" +
                "@Service\n" +
                "public class ApiService {\n" +
                "    private final static Log logger = LogFactory.getLog(ApiService.class);\n" +
                "\n" +
                "    private Map<String, Object> removeNull(Map<String, Object> tmp) {\n" +
                "        Map<String, Object> ans = new HashMap<>();\n" +
                "        for (Map.Entry<String, Object> x : tmp.entrySet()) {\n" +
                "            if (x.getValue() instanceof Map) {\n" +
                "                x.setValue(removeNull((Map<String, Object>) x.getValue()));\n" +
                "            }\n" +
                "            if (Objects.nonNull(x.getValue())) {\n" +
                "                ans.put(x.getKey(), x.getValue());\n" +
                "            }\n" +
                "        }\n" +
                "        return ans;\n" +
                "    }\n" +
                "\n" +
                "    private Map<String, String> defaultHeaders() {\n" +
                "        Map<String, String> ans = new HashMap<>();\n" +
                "        ans.put(\"Accept\", \"application/json, text/plain, */*\");\n" +
                "        ans.put(\"referer\", serverIp + \"/\");\n" +
                "        ans.put(\"Origin\", serverIp);\n" +
                "        ans.put(\"Content-Type\", \"application/json\");\n" +
                "        ans.put(\"Connection\", \"keep-alive\");\n" +
                "        ans.put(\"Accept-Language\", \"zh-CN,zh;q=0.9\");\n" +
                "        ans.put(\"Accept-Encoding\", \"gzip, deflate, br\");\n" +
                "        ans.put(\"User-Agent\", \"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.71 Safari/537.36\");\n" +
                "        return ans;\n" +
                "    }\n" +
                "\n" +
                "    public Map<String, Object> doRequest(String url, String method, Map<String, Object> params, Map<String, String> headers) {\n" +
                "        params = removeNull(params);\n" +
                "        headers.putAll(defaultHeaders());\n" +
                "        SendReq.ResBody resBody = SendReq.sendReq(serverIp + url, method, params, headers);\n" +
                "        Map<String, Object> tmp = Json.toMap(resBody.getResponce());\n" +
                "        // TODO: 2021/10/11 加入过期判断\n" +
                "        if (resBody.getCode().equals(200)) {\n" +
                "            return Json.toMap(resBody.getResponce());\n" +
                "        }\n" +
                "        return Json.toMap(resBody.getResponce());\n" +
                "    }\n" +
                "\n" +
                "{body}" +
                "\n" +
                "}";
        Map<String, Object> params = new HashMap<>();
        params.put("body", body);
        return StringFormatUtils.formatByName(apiServerStr, params);
    }

}

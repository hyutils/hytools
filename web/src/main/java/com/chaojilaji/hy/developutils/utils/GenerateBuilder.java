package com.chaojilaji.hy.developutils.utils;


import com.chaojilaji.hyutils.dbcore.utils.StringFormatUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenerateBuilder {


    public static String getBuilderStr(String className, Map<String, String> names) {
        String init = StringFormatUtils.formatByName("    public static Builder builder() {\n" +
                "        return new Builder();\n" +
                "    }\n" +
                "    public static class Builder {\n" +
                "        private {class_name} target;\n" +
                "\n" +
                "        public Builder() {\n" +
                "            this.target = new {class_name}();\n" +
                "        }\n" +
                "\n" +
                "        public {class_name} build() {\n" +
                "            return target;\n" +
                "        }", new HashMap<String, Object>() {
            {
                put("class_name", className);
            }
        });
        StringBuilder xx = new StringBuilder();
        for (Map.Entry<String, String> x : names.entrySet()) {
            xx.append(StringFormatUtils.formatByName("public Builder {name}({type} {name}) {\n" +
                    "            this.target.{name}={name};\n" +
                    "            return this;\n" +
                    "        }\n", new HashMap<String, Object>() {
                {
                    put("name", x.getKey());
                    put("type", x.getValue());
                    put("big_name", StringFormatUtils.snake(x.getKey(), true));
                }
            }));
        }
        return init + xx.append("}\n").toString();
    }

    public static String getBuilderStr(String code) {
        String[] codes = code.split("\n");
        Pattern pattern = Pattern.compile("class (?<className>[a-zA-Z0-9]+)");
        Pattern pattern1 = Pattern.compile("(?<type>[<>a-zA-Z0-9]+) (?<name>[a-zA-Z0-9]+);");
        String className = "";
        Map<String, String> names = new HashMap<>();
        for (String a : codes) {
            Matcher matcher = pattern.matcher(a);
            if (matcher.find()) {
                className = matcher.group("className");
            }
            Matcher matcher1 = pattern1.matcher(a);
            if (matcher1.find()) {
                names.put(matcher1.group("name"), matcher1.group("type"));
            }
        }
        return getBuilderStr(className, names);
    }

    public static Map<String, String> getBuilderFields(String code) {
        String[] codes = code.split("\n");
        Pattern pattern = Pattern.compile("class (?<className>[a-zA-Z0-9]+)");
        Pattern pattern1 = Pattern.compile("(?<type>[<>a-zA-Z0-9]+) (?<name>[a-zA-Z0-9]+);");
        String className = "";
        Map<String, String> names = new HashMap<>();
        for (String a : codes) {
            Matcher matcher = pattern.matcher(a);
            if (matcher.find()) {
                className = matcher.group("className");
            }
            Matcher matcher1 = pattern1.matcher(a);
            if (matcher1.find()) {
                names.put(matcher1.group("name"), matcher1.group("type"));
            }
        }
        return names;
    }

    public static void main(String[] args) {
        String ans = getBuilderStr("public class RetestIp {\n" +
                "    private String ip;\n" +
                "    private List<Integer> port;\n" +
                "}");
        System.out.println(ans);
    }

}

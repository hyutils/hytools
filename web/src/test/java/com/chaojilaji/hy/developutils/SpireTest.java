package com.chaojilaji.hy.developutils;

import com.chaojilaji.hy.developutils.model.Vuln;
import com.chaojilaji.hy.developutils.utils.WordUtils;
import com.chaojilaji.hyutils.dbcore.utils.Json;
import com.spire.doc.*;
import com.spire.doc.documents.Paragraph;
import com.spire.doc.fields.TextBox;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpireTest {


    @Test
    public void demo1() {
        Document document = new Document();
        document.loadFromFile("D:\\2022项目\\记忆宫殿\\学习\\网络安全渗透测试漏洞报告-导入模板20221212.docx");
        String x = document.getText();
        System.out.println(x);
        FileUtils.createFileWithFullPath("D:\\2022项目\\记忆宫殿\\学习\\demo1.txt", x);
    }

    @Test
    public void demo2() {
        Document document = new Document();
        document.loadFromFile("D:\\2022项目\\记忆宫殿\\学习\\网络安全渗透测试漏洞报告-导入模板(带下拉)20221212.docx");
        String x = document.getText();
        System.out.println(x);
        FileUtils.createFileWithFullPath("D:\\2022项目\\记忆宫殿\\学习\\demo2.txt", x);
    }

    @Test
    public void demo3() {
        Document document = new Document();
        document.loadFromFile("D:\\2022项目\\记忆宫殿\\学习\\网络安全渗透测试漏洞报告-导入模板(带下拉)20221212.docx");
        int cntn = 0;
        for (Section section : (Iterable<Section>) document.getSections()) {
            int cntm = 0;
            for (Paragraph para : (Iterable<Paragraph>) section.getParagraphs()) {
                System.out.println(cntn + " " + cntm + " " + para.getText());
                cntm++;
//                for (Map.Entry<String, String> entry : map.entrySet()) {
//                    para.replace("${" + entry.getKey() + "}", entry.getValue(), false, true);
//                }
            }
            cntn++;
        }
    }

    @Test
    public void demo4() {
        Document document = new Document();
        document.loadFromFile("D:\\2022项目\\记忆宫殿\\学习\\网络安全渗透测试漏洞报告-导入模板(带下拉)20221212.docx");
        // TODO: 2022/12/14 加载不到文本框
        System.out.println("加载文本框");
        for (TextBox textBox : (Iterable<TextBox>) document.getTextBoxes()) {
            System.out.println("1");
            for (Table table : (Iterable<Table>) textBox.getBody().getTables()){
                System.out.println(table.getRows().getCount());
                for (int i = 0; i < table.getRows().getCount(); i++) {
                    TableRow row = table.getRows().get(i);
                    for (int j = 0; j < row.getCells().getCount(); j++) {
                        TableCell cell = row.getCells().get(j);
                        for (int k = 0; k < cell.getParagraphs().getCount(); k++) {
                            Paragraph paragraph = cell.getParagraphs().get(k);
                            System.out.println(paragraph.getText());
                        }
                    }
                }
            }
        }
    }

    @Test
    public void demo5(){
        Document document = new Document();
        document.loadFromFile("D:\\2022项目\\记忆宫殿\\学习\\网络安全渗透测试漏洞报告-导入模板(带下拉)20221212.docx");
        for (Section section : (Iterable<Section>) document.getSections()) {
            for (Table table : (Iterable<Table>) section.getBody().getTables()){
                System.out.println(table.getRows().getCount());
                for (int i = 0; i < table.getRows().getCount(); i++) {
                    TableRow row = table.getRows().get(i);
                    for (int j = 0; j < row.getCells().getCount(); j++) {
                        TableCell cell = row.getCells().get(j);
                        for (int k = 0; k < cell.getParagraphs().getCount(); k++) {
                            Paragraph paragraph = cell.getParagraphs().get(k);
                            System.out.println(i+" "+j+" "+k+" "+paragraph.getText());
                        }
                    }
                }
            }
        }
    }

    @Test
    public void demo6(){
        List<Vuln> vulns = WordUtils.getAllVulns(WordUtils.getDoc("D:\\2022项目\\记忆宫殿\\学习\\网络安全渗透测试漏洞报告-导入模板(带下拉)20221212.docx"));
        System.out.println(vulns.size());
    }


    @Test
    public void test1(){
        Map<String,Object> ans = new HashMap<>();
        List<List<Integer>> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        b.add(0);
        b.add(0);
        a.add(b);
        ans.put("a",a);
        System.out.println(Json.toJson(ans));
    }


}

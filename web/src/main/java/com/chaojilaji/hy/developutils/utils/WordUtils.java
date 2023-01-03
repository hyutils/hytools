package com.chaojilaji.hy.developutils.utils;

import com.chaojilaji.hy.developutils.model.Vuln;
import com.spire.doc.*;
import com.spire.doc.documents.Paragraph;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.print.Doc;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 解析word
 */
public class WordUtils {


    /**
     * 读取 word
     *
     * @param fullPath
     * @return
     */
    public static String getWordContext(String fullPath) {
        if (fullPath.endsWith(".doc") || fullPath.endsWith("docx")) {
            Document document = new Document();
            document.loadFromFile(fullPath);
            return document.getText();
        } else {
            return null;
        }
    }

    /**
     * 根据输入流 读取word
     *
     * @param inputStream
     * @return
     */
    public static String getWordContext(InputStream inputStream) {
        Document document = new Document();
        document.loadFromStream(inputStream, FileFormat.Doc);
        return document.getText();
    }


    public static String getWordContext(MultipartFile multipartFile) {
        try {
            return getWordContext(multipartFile.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getWordContext(File file) {
        try {
            return getWordContext(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Document getDoc(String fileName) {
        Document document = new Document();
        document.loadFromFile(fileName);
        return document;
    }

    public static Document getDoc(InputStream inputStream) {
        Document document = new Document();
        document.loadFromStream(inputStream, FileFormat.Doc);
        return document;
    }

    public static Document getDoc(MultipartFile multipartFile) {
        try {
            return getDoc(multipartFile.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Document getDoc(File file) {
        try {
            return getDoc(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析漏洞
     * @param document
     * @return
     */
    public static List<Vuln> getAllVulns(Document document) {
        List<Vuln> vulns = new ArrayList<>();
        if (Objects.nonNull(document)) {
            for (Section section : (Iterable<Section>) document.getSections()) {
                for (Table table : (Iterable<Table>) section.getBody().getTables()) {
                    int templateFlag = 0;
                    Vuln vuln = new Vuln();
                    for (int i = 0; i < table.getRows().getCount(); i++) {
                        TableRow row = table.getRows().get(i);
                        String name = "";
                        for (int j = 0; j < row.getCells().getCount(); j++) {
                            if (row.getCells().getCount() > 2) {
                                templateFlag = 1;
                                break;
                            }
                            TableCell cell = row.getCells().get(j);
                            for (int k = 0; k < cell.getParagraphs().getCount(); k++) {
                                Paragraph paragraph = cell.getParagraphs().get(k);
                                String text = paragraph.getText();
                                if (StringUtils.hasText(text)) {
                                    vuln.setValue(name,text);
                                    name = text + "";
                                }
                            }
                        }
                        if (templateFlag == 1){
                            // TODO: 2022/12/14 说明是模板
                            break;
                        }
                    }
                    if (templateFlag == 0){
                        vulns.add(vuln);
                    }
                }
            }
        }
        return vulns;
    }




}

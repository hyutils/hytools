package com.chaojilaji.hy.developutils;


import com.chaojilaji.hyutils.dbcore.utils.ArrayStrUtil;
import com.chaojilaji.hyutils.dbcore.utils.Json;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 主要是文件读取操作
 */
public class FileUtils {

    public static Boolean checkFileExist(String fileName, Boolean absolutePath) {
        if (absolutePath) {
            try {
                File file = new File(new File(".").getCanonicalPath() + "/" + fileName);
                return file.exists();
            } catch (IOException e) {
                return false;
            }
        } else {
            File file = new File(fileName);
            return file.exists();
        }
    }

    public static String getFileContent(File file) {
//        StringBuilder builder = new StringBuilder();
//        try {
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
//            String line = "";
//            while ((line = bufferedReader.readLine()) != null) {
//                builder.append(line).append("\n");
//            }
//            bufferedReader.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return builder.toString();
        try {
            return getFileContent(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getFileContent(FileInputStream fileInputStream) {
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line).append("\n");
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public static String getFileContent(InputStream inputStream) {
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line).append("\n");
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }


    public static List<String> readLines(File file, int limit) {
        List<String> ans = new ArrayList<>();
        int cnt = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                ans.add(line);
                cnt++;
                if (limit != -1 && cnt >= limit) {
                    break;
                }
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }


    public static String getFileContent(String fileName, Boolean absolutePath) {
        if (absolutePath) {
            try {
                return getFileContent(new File(new File(".").getCanonicalPath() + "/" + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        } else {
            try {
                return getFileContent(new File(fileName));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        }
    }

    public static String getFileContent2(String fileName, Boolean absolutePath) {
        if (!absolutePath) {
            try {
                return getFileContent(new File(new File(".").getCanonicalPath() + "/" + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        } else {
            try {
                return getFileContent(new File(fileName));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        }
    }


    public static List<String> readlines(String fileName, Boolean absolutePath, int limit) {
        if (!absolutePath) {
            try {
                return readLines(new File(new File(".").getCanonicalPath() + "/" + fileName), limit);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new ArrayList<>();
        } else {
            return readLines(new File(fileName), limit);
        }
    }


    /**
     * 如果内容是List str 类型，就加载
     *
     * @param fileName
     * @param absolutePath
     * @return
     */
    public static List<String> getListKeyWithOneLine(String fileName, Boolean absolutePath) {
        String source = getFileContent(fileName, absolutePath);
        return ArrayStrUtil.str2Array(source, "\n");
    }

    public static List<String> getListKeyWithOneLine2(String fileName, Boolean absolutePath) {
        String source = getFileContent2(fileName, absolutePath);
        return ArrayStrUtil.str2Array(source, "\n");
    }

    /**
     * 如果内容是一个List Map 类型
     *
     * @param fileName
     * @param absolutePath
     * @return
     */
    public static List<Map<String, Object>> getListMapFromLocal(String fileName, Boolean absolutePath) {
        String source = getFileContent(fileName, absolutePath);
        List<Map<String, Object>> x = Json.toObject(source, List.class);
        return x;
    }

    public static List<Map<String, Object>> getListMapFromLocal(File file) {
        String source = getFileContent(file);
        List<Map<String, Object>> x = Json.toObject(source, List.class);
        return x;
    }

    /**
     * 如果内容是一个List 对象类型
     *
     * @param fileName
     * @param absolutePath
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> List<T> getListObject(String fileName, Boolean absolutePath, Class<T> tClass) {
        List<T> ans = new ArrayList<>();
        List<Map<String, Object>> x = getListMapFromLocal(fileName, absolutePath);
        for (Map<String, Object> y : x) {
            T z = Json.toObject(Json.toJson(y), tClass);
            ans.add(z);
        }
        return ans;
    }

    public static <T> List<T> getListObject(File file, Class<T> tClass) {
        List<T> ans = new ArrayList<>();
        List<Map<String, Object>> x = getListMapFromLocal(file);
        for (Map<String, Object> y : x) {
            T z = Json.toObject(Json.toJson(y), tClass);
            ans.add(z);
        }
        return ans;
    }

    /**
     * 获取一个folder里面的所有对象
     *
     * @param folderName
     * @param absolutePath
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> List<T> getListObjectOfFolder(String folderName, Boolean absolutePath, Class<T> tClass) {
        if (absolutePath) {
            try {
                folderName = new File(".").getCanonicalPath() + "/" + folderName;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        List<T> ans = new ArrayList<>();
        File file = new File(folderName);
        File[] files = file.listFiles();
        for (File file1 : files) {
            if (file1.isDirectory()) continue;
            List<T> tmp = getListObject(file1, tClass);
            ans.addAll(tmp);
        }
        return ans;
    }

    /**
     * 获取一个folder里面的所有对象
     *
     * @param folderName
     * @param absolutePath
     * @param tClass
     * @param withoutFileName 剔除的文件
     * @param <T>
     * @return
     */
    public static <T> List<T> getListObjectOfFolder(String folderName, Boolean absolutePath, Class<T> tClass, String withoutFileName) {
        if (absolutePath) {
            try {
                folderName = new File(".").getCanonicalPath() + "/" + folderName;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        List<T> ans = new ArrayList<>();
        File file = new File(folderName);
        File[] files = file.listFiles();
        for (File file1 : files) {
            if (file1.isDirectory()) continue;
            if (withoutFileName.contains(file1.getName())) continue;
            List<T> tmp = getListObject(file1, tClass);
            ans.addAll(tmp);
        }
        return ans;
    }


    // todo 写入文件
    public static void createFile(String folder, String fileName, String value) {
        fileName = folder + "/" + fileName;
        try {
            createFileRecursion(fileName, 0);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileName, false));
            bufferedOutputStream.write(value.getBytes());
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void createFileRecursion(String fileName, Integer height) throws IOException {
        Path path = Paths.get(fileName);
        if (Files.exists(path)) {
            // TODO: 2021/11/13 如果文件存在
            return;
        }
        if (Files.exists(path.getParent())) {
            // TODO: 2021/11/13 如果父级文件存在，直接创建文件
            if (height == 0) {
                Files.createFile(path);
            } else {
                Files.createDirectory(path);
            }
        } else {
            createFileRecursion(path.getParent().toString(), height + 1);
            // TODO: 2021/11/13 这一步能保证path的父级一定存在了，现在需要把自己也建一下
            createFileRecursion(fileName, height);
        }
    }


    public static String createFileWithRelativePath(String folder, String fileName, String value) {
        File directory = new File(".");
        try {
            fileName = directory.getCanonicalPath() + "/" + folder + "/" + fileName;
            createFileRecursion(fileName, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileName, false));
            bufferedOutputStream.write(value.getBytes());
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    public static void createFileWithFullPath(String fileName, String value) {
        try {
            createFileRecursion(fileName, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileName, false));
            bufferedOutputStream.write(value.getBytes());
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void appendFileWithRelativePath(String folder, String fileName, String value) {
        File directory = new File(".");
        try {
            fileName = directory.getCanonicalPath() + "/" + folder + "/" + fileName;
            createFileRecursion(fileName, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileName, true));
            bufferedOutputStream.write(value.getBytes());
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void appendFileWithRelativePath(String folder, String fileName, String value, Boolean absolute) {
        File directory = new File(".");
        if (!absolute) {
            try {
                fileName = directory.getCanonicalPath() + "/" + folder + "/" + fileName;
                createFileRecursion(fileName, 0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                fileName = folder + "/" + fileName;
                createFileRecursion(fileName, 0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileName, true));
            bufferedOutputStream.write(value.getBytes());
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Boolean removeFile(String folder, String fileName, Boolean absolute) {
        File directory = new File(".");
        if (absolute) {
            try {
                fileName = directory.getCanonicalPath() + "/" + folder + "/" + fileName;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File file = new File(fileName);
        if (file.exists()){
            return file.delete();
        }
        return true;
    }

}

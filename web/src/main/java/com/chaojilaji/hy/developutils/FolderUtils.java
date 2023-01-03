package com.chaojilaji.hy.developutils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FolderUtils {

    public static class Dir {
        private String name;
        private String type;
        private String absolutePath;
        private List<Dir> suns;

        public Dir() {
        }

        public Dir(String name, String type, String absolutePath) {
            this.name = name;
            this.type = type;
            this.absolutePath = absolutePath;
        }

        public String getAbsolutePath() {
            return absolutePath;
        }

        public void setAbsolutePath(String absolutePath) {
            this.absolutePath = absolutePath;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<Dir> getSuns() {
            return suns;
        }

        public void setSuns(List<Dir> suns) {
            this.suns = suns;
        }
    }

    public static Boolean makeFolder(String folderName,Boolean absolute){
        File directory = new File(".");
        if (absolute) {
            try {
                folderName = directory.getCanonicalPath() + "/" + folderName;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File file = new File(folderName);
        return file.mkdir();
    }

    /**
     * 获取所有名字包含target的绝对路径
     *
     * @param dir
     * @param target
     * @return
     */
    public static List<String> getAbsolutePathOfDirNameByCondition(Dir dir, String target) {
        if (dir.getType().equals("file")) {
            List<String> ans = new ArrayList<>();
            if (dir.getName().contains(target)) {
                ans.add(dir.getAbsolutePath());
            }
            return ans;
        } else {
            List<Dir> dirs = dir.getSuns();
            List<String> ans = new ArrayList<>();
            for (Dir file : dirs) {
                ans.addAll(getAbsolutePathOfDirNameByCondition(file, target));
            }
            return ans;
        }
    }

    public static List<String> getAbsolutePathOfDirContentByCondition(Dir dir, String target) {
        if (dir.getType().equals("file")) {
            List<String> ans = new ArrayList<>();
            if (FileUtils.getFileContent(dir.getAbsolutePath(), false).contains(target)) {
                ans.add(dir.getAbsolutePath());
            }
            return ans;
        } else {
            List<Dir> dirs = dir.getSuns();
            List<String> ans = new ArrayList<>();
            for (Dir file : dirs) {
                ans.addAll(getAbsolutePathOfDirContentByCondition(file, target));
            }
            return ans;
        }
    }

    public static List<String> getAbsolutePathOfDirLineContentByCondition(Dir dir, String target) {
        if (dir.getType().equals("file")) {
            List<String> ans = new ArrayList<>();
            List<String> lines = FileUtils.getListKeyWithOneLine(dir.absolutePath, false);
            for (String line : lines) {
                if (line.contains(target)) {
                    ans.add(line);
                }
            }
            return ans;
        } else {
            List<Dir> dirs = dir.getSuns();
            List<String> ans = new ArrayList<>();
            for (Dir file : dirs) {
                ans.addAll(getAbsolutePathOfDirLineContentByCondition(file, target));
            }
            return ans;
        }
    }


    public static List<String> getAbsolutePathOfDirContentByCondition(Dir dir, Pattern target) {
        if (dir.getType().equals("file")) {
            List<String> ans = new ArrayList<>();
            String content = FileUtils.getFileContent(dir.getAbsolutePath(), false);
            Matcher matcher = target.matcher(content);
            if (matcher.find()) {
                ans.add(dir.getAbsolutePath());
            }
            return ans;
        } else {
            List<Dir> dirs = dir.getSuns();
            List<String> ans = new ArrayList<>();
            for (Dir file : dirs) {
                ans.addAll(getAbsolutePathOfDirContentByCondition(file, target));
            }
            return ans;
        }
    }

    public static Dir getFolder(File file, Set<String> ignores) {
        if (ignores.contains(file.getName())) {
            return null;
        }
        if (file.isFile()) {
            return new Dir(file.getName(), "file", file.getAbsolutePath());
        }
        File[] files = file.listFiles();
        Dir dir = new Dir();
        dir.name = file.getName();
        dir.type = "dir";
        dir.absolutePath = file.getAbsolutePath();
        dir.suns = new ArrayList<>();
        for (File file1 : files) {
            Dir tmp = getFolder(file1, ignores);
            if (Objects.nonNull(tmp)) {
                dir.getSuns().add(tmp);
            }
        }
        return dir;
    }

    public static Dir getFolder(String folderName) {
        return getFolder(new File(folderName), new HashSet<>());
    }

    public static Dir getFolder(String folderName, String ignores) {
        String[] x = ignores.split(",");
        Set<String> s = new HashSet<>(Arrays.asList(x));
        return getFolder(new File(folderName), s);
    }

    public static List<String> getAllNameInByTypeInFolder(Dir dir, String type) {
        List<String> ans = new ArrayList<>();
        if (dir.getName().endsWith(type)) {
            ans.add(dir.name.replace("." + type, ""));
        }
        if (Objects.nonNull(dir.suns)) {
            for (Dir dir1 : dir.suns) {
                ans.addAll(getAllNameInByTypeInFolder(dir1, type));
            }
        }
        return ans;
    }

    public static List<String> getAllTxtNameInFolder(String folderName, Boolean absolute) {
        File file = null;
        if (absolute) {
            try {
                file = new File(new File(".").getCanonicalPath() + "/" + folderName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Dir dir = getFolder(file, new HashSet<>());
        return getAllNameInByTypeInFolder(dir, "txt");
    }

    public static List<String> getAllTypeNameInFolder(String folderName, Boolean absolute,String type) {
        File file = null;
        if (absolute) {
            try {
                file = new File(new File(".").getCanonicalPath() + "/" + folderName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Dir dir = getFolder(file, new HashSet<>());
        return getAllNameInByTypeInFolder(dir, type);
    }

}

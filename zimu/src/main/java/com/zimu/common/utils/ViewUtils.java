package com.zimu.common.utils;

import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViewUtils {

    public static List<String> getUrlPaths() {

        List<String> urlPaths = new ArrayList<>();
        File baseFile = baseFile();
        if (baseFile == null) {
            return urlPaths;
        }
        String basePath = baseFile.getPath();

        List<File> fileList = getFiles();
        for (File file : fileList) {
            String path = file.getPath();
            String urlPath = path.substring(basePath.length());
            urlPath = urlPath.replace("\\", "/");
            urlPaths.add(urlPath);
        }
        return urlPaths;
    }

    private static File baseFile() {
        try {
            ClassPathResource classPathResource = new ClassPathResource("templates/");
            return classPathResource.getFile();
        } catch (IOException e) {
        }
        return null;
    }

    public static List<File> getFiles() {
        List<File> fileList = new ArrayList<>();
        File file = baseFile();
        if (file == null) {
            return fileList;
        }
        getFiles(file, fileList);
        return fileList;
    }

    private static void getFiles(File file, List<File> fileList) {
        if (file.isFile()) {
            if (file.getName().endsWith(ThymeleafProperties.DEFAULT_SUFFIX)) {
                fileList.add(file);
            }
        } else {
            File[] files = file.listFiles();
            if (files == null) {
                return;
            }
            for (File f : files) {
                getFiles(f, fileList);
            }
        }
    }
}

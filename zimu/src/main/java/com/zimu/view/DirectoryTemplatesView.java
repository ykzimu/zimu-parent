package com.zimu.view;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class DirectoryTemplatesView implements TemplatesView {

    @Override
    public List<String> getAllHtmlFile() {
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

    @Override
    public boolean isEnable() {
        return isDirectory();
    }

    //目录方式，获取文件file
    private File baseFile() {
        try {
            ClassPathResource classPathResource = new ClassPathResource("templates/");
            return classPathResource.getFile();
        } catch (IOException e) {
        }
        return null;
    }

    //使用递归方式获取所有html文件
    public List<File> getFiles() {
        List<File> fileList = new ArrayList<>();
        File file = baseFile();
        if (file == null) {
            return fileList;
        }
        getFiles(file, fileList);
        return fileList;
    }

    //递归方法
    private void getFiles(File file, List<File> fileList) {
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

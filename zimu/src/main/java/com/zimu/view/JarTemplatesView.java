package com.zimu.view;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

@Slf4j
@Component
public class JarTemplatesView implements TemplatesView {

    @Override
    public List<String> getAllHtmlFile() {

        List<String> urlPaths = new ArrayList<>();
        URL url = this.getClass().getProtectionDomain().getCodeSource().getLocation();
        String path = url.getPath();
        path = path.substring("file:".length());
        path = path.substring(0, path.length() - "!/BOOT-INF/classes!/".length());
        log.info("jarFilePath:{}", path);
        File file = new File(path);
        JarFile jarFile = null;
        try {
            jarFile = new JarFile(file);
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry jarEntry = entries.nextElement();
                String name = jarEntry.getName();
                if (!jarEntry.isDirectory() && name.contains("/templates/") && name.endsWith(ThymeleafProperties.DEFAULT_SUFFIX)) {
                    int io = name.indexOf("templates");
                    urlPaths.add(name.substring(io + "templates".length()).replace("\\", "/"));
                }
            }
        } catch (IOException e) {
            log.error("jar获取方式失败！", e);
        } finally {
            IOUtils.closeQuietly(jarFile);
        }
        return urlPaths;
    }


    @Override
    public boolean isEnable() {
        return isJar();
    }
}

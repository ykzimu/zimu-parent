package com.zimu.view;

import java.net.URL;
import java.util.Collections;
import java.util.List;

/**
 * 获取templates下所有html文件相对路径作为urlpath
 */
public interface TemplatesView {

    default List<String> getUrlPaths() {
        if (isEnable()) {
            return getAllHtmlFile();
        }
        return Collections.emptyList();
    }

    List<String> getAllHtmlFile();

    boolean isEnable();

    default boolean isJar() {
        URL url = this.getClass().getProtectionDomain().getCodeSource().getLocation();
        return url.getPath().endsWith(".jar!/BOOT-INF/classes!/");
    }

    default boolean isDirectory() {
        return !isJar();
    }
}

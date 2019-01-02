package com.zimu.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ZipFileUtils {

    private static final String ZIP_SUFFIX = ".zip";

    /**
     * 压缩成ZIP 方法
     *
     * @param filePath 压缩文件夹路径
     */
    public static File toZip(String filePath) {

        ZipArchiveOutputStream zaos = null;
        File file = null;
        FileOutputStream fos = null;
        try {
            String fileName = filePath + ZIP_SUFFIX;
            file = new File(fileName);
            fos = new FileOutputStream(file);
            zaos = new ZipArchiveOutputStream(fos);
            File sourceFile = new File(filePath);
            Long startTime = System.currentTimeMillis();
            compress(sourceFile, zaos, sourceFile.getName());
            Long endTime = System.currentTimeMillis();
            log.info("压缩路径:{},耗时:{}", filePath, endTime - startTime);
        } catch (IOException e) {
            log.error("文件压缩失败！", e);
            return null;
        } finally {
            if (zaos != null) {
                try {
                    zaos.close();
                } catch (IOException e) {
                    log.error("文件压缩失败！", e);
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    log.error("文件压缩失败！", e);
                }

            }
        }

        return file;

    }

    /**
     * 递归压缩方法
     *
     * @param sourceFile 源文件
     * @param zaos       zip输出流
     * @param name       压缩后的名称
     */
    private static void compress(File sourceFile, ZipArchiveOutputStream zaos, String name) {

        if (sourceFile.isFile()) {
            try {
                // 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
                ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(sourceFile, name);
                zaos.putArchiveEntry(zipArchiveEntry);
                InputStream is = new FileInputStream(sourceFile);
                IOUtils.copy(is, zaos);
                is.close();
                zaos.closeArchiveEntry();
            } catch (IOException e) {
                log.error("文件压缩失败！", e);
            }

        } else {
            File[] listFiles = sourceFile.listFiles();
            if (listFiles == null || listFiles.length == 0) {
                try {
                    ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(name + File.separator);
                    // 空文件夹的处理
                    zaos.putArchiveEntry(zipArchiveEntry);
                    // 没有文件，不需要文件的copy
                    zaos.closeArchiveEntry();
                } catch (IOException e) {
                    log.error("文件压缩失败！", e);
                }
            } else {
                for (File file : listFiles) {
                    // 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
                    compress(file, zaos, name + File.separator + file.getName());
                }
            }
        }
    }
}

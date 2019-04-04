package com.zimu.common.mybatisplus.engine;

import com.baomidou.mybatisplus.generator.config.rules.FileType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

public class ZimuFreemarkerTemplateEngine extends FreemarkerTemplateEngine {

    @Override
    protected boolean isCreate(FileType fileType, String filePath) {
        if (fileType == FileType.ENTITY) {
            return true;
        }
        return super.isCreate(fileType, filePath);
    }
}

package com.zimu.common.utils;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class IdUtils {

    public static List<Long> getIds(String ids) {
        //数据处理
        if (StringUtils.isBlank(ids)) {
            return null;
        }
        String[] strIds = ids.split(",");
        List<Long> list = new ArrayList<>();
        for (String id : strIds) {
            if (StringUtils.isBlank(id) || !StringUtils.isNumeric(id.trim())) {
                return null;
            }
            list.add(Long.parseLong(id.trim()));
        }
        return list;
    }
}

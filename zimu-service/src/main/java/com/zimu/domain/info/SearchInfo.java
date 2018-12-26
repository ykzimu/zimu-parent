package com.zimu.domain.info;

import lombok.Data;

@Data
public class SearchInfo {

    /**
     * 关键字
     */
    private String keyword;

    /**
     * 排序字段
     */
    private String fieldName;

    /**
     * 排序方式
     */
    private String sortType;

    /**
     * 页码
     */
    private Integer pageNum;

    /**
     * 每页大小
     */
    private Integer pageSize;

    public Integer getPageNum() {
        if (pageNum == null) {
            pageNum = 1;
        }
        return pageNum;
    }

    public Integer getPageSize() {
        if (pageSize == null) {
            pageSize = 10;
        }
        return pageSize;
    }
}


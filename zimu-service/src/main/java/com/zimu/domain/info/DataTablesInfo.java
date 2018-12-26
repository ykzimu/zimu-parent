package com.zimu.domain.info;

import lombok.Data;

@Data
public class DataTablesInfo {

    private Long draw;

    private Integer start;

    private Integer length;
    /**
     * 页码
     */
    private Integer pageNum;

    /**
     * 每页大小
     */
    private Integer pageSize;

    public Integer getPageNum() {
        pageNum = getStart() / getLength() + 1;
        return pageNum;
    }

    public Integer getPageSize() {
        return getLength();
    }

    public Integer getStart() {
        if (start == null) {
            start = 0;
        }
        return start;
    }

    public Integer getLength() {
        if (length == null) {
            length = 10;
        }
        return length;
    }
}

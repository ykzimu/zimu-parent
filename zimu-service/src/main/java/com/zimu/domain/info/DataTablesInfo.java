package com.zimu.domain.info;

import lombok.Data;

import java.util.List;

@Data
public class DataTablesInfo {

    @Data
    public static class Search {

        protected String value;

        protected Boolean regex;
    }

    @Data
    public static class Column {

        protected String data;

        protected String name;

        protected Boolean searchable;

        protected Boolean orderable;

        protected Search search;
    }

    @Data
    public static class Order {

        protected Integer column;

        protected String dir;
    }

    private Long draw;

    private Long start;

    private Integer length;

    private List<Column> columns;

    private Search search;

    private List<Order> order;

    /**
     * 页码
     */
    private Integer pageNum;

    /**
     * 每页大小
     */
    private Integer pageSize;

    public Integer getPageNum() {
        Long pN = getStart() / getLength() + 1L;
        return pN.intValue();
    }

    public Integer getPageSize() {
        return getLength();
    }

    public Long getStart() {
        if (start == null) {
            start = 0L;
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

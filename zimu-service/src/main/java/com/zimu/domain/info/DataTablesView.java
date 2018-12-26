package com.zimu.domain.info;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DataTablesView<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long draw;
    private Long recordsTotal;
    private Long recordsFiltered;
    private List<T> data;
    private String error;

    public DataTablesView(PageInfo<T> pageInfo) {
        this.recordsTotal = pageInfo.getTotal();
        this.recordsFiltered = pageInfo.getTotal();
        this.data = pageInfo.getList();
    }
}

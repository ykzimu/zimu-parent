package com.zimu.domain.info;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
    private JSONObject extendData;

    private DataTablesView() {

    }

    public DataTablesView(IPage<T> page) {
        this.recordsTotal = page.getTotal();
        this.recordsFiltered = page.getTotal();
        this.data = page.getRecords();
    }

    public DataTablesView(List<T> list) {
        Long size = new Long(list.size());
        this.recordsTotal = size;
        this.recordsFiltered = size;
        this.data = list;
    }

    public static DataTablesView error(String error) {
        DataTablesView dataTablesView = new DataTablesView();
        dataTablesView.setError(error);
        return dataTablesView;
    }

    public static DataTablesView error(Exception e) {
        return error(e.getMessage());
    }
}

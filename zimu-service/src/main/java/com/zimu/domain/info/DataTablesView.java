package com.zimu.domain.info;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DataTablesView<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long iTotalRecords;
    private Integer iTotalDisplayRecords;
    private List<T> aaData;
}

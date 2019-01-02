package com.zimu.service;

import java.util.List;

import com.zimu.domain.info.SelectInfo;

public interface RequestMappingService {

    void initRequestMapping();

    List<SelectInfo> getUrls();

}

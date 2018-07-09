package com.zimu.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.zimu.common.CacheNames;
import com.zimu.dao.RequestMappingEntityMapper;
import com.zimu.domain.entity.RequestMappingEntity;
import com.zimu.domain.entity.RequestMappingEntityExample;
import com.zimu.domain.info.SelectInfo;
import com.zimu.service.RequestMappingService;

@Service
public class RequestMappingServiceImpl implements RequestMappingService {

    @Autowired
    private RequestMappingEntityMapper requestMappingEntityMapper;

    @Autowired
    private WebApplicationContext webApplicationConnect;

    @Autowired
    private CacheManager cacheManager;

    @Override
    public void initRequestMapping() {

        Date now = new Date();

        //查询所有的url
        List<RequestMappingEntity> list = requestMappingEntityMapper.selectByExample(null);


        //获取所有请求mapping
        Set<String> result = new HashSet<>();
        RequestMappingHandlerMapping bean = webApplicationConnect.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = bean.getHandlerMethods();
        for (RequestMappingInfo requestMappingInfo : handlerMethods.keySet()) {
            Set<String> patterns = requestMappingInfo.getPatternsCondition().getPatterns();
            result.addAll(patterns);
        }

        List<String> values = new ArrayList<>();
        //遍历数据，插入
        for (String pattern : result) {
            values.add(pattern);
            RequestMappingEntity entity = findByPattern(list, pattern);
            if (entity != null) {
                continue;
            }
            entity = new RequestMappingEntity();
            entity.setPatterns(pattern);
            entity.setCreateBy("1");
            entity.setCreateDate(now);
            entity.setDelFlag(0);
            entity.setVersion(1);
            entity.setUpdateDate(now);
            entity.setUpdateBy("1");
            requestMappingEntityMapper.insert(entity);
        }

        if (values.isEmpty()) {
            return;
        }
        RequestMappingEntityExample example = new RequestMappingEntityExample();
        example.createCriteria().andPatternsNotIn(values);
        requestMappingEntityMapper.deleteByExample(example);


    }

    private RequestMappingEntity findByPattern(List<RequestMappingEntity> list, String pattern) {
        if (list == null || list.isEmpty()) {
            return null;
        }

        RequestMappingEntity requestMappingEntity = null;
        for (RequestMappingEntity entity : list) {
            if (entity.getPatterns().equals(pattern)) {
                requestMappingEntity = entity;
                break;
            }
        }
        return requestMappingEntity;
    }

    @Override
    @SuppressWarnings("unchecked")
	public List<SelectInfo> getUrls() {

        Cache cache = cacheManager.getCache(CacheNames.CACHE_NAME_MUZI);
        List<SelectInfo> cacheList = cache.get(CacheNames.CACHE_KEY_URIS, List.class);
        if (cacheList != null && !cacheList.isEmpty()) {
            return cacheList;
        }

        RequestMappingEntityExample example = new RequestMappingEntityExample();
        example.setOrderByClause(" patterns ASC ");
        List<RequestMappingEntity> list = requestMappingEntityMapper.selectByExample(example);
        List<SelectInfo> result = new ArrayList<>();
        SelectInfo selectInfo = null;
        for (RequestMappingEntity entity : list) {
            selectInfo = new SelectInfo();
            selectInfo.setId(entity.getId().toString());
            selectInfo.setText(entity.getPatterns());
            result.add(selectInfo);
        }
        cache.put(CacheNames.CACHE_KEY_URIS, result);
        return result;
    }
}

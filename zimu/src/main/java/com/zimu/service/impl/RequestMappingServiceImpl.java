package com.zimu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zimu.domain.info.SelectInfo;
import com.zimu.entity.RequestMappingEntity;
import com.zimu.mapper.RequestMappingMapper;
import com.zimu.service.RequestMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 * 所有url 服务实现类
 * </p>
 *
 * @author 杨坤
 * @since 2019-04-04
 */
@Service
public class RequestMappingServiceImpl extends ServiceImpl<RequestMappingMapper, RequestMappingEntity> implements RequestMappingService {


    @Autowired
    private RequestMappingMapper requestMappingMapper;

    @Autowired
    private WebApplicationContext webApplicationConnect;

    @Override
    public void initRequestMapping() {

        LocalDateTime now = LocalDateTime.now();

        //查询所有的url
        List<RequestMappingEntity> list = requestMappingMapper.selectList(new QueryWrapper<>());


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
            requestMappingMapper.insert(entity);
        }

        if (values.isEmpty()) {
            return;
        }
        LambdaUpdateWrapper<RequestMappingEntity> wrapper = Wrappers.lambdaUpdate();
        wrapper.notIn(RequestMappingEntity::getPatterns, values);
        requestMappingMapper.delete(wrapper);


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
    public List<SelectInfo> getUrls() {
        LambdaQueryWrapper<RequestMappingEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.orderByAsc(RequestMappingEntity::getPatterns);
        List<RequestMappingEntity> list = requestMappingMapper.selectList(queryWrapper);
        List<SelectInfo> result = new ArrayList<>();
        SelectInfo selectInfo = null;
        for (RequestMappingEntity entity : list) {
            selectInfo = new SelectInfo();
            selectInfo.setId(entity.getId().toString());
            selectInfo.setText(entity.getPatterns());
            result.add(selectInfo);
        }
        return result;
    }
}

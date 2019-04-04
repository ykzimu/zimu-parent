package com.zimu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zimu.domain.info.SelectInfo;
import com.zimu.entity.RequestMappingEntity;

import java.util.List;

/**
 * <p>
 * 所有url 服务类
 * </p>
 *
 * @author 杨坤
 * @since 2019-04-04
 */
public interface RequestMappingService extends IService<RequestMappingEntity> {

    void initRequestMapping();

    List<SelectInfo> getUrls();

}

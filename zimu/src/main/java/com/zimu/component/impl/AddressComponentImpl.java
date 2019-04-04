package com.zimu.component.impl;

import com.zimu.common.CacheNames;
import com.zimu.common.Constants;
import com.zimu.common.enums.AddressLevel;
import com.zimu.component.AddressComponent;
import com.zimu.dao.DictAddressEntityMapper;
import com.zimu.domain.entity.DictAddressEntity;
import com.zimu.domain.entity.DictAddressEntityExample;
import com.zimu.domain.info.AddressInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@CacheConfig(cacheNames = CacheNames.CACHE_NAME_ADDRESS)
public class AddressComponentImpl implements AddressComponent {

    @Autowired
    private DictAddressEntityMapper dictAddressEntityMapper;

    /**
     * 获取信息
     */
    private List<AddressInfo> getList(AddressLevel level, String code) {

        // 查询条件
        DictAddressEntityExample example = new DictAddressEntityExample();
        example.createCriteria().andLevelEqualTo(level.getCode()).andDelFlagNotEqualTo(Constants.DEL_FLAG_OK).andParentCodeEqualTo(code);
        example.setOrderByClause("sort_no asc,code asc");
        List<DictAddressEntity> addressList = dictAddressEntityMapper.selectByExample(example);
        return addressList.stream().map(AddressInfo::new).collect(Collectors.toList());
    }

    /**
     * 获取省信息
     */
    @Override
    @Cacheable(key = "#root.methodName")
    public List<AddressInfo> getProvinceList() {
        return getList(AddressLevel.PROVINCE, "0");
    }

    /**
     * 获取市信息
     */
    @Override
    @Cacheable(key = "#root.methodName + #code")
    public List<AddressInfo> getCityList(String code) {
        return getList(AddressLevel.CITY, code);
    }

    /**
     * 获取区县信息
     */
    @Override
    @Cacheable(key = "#root.methodName + #code")
    public List<AddressInfo> getAreaList(String code) {
        return getList(AddressLevel.AREA, code);
    }

    /**
     * 清除所有缓存
     */
    @Override
    @CacheEvict(allEntries = true)
    public void deleteAllCache() {
        //清除所有省市区缓存
    }

}

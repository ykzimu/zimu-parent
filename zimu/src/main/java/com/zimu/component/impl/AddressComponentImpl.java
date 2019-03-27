package com.zimu.component.impl;

import com.zimu.common.CacheNames;
import com.zimu.common.enums.AddressLevel;
import com.zimu.component.AddressComponent;
import com.zimu.dao.DictAddressEntityMapper;
import com.zimu.domain.entity.DictAddressEntity;
import com.zimu.domain.entity.DictAddressEntityExample;
import com.zimu.domain.info.AddressInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@CacheConfig(cacheNames = CacheNames.CACHE_NAME_MUZI)
public class AddressComponentImpl implements AddressComponent {

    @Autowired
    private DictAddressEntityMapper dictAddressEntityMapper;


    /**
     * 获取信息
     */
    @Override
    public List<AddressInfo> getList(AddressLevel level, String code) {

        // 返回数据
        List<AddressInfo> list = new ArrayList<>();

        // 查询条件
        DictAddressEntityExample example = new DictAddressEntityExample();
        DictAddressEntityExample.Criteria criteria = example.createCriteria();
        criteria.andLevelEqualTo(level.getCode());
        criteria.andDelFlagNotEqualTo(1);
        if (level != AddressLevel.PROVINCE) {
            criteria.andParentCodeEqualTo(code);
        }
        example.setOrderByClause("sort_no asc,code asc");
        List<DictAddressEntity> addressList = dictAddressEntityMapper.selectByExample(example);
        if (addressList == null || addressList.isEmpty()) {
            return list;
        }

        // 获取信息
        addressList.forEach(dictAddressEntity -> {
            AddressInfo addressInfo = new AddressInfo();
            addressInfo.setCode(dictAddressEntity.getCode());
            addressInfo.setName(dictAddressEntity.getName());
            list.add(addressInfo);
        });
        return list;
    }

    /**
     * 获取省信息
     */
    @Override
    @Cacheable
    public List<AddressInfo> getProvinceList() {
        return getList(AddressLevel.PROVINCE, null);
    }

    /**
     * 获取市信息
     */
    @Override
    @Cacheable(key = "#code")
    public List<AddressInfo> getCityList(String code) {
        return getList(AddressLevel.CITY, code);
    }

    /**
     * 获取区县信息
     */
    @Override
    @Cacheable(key = "#code")
    public List<AddressInfo> getAreaList(String code) {
        return getList(AddressLevel.AREA, code);
    }
}

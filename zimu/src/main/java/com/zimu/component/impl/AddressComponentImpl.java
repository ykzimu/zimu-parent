package com.zimu.component.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;

import com.zimu.common.RedisKeys;
import com.zimu.common.enums.AddressLevel;
import com.zimu.component.AddressComponent;
import com.zimu.dao.DictAddressEntityMapper;
import com.zimu.domain.entity.DictAddressEntity;
import com.zimu.domain.entity.DictAddressEntityExample;
import com.zimu.domain.info.AddressInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class AddressComponentImpl implements AddressComponent {

    @Autowired
    private DictAddressEntityMapper dictAddressEntityMapper;

    @SuppressWarnings("rawtypes")
    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 获取信息
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<AddressInfo> getList(AddressLevel level, String code) {

        // 返回数据
        List<AddressInfo> list = new ArrayList<>();
        AddressInfo addressInfo = new AddressInfo();
        addressInfo.setCode("");
        addressInfo.setName("请选择");
        list.add(addressInfo);
        if (level != AddressLevel.PROVINCE && StringUtils.isBlank(code)) {
            return list;
        }

        // 设置序列化Serializer
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<AddressInfo>(AddressInfo.class));

        String rediskey = RedisKeys.KEY_ADDRESS_ALL + level.getCode();
        if (level != AddressLevel.PROVINCE) {
            rediskey = rediskey + "_" + code;
        }

        // 先从缓存中取数据
        ListOperations<String, AddressInfo> los = redisTemplate.opsForList();

        // 查询当前key是否已存缓存
        boolean flag = redisTemplate.hasKey(rediskey);

        // 若缓存中存在，从缓存中获取数据
        if (flag) {
            // 从缓存中获取数据
            List<AddressInfo> cacheList = los.range(rediskey, 0, -1);
            if (cacheList != null && !cacheList.isEmpty()) {
                list = cacheList;
            }
        } else {// 缓存中不存在，从数据库中获取数据并插入缓存
            // 查询条件
            DictAddressEntityExample example = new DictAddressEntityExample();
            DictAddressEntityExample.Criteria criteria = example.createCriteria();
            criteria.andLevelEqualTo(level.getCode());
            criteria.andDelFlagEqualTo(0);
            if (level != AddressLevel.PROVINCE) {
                criteria.andParentCodeEqualTo(code);
            }
            example.setOrderByClause("sort_no asc");
            List<DictAddressEntity> addressList = dictAddressEntityMapper.selectByExample(example);
            if (addressList != null && !addressList.isEmpty()) {
                // 获取信息
                for (DictAddressEntity dictAddressEntity : addressList) {
                    addressInfo = new AddressInfo();
                    addressInfo.setCode(dictAddressEntity.getCode());
                    addressInfo.setName(dictAddressEntity.getName());
                    list.add(addressInfo);
                }
            }
            // 插入缓存
            los.rightPushAll(rediskey, list);
        }

        return list;
    }

    /**
     * 获取省信息
     */
    @Override
    public List<AddressInfo> getProvinceList() {
        return getList(AddressLevel.PROVINCE, null);
    }

    /**
     * 获取市信息
     */
    @Override
    public List<AddressInfo> getCityList(String code) {
        return getList(AddressLevel.CITY, code);
    }

    /**
     * 获取区县信息
     */
    @Override
    public List<AddressInfo> getAreaList(String code) {
        return getList(AddressLevel.AREA, code);
    }

    /**
     * 添加所有缓存
     */
    @SuppressWarnings("unchecked")
    @Override
    public void addAllCache() {

        // 不推荐使用循环查询操作，原因是效率低下

        // 设置序列化Serializer
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<AddressInfo>(AddressInfo.class));
        // 先从缓存中取数据
        ListOperations<String, AddressInfo> los = redisTemplate.opsForList();

        // 查询条件
        DictAddressEntityExample example = new DictAddressEntityExample();
        DictAddressEntityExample.Criteria criteria = example.createCriteria();
        criteria.andDelFlagEqualTo(0);
        example.setOrderByClause("parent_code asc, sort_no asc");
        List<DictAddressEntity> addressList = dictAddressEntityMapper.selectByExample(example);

        // 处理结果
        int size = addressList.size();
        List<AddressInfo> list = new ArrayList<>();
        AddressInfo addressInfo = null;
        DictAddressEntity dictAddressEntity = null;
        DictAddressEntity lastDictAddressEntity = null;

        // 遍历循环
        for (int i = 0; i < size; i++) {
            dictAddressEntity = addressList.get(i);
            addressInfo = new AddressInfo();
            addressInfo.setCode(dictAddressEntity.getCode());
            addressInfo.setName(dictAddressEntity.getName());

            if (i != 0) {
                lastDictAddressEntity = addressList.get(i - 1);
                if (!lastDictAddressEntity.getParentCode().equals(dictAddressEntity.getParentCode())) {
                    String rediskey = RedisKeys.KEY_ADDRESS_ALL + lastDictAddressEntity.getLevel();
                    if (lastDictAddressEntity.getLevel() != AddressLevel.PROVINCE.getCode()) {
                        rediskey = rediskey + "_" + lastDictAddressEntity.getParentCode();
                    }
                    // 插入缓存
                    los.rightPushAll(rediskey, prefixWithBlank(list));
                    list = new ArrayList<>();
                }
            }
            list.add(addressInfo);
        }

        lastDictAddressEntity = addressList.get(size - 1);
        String rediskey = RedisKeys.KEY_ADDRESS_ALL + lastDictAddressEntity.getLevel();
        if (lastDictAddressEntity.getLevel() != AddressLevel.PROVINCE.getCode()) {
            rediskey = rediskey + "_" + lastDictAddressEntity.getParentCode();
        }
        // 插入缓存
        los.rightPushAll(rediskey, prefixWithBlank(list));

    }

    public List<AddressInfo> prefixWithBlank(List<AddressInfo> list) {
        List<AddressInfo> newList = new ArrayList<>();
        AddressInfo addressInfo = new AddressInfo();
        addressInfo.setCode("");
        addressInfo.setName("请选择");
        newList.add(addressInfo);
        newList.addAll(list);
        return newList;
    }

    /**
     * 删除所有缓存
     */
    @SuppressWarnings("unchecked")
    @Override
    public void delALlCache() {
        // 获取所有地址key
        Set<String> keys = redisTemplate.keys(RedisKeys.KEY_ADDRESS_ALL + "*");
        // 删除所有key
        redisTemplate.delete(keys);
    }

    /**
     * 刷新所有缓存
     */
    @Override
    public void refreshALlCache() {
        delALlCache();
        addAllCache();
    }

}

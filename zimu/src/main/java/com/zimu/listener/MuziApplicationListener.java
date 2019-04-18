package com.zimu.listener;

import com.zimu.common.RedisKeys;
import com.zimu.service.RequestMappingService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class MuziApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private RequestMappingService requestMappingService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        //防止集群同时操作数据库
        String flag = valueOperations.get(RedisKeys.KEY_INIT_REQUEST_MAPPING);
        if (StringUtils.isBlank(flag)) {
            valueOperations.set(RedisKeys.KEY_INIT_REQUEST_MAPPING, "1", 1L, TimeUnit.MINUTES);
            requestMappingService.initRequestMapping();
        }
    }
}

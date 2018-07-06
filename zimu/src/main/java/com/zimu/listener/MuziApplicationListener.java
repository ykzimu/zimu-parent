package com.zimu.listener;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.zimu.common.RedisKeys;
import com.zimu.component.CommonComponent;
import com.zimu.domain.info.SelectInfo;
import com.zimu.service.RequestMappingService;

@Component
public class MuziApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private CommonComponent commonComponent;

	@Autowired
    private WebApplicationContext webApplicationConnect;

    @Autowired
    private RequestMappingService requestMappingService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		List<SelectInfo> pageSizeInfos = commonComponent.getPageSizeList();
		ServletContext servletContext = webApplicationConnect.getServletContext();
		servletContext.setAttribute("pageSizeInfos", pageSizeInfos);
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();

        //防止集群同时操作数据库
        String flag = valueOperations.get(RedisKeys.KEY_INIT_REQUEST_MAPPING);
        if (StringUtils.isBlank(flag)) {
            valueOperations.set(RedisKeys.KEY_INIT_REQUEST_MAPPING, "1", 1L, TimeUnit.MINUTES);
            requestMappingService.initRequestMapping();
        }
    }
}

package com.zimu.advice;

import com.zimu.common.utils.HttpServletManager;
import com.zimu.domain.info.DataTablesView;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class DataTablesResponseBodyAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return returnType.getParameterType().equals(DataTablesView.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        HttpServletRequest httpServletRequest = HttpServletManager.getRequest();
        String drawStr = httpServletRequest.getParameter("draw");
        if (StringUtils.isNotBlank(drawStr) && StringUtils.isNumeric(drawStr)) {
            DataTablesView dataTablesView = (DataTablesView) body;
            dataTablesView.setDraw(Long.parseLong(drawStr));
            return dataTablesView;
        }
        return body;
    }
}

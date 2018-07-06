package com.zimu.advice;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zimu.common.exception.AuthenticationException;
import com.zimu.common.exception.BasicException;
import com.zimu.common.exception.BusinessException;
import com.zimu.common.exception.ValidationException;
import com.zimu.common.utils.SpringContextUtils;
import com.zimu.domain.info.JsonView;
import com.zimu.domain.info.Msg;
import com.zimu.domain.info.ResultCode;

import java.util.Locale;

@ControllerAdvice("com.zimu.controller")
public class ExceptionHandlerAdvice {

	@ExceptionHandler(value = { AuthenticationException.class, ValidationException.class, BusinessException.class,
			Exception.class })
	@ResponseBody
	public JsonView exception(Exception e) {
		JsonView result = new JsonView();
		Msg msg = result.getMsg();

		String message = null;
		if (e instanceof AuthenticationException) {
			msg.setMsg(ResultCode.AUTH_ERROR);
			message = getMessage((BasicException) e);
		} else if (e instanceof ValidationException) {
			msg.setMsg(ResultCode.ARGUMENT_ERROR);
			message = getMessage((BasicException) e);
		} else if (e instanceof BusinessException) {
			msg.setMsg(ResultCode.BUSINESS_ERROR);
			message = getMessage((BasicException) e);
		} else {
			msg.setMsg(ResultCode.SYSTEM_ERROR);
			message = e.getMessage();
		}
		msg.setMessage(message);
		return result;
	}

	// 获取资源国际化值
	private String getMessage(BasicException e) {
		try {
			// 获取资源
			MessageSource messageSource = SpringContextUtils.getBean(MessageSource.class);
			Locale locale = LocaleContextHolder.getLocale();
			return messageSource.getMessage(e.getMessage(), e.getVariables(), locale);
		} catch (NoSuchMessageException nse) {
			return e.getMessage();
		}
	}
}

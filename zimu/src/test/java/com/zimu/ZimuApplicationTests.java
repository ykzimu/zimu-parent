package com.zimu;

import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZimuApplicationTests {

	@Autowired
	private MessageSource messageSource;

	@Test
	public void contextLoads() {
		Locale locale = LocaleContextHolder.getLocale();
		Object[] args = new String[] { "yangkun" };
		String value = messageSource.getMessage("I0001", args, locale);
		System.out.println(value);
	}

}

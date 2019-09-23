package com.zimu.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.zimu.interceptor.HttpServletInterceptor;
import com.zimu.resolver.DataTablesHandlerMethodArgumentResolver;
import com.zimu.view.TemplatesView;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.session.security.web.authentication.SpringSessionRememberMeServices;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
@Import({BCryptPasswordEncoder.class, PaginationInterceptor.class})
public class ZimuWebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private WebMvcProperties webMvcProperties;


    /**
     * urlpath处理器
     */
    @Autowired
    private List<TemplatesView> templatesViewList;

    /**
     * 自定义拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HttpServletInterceptor())
                .excludePathPatterns(webMvcProperties.getStaticPathPattern());
    }

    /**
     * 自动装配controller  viewname
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "index.html");
        registry.addRedirectViewController("/login", "/login.html");
        List<String> urlPaths = new ArrayList<>();
        templatesViewList.forEach(t -> urlPaths.addAll(t.getUrlPaths()));
        log.info("自动映射url，-----start------------------");
        urlPaths.forEach(log::info);
        log.info("自动映射url，-----end------------------");
        urlPaths.forEach(x -> registry.addViewController(x).setViewName(x.substring(1)));
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new DataTablesHandlerMethodArgumentResolver());
    }


    @Bean
    public Validator defaultValidator(MessageSource messageSource) {
        LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
        factoryBean.setValidationMessageSource(messageSource);
        factoryBean.setProviderClass(HibernateValidator.class);
        return factoryBean;
    }

    @Bean
    public SpringSessionRememberMeServices rememberMeServices() {
        SpringSessionRememberMeServices rememberMeServices = new SpringSessionRememberMeServices();
        rememberMeServices.setAlwaysRemember(true);
        return rememberMeServices;
    }

}

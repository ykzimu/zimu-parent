package com.zimu.view;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Field;

@Slf4j
public abstract class BaseView {

    //获取viewName
    protected abstract String viewName();


    /**
     * 构建ModelAndView
     */
    public ModelAndView mv() {
        ModelAndView mv = new ModelAndView(this.viewName());
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {

                //忽略VIEWNAME属性
                if ("VIEWNAME".equals(field.getName())) {
                    continue;
                }
                field.setAccessible(true);
                mv.addObject(field.getName(), field.get(this));
            } catch (Exception e) {
                log.error("反射错误!", e);
            }
        }
        return mv;
    }
}

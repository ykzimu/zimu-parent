package com.zimu.view;

import com.zimu.annotation.ViewName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Field;

/**
 * @author yk
 */
public interface BaseView {

    /**
     * 构建ModelAndView
     */
    default ModelAndView view() {
        ViewName viewName = this.getClass().getAnnotation(ViewName.class);
        ModelAndView mv = new ModelAndView(viewName.value());
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

            }
        }
        return mv;
    }
}

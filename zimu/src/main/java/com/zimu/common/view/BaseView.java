package com.zimu.common.view;

import com.zimu.common.view.annotation.ViewName;
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
        ModelAndView mv = new ModelAndView(viewName.value().getViewName());
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                mv.addObject(field.getName(), field.get(this));
            } catch (Exception e) {

            }
        }
        return mv;
    }
}

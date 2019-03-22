package com.zimu.common.view.annotation;

import com.zimu.common.view.ViewNames;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ViewName {

    ViewNames value();
}

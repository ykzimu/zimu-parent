package com.zimu.filter;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class MySiteMeshFilter extends ConfigurableSiteMeshFilter {
    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder.addDecoratorPath("/*", "/layouts/decorator")
            //
            .addDecoratorPath("/admin/*", "/layouts/cms")
            //
            .addDecoratorPath("/home", "/layouts/cms")
            //
            .addDecoratorPath("/user/*", "/layouts/cms")
            //
            .addDecoratorPath("/success", "/layouts/cms")
            //
            .addExcludedPath("/test/*").addExcludedPath("/static/**")
            //
            .addExcludedPath("/druid/*").addExcludedPath("/login")
            //
            .addExcludedPath("/auth/login").addExcludedPath("/register/success");
    }
}

package com.zimu.design.proxy;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MapperInvocationHandler<T> implements InvocationHandler, Serializable {

    private MapperProxy<T> mapperProxy;


    public MapperInvocationHandler(MapperProxy<T> mapperProxy) {
        this.mapperProxy = mapperProxy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        return mapperProxy.execute(args);
    }
}

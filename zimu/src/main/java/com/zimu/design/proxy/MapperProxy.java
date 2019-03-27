package com.zimu.design.proxy;

import com.zimu.entity.UserEntity;

import java.io.Serializable;

public class MapperProxy<T> implements Serializable {


    public T execute(Object[] args) {

        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        UserEntity userEntity = new UserEntity();
        return (T) userEntity;
    }

}

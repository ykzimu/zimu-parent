package com.zimu.design.proxy;

public interface BaseMapper<T, ID> {

    /**
     * @param id
     * @return T
     */
    T findOne(ID id);
}

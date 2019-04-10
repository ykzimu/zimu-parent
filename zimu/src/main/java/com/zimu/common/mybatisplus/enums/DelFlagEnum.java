package com.zimu.common.mybatisplus.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

import java.io.Serializable;

public enum DelFlagEnum implements IEnum {

    /**
     * 已删除
     */
    YES(1),

    /**
     * 未删除
     */
    NO(0);

    private Integer delFlag;

    DelFlagEnum(Integer delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public Serializable getValue() {
        return delFlag;
    }
}

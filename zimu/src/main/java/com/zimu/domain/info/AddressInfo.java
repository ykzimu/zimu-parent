package com.zimu.domain.info;

import com.zimu.domain.entity.DictAddressEntity;
import lombok.Data;

import java.io.Serializable;

@Data
public class AddressInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    public AddressInfo() {

    }

    public AddressInfo(DictAddressEntity dictAddressEntity) {
        this.code = dictAddressEntity.getCode();
        this.name = dictAddressEntity.getName();
    }

    private String code;

    private String name;
}

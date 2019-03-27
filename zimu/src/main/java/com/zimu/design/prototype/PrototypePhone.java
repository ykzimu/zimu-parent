package com.zimu.design.prototype;

import lombok.Data;

import java.io.Serializable;

@Data
public class PrototypePhone implements Serializable {

    private Long phoneNumber;

    private String phoneType;
}

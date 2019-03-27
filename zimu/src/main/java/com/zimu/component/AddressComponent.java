package com.zimu.component;


import java.util.List;

import com.zimu.common.enums.AddressLevel;
import com.zimu.domain.info.AddressInfo;


public interface AddressComponent {

    List<AddressInfo> getList(AddressLevel level, String code);

    List<AddressInfo> getProvinceList();

    List<AddressInfo> getCityList(String code);

    List<AddressInfo> getAreaList(String code);

}

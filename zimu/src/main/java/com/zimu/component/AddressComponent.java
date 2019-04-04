package com.zimu.component;


import com.zimu.domain.info.AddressInfo;

import java.util.List;


public interface AddressComponent {

    List<AddressInfo> getProvinceList();

    List<AddressInfo> getCityList(String code);

    List<AddressInfo> getAreaList(String code);

    void deleteAllCache();
}

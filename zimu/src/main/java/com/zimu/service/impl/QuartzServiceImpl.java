package com.zimu.service.impl;

import com.zimu.common.utils.SpringContextUtils;
import com.zimu.dao.QrtzJobDetailsMapper;
import com.zimu.domain.entity.QrtzJobDetails;
import com.zimu.domain.info.SelectInfo;
import com.zimu.service.QuartzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuartzServiceImpl implements QuartzService {

    @Autowired
    private QrtzJobDetailsMapper qrtzJobDetailsMapper;

    @Override
    public List<SelectInfo> beansList() {


        ApplicationContext applicationContext = SpringContextUtils.getApplicationContext();
        String[] beanNames = applicationContext.getBeanNamesForType(QuartzJobBean.class);
        List<QrtzJobDetails> qrtzJobDetailsList = qrtzJobDetailsMapper.selectByExample(null);

        Boolean flag = null;
        List<String> nameList = new ArrayList<>();
        for (String beanName : beanNames) {
            flag = false;
            Class clazz = SpringContextUtils.getBean(beanName).getClass();

            for (QrtzJobDetails qrtzJobDetails : qrtzJobDetailsList) {
                try {
                    String jobClassName = qrtzJobDetails.getJobClassName();
                    Class jobClass = Class.forName(jobClassName);
                    if (jobClass.equals(clazz)) {
                        flag = true;
                        break;
                    }
                } catch (Exception e) {

                }
            }
            if (!flag) {
                nameList.add(beanName);
            }
        }


        List<SelectInfo> selectInfoList = new ArrayList<>();
        for (String beanName : nameList) {
            SelectInfo selectInfo = new SelectInfo();
            selectInfo.setId(beanName);
            selectInfo.setText(beanName);
            selectInfoList.add(selectInfo);
        }
        return selectInfoList;
    }
}

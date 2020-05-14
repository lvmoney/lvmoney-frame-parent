package com.zhy.frame.newsql.scylla.service.impl;

import com.zhy.frame.newsql.scylla.service.DeviceDataSerivce;
import com.zhy.frame.newsql.scylla.dao.DeviceDataDao;
import com.zhy.frame.newsql.scylla.vo.DeviceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceDataServiceImpl implements DeviceDataSerivce {
    @Autowired
    DeviceDataDao deviceDataDao;

    @Override
    public void saveDeviceData(DeviceData deviceData) {
        deviceDataDao.save(deviceData);
    }
}

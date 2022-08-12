package com.lvmoney.frame.demo.influxdb.controller;/**
 * 描述:
 * 包名:com.lvmoney.frame.db.influxdb.controller
 * 版本信息: 版本1.0
 * 日期:2022/1/14
 * Copyright XXXXXX科技有限公司
 */


import com.alibaba.fastjson.JSON;
import com.lvmoney.frame.demo.influxdb.application.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.ojbk.influxdb.core.Delete;
import plus.ojbk.influxdb.core.InfluxdbTemplate;
import plus.ojbk.influxdb.core.Op;
import plus.ojbk.influxdb.core.Query;
import plus.ojbk.influxdb.core.enums.Order;
import plus.ojbk.influxdb.core.model.DeleteModel;
import plus.ojbk.influxdb.core.model.QueryModel;
import plus.ojbk.influxdb.util.InfluxdbUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/1/14 15:27
 */
@RestController
public class InfluxDbController {


    @Autowired
    private InfluxdbTemplate influxdbTemplate;

    private String measurement = "device";

    @GetMapping("getCount")
    void getCount() {
        QueryModel countModel = new QueryModel();
        ///countModel.setMeasurement(measurement);
        countModel.setMeasurement(InfluxdbUtils.getMeasurement(Device.class));
        countModel.setStart(LocalDateTime.now().plusHours(-2L));
        countModel.setEnd(LocalDateTime.now());
        //countModel.setSelect(Query.count("voltage"));  //只能count field字段
        countModel.setSelect(Query.count(InfluxdbUtils.getCountField(Device.class)));
        countModel.setWhere(Op.where(countModel));
        //获得总条数
        long count = influxdbTemplate.count(Query.build(countModel));
        System.err.println(count);
    }
    @GetMapping("getData")
    void getData() {
        QueryModel model = new QueryModel();
        model.setCurrent(1L);
        model.setSize(10L);
        //model.setMeasurement(measurement);
        model.setMeasurement(InfluxdbUtils.getMeasurement(Device.class));
//        model.setStart(LocalDateTime.now().plusHours(-2L));
//        model.setEnd(LocalDateTime.now());
//        model.setUseTimeZone(true);  //时区
        model.setOrder(Order.DESC);  //排序
        //where 条件中额外参数可放入model.setMap();
        model.setWhere(Op.where(model));
        //分页数据
        List<Device> deviceList = influxdbTemplate.selectList(Query.build(model), Device.class);
        System.err.println(JSON.toJSONString(deviceList));
    }
    @GetMapping("insert")
    void insert() {
        List<Device> deviceList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Device device = new Device();
            device.setDeviceNo("device-" + i);
            device.setValue(new BigDecimal(12.548));
            device.setState(true);
            device.setVoltage(3.5F);
            //  device.setTime(LocalDateTime.now());
            deviceList.add(device);
        }
        influxdbTemplate.insert(deviceList);
    }
    @GetMapping("delete")
    void delete() {
        Map<String, Object> map = new TreeMap<>();
        map.put("device_no", "device-1");
        DeleteModel model = new DeleteModel();
        model.setMap(map);
        //model.setStart(LocalDateTime.now().plusHours(-10L));
        //model.setEnd(LocalDateTime.now());
        model.setMeasurement(measurement);
        model.setWhere(Op.where(model));
        influxdbTemplate.delete(Delete.build(model));
    }


    void other() {
        influxdbTemplate.execute("自己写sql");
    }
}

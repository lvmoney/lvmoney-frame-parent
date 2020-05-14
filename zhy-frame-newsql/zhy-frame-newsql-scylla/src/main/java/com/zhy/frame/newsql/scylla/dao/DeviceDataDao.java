package com.zhy.frame.newsql.scylla.dao;

import com.zhy.frame.newsql.scylla.vo.DeviceData;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DeviceDataDao extends CassandraRepository<DeviceData, String> {
    @Query("select * from device_data where value= ?1")
    List<DeviceData> findByValue(String id);
}
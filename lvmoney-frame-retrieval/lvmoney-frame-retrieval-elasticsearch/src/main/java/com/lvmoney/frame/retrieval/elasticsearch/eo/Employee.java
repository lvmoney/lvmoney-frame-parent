/**
 * 描述:
 * 包名:com.lvmoney.hotel.elasticsearch.ro
 * 版本信息: 版本1.0
 * 日期:2018年11月7日  上午10:37:51
 * Copyright XXXXXX科技有限公司
 */

package com.lvmoney.frame.retrieval.elasticsearch.eo;

/**
 * @describe：
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2018年11月7日 上午10:37:51
 */

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.GeoPointField;

/**
 * @describe：
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2019年1月3日 下午1:45:54
 */
@Document(indexName = "company", type = "employee", shards = 1, replicas = 0)
public class Employee {
    @Id
    private Long id;
    private String first_name;
    private String last_name;
    private int age;
    private String about;
    private String address;
    @GeoPointField
    private String coordinate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

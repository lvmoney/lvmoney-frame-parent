/**
 * 描述:
 * 包名:com.zhy.mongo.mo
 * 版本信息: 版本1.0
 * 日期:2019年1月10日  上午11:27:59
 * Copyright xxxx科技有限公司
 */

package com.zhy.frame.oss.gridfs.mo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月10日 上午11:27:59
 */
@Document(collection = "mongodbTestModel")
public class MongodbTestMo {
    @Id
    private String mid;
    private String name;
    private String age;

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "MongodbTestModel [mid=" + mid + ", name=" + name + ", age=" + age + "]";
    }
}

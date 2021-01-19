package com.lvmoney.frame.demo.seata.client.vo.resp;

import lombok.Data;

/**
 * @describe：
 * @author: lvmoney /成都三合力通科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@Data
public class CommonVo<T> {
    private Integer code;
    private Long date;
    private String msg;
    private boolean success;
    private T data;
}

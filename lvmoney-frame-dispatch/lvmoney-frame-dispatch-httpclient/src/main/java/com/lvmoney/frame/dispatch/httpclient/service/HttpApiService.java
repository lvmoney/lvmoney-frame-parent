/**
 * 描述:
 * 包名:com.lvmoney.httpclient.service
 * 版本信息: 版本1.0
 * 日期:2018年10月30日  下午3:29:38
 * Copyright XXXXXX科技有限公司
 */

package com.lvmoney.frame.dispatch.httpclient.service;


import com.lvmoney.frame.dispatch.httpclient.vo.HttpFileResult;
import com.lvmoney.frame.dispatch.httpclient.vo.HttpResult;

import java.io.File;
import java.net.URLConnection;
import java.util.Map;


/**
 * @describe：
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */

public interface HttpApiService {
    /**
     * 简单的get
     *
     * @param url: 请求url
     * @throws Exception
     * @return: java.lang.String
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 20:42
     */
    String doGet(String url) throws Exception;

    /**
     * get map
     *
     * @param url: 请求地址
     * @param map: 请求数据
     * @throws Exception
     * @return: java.lang.String
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 20:44
     */
    String doGet(String url, Map<String, Object> map) throws Exception;

    /**
     * post
     *
     * @param url: 请求地址
     * @param map: 请求数据
     * @throws Exception
     * @return: com.lvmoney.httpclient.vo.HttpResult
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 20:45
     */
    HttpResult doPost(String url, Map<String, Object> map) throws Exception;

    /**
     * 简单的post
     *
     * @param url: 请求地址
     * @throws Exception
     * @return: com.lvmoney.httpclient.vo.HttpResult
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 20:45
     */
    HttpResult doPost(String url) throws Exception;

    /**
     * post json
     *
     * @param url:  请求地址
     * @param json: json对象
     * @throws
     * @return: com.lvmoney.httpclient.vo.HttpResult
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 20:45
     */
    HttpResult doJsonPost(String url, String json);

    /**
     * post 文件 请求参数
     *
     * @param url:       请求地址
     * @param fileParam: 文件
     * @param map:       请求数据
     * @throws
     * @return: com.lvmoney.httpclient.vo.HttpResult
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 20:46
     */
    HttpResult doFilePost(String url, Map<String, File> fileParam, Map<String, Object> map);


    /**
     * post json 文件
     *
     * @param url:  请求地址
     * @param json: json对象
     * @throws
     * @return: com.lvmoney.httpclient.vo.HttpFileResult
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 20:47
     */
    HttpFileResult doJsonFilePost(String url, String json);

    /**
     * connection msg
     *
     * @param url:   请求地址
     * @param param: 请求参数
     * @throws
     * @return: java.net.URLConnection
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 20:47
     */
    URLConnection getConnectionMsg(String url, String param);
}

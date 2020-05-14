/**
 * 描述:
 * 包名:com.zhy.pay.httpclient
 * 版本信息: 版本1.0
 * 日期:2018年10月18日  下午3:36:41
 * Copyright xxxx科技有限公司
 */

package com.zhy.frame.dispatch.httpclient.service.impl;

import com.zhy.frame.base.core.constant.BaseConstant;
import com.zhy.frame.base.core.exception.BusinessException;
import com.zhy.frame.dispatch.common.exception.DispatchException;
import com.zhy.frame.dispatch.httpclient.service.HttpApiService;
import com.zhy.frame.dispatch.httpclient.vo.HttpFileResult;
import com.zhy.frame.dispatch.httpclient.vo.HttpResult;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月18日 下午3:36:41
 */

@Service("frameHttpAPIService")
public class HttpApiServiceImpl implements HttpApiService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpApiServiceImpl.class);
    @Autowired
    private CloseableHttpClient httpClient;

    @Autowired
    private RequestConfig config;

    private static final int HTTP_SUCCESS_CODE = 200;

    /**
     * 不带参数的get请求，如果状态码为200，则返回body，如果不为200，则返回null
     *
     * @param url
     * @return
     * @throws Exception
     */
    @Override
    public String doGet(String url) {
        // 声明 http get 请求
        HttpGet httpGet = new HttpGet(url);

        // 装载配置信息
        httpGet.setConfig(config);
        // 发起请求
        CloseableHttpResponse response;
        try {
            response = this.httpClient.execute(httpGet);
            // 判断状态码是否为200
            if (response.getStatusLine().getStatusCode() == HTTP_SUCCESS_CODE) {
                // 返回响应体的内容
                return EntityUtils.toString(response.getEntity(), BaseConstant.CHARACTER_ENCODE_UTF8_UPPER);
            } else {
                httpGet.abort();
            }
        } catch (IOException e) {
            httpGet.abort();
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * 带参数的get请求，如果状态码为200，则返回body，如果不为200，则返回null
     *
     * @param url
     * @return
     * @throws Exception
     */
    @Override
    public String doGet(String url, Map<String, Object> map) throws Exception {
        URIBuilder uriBuilder = new URIBuilder(url);

        if (map != null) {
            // 遍历map,拼接请求参数
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
            }
        }
        String result = this.doGet(uriBuilder.build().toString());
        // 调用不带参数的get请求
        return result;

    }

    /**
     * 带参数的post请求
     *
     * @param url
     * @param map
     * @return
     * @throws Exception
     */
    @Override
    public HttpResult doPost(String url, Map<String, Object> map) throws Exception {
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);
        // 加入配置信息
        httpPost.setConfig(config);

        // 判断map是否为空，不为空则进行遍历，封装from表单对象
        if (map != null) {
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                list.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }
            // 构造from表单对象
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, BaseConstant.CHARACTER_ENCODE_UTF8_UPPER);

            // 把表单放到post里
            httpPost.setEntity(urlEncodedFormEntity);
        }

        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpPost);
        return new HttpResult(response.getStatusLine().getStatusCode(),
                EntityUtils.toString(response.getEntity(), BaseConstant.CHARACTER_ENCODE_UTF8_UPPER));
    }

    /**
     * 不带参数post请求
     *
     * @param url
     * @return
     * @throws Exception
     */
    @Override
    public HttpResult doPost(String url) throws Exception {
        return this.doPost(url, null);
    }

    @Override
    public HttpResult doJsonPost(String url, String json) {
        try {
            //第二步：创建httpPost对象
            HttpPost httpPost = new HttpPost(url);
            //第三步：给httpPost设置JSON格式的参数
            StringEntity requestEntity = new StringEntity(json, BaseConstant.CHARACTER_ENCODE_UTF8_LOWER);
            requestEntity.setContentEncoding(BaseConstant.CHARACTER_ENCODE_UTF8_UPPER);
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setEntity(requestEntity);
            CloseableHttpResponse response = this.httpClient.execute(httpPost);
            return new HttpResult(response.getStatusLine().getStatusCode(),
                    EntityUtils.toString(response.getEntity(), BaseConstant.CHARACTER_ENCODE_UTF8_UPPER));

        } catch (Exception e) {
            LOGGER.error("通过httpclient 发送json请求数据报错:{}", e.getMessage());
            throw new BusinessException(DispatchException.Proxy.HTTPCLIENT_JSON_ERROR);
        }
    }

    @Override
    public HttpResult doFilePost(String url, Map<String, File> fileParam, Map<String, Object> map) {
        try {
            HttpPost httpPost = new HttpPost(url);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.RFC6532);
            ;
            if (fileParam != null) {
                //遍历图片并添加到MultipartEntity实体中
                for (Map.Entry<String, File> entry : fileParam.entrySet()) {
                    builder.addBinaryBody(entry.getKey(), new FileInputStream(entry.getValue()), ContentType.DEFAULT_BINARY, entry.getValue().getName());
                }
            }
            if (map != null) {
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    builder.addPart(entry.getKey(), new StringBody(entry.getValue().toString(), ContentType.create("text/plain", Consts.UTF_8)));
                }
            }
            // 生成 HTTP POST 实体
            HttpEntity entity = builder.build();
            httpPost.setEntity(entity);
            CloseableHttpResponse response = this.httpClient.execute(httpPost);
            return new HttpResult(response.getStatusLine().getStatusCode(),
                    EntityUtils.toString(response.getEntity(), BaseConstant.CHARACTER_ENCODE_UTF8_UPPER));
        } catch (Exception e) {
            LOGGER.error("通过httpclient 发送file请求数据报错:{}", e.getMessage());
            throw new BusinessException(DispatchException.Proxy.HTTPCLIENT_FILE_ERROR);
        }
    }

    @Override
    public HttpFileResult doJsonFilePost(String url, String json) {
        try {
            //第二步：创建httpPost对象
            HttpPost httpPost = new HttpPost(url);
            //第三步：给httpPost设置JSON格式的参数
            StringEntity requestEntity = new StringEntity(json, BaseConstant.CHARACTER_ENCODE_UTF8_LOWER);
            requestEntity.setContentEncoding(BaseConstant.CHARACTER_ENCODE_UTF8_UPPER);
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setEntity(requestEntity);
            CloseableHttpResponse response = this.httpClient.execute(httpPost);
            return new HttpFileResult(response.getStatusLine().getStatusCode(),
                    response.getEntity().getContent());

        } catch (Exception e) {
            LOGGER.error("通过httpclient 发送file请求数据返回文件流报错:{}", e.getMessage());
            throw new BusinessException(DispatchException.Proxy.HTTPCLIENT_FILE2_ERROR);
        }
    }

    @Override
    public URLConnection getConnectionMsg(String url, String param) {
        URLConnection connection = null;
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
        } catch (Exception e) {
            LOGGER.error("获得hpptclient请求链接信息报错:{}", e.getMessage());
            throw new BusinessException(DispatchException.Proxy.HTTPCLIENT_CONNECTION_ERROR);
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                LOGGER.error("获得hpptclient请求链接信息报错:{}", e2.getMessage());
                throw new BusinessException(DispatchException.Proxy.HTTPCLIENT_CONNECTION_ERROR);
            }
        }
        return connection;
    }
}

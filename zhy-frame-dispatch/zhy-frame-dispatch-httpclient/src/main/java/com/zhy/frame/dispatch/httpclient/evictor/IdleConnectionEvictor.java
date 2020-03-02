/**
 * 描述:
 * 包名:com.zhy.frame.dispatch.httpclient.evictor
 * 版本信息: 版本1.0
 * 日期:2018年10月18日  下午3:43:09
 * Copyright xxxx科技有限公司
 */

package com.zhy.frame.dispatch.httpclient.evictor;

import org.apache.http.conn.HttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月18日 下午3:43:10
 */

@Component
public class IdleConnectionEvictor extends Thread {

    @Autowired
    private HttpClientConnectionManager connMgr;

    private volatile boolean shutdown;

    public IdleConnectionEvictor() {
        super();
        super.start();
    }

    @Override
    public void run() {
        try {
            while (!shutdown) {
                synchronized (this) {
                    wait(5000);
                    // 关闭失效的连接
                    connMgr.closeExpiredConnections();
                }
            }
        } catch (InterruptedException ex) {
            // 结束
        }
    }

    /**
     * @describe: 关闭清理无效连接的线程
     * @param: []
     * @return: void
     * @author: lvmoney /四川******科技有限公司
     * 2019/9/9 10:13
     */
    public void shutdown() {
        shutdown = true;
        synchronized (this) {
            notifyAll();
        }
    }
}

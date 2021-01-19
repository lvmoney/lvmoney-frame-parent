package com.lvmoney.frame.sync.canal.common.transfer;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.protocol.Message;
import com.alibaba.otter.canal.protocol.exception.CanalClientException;
import com.lvmoney.frame.base.core.exception.BusinessException;
import com.lvmoney.frame.sync.canal.common.annotation.CanalEventListener;
import com.lvmoney.frame.sync.canal.common.properties.CanalProp;
import com.lvmoney.frame.sync.canal.common.vo.ListenerPointVo;
import com.lvmoney.frame.sync.common.exception.SyncException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney /成都三合力通科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
public abstract class AbstractMessageTransponder implements MessageTransponder {

    /**
     * canal 连接器
     */
    private final CanalConnector connector;

    /**
     * custom 连接配置
     */
    protected final CanalProp.Instance config;

    /**
     * canal 服务指令
     */
    protected final String destination;

    /**
     * 实现接口的 canal 监听器(上：表内容，下：表结构)
     */
    protected final List<CanalEventListener> listeners = new ArrayList<>();


    /**
     * 通过注解方式的 canal 监听器
     */
    protected final List<ListenerPointVo> annoListeners = new ArrayList<>();

    /**
     * canal 客户端的运行状态
     */
    private volatile boolean running = true;

    /**
     * 日志记录
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractMessageTransponder.class);

    /**
     * 构造方法，初始化参数
     *
     * @param connector     canal 连接器
     * @param config        canal 连接配置
     * @param listeners     实现接口层的 canal 监听器(表结构)
     * @param annoListeners 通过注解方式的 canal 监听器
     * @return
     */
    public AbstractMessageTransponder(CanalConnector connector, Map.Entry<String, CanalProp.Instance> config, List<CanalEventListener> listeners, List<ListenerPointVo> annoListeners) {
        //参数处理
        if (connector == null) {
            throw new BusinessException(SyncException.Proxy.CANAL_CONNECTOR_IS_NULL);
        }
        if (config == null) {
            throw new BusinessException(SyncException.Proxy.CANAL_CONFIG_IS_NULL);
        }
        //参数初始化
        this.connector = connector;
        this.destination = config.getKey();
        this.config = config.getValue();
        if (listeners != null) {
            this.listeners.addAll(listeners);
        }

        if (annoListeners != null) {
            this.annoListeners.addAll(annoListeners);
        }
    }

    /**
     * 线程搞起来
     *
     * @param
     * @return
     */
    @Override
    public void run() {
        //错误重试次数
        int errorCount = config.getRetryCount();
        //捕获信息的心跳时间
        final long interval = config.getAcquireInterval();
        //当前线程的名字
        final String threadName = Thread.currentThread().getName();
        //若线程正在进行
        while (running && !Thread.currentThread().isInterrupted()) {
            try {
                //获取消息
                Message message = connector.getWithoutAck(config.getBatchSize());
                //获取消息 ID
                long batchId = message.getId();
                //消息数
                int size = message.getEntries().size();
                //debug 模式打印消息数
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("{}: 从 canal 服务器获取消息： >>>>> 数:{}", threadName, size);
                }
                //若是没有消息
                if (batchId == -1 || size == 0) {
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("{}: 没有任何消息啊，我休息{}毫秒", threadName, interval);
                    }
                    //休息
                    Thread.sleep(interval);
                } else {
                    //处理消息
                    distributeEvent(message);
                }
                //确认消息已被处理完
                connector.ack(batchId);
                //若是 debug模式
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("{}: 确认消息已被消费，消息ID:{}", threadName, batchId);
                }
            } catch (CanalClientException e) {
                //每次错误，重试次数减一处理
                errorCount--;
                LOGGER.error(threadName + "获取数据错误:{}", e.getLocalizedMessage());
                try {
                    //等待时间
                    Thread.sleep(interval);
                } catch (InterruptedException e1) {
                    errorCount = 0;
                }
            } catch (InterruptedException e) {
                //线程中止处理
                errorCount = 0;
                connector.rollback();
            } finally {
                //若错误次数小于 0
                if (errorCount <= 0) {
                    //停止 canal 客户端
                    stop();
                    LOGGER.info("{}: canal 客户端已停止... ", Thread.currentThread().getName());
                }
            }
        }
        //停止 canal 客户端
        stop();
        LOGGER.info("{}: canal 客户端已停止. ", Thread.currentThread().getName());
    }

    /**
     * 处理监听的事件
     *
     * @param message: 信息体
     * @return void
     * @author: lvmoney /XXXXXX科技有限公司
     * @data: 2019/9/9 19:05
     */
    protected abstract void distributeEvent(Message message);

    /**
     * @describe: 停止 canal 客户端
     * @param: []
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/9/9 9:49
     */
    void stop() {
        running = false;
    }

}

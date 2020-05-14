package com.zhy.frame.sync.canal.common.client;

import com.alibaba.otter.canal.client.CanalConnector;
import com.zhy.frame.sync.canal.common.annotation.CanalEventListener;
import com.zhy.frame.sync.canal.common.properties.CanalProp;
import com.zhy.frame.sync.canal.common.spring.HandListenerContext;
import com.zhy.frame.sync.canal.common.util.ContextBeanUtil;
import com.zhy.frame.sync.canal.common.vo.ListenerPointVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @describe：通过线程池处理
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
public class SimpleCanalClient extends AbstractCanalClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleCanalClient.class);
    /**
     * 声明一个线程池
     */
    private ExecutorService executorService;
    /**
     * 失效时间
     */
    private static final Long TIMEOUT = 30L;
    /**
     * 通过实现接口的监听器
     */
    protected final List<CanalEventListener> listeners = new ArrayList<>();

    /**
     * 通过注解的方式实现的监听器
     */
    private final List<ListenerPointVo> annoListeners = new ArrayList<>();

    HandListenerContext handListenerContext;

    /**
     * @describe: 构造方法，进行一些基本信息初始化
     * @param: [canalProp, handListenerContext]
     * @return:
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/9/9 14:14
     */
    public SimpleCanalClient(CanalProp canalProp, HandListenerContext handListenerContext) {
        super(canalProp);
        //这边上可能需要调整，紧跟核数脚步走，默认核心线程数5个，最大线程数20个，线程两分钟分钟不执行就。。。
//		executor = new ThreadPoolExecutor(5, 20,
//				120L, TimeUnit.SECONDS,
//				new SynchronousQueue<>(), Executors.defaultThreadFactory());

        executorService = new ThreadPoolExecutor(canalProp.getInitPoolSize(), canalProp.getMaxPoolSize(),
                120L, TimeUnit.SECONDS,
                new SynchronousQueue<>(), new ThreadPoolExecutor.AbortPolicy());
//        executorService = Executors.newFixedThreadPool(canalProp.getPoolSize());
        //初始化监听器
        this.handListenerContext = handListenerContext;
        initListeners();
    }

    @Override
    protected void process(CanalConnector connector, Map.Entry<String, CanalProp.Instance> config) {
//		executor.submit(factory.newTransponder(connector, config, listeners, annoListeners));
        executorService.submit(factory.newTransponder(connector, config, listeners, annoListeners));
    }

    /**
     * @describe: 关闭 canal 客户端
     * @param: []
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/9/9 14:15
     */
    @Override
    public void stop() {
        //停止 canal 客户端
        super.stop();
        //优雅的线程池关闭
        executorService.shutdown();
        try {
            // Wait a while for existing tasks to terminate
            if (!executorService.awaitTermination(TIMEOUT, TimeUnit.SECONDS)) {
                // Cancel currently executing tasks
                executorService.shutdownNow();
                // Wait a while for tasks to respond to being cancelled
                if (!executorService.awaitTermination(TIMEOUT, TimeUnit.SECONDS)) {
                    LOGGER.warn("{Pool did not terminate ");
                }

            }
        } catch (InterruptedException ie) {
            // (Re-)Cancel if current thread also interrupted
            executorService.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }

    /**
     * @describe: 初始化监听器
     * @param: []
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/9/9 14:15
     */
    private void initListeners() {
        LOGGER.info("{}: 监听器正在初始化....", Thread.currentThread().getName());
        //获取监听器,这里代码暂时没用用到的意义
        //        List<CanalEventListener> list = ApplicationBeanUtil.getBeansOfType(CanalEventListener.class);
//        //若没有任何监听的，我也不知道引入这个 jar 干什么，直接返回吧
//        if (list != null) {
//            //若存在目标监听，放入 listenerMap
//            listeners.addAll(list);
//        }

        //若是你喜欢通过注解的方式去监听的话。。
//        Map<String, Object> listenerMap = ApplicationBeanUtil.getBeansWithAnnotation(CanalEventListener.class);
//        //也放入 map
//        if (listenerMap != null) {
//            for (Object target : listenerMap.values()) {
//                //方法获取
//                Method[] methods = target.getClass().getDeclaredMethods();
//                if (methods != null && methods.length > 0) {
//                    for (Method method : methods) {
//                        //获取监听的节点
//                        ListenPoint l = AnnotationUtils.findAnnotation(method, ListenPoint.class);
//                        if (l != null) {
//                            annoListeners.add(new ListenerPointVo(target, method, l));
//                        }
//                    }
//                }
//            }
//        }

        annoListeners.addAll(ContextBeanUtil.getDataHandEventListener(handListenerContext));
        //初始化监听结束
        LOGGER.info("{}: 监听器初始化完成.", Thread.currentThread().getName());
        //整个项目上下文都没发现监听器。。。
//        if (logger.isWarnEnabled() && listeners.isEmpty() && annoListeners.isEmpty()) {
//            LOGGER.warn("{}: 该项目中没有任何监听的目标! ", Thread.currentThread().getName());
//        }

        if (LOGGER.isWarnEnabled() && annoListeners.isEmpty()) {
            LOGGER.warn("{}: 该项目中没有任何监听的目标! ", Thread.currentThread().getName());
        }
    }
}

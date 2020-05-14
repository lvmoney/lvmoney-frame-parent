package com.zhy.frame.sync.canal.common.transfer;


import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import com.zhy.frame.base.core.exception.BusinessException;
import com.zhy.frame.sync.canal.common.annotation.CanalEventListener;
import com.zhy.frame.sync.canal.common.annotation.ListenPoint;
import com.zhy.frame.sync.canal.common.properties.CanalProp;
import com.zhy.frame.sync.canal.common.vo.DbMsgVo;
import com.zhy.frame.sync.canal.common.vo.ListenerPointVo;
import com.zhy.frame.sync.common.exception.SyncException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * @describe：默认转信息换器
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
public class DefaultMessageTransponder extends AbstractMessageTransponder {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultMessageTransponder.class);

    public DefaultMessageTransponder(CanalConnector connector, Map.Entry<String, CanalProp.Instance> config,
                                     List<CanalEventListener> listeners, List<ListenerPointVo> annoListeners) {
        super(connector, config, listeners, annoListeners);
    }


    /**
     * 断言注解方式的监听过滤规则
     *
     * @param destination 指定
     * @param schemaName  数据库实例
     * @param tableName   表名称
     * @param eventType   事件类型
     * @return
     */
    protected Predicate<Map.Entry<Method, ListenPoint>> getAnnotationFilter(String destination, String schemaName, String tableName, CanalEntry.EventType eventType) {
        //看看指令是否正确
        Predicate<Map.Entry<Method, ListenPoint>> df = e -> StringUtils.isEmpty(e.getValue().destination())
                || e.getValue().destination().equals(destination) || destination == null;

        //看看数据库实例名是否一样
        Predicate<Map.Entry<Method, ListenPoint>> sf = e -> e.getValue().schema().length == 0
                || Arrays.stream(e.getValue().schema()).anyMatch(s -> s.equals(schemaName)) || schemaName == null;

        //看看表名是否一样
        Predicate<Map.Entry<Method, ListenPoint>> tf = e -> e.getValue().table().length == 0
                || Arrays.stream(e.getValue().table()).anyMatch(t -> t.equals(tableName)) || tableName == null;

        //类型一致？
        Predicate<Map.Entry<Method, ListenPoint>> ef = e -> e.getValue().eventType().length == 0
                || Arrays.stream(e.getValue().eventType()).anyMatch(ev -> ev == eventType) || eventType == null;

        return df.and(sf).and(tf).and(ef);
    }

    /**
     * 获取处理的参数
     *
     * @param method    监听的方法
     * @param dbMsgVo   事件节点
     * @param rowChange 詳細參數
     * @return
     * @author lvmoney
     * @time 2018/5/28 17:18
     */
    protected Object[] getInvokeArgs(Method method, DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange) {
        return Arrays.stream(method.getParameterTypes())
                .map(p -> p == DbMsgVo.class ? dbMsgVo : p == CanalEntry.RowChange.class ? rowChange : null)
                .toArray();
    }


    /**
     * 忽略实体类的类型
     *
     * @param
     * @return
     */
    protected List<CanalEntry.EntryType> getIgnoreEntryTypes() {
        return Arrays.asList(CanalEntry.EntryType.TRANSACTIONBEGIN, CanalEntry.EntryType.TRANSACTIONEND, CanalEntry.EntryType.HEARTBEAT);
    }

    /**
     * 处理消息
     */
    @Override
    protected void distributeEvent(Message message) {
        //获取操作实体
        List<CanalEntry.Entry> entries = message.getEntries();
        //遍历实体
        for (CanalEntry.Entry entry : entries) {
            //忽略实体类的类型
            List<CanalEntry.EntryType> ignoreEntryTypes = getIgnoreEntryTypes();
            if (ignoreEntryTypes != null
                    && ignoreEntryTypes.stream().anyMatch(t -> entry.getEntryType() == t)) {
                continue;
            }
            //canal 改变信息
            CanalEntry.RowChange rowChange = null;
            try {
                //获取信息改变
                rowChange = CanalEntry.RowChange.parseFrom(entry.getStoreValue());

            } catch (Exception e) {
				/*throw new CanalClientException("错误 ##转换错误 , 数据信息:" + entry.toString(),
						e);*/
            }

            distributeByAnnotation(destination,
                    entry.getHeader().getSchemaName(),
                    entry.getHeader().getTableName(), rowChange);
			/*distributeByImpl(destination,
					entry.getHeader().getSchemaName(),
					entry.getHeader().getTableName(), rowChange);*/

        }
    }

    /**
     * 处理注解方式的 canal 监听器,ERASE删除表,QUERY创建表，清空表TRUNCATE
     *
     * @param destination canal 指令
     * @param schemaName  实例名称
     * @param tableName   表名称
     * @param rowChange   数据
     * @return
     */
    protected void distributeByAnnotation(String destination,
                                          String schemaName,
                                          String tableName,
                                          CanalEntry.RowChange rowChange) {

        //对注解的监听器进行事件委托
        if (!CollectionUtils.isEmpty(annoListeners)) {
            annoListeners.forEach(point -> point
                    .getInvokeMap()
                    .entrySet()
                    .stream()
                    .filter(getAnnotationFilter(destination, schemaName, tableName, rowChange.getEventType()))
                    .forEach(entry -> {
                        Method method = entry.getKey();
                        method.setAccessible(true);
                        try {
                            DbMsgVo dbMsgVo = new DbMsgVo();
                            dbMsgVo.setDestination(destination);
                            dbMsgVo.setSchemaName(schemaName);
                            dbMsgVo.setTableName(tableName);
                            Object[] args = getInvokeArgs(method, dbMsgVo, rowChange);
                            method.invoke(point.getTarget(), args);
                        } catch (Exception e) {
                            LOGGER.error("{}: 委托 canal 监听器发生错误! 错误类:{}, 方法名:{}",
                                    Thread.currentThread().getName(),
                                    point.getTarget().getClass().getName(), method.getName());
                            throw new BusinessException(SyncException.Proxy.CANAL_LISTENER_METHOD_INVOKE_ERROR);
                        }
                    }));
        }
    }


    /**
     * 处理监听信息
     */
//	protected void distributeByImpl(String destination,
//									String schemaName,
//									String tableName,
//									CanalEntry.RowChange rowChange) {
//		if (listeners != null) {
//			for (CanalEventListener listener : listeners) {
//				listener.onEvent(destination, schemaName, tableName, rowChange);
//			}
//		}
//	}
}

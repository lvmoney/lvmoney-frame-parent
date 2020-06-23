package com.zhy.frame.sync.canal.common.service;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.zhy.frame.core.vo.Page;
import com.zhy.frame.sync.canal.common.vo.DbMsgVo;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
public interface CanalService {

    /**
     * 把获得的canal有变更的数据保存到redis中，list的形式.key:数据库名称_表名。list里面存的是map的json数据，这个json数据可以 通过工具转换成bean（系统提供工具），在其他模块使用的时候，可根据redis里面的数据自定义bean用工具把json转为bean
     *
     * @param dbMsgVo:   数据库信息实体
     * @param rowChange: 更改的行数据
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:51
     */
    void save(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange);


    /**
     * 删除指定的数据
     *
     * @param dbMsgVo:   数据库信息实体
     * @param rowChange: 更改的行数据
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:51
     */
    void deleteRow(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange);

    /**
     * 删除表数据
     *
     * @param dbMsgVo: 数据库信息实体
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:52
     */
    void deleteTable(DbMsgVo dbMsgVo);

    /**
     * 重命名数据库表
     *
     * @param dbMsgVo:   数据库信息实体
     * @param rowChange: 更改的行数据
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:53
     */
    void renameTable(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange);

    /**
     * 删除数据库操作
     *
     * @param dbMsgVo:   数据库信息实体
     * @param rowChange: 更改的行数据
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:53
     */
    void deleteDb(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange);

    /**
     * 分页获得数据
     * @param page:
     * @param key:
     * @return: com.zhy.frame.core.vo.Page
     * @throws
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/6/9 14:39
     */
    Page getData(Page page, String key);


}

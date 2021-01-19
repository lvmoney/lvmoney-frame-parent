package com.lvmoney.frame.demo.seata.test.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lvmoney.frame.demo.seata.test.po.Stage;
import org.apache.ibatis.annotations.Update;

/**
 * @describe：
 * @author: lvmoney /成都三合力通科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
public interface StageDao extends BaseMapper<Stage> {
    /**
     * 测试 用于更新
     *
     * @param userId:
     * @throws
     * @return: int
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/10 8:50
     */
    @Update("update stage as a set a.stage = a.stage-20 where a.user_id=#{userId}")
    int updateStage(String userId);
}
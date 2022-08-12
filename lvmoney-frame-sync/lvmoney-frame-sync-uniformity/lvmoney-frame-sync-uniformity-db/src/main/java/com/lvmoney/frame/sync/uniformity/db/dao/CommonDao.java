package com.lvmoney.frame.sync.uniformity.db.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 交易账户表 Mapper 接口
 * </p>
 *
 * @author lvmoney
 * @since 2021-12-29
 */
public interface CommonDao extends BaseMapper {
    /**
     * 通用sql
     *
     * @param sql:
     * @throws
     * @return: java.util.List
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/12/30 16:57
     */
    @Select({"${sql}"})
    @ResultType(ArrayList.class)
    List<Map> executeQuery(@Param("sql") String sql);

    /**
     * 获得数据总数
     *
     * @param sql:
     * @throws
     * @return: java.lang.Long
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/12/31 13:00
     */
    @Select({"${sql}"})
    @ResultType(Long.class)
    Long executeCount(@Param("sql") String sql);
}

package com.zhy.demo.db.dao;

import com.baomidou.mybatisplus.annotation.SqlParser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhy.demo.db.entity.DictionariesInfo;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 会用相关字典表 Mapper 接口
 * </p>
 *
 * @author lvmoney
 * @since 2020-01-15
 */
public interface DictionariesInfoDao extends BaseMapper<DictionariesInfo> {
    @Select("select * from dictionaries_info where name = #{name}")
    DictionariesInfo getDataByName(String name);
}

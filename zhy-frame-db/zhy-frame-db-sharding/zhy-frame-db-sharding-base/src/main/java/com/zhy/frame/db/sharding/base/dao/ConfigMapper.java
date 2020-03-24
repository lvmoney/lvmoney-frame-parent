package com.zhy.frame.db.sharding.base.dao;

import com.zhy.frame.db.sharding.base.po.Config;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Description
 *
 * @author hujy
 * @version 1.0
 * @date 2019-09-20 10:22
 */
@Mapper
public interface ConfigMapper {
    @Insert("insert into t_config(id,remark) values(#{id},#{remark})")
    Integer save(Config config);

    @Select("select * from t_config  where id = #{id}")
    Config selectById(Integer id);
}

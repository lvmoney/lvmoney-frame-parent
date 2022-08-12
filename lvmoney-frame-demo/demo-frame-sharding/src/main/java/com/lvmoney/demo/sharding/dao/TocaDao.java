package com.lvmoney.demo.sharding.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lvmoney.demo.sharding.entity.Toca;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 会用相关字典表 Mapper 接口
 * </p>
 *
 * @author lvmoney
 * @since 2020-01-15
 */
@Mapper
public interface TocaDao extends BaseMapper<Toca> {
    @Select("select * from dictionaries_info where name = #{name}")
    Toca getDataByName(String name);

    @Insert("insert into toca(id,name,phone,code,num) values(#{toca.id},#{toca.name},#{toca.phone},#{toca.code},#{toca.num})")
    void insertData(@Param("toca") Toca toca);
}

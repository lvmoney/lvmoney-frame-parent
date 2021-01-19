package com.lvmoney.demo.sharding.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lvmoney.demo.sharding.entity.Toca;
import com.lvmoney.demo.sharding.po.TestPo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 会用相关字典表 Mapper 接口
 * </p>
 *
 * @author lvmoney
 * @since 2020-01-15
 */
public interface TocaDao extends BaseMapper<Toca> {


    @Insert("insert into toca(id,name,phone,code,num) values(#{toca.id},#{toca.name},#{toca.phone},#{toca.code},#{toca.num})")
    void insertData(@Param("toca") Toca toca);


    @Select("select a.id as id ,b.id as tid from toca2 as a left join toca2 as b on a.id=b.id")
    List<TestPo> selectAll();
}

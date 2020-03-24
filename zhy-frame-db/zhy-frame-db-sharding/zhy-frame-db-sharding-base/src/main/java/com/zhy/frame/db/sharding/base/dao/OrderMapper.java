package com.zhy.frame.db.sharding.base.dao;

import com.zhy.frame.db.sharding.base.po.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 建表：ddl.sql
 *
 * @author hujy
 * @version 1.0
 * @date 2019-09-18 10:35
 */
@Mapper
public interface OrderMapper {

    @Insert("insert into t_order(order_id,user_id,config_id,remark) values(#{orderId},#{userId},#{configId},#{remark})")
    Integer save(Order order);

    @Select("select order_id orderId, user_id userId, config_id configId, remark from t_order  " +
            "where user_id = #{userId} and order_id = #{orderId}")
    Order selectBySharding(Integer userId, Integer orderId);

    @Select("select o.order_id orderId, o.user_id userId, o.config_id configId, o.remark from " +
            "t_order o inner join t_order_item i on o.order_id = i.order_id " +
            "where o.user_id =#{userId} and o.order_id =#{orderId}")
    List<Order> selectOrderJoinOrderItem(Integer userId, Integer orderId);

    @Select("select  o.order_id orderId, o.user_id userId, o.config_id configId, o.remark " +
            "from t_order o inner join t_config c on o.config_id = c.id " +
            "where o.user_id =#{userId} and o.order_id =#{orderId}")
    List<Order> selectOrderJoinConfig(Integer userId, Integer orderId);
}

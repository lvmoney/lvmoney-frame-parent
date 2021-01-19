package com.lvmoney.frame.workflow.activiti.dao;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotations
 * 版本信息: 版本1.0
 * 日期:2019/1/22
 * Copyright 成都三合力通科技有限公司
 */


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lvmoney.frame.workflow.activiti.entity.VacationForm;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @describe：
 * @author: lvmoney /成都三合力通科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
public interface VacationFormMapper extends BaseMapper<VacationForm> {
    /**
     * 查询所有
     *
     * @throws
     * @return: java.util.List<com.lvmoney.activiti.entity.VacationForm>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/10 8:59
     */
    @Select("select * from vacation_form")
    List<VacationForm> selectAll();
}

package com.lvmoney.frame.sync.uniformity.db.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.demo.sharding.service.impl
 * 版本信息: 版本1.0
 * 日期:2021/12/31
 * Copyright XXXXXX科技有限公司
 */


import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.lvmoney.frame.base.core.constant.BaseConstant;
import com.lvmoney.frame.sync.uniformity.db.dao.CommonDao;
import com.lvmoney.frame.sync.uniformity.db.dto.BuildHintManagerDto;
import com.lvmoney.frame.sync.uniformity.db.dto.GetBitmapSharingListDto;
import com.lvmoney.frame.sync.uniformity.db.dto.GetSelectDurationDto;
import com.lvmoney.frame.sync.uniformity.db.dto.GetSharingListDto;
import com.lvmoney.frame.sync.uniformity.db.ro.SelectDurationRo;
import com.lvmoney.frame.sync.uniformity.db.service.CommonService;
import com.lvmoney.frame.sync.uniformity.db.service.UniformityRedisService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shardingsphere.api.hint.HintManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static com.lvmoney.frame.base.core.constant.BaseConstant.SINGLE_QUOTATION_MARK;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/12/31 11:08
 */
@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    CommonDao commonDao;
    @Autowired
    UniformityRedisService uniformityRedisService;
    /**
     * select
     */
    private static final String SELECT = "SELECT";
    /**
     * from
     */
    private static final String FROM = "FROM";

    /**
     * WHERE 1=1
     */
    private static final String WHERE = "WHERE 1=1";
    /**
     * and
     */
    private static final String AND = "AND";
    /**
     * 1
     */
    private static final String VALID_TRUE = "1";
    /**
     * ORDER BY
     */
    private static final String ORDER_BY = "ORDER BY";
    /**
     * limit
     */
    private static final String LIMIT = "LIMIT";
    /**
     * count
     */
    private static final String COUNT = "COUNT";
    /**
     * 时间开始
     */
    private static final String DATE_START = ">";
    /**
     * 时间结束
     */
    private static final String DATE_END = "<=";
    /**
     * in
     */
    private static final String IN = "in";

    /**
     * 主键
     */
    private static final String PRIMARY_KEY_FIELD = "id";


    @Override
    public List<Map> getShardingList(GetSharingListDto getSharingListDto) {
        try (HintManager hintManager = HintManager.getInstance()) {
            BuildHintManagerDto buildHintManagerDto = Convert.convert(BuildHintManagerDto.class, getSharingListDto);
            buildHintManager(hintManager, buildHintManagerDto);
            String sql = this.structureShardingSelectSql(getSharingListDto);
            List list = commonDao.executeQuery(sql);
            return list;
        }

    }

    @Override
    public Long getShardingCount(GetSharingListDto getSharingListDto) {
        try (HintManager hintManager = HintManager.getInstance()) {
            BuildHintManagerDto buildHintManagerDto = Convert.convert(BuildHintManagerDto.class, getSharingListDto);
            buildHintManager(hintManager, buildHintManagerDto);
            String sql = this.structureShardingCountSql(getSharingListDto);
            Long count = commonDao.executeCount(sql);
            return count;
        }

    }


    @Override
    public String structureShardingSelectSql(GetSharingListDto getSharingListDto) {
        StringBuffer sql = new StringBuffer();
        List<String> selectField = getSharingListDto.getSelectField();
        String dbLogicTable = getSharingListDto.getDbLogicTable();
        sql.append(SELECT);
        StringBuffer selectFieldSb = new StringBuffer();
        selectField.forEach(d -> {
            selectFieldSb.append(BaseConstant.PLACEHOLDER_BLANK_SPACE);
            selectFieldSb.append(d);
            selectFieldSb.append(BaseConstant.CHAR_COMMA);
        });
        if (selectFieldSb.toString().endsWith(BaseConstant.CHAR_COMMA)) {
            String selectFieldSbStr = selectFieldSb.substring(0, selectFieldSb.lastIndexOf(BaseConstant.CHAR_COMMA));
            sql.append(selectFieldSbStr);
        }
        sql.append(BaseConstant.PLACEHOLDER_BLANK_SPACE);
        sql.append(FROM + BaseConstant.PLACEHOLDER_BLANK_SPACE + dbLogicTable);
        sql.append(BaseConstant.PLACEHOLDER_BLANK_SPACE);
        sql.append(WHERE);
        sql.append(BaseConstant.PLACEHOLDER_BLANK_SPACE);

        //获得查询时间 start
        GetSelectDurationDto getSelectDurationDto = Convert.convert(GetSelectDurationDto.class, getSharingListDto);
        SelectDurationRo selectDurationRo = uniformityRedisService.getSelectDuration(getSelectDurationDto);

        LocalDateTime lastSyncDate = selectDurationRo.getLastSyncDate();
        LocalDateTime latelySyncDate = selectDurationRo.getLatelySyncDate();
        sql.append(AND);
        sql.append(BaseConstant.PLACEHOLDER_BLANK_SPACE);
        String dateField = getSharingListDto.getDateField();
        sql.append(dateField);
        sql.append(DATE_START);
        sql.append(SINGLE_QUOTATION_MARK + lastSyncDate + SINGLE_QUOTATION_MARK);
        sql.append(BaseConstant.PLACEHOLDER_BLANK_SPACE);
        sql.append(AND);
        sql.append(BaseConstant.PLACEHOLDER_BLANK_SPACE);
        sql.append(dateField);
        sql.append(DATE_END);
        sql.append(SINGLE_QUOTATION_MARK + latelySyncDate + SINGLE_QUOTATION_MARK);
        sql.append(BaseConstant.PLACEHOLDER_BLANK_SPACE);

        //获得查询时间  end
        String valid = getSharingListDto.getDeletedField();
        if (StringUtils.isNotEmpty(valid)) {
            sql.append(AND);
            sql.append(BaseConstant.PLACEHOLDER_BLANK_SPACE);
            sql.append(valid + BaseConstant.CHAR_EQUAL_SIGN + VALID_TRUE);
        }
        String where = getSharingListDto.getWhere();
        if (StringUtils.isNotEmpty(where)) {
            sql.append(BaseConstant.PLACEHOLDER_BLANK_SPACE);
            sql.append(AND);
            sql.append(BaseConstant.PLACEHOLDER_BLANK_SPACE);
            sql.append(where);
        }
        String orderBy = getSharingListDto.getOrderByField();
        if (StringUtils.isNotEmpty(orderBy)) {
            sql.append(BaseConstant.PLACEHOLDER_BLANK_SPACE);
            sql.append(ORDER_BY);
            sql.append(BaseConstant.PLACEHOLDER_BLANK_SPACE);
            sql.append(orderBy);
        }
        Integer pageNo = getSharingListDto.getPageNo();
        Integer pageSize = getSharingListDto.getPageSize();
        if (ObjectUtil.isNotNull(pageNo) && ObjectUtil.isNotNull(pageSize)) {
            if (pageNo > 0 && pageSize > 0) {
                sql.append(BaseConstant.PLACEHOLDER_BLANK_SPACE);
                Integer start = (pageNo - 1) * pageSize;
                Integer end = pageNo * pageSize - 1;
                sql.append(LIMIT);
                sql.append(BaseConstant.PLACEHOLDER_BLANK_SPACE);
                sql.append(start + BaseConstant.CHAR_COMMA + end);
            }
        }
        return sql.toString();
    }

    @Override
    public String structureShardingCountSql(GetSharingListDto getSharingListDto) {
        StringBuffer sql = new StringBuffer();
        String dbLogicTable = getSharingListDto.getDbLogicTable();
        sql.append(SELECT);
        String countField = getSharingListDto.getCountField();
        sql.append(BaseConstant.PLACEHOLDER_BLANK_SPACE);
        sql.append(COUNT + BaseConstant.BRACKETS_LEFT + countField + BaseConstant.BRACKETS_RIGHT);
        sql.append(BaseConstant.PLACEHOLDER_BLANK_SPACE);
        sql.append(FROM + BaseConstant.PLACEHOLDER_BLANK_SPACE + dbLogicTable);
        sql.append(BaseConstant.PLACEHOLDER_BLANK_SPACE);
        sql.append(WHERE);
        sql.append(BaseConstant.PLACEHOLDER_BLANK_SPACE);

        //获得查询时间 start
        GetSelectDurationDto getSelectDurationDto = Convert.convert(GetSelectDurationDto.class, getSharingListDto);
        SelectDurationRo selectDurationRo = uniformityRedisService.getSelectDuration(getSelectDurationDto);

        LocalDateTime lastSyncDate = selectDurationRo.getLastSyncDate();
        LocalDateTime latelySyncDate = selectDurationRo.getLatelySyncDate();
        sql.append(AND);
        sql.append(BaseConstant.PLACEHOLDER_BLANK_SPACE);
        String dateField = getSharingListDto.getDateField();
        sql.append(dateField);
        sql.append(DATE_START);
        sql.append(SINGLE_QUOTATION_MARK + lastSyncDate + SINGLE_QUOTATION_MARK);
        sql.append(BaseConstant.PLACEHOLDER_BLANK_SPACE);
        sql.append(AND);
        sql.append(BaseConstant.PLACEHOLDER_BLANK_SPACE);
        sql.append(dateField);
        sql.append(DATE_END);
        sql.append(SINGLE_QUOTATION_MARK + latelySyncDate + SINGLE_QUOTATION_MARK);
        sql.append(BaseConstant.PLACEHOLDER_BLANK_SPACE);

        //获得查询时间  end


        String valid = getSharingListDto.getDeletedField();
        if (StringUtils.isNotEmpty(valid)) {
            sql.append(AND);
            sql.append(BaseConstant.PLACEHOLDER_BLANK_SPACE);
            sql.append(valid + BaseConstant.CHAR_EQUAL_SIGN + VALID_TRUE);
        }
        String where = getSharingListDto.getWhere();
        if (StringUtils.isNotEmpty(where)) {
            sql.append(BaseConstant.PLACEHOLDER_BLANK_SPACE);
            sql.append(AND);
            sql.append(BaseConstant.PLACEHOLDER_BLANK_SPACE);
            sql.append(where);
        }
        String orderBy = getSharingListDto.getOrderByField();
        if (StringUtils.isNotEmpty(orderBy)) {
            sql.append(BaseConstant.PLACEHOLDER_BLANK_SPACE);
            sql.append(ORDER_BY);
            sql.append(BaseConstant.PLACEHOLDER_BLANK_SPACE);
            sql.append(orderBy);
        }
        return sql.toString();
    }

    @Override
    public String structureBitmapShardingSelectSql(GetBitmapSharingListDto getBitmapSharingListDto) {
        StringBuffer sql = new StringBuffer();
        List<String> selectField = getBitmapSharingListDto.getSelectField();
        String dbLogicTable = getBitmapSharingListDto.getDbLogicTable();
        sql.append(SELECT);
        StringBuffer selectFieldSb = new StringBuffer();
        selectField.forEach(d -> {
            selectFieldSb.append(BaseConstant.PLACEHOLDER_BLANK_SPACE);
            selectFieldSb.append(d);
            selectFieldSb.append(BaseConstant.CHAR_COMMA);
        });
        if (selectFieldSb.toString().endsWith(BaseConstant.CHAR_COMMA)) {
            String selectFieldSbStr = selectFieldSb.substring(0, selectFieldSb.lastIndexOf(BaseConstant.CHAR_COMMA));
            sql.append(selectFieldSbStr);
        }
        sql.append(BaseConstant.PLACEHOLDER_BLANK_SPACE);
        sql.append(FROM + BaseConstant.PLACEHOLDER_BLANK_SPACE + dbLogicTable);
        sql.append(BaseConstant.PLACEHOLDER_BLANK_SPACE);
        sql.append(WHERE);
        sql.append(BaseConstant.PLACEHOLDER_BLANK_SPACE);
        List<Long> selectedId = getBitmapSharingListDto.getSelectedId();

        if (ObjectUtil.isNotEmpty(selectedId)) {
            sql.append(AND);
            sql.append(BaseConstant.PLACEHOLDER_BLANK_SPACE);
            sql.append(PRIMARY_KEY_FIELD);
            sql.append(BaseConstant.PLACEHOLDER_BLANK_SPACE);
            sql.append(IN);
            sql.append(BaseConstant.PLACEHOLDER_BLANK_SPACE);
            sql.append(BaseConstant.BRACKETS_LEFT);
            String symbol = StringUtils.join(selectedId.toArray(), BaseConstant.CHAR_COMMA);
            sql.append(symbol);
            sql.append(BaseConstant.BRACKETS_RIGHT);
        }
        return sql.toString();
    }

    @Override
    public List<Map> getBitmapShardingList(GetBitmapSharingListDto getBitmapSharingListDto) {
        try (HintManager hintManager = HintManager.getInstance()) {
            BuildHintManagerDto buildHintManagerDto = Convert.convert(BuildHintManagerDto.class, getBitmapSharingListDto);
            buildHintManager(hintManager, buildHintManagerDto);
            String sql = this.structureBitmapShardingSelectSql(getBitmapSharingListDto);
            List list = commonDao.executeQuery(sql);
            return list;
        }
    }

    /**
     * 构造 hintManager
     *
     * @param hintManager:
     * @param buildHintManagerDto:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/12/31 15:27
     */
    private void buildHintManager(HintManager hintManager, BuildHintManagerDto buildHintManagerDto) {
        List<String> selectedDb = buildHintManagerDto.getSelectedDb();
        String dbLogicTable = buildHintManagerDto.getDbLogicTable();
        if (StringUtils.isNotEmpty(dbLogicTable) && ObjectUtil.isNotEmpty(selectedDb)) {
            selectedDb.forEach(e -> {
                hintManager.addDatabaseShardingValue(dbLogicTable, e);
            });
        }
        String tableLogicTable = buildHintManagerDto.getTableLogicTable();
        List<String> selectedTable = buildHintManagerDto.getSelectedTable();
        if (StringUtils.isNotEmpty(tableLogicTable) && ObjectUtil.isNotEmpty(selectedTable)) {
            selectedTable.forEach(e -> {
                hintManager.addTableShardingValue(tableLogicTable, e);
            });
        }
    }


}

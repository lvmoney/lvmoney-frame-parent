package com.lvmoney.frame.ai.seetaface.base.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lvmoney.frame.ai.seetaface.base.entity.ComparisonResource;
import com.lvmoney.frame.ai.seetaface.common.constant.SeetafaceCommonConstant;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 人脸识别原图 Mapper 接口
 * </p>
 *
 * @author lvmoney
 * @since 2022-02-11
 */
public interface ComparisonResourceDao extends BaseMapper<ComparisonResource> {
    /**
     * 根据身份证号获得人的真实图片信息
     *
     * @param idCard:
     * @param idCardPrivacy:
     * @throws
     * @return: ComparisonResource
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/11 17:42
     */
    @Select({"<script>",
            "SELECT a.file_id,a.file_name,a.file_type FROM comparison_resource AS a WHERE a.id_card =#{idCard} AND a.id_card_privacy=#{idCardPrivacy} AND a.valid=",
            SeetafaceCommonConstant.VALID_NOT_DELETE,
            "</script>"})
    ComparisonResource getByIdCard(@Param("idCard") String idCard, @Param("idCardPrivacy") String idCardPrivacy);
}

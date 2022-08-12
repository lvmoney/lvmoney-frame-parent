package com.lvmoney.frame.autocode.catalogue.util;/**
 * 描述:
 * 包名:com.lvmoney.frame.autocode.catalogue.service.impl
 * 版本信息: 版本1.0
 * 日期:2020/6/17
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.autocode.common.enums.CatalogueEnum;
import com.lvmoney.frame.autocode.catalogue.vo.CatalogueVo;
import com.lvmoney.frame.core.util.FileUtil;
import com.lvmoney.frame.core.util.ModuleUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

import static com.lvmoney.frame.autocode.common.enums.AutocodeConstant.*;
import static com.lvmoney.frame.base.core.constant.BaseConstant.*;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/6/17 15:05
 */
public class CatalogueUtil {
    /**
     * 创建目录结构
     *
     * @param catalogueVo:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/6/18 10:56
     */
    public static boolean create(CatalogueVo catalogueVo) {
        String path = catalogueVo.getPath();
        String base = DEFAULT_JAVA_BASE + BACKSLASH + DEFAULT_PACKAGE_COM + BACKSLASH + catalogueVo.getOrg() + BACKSLASH + catalogueVo.getArtifact() + BACKSLASH + catalogueVo.getSys() + BACKSLASH;
        base = base.replaceAll(REPLACE_DECIMAL_POINT, BACKSLASH);
        List<String> catalogues = catalogueVo.getCatalogues();
        boolean cover = catalogueVo.isCover();
        if (StringUtils.isEmpty(path)) {
            path = ModuleUtil.getModuleRootPath();
        }
        FileUtil.createFile(path, cover);
        String basePkg = path + BACKSLASH + base;
        FileUtil.createFile(basePkg, cover);
        if (ObjectUtils.isEmpty(catalogues)) {
            CatalogueEnum.getValues().parallelStream().forEach(e -> {
                String cataloguePath = basePkg + BACKSLASH + e;
                cataloguePath = cataloguePath.replaceAll(REPLACE_DECIMAL_POINT, BACKSLASH);
                FileUtil.createFile(cataloguePath, cover);
            });
        } else {
            catalogues.parallelStream().forEach(e -> {
                String cataloguePath = basePkg + BACKSLASH + e;
                cataloguePath = cataloguePath.replaceAll(REPLACE_DECIMAL_POINT, BACKSLASH);
                FileUtil.createFile(cataloguePath, cover);
            });
        }
        return true;
    }

}

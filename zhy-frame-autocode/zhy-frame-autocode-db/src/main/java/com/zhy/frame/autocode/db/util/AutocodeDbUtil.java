package com.zhy.frame.autocode.db.util;/**
 * 描述:
 * 包名:com.zhy.frame.autocode.db.service.impl
 * 版本信息: 版本1.0
 * 日期:2020/6/18
 * Copyright XXXXXX科技有限公司
 */


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.zhy.frame.autocode.common.enums.AutocodeConstant;
import com.zhy.frame.autocode.common.enums.CatalogueEnum;
import com.zhy.frame.autocode.db.enums.DbEnvEnum;
import com.zhy.frame.autocode.db.vo.DbVo;
import com.zhy.frame.base.core.constant.BaseConstant;
import com.zhy.frame.core.util.FileUtil;
import com.zhy.frame.core.util.ModuleUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.List;

import static com.zhy.frame.autocode.common.enums.AutocodeConstant.*;
import static com.zhy.frame.autocode.db.enums.DbPkgEnum.EXTERNAL_CONTROLLER;
import static com.zhy.frame.autocode.db.enums.DbPkgEnum.INTERNAL_CONTROLLER;
import static com.zhy.frame.base.core.constant.BaseConstant.*;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/6/18 9:32
 */
public class AutocodeDbUtil {
    /**
     * 创建数据库相关的entit，dao。service
     *
     * @param dbVo:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/6/18 14:19
     */
    public static boolean create(DbVo dbVo) {
        boolean bool = generator(dbVo);
        if (bool) {
            return deleteXml(dbVo);
        }
        return false;
    }

    /**
     * 删除生成的xml
     *
     * @param dbVo:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/6/18 14:21
     */
    public static boolean deleteXml(DbVo dbVo) {
        return delPkg(dbVo, "mapper");
    }

    /**
     * 创建数据库相关的entit，dao。service,xml
     *
     * @param dbVo:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/6/18 14:22
     */
    public static boolean generator(DbVo dbVo) {
        if (dbVo.getEnv().equals(DbEnvEnum.EXTERNAL)) {
            return createExternal(dbVo);
        } else if (dbVo.getEnv().equals(DbEnvEnum.INTERNAL)) {
            return createInternal(dbVo);
        } else {
            boolean bool = createExternal(dbVo);
            if (bool) {
                return createInternal(dbVo);
            } else {
                return false;
            }
        }
    }

    /**
     * 创建内部使用的数据库相关的，内部op系统用
     *
     * @param dbVo:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/6/18 14:23
     */
    private static boolean createInternal(DbVo dbVo) {
        AutoGenerator mpg = new AutoGenerator();
        GlobalConfig gc = getGlobalConfig(dbVo);
        mpg.setGlobalConfig(gc);
        DataSourceConfig dsc = getDataSourceConfig(dbVo);
        mpg.setDataSource(dsc);
        StrategyConfig strategy = getStrategyConfig(dbVo);
        mpg.setStrategy(strategy);
        // 包配置
        PackageConfig pc = new PackageConfig();
        String base = DEFAULT_PACKAGE_COM + DECIMAL_POINT + dbVo.getOrg() + DECIMAL_POINT + dbVo.getArtifact() + DECIMAL_POINT + dbVo.getSys();
        pc.setParent(base);
        pc.setController(INTERNAL_CONTROLLER.getValue());
        pc.setService(CatalogueEnum.INTERNAL_SERVICE.getValue());
        pc.setServiceImpl(CatalogueEnum.INTERNAL_IMPL.getValue());
        pc.setMapper(CatalogueEnum.INTERNAL_DAO.getValue());
        pc.setEntity(CatalogueEnum.ENTITY.getValue());
        pc.setXml("mapper");
        mpg.setPackageInfo(pc);
        // 执行生成
        mpg.execute();
        delPkg(dbVo, INTERNAL_CONTROLLER.getValue());
        return true;
    }

    /**
     * 创建外部使用的数据库相关的，中台服务被用已外部调用用
     *
     * @param dbVo:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/6/18 14:23
     */
    private static boolean createExternal(DbVo dbVo) {
        AutoGenerator mpg = new AutoGenerator();
        GlobalConfig gc = getGlobalConfig(dbVo);
        mpg.setGlobalConfig(gc);
        DataSourceConfig dsc = getDataSourceConfig(dbVo);
        mpg.setDataSource(dsc);
        StrategyConfig strategy = getStrategyConfig(dbVo);
        mpg.setStrategy(strategy);
        // 包配置
        PackageConfig pc = new PackageConfig();
        String base = DEFAULT_PACKAGE_COM + DECIMAL_POINT + dbVo.getOrg() + DECIMAL_POINT + dbVo.getArtifact() + DECIMAL_POINT + dbVo.getSys();
        pc.setParent(base);
        pc.setController(EXTERNAL_CONTROLLER.getValue());
        pc.setService(CatalogueEnum.EXTERNAL_SERVICE.getValue());
        pc.setServiceImpl(CatalogueEnum.EXTERNAL_IMPL.getValue());
        pc.setMapper(CatalogueEnum.EXTERNAL_DAO.getValue());
        pc.setEntity(CatalogueEnum.ENTITY.getValue());
        pc.setXml("mapper");
        mpg.setPackageInfo(pc);
        // 执行生成
        mpg.execute();

        delPkg(dbVo, EXTERNAL_CONTROLLER.getValue());
        return true;
    }

    /**
     * GlobalConfig
     *
     * @param dbVo:
     * @throws
     * @return: com.baomidou.mybatisplus.generator.config.GlobalConfig
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/6/18 14:24
     */
    private static GlobalConfig getGlobalConfig(DbVo dbVo) {
        GlobalConfig gc = new GlobalConfig();
        String path = dbVo.getPath();
        if (StringUtils.isEmpty(path)) {
            path = ModuleUtil.getModuleRootPath();
        }
        //输出文件路径
        gc.setOutputDir(path + BaseConstant.BACKSLASH + AutocodeConstant.DEFAULT_JAVA_BASE);
        gc.setFileOverride(true);
        // 不需要ActiveRecord特性的请改为false
        gc.setActiveRecord(false);
        // XML 二级缓存
        gc.setEnableCache(false);
        // XML ResultMap
        gc.setBaseResultMap(true);
        // XML columnList
        gc.setBaseColumnList(false);
        gc.setAuthor(dbVo.getAuthor());
        //主键策略
        gc.setIdType(IdType.ASSIGN_ID);
        // 每一次生成需要覆盖
        gc.setFileOverride(dbVo.isCover());
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setControllerName("%sController");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setMapperName("%sDao");
        gc.setXmlName("%sMapper");
        return gc;
    }

    /**
     * DataSourceConfig
     *
     * @param dbVo:
     * @throws
     * @return: com.baomidou.mybatisplus.generator.config.DataSourceConfig
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/6/18 14:24
     */
    private static DataSourceConfig getDataSourceConfig(DbVo dbVo) {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername(dbVo.getDbUsername());
        dsc.setPassword(dbVo.getDbPassword());
        dsc.setUrl("jdbc:mysql://" + dbVo.getDbIp() + ":" + dbVo.getDbPort() + "/" + dbVo.getDbName() + "?useUnicodle=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC");
        return dsc;
    }

    /**
     * StrategyConfig
     *
     * @param dbVo:
     * @throws
     * @return: com.baomidou.mybatisplus.generator.config.StrategyConfig
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/6/18 14:24
     */
    private static StrategyConfig getStrategyConfig(DbVo dbVo) {
        StrategyConfig strategy = new StrategyConfig();
        // 表名生成策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 需要生成的表
        List<String> tables = dbVo.getDbTable();
        if (ObjectUtils.isNotEmpty(tables)) {
            String[] arr;
            arr = (String[]) tables.toArray();
            strategy.setInclude(arr);
        }
        strategy.setSuperServiceClass(null);
        strategy.setSuperServiceImplClass(null);
        strategy.setSuperMapperClass(null);
        return strategy;
    }

    /**
     * 清空包
     *
     * @param dbVo:
     * @param controller:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/6/18 14:25
     */
    private static boolean delPkg(DbVo dbVo, String controller) {
        String path = dbVo.getPath();
        if (StringUtils.isEmpty(path)) {
            path = ModuleUtil.getModuleRootPath();
        }
        String basePath = DEFAULT_JAVA_BASE + BACKSLASH + DEFAULT_PACKAGE_COM + BACKSLASH + dbVo.getOrg() + BACKSLASH + dbVo.getArtifact() + BACKSLASH + dbVo.getSys() + BACKSLASH + controller;
        basePath = path + basePath.replaceAll(REPLACE_DECIMAL_POINT, BACKSLASH);
        FileUtil.delFile(new File(basePath));
        return true;
    }
}

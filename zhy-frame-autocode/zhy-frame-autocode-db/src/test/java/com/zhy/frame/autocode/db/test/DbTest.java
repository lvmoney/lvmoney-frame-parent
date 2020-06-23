package com.zhy.frame.autocode.db.test;/**
 * 描述:
 * 包名:com.zhy.frame.autocode.test
 * 版本信息: 版本1.0
 * 日期:2020/6/17
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.autocode.db.enums.DbEnvEnum;
import com.zhy.frame.autocode.db.util.AutocodeDbUtil;
import com.zhy.frame.autocode.db.vo.DbVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/6/17 15:25
 */
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class DbTest {

    @Test
    public void catalogue() throws Throwable {
        DbVo dbVo = new DbVo();
        dbVo.setDbIp("10.20.128.16");
        dbVo.setDbName("data_meituan");
        dbVo.setDbPassword("root");
        dbVo.setDbPort(3306);
        dbVo.setDbUsername("root");
        dbVo.setEnv(DbEnvEnum.ALL);
        dbVo.setSys("auth");
        dbVo.setOrg("zhy");
        dbVo.setArtifact("frame");
        dbVo.setCover(true);
        dbVo.setAuthor("lvmoney");
        AutocodeDbUtil.create(dbVo);
    }
}

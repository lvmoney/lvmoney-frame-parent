package com.lvmoney.frame.autocode.db.test;/**
 * 描述:
 * 包名:com.lvmoney.frame.autocode.test
 * 版本信息: 版本1.0
 * 日期:2020/6/17
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.autocode.db.enums.DbEnvEnum;
import com.lvmoney.frame.autocode.db.util.AutocodeDbUtil;
import com.lvmoney.frame.autocode.db.vo.DbVo;
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
        dbVo.setDbIp("118.122.93.102");
        dbVo.setDbName("agribusiness");
        dbVo.setDbPassword("woaiwojia");
        dbVo.setDbPort(3308);
        dbVo.setDbUsername("root");
        dbVo.setEnv(DbEnvEnum.ALL);
        dbVo.setSys("user");
        dbVo.setOrg("scmmzx");
        dbVo.setArtifact("agribusiness");
        dbVo.setCover(true);
        dbVo.setAuthor("lvmoney");
        AutocodeDbUtil.create(dbVo);
    }
}

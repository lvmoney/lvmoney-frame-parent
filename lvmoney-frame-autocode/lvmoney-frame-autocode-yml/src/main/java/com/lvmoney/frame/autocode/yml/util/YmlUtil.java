package com.lvmoney.frame.autocode.yml.util;/**
 * 描述:
 * 包名:com.lvmoney.frame.autocode.yml.util
 * 版本信息: 版本1.0
 * 日期:2020/6/18
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.autocode.yml.constant.YmlConstant;
import com.lvmoney.frame.autocode.yml.enums.YmlEnum;
import com.lvmoney.frame.autocode.yml.jyml.*;
import com.lvmoney.frame.autocode.yml.vo.*;
import com.lvmoney.frame.core.util.ModuleUtil;
import com.lvmoney.frame.core.util.YamlUtil;
import com.lvmoney.frame.core.vo.YamlBuildVo;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static com.lvmoney.frame.autocode.common.enums.AutocodeConstant.*;
import static com.lvmoney.frame.autocode.yml.constant.YmlConstant.*;
import static com.lvmoney.frame.base.core.constant.BaseConstant.DASH_LINE;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/6/18 14:30
 */
public class YmlUtil {
    /**
     * 创建基础的yml
     *
     * @param ymlVo:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/6/18 17:19
     */
    public static void create(YmlVo ymlVo) {
        createApplicationYml(ymlVo);
        createOtherApplicationYml(ymlVo);
    }

    /**
     * 创建application.yml
     *
     * @param ymlVo:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/6/18 17:19
     */
    public static void createApplicationYml(YmlVo ymlVo) {
        YamlBuildVo yamlBuildVo = new YamlBuildVo();
        yamlBuildVo.setCover(ymlVo.isCover());
        String path = ymlVo.getPath();
        if (StringUtils.isEmpty(path)) {
            path = ModuleUtil.getModuleRootPath();
        }
        String activeProfile = ymlVo.getActiveProfile();
        if (StringUtils.isEmpty(activeProfile)) {
            activeProfile = DEFAULT_ACTIVE_PROFILE;
        }
        final String finalActiveProfile = activeProfile;
        List<Object> list = new ArrayList();
        Profiles profiles = new Profiles(finalActiveProfile);
        ApplicationYml applicationYml = new ApplicationYml();
        Spring spring = new Spring();
        spring.setProfiles(profiles);
        applicationYml.setSpring(spring);
        list.add(applicationYml);
        String basePath = path + DEFAULT_RESOURCES_BASE;
        yamlBuildVo.setPath(basePath);
        yamlBuildVo.setData(list);
        yamlBuildVo.setName(YML_DEFAULT_PREFIX + YmlConstant.YML_DEFAULT_SUFFIX);
        YamlUtil.buildYaml(yamlBuildVo);
    }

    /**
     * 创建application-dev.yml等
     *
     * @param ymlVo:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/6/18 17:19
     */
    public static void createOtherApplicationYml(YmlVo ymlVo) {
        List<String> env = ymlVo.getEnv();
        if (ObjectUtils.isEmpty(env)) {
            env = YmlEnum.getValues();
        }
        YamlBuildVo yamlBuildVo = new YamlBuildVo();
        yamlBuildVo.setCover(ymlVo.isCover());
        String path = ymlVo.getPath();
        if (StringUtils.isEmpty(path)) {
            path = ModuleUtil.getModuleRootPath();
        }
        String basePath = path + DEFAULT_RESOURCES_BASE;
        yamlBuildVo.setPath(basePath);
        final String sysName = ymlVo.getSysName();
        final Integer sysPort = ymlVo.getSysPort();
        List<Object> list = new ArrayList();
        OtherApplicationYml otherApplicationYml = new OtherApplicationYml();
        Spring spring = new Spring();
        Application application = new Application();
        Server server = new Server();
        server.setPort(sysPort);
        spring.setApplication(application);
        application.setName(sysName);
        otherApplicationYml.setServer(server);
        otherApplicationYml.setSpring(spring);
        Rpc rpc = new Rpc();
        Gateway gateway = new Gateway();
        gateway.setServer(ymlVo.getGateway());
        rpc.setGateway(gateway);
        otherApplicationYml.setRpc(rpc);
        list.add(otherApplicationYml);
        yamlBuildVo.setData(list);
        env.parallelStream().forEach(e -> {
            yamlBuildVo.setName(YML_DEFAULT_PREFIX + DASH_LINE + e + YmlConstant.YML_DEFAULT_SUFFIX);
            YamlUtil.buildYaml(yamlBuildVo);
        });
    }
}

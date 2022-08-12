package com.lvmoney.frame.base.validator.aspect;/**
 * 描述:
 * 包名:com.lvmoney.frame.base.validator.aspect
 * 版本信息: 版本1.0
 * 日期:2021/12/14
 * Copyright XXXXXX科技有限公司
 */


import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.lvmoney.frame.base.core.constant.BaseConstant;
import com.lvmoney.frame.base.core.exception.BusinessException;
import com.lvmoney.frame.base.core.exception.CommonException;
import com.lvmoney.frame.base.core.exception.JsonValidatorException;
import com.lvmoney.frame.base.validator.annotations.JsonValid;
import com.lvmoney.frame.base.validator.vo.JsonValidatorVo;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.LogLevel;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.lvmoney.frame.base.core.exception.CommonException.Proxy.READ_JSON_SCHEMA_ERROR;


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/12/14 12:59
 */
@Component
@Aspect
public class JsonValidAspect {
    private static final String DEFAULT_SCHEMA_PATH = "schema";

    private final Logger LOGGER = LoggerFactory.getLogger(JsonValidAspect.class);
    /**
     * 错误信息
     */
    private static final String ERROR_MESSAGE_FILED = "%s.errorMessage";
    /**
     * 控制信息
     */
    private static final String EMPTY_MESSAGE_FILED = "%s.emptyMessage";

    /**
     * pointer field
     */
    private static final String POINTER = "pointer";
    /**
     * instance field
     */
    private static final String INSTANCE = "instance";

    @Pointcut("@annotation(com.lvmoney.frame.base.validator.annotations.JsonValid)")
    private void pointcut() {
    }

    @Before("pointcut() && @annotation(jsonValid)")
    public void before(JoinPoint joinPoint, JsonValid jsonValid) {
        Object[] args = joinPoint.getArgs();
        if (args != null) {
            String jsonString = JSONObject.toJSONString(args[0]);
            Resource resource = new ClassPathResource(DEFAULT_SCHEMA_PATH + BaseConstant.FILE_SEPARATOR + jsonValid.schemaName());
            String validParam = "";
            try {
                validParam = IOUtils.toString(resource.getInputStream(), BaseConstant.CHARACTER_ENCODE_UTF8_LOWER);
            } catch (IOException e) {
                throw new BusinessException(READ_JSON_SCHEMA_ERROR);
            }
            validJson(jsonString, validParam);
        }
    }

    /**
     * jsonSchema 校验
     *
     * @param jsonString: 被校验对象
     * @param validParam: 校验规则
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/12/14 15:03
     */
    public void validJson(String jsonString, String validParam) {

        try {
            JSONObject object = JSONObject.parseObject(jsonString);
            JsonNode schema = JsonLoader.fromString(validParam);
            JsonNode data = JsonLoader.fromString(object.toJSONString());
            final JsonSchema jsonSchema = JsonSchemaFactory.byDefault().getJsonSchema(schema);
            ProcessingReport report = jsonSchema.validate(data);
            List<JsonValidatorVo> jsonValidatorVoList = new ArrayList();

            report.forEach(pm -> {
                if (LogLevel.ERROR.equals(pm.getLogLevel())) {
                    JsonNode jsonNode = pm.asJson();
                    String srcSchemaPath = getByPath(jsonNode.toString(), "schema.pointer");
                    String schemaPath = getFiledByPath(srcSchemaPath);

                    String express = String.format(ERROR_MESSAGE_FILED, schemaPath);
                    String errorMessage = getByPath(validParam, express);

                    JsonNode key = Optional.ofNullable(jsonNode)
                            .map(o -> o.get(INSTANCE))
                            .map(o -> o.get(POINTER))
                            .orElse(null);
                    JsonValidatorVo jsonValidatorVo = new JsonValidatorVo();
                    String field = key == null ? BaseConstant.EMPTY : getFiledByPath(key.asText());
                    jsonValidatorVo.setField(field);
                    jsonValidatorVo.setDefaultMessage(errorMessage);
                    jsonValidatorVoList.add(jsonValidatorVo);
                }
            });
            if (ObjectUtils.isNotEmpty(jsonValidatorVoList)) {
                throw new JsonValidatorException(CommonException.Proxy.PARAM_ERROR, jsonValidatorVoList);
            }
        } catch (IOException e) {
            throw new BusinessException(CommonException.Proxy.JSON_NODE_BY_JSON_ERROR);
        } catch (ProcessingException e) {
            throw new BusinessException(CommonException.Proxy.JSON_SCHEMA_ERROR);
        }
    }

    /**
     * 通过hutool工具获得指定key的值
     *
     * @param json:
     * @param path:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/12/14 19:45
     */
    private String getByPath(String json, String path) {
        JSON data = JSONUtil.parse(json);
        return String.valueOf(JSONUtil.getByPath(data, path));
    }

    /**
     * 根据path获得filed，用于hutool工具获得指定key
     *
     * @param nodePath:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/12/14 19:45
     */
    private String getFiledByPath(String nodePath) {
        return nodePath.replaceFirst(BaseConstant.BACKSLASH, BaseConstant.EMPTY).replaceAll(BaseConstant.BACKSLASH, BaseConstant.DECIMAL_POINT);
    }
}

package com.lvmoney.frame.blockchain.webase.weidentity.api.constant;/**
 * 描述:
 * 包名:com.lvmoney.frame.blockchain.webase.weidentity.api.constant
 * 版本信息: 版本1.0
 * 日期:2021/6/30
 * Copyright XXXXXX科技有限公司
 */


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/6/30 19:10
 */
public class WeidConstant {
    /**
     * 创建WeIdentity DID（有参创建方式）-第一步
     */
    public static final String URL_WEID_API_ENCODE = "/api/encode";

    /**
     * 创建WeIdentity DID（有参创建方式）-第二步
     */
    public static final String URL_WEID_API_TRANSACT = "/api/transact";

    /**
     * 创建weid 调用合约的方法名
     */
    public static final String FUNC_CREATE_WE_ID = "createWeId";

    /**
     * 获取WeIdentity DID Document
     */
    public static final String FUNC_GET_WE_ID_DOCUMENT = "getWeIdDocument";

    /**
     * 创建WeIdentity DID（无参创建方式）
     */
    public static final String URL_WEID_API_INVOKE = "/api/invoke";

    /**
     * 创建AuthorityIssuer
     */
    public static final String FUNC_REGISTER_AUTHORITY_ISSUER = "registerAuthorityIssuer";

    /**
     * 查询AuthorityIssuer
     */
    public static final String FUNC_QUERY_AUTHORITY_ISSUER = "queryAuthorityIssuer";

    /**
     * 创建CPT
     */
    public static final String FUNC_REGISTER_CPT = "registerCpt";

    /**
     * 查询CPT
     */
    public static final String FUNC_QUERY_CPT = "queryCpt";

    /**
     * 创建Credential
     */
    public static final String FUNC_CREATE_CREDENTIAL = "createCredential";

    /**
     * 验证Credential
     */
    public static final String FUNC_VERIFY_CREDENTIAL = "verifyCredential";

    /**
     * 创建CredentialPojo
     */
    public static final String FUNC_CREATE_CREDENTIAL_POJO = "createCredentialPojo";

    /**
     * 验证CredentialPojo
     */
    public static final String FUNC_VERIFY_CREDENTIAL_POJO = "verifyCredentialPojo";


}

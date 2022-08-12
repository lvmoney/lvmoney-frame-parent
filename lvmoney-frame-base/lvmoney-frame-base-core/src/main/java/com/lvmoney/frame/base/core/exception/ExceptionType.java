package com.lvmoney.frame.base.core.exception;

/**
 * @describe：业务异常类型顶层接口,推荐使用枚举来进行实现
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年12月29日 上午11:18:50
 */
public interface ExceptionType {
    /**
     * 异常代码,为方便前端捕捉,推荐分段处理
     *
     * @throws
     * @return: int
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 20:31
     */
    int getCode();

    /**
     * 异常描述信息
     *
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 20:32
     */
    String getDescription();

}

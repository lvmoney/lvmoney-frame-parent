package com.lvmoney.frame.expression.eval;/**
 * 描述:
 * 包名:com.lvmoney.frame.expression.eval
 * 版本信息: 版本1.0
 * 日期:2019/11/19
 * Copyright 四川******科技有限公司
 */

import com.lvmoney.frame.base.core.exception.BusinessException;
import com.lvmoney.frame.expression.exception.ExpressionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2019/11/19 16:38
 */
public class FunctionCall extends Expression {
    private static final Logger LOGGER = LoggerFactory.getLogger(FunctionCall.class);


    private static final long serialVersionUID = 5758404497296893915L;
    /**
     * 参数
     */
    private List<Expression> parameters = new ArrayList<Expression>();
    /**
     * 方法
     */
    private Function function;

    @Override
    public double evaluate() {
        try {
            return function.eval(parameters);
        } catch (Exception e) {
            if (function.errorThrowException()) {
                LOGGER.error("{} call function ,parameters={},error:{}", this.function, this.parameters, e);
                throw new BusinessException(ExpressionException.Proxy.FUNCTION_EVAL_ERROR);
            }
            return Double.NaN;
        }

    }

    @Override
    public Expression simplify() {
        if (!function.isNaturalFunction()) {
            return this;
        }
        for (Expression expr : parameters) {
            if (!expr.isConstant()) {
                return this;
            }
        }
        return new Constant(evaluate());
    }

    /**
     * Sets the function to evaluate.
     *
     * @param function the function to evaluate
     */
    public void setFunction(Function function) {
        this.function = function;
    }

    /**
     * Adds an expression as parameter.
     *
     * @param expression the parameter to add
     */
    public void addParameter(Expression expression) {
        parameters.add(expression);
    }

    /**
     * Returns all parameters added so far.
     *
     * @return a list of parameters added to this call
     */
    public List<Expression> getParameters() {
        return parameters;
    }

}

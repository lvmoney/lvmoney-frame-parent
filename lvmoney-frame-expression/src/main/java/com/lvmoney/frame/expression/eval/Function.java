package com.lvmoney.frame.expression.eval;/**
 * 描述:
 * 包名:com.lvmoney.frame.expression.eval
 * 版本信息: 版本1.0
 * 日期:2019/11/19
 * Copyright 四川******科技有限公司
 */

import java.util.List;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2019/11/19 16:38
 */
public interface Function {

    /**
     * Returns the number of expected arguments.
     * <p>
     * If the function is called with a different number of arguments, an error will be created
     * <p>
     * In order to support functions with a variable number of arguments, a negative number can be returned.
     * This will essentially disable the check.
     *
     * @return the number of arguments expected by this function or a negative number to indicate that this
     * function accepts a variable number of arguments
     */
    int getNumberOfArguments();

    /**
     * Executes the function with the given arguments.
     * <p>
     * The arguments need to be evaluated first. This is not done externally to permit functions to perform lazy
     * evaluations.
     *
     * @param args the arguments for this function. The length of the given list will exactly match
     *             <tt>getNumberOfArguments</tt>
     * @return the result of the function evaluated with the given arguments
     */
    double eval(List<Expression> args);

    /**
     * A natural function returns the same output for the same input.
     * <p>
     * All classical mathematical functions are "natural". A function which reads user input is not natural, as
     * the function might return different results depending on the users input
     *
     * @return <tt>true</tt> if the function returns the same output for the same input, <tt>false</tt> otherwise
     */
    boolean isNaturalFunction();

    /**
     * 默认错误返回false
     *
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/6/13 15:49
     */
    default boolean errorThrowException() {
        return false;
    }
}

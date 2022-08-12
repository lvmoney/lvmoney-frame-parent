package com.lvmoney.frame.expression.common;
/**
 * 描述:
 * 包名:com.lvmoney.frame.expression.common
 * 版本信息: 版本1.0
 * 日期:2019/11/19
 * Copyright 四川******科技有限公司
 */


import com.lvmoney.frame.expression.eval.Expression;
import com.lvmoney.frame.expression.eval.Function;
import com.lvmoney.frame.expression.eval.IValue;

import java.util.List;

/**
 * @describe：自定义函数多参数父类
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2019/11/19 16:38
 */
public abstract class MultiFunction implements Function {

	@Override
	public abstract double eval(List<Expression> arg0);

	@Override
	public int getNumberOfArguments() {
		return -1;
	}

	@Override
	public boolean isNaturalFunction() {
		return true;
	}

	protected String getString(Expression e) {
		if (e instanceof IValue) {
			return ((IValue) e).value();
		}
		return String.valueOf(e.evaluate());
	}

	protected double getDouble(Expression e) {
		return e.evaluate();
	}

	protected int getInt(Expression e) {
		return (int) this.getDouble(e);
	}

	@Override
	public boolean errorThrowException() {
		return false;
	}

}

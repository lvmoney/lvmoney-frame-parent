package com.lvmoney.frame.expression.common;/**
 * 描述:
 * 包名:com.lvmoney.frame.expression.common
 * 版本信息: 版本1.0
 * 日期:2019/11/19
 * Copyright 四川******科技有限公司
 */


import com.lvmoney.frame.base.core.exception.BusinessException;
import com.lvmoney.frame.expression.eval.Expression;
import com.lvmoney.frame.expression.eval.Parser;
import com.lvmoney.frame.expression.eval.Scope;
import com.lvmoney.frame.expression.exception.ExpressionException;
import com.lvmoney.frame.expression.tokenizer.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2019/11/19 16:38
 */
public class MathExpress {

    private static final Logger LOGGER = LoggerFactory.getLogger(MathExpress.class);

    private Scope scope = new Scope();
    /**
     * 随着解析过程，这里存储最新的替换过后的表达式
     */
    private String express;

    private Expression expr;
    /**
     * 变量
     */
    private Set<String> vars;
    /**
     * 没有匹配的变量
     */
    private Set<String> notMatchVars = new HashSet<String>();
    /**
     * 历史数据值引用
     */
    public Map<String, VarIndex> indexVarMap = new HashMap<String, VarIndex>();

    public Set<String> getVars() {
        return this.vars;
    }

    public static boolean isSuccess(String express) {

        try {
            Parser.parse(express, new Scope());
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public MathExpress(String express) {
        try {
            this.express = express;
            expr = Parser.parse(express, scope);
            vars = scope.getLocalNames();
            this.dealIndex();
            removeDuplicate();
        } catch (Exception e) {
            LOGGER.error("{} parse express error:{}", express, e);
            throw new BusinessException(ExpressionException.Proxy.PARSE_EXPRESS_ERROR);
        }
    }

    /**
     * 将历史数据引用替换为具体的值
     *
     * @param param
     * @return
     */
    public MathExpress reBuild(Map<String, String> param) {
        String express = this.express;
        for (Entry<String, String> e : param.entrySet()) {
            express = express.replaceAll(e.getKey(), e.getValue());
        }
        return new MathExpress(express);
    }

    public void calc(Map<String, Double> varVals) {
        this.notMatchVars.clear();
        for (String var : vars) {
            Double val = varVals.get(var);
            if (val != null) {
                //以前有判断val != -1,是否需要判断val != NaN ???
                scope.getVariable(var).setValue(val);
                this.express = this.express.replaceAll(var, val.toString());
            } else {
                this.notMatchVars.add(var);
            }
        }
    }

    public double getValue() {
        if (!this.getNotMatchVars().isEmpty()) {
            return Double.NaN;
        }
        return expr.evaluate();
    }

    private void dealIndex() {
        //a1#1#10~40
        for (String var : vars) {
            if (!var.contains(VarIndex.varIndxSplit)) {
                continue;
            }
            indexVarMap.put(var, new VarIndex(var));
        }
    }

    private void removeDuplicate() {
        for (String e : indexVarMap.keySet()) {
            this.vars.remove(e);
        }
    }

    public Set<String> getNotMatchVars() {
        return notMatchVars;
    }

    public String getExpress() {
        return express;
    }
}

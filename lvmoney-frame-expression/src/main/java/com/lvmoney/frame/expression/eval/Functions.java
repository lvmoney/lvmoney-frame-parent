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
public class Functions {
    /**
     * 默认大小 24*60*60*1000=86400000
     */
    private static final Long DEFAULT_VALUE = 86400000L;

    /**
     * Provides access to {@link Math#sin(double)}
     */
    public static final Function SIN = new UnaryFunction() {
        @Override
        protected double eval(double a) {
            return Math.sin(a);
        }
    };

    /**
     * Provides access to {@link Math#sinh(double)}
     */
    public static final Function SINH = new UnaryFunction() {
        @Override
        protected double eval(double a) {
            return Math.sinh(a);
        }
    };

    /**
     * Provides access to {@link Math#cos(double)}
     */
    public static final Function COS = new UnaryFunction() {
        @Override
        protected double eval(double a) {
            return Math.cos(a);
        }
    };

    /**
     * Provides access to {@link Math#cosh(double)}
     */
    public static final Function COSH = new UnaryFunction() {
        @Override
        protected double eval(double a) {
            return Math.cosh(a);
        }
    };

    /**
     * Provides access to {@link Math#tan(double)}
     */
    public static final Function TAN = new UnaryFunction() {
        @Override
        protected double eval(double a) {
            return Math.tan(a);
        }
    };

    /**
     * Provides access to {@link Math#tanh(double)}
     */
    public static final Function TANH = new UnaryFunction() {
        @Override
        protected double eval(double a) {
            return Math.tanh(a);
        }
    };

    /**
     * Provides access to {@link Math#abs(double)}
     */
    public static final Function ABS = new UnaryFunction() {
        @Override
        protected double eval(double a) {
            return Math.abs(a);
        }
    };

    /**
     * Provides access to {@link Math#asin(double)}
     */
    public static final Function ASIN = new UnaryFunction() {
        @Override
        protected double eval(double a) {
            return Math.asin(a);
        }
    };

    /**
     * Provides access to {@link Math#acos(double)}
     */
    public static final Function ACOS = new UnaryFunction() {
        @Override
        protected double eval(double a) {
            return Math.acos(a);
        }
    };

    /**
     * Provides access to {@link Math#atan(double)}
     */
    public static final Function ATAN = new UnaryFunction() {
        @Override
        protected double eval(double a) {
            return Math.atan(a);
        }
    };

    /**
     * Provides access to {@link Math#atan2(double, double)}
     */
    public static final Function ATAN2 = new BinaryFunction() {
        @Override
        protected double eval(double a, double b) {
            return Math.atan2(a, b);
        }
    };

    /**
     * Provides access to {@link Math#round(double)}
     */
    public static final Function ROUND = new UnaryFunction() {
        @Override
        protected double eval(double a) {
            return Math.round(a);
        }
    };

    /**
     * Provides access to {@link Math#floor(double)}
     */
    public static final Function FLOOR = new UnaryFunction() {
        @Override
        protected double eval(double a) {
            return Math.floor(a);
        }
    };

    /**
     * Provides access to {@link Math#ceil(double)}
     */
    public static final Function CEIL = new UnaryFunction() {
        @Override
        protected double eval(double a) {
            return Math.ceil(a);
        }
    };

    /**
     * Provides access to {@link Math#pow(double, double)}
     */
    public static final Function POW = new BinaryFunction() {
        @Override
        protected double eval(double a, double b) {
            return Math.pow(a, b);
        }
    };

    /**
     * Provides access to {@link Math#sqrt(double)}
     */
    public static final Function SQRT = new UnaryFunction() {
        @Override
        protected double eval(double a) {
            return Math.sqrt(a);
        }
    };

    /**
     * Provides access to {@link Math#exp(double)}
     */
    public static final Function EXP = new UnaryFunction() {
        @Override
        protected double eval(double a) {
            return Math.exp(a);
        }
    };

    /**
     * Provides access to {@link Math#log(double)}
     */
    public static final Function LN = new UnaryFunction() {
        @Override
        protected double eval(double a) {
            return Math.log(a);
        }
    };

    /**
     * Provides access to {@link Math#log10(double)}
     */
    public static final Function LOG = new UnaryFunction() {
        @Override
        protected double eval(double a) {
            return Math.log10(a);
        }
    };

    /**
     * Provides access to {@link Math#min(double, double)}
     */
    /**
     * Provides access to {@link Math#min(double, double)}
     */
    public static final Function MIN = new Function() {

        @Override
        public int getNumberOfArguments() {
            return -1;
        }

        @Override
        public double eval(List<Expression> args) {
            if (args.isEmpty()) {
                return 0;
            }
            double min = args.get(0).evaluate();
            for (Expression e : args) {
                double v = e.evaluate();
                if (min > v) {
                    min = v;
                }
            }
            return min;
        }

        @Override
        public boolean isNaturalFunction() {
            return true;
        }
    };

    public static final Function MAX = new Function() {

        @Override
        public int getNumberOfArguments() {
            return -1;
        }

        @Override
        public double eval(List<Expression> args) {
            if (args.isEmpty()) {
                return 0;
            }
            double max = args.get(0).evaluate();
            for (Expression e : args) {
                double v = e.evaluate();
                if (max < v) {
                    max = v;
                }
            }
            return max;
        }

        @Override
        public boolean isNaturalFunction() {
            return true;
        }
    };

    /**
     * Provides access to {@link Math#random()} which will be multiplied by the given argument.
     */
    public static final Function RND = new UnaryFunction() {
        @Override
        protected double eval(double a) {
            return Math.random() * a;
        }
    };

    /**
     * Provides access to {@link Math#signum(double)}
     */
    public static final Function SIGN = new UnaryFunction() {
        @Override
        protected double eval(double a) {
            return Math.signum(a);
        }
    };

    /**
     * Provides access to {@link Math#toDegrees(double)}
     */
    public static final Function DEG = new UnaryFunction() {
        @Override
        protected double eval(double a) {
            return Math.toDegrees(a);
        }
    };

    /**
     * Provides access to {@link Math#toRadians(double)}
     */
    public static final Function RAD = new UnaryFunction() {
        @Override
        protected double eval(double a) {
            return Math.toRadians(a);
        }
    };

    /**
     * Provides an if-like function
     * <p>
     * It expects three arguments: A condition, an expression being evaluated if the condition is non zero and an
     * expression which is being evaluated if the condition is zero.
     */
    public static final Function IF = new Function() {
        @Override
        public int getNumberOfArguments() {
            return 3;
        }

        @Override
        public double eval(List<Expression> args) {
            double check = args.get(0).evaluate();
            if (Double.isNaN(check)) {
                return check;
            }
            if (Math.abs(check) > 0) {
                return args.get(1).evaluate();
            } else {
                return args.get(2).evaluate();
            }
        }

        @Override
        public boolean isNaturalFunction() {
            return false;
        }
    };

    public static final Function IN = new Function() {
        @Override
        public int getNumberOfArguments() {
            return -1;
        }

        @Override
        public double eval(List<Expression> args) {
            double result = 0;
            if (args.size() < 2) {
                return result;
            }
            Expression first = args.get(0);
            IValue iv;
            if (first instanceof IValue && !(iv = (IValue) first).isNumber()) {
                String arg1 = iv.value();
                for (int i = 1; i < args.size(); i++) {
                    Expression current = args.get(i);
                    if (current instanceof IValue && arg1.equals(((IValue) current).value())) {
                        return 1;
                    }
                }
            } else {
                double v1 = first.evaluate();
                if (v1 == 0) {
                    return 0;
                }
                for (int i = 1; i < args.size(); i++) {
                    if (v1 == args.get(i).evaluate()) {
                        return 1;
                    }
                }
            }

            return 0;
        }

        @Override
        public boolean isNaturalFunction() {
            return true;
        }
    };

    public static final Function DATE_DIFF = new BinaryFunction() {
        @Override
        public int getNumberOfArguments() {
            return 2;
        }

        @Override
        public double eval(double a, double b) {
            return (long) a / DEFAULT_VALUE - (long) b / DEFAULT_VALUE;
        }

        @Override
        public boolean isNaturalFunction() {
            return false;
        }
    };

    private Functions() {
    }
}

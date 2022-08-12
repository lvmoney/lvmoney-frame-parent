package com.lvmoney.frame.expression.eval;
/**
 * 描述:
 * 包名:com.lvmoney.frame.expression.eval
 * 版本信息: 版本1.0
 * 日期:2019/11/19
 * Copyright 四川******科技有限公司
 */


import com.lvmoney.frame.base.core.constant.BaseConstant;
import com.lvmoney.frame.base.core.util.DoubleUtil;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2019/11/19 16:38
 */
public class BinaryOperation extends Expression {

    private static final long serialVersionUID = -3811171614568221526L;

    /**
     * Enumerates the operations supported by this expression.
     */
    public enum Op {
        ADD(3), SUBTRACT(3), MULTIPLY(4), DIVIDE(4), MODULO(4), POWER(5), LT(2), LT_EQ(2), EQ(2), GT_EQ(2), GT(2),
        NEQ(2), AND(1), OR(1);

        public int getPriority() {
            return priority;
        }

        private final int priority;

        Op(int priority) {
            this.priority = priority;
        }
    }

    private final Op op;
    private Expression left;
    private Expression right;
    private boolean sealed = false;

    /**
     * When comparing two double values, those are considered equal, if their difference is lower than the defined
     * epsilon. This is way better than relying on an exact comparison due to rounding errors
     */
    public static final double EPSILON = 0.0000000001;

    /**
     * Creates a new binary operator for the given operator and operands.
     *
     * @param op    the operator of the operation
     * @param left  the left operand
     * @param right the right operand
     */
    public BinaryOperation(Op op, Expression left, Expression right) {
        this.op = op;
        this.left = left;
        this.right = right;
    }

    /**
     * Returns the operation performed by this binary operation.
     *
     * @return the operation performed
     */
    public Op getOp() {
        return op;
    }

    /**
     * Returns the left operand
     *
     * @return the left operand of this operation
     */
    public Expression getLeft() {
        return left;
    }

    /**
     * Replaces the left operand of the operation with the given expression.
     *
     * @param left the new expression to be used as left operand
     */
    public void setLeft(Expression left) {
        this.left = left;
    }

    /**
     * Returns the right operand
     *
     * @return the right operand of this operation
     */
    public Expression getRight() {
        return right;
    }

    /**
     * Marks an operation as sealed, meaning that re-ordering or operations on the same level must not be re-ordered.
     * <p>
     * Binary operations are sealed if they're e.g. surrounded by braces.
     */
    public void seal() {
        sealed = true;
    }

    /**
     * Determines if the operation is sealed and operands must not be re-ordered.
     *
     * @return <tt>true</tt> if the operation is protected by braces and operands might not be exchanged with
     * operations nearby.
     */
    public boolean isSealed() {
        return sealed;
    }

    @Override
    public double evaluate() {

        double a = left.evaluate();
        double b = right.evaluate();
        if (DoubleUtil.isErrNumber(a) || DoubleUtil.isErrNumber(b)) {
            return Double.NaN;
        }


        if (op == Op.EQ) {
            return compareEqual(left, right);
        }
        if (op == Op.NEQ) {
            return compareEqual(left, right) == 1 ? 0 : 1;
        }


        if (op == Op.AND && Math.abs(a) == 0) {
            return 0;
        }
        if (op == Op.OR && Math.abs(a) > 0) {
            return 1;
        }

        if (op == Op.AND || op == Op.OR) {
            return b;
        }

        if (op == Op.ADD) {
            return a + b;
        }
        if (op == Op.SUBTRACT) {
            return a - b;
        }
        if (op == Op.MULTIPLY) {
            return a * b;
        }
        if (op == Op.DIVIDE) {
            return a / b;
        }
        if (op == Op.POWER) {
            return Math.pow(a, b);
        }
        if (op == Op.MODULO) {
            return a % b;
        }
        if (op == Op.LT) {
            return a < b ? 1 : 0;
        }
        if (op == Op.LT_EQ) {
            return a < b || Math.abs(a - b) < EPSILON ? 1 : 0;
        }
        if (op == Op.GT) {
            return a > b ? 1 : 0;
        }
        if (op == Op.GT_EQ) {
            return a > b || Math.abs(a - b) < EPSILON ? 1 : 0;
        }

        throw new UnsupportedOperationException(String.valueOf(op));
    }


    @Override
    public Expression simplify() {
        left = left.simplify();
        right = right.simplify();
        // First of all we check of both sides are constant. If true, we can directly evaluate the result...
        if (left.isConstant() && right.isConstant()) {
            return new Constant(evaluate());
        }
        // + and * are commutative and associative, therefore we can reorder operands as we desire
        if (op == Op.ADD || op == Op.MULTIPLY) {
            // We prefer the have the constant part at the left side, re-order if it is the other way round.
            // This simplifies further optimizations as we can concentrate on the left side
            if (right.isConstant()) {
                Expression tmp = right;
                right = left;
                left = tmp;
            }

            if (right instanceof BinaryOperation) {
                BinaryOperation childOp = (BinaryOperation) right;
                if (op == childOp.op) {
                    // We have a sub-operation with the same operator, let's see if we can pre-compute some constants
                    if (left.isConstant()) {
                        // Left side is constant, we therefore can combine constants. We can rely on the constant
                        // being on the left side, since we reorder commutative operations (see above)
                        if (childOp.left.isConstant()) {
                            if (op == Op.ADD) {
                                return new BinaryOperation(op,
                                        new Constant(left.evaluate() + childOp.left.evaluate()),
                                        childOp.right);
                            }
                            if (op == Op.MULTIPLY) {
                                return new BinaryOperation(op,
                                        new Constant(left.evaluate() * childOp.left.evaluate()),
                                        childOp.right);
                            }
                        }
                    } else if (childOp.left.isConstant()) {
                        // Since our left side is non constant, but the left side of the child expression is,
                        // we push the constant up, to support further optimizations
                        return new BinaryOperation(op, childOp.left, new BinaryOperation(op, left, childOp.right));
                    }
                }
            }
        }

        return super.simplify();
    }

    @Override
    public String toString() {
        return BaseConstant.BRACKETS_LEFT + left.toString() + BaseConstant.PLACEHOLDER_BLANK_SPACE + op + BaseConstant.PLACEHOLDER_BLANK_SPACE + right + BaseConstant.BRACKETS_RIGHT;
    }


    private double compareEqual(Expression left, Expression right) {
        if (left instanceof IValue && right instanceof IValue) {
            IValue l = (IValue) left;
            IValue r = (IValue) right;
            if (!l.isNumber() && !r.isNumber()) {
                return l.value().equals(r.value()) ? 1 : 0;
            } else if (!l.isNumber() || !r.isNumber()) {
                return 0;
            }
        }
        return Math.abs(left.evaluate() - right.evaluate()) < EPSILON ? 1 : 0;
    }

}

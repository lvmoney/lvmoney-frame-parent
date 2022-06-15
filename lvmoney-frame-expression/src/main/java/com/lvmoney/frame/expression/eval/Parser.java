package com.lvmoney.frame.expression.eval;/**
 * 描述:
 * 包名:com.lvmoney.frame.expression.eval
 * 版本信息: 版本1.0
 * 日期:2019/11/19
 * Copyright 四川******科技有限公司
 */

import com.lvmoney.frame.base.core.constant.BaseConstant;
import com.lvmoney.frame.expression.common.MultiFunction;
import com.lvmoney.frame.expression.common.PluginLoad;
import com.lvmoney.frame.expression.tokenizer.ParseError;
import com.lvmoney.frame.expression.tokenizer.ParseException;
import com.lvmoney.frame.expression.tokenizer.Token;
import com.lvmoney.frame.expression.tokenizer.Tokenizer;

import java.io.Reader;
import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static com.lvmoney.frame.base.core.constant.BaseConstant.*;
import static com.lvmoney.frame.base.core.constant.MathOperationConstant.*;
import static com.lvmoney.frame.expression.constant.ExpressionConstant.*;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2019/11/19 16:38
 */
public class Parser implements Serializable {

    private static final long serialVersionUID = 437479083676978701L;
    private final Scope scope;
    private List<ParseError> errors = new ArrayList<ParseError>();
    private Tokenizer tokenizer;
    private static Map<String, Function> functionTable;

    private static Function empty_function = new MultiFunction() {
        @Override
        public double eval(List<Expression> arg0) {
            return 0;
        }
    };


    /**
     * Registers a new function which can be referenced from within an expression.
     * <p>
     * A function must be registered before an expression is parsed in order to be visible.
     *
     * @param name     the name of the function. If a function with the same name is already available, it will be
     *                 overridden
     * @param function the function which is invoked as an expression is evaluated
     */
    public static void registerFunction(String name, Function function) {

        functionTable.put(name, function);
    }

    /**
     * Setup well known functions
     */
    static {
        functionTable = new TreeMap<String, Function>();

        registerFunction("sin", Functions.SIN);
        registerFunction("cos", Functions.COS);
        registerFunction("tan", Functions.TAN);
        registerFunction("sinh", Functions.SINH);
        registerFunction("cosh", Functions.COSH);
        registerFunction("tanh", Functions.TANH);
        registerFunction("asin", Functions.ASIN);
        registerFunction("acos", Functions.ACOS);
        registerFunction("atan", Functions.ATAN);
        registerFunction("atan2", Functions.ATAN2);
        registerFunction("deg", Functions.DEG);
        registerFunction("rad", Functions.RAD);
        registerFunction("abs", Functions.ABS);
        registerFunction("round", Functions.ROUND);
        registerFunction("ceil", Functions.CEIL);
        registerFunction("floor", Functions.FLOOR);
        registerFunction("exp", Functions.EXP);
        registerFunction("ln", Functions.LN);
        registerFunction("log", Functions.LOG);
        registerFunction("sqrt", Functions.SQRT);
        registerFunction("pow", Functions.POW);
        registerFunction("min", Functions.MIN);
        registerFunction("max", Functions.MAX);
        registerFunction("random", Functions.RND);
        registerFunction("sign", Functions.SIGN);
        //    registerFunction("if", Functions.IF);
        registerFunction("in", Functions.IN);
        registerFunction("date_diff", Functions.DATE_DIFF);


        registerFunction("avg", new Function() {

            @Override
            public int getNumberOfArguments() {
                return -1;
            }

            @Override
            public double eval(List<Expression> args) {

                double avg = 0.0D;

                if (args.isEmpty()) {

                    return avg;

                }

                for (Expression e : args) {

                    avg += e.evaluate();

                }
                return avg / args.size();
            }

            @Override
            public boolean isNaturalFunction() {
                return true;
            }
        });
        registerFunction("sum", new Function() {
            @Override
            public int getNumberOfArguments() {
                return -1;
            }

            @Override
            public double eval(List<Expression> args) {
                double sum = 0.0D;
                if (args.isEmpty()) {
                    return sum;
                }
                for (Expression e : args) {
                    sum += e.evaluate();
                }
                return sum;
            }

            @Override
            public boolean isNaturalFunction() {
                return true;
            }
        });

        //注册function
        PluginLoad.registerPluginsForPackage(getCurrentFunctionPackage());


    }

    private static String getCurrentFunctionPackage() {
        String packageName = Parser.class.getPackage().getName();
        return packageName.substring(0, packageName.lastIndexOf(BaseConstant.DECIMAL_POINT));

        //return packageName.substring(0,packageName.lastIndexOf("."))+".function";
    }

    /**
     * Parses the given input into an expression.
     *
     * @param input the expression to be parsed
     * @return the resulting AST as expression
     * @throws ParseException if the expression contains one or more errors
     */
    public static Expression parse(String input) throws ParseException {
        return new Parser(new StringReader(input), new Scope()).parse();
    }

    /**
     * Parses the given input into an expression.
     *
     * @param input the expression to be parsed
     * @return the resulting AST as expression
     * @throws ParseException if the expression contains one or more errors
     */
    public static Expression parse(Reader input) throws ParseException {
        return new Parser(input, new Scope()).parse();
    }

    /**
     * Parses the given input into an expression.
     * <p>
     * Referenced variables will be resolved using the given Scope
     *
     * @param input the expression to be parsed
     * @param scope the scope used to resolve variables
     * @return the resulting AST as expression
     * @throws ParseException if the expression contains one or more errors
     */
    public static Expression parse(String input, Scope scope) throws ParseException {
        return new Parser(new StringReader(input), scope).parse();
    }

    /**
     * Parses the given input into an expression.
     * <p>
     * Referenced variables will be resolved using the given Scope
     *
     * @param input the expression to be parsed
     * @param scope the scope used to resolve variables
     * @return the resulting AST as expression
     * @throws ParseException if the expression contains one or more errors
     */
    public static Expression parse(Reader input, Scope scope) throws ParseException {
        return new Parser(input, scope).parse();
    }

    /*
     * Use one of the static methods to parse an expression
     */
    protected Parser(Reader input, Scope scope) {
        this.scope = scope;
        tokenizer = new Tokenizer(input);
        tokenizer.setProblemCollector(errors);
    }

    /**
     * Parses the expression in <tt>input</tt>
     *
     * @return the parsed expression
     * @throws ParseException if the expression contains one or more errors
     */
    protected Expression parse() throws ParseException {
        Expression result = expression().simplify();
        if (tokenizer.current().isNotEnd()) {
            Token token = tokenizer.consume();
            errors.add(ParseError.error(token,
                    String.format("Unexpected token: '%s'. Expected an expression.",
                            token.getSource())));
        }
        if (!errors.isEmpty()) {
            throw ParseException.create(errors);
        }
        result.setScope(this.scope);
        return result;
    }

    /**
     * Parser rule for parsing an expression.
     * <p>
     * This is the root rule. An expression is a <tt>relationalExpression</tt> which might be followed by a logical
     * operator (&amp;&amp; or ||) and another <tt>expression</tt>.
     *
     * @return an expression parsed from the given input
     */
    protected Expression expression() {
        Expression left = relationalExpression();
        if (tokenizer.current().isSymbol(OPERATION_SYMBOL_AND_AND)) {
            tokenizer.consume();
            Expression right = expression();
            return reOrder(left, right, BinaryOperation.Op.AND);
        }
        if (tokenizer.current().isSymbol(OPERATION_SYMBOL_OR_OR)) {
            tokenizer.consume();
            Expression right = expression();
            return reOrder(left, right, BinaryOperation.Op.OR);
        }
        return left;
    }

    /**
     * Parser rule for parsing a relational expression.
     * <p>
     * A relational expression is a <tt>term</tt> which might be followed by a relational operator
     * (&lt;,&lt;=,...,&gt;) and another <tt>relationalExpression</tt>.
     *
     * @return a relational expression parsed from the given input
     */
    protected Expression relationalExpression() {
        Expression left = term();
        if (tokenizer.current().isSymbol(OPERATION_SYMBOL_LESS)) {
            tokenizer.consume();
            Expression right = relationalExpression();
            return reOrder(left, right, BinaryOperation.Op.LT);
        }
        if (tokenizer.current().isSymbol(OPERATION_SYMBOL_LESS_EQUAL)) {
            tokenizer.consume();
            Expression right = relationalExpression();
            return reOrder(left, right, BinaryOperation.Op.LT_EQ);
        }
        if (tokenizer.current().isSymbol(OPERATION_SYMBOL_EQUAL_EQUAL) || tokenizer.current().isSymbol(OPERATION_SYMBOL_EQUAL)) {
            tokenizer.consume();
            Expression right = relationalExpression();
            return reOrder(left, right, BinaryOperation.Op.EQ);
        }
        if (tokenizer.current().isSymbol(OPERATION_SYMBOL_GREATER_EQUAL)) {
            tokenizer.consume();
            Expression right = relationalExpression();
            return reOrder(left, right, BinaryOperation.Op.GT_EQ);
        }
        if (tokenizer.current().isSymbol(OPERATION_SYMBOL_GREATER)) {
            tokenizer.consume();
            Expression right = relationalExpression();
            return reOrder(left, right, BinaryOperation.Op.GT);
        }
        if (tokenizer.current().isSymbol(OPERATION_SYMBOL_EQUAL_NOT)) {
            tokenizer.consume();
            Expression right = relationalExpression();
            return reOrder(left, right, BinaryOperation.Op.NEQ);
        }
        return left;
    }

    /**
     * Parser rule for parsing a term.
     * <p>
     * A term is a <tt>product</tt> which might be followed by + or - as operator and another <tt>term</tt>.
     *
     * @return a term parsed from the given input
     */
    protected Expression term() {
        Expression left = product();
        if (tokenizer.current().isSymbol(OPERATION_SYMBOL_PLUS)) {
            tokenizer.consume();
            Expression right = term();
            return reOrder(left, right, BinaryOperation.Op.ADD);
        }
        if (tokenizer.current().isSymbol(OPERATION_SYMBOL_REDUCE)) {
            tokenizer.consume();
            Expression right = term();
            return reOrder(left, right, BinaryOperation.Op.SUBTRACT);
        }
        if (tokenizer.current().isNumber()) {
            if (tokenizer.current().getContents().startsWith(OPERATION_SYMBOL_REDUCE)) {
                Expression right = term();
                return reOrder(left, right, BinaryOperation.Op.ADD);
            }
        }

        return left;
    }

    /**
     * Parser rule for parsing a product.
     * <p>
     * A product is a <tt>power</tt> which might be followed by *, / or % as operator and another <tt>product</tt>.
     *
     * @return a product parsed from the given input
     */
    protected Expression product() {
        Expression left = power();
        if (tokenizer.current().isSymbol(OPERATION_SYMBOL_MULTIPLICATION)) {
            tokenizer.consume();
            Expression right = product();
            return reOrder(left, right, BinaryOperation.Op.MULTIPLY);
        }
        if (tokenizer.current().isSymbol(OPERATION_SYMBOL_DIVISION)) {
            tokenizer.consume();
            Expression right = product();
            return reOrder(left, right, BinaryOperation.Op.DIVIDE);
        }
        if (tokenizer.current().isSymbol(OPERATION_SYMBOL_RESIDUAL)) {
            tokenizer.consume();
            Expression right = product();
            return reOrder(left, right, BinaryOperation.Op.MODULO);
        }
        return left;
    }

    /*
     * Reorders the operands of the given operation in order to generate a "left handed" AST which performs evaluations
     * in natural order (from left to right).
     */
    protected Expression reOrder(Expression left, Expression right, BinaryOperation.Op op) {
        if (right instanceof BinaryOperation) {
            BinaryOperation rightOp = (BinaryOperation) right;
            if (!rightOp.isSealed() && rightOp.getOp().getPriority() == op.getPriority()) {
                replaceLeft(rightOp, left, op);
                return right;
            }
        }
        return new BinaryOperation(op, left, right);
    }

    protected void replaceLeft(BinaryOperation target, Expression newLeft, BinaryOperation.Op op) {
        if (target.getLeft() instanceof BinaryOperation) {
            BinaryOperation leftOp = (BinaryOperation) target.getLeft();
            if (!leftOp.isSealed() && leftOp.getOp().getPriority() == op.getPriority()) {
                replaceLeft(leftOp, newLeft, op);
                return;
            }
        }
        target.setLeft(new BinaryOperation(op, newLeft, target.getLeft()));
    }

    /**
     * Parser rule for parsing a power.
     * <p>
     * A power is an <tt>atom</tt> which might be followed by ^ or ** as operator and another <tt>power</tt>.
     *
     * @return a power parsed from the given input
     */
    protected Expression power() {
        Expression left = atom();
        if (tokenizer.current().isSymbol(OPERATION_SYMBOL_XOR) || tokenizer.current().isSymbol(OPERATION_SYMBOL_ASTERISK_ASTERISK)) {
            tokenizer.consume();
            Expression right = power();
            return reOrder(left, right, BinaryOperation.Op.POWER);
        }
        return left;
    }

    /**
     * Parser rule for parsing an atom.
     * <p>
     * An atom is either a numeric constant, an <tt>expression</tt> in brackets, an <tt>expression</tt> surrounded by
     * | to signal the absolute function, an identifier to signal a variable reference or an identifier followed by a
     * bracket to signal a function call.
     *
     * @return an atom parsed from the given input
     */
    protected Expression atom() {
        if (tokenizer.current().isSymbol(OPERATION_SYMBOL_REDUCE)) {
            tokenizer.consume();
            BinaryOperation result = new BinaryOperation(BinaryOperation.Op.SUBTRACT, new Constant(0d), atom());
            result.seal();
            return result;
        }
        if (tokenizer.current().isSymbol(OPERATION_SYMBOL_PLUS) && tokenizer.next().isSymbol(BRACKETS_LEFT)) {
            // Support for brackets with a leading + like "+(2.2)" in this case we simply ignore the
            // + sign
            tokenizer.consume();
        }
        if (tokenizer.current().isSymbol(BRACKETS_LEFT)) {
            tokenizer.consume();
            Expression result = expression();
            if (result instanceof BinaryOperation) {
                ((BinaryOperation) result).seal();
            }
            expect(Token.TokenType.SYMBOL, BRACKETS_RIGHT);
            return result;
        }
        if (tokenizer.current().isSymbol(OPERATION_SYMBOL_OR)) {
            tokenizer.consume();
            FunctionCall call = new FunctionCall();
            call.addParameter(expression());
            call.setFunction(Functions.ABS);
            expect(Token.TokenType.SYMBOL, OPERATION_SYMBOL_OR);
            return call;
        }
        if (tokenizer.current().isIdentifier()) {
            if (tokenizer.next().isSymbol(BRACKETS_LEFT)) {
                return functionCall();
            }
            Token variableName = tokenizer.consume();
            try {
                return new VariableReference(scope.getVariable(variableName.getContents()));
            } catch (@SuppressWarnings("UnusedCatchParameter") IllegalArgumentException e) {
                errors.add(ParseError.error(variableName,
                        String.format("Unknown variable: '%s'", variableName.getContents())));
                return new Constant(0);
            }
        }
        return literalAtom();
    }

    /**
     * Parser rule for parsing a literal atom.
     * <p>
     * An literal atom is a numeric constant.
     *
     * @return an atom parsed from the given input
     */
    private Expression literalAtom() {
        if (tokenizer.current().isSymbol(OPERATION_SYMBOL_PLUS) && tokenizer.next().isNumber()) {
            // Parse numbers with a leading + sign like +2.02 by simply ignoring the +
            tokenizer.consume();
        }
        if (tokenizer.current().isNumber()) {
            double value = Double.parseDouble(tokenizer.consume().getContents());
            if (tokenizer.current().is(Token.TokenType.ID)) {
                String quantifier = tokenizer.current().getContents().intern();
                if (STRING_N_LOWER == quantifier) {
                    value /= 1000000000d;
                    tokenizer.consume();
                } else if (STRING_U_LOWER == quantifier) {
                    value /= 1000000d;
                    tokenizer.consume();
                } else if (STRING_M_LOWER == quantifier) {
                    value /= 1000d;
                    tokenizer.consume();
                } else if (STRING_K_UPPER == quantifier || STRING_k_LOWER == quantifier) {
                    value *= 1000d;
                    tokenizer.consume();
                } else if (STRING_M_UPPER == quantifier) {
                    value *= 1000000d;
                    tokenizer.consume();
                } else if (STRING_G_UPPER == quantifier) {
                    value *= 1000000000d;
                    tokenizer.consume();
                } else {
                    Token token = tokenizer.consume();
                    errors.add(ParseError.error(token,
                            String.format("Unexpected token: '%s'. Expected a valid quantifier.",
                                    token.getSource())));
                }
            }
            return new Constant(value);
        }
        if (tokenizer.current().isString()) {
            return new Constant(tokenizer.consume().getContents());
        }
        Token token = tokenizer.consume();
        errors.add(ParseError.error(token,
                String.format("Unexpected token: '%s'. Expected an expression.",
                        token.getSource())));
        return Constant.EMPTY;
    }

    /**
     * Parses a function call.
     *
     * @return the function call as Expression
     */
    protected Expression functionCall() {
        FunctionCall call = new FunctionCall();
        Token funToken = tokenizer.consume();
        Function fun = functionTable.get(funToken.getContents());
        if (fun == null) {
            errors.add(ParseError.error(funToken, String.format("Unknown function: '%s'", funToken.getContents())));
        }
        call.setFunction(fun);
        tokenizer.consume();
        while (!tokenizer.current().isSymbol(BRACKETS_RIGHT) && tokenizer.current().isNotEnd()) {
            if (!call.getParameters().isEmpty()) {
                expect(Token.TokenType.SYMBOL, CHAR_COMMA);
            }
            call.addParameter(expression());
        }
        expect(Token.TokenType.SYMBOL, BRACKETS_RIGHT);
        if (fun == null) {
            return Constant.EMPTY;
        }
        if (call.getParameters().size() != fun.getNumberOfArguments() && fun.getNumberOfArguments() >= 0) {
            errors.add(ParseError.error(funToken,
                    String.format(
                            "Number of arguments for function '%s' do not match. Expected: %d, Found: %d",
                            funToken.getContents(),
                            fun.getNumberOfArguments(),
                            call.getParameters().size())));
            return Constant.EMPTY;
        }
        return call;
    }

    /**
     * Signals that the given token is expected.
     * <p>
     * If the current input is pointing at the specified token, it will be consumed. If not, an error will be added
     * to the error list and the input remains unchanged.
     *
     * @param type    the type of the expected token
     * @param trigger the trigger of the expected token
     */
    protected void expect(Token.TokenType type, String trigger) {
        if (tokenizer.current().matches(type, trigger)) {
            tokenizer.consume();
        } else {
            errors.add(ParseError.error(tokenizer.current(),
                    String.format("Unexpected token '%s'. Expected: '%s'",
                            tokenizer.current().getSource(),
                            trigger)));
        }
    }
}

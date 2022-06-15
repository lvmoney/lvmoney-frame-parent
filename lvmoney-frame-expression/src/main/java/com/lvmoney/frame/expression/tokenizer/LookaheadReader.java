package com.lvmoney.frame.expression.tokenizer;/**
 * 描述:
 * 包名:com.lvmoney.frame.expression.tokenizer
 * 版本信息: 版本1.0
 * 日期:2019/11/19
 * Copyright 四川******科技有限公司
 */

import com.lvmoney.frame.base.core.constant.BaseConstant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import static com.lvmoney.frame.expression.constant.ExpressionConstant.CHAR_N_TRANS;
import static com.lvmoney.frame.expression.constant.ExpressionConstant.CHAR_ZERO_TRANS;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2019/11/19 16:38
 */
public class LookaheadReader extends Lookahead<Char> {

    private static final Integer NUM_2 = 2;
    private Reader input;
    private int line = 1;
    private int pos = 0;

    /**
     * Creates a new LookaheadReader for the given Reader.
     * <p>
     * Internally a {@link BufferedReader} is used to efficiently read single characters. The given reader will not
     * be closed by this class.
     *
     * @param input the reader to draw the input from
     */
    public LookaheadReader(Reader input) {
        if (input == null) {
            throw new IllegalArgumentException("input must not be null");
        }
        this.input = new BufferedReader(input);
    }

    @Override
    protected Char endOfInput() {
        return new Char(CHAR_ZERO_TRANS, line, pos);
    }

    @Override
    protected Char fetch() {
        try {
            int character = input.read();
            if (character == -1) {
                return null;
            }
            if (character == CHAR_N_TRANS) {
                line++;
                pos = 0;
            }
            pos++;
            return new Char((char) character, line, pos);
        } catch (IOException e) {
            problemCollector.add(ParseError.error(new Char(CHAR_ZERO_TRANS, line, pos), e.getMessage()));
            return null;
        }
    }

    @Override
    public String toString() {
        if (itemBuffer.isEmpty()) {
            return line + BaseConstant.COLON + pos + ": Buffer empty";
        }
        if (itemBuffer.size() < NUM_2) {
            return line + BaseConstant.COLON + pos + BaseConstant.COLON + BaseConstant.PLACEHOLDER_BLANK_SPACE + current();
        }
        return line + BaseConstant.COLON + pos + BaseConstant.COLON + BaseConstant.PLACEHOLDER_BLANK_SPACE + current() + BaseConstant.CHAR_COMMA + BaseConstant.PLACEHOLDER_BLANK_SPACE + next();
    }
}

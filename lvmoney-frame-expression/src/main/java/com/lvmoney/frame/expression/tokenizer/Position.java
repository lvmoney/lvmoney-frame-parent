package com.lvmoney.frame.expression.tokenizer;/**
 * 描述:
 * 包名:com.lvmoney.frame.expression.tokenizer
 * 版本信息: 版本1.0
 * 日期:2019/11/19
 * Copyright 四川******科技有限公司
 */

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2019/11/19 16:38
 */
public interface Position {
    /**
     * Returns the line number of this position.
     *
     * @return the one-based line number of this position
     */
    int getLine();

    /**
     * Returns the character position within the line of this position
     *
     * @return the one-based character position of this
     */
    int getPos();

    /**
     * Represents an unknown position for warnings and errors which cannot be associated with a defined position.
     */
    Position UNKNOWN = new Position() {

        @Override
        public int getLine() {
            return 0;
        }

        @Override
        public int getPos() {
            return 0;
        }
    };
}

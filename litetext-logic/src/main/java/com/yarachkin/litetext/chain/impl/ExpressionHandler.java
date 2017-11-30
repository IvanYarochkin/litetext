package com.yarachkin.litetext.chain.impl;

import com.yarachkin.litetext.chain.BaseHandler;
import com.yarachkin.litetext.composite.LiteTextComponent;
import com.yarachkin.litetext.composite.impl.Expression;

public class ExpressionHandler implements BaseHandler {

    @Override
    public LiteTextComponent parse(String text) {
        return new Expression(text);
    }
}

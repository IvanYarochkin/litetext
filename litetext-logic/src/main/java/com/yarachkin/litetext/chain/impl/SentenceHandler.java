package com.yarachkin.litetext.chain.impl;

import com.yarachkin.litetext.chain.BaseHandler;
import com.yarachkin.litetext.composite.LiteTextComponent;

public class SentenceHandler implements BaseHandler {
    private LexemeHandler lexemeHandler = new LexemeHandler();
    private ExpressionHandler expressionHandler = new ExpressionHandler();

    @Override
    public LiteTextComponent parse(String text) {
        throw new UnsupportedOperationException();
    }
}

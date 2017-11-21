package com.yarachkin.litetext.chain.impl;

import com.yarachkin.litetext.chain.BaseHandler;
import com.yarachkin.litetext.composite.LiteTextComponent;

public class LexemeHandler implements BaseHandler {
    private WordHandler wordHandler = new WordHandler();
    private SignHandler signHandler = new SignHandler();

    @Override
    public LiteTextComponent parse(String text) {
        throw new UnsupportedOperationException();
    }
}

package com.yarachkin.litetext.chain.impl;

import com.yarachkin.litetext.chain.BaseHandler;
import com.yarachkin.litetext.composite.LiteTextComponent;
import com.yarachkin.litetext.composite.impl.Word;

public class WordHandler implements BaseHandler {

    @Override
    public LiteTextComponent parse(String text) {
        LiteTextComponent word = new Word(text);
        return word;
    }
}

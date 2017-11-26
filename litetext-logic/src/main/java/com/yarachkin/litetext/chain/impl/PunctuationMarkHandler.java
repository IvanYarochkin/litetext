package com.yarachkin.litetext.chain.impl;

import com.yarachkin.litetext.chain.BaseHandler;
import com.yarachkin.litetext.composite.LiteTextComponent;
import com.yarachkin.litetext.composite.impl.PunctuationMark;

public class PunctuationMarkHandler implements BaseHandler {

    @Override
    public LiteTextComponent parse(String text) {
        if ( text.equals("-") ) {
            text = " " + text;
        }
        LiteTextComponent punctuationMark = new PunctuationMark(text);
        return punctuationMark;
    }
}

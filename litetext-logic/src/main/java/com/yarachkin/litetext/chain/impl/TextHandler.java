package com.yarachkin.litetext.chain.impl;

import com.yarachkin.litetext.chain.BaseHandler;
import com.yarachkin.litetext.chain.exception.ChainLiteTextException;
import com.yarachkin.litetext.composite.LiteTextComponent;
import com.yarachkin.litetext.exception.IOLiteTextException;
import com.yarachkin.litetext.reader.LiteTextFileReader;

public class TextHandler implements BaseHandler {
    private ParagraphHandler paragraphHandler = new ParagraphHandler();

    @Override
    public LiteTextComponent parse(String text) throws ChainLiteTextException {
        try {
            return paragraphHandler.parse(LiteTextFileReader.getInstance().readFromFile());
        } catch (IOLiteTextException e) {
            throw new ChainLiteTextException(e);
        }
    }
}

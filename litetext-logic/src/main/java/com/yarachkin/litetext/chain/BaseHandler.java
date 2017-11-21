package com.yarachkin.litetext.chain;

import com.yarachkin.litetext.chain.exception.ChainLiteTextException;
import com.yarachkin.litetext.composite.LiteTextComponent;

public interface BaseHandler {
    LiteTextComponent parse(String text) throws ChainLiteTextException;
}

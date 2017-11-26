package com.yarachkin.litetext.chain;

import com.yarachkin.litetext.composite.LiteTextComponent;
import com.yarachkin.litetext.exception.ChainLiteTextException;

public interface BaseHandler {
    LiteTextComponent parse(String text) throws ChainLiteTextException;
}

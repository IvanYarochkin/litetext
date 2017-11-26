package com.yarachkin.litetext.composite;

import com.yarachkin.litetext.exception.CompositeLiteTextException;

public interface LiteTextComponent {
    void add(LiteTextComponent component);

    LiteTextComponent getChild(int index) throws CompositeLiteTextException;

    void remove(LiteTextComponent component);

    void setFirstAdditionalText(String firstAdditionalText);

    void setLastAdditionalText(String secondAdditionalText);
}

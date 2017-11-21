package com.yarachkin.litetext.composite;

import com.yarachkin.litetext.composite.exception.CompositeLiteTextException;

public interface LiteTextComponent {
    void add(LiteTextComponent component);

    LiteTextComponent getChild(int index) throws CompositeLiteTextException;

    void remove(LiteTextComponent component);
}

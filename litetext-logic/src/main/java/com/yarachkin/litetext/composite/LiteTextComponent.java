package com.yarachkin.litetext.composite;

import com.yarachkin.litetext.exception.CompositeLiteTextException;

import java.util.ArrayList;

public interface LiteTextComponent {
    void add(LiteTextComponent component);

    LiteTextComponent getChild(int index) throws CompositeLiteTextException, CloneNotSupportedException;

    void remove(LiteTextComponent component) throws CompositeLiteTextException;

    void setFirstAdditionalText(String firstAdditionalText);

    void setLastAdditionalText(String secondAdditionalText);

    ArrayList<LiteTextComponent> getComponents() throws CompositeLiteTextException;
}

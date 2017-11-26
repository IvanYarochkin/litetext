package com.yarachkin.litetext.composite.impl;

import com.yarachkin.litetext.composite.LiteTextComponent;
import com.yarachkin.litetext.composite.exception.CompositeLiteTextException;

public class Expression implements LiteTextComponent {
    private String expression;

    public Expression(String expression) {
        this.expression = expression;
    }

    @Override
    public void add(LiteTextComponent component) {
    }

    @Override
    public LiteTextComponent getChild(int index) throws CompositeLiteTextException {
        throw new CompositeLiteTextException("Leaf " + this.getClass().getName() + " hasn't a child");
    }

    @Override
    public void remove(LiteTextComponent component) {
    }

    @Override
    public void setFirstAdditionalText(String firstAdditionalText) {
    }

    @Override
    public void setLastAdditionalText(String secondAdditionalText) {
    }

    @Override
    public String toString() {
        return " " + expression;
    }
}

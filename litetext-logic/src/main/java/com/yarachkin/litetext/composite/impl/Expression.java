package com.yarachkin.litetext.composite.impl;

import com.yarachkin.litetext.composite.LiteTextComponent;
import com.yarachkin.litetext.converter.ReversePolishConverter;
import com.yarachkin.litetext.exception.CompositeLiteTextException;
import com.yarachkin.litetext.interpreter.Client;

public class Expression implements LiteTextComponent {
    private double expression;
    private String firstAdditionalText = "";
    private String lastAdditionalText = "";

    public Expression(String expression) {
        this.expression = calculateExpression(expression);
    }

    private double calculateExpression(String expression) {
        Client client = new Client();
        ReversePolishConverter converter = new ReversePolishConverter();
        client.parse(converter.convertToReversePolish(expression));
        return client.calculate();
    }

    @Override
    public void add(LiteTextComponent component) {
    }

    @Override
    public LiteTextComponent getChild(int index) throws CompositeLiteTextException {
        throw new CompositeLiteTextException("Leaf " + this.getClass().getName() + " hasn't a child");
    }

    @Override
    public void remove(LiteTextComponent component) throws CompositeLiteTextException {
        throw new CompositeLiteTextException("Can't remove a Leaf " + this.getClass().getName());
    }

    @Override
    public void setFirstAdditionalText(String firstAdditionalText) {
        this.firstAdditionalText = firstAdditionalText;
    }

    @Override
    public void setLastAdditionalText(String lastAdditionalText) {
        this.lastAdditionalText = lastAdditionalText;
    }

    @Override
    public String toString() {
        return firstAdditionalText + expression + lastAdditionalText;
    }
}

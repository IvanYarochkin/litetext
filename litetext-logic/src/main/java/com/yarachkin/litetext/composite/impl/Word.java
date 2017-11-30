package com.yarachkin.litetext.composite.impl;

import com.yarachkin.litetext.composite.LiteTextComponent;
import com.yarachkin.litetext.exception.CompositeLiteTextException;

import java.util.ArrayList;

public class Word implements LiteTextComponent {
    private String word;
    private String firstAdditionalText = "";
    private String lastAdditionalText = "";

    public Word(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
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
    public ArrayList<LiteTextComponent> getComponents() throws CompositeLiteTextException {
        throw new CompositeLiteTextException("Can't use method getComponent in Leaf " + this.getClass().getName());
    }

    @Override
    public String toString() {
        return firstAdditionalText + word + lastAdditionalText;
    }
}

package com.yarachkin.litetext.composite.impl;

import com.yarachkin.litetext.composite.LiteTextComponent;

import java.util.ArrayList;

public class LiteTextComposite implements LiteTextComponent {
    private ArrayList<LiteTextComponent> components = new ArrayList<>();
    private String firstAdditionalText = "";
    private String lastAdditionalText = "";

    public ArrayList<LiteTextComponent> getComponents() {
        return components;
    }

    @Override
    public void add(LiteTextComponent component) {
        components.add(component);
    }

    @Override
    public LiteTextComponent getChild(int index) {
        return components.get(index);
    }

    @Override
    public void remove(LiteTextComponent component) {
        components.remove(component);
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
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < components.size(); i++) {
            stringBuilder.append(firstAdditionalText + components.get(i).toString() + lastAdditionalText);
        }
        return stringBuilder.toString();
    }
}

package com.yarachkin.litetext.composite.impl;

import com.yarachkin.litetext.composite.LiteTextComponent;
import com.yarachkin.litetext.exception.CompositeLiteTextException;

import java.util.ArrayList;

public class LiteTextComposite implements LiteTextComponent {
    private ArrayList<LiteTextComponent> components = new ArrayList<>();
    private String firstAdditionalText = "";
    private String lastAdditionalText = "";

    @Override
    public void add(LiteTextComponent component) {
        components.add(component);
    }

    @Override
    public LiteTextComponent getChild(int index) throws CompositeLiteTextException {
        if ( index > 0 || index < components.size() - 1 ) {
            return components.get(index);
        }

        throw new CompositeLiteTextException("Illegal index. Index = " + index);
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
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < components.size(); i++) {
            stringBuffer.append(firstAdditionalText + components.get(i).toString() + lastAdditionalText);
        }
        return stringBuffer.toString();
    }
}

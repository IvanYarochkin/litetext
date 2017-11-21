package com.yarachkin.litetext.composite.impl;

import com.yarachkin.litetext.composite.LiteTextComponent;
import com.yarachkin.litetext.composite.exception.CompositeLiteTextException;

import java.util.ArrayList;

public class LiteTextComposite implements LiteTextComponent {
    private ArrayList<LiteTextComponent> components = new ArrayList<>();

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
    public String toString() {
        String result = "";
        for (LiteTextComponent component : components) {
            result = result + component;
        }
        return result;
    }
}

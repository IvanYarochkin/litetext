package com.yarachkin.litetext.interpreter;

import java.util.ArrayDeque;

public class Context {
    private ArrayDeque<Double> values = new ArrayDeque<>();

    public Double popValue() {
        return values.pop();
    }

    public void pushValue(Double value) {
        values.push(value);
    }
}

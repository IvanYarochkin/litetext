package com.yarachkin.litetext.interpreter.impl;

import com.yarachkin.litetext.interpreter.Context;
import com.yarachkin.litetext.interpreter.MathExpression;

public class NonTerminalExpressionNumber implements MathExpression {
    private double number;

    public NonTerminalExpressionNumber(double number) {
        this.number = number;
    }

    @Override
    public void interpret(Context context) {
        context.pushValue(number);
    }
}

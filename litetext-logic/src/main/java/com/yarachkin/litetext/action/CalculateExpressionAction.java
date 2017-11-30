package com.yarachkin.litetext.action;

import com.yarachkin.litetext.converter.ReversePolishConverter;
import com.yarachkin.litetext.interpreter.Client;

public class CalculateExpressionAction {

    public static double calculate(String expression) {
        Client client = new Client();
        ReversePolishConverter converter = new ReversePolishConverter();
        client.parse(converter.convertToReversePolish(expression));
        return client.calculate();
    }
}

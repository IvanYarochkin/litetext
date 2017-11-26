package com.yarachkin.litetext.interpreter;

import com.yarachkin.litetext.exception.InterpreterLiteTextException;
import com.yarachkin.litetext.interpreter.impl.NonTerminalExpressionNumber;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;

import static com.yarachkin.litetext.interpreter.MathematicalSignSet.values;

public class Client {
    private static final Logger LOGGER = LogManager.getRootLogger();

    private ArrayDeque<String> signs = new ArrayDeque<>();
    private ArrayDeque<NonTerminalExpressionNumber> nonTerminalExpressionNumbers = new ArrayDeque<>();

    public void parse(String expression) {
        List<String> valuesAndSignsSet = Arrays.asList(expression.split("\\p{Blank}"));
        valuesAndSignsSet.forEach(lexeme -> {
            if ( !lexeme.isEmpty() ) {
                if ( isSign(lexeme) ) {
                    signs.addLast(lexeme);
                } else {
                    nonTerminalExpressionNumbers.push(new NonTerminalExpressionNumber(Double.parseDouble(lexeme)));
                }
            }
        });
    }

    public double calculate() {
        Context context = new Context();
        nonTerminalExpressionNumbers.forEach(number -> number.interpret(context));

        for (String sign : signs) {
            try {
                switch (MathematicalSignSet.getMathematicalSign(sign)) {
                    case PLUS: {
                        MathExpression plus = (actualContext) -> actualContext.pushValue(actualContext.popValue() + actualContext.popValue());
                        plus.interpret(context);
                        break;
                    }
                    case MINUS: {
                        MathExpression minus = (actualContext) -> actualContext.pushValue(-actualContext.popValue() + actualContext.popValue());
                        minus.interpret(context);
                        break;
                    }
                    case DIVIDE: {
                        MathExpression divide = (actualContext) -> actualContext.pushValue(1 / actualContext.popValue() * actualContext.popValue());
                        divide.interpret(context);
                        break;
                    }
                    case MULTIPLY: {
                        MathExpression multiply = (actualContext) -> actualContext.pushValue(actualContext.popValue() * actualContext.popValue());
                        multiply.interpret(context);
                        break;
                    }
                    case INCREMENT: {
                        MathExpression increment = (actualContext) -> actualContext.pushValue(actualContext.popValue() + 1);
                        increment.interpret(context);
                        break;
                    }
                    case DECREMENT: {
                        MathExpression decrement = (actualContext) -> actualContext.pushValue(actualContext.popValue() - 1);
                        decrement.interpret(context);
                        break;
                    }
                }
            } catch (InterpreterLiteTextException e) {
                LOGGER.log(Level.INFO, "Incorrect element " + sign);
            }
        }

        return context.popValue();
    }

    private boolean isSign(String lexeme) {
        for (MathematicalSignSet element : values()) {
            if ( element.toString().equals(lexeme) ) {
                return true;
            }
        }
        return false;
    }
}

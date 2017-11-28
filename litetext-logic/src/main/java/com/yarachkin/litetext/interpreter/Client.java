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

    private ArrayDeque<MathExpression> expressions = new ArrayDeque<>();

    public void parse(String expression) {
        List<String> valuesAndSignsSet = Arrays.asList(expression.split("\\p{Blank}"));
        valuesAndSignsSet.forEach(lexeme -> {
            if ( !lexeme.isEmpty() ) {
                if ( isSign(lexeme) ) {
                    try {
                        switch (MathematicalSignSet.getMathematicalSign(lexeme)) {
                            case PLUS: {
                                MathExpression plus = (actualContext) -> actualContext.pushValue(actualContext.popValue() + actualContext.popValue());
                                expressions.addLast(plus);
                                break;
                            }
                            case MINUS: {
                                MathExpression minus = (actualContext) -> actualContext.pushValue(-actualContext.popValue() + actualContext.popValue());
                                expressions.addLast(minus);
                                break;
                            }
                            case DIVIDE: {
                                MathExpression divide = (actualContext) -> actualContext.pushValue(1 / actualContext.popValue() * actualContext.popValue());
                                expressions.addLast(divide);
                                break;
                            }
                            case MULTIPLY: {
                                MathExpression multiply = (actualContext) -> actualContext.pushValue(actualContext.popValue() * actualContext.popValue());
                                expressions.addLast(multiply);
                                break;
                            }
                        }
                    } catch (InterpreterLiteTextException e) {
                        LOGGER.log(Level.INFO, "Incorrect element " + lexeme);
                    }
                } else {
                    expressions.addLast(new NonTerminalExpressionNumber(Double.parseDouble(lexeme)));
                }
            }
        });
    }

    public double calculate() {
        Context context = new Context();
        expressions.forEach(number -> number.interpret(context));
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

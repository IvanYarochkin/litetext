package com.yarachkin.litetext.interpreter;

import com.yarachkin.litetext.interpreter.impl.NonTerminalExpressionNumber;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;

import static com.yarachkin.litetext.interpreter.MathematicalSignSet.DECREMENT;
import static com.yarachkin.litetext.interpreter.MathematicalSignSet.DIVIDE;
import static com.yarachkin.litetext.interpreter.MathematicalSignSet.INCREMENT;
import static com.yarachkin.litetext.interpreter.MathematicalSignSet.MINUS;
import static com.yarachkin.litetext.interpreter.MathematicalSignSet.MULTIPLY;
import static com.yarachkin.litetext.interpreter.MathematicalSignSet.PLUS;
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

        signs.forEach(sign -> {
            if ( sign.equals(PLUS.toString()) ) {
                MathExpression plus = (actualContext) -> actualContext.pushValue(actualContext.popValue() + actualContext.popValue());
                plus.interpret(context);
            } else if ( sign.equals(MINUS.toString()) ) {
                MathExpression minus = (actualContext) -> actualContext.pushValue(actualContext.popValue() - actualContext.popValue());
                minus.interpret(context);
            } else if ( sign.equals(DIVIDE.toString()) ) {
                MathExpression divide = (actualContext) -> actualContext.pushValue(actualContext.popValue() / actualContext.popValue());
                divide.interpret(context);
            } else if ( sign.equals(MULTIPLY.toString()) ) {
                MathExpression multiply = (actualContext) -> actualContext.pushValue(actualContext.popValue() * actualContext.popValue());
                multiply.interpret(context);
            } else if ( sign.equals(INCREMENT.toString()) ) {
                MathExpression increment = (actualContext) -> actualContext.pushValue(actualContext.popValue() + 1);
                increment.interpret(context);
            } else if ( sign.equals(DECREMENT.toString()) ) {
                MathExpression decrement = (actualContext) -> actualContext.pushValue(actualContext.popValue() - 1);
                decrement.interpret(context);
            } else {
                LOGGER.log(Level.INFO, "Incorrect element " + sign);
            }
        });
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

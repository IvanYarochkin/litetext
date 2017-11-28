package com.yarachkin.litetext.converter;

import java.util.ArrayDeque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.yarachkin.litetext.converter.VariableStore.acquireI;
import static com.yarachkin.litetext.converter.VariableStore.acquireJ;
import static com.yarachkin.litetext.converter.VariableStore.decrementI;
import static com.yarachkin.litetext.converter.VariableStore.decrementJ;
import static com.yarachkin.litetext.converter.VariableStore.incrementI;
import static com.yarachkin.litetext.converter.VariableStore.incrementJ;

public class ReversePolishConverter {
    private static final Pattern NUMBER_AND_OPERATOR_PATTERN = Pattern.compile("((\\+{2}|-{2})?(i|j)(\\+{2}|-{2})?)|" +
            "(-i|-j)|((?<=(\\+|-|\\*|/|\\())(-?\\d+(\\.\\d+)?))|\\d+|(\\+{2}|-{2}|\\+|-|/|\\*|\\(|\\))");
    private static final Pattern NUMBER_PATTERN = Pattern.compile("(-?\\d+(\\.\\d+)?)");
    private static final Pattern OPERATOR_PATTERN = Pattern.compile("(\\+{2}|-{2}|\\+|-|/|\\*|\\(|\\))");

    private ArrayDeque<String> operatorsAndValues = new ArrayDeque<>();
    private ArrayDeque<String> operator = new ArrayDeque<>();
    private ArrayDeque<String> reversePolish = new ArrayDeque<>();

    public String convertToReversePolish(String expression) {
        if ( expression == null ) {
            return null;
        }

        fillOperatorsAndValues(expression);

        StringBuffer result = new StringBuffer();
        operator.push("#");
        operatorsAndValues.forEach(element -> {
            if ( element.contains("i") || element.contains("j") ) {
                pushIfVariable(element);
            } else if ( isNumber(element) ) {
                reversePolish.push(element);
            } else if ( isOperator(element) ) {
                chooseOperator(element);
            }
        });

        while (!reversePolish.isEmpty()) {
            result.append(reversePolish.pollLast() + " ");
        }

        while (!operator.peek().equals("#")) {
            result.append(operator.pop() + " ");
        }

        return result.toString();
    }

    private void pushIfVariable(String element) {
        if ( element.equals("++i") || element.equals("i++") || element.equals("++j") || element.equals("j++") ) {
            reversePolish.push(Double.toString(element.contains("i") ? incrementI() : incrementJ()));
        } else if ( element.equals("--i") || element.equals("i--") || element.equals("--j") || element.equals("j--") ) {
            reversePolish.push(Double.toString(element.contains("i") ? decrementI() : decrementJ()));
        } else if ( element.equals("i") || element.equals("j") ) {
            reversePolish.push(Double.toString(element.contains("i") ? acquireI() : acquireJ()));
        } else if ( element.equals("-i") || element.equals("-j") ) {
            reversePolish.push(Double.toString(element.contains("i") ? -acquireI() : -acquireJ()));
        }
    }

    private void chooseOperator(String element) {
        switch (element) {
            case "(": {
                operator.push(element);
                break;
            }
            case ")": {
                while (!operator.peek().equals("(")) {
                    reversePolish.push(operator.pop());
                }
                operator.pop();
                break;
            }
            case "+":
            case "-": {
                pushIfPlusOrMinus(element);
                break;
            }
            case "*":
            case "/": {
                pushIfMultiplyOrDivide(element);
                break;
            }
        }
    }

    private void pushIfPlusOrMinus(String element) {
        if ( operator.peek().equals("(") ) {
            operator.push(element);
        } else {
            while (!operator.peek().equals("#") && !operator.peek().equals("(")) {
                reversePolish.push(operator.pop());
            }
            operator.push(element);
        }
    }

    private void pushIfMultiplyOrDivide(String element) {
        if ( operator.peek().equals("(") ) {
            operator.push(element);
        } else {
            while (!operator.peek().equals("#") && !operator.peek().equals("+") &&
                    !operator.peek().equals("-") && !operator.peek().equals("(")) {
                reversePolish.push(operator.pop());
            }
            operator.push(element);
        }
    }

    private void fillOperatorsAndValues(String expression) {
        Matcher matcher = NUMBER_AND_OPERATOR_PATTERN.matcher(expression);
        while (matcher.find()) {
            operatorsAndValues.addLast(matcher.group());
        }
    }

    private boolean isNumber(String sourceText) {
        Matcher numberMatcher = NUMBER_PATTERN.matcher(sourceText);
        if ( numberMatcher.matches() ) {
            return true;
        }
        return false;
    }

    private boolean isOperator(String sourceText) {
        Matcher operatorMatcher = OPERATOR_PATTERN.matcher(sourceText);
        if ( operatorMatcher.matches() ) {
            return true;
        }
        return false;
    }
}

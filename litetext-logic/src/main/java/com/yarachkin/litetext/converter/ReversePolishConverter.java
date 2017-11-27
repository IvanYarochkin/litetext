package com.yarachkin.litetext.converter;

import java.util.ArrayDeque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReversePolishConverter {
    private static final Pattern NUMBER_AND_OPERATOR_PATTERN = Pattern.compile("(i|j|-i|-j)|((?<=(\\+|-|\\*|/|\\())(-?\\d+(\\.\\d+)?))|\\d+|(\\+{2}|-{2}|\\+|-|/|\\*|\\(|\\))");
    private static final Pattern NUMBER_PATTERN = Pattern.compile("(-?\\d+(\\.\\d+)?)");
    private static final Pattern OPERATOR_PATTERN = Pattern.compile("(\\+{2}|-{2}|\\+|-|/|\\*|\\(|\\))");

    private static double i;
    private static double j;

    private ArrayDeque<String> operatorsAndValues = new ArrayDeque<>();

    public static void initializeDefaultValues(double iValue, double jValue) {
        i = iValue;
        j = jValue;
    }

    public String convertToReversePolish(String expression) {
        if ( expression == null ) {
            return null;
        }

        fillOperatorsAndValues(expression);

        StringBuffer result = new StringBuffer();
        ArrayDeque<String> operator = new ArrayDeque<>();
        ArrayDeque<String> values = new ArrayDeque<>();
        operator.push("#");
        operatorsAndValues.forEach(element -> {
            if ( element.equals("i") || element.equals("-i")) {
                values.push(Double.toString(element.equals("i") ? i : -i));
            } else if ( element.equals("j") || element.equals("-j")) {
                values.push(Double.toString(element.equals("j") ? j : -j));

            } else if ( isNumber(element) ) {
                values.push(element);
            } else if ( isOperator(element) ) {
                switch (element) {
                    case "(": {
                        operator.push(element);
                        break;
                    }
                    case ")": {
                        while (!operator.peek().equals("(")) {
                            values.push(operator.pop());
                        }
                        operator.pop();
                        break;
                    }
                    case "+":
                    case "-": {
                        if ( operator.peek().equals("(") ) {
                            operator.push(element);
                        } else {
                            while (!operator.peek().equals("#") && !operator.peek().equals("(")) {
                                values.push(operator.pop());
                            }
                            operator.push(element);
                        }
                        break;
                    }
                    case "*":
                    case "/": {
                        if ( operator.peek().equals("(") ) {
                            operator.push(element);
                        } else {
                            while (!operator.peek().equals("#") && !operator.peek().equals("+") &&
                                    !operator.peek().equals("-") && !operator.peek().equals("(")) {
                                values.push(operator.pop());
                            }
                            operator.push(element);
                        }
                        break;
                    }
                    case "++":
                    case "--": {
                        if ( operator.peek().equals("(") )
                            operator.push(element);
                        else {
                            while (!operator.peek().equals("#") && !operator.peek().equals("+") &&
                                    !operator.peek().equals("-") && !operator.peek().equals("(") &&
                                    !operator.peek().equals("*") && !operator.peek().equals("/")) {
                                values.push(operator.pop());
                            }
                            operator.push(element);
                        }
                        break;
                    }
                }
            }
        });

        while (!values.isEmpty()) {
            result.append(values.pollLast() + " ");
        }

        while (!operator.peek().equals("#")) {
            result.append(operator.pop() + " ");
        }

        return result.toString();
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

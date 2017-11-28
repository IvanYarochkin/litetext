package com.yarachkin.litetext.interpreter;

import com.yarachkin.litetext.exception.InterpreterLiteTextException;

public enum MathematicalSignSet {
    PLUS {
        @Override
        public String toString() {
            return "+";
        }
    }, MINUS {
        @Override
        public String toString() {
            return "-";
        }
    }, MULTIPLY {
        @Override
        public String toString() {
            return "*";
        }
    }, DIVIDE {
        @Override
        public String toString() {
            return "/";
        }
    };

    public static MathematicalSignSet getMathematicalSign(String sign) throws InterpreterLiteTextException {
        for (MathematicalSignSet element : MathematicalSignSet.values()) {
            if ( element.toString().equals(sign) ) {
                return element;
            }
        }

        throw new InterpreterLiteTextException("Incorrect sign: " + sign);
    }
}

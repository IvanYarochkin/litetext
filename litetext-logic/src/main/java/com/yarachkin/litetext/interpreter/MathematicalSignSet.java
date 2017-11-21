package com.yarachkin.litetext.interpreter;

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
    }, INCREMENT {
        @Override
        public String toString() {
            return "++";
        }
    }, DECREMENT {
        @Override
        public String toString() {
            return "--";
        }
    },
}

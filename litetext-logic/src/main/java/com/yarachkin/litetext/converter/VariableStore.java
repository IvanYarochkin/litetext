package com.yarachkin.litetext.converter;

public class VariableStore {
    private static double i;
    private static double j;

    public static void initializeDefaultValues(double iValue, double jValue) {
        i = iValue;
        j = jValue;
    }

    public static double acquireI() {
        return i;
    }

    public static double acquireJ() {
        return j;
    }

    public static double incrementI() {
        return ++i;
    }

    public static double incrementJ() {
        return ++j;
    }

    public static double decrementI() {
        return --i;
    }

    public static double decrementJ() {
        return --j;
    }
}

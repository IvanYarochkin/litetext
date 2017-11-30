package com.yarachkin.litetext.action;

import com.yarachkin.litetext.converter.VariableStore;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CalculateExpressionActionTest {
    private String expression;

    @BeforeMethod
    public void setUp() throws Exception {
        VariableStore.initializeDefaultValues(1, 1);
        expression = "2 + 2 * (3 - 5) / i";
    }

    @Test
    public void testCalculate() throws Exception {
        assertEquals(CalculateExpressionAction.calculate(expression), -2.0);
    }

}
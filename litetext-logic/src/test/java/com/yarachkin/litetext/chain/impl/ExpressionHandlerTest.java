package com.yarachkin.litetext.chain.impl;

import com.yarachkin.litetext.converter.VariableStore;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ExpressionHandlerTest {
    private String testText;
    private ExpressionHandler expressionHandler;

    @BeforeMethod
    public void setUp() throws Exception {
        VariableStore.initializeDefaultValues(0, 0);
        testText = "(2+2*2)/3";
        expressionHandler = new ExpressionHandler();
    }

    @Test
    public void testParse() throws Exception {
        assertEquals(expressionHandler.parse(testText).toString(), "2.0");
    }
}
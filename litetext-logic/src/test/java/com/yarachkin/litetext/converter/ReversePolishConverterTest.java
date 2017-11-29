package com.yarachkin.litetext.converter;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class ReversePolishConverterTest {
    private String expression;
    private ReversePolishConverter converter;

    @BeforeMethod
    public void setUp() throws Exception {
        VariableStore.initializeDefaultValues(1, 3);
        expression = "(2 + 3) * 4 - i++ + --j";
        converter = new ReversePolishConverter();
    }

    @Test
    public void convertToReversePolishTest() throws Exception {
        assertEquals(converter.convertToReversePolish(expression), "2 3 + 4 * 2.0 - 2.0 + ");
    }

}
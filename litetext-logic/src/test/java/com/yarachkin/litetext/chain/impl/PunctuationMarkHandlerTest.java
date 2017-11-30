package com.yarachkin.litetext.chain.impl;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PunctuationMarkHandlerTest {
    private String testText;
    private PunctuationMarkHandler punctuationMarkHandler;

    @BeforeMethod
    public void setUp() throws Exception {
        testText = "?!";
        punctuationMarkHandler = new PunctuationMarkHandler();
    }

    @Test
    public void testParse() throws Exception {
        assertEquals(punctuationMarkHandler.parse(testText).toString(), testText);
    }
}
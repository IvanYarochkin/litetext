package com.yarachkin.litetext.chain.impl;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LexemeHandlerTest {
    private String testText;
    private LexemeHandler lexemeHandler;

    @BeforeMethod
    public void setUp() throws Exception {
        testText = "Lexeme!";
        lexemeHandler = new LexemeHandler();
    }

    @Test
    public void testParse() throws Exception {
        assertEquals(lexemeHandler.parse(testText).toString(), testText);
    }
}
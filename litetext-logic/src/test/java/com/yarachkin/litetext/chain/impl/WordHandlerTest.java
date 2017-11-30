package com.yarachkin.litetext.chain.impl;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class WordHandlerTest {
    private String testText;
    private WordHandler wordHandler;

    @BeforeMethod
    public void setUp() throws Exception {
        testText = "Java";
        wordHandler = new WordHandler();
    }

    @Test
    public void testParse() throws Exception {

        assertEquals(wordHandler.parse(testText).toString(), testText);
    }
}
package com.yarachkin.litetext.chain.impl;

import com.yarachkin.litetext.converter.VariableStore;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class SentenceHandlerTest {
    private String testText;
    private String result;
    private SentenceHandler sentenceHandler;

    @BeforeMethod
    public void setUp() throws Exception {
        VariableStore.initializeDefaultValues(2, 3);
        testText = "It has survived - not only (five) centuries, but also the leap into 13+i-- electronic typesetting," +
                " remaining 3+5 essentially 6+9*(3-4) unchanged.";

        result = " It has survived - not only (five) centuries, but also the leap into 14.0 electronic typesetting," +
                " remaining 8.0 essentially -3.0 unchanged.";
        sentenceHandler = new SentenceHandler();
    }

    @Test
    public void testParse() throws Exception {
        assertEquals(sentenceHandler.parse(testText).toString(), result);
    }
}

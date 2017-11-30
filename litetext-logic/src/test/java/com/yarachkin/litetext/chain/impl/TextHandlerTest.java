package com.yarachkin.litetext.chain.impl;

import com.yarachkin.litetext.converter.VariableStore;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TextHandlerTest {
    private String testText;
    private String result;
    private TextHandler textHandler;

    @BeforeMethod
    public void setUp() throws Exception {
        VariableStore.initializeDefaultValues(2, 3);

        testText = "\tIt has survived - not only (five) centuries, but also the leap into 13+i-- electronic typesetting," +
                " remaining 3+5 essentially 6+9*(3-4) unchanged.\n\n" +
                "\tIt was popularised in the 5*(1*2*(3*(4*(5- --j+4)-3)-2)-1) with the release of Letraset sheets containing " +
                "Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions" +
                " of Lorem Ipsum.";

        result = "\t It has survived - not only (five) centuries, but also the leap into 14.0 electronic typesetting," +
                " remaining 8.0 essentially -3.0 unchanged.\n\n\t It was popularised in the 725.0" +
                " with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing" +
                " software like Aldus PageMaker including versions of Lorem Ipsum.\n\n";
        textHandler = new TextHandler();
    }

    @Test
    public void testParse() throws Exception {
        assertEquals(textHandler.parse(testText).toString(), result);
    }
}
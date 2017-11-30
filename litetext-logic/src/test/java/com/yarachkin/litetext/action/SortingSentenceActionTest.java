package com.yarachkin.litetext.action;

import com.yarachkin.litetext.composite.LiteTextComponent;
import com.yarachkin.litetext.converter.VariableStore;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.TreeMap;

import static org.testng.Assert.assertEquals;

public class SortingSentenceActionTest {
    private String testText;
    private ArrayList<Integer> expectedLexemes;

    @BeforeMethod
    public void setUp() throws Exception {
        VariableStore.initializeDefaultValues(0, 0);
        testText = "\tIt has survived - not only (five) centuries, but also the leap into 13+i-- electronic typesetting," +
                " remaining 3+5 essentially 6+9*(3-4) unchanged. It was popularised in the 5*(1*2*(3*(4*(5- --j+4)-3)-2)-1)" +
                " with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing" +
                " software like Aldus PageMaker including versions of Lorem Ipsum.";

        expectedLexemes = new ArrayList<>();
    }

    @Test
    public void testPrintSentencesInAscendingOrder() throws Exception {
        TreeMap<Integer, LiteTextComponent> sentences = SortingSentenceAction.printSentencesInAscendingOrder(testText);
        ArrayList<Integer> actualLexemes = new ArrayList<>();
        sentences.entrySet().forEach(element -> actualLexemes.add(element.getKey()));
        expectedLexemes.add(17);
        expectedLexemes.add(30);
        assertEquals(actualLexemes, expectedLexemes);
    }
}
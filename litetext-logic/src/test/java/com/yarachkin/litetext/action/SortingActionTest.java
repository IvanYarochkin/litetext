package com.yarachkin.litetext.action;

import com.yarachkin.litetext.chain.impl.TextHandler;
import com.yarachkin.litetext.composite.LiteTextComponent;
import com.yarachkin.litetext.filehelper.LiteTextFileHelper;
import com.yarachkin.litetext.reader.LiteTextFileReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.TreeMap;

import static org.testng.Assert.assertEquals;

public class SortingActionTest {
    private String filePath;
    private File testFile;
    private String testText;
    private ArrayList<Integer> expectedLexemes;
    private TextHandler textHandler;

    @BeforeMethod
    public void setUp() throws Exception {
        testFile = File.createTempFile("litetext_order_action_test", "txt");
        filePath = testFile.getAbsolutePath();

        testText = "\tIt has survived - not only (five) centuries, but also the leap into 13+i-- electronic typesetting," +
                " remaining 3+5 essentially 6+9*(3-4) unchanged. It was popularised in the 5*(1*2*(3*(4*(5- --j+4)-3)-2)-1)" +
                " with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing" +
                " software like Aldus PageMaker including versions of Lorem Ipsum.";

        try (FileWriter fileWriter = new FileWriter(filePath);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(testText);

        }
        LiteTextFileHelper.getInstance().setFilePath(filePath);

        expectedLexemes = new ArrayList<>();
        textHandler = new TextHandler();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        testFile.delete();
        LiteTextFileHelper.getInstance().setDefaultPropertyPath();
    }

    @Test
    public void testPrintSentencesInAscendingOrder() throws Exception {
        LiteTextComponent component = textHandler.parse(LiteTextFileReader.getInstance().readFromFile());
        TreeMap<Integer, LiteTextComponent> sentences = SortingAction.printSentencesInAscendingOrder(component);
        ArrayList<Integer> actualLexemes = new ArrayList<>();
        sentences.entrySet().forEach(element -> actualLexemes.add(element.getKey()));
        expectedLexemes.add(17);
        expectedLexemes.add(30);
        assertEquals(actualLexemes, expectedLexemes);
    }
}
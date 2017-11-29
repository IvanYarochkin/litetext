package com.yarachkin.litetext.chain.impl;

import com.yarachkin.litetext.converter.VariableStore;
import com.yarachkin.litetext.filehelper.LiteTextFileHelper;
import com.yarachkin.litetext.reader.LiteTextFileReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import static org.testng.AssertJUnit.assertEquals;

public class SentenceHandlerTest {
    private String filePath;
    private File testFile;
    private String testText;
    private String result;

    @BeforeMethod
    public void setUp() throws Exception {
        VariableStore.initializeDefaultValues(2, 3);
        testFile = File.createTempFile("litetext_sentence_handler_test", "txt");
        filePath = testFile.getAbsolutePath();

        testText = "It has survived - not only (five) centuries, but also the leap into 13+i-- electronic typesetting," +
                " remaining 3+5 essentially 6+9*(3-4) unchanged.";

        result = " It has survived - not only (five) centuries, but also the leap into 14.0 electronic typesetting," +
                " remaining 8.0 essentially -3.0 unchanged.";
        try (FileWriter fileWriter = new FileWriter(filePath);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(testText);
        }
        LiteTextFileHelper.getInstance().setFilePath(filePath);
    }

    @AfterMethod
    public void tearDown() throws Exception {
        testFile.delete();
        LiteTextFileHelper.getInstance().setDefaultPropertyPath();
    }

    @Test
    public void testParse() throws Exception {
        SentenceHandler sentenceHandler = new SentenceHandler();
        assertEquals(sentenceHandler.parse(LiteTextFileReader.getInstance().readFromFile()).toString(), result);
    }
}

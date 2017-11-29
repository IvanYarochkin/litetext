package com.yarachkin.litetext.chain.impl;

import com.yarachkin.litetext.filehelper.LiteTextFileHelper;
import com.yarachkin.litetext.reader.LiteTextFileReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import static org.testng.Assert.assertEquals;

public class PunctuationMarkHandlerTest {
    private String filePath;
    private File testFile;
    private String testText;

    @BeforeMethod
    public void setUp() throws Exception {
        testFile = File.createTempFile("litetext_punctuation_mark_handler_test", "txt");
        filePath = testFile.getAbsolutePath();

        testText = "?!";

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
        PunctuationMarkHandler punctuationMarkHandler = new PunctuationMarkHandler();
        assertEquals(punctuationMarkHandler.parse(LiteTextFileReader.getInstance().readFromFile()).toString(), testText);
    }
}
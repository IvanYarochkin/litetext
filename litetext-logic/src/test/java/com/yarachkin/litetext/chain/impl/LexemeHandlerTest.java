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

public class LexemeHandlerTest {
    private String filePath;
    private File testFile;
    private String testText;

    @BeforeMethod
    public void setUp() throws Exception {
        testFile = File.createTempFile("litetext_lexeme_handler_test", "txt");
        filePath = testFile.getAbsolutePath();

        testText = "Lexeme!";

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
        LexemeHandler lexemeHandler = new LexemeHandler();
        assertEquals(lexemeHandler.parse(LiteTextFileReader.getInstance().readFromFile()).toString(), testText);
    }
}
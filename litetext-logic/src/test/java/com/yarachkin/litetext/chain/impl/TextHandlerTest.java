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

import static org.testng.Assert.assertEquals;

public class TextHandlerTest {
    private String filePath;
    private File testFile;
    private String testText;
    private String result;

    @BeforeMethod
    public void setUp() throws Exception {
        VariableStore.initializeDefaultValues(2, 3);
        testFile = File.createTempFile("litetext_text_handler_test", "txt");
        filePath = testFile.getAbsolutePath();

        testText = "\tIt has survived - not only (five) centuries, but also the leap into 13+i-- electronic typesetting," +
                " remaining 3+5 essentially 6+9*(3-4) unchanged.\n\n" +
                "\tIt was popularised in the 5*(1*2*(3*(4*(5- --j+4)-3)-2)-1) with the release of Letraset sheets containing " +
                "Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions" +
                " of Lorem Ipsum.";

        result = "\t It has survived - not only (five) centuries, but also the leap into 14.0 electronic typesetting," +
                " remaining 8.0 essentially -3.0 unchanged.\n\n\t It was popularised in the 725.0" +
                " with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing" +
                " software like Aldus PageMaker including versions of Lorem Ipsum.\n\n";
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
        TextHandler textHandler = new TextHandler();
        assertEquals(textHandler.parse(LiteTextFileReader.getInstance().readFromFile()).toString(), result);
    }
}
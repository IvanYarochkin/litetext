package com.yarachkin.litetext.action;

import com.yarachkin.litetext.converter.VariableStore;
import com.yarachkin.litetext.filehelper.LiteTextFileHelper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import static org.testng.Assert.assertEquals;

public class RemoveLexemeActionTest {
    private File testFile;
    private String testText;
    private String result;
    private int lexemeSize;
    private char firstSymbol;

    @BeforeMethod
    public void setUp() throws Exception {
        VariableStore.initializeDefaultValues(0, 0);
        testFile = File.createTempFile("litetext_remove_action_test", "txt");
        String filePath = testFile.getAbsolutePath();

        testText = "\tIt has survived - not only (five) centuries, but also the leap into 13+i-- electronic typesetting," +
                " remaining 3+5 essentially 6+9*(3-4) unchanged. It was popularised in the 5*(1*2*(3*(4*(5- --j+4)-3)-2)-1)" +
                " with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing" +
                " software like Aldus PageMaker including versions of Lorem Ipsum.";

        result = "\t has survived - not only (five) centuries, but also the leap into 12.0 electronic typesetting," +
                " remaining 8.0 essentially -3.0 unchanged. was popularised in the 1085.0" +
                " with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing" +
                " software like Aldus PageMaker including versions of Lorem Ipsum.\n\n";

        try (FileWriter fileWriter = new FileWriter(filePath);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(testText);

        }
        LiteTextFileHelper.getInstance().setFilePath(filePath);

        lexemeSize = 2;
        firstSymbol = 'I';
    }

    @AfterMethod
    public void tearDown() throws Exception {
        testFile.delete();
        LiteTextFileHelper.getInstance().setDefaultPropertyPath();
    }

    @Test
    public void testRemoveLexeme() throws Exception {
        assertEquals(RemoveLexemeAction.removeLexeme(testText, lexemeSize, firstSymbol).toString(), result);
    }

}
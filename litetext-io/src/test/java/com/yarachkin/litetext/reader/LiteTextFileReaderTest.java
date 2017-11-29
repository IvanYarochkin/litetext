package com.yarachkin.litetext.reader;

import com.yarachkin.litetext.filehelper.LiteTextFileHelper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertFalse;

public class LiteTextFileReaderTest {
    private String filePath;
    private String testText;
    private File testFile;

    @BeforeMethod
    public void setUp() throws Exception {
        testFile = File.createTempFile("lite_tetragon_reader", "txt");
        filePath = testFile.getAbsolutePath();

        testText = "It has survived - not only (five) centuries, but also the leap into 13+i-- electronic " +
                "typesetting, remaining 3+5 essentially 6+9*(3-4) unchanged. It was popularised in the " +
                "5*(1*2*(3*(4*(5- --j+4)-3)-2)-1) with the release of Letraset sheets containing Lorem Ipsum passages, " +
                "and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";

        try (FileWriter fileWriter = new FileWriter(filePath);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(testText);

        }
        LiteTextFileHelper.getInstance().setFilePath(filePath);

    }

    @AfterMethod
    public void tearDown() throws Exception {
        Files.deleteIfExists(Paths.get("src/test/resources/testFile.txt"));
        LiteTextFileHelper.getInstance().setDefaultPropertyPath();
        testFile.delete();
    }

    @Test
    public void readFromFileTest() throws Exception {
        assertEquals(LiteTextFileReader.getInstance().readFromFile(), testText);
    }

    @Test
    public void createFileIfNotExistsTest() throws Exception {
        LiteTextFileHelper.getInstance().setFilePath("src/test/resources/testFile.txt");
        LiteTextFileReader.getInstance().createFileIfNotExists();
        assertFalse(Files.notExists(Paths.get("src/test/resources/testFile.txt")));
    }

}
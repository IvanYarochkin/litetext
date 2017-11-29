package com.yarachkin.litetext.filehelper;

import com.yarachkin.litetext.exception.IOLiteTextException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static org.testng.Assert.assertEquals;

public class LiteTextFileHelperTest {
    private String filePath;
    private Properties properties;

    @BeforeMethod
    public void setUp() throws Exception {
        properties = new Properties();
        properties.load(LiteTextFileHelperTest.class.getResourceAsStream("/file_helper_test.properties"));
        LiteTextFileHelper.getInstance().loadProperties(properties);
        filePath = properties.getProperty("file.path") + properties.getProperty("file.name");

        Files.createFile(Paths.get(filePath));

    }

    @AfterMethod
    public void tearDown() throws Exception {
        Files.delete(Paths.get(filePath));
        LiteTextFileHelper.getInstance().setDefaultPropertyPath();
    }

    @Test
    public void acquireFilePathTest() throws IOLiteTextException {
        assertEquals(LiteTextFileHelper.getInstance().acquireFilePath(), filePath);
    }

    @Test
    public void loadPropertiesTest() throws IOLiteTextException {
        LiteTextFileHelper.getInstance().loadProperties(properties);
        assertEquals(LiteTextFileHelper.getInstance().acquireFilePath(), filePath);
    }

    @Test
    public void setDefaultPropertyPathTest() throws IOLiteTextException, IOException {
        LiteTextFileHelper.getInstance().setDefaultPropertyPath();
        Properties defaultProperties = new Properties();
        defaultProperties.load(LiteTextFileHelperTest.class.getResourceAsStream("/file.properties"));
        String defaultPath = defaultProperties.getProperty("file.path") + defaultProperties.getProperty("file.name");
        assertEquals(LiteTextFileHelper.getInstance().acquireFilePath(), defaultPath);
    }

    @Test
    public void setPropertyPathTest() throws IOLiteTextException {
        LiteTextFileHelper.getInstance().setPropertyPath("/file_helper_test.properties");

        assertEquals(LiteTextFileHelper.getInstance().acquireFilePath(), filePath);
    }
}
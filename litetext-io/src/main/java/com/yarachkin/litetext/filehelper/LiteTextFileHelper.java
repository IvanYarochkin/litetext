package com.yarachkin.litetext.filehelper;

import com.yarachkin.litetext.exception.IOLiteTextException;

import java.io.IOException;
import java.util.Properties;

public class LiteTextFileHelper {
    private static final String FILE_PROPERTIES = "/file.properties";
    private static final String FILE_DIRECTORY = "file.path";
    private static final String FILE_NAME = "file.name";

    private String propertyPath;
    private Properties properties;
    private String filePath = "";

    private LiteTextFileHelper() {
        propertyPath = FILE_PROPERTIES;
    }

    private static class SingletonHolder {
        private static final LiteTextFileHelper INSTANCE = new LiteTextFileHelper();
    }

    public static LiteTextFileHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void setPropertyPath(String propertyPath) throws IOLiteTextException {
        this.propertyPath = propertyPath;
        filePath = "";
        loadProperties();
    }

    public void setDefaultPropertyPath() throws IOLiteTextException {
        this.propertyPath = FILE_PROPERTIES;
        filePath = "";
        loadProperties();
    }

    public String acquireFilePath() throws IOLiteTextException {
        if ( !filePath.isEmpty() ) {
            return filePath;
        }

        if ( properties == null ) {
            loadProperties();
        }
        return properties.getProperty(FILE_DIRECTORY) + properties.getProperty(FILE_NAME);
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void loadProperties(Properties properties) {
        filePath = "";
        this.properties = properties;
    }

    private void loadProperties() throws IOLiteTextException {
        try {
            properties = new Properties();
            properties.load(LiteTextFileHelper.class.getResourceAsStream(propertyPath));
        } catch (IOException e) {
            throw new IOLiteTextException("Unable to open " + FILE_PROPERTIES, e);
        }
    }
}

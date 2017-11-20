package com.yarachkin.litetext.filehelper;

import com.yarachkin.litetext.exception.IOLitetextException;

import java.io.IOException;
import java.util.Properties;

public class LitetextFileHelper {
    private static final String FILE_PROPERTIES = "/file.properties";
    private static final String FILE_DIRECTORY = "file.path";
    private static final String FILE_NAME = "file.name";

    private String propertyPath;
    private Properties properties;

    private LitetextFileHelper() {
        propertyPath = FILE_PROPERTIES;
    }

    private static class SingletonHolder {
        private static final LitetextFileHelper INSTANCE = new LitetextFileHelper();
    }

    public static LitetextFileHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void setPropertyPath(String propertyPath) throws IOLitetextException {
        this.propertyPath = propertyPath;
        loadProperties();
    }

    public void setDefaultPropertyPath() throws IOLitetextException {
        this.propertyPath = FILE_PROPERTIES;
        loadProperties();
    }

    public String acquireFilePath() throws IOLitetextException {
        if ( properties == null ) {
            loadProperties();
        }
        return properties.getProperty(FILE_DIRECTORY) + properties.getProperty(FILE_NAME);
    }

    public void loadProperties(Properties properties) {
        this.properties = properties;
    }

    private void loadProperties() throws IOLitetextException {
        try {
            properties = new Properties();
            properties.load(LitetextFileHelper.class.getResourceAsStream(propertyPath));
        } catch (IOException e) {
            throw new IOLitetextException("Unable to open " + FILE_PROPERTIES, e);
        }
    }
}

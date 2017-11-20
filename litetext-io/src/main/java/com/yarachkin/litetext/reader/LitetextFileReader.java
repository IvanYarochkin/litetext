package com.yarachkin.litetext.reader;

import com.yarachkin.litetext.exception.IOLitetextException;
import com.yarachkin.litetext.filehelper.LitetextFileHelper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LitetextFileReader {

    private static final Logger LOGGER = LogManager.getRootLogger();
    private static final String FILE_MESSAGE = "File ";

    private LitetextFileReader() {

    }

    private static class SingletonHolder {
        private static final LitetextFileReader INSTANCE = new LitetextFileReader();
    }

    public static LitetextFileReader getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public String readFromFile() throws IOLitetextException {
        createFileIfNotExists();
        String text = "";
        try (FileReader reader = new FileReader(LitetextFileHelper.getInstance().acquireFilePath())) {
            int symbol;
            while ((symbol = reader.read()) != -1) {
                text = text + (char) symbol;
            }

        } catch (IOException e) {
            throw new IOLitetextException("Read error", e);
        }
        return text;
    }

    public void createFileIfNotExists() throws IOLitetextException {
        String filePath = LitetextFileHelper.getInstance().acquireFilePath();
        try {
            Path cachePath = Paths.get(filePath);

            if ( Files.notExists(cachePath) ) {
                LOGGER.log(Level.INFO, FILE_MESSAGE + filePath + " does not exist.");
                Files.createFile(cachePath);
                LOGGER.log(Level.INFO, FILE_MESSAGE + filePath + " created.");
            }
        } catch (IOException e) {
            throw new IOLitetextException("Unable to create " + filePath, e);
        }
    }
}

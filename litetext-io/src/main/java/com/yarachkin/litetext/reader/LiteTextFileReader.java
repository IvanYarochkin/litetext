package com.yarachkin.litetext.reader;

import com.yarachkin.litetext.exception.IOLiteTextException;
import com.yarachkin.litetext.filehelper.LiteTextFileHelper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LiteTextFileReader {

    private static final Logger LOGGER = LogManager.getRootLogger();
    private static final String FILE_MESSAGE = "File ";

    private LiteTextFileReader() {

    }

    private static class SingletonHolder {
        private static final LiteTextFileReader INSTANCE = new LiteTextFileReader();
    }

    public static LiteTextFileReader getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public String readFromFile() throws IOLiteTextException {
        createFileIfNotExists();
        String text = "";
        try (FileReader reader = new FileReader(LiteTextFileHelper.getInstance().acquireFilePath())) {
            int symbol;
            while ((symbol = reader.read()) != -1) {
                text = text + (char) symbol;
            }

        } catch (IOException e) {
            throw new IOLiteTextException("Read error", e);
        }
        return text;
    }

    public void createFileIfNotExists() throws IOLiteTextException {
        String filePath = LiteTextFileHelper.getInstance().acquireFilePath();
        try {
            Path cachePath = Paths.get(filePath);

            if ( Files.notExists(cachePath) ) {
                LOGGER.log(Level.INFO, FILE_MESSAGE + filePath + " does not exist.");
                Files.createFile(cachePath);
                LOGGER.log(Level.INFO, FILE_MESSAGE + filePath + " created.");
            }
        } catch (IOException e) {
            throw new IOLiteTextException("Unable to create " + filePath, e);
        }
    }
}

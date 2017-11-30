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
        StringBuilder buffer = new StringBuilder();
        try (FileReader reader = new FileReader(LiteTextFileHelper.getInstance().acquireFilePath())) {
            int symbol;
            while ((symbol = reader.read()) != -1) {
                buffer.append((char) symbol);
            }

            return buffer.toString();
        } catch (IOException e) {
            throw new IOLiteTextException("Read error", e);
        }
    }

    public void createFileIfNotExists() throws IOLiteTextException {
        String filePath = LiteTextFileHelper.getInstance().acquireFilePath();
        try {
            Path liteTextPath = Paths.get(filePath);

            if ( Files.notExists(liteTextPath) ) {
                LOGGER.log(Level.INFO, FILE_MESSAGE + filePath + " does not exist.");
                Files.createFile(liteTextPath);
                LOGGER.log(Level.INFO, FILE_MESSAGE + filePath + " created.");
            }
        } catch (IOException e) {
            throw new IOLiteTextException("Unable to create " + filePath, e);
        }
    }
}

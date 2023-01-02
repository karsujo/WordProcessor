package wordProcessor.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import wordProcessor.util.MyLogger.DebugLevel;

/**
 * The FileProcessor class is used to read and write files
 */
public class FileProcessor implements FileProcessorInterface {

    private BufferedReader _reader;

    public String fileName;

    public String fullyQualifiedFileName;

    public File file;

    public FileProcessor(String fileNameIn) {
        fileName = fileNameIn;
        InitializeFile();
    }

    /**
     * The function initializes the file object and the reader object
     */
    private void InitializeFile() {
        try {
            String fullFileName = System.getProperty("user.dir") + "/" + fileName;
            fullyQualifiedFileName = fullFileName;
            file = new File(fullFileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            _reader = bufferedReader;

        } catch (FileNotFoundException exceptionIn) {

            MyLogger.writeMessage("Exception at FileProcessor.InitializeFile() - Cant find input file.", DebugLevel.FILE_PROCESSOR);
            ExceptionHandler.handleException(exceptionIn, "Unable to locate file : " + fileName);

        } finally {
        }
    }

    /**
     * It reads a file and returns an array of strings
     * 
     * @return An ArrayList of Strings
     */
    public ArrayList<String> readInputFile() {
        ArrayList<String> result = new ArrayList<String>();
        String str;
        try {
            str = _reader.readLine();
            while (str != null) {
                result.add(str);
                str = _reader.readLine();
            }
        } catch (IOException exceptionIn) {

            MyLogger.writeMessage("Exception at FileProcessor.readInputFile() - Cant read from file.", DebugLevel.FILE_PROCESSOR);

            ExceptionHandler.handleException(exceptionIn, "Unable to locate/read from file : " + fileName);

        } finally {
        }

        return result;
    }

    /**
     * Reads a file as a string
     * 
     * @return A string containing the contents of the file.
     */
    public String readFileAsString() {

        String fileString = null;

        try {
            fileString = new String(Files.readAllBytes(Paths.get(fullyQualifiedFileName)));

        } catch (IOException exIn) {

        } finally {

        }

        return fileString;
    }

    /**
     * This function returns true if the file exists,
     * false otherwise.
     * 
     * @return A boolean value.
     */
    public boolean fileExists() {
        return file.exists();
    }

    /**
     * This function returns the file name.
     * 
     * @return The file name.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Read a line from the file and return it as a string.
     * 
     * @return A string
     */
    public String readLine() {
        String result = null;

        try {
            result = _reader.readLine();
        } catch (IOException exceptionIn) {

            ExceptionHandler.handleException(exceptionIn, "Unable to locate/read from file : " + fileName);

        } finally {
        }
        return result;
    }

    /**
     * The toString() function returns a string representation of the
     * object
     * 
     * @return The toString() method is being returned.
     */
    @Override
    public String toString() {
        return "{" +
        // " _reader='" + get_reader() + "'" +
        // ", _writer='" + get_writer() + "'" +
                "}";
    }

}

package wordProcessor.util;

import java.util.ArrayList;

// This is an interface for the FileProcessor class.
public interface FileProcessorInterface {

    public ArrayList<String> readInputFile();

    public String getFileName();

    public boolean fileExists();

    public String readLine();

    public String readFileAsString();

    public String toString();

}

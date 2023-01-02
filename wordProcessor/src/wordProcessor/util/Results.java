package wordProcessor.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Results implements ResultsInterface {
    private BufferedWriter _writer;
    public String fileName;

    public Results(String fileNameIn) {
        try {

            fileName = System.getProperty("user.dir") + "/" + fileNameIn;

            File file = new File(fileName);
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            _writer = bufferedWriter;

        } catch (IOException exceptionIn) {
            handleException(exceptionIn, "");
        } finally {
        }
    }

    public void writeToFile(String strIn) {
        try {
            _writer.write(strIn);
            _writer.newLine();
        } catch (IOException exceptionIn) {
            handleException(exceptionIn, "");
        } finally {
        }
    }

    public void writeToConsole(String strIn) {
        System.out.println(strIn);
    }

    public void writeToOutput(String strIn) {
        writeToConsole(strIn);
        writeToFile(strIn);
    }

    public void closeInterface() {
        try {
            _writer.close();
        } catch (IOException exceptionIn) {
            handleException(exceptionIn, "");
        } finally {
        }

    }

    public void handleException(Exception exceptionIn, String errorMessageIn) {
        // explain in readme why
        String message;
        if (errorMessageIn.isEmpty()) {
            message = exceptionIn.getMessage();
        } else {
            message = errorMessageIn;
        }
        String str = "EXCEPTION : " + message;
        writeToConsole(str);
        writeToFile(str);
        exceptionIn.printStackTrace();
        closeInterface();
        System.exit(1);
    }
}

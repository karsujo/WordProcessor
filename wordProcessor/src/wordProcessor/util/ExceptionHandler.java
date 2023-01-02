package wordProcessor.util;

/**
 * The ExceptionHandler class is a utility class that provides a single 
 * method to handle exceptions
 */
public class ExceptionHandler {

    public static ResultsInterface errorLogProcessor;

    public static void handleException(Exception exceptionIn, String errorMessageIn) {

        String message;

        if (errorMessageIn.isEmpty()) {
            message = exceptionIn.getMessage();
        } else {
            message = errorMessageIn;
        }

        String str = "EXCEPTION : " + message;
        if (errorLogProcessor != null) {
            errorLogProcessor.writeToConsole(str);
            errorLogProcessor.writeToFile(str);
            errorLogProcessor.closeInterface();
        } else {
            System.out.println(str);
        }

        if (exceptionIn != null) {
            exceptionIn.printStackTrace();
        }
        System.exit(1);

    }

}

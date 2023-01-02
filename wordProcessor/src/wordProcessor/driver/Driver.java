package wordProcessor.driver;

import wordProcessor.util.ExceptionHandler;
import wordProcessor.wordProcessorManager.WordProcessorManager;
import wordProcessor.wordProcessorManager.WordProcessorManagerI;

/**
 * 
 * @authors
 *          Karthik Shangmugam
 *          Yash Makwana
 *
 */

/**
 * It's a driver class that takes in 6 arguments and passes them to the
 * WordProcessorManager class
 */
public class Driver {

    public static void main(String[] args) {
        final int NUMBER_OF_ARGUMENTS = 6;
        if (args.length != NUMBER_OF_ARGUMENTS || args[0].equals("${arg0}") || args[1].equals("${arg1}") || args[2].equals("${arg2}")|| args[3].equals("${arg3}") || args[4].equals("${arg4}") || args[5].equals("${arg5}")) {
            String errorMessage = "Error: Incorrect number of arguments. Program accepts 6 arguments.";
            ExceptionHandler.handleException(null, errorMessage);
        }

        String inputFileName = args[0];
        String mappingFileName = args[1];
        String topKOutputFileName = args[3];
        String spellCheckOutputFileName = args[4];

        int debugLevel = 0;
        int topKFrequentWords = 0;

        try {

            debugLevel = Integer.valueOf(args[5]);
            topKFrequentWords = Integer.valueOf(args[2]);

        } catch (Exception exceptionIn) {

            ExceptionHandler.handleException(exceptionIn, "Enter valid Integers as args for DebugLevel or K");
            
        } finally {

        }

        //Check for invalid values of K
        if (topKFrequentWords <= 0) {

            ExceptionHandler.handleException(null,
                    "Invalid value of K. K must be > 0 and < total number of words in the file");
        }

        try {

            WordProcessorManagerI wordProcessorManager = new WordProcessorManager(inputFileName, mappingFileName,topKOutputFileName, spellCheckOutputFileName, topKFrequentWords, debugLevel);
            wordProcessorManager.processFile();

        } catch (Exception exceptionIn) {

            ExceptionHandler.handleException(exceptionIn, "");

        } finally {

            System.out.println("Complete");

        }
    }
}
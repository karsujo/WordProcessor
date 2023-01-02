package wordProcessor.strategy;

import wordProcessor.util.FileProcessorInterface;
import wordProcessor.util.ResultsInterface;
import java.util.HashMap;
import java.util.Iterator;
import wordProcessor.myElement.MyElementI;

/**
 * This class is a strategy that is case in-sensitive.
 */
public class CaseInsensitiveStrategy implements StrategyI {

    private ResultsInterface outputFileProcessor;
    private FileProcessorInterface inputFileProcessor;

    public CaseInsensitiveStrategy(ResultsInterface outputFileProcessorIn, FileProcessorInterface inputFileProcessorIn) {
        outputFileProcessor = outputFileProcessorIn;
        inputFileProcessor = inputFileProcessorIn;
    }

    @Override
    // This method is called by the client to implement the strategy.
    public void implementStrategy(MyElementI myElementIn) {

        caseInsensitiveSpellCheck(myElementIn);

    }

    private HashMap<String, String> buildCaseInsensitiveHashMap() {
        HashMap<String, String> map = new HashMap<String, String>();
        String[] britishToAmericanWords;
        String mapFileAsString = inputFileProcessor.readFileAsString();
        if (mapFileAsString == "" || mapFileAsString == " ") {
            return map; // return empty map if map file is empty : output will be equal to input
        }
        britishToAmericanWords = mapFileAsString.split(","); // DELIMITER - COMMA
        for (String britishToAmericanWord : britishToAmericanWords) {
            String[] words = britishToAmericanWord.split(":");

            map.put(words[0].toLowerCase(), words[1]);

        }
        return map;
    }

    public void caseInsensitiveSpellCheck(MyElementI caseInsensitiveElement) {

        HashMap<String, String> caseInsensitiveSensitiveMap = buildCaseInsensitiveHashMap();

        writeOutput("STRATEGY 1 :  CASE IN-SENSITIVE SPELL CHECK \n");

        for (Iterator<String> it = (Iterator<String>) caseInsensitiveElement.getIterator(); it.hasNext();) {

            String sentence = it.next(); // case in-sensitive

            String[] words = sentence.split(" ");

            String translatedSentence = "";

            for (int i = 0; i < words.length; i++) {

                String compareKeyInsensitive = words[i].toLowerCase();
                if (caseInsensitiveSensitiveMap.containsKey(compareKeyInsensitive)) // Case-Insensitive Comparison
                {
                    translatedSentence += caseInsensitiveSensitiveMap.get(compareKeyInsensitive); // replace with
                                                                                                  // map-value
                } else {
                    translatedSentence += words[i];
                }

                if (i != words.length - 1)
                    translatedSentence += " ";
            }

            translatedSentence += "."; // end sentence
            writeOutput(translatedSentence);
        }

        writeOutput("\n*************************************************************************\n");

    }

    private void writeOutput(String sentence) {

        outputFileProcessor.writeToFile(sentence);

    }

}

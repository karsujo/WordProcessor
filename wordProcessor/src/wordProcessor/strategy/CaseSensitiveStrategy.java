package wordProcessor.strategy;

import wordProcessor.myElement.MyElementI;
import wordProcessor.util.FileProcessorInterface;
import wordProcessor.util.ResultsInterface;
import java.util.HashMap;
import java.util.Iterator;

/**
 * This class is a strategy that is case sensitive.
 */
public class CaseSensitiveStrategy implements StrategyI {

    private ResultsInterface outputFileProcessor;

    private FileProcessorInterface inputFileProcessor;

    public CaseSensitiveStrategy(ResultsInterface outputFileProcessorIn, FileProcessorInterface inputFileProcessorIn) {

        outputFileProcessor = outputFileProcessorIn;

        inputFileProcessor = inputFileProcessorIn;
    }

    public void implementStrategy(MyElementI myElementIn) {

        caseSensitiveSpellCheck(myElementIn);

    }

    private HashMap<String, String> buildCaseSensitiveHashMap() {

        HashMap<String, String> map = new HashMap<String, String>();

        String[] britishToAmericanWords;

        String mapFileAsString = inputFileProcessor.readFileAsString();

        // return empty map if map file is empty : output will be equal to input
        if (mapFileAsString == "" || mapFileAsString == " ") {
            return map;
        }

        britishToAmericanWords = mapFileAsString.split(","); // DELIMITER - COMMA

        for (String britishToAmericanWord : britishToAmericanWords) {

            String[] words = britishToAmericanWord.split(":");
            map.put(words[0], words[1]);
            
        }

        return map;
    }

    public void caseSensitiveSpellCheck(MyElementI caseSensitiveElement) {

        HashMap<String, String> caseSensitiveMap = buildCaseSensitiveHashMap();

        writeOutput("STRATEGY 2 :  CASE SENSITIVE SPELL CHECK \n");

        // This is an iterator that is iterating through the elements of the caseSensitiveElement.
        for (Iterator<String> it = (Iterator<String>) caseSensitiveElement.getIterator(); it.hasNext();) {

            String sentence = it.next(); // case sensitive    

            String[] words = sentence.split(" ");

            String translatedSentence = "";

            for (int i = 0; i < words.length; i++) {

                if (caseSensitiveMap.containsKey(words[i])) {
                    translatedSentence += caseSensitiveMap.get(words[i]); // replace with map-value
                } else {
                    translatedSentence += words[i];
                }

                if (i != words.length - 1)
                    translatedSentence += " ";

            }

            translatedSentence += "."; // end sentence

            writeOutput(translatedSentence);
        }

        writeOutput("\n*************************************************************************");

    }

    private void writeOutput(String sentence) {

        outputFileProcessor.writeToFile(sentence);

    }

}

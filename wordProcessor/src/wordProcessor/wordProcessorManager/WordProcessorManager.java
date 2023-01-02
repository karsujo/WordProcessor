package wordProcessor.wordProcessorManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import wordProcessor.myArray.MyArray;
import wordProcessor.myArray.MyArrayI;
import wordProcessor.util.FileProcessor;
import wordProcessor.util.FileProcessorInterface;
import wordProcessor.util.MyLogger;
import wordProcessor.util.Results;
import wordProcessor.util.ResultsInterface;
import wordProcessor.util.WordProcessorHelpers;
import wordProcessor.util.MyLogger.DebugLevel;
import wordProcessor.visitor.SpellCheckVisitor;
import wordProcessor.visitor.TopKFrequentVisitor;
import wordProcessor.visitor.VisitorI;

public class WordProcessorManager implements WordProcessorManagerI {
    private FileProcessorInterface inputFileProcessor;
    private FileProcessorInterface mappingFileProcessor;
    private ResultsInterface topKOutputFileProcessor;
    private ResultsInterface spellCheckOutputFileProcessor;
    private int K;
    private int debugLevel;

    public WordProcessorManager(String inputFileNameIn, String mappingFileNameIn, String topKOutputFileNameIn,
            String spellCheckOutputFileNameIn, int KIn, int debugLevelIn) {

        inputFileProcessor = new FileProcessor(inputFileNameIn);
        mappingFileProcessor = new FileProcessor(mappingFileNameIn);
        topKOutputFileProcessor = new Results(topKOutputFileNameIn);
        spellCheckOutputFileProcessor = new Results(spellCheckOutputFileNameIn);
        K = KIn;
        debugLevel = debugLevelIn;
        MyLogger.setDebugValue(debugLevel);
        MyLogger.writeMessage("Created manager.", DebugLevel.CONSTRUCTOR);

    }

    public void processFile() {
        

        MyArrayI myArray = new MyArray();


        // Process input file - validate character set and set value in myArray
        String fileAsString = inputFileProcessor.readFileAsString();

        if (fileAsString == "" || fileAsString == " ") {
            return; // If input file is blank, return and do nothing .
        }

        WordProcessorHelpers.validateCharacterSet(fileAsString);

        List<String> arrayOfSentences = new ArrayList<String>(Arrays.asList(fileAsString.split("\\.")));

        myArray.setArrayListOfSentences((ArrayList<String>) arrayOfSentences);

        // Create visitors
        VisitorI topKFrequentVisitor = new TopKFrequentVisitor(topKOutputFileProcessor, K);

        VisitorI spellCheckVisitor = new SpellCheckVisitor(mappingFileProcessor, spellCheckOutputFileProcessor);

        // Deploy visitors
        myArray.accept(topKFrequentVisitor);

        myArray.accept(spellCheckVisitor);

    }

}
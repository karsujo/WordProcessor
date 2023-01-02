package wordProcessor.visitor;
import wordProcessor.myElement.MyElementI;
import wordProcessor.util.ExceptionHandler;
import wordProcessor.util.MyLogger;
import wordProcessor.util.ResultsInterface;
import wordProcessor.util.MyLogger.DebugLevel;

import java.util.Iterator;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Collections;

public class TopKFrequentVisitor implements VisitorI{
    private ResultsInterface outputFileProcessor;
    private int K;

    /*  
     * This is the constructor for the TopKFrequentVisitor class. 
     * It takes in the ResultsInterface and the K value.
    */
    public TopKFrequentVisitor(ResultsInterface outputFileProcessorIn, int KIn)
    {
        outputFileProcessor = outputFileProcessorIn;
        K = KIn;

        MyLogger.writeMessage("Created Top-K Visitor.", DebugLevel.CONSTRUCTOR);
    }


    /**
     * The function computes the top k frequent elements in the input file and writes the output to the
     * output file
     * 
     * @param myElement The element that is being visited.
     */
    @Override
    public void visit(MyElementI myElement) {
        computeTopKFrequentElements(myElement);
        outputFileProcessor.closeInterface();
    }


    private void computeTopKFrequentElements(MyElementI myElement)
    {
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        String[] topKArray = new String[K];

        for (Iterator<String> it = (Iterator<String>) myElement.getIterator(); it.hasNext(); ) {

            String sentence = it.next();
            String[] words = sentence.split(" "); //DELIMITER : SPACE

            for (String word : words) {
                map.put(word, map.getOrDefault(word, 1)+1);
            }


            ArrayList<String> sortedWordList = new ArrayList<String>();
            
            // This is a priority queue that is sorted by the word count. If the word count is the
            // same, then it is sorted by the word itself.
            PriorityQueue<String> priorityQueue = new PriorityQueue<>(new Comparator<String>() {

                @Override
                public int compare(String word1, String word2) {
                    if (map.get(word1)  == map.get(word2)){
                        return word1.compareTo(word2);
                    }
                    return map.get(word1) - map.get(word2);
                } 
            });

            for (String wordKey : map.keySet()) {
                priorityQueue.add(wordKey);
                if (priorityQueue.size() > K) {
                    priorityQueue.poll();
                }
            }

            while (!priorityQueue.isEmpty()){
                sortedWordList.add(priorityQueue.poll());
            }

            Collections.reverse(sortedWordList);

            try {
                for (int i = 0; i < K; i++) {
                    topKArray[i] = (String) sortedWordList.get(i);
                }
            } catch (Exception exceptionIn) {
                ExceptionHandler.handleException(exceptionIn, "K is greater than words in sentences. Program exiting");
            } finally {

            }
        }
        writeOutput(topKArray);
    }

    private void writeOutput(String[] topKArray)
    {
        StringBuilder sb = new StringBuilder();

        sb.append("most-frequent-word : " + topKArray[0]).append(" \n");

        for(int i=1; i<K; i++)
        {
            sb.append((i+1)+"-most-frequent-word : " + topKArray[i]).append(" \n");
        }

        outputFileProcessor.writeToFile(sb.toString());
    }   
}
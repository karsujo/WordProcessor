package wordProcessor.myArray;

import java.util.ArrayList;
import java.util.Iterator;

import wordProcessor.myElement.MyElement;
import wordProcessor.visitor.VisitorI;

/**
 * The MyArray class is a subclass of the MyElement class and implements
 * the MyArrayI interface
 */
public class MyArray extends MyElement implements MyArrayI {

    private ArrayList<String> arrayListOfSentences;

    // Creating a new ArrayList of Strings.
    public MyArray() {

        arrayListOfSentences = new ArrayList<>();

    }

 
    public ArrayList<String> getArrayListOfSentences() {

        return arrayListOfSentences;

    }

    /**
     * This function sets the value of the arrayListOfSentences variable 
     * to the value of the arrayListOfSentencesIn variable
     * 
     * @param arrayListOfSentencesIn The ArrayList of sentences that you 
     * want to be analyzed.
     */
    public void setArrayListOfSentences(ArrayList<String> arrayListOfSentencesIn) {

        arrayListOfSentences = arrayListOfSentencesIn;

    }

  
    public Iterator<String> getIterator(){

        return arrayListOfSentences.iterator();

    }

   /**
    * The accept function takes a visitor as a parameter and calls the 
    * visitor's visit function with the current object as a parameter.
    * 
    * @param visitor The visitor object that will be visiting this node.
    */
    @Override
    public void accept(VisitorI visitor) {
        
        //Double Dispatch
        visitor.visit(this);
    }


    public String toString() {
        return null;
    }
}

package wordProcessor.myArray;

import java.util.ArrayList;
import java.util.Iterator;

import wordProcessor.visitor.VisitorI;

// An interface for the MyArray class.
public interface MyArrayI {

    public ArrayList<String> getArrayListOfSentences();

    public void setArrayListOfSentences(ArrayList<String> arrayListOfSentencesIn);

    public Iterator<String> getIterator();

    public void accept(VisitorI visitor);
}

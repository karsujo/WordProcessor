package wordProcessor.myElement;
import java.util.Iterator;
import wordProcessor.visitor.VisitorI;

/**
 * MyElement is an abstract class that implements the MyElementI 
 * interface and provides a default implementation of the accept method.
 */
public abstract class MyElement implements MyElementI {
    public abstract Iterator<String> getIterator();
    public abstract void accept(VisitorI visitor);
}

package wordProcessor.myElement;
import java.util.Iterator;
import wordProcessor.visitor.VisitorI;

// An interface that is implemented by the MyElement class.
public interface MyElementI {
    public abstract Iterator<String> getIterator();

    public abstract void accept(VisitorI visitor);
}

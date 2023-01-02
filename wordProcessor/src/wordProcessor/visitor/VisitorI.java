package wordProcessor.visitor;

import wordProcessor.myElement.MyElementI;
// The interface for the visitor.
public interface VisitorI {
    public void visit(MyElementI myElement);
}

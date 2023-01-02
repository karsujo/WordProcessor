package wordProcessor.visitor;
import wordProcessor.myElement.MyElementI;
import wordProcessor.strategy.CaseInsensitiveStrategy;
import wordProcessor.strategy.CaseSensitiveStrategy;
import wordProcessor.strategy.StrategyI;
import wordProcessor.util.FileProcessorInterface;
import wordProcessor.util.MyLogger;
import wordProcessor.util.ResultsInterface;
import wordProcessor.util.MyLogger.DebugLevel;
public class SpellCheckVisitor implements VisitorI {
    private ResultsInterface outputFileProcessor;
    private FileProcessorInterface inputFileProcessor;


    public SpellCheckVisitor(FileProcessorInterface inputFileProcessorIn, ResultsInterface outputFileProcessorIn) {
        inputFileProcessor = inputFileProcessorIn;
        outputFileProcessor = outputFileProcessorIn;
        MyLogger.writeMessage("Created Spell Check Visitor.", DebugLevel.CONSTRUCTOR);
    }

    @Override
    public void visit(MyElementI myElement) {

        StrategyI caseSensitiveStrategy = new CaseSensitiveStrategy(outputFileProcessor, inputFileProcessor);
        
        StrategyI caseInSensitiveStrategy = new CaseInsensitiveStrategy(outputFileProcessor, inputFileProcessor);

        //Execute Strategy 1
        executeStrategies(myElement, caseInSensitiveStrategy);
        //Execute Strategy 2
        executeStrategies(myElement, caseSensitiveStrategy);
        
         outputFileProcessor.closeInterface();
    }


    private void executeStrategies(MyElementI myElement, StrategyI strategy)
    {
        strategy.implementStrategy(myElement);
    }

}

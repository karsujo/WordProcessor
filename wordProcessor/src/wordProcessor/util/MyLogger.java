
package wordProcessor.util;

public class MyLogger{

    public static enum DebugLevel {NONE, CONSTRUCTOR, FILE_PROCESSOR
                                   };

    private static DebugLevel debugLevel;

    public static void setDebugValue (int levelIn) {
	switch (levelIn) {
	case 1: debugLevel = DebugLevel.CONSTRUCTOR; break;
	case 2: debugLevel = DebugLevel.FILE_PROCESSOR; break;
	default: debugLevel = DebugLevel.NONE; break;
	}
    }

    public static void setDebugValue (DebugLevel levelIn) {
	debugLevel = levelIn;
    }

    public static void writeMessage (String     message  ,
                                     DebugLevel levelIn ) {
	if (levelIn == debugLevel)
	    System.out.println(message);
    }

    public String toString() {
	return "The debug level has been set to the following " + debugLevel;
    }
}

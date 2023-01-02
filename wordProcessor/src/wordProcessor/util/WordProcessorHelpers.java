package wordProcessor.util;
import java.util.regex.Pattern;


public class WordProcessorHelpers {

    /**
     * It takes a string and checks if it contains only alphabets, 
     * spaces and periods
     * 
     * @param fileString The string that needs to be validated.
     * @return A boolean value.
     */
    public static boolean validateCharacterSet(String fileString) {
        
        String regEx_Only_Alph_Period_Space = "^[a-zA-Z\\s.-]+$";

        Pattern pattern = Pattern.compile(regEx_Only_Alph_Period_Space);

        if(!pattern.matcher(fileString).matches())
        {
            ExceptionHandler.handleException(null, "Invalid Characters detected in the file. Program Exiting ... ");
        }

        return true;

    }

}

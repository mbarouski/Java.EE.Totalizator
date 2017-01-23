package sport.totalizator.util;

import sport.totalizator.exception.ExceptionWithErrorList;

public class NumberValidator {
    public static int parseInt(String intInString, ExceptionWithErrorList exceptionWithErrorList, String errorMessage){
        int result;
        try{
            result = Integer.parseInt(intInString);
        } catch (NumberFormatException exc){
            result = -1;
            exceptionWithErrorList.addMessage(errorMessage);
        }
        return result;
    }

    public static int parseInt(String intInString, int defaultValue){
        int result;
        try{
            result = Integer.parseInt(intInString);
        } catch (NumberFormatException exc){
            result = defaultValue;
        }
        return result;
    }

    public static int parseInt(String intInString){
        int result;
        try{
            result = Integer.parseInt(intInString);
        } catch (NumberFormatException exc){
            result = -1;
        }
        return result;
    }

    public static double parseDouble(String doubleInString, ExceptionWithErrorList exceptionWithErrorList, String errorMessage){
        double result;
        try{
            result = Double.parseDouble(doubleInString);
        } catch (NumberFormatException exc){
            result = -1.0;
            exceptionWithErrorList.addMessage(errorMessage);
        }
        return result;
    }

    public static double parseDouble(String doubleInString, double defaultValue){
        double result;
        try{
            result = Double.parseDouble(doubleInString);
        } catch (NumberFormatException exc){
            result = defaultValue;
        }
        return result;
    }
}

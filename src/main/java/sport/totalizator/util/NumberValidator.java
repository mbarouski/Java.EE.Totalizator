package sport.totalizator.util;

import sport.totalizator.exception.ExceptionWithErrorList;

/**
 * {@link NumberValidator}'s task is to convert string to int or double values.
 */
public class NumberValidator {
    /**
     * Method that tries to convert string to int value. In error case it writes errorMessage to exceptionWithErrorList.
     * @param intInString
     * @param exceptionWithErrorList
     * @param errorMessage
     * @return -1 or converted value in case of success.
     */
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

    /**
     * Method that tries to convert string to int value. In error case it returns default value.
     * @param intInString
     * @param defaultValue
     * @return defaultValue or converted value.
     */
    public static int parseInt(String intInString, int defaultValue){
        int result;
        try{
            result = Integer.parseInt(intInString);
        } catch (NumberFormatException exc){
            result = defaultValue;
        }
        return result;
    }

    /**
     * Method that tries to convert string to int value. In error case it returns -1.
     * @param intInString
     * @return -1 or converted value.
     */
    public static int parseInt(String intInString){
        int result;
        try{
            result = Integer.parseInt(intInString);
        } catch (NumberFormatException exc){
            result = -1;
        }
        return result;
    }

    /**
     *  Method that tries to convert string to double value. In error case it writes errorMessage to exceptionWithErrorList.
     * @param doubleInString
     * @param exceptionWithErrorList
     * @param errorMessage
     * @return -1.0 or converted value in case of success.
     */
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

    /**
     * Method that tries to convert string to double value. In error case it returns default value.
     * @param doubleInString
     * @param defaultValue
     * @return defaultValue or converted value.
     */
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

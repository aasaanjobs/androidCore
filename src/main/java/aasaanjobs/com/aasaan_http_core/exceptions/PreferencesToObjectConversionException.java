package aasaanjobs.com.aasaan_http_core.exceptions;

// TODO: Auto-generated Javadoc
/**
 * Created by dineshsingh on 02/03/15.
 */
public class PreferencesToObjectConversionException extends BaseException {


    /** The message. */
    private static String message = "Unable to convert shared preferences to object";
    
    /** The code. */
    private static int code = ExceptionCodesConstants.PREFERENCES_TO_OBJECT_EXCEPTION;


    /**
     * Instantiates a new preferences to object conversion exception.
     *
     * @param message the message
     */
    public PreferencesToObjectConversionException(String message) {
        super(message, code);
    }

    /**
     * Instantiates a new preferences to object conversion exception.
     *
     * @param message the message
     * @param code the code
     */
    public PreferencesToObjectConversionException(String message, int code) {
        super(message, code);
    }

    /**
     * Instantiates a new preferences to object conversion exception.
     *
     * @param cause the cause
     * @param message the message
     */
    public PreferencesToObjectConversionException(Throwable cause, String message) {
        super(cause, message);
    }

    /**
     * Instantiates a new preferences to object conversion exception.
     *
     * @param code the code
     */
    public PreferencesToObjectConversionException(int code) {
        super(message, code);
    }

    /**
     * Instantiates a new preferences to object conversion exception.
     */
    public PreferencesToObjectConversionException() {
        super(message, code);
    }
}

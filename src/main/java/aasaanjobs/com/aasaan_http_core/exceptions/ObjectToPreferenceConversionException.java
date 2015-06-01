package aasaanjobs.com.aasaan_http_core.exceptions;

// TODO: Auto-generated Javadoc
/**
 * Created by dineshsingh on 02/03/15.
 */
public class ObjectToPreferenceConversionException extends BaseException {
    
    /** The message. */
    private static String message = "Unable to convert  object to shared preferences ";
    
    /** The code. */
    private static int code = ExceptionCodesConstants.OBJECT_TO_PREFERENCES_EXCEPTION;

    /**
     * Instantiates a new object to preference conversion exception.
     */
    public ObjectToPreferenceConversionException() {
        super(message, code);
    }

    /**
     * Instantiates a new object to preference conversion exception.
     *
     * @param message the message
     */
    public ObjectToPreferenceConversionException(String message) {
        super(message, code);
    }

    /**
     * Instantiates a new object to preference conversion exception.
     *
     * @param message the message
     * @param code the code
     */
    public ObjectToPreferenceConversionException(String message, int code) {
        super(message, code);
    }

    /**
     * Instantiates a new object to preference conversion exception.
     *
     * @param cause the cause
     * @param message the message
     */
    public ObjectToPreferenceConversionException(Throwable cause, String message) {
        super(cause, message);
    }

    /**
     * Instantiates a new object to preference conversion exception.
     *
     * @param code the code
     */
    public ObjectToPreferenceConversionException(int code) {
        super(message, code);
    }
}

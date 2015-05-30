package aasaanjobs.com.aasaan_http_core.exceptions;

/**
 * Created by dineshsingh on 02/03/15.
 */
public class ObjectToPreferenceConversionException extends BaseException {
    private static String message = "Unable to convert  object to shared preferences ";
    private static int code = ExceptionCodesConstants.OBJECT_TO_PREFERENCES_EXCEPTION;

    public ObjectToPreferenceConversionException() {
        super(message, code);
    }

    public ObjectToPreferenceConversionException(String message) {
        super(message, code);
    }

    public ObjectToPreferenceConversionException(String message, int code) {
        super(message, code);
    }

    public ObjectToPreferenceConversionException(Throwable cause, String message) {
        super(cause, message);
    }

    public ObjectToPreferenceConversionException(int code) {
        super(message, code);
    }
}

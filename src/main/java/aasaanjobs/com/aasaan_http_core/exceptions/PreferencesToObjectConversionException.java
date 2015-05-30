package aasaanjobs.com.aasaan_http_core.exceptions;

/**
 * Created by dineshsingh on 02/03/15.
 */
public class PreferencesToObjectConversionException extends BaseException {


    private static String message = "Unable to convert shared preferences to object";
    private static int code = ExceptionCodesConstants.PREFERENCES_TO_OBJECT_EXCEPTION;


    public PreferencesToObjectConversionException(String message) {
        super(message, code);
    }

    public PreferencesToObjectConversionException(String message, int code) {
        super(message, code);
    }

    public PreferencesToObjectConversionException(Throwable cause, String message) {
        super(cause, message);
    }

    public PreferencesToObjectConversionException(int code) {
        super(message, code);
    }

    public PreferencesToObjectConversionException() {
        super(message, code);
    }
}

package aasaanjobs.com.aasaan_http_core.exceptions;

// TODO: Auto-generated Javadoc
/**
 * Created by dineshsingh on 19/02/15.
 */
public class BaseException extends Exception {

    /**
     * The Constant message.
     */
    private final static String message = "Something went wrong.";
    /**
     * The code.
     */
    private int code;


    /**
     * Instantiates a new base exception impl.
     */
    public BaseException() {
        // TODO Auto-generated constructor stub

        super(message);
        this.code = ExceptionCodesConstants.BASE_EXCEPTION;
    }

    /**
     * Instantiates a new base exception impl.
     *
     * @param message the message
     */
    public BaseException(String message) {
        super(message);
        this.code = ExceptionCodesConstants.BASE_EXCEPTION;
    }

    /**
     * Instantiates a new base exception impl.
     *
     * @param message the message
     * @param code    the code
     */
    public BaseException(String message, int code) {
        super(message);
        this.setCode(code);
    }

    /**
     * Instantiates a new base exception impl.
     *
     * @param cause   the cause
     * @param message the message
     */
    public BaseException(Throwable cause, String message) {
        super(message, cause);
        this.code = ExceptionCodesConstants.BASE_EXCEPTION;
    }

    /**
     * Instantiates a new base exception.
     *
     * @param code the code
     */
    public BaseException(int code) {
        this.code = code;
    }

    /**
     * Gets the code.
     *
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * Sets the code.
     *
     * @param code the code to set
     */
    public void setCode(int code) {
        this.code = code;
    }
}

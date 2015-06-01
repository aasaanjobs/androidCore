package aasaanjobs.com.aasaan_http_core.repositories;


import aasaanjobs.com.aasaan_http_core.exceptions.BaseException;

// TODO: Auto-generated Javadoc
/**
 * Created by dineshsingh on 01/04/15.
 *
 * @see CustomErrorEvent
 */
public interface CustomErrorListener {
    
    /**
     * On error.
     *
     * @param <T> the generic type
     * @param error the error
     */
    public <T extends BaseException> void onError(Exception error);
}

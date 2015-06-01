package aasaanjobs.com.aasaan_http_core.utils.Listeners;

// TODO: Auto-generated Javadoc
/**
 * Created by nazmuddin.m on 2/24/2015.
 *
 * @param <T> the generic type
 * @see ViewEvent
 */
public interface ViewListener<T> extends BaseListener {
    
    /**
     * On success.
     *
     * @param response the response
     */
    public void onSuccess(T response);

    /**
     * On error.
     *
     * @param error the error
     */
    public void onError(Exception error);
}

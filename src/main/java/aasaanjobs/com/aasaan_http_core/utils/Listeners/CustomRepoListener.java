package aasaanjobs.com.aasaan_http_core.utils.Listeners;

// TODO: Auto-generated Javadoc
/**
 * Created by dineshsingh on 23/02/15.
 *
 * @param <T> the generic type
 * @see CustomRepoEvent
 */
public interface CustomRepoListener<T> extends BaseRepoListener {
    
    /**
     * On success.
     *
     * @param response the response
     */
    public void onSuccess(T response);

}

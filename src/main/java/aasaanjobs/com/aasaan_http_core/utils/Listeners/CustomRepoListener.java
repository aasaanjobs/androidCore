package aasaanjobs.com.aasaan_http_core.utils.Listeners;

/**
 * Created by dineshsingh on 23/02/15.
 */
public interface CustomRepoListener<T> extends BaseRepoListener {
    public void onSuccess(T response);

}
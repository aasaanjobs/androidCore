package aasaanjobs.com.aasaan_http_core.utils.Listeners;

/**
 * Created by nazmuddin.m on 2/24/2015.
 */
public interface ViewListener<T> extends BaseListener {
    public void onSuccess(T response);

    public void onError(Exception error);
}

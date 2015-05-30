package aasaanjobs.com.aasaan_http_core.utils.Listeners;

import java.util.List;

/**
 * Created by dineshsingh on 23/02/15.
 */
public interface CustomRepoListListener<T> extends BaseRepoListener {
    public void onSuccess(List<T> list);
}

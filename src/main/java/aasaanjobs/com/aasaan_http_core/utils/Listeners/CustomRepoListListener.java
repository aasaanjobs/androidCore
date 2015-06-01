package aasaanjobs.com.aasaan_http_core.utils.Listeners;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * Created by dineshsingh on 23/02/15.
 *
 * @param <T> the generic type
 * @see CustomRepoListEvent
 */
public interface CustomRepoListListener<T> extends BaseRepoListener {
    
    /**
     * On success.
     *
     * @param list the list
     */
    public void onSuccess(List<T> list);
}

package aasaanjobs.com.aasaan_http_core.repositories;

import android.content.Context;

import org.json.JSONObject;

import java.io.File;

import aasaanjobs.com.aasaan_http_core.models.BaseDO;
import aasaanjobs.com.aasaan_http_core.models.BaseResponseDO;
import aasaanjobs.com.aasaan_http_core.utils.Listeners.CustomRepoListListener;
import aasaanjobs.com.aasaan_http_core.utils.Listeners.CustomRepoListener;


// TODO: Auto-generated Javadoc
/**
 * Created by dineshsingh on 21/02/15.
 */
public interface CustomService extends BaseService {

    /**
     * Sets the context.
     *
     * @param context the new context
     */
    public void setContext(Context context);

    /**
     * Gets the.
     *
     * @param <T> the generic type
     * @param c the c
     * @param listener the listener
     */
    public <T> void get(Class<T> c, CustomRepoListener<T> listener);

    /**
     * Gets the.
     *
     * @param <T> the generic type
     * @param c the c
     * @param listener the listener
     * @param url the url
     */
    public <T> void get(Class<T> c, CustomRepoListener<T> listener, String url);

    /**
     * Gets the.
     *
     * @param <T> the generic type
     * @param c the c
     * @param requestObject the request object
     * @param listener the listener
     */
    public <T> void get(Class<T> c, JSONObject requestObject, CustomRepoListener<T> listener);

    /**
     * Gets the list.
     *
     * @param <T> the generic type
     * @param c the c
     * @param listener the listener
     * @return the list
     */
    public <T> void getList(Class<T> c, CustomRepoListListener<T> listener);


    //public <T > void add(Class<T> c, CustomRepoListener<T> listener);
    /**
     * Adds the.
     *
     * @param <T> the generic type
     * @param c the c
     * @param request the request
     * @param listener the listener
     */
    public <T> void add(Class<T> c, T request, CustomRepoListener<T> listener);

    /**
     * Adds the.
     *
     * @param <T> the generic type
     * @param c the c
     * @param request the request
     * @param listener the listener
     */
    public <T> void add(Class<T> c, JSONObject request, CustomRepoListener<T> listener);

    /**
     * Patch.
     *
     * @param <T> the generic type
     * @param c the c
     * @param request the request
     * @param listener the listener
     */
    public <T> void patch(Class<T> c, T request, CustomRepoListener<T> listener);

    /**
     * Patch.
     *
     * @param <T> the generic type
     * @param c the c
     * @param request the request
     * @param listener the listener
     */
    public <T> void patch(Class<T> c, JSONObject request, CustomRepoListener<T> listener);

    /**
     * Adds the.
     *
     * @param <T> the generic type
     * @param <P> the generic type
     * @param c the c
     * @param request the request
     * @param listener the listener
     */
    public <T extends BaseDO, P extends BaseResponseDO> void add(Class<P> c, T request, CustomRepoListener<P> listener);


    //public <T > void update(Class<T> c, CustomRepoListener<T> listener);
    /**
     * Update.
     *
     * @param <T> the generic type
     * @param c the c
     * @param request the request
     * @param listener the listener
     */
    public <T> void update(Class<T> c, T request, CustomRepoListener<T> listener);

    /**
     * Delete.
     *
     * @param <T> the generic type
     * @param c the c
     * @param request the request
     * @param listener the listener
     */
    public <T> void delete(Class<T> c, T request, CustomRepoListener<T> listener);

    // public void getAndSave(Class<SearchDO> searchDOClass, CustomRepoListener<SearchDO> customRepoListener);
    //public <T > void delete(Class<T> c, CustomRepoListener<T> listener);

    /**
     * Gets the and save.
     *
     * @param <T> the generic type
     * @param clazz the clazz
     * @param listener the listener
     * @param showLoadingDialogue the show loading dialogue
     * @return the and save
     */
    public <T> void getAndSave(Class<T> clazz, CustomRepoListener<T> listener, boolean showLoadingDialogue);

    /**
     * Gets the and save.
     *
     * @param <T> the generic type
     * @param clazz the clazz
     * @param listener the listener
     * @return the and save
     */
    public <T> void getAndSave(Class<T> clazz, CustomRepoListener<T> listener);

    /**
     * Gets the string.
     *
     * @param customRepoListener the custom repo listener
     * @param url the url
     * @return the string
     */
    void getString(CustomRepoListener<String> customRepoListener, String url);

    /**
     * Upload file.
     *
     * @param <T> the generic type
     * @param c the c
     * @param file the file
     * @param url the url
     * @param listener the listener
     */
    public <T> void uploadFile(Class<T> c, File file, String url, CustomRepoListener<T> listener);

    public <T> void uploadFile(Class<T> c, File file, String body, String url, CustomRepoListener<T> listener);

    /**
     * Download file.
     *
     * @param url the url
     * @param type the type
     * @param listener the listener
     */
    public void downloadFile(String url, int type, CustomRepoListener<File> listener);


}

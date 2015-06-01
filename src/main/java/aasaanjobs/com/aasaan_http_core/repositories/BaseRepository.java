package aasaanjobs.com.aasaan_http_core.repositories;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import aasaanjobs.com.aasaan_http_core.models.BaseDO;
import aasaanjobs.com.aasaan_http_core.models.BaseResponseDO;
import aasaanjobs.com.aasaan_http_core.utils.Listeners.CustomRepoListListener;
import aasaanjobs.com.aasaan_http_core.utils.Listeners.CustomRepoListener;

// TODO: Auto-generated Javadoc
/**
 * Created by dineshsingh on 21/02/15.
 */
public interface BaseRepository {


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
     * @param clazz the clazz
     * @param url the url
     * @param listener the listener
     */
    public <T> void get(Class<T> clazz, String url, CustomRepoListener<T> listener);

    /**
     * Gets the.
     *
     * @param <T> the generic type
     * @param clazz the clazz
     * @param listener the listener
     */
    public <T> void get(Class<T> clazz, CustomRepoListener<T> listener);

    /**
     * Gets the.
     *
     * @param <T> the generic type
     * @param clazz the clazz
     * @param requestObject the request object
     * @param listener the listener
     */
    public <T> void get(Class<T> clazz, JSONObject requestObject, CustomRepoListener<T> listener);


    /**
     * Gets the list.
     *
     * @param <T> the generic type
     * @param clazz the clazz
     * @param url the url
     * @param listener the listener
     * @return the list
     */
    public <T> void getList(Class<T> clazz, String url, CustomRepoListListener<T> listener);

    /**
     * Gets the list.
     *
     * @param <T> the generic type
     * @param clazz the clazz
     * @param listener the listener
     * @return the list
     */
    public <T> void getList(Class<T> clazz, CustomRepoListListener<T> listener);

    /**
     * Post.
     *
     * @param <T> the generic type
     * @param clazz the clazz
     * @param requestObject the request object
     * @param listener the listener
     */
    public <T> void post(Class<T> clazz, JSONObject requestObject, CustomRepoListener<T> listener);

    /**
     * Post.
     *
     * @param <T> the generic type
     * @param clazz the clazz
     * @param url the url
     * @param requestObject the request object
     * @param listener the listener
     */
    public <T> void post(Class<T> clazz, String url, JSONObject requestObject, CustomRepoListener<T> listener);

    /**
     * Post.
     *
     * @param <T> the generic type
     * @param c the c
     * @param request the request
     * @param listener the listener
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws JSONException the JSON exception
     */
    public <T> void post(Class<T> c, T request, CustomRepoListener<T> listener) throws IOException, JSONException;

    /**
     * Put.
     *
     * @param <T> the generic type
     * @param clazz the clazz
     * @param requestObject the request object
     * @param listener the listener
     */
    public <T> void put(Class<T> clazz, JSONObject requestObject, CustomRepoListener<T> listener);

    /**
     * Put.
     *
     * @param <T> the generic type
     * @param clazz the clazz
     * @param url the url
     * @param requestObject the request object
     * @param listener the listener
     */
    public <T> void put(Class<T> clazz, String url, JSONObject requestObject, CustomRepoListener<T> listener);

    /**
     * Put.
     *
     * @param <T> the generic type
     * @param c the c
     * @param request the request
     * @param listener the listener
     */
    public <T> void put(Class<T> c, T request, CustomRepoListener<T> listener);

    /**
     * Patch.
     *
     * @param <T> the generic type
     * @param clazz the clazz
     * @param requestObject the request object
     * @param listener the listener
     */
    public <T> void patch(Class<T> clazz, JSONObject requestObject, CustomRepoListener<T> listener);

    /**
     * Patch.
     *
     * @param <T> the generic type
     * @param clazz the clazz
     * @param url the url
     * @param requestObject the request object
     * @param listener the listener
     */
    public <T> void patch(Class<T> clazz, String url, JSONObject requestObject, CustomRepoListener<T> listener);

    /**
     * Patch.
     *
     * @param <T> the generic type
     * @param c the c
     * @param request the request
     * @param listener the listener
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws JSONException the JSON exception
     */
    public <T> void patch(Class<T> c, T request, CustomRepoListener<T> listener) throws IOException, JSONException;

    /**
     * Delete.
     *
     * @param <T> the generic type
     * @param clazz the clazz
     * @param requestObject the request object
     * @param listener the listener
     */
    public <T> void delete(Class<T> clazz, JSONObject requestObject, CustomRepoListener<T> listener);

    /**
     * Delete.
     *
     * @param <T> the generic type
     * @param clazz the clazz
     * @param url the url
     * @param requestObject the request object
     * @param listener the listener
     */
    public <T> void delete(Class<T> clazz, String url, JSONObject requestObject, CustomRepoListener<T> listener);

    /**
     * Delete.
     *
     * @param <T> the generic type
     * @param c the c
     * @param request the request
     * @param listener the listener
     */
    public <T> void delete(Class<T> c, T request, CustomRepoListener<T> listener);


    /**
     * Send request.
     *
     * @param <T> the generic type
     * @param clazz the clazz
     * @param url the url
     * @param requestType the request type
     * @param listener the listener
     */
    public <T> void sendRequest(Class<T> clazz, String url, int requestType, CustomRepoListener<T> listener);

    /**
     * Send request.
     *
     * @param <T> the generic type
     * @param clazz the clazz
     * @param requestType the request type
     * @param listener the listener
     */
    public <T> void sendRequest(Class<T> clazz, int requestType, CustomRepoListener<T> listener);

    /**
     * Send request.
     *
     * @param <T> the generic type
     * @param clazz the clazz
     * @param url the url
     * @param requestType the request type
     */
    public <T> void sendRequest(Class<T> clazz, String url, int requestType);

    /**
     * Call method by request type.
     *
     * @param <T> the generic type
     * @param requestType the request type
     * @param requestObject the request object
     * @param clazz the clazz
     */
    public <T> void callMethodByRequestType(final int requestType, JSONObject requestObject, final Class<T> clazz);

    /**
     * Call method by request type.
     *
     * @param <T> the generic type
     * @param requestType the request type
     * @param clazz the clazz
     */
    public <T> void callMethodByRequestType(final int requestType, final Class<T> clazz);

    //public <T> void getListAndSave(final Class<T> clazz, final CustomRepoListListener<T> listener);

    /**
     * Gets the and save.
     *
     * @param <T> the generic type
     * @param clazz the clazz
     * @param listener the listener
     * @return the and save
     */
    public <T> void getAndSave(final Class<T> clazz, final CustomRepoListener<T> listener);

    /**
     * Gets the and save.
     *
     * @param <T> the generic type
     * @param clazz the clazz
     * @param listener the listener
     * @param showLoadingDialogue the show loading dialogue
     * @return the and save
     */
    public <T> void getAndSave(final Class<T> clazz, final CustomRepoListener<T> listener, boolean showLoadingDialogue);

    /**
     * Call method by request type and save.
     *
     * @param <T> the generic type
     * @param requestType the request type
     * @param clazz the clazz
     * @param showLoadingDialogue the show loading dialogue
     */
    public <T> void callMethodByRequestTypeAndSave(final int requestType, final Class<T> clazz, boolean showLoadingDialogue);


    /**
     * Gets the.
     *
     * @param <T> the generic type
     * @param c the c
     * @param listener the listener
     * @param url the url
     */
    <T> void get(Class<T> c, CustomRepoListener<T> listener, String url);

    /**
     * Post.
     *
     * @param <T> the generic type
     * @param <P> the generic type
     * @param c the c
     * @param request the request
     * @param listener the listener
     */
    public <T extends BaseDO, P extends BaseResponseDO> void post(Class<P> c, T request, CustomRepoListener<P> listener);

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
    <T> void uploadFile(Class<T> c, File file, String url, CustomRepoListener<T> listener);

    /**
     * Download file.
     *
     * @param url the url
     * @param type the type
     * @param listener the listener
     */
    void downloadFile(String url, int type, CustomRepoListener<File> listener);
}

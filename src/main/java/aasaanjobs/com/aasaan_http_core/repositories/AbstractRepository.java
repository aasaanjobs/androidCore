package aasaanjobs.com.aasaan_http_core.repositories;

import android.app.ProgressDialog;
import android.content.Context;


import org.json.JSONObject;

import aasaanjobs.com.aasaan_http_core.models.BaseDO;
import aasaanjobs.com.aasaan_http_core.utils.Listeners.CustomRepoListListener;
import aasaanjobs.com.aasaan_http_core.utils.Listeners.CustomRepoListener;

// TODO: Auto-generated Javadoc
/**
 * Created by dineshsingh on 19/02/15.
 *
 * @param <T> the generic type
 */
public abstract class AbstractRepository<T extends BaseDO> implements BaseRepository {


    /** The model. */
    protected BaseDO model;
    
    /** The url. */
    protected String url;
    
    /** The custom repo listener. */
    protected CustomRepoListener customRepoListener;
    
    /** The response class. */
    protected Class<T> responseClass;

    /** The progress dialog. */
    protected ProgressDialog progressDialog;


    /** The context. */
    protected Context context;

    /* (non-Javadoc)
     * @see aasaanjobs.com.aasaan_http_core.repositories.BaseRepository#setContext(Context)
     */
    @Override
    public void setContext(Context context) {

        this.context = context;
    }

    /* (non-Javadoc)
     * @see aasaanjobs.com.aasaan_http_core.repositories.BaseRepository#sendRequest(java.lang.Class, java.lang.String, int, aasaanjobs.com.aasaan_http_core.utils.Listeners.CustomRepoListener)
     */
    @Override
    public <T> void sendRequest(Class<T> clazz, String url, int requestType, CustomRepoListener<T> listener) {

        setUrl(url);
        setCustomRepoListener(listener);
        callMethodByRequestType(requestType, clazz);

    }

    /* (non-Javadoc)
     * @see aasaanjobs.com.aasaan_http_core.repositories.BaseRepository#sendRequest(java.lang.Class, int, aasaanjobs.com.aasaan_http_core.utils.Listeners.CustomRepoListener)
     */
    @Override
    public <T> void sendRequest(Class<T> clazz, int requestType, CustomRepoListener<T> listener) {
        setCustomRepoListener(listener);
        callMethodByRequestType(requestType, clazz);
    }

    /* (non-Javadoc)
     * @see aasaanjobs.com.aasaan_http_core.repositories.BaseRepository#sendRequest(java.lang.Class, java.lang.String, int)
     */
    @Override
    public <T> void sendRequest(Class<T> clazz, String url, int requestType) {
        callMethodByRequestType(requestType, clazz);
    }

    /* (non-Javadoc)
     * @see aasaanjobs.com.aasaan_http_core.repositories.BaseRepository#get(java.lang.Class, java.lang.String, aasaanjobs.com.aasaan_http_core.utils.Listeners.CustomRepoListener)
     */
    @Override
    public <T> void get(Class<T> clazz, String url, CustomRepoListener<T> listener) {
        get(clazz, listener);
    }

    /* (non-Javadoc)
     * @see aasaanjobs.com.aasaan_http_core.repositories.BaseRepository#getList(java.lang.Class, java.lang.String, aasaanjobs.com.aasaan_http_core.utils.Listeners.CustomRepoListListener)
     */
    @Override
    public <T> void getList(Class<T> clazz, String url, CustomRepoListListener<T> listener) {
        getList(clazz, listener);

    }

    /* (non-Javadoc)
     * @see aasaanjobs.com.aasaan_http_core.repositories.BaseRepository#post(Class, String, JSONObject, CustomRepoListener)
     */
    @Override
    public <T> void post(Class<T> clazz, String url, JSONObject request, CustomRepoListener<T> listener) {

        post(clazz, request, listener);
    }

    /* (non-Javadoc)
     * @see aasaanjobs.com.aasaan_http_core.repositories.BaseRepository#put(Class, String, JSONObject, CustomRepoListener)
     */
    @Override
    public <T> void put(Class<T> clazz, String url, JSONObject request, CustomRepoListener<T> listener) {
        put(clazz, request, listener);

    }

    /* (non-Javadoc)
     * @see aasaanjobs.com.aasaan_http_core.repositories.BaseRepository#patch(Class, String, JSONObject, CustomRepoListener)
     */
    @Override
    public <T> void patch(Class<T> clazz, String url, JSONObject request, CustomRepoListener<T> listener) {
        patch(clazz, request, listener);

    }

    /* (non-Javadoc)
     * @see aasaanjobs.com.aasaan_http_core.repositories.BaseRepository#delete(Class, String, JSONObject, CustomRepoListener)
     */
    @Override
    public <T> void delete(Class<T> clazz, String url, JSONObject request, CustomRepoListener<T> listener) {

        delete(clazz, request, listener);
    }

    /**
     * Gets the response class.
     *
     * @return the response class
     */
    public Class<T> getResponseClass() {
        return responseClass;
    }

    /**
     * Sets the response class.
     *
     * @param responseClass the new response class
     */
    public void setResponseClass(Class<T> responseClass) {
        this.responseClass = responseClass;
    }

    /**
     * Gets the custom repo listener.
     *
     * @return the custom repo listener
     */
    public CustomRepoListener getCustomRepoListener() {
        return customRepoListener;
    }

    /**
     * Sets the custom repo listener.
     *
     * @param customRepoListener the new custom repo listener
     */
    public void setCustomRepoListener(CustomRepoListener customRepoListener) {
        this.customRepoListener = customRepoListener;
    }

    /**
     * Gets the url.
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the url.
     *
     * @param url the new url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * The Interface Method.
     */
    public interface Method {
        
        /** The deprecated get or post. */
        int DEPRECATED_GET_OR_POST = -1;
        
        /** The get. */
        int GET = 0;
        
        /** The post. */
        int POST = 1;
        
        /** The put. */
        int PUT = 2;
        
        /** The delete. */
        int DELETE = 3;
        
        /** The head. */
        int HEAD = 4;
        
        /** The options. */
        int OPTIONS = 5;
        
        /** The trace. */
        int TRACE = 6;
        
        /** The patch. */
        int PATCH = 7;
    }
}

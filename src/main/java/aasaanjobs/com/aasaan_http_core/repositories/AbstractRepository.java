package aasaanjobs.com.aasaan_http_core.repositories;

import android.app.ProgressDialog;
import android.content.Context;


import org.json.JSONObject;

import aasaanjobs.com.aasaan_http_core.models.BaseDO;
import aasaanjobs.com.aasaan_http_core.utils.Listeners.CustomRepoListListener;
import aasaanjobs.com.aasaan_http_core.utils.Listeners.CustomRepoListener;

/**
 * Created by dineshsingh on 19/02/15.
 */
public abstract class AbstractRepository<T extends BaseDO> implements BaseRepository {


    protected BaseDO model;
    protected String url;
    protected CustomRepoListener customRepoListener;
    protected Class<T> responseClass;

    protected ProgressDialog progressDialog;


    protected Context context;

    @Override
    public void setContext(Context context) {

        this.context = context;
    }

    @Override
    public <T> void sendRequest(Class<T> clazz, String url, int requestType, CustomRepoListener<T> listener) {

        setUrl(url);
        setCustomRepoListener(listener);
        callMethodByRequestType(requestType, clazz);

    }

    @Override
    public <T> void sendRequest(Class<T> clazz, int requestType, CustomRepoListener<T> listener) {
        setCustomRepoListener(listener);
        callMethodByRequestType(requestType, clazz);
    }

    @Override
    public <T> void sendRequest(Class<T> clazz, String url, int requestType) {
        callMethodByRequestType(requestType, clazz);
    }

    @Override
    public <T> void get(Class<T> clazz, String url, CustomRepoListener<T> listener) {
        get(clazz, listener);
    }

    @Override
    public <T> void getList(Class<T> clazz, String url, CustomRepoListListener<T> listener) {
        getList(clazz, listener);

    }

    @Override
    public <T> void post(Class<T> clazz, String url, JSONObject request, CustomRepoListener<T> listener) {

        post(clazz, request, listener);
    }

    @Override
    public <T> void put(Class<T> clazz, String url, JSONObject request, CustomRepoListener<T> listener) {
        put(clazz, request, listener);

    }

    @Override
    public <T> void patch(Class<T> clazz, String url, JSONObject request, CustomRepoListener<T> listener) {
        patch(clazz, request, listener);

    }

    @Override
    public <T> void delete(Class<T> clazz, String url, JSONObject request, CustomRepoListener<T> listener) {

        delete(clazz, request, listener);
    }

    public Class<T> getResponseClass() {
        return responseClass;
    }

    public void setResponseClass(Class<T> responseClass) {
        this.responseClass = responseClass;
    }

    public CustomRepoListener getCustomRepoListener() {
        return customRepoListener;
    }

    public void setCustomRepoListener(CustomRepoListener customRepoListener) {
        this.customRepoListener = customRepoListener;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public interface Method {
        int DEPRECATED_GET_OR_POST = -1;
        int GET = 0;
        int POST = 1;
        int PUT = 2;
        int DELETE = 3;
        int HEAD = 4;
        int OPTIONS = 5;
        int TRACE = 6;
        int PATCH = 7;
    }
}

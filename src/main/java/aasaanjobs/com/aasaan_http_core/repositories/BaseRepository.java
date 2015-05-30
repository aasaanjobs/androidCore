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

/**
 * Created by dineshsingh on 21/02/15.
 */
public interface BaseRepository {


    public void setContext(Context context);

    public <T> void get(Class<T> clazz, String url, CustomRepoListener<T> listener);

    public <T> void get(Class<T> clazz, CustomRepoListener<T> listener);

    public <T> void get(Class<T> clazz, JSONObject requestObject, CustomRepoListener<T> listener);


    public <T> void getList(Class<T> clazz, String url, CustomRepoListListener<T> listener);

    public <T> void getList(Class<T> clazz, CustomRepoListListener<T> listener);

    public <T> void post(Class<T> clazz, JSONObject requestObject, CustomRepoListener<T> listener);

    public <T> void post(Class<T> clazz, String url, JSONObject requestObject, CustomRepoListener<T> listener);

    public <T> void post(Class<T> c, T request, CustomRepoListener<T> listener) throws IOException, JSONException;

    public <T> void put(Class<T> clazz, JSONObject requestObject, CustomRepoListener<T> listener);

    public <T> void put(Class<T> clazz, String url, JSONObject requestObject, CustomRepoListener<T> listener);

    public <T> void put(Class<T> c, T request, CustomRepoListener<T> listener);

    public <T> void patch(Class<T> clazz, JSONObject requestObject, CustomRepoListener<T> listener);

    public <T> void patch(Class<T> clazz, String url, JSONObject requestObject, CustomRepoListener<T> listener);

    public <T> void patch(Class<T> c, T request, CustomRepoListener<T> listener) throws IOException, JSONException;

    public <T> void delete(Class<T> clazz, JSONObject requestObject, CustomRepoListener<T> listener);

    public <T> void delete(Class<T> clazz, String url, JSONObject requestObject, CustomRepoListener<T> listener);

    public <T> void delete(Class<T> c, T request, CustomRepoListener<T> listener);


    public <T> void sendRequest(Class<T> clazz, String url, int requestType, CustomRepoListener<T> listener);

    public <T> void sendRequest(Class<T> clazz, int requestType, CustomRepoListener<T> listener);

    public <T> void sendRequest(Class<T> clazz, String url, int requestType);

    public <T> void callMethodByRequestType(final int requestType, JSONObject requestObject, final Class<T> clazz);

    public <T> void callMethodByRequestType(final int requestType, final Class<T> clazz);

    //public <T> void getListAndSave(final Class<T> clazz, final CustomRepoListListener<T> listener);

    public <T> void getAndSave(final Class<T> clazz, final CustomRepoListener<T> listener);

    public <T> void getAndSave(final Class<T> clazz, final CustomRepoListener<T> listener, boolean showLoadingDialogue);

    public <T> void callMethodByRequestTypeAndSave(final int requestType, final Class<T> clazz, boolean showLoadingDialogue);


    <T> void get(Class<T> c, CustomRepoListener<T> listener, String url);

    public <T extends BaseDO, P extends BaseResponseDO> void post(Class<P> c, T request, CustomRepoListener<P> listener);

    void getString(CustomRepoListener<String> customRepoListener, String url);

    <T> void uploadFile(Class<T> c, File file, String url, CustomRepoListener<T> listener);

    void downloadFile(String url, int type, CustomRepoListener<File> listener);
}

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
 * Created by dineshsingh on 19/02/15.
 */
public class CustomServiceImpl extends AbstractService implements CustomService {

    private Context context;
    private CustomRepository repository;
    private BaseDO model;


    public CustomServiceImpl(Context context, BaseDO model) {
        setContext(context);
        this.model = model;
        this.repository = new VolleyRepositoryImpl(context, model);
    }


    public BaseDO getModel() {
        return model;
    }

    public void setModel(BaseDO model) {
        this.model = model;
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public <T> void get(Class<T> c, CustomRepoListener<T> listener) {
        repository.get(c, listener);

    }

    @Override
    public <T> void get(Class<T> c, CustomRepoListener<T> listener, String url) {
        repository.get(c, listener, url);
    }

    @Override
    public <T> void get(Class<T> c, JSONObject requestObject, CustomRepoListener<T> listener) {
        repository.get(c, requestObject, listener);
    }

    @Override
    public <T> void getList(Class<T> c, CustomRepoListListener<T> listener) {
        repository.getList(c, listener);
    }

    @Override
    public <T> void add(Class<T> c, T request, CustomRepoListener<T> listener) {
        try {
            repository.post(c, request, listener);
        } catch (IOException e) {
            e.printStackTrace();
            listener.onError(e);
        } catch (JSONException e) {
            e.printStackTrace();
            listener.onError(e);
        }

    }

    @Override
    public <T> void add(Class<T> c, JSONObject request, CustomRepoListener<T> listener) {

        try {
            repository.post(c, request, listener);
        } catch (Exception e) {
            listener.onError(e);
        }
    }

    @Override
    public <T> void patch(Class<T> c, T request, CustomRepoListener<T> listener) {

        //  Volley.newRequestQueue(context.getApplicationContext(), new OkHttpStack()).add(request);
        try {
            repository.patch(c, request, listener);
        } catch (IOException e) {
            e.printStackTrace();
            listener.onError(e);
        } catch (JSONException e) {
            e.printStackTrace();
            listener.onError(e);
        }
    }

    @Override
    public <T> void patch(Class<T> c, JSONObject request, CustomRepoListener<T> listener) {
        try {
            repository.patch(c, request, listener);
        } catch (Exception e) {
            listener.onError(e);
        }
    }

    @Override
    public <T extends BaseDO, P extends BaseResponseDO> void add(Class<P> c, T request, CustomRepoListener<P> listener) {

        try {
            repository.post(c, request, listener);
        } catch (Exception e) {
            listener.onError(e);
        }
    }


    @Override

    public <T> void update(Class<T> c, T request, CustomRepoListener<T> listener) {
        try {
            repository.post(c, request, listener);
        } catch (Exception e) {


            listener.onError(e);
        }


    }

    @Override
    public <T> void delete(Class<T> c, T request, CustomRepoListener<T> listener) {
        repository.delete(c, request, listener);
    }

    @Override
    public <T> void getAndSave(Class<T> clazz, CustomRepoListener<T> listener, boolean showLoadingDialogue) {
        repository.getAndSave(clazz, listener, showLoadingDialogue);
    }

    @Override
    public <T> void getAndSave(Class<T> clazz, CustomRepoListener<T> listener) {
        repository.getAndSave(clazz, listener);
    }

    @Override
    public void getString(CustomRepoListener<String> customRepoListener, String url) {
        repository.getString(customRepoListener, url);
    }

    @Override
    public <T> void uploadFile(Class<T> c, File file, String url, CustomRepoListener<T> listener) {
        repository.uploadFile(c, file, url, listener);
    }

    @Override
    public void downloadFile(String url, int type, CustomRepoListener<File> listener) {
        repository.downloadFile(url, type, listener);
    }


}

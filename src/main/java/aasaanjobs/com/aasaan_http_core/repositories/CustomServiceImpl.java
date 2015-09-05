package aasaanjobs.com.aasaan_http_core.repositories;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import aasaanjobs.com.aasaan_http_core.models.BaseDO;
import aasaanjobs.com.aasaan_http_core.models.BaseResponseDO;
import aasaanjobs.com.aasaan_http_core.utils.Listeners.CustomRepoListListener;
import aasaanjobs.com.aasaan_http_core.utils.Listeners.CustomRepoListener;

// TODO: Auto-generated Javadoc

/**
 * Created by dineshsingh on 19/02/15.
 */
public class CustomServiceImpl extends AbstractService implements CustomService {

    /**
     * The context.
     */
    protected Context context;

    /**
     * The repository.
     */
    private CustomRepository repository;

    /**
     * The model.
     */
    private BaseDO model;


    /**
     * Instantiates a new custom service impl.
     *
     * @param context the context
     * @param model   the model
     */
    public CustomServiceImpl(Context context, BaseDO model) {
        setContext(context);
        this.model = model;
        this.repository = new VolleyRepositoryImpl(context, model);
    }


    /**
     * Gets the model.
     *
     * @return the model
     */
    public BaseDO getModel() {
        return model;
    }

    /**
     * Sets the model.
     *
     * @param model the new model
     */
    public void setModel(BaseDO model) {
        this.model = model;
    }

    /* (non-Javadoc)
     * @see aasaanjobs.com.aasaan_http_core.repositories.CustomService#setContext(Context)
     */
    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    /* (non-Javadoc)
     * @see aasaanjobs.com.aasaan_http_core.repositories.CustomService#get(java.lang.Class, aasaanjobs.com.aasaan_http_core.utils.Listeners.CustomRepoListener)
     */
    @Override
    public <T> void get(Class<T> c, CustomRepoListener<T> listener) {
        repository.get(c, listener);

    }

    @Override
    public <T> void get(Class<T> c, CustomRepoListener<T> listener, boolean showLoadingDialogue) {
        repository.get(c, listener, showLoadingDialogue);
    }

    /* (non-Javadoc)
     * @see aasaanjobs.com.aasaan_http_core.repositories.CustomService#get(java.lang.Class, aasaanjobs.com.aasaan_http_core.utils.Listeners.CustomRepoListener, java.lang.String)
     */
    @Override
    public <T> void get(Class<T> c, CustomRepoListener<T> listener, String url) {
        repository.get(c, listener, url);
    }

    @Override
    public <T> void get(Class<T> c, CustomRepoListener<T> listener, String url, boolean showLoadingDialogue) {
        repository.get(c, listener, url, showLoadingDialogue);
    }

    /* (non-Javadoc)
     * @see aasaanjobs.com.aasaan_http_core.repositories.CustomService#get(Class, JSONObject, CustomRepoListener)
     */
    @Override
    public <T> void get(Class<T> c, JSONObject requestObject, CustomRepoListener<T> listener) {
        repository.get(c, requestObject, listener);
    }

    @Override
    public <T> void get(Class<T> c, JSONObject requestObject, CustomRepoListener<T> listener, boolean showLoadingDialogue) {
        repository.get(c, requestObject, listener, showLoadingDialogue);
    }

    /* (non-Javadoc)
     * @see aasaanjobs.com.aasaan_http_core.repositories.CustomService#getList(java.lang.Class, aasaanjobs.com.aasaan_http_core.utils.Listeners.CustomRepoListListener)
     */
    @Override
    public <T> void getList(Class<T> c, CustomRepoListListener<T> listener) {
        repository.getList(c, listener);
    }

    @Override
    public <T> void getList(Class<T> c, CustomRepoListListener<T> listener, boolean showLoadingDialogue) {
        repository.getList(c, listener, showLoadingDialogue);
    }

    /* (non-Javadoc)
     * @see aasaanjobs.com.aasaan_http_core.repositories.CustomService#add(java.lang.Class, java.lang.Object, aasaanjobs.com.aasaan_http_core.utils.Listeners.CustomRepoListener)
     */
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
    public <T> void add(Class<T> c, T request, CustomRepoListener<T> listener, boolean showLoadingDialogue) {
        try {
            repository.post(c, request, listener, showLoadingDialogue);
        } catch (IOException e) {
            e.printStackTrace();
            listener.onError(e);
        } catch (JSONException e) {
            e.printStackTrace();
            listener.onError(e);
        }
    }


    /* (non-Javadoc)
     * @see aasaanjobs.com.aasaan_http_core.repositories.CustomService#add(Class, JSONObject, CustomRepoListener)
     */
    @Override
    public <T> void add(Class<T> c, JSONObject request, CustomRepoListener<T> listener) {

        try {
            repository.post(c, request, listener);
        } catch (Exception e) {
            listener.onError(e);
        }
    }

    @Override
    public <T> void add(Class<T> c, JSONObject request, CustomRepoListener<T> listener, boolean showLoadingDialogue) {
        try {
            repository.post(c, request, listener, showLoadingDialogue);
        } catch (Exception e) {
            listener.onError(e);
        }
    }

    /* (non-Javadoc)
     * @see aasaanjobs.com.aasaan_http_core.repositories.CustomService#patch(java.lang.Class, java.lang.Object, aasaanjobs.com.aasaan_http_core.utils.Listeners.CustomRepoListener)
     */
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
    public <T> void patch(Class<T> c, T request, CustomRepoListener<T> listener, boolean showLoadingDialogue) {
        try {
            repository.patch(c, request, listener, showLoadingDialogue);
        } catch (IOException e) {
            e.printStackTrace();
            listener.onError(e);
        } catch (JSONException e) {
            e.printStackTrace();
            listener.onError(e);
        }
    }

    /* (non-Javadoc)
     * @see aasaanjobs.com.aasaan_http_core.repositories.CustomService#patch(Class, JSONObject, CustomRepoListener)
     */
    @Override
    public <T> void patch(Class<T> c, JSONObject request, CustomRepoListener<T> listener) {
        try {
            repository.patch(c, request, listener);
        } catch (Exception e) {
            listener.onError(e);
        }
    }

    @Override
    public <T> void patch(Class<T> c, JSONObject request, CustomRepoListener<T> listener, boolean showLoadingDialogue) {
        try {
            repository.patch(c, request, listener, showLoadingDialogue);
        } catch (Exception e) {
            listener.onError(e);
        }
    }

    @Override
    public <T extends BaseDO, P extends BaseResponseDO> void patch(Class<P> c, T request, CustomRepoListener<P> listener) {
        try {
            repository.patch(c, request, listener);
        } catch (Exception e) {
            listener.onError(e);
        }
    }

    @Override
    public <T extends BaseDO, P extends BaseResponseDO> void patch(Class<P> c, T request, CustomRepoListener<P> listener, boolean showLoadingDialogue) {
        try {
            repository.patch(c, request, listener, showLoadingDialogue);
        } catch (Exception e) {
            listener.onError(e);
        }
    }

    /* (non-Javadoc)
     * @see aasaanjobs.com.aasaan_http_core.repositories.CustomService#add(java.lang.Class, aasaanjobs.com.aasaan_http_core.models.BaseDO, aasaanjobs.com.aasaan_http_core.utils.Listeners.CustomRepoListener)
     */
    @Override
    public <T extends BaseDO, P extends BaseResponseDO> void add(Class<P> c, T request, CustomRepoListener<P> listener) {

        try {
            repository.post(c, request, listener);
        } catch (Exception e) {
            listener.onError(e);
        }
    }

    @Override
    public <T extends BaseDO, P extends BaseResponseDO> void add(Class<P> c, T request, CustomRepoListener<P> listener, boolean showLoadingDialogue) {
        try {
            repository.post(c, request, listener, showLoadingDialogue);
        } catch (Exception e) {
            listener.onError(e);
        }
    }


    /* (non-Javadoc)
     * @see aasaanjobs.com.aasaan_http_core.repositories.CustomService#update(java.lang.Class, java.lang.Object, aasaanjobs.com.aasaan_http_core.utils.Listeners.CustomRepoListener)
     */
    @Override

    public <T> void update(Class<T> c, T request, CustomRepoListener<T> listener) {
        try {
            repository.post(c, request, listener);
        } catch (Exception e) {


            listener.onError(e);
        }
    }

    @Override
    public <T> void update(Class<T> c, T request, CustomRepoListener<T> listener, boolean showLoadingDialogue) {
        try {
            repository.post(c, request, listener, showLoadingDialogue);
        } catch (Exception e) {
            listener.onError(e);
        }
    }

    /* (non-Javadoc)
     * @see aasaanjobs.com.aasaan_http_core.repositories.CustomService#delete(java.lang.Class, java.lang.Object, aasaanjobs.com.aasaan_http_core.utils.Listeners.CustomRepoListener)
     */
    @Override
    public <T> void delete(Class<T> c, T request, CustomRepoListener<T> listener) {
        repository.delete(c, request, listener);
    }

    @Override
    public <T> void delete(Class<T> c, T request, CustomRepoListener<T> listener, boolean showLoadingDialogue) {
        repository.delete(c, request, listener, showLoadingDialogue);
    }

    /* (non-Javadoc)
     * @see aasaanjobs.com.aasaan_http_core.repositories.CustomService#getAndSave(java.lang.Class, aasaanjobs.com.aasaan_http_core.utils.Listeners.CustomRepoListener, boolean)
     */
    @Override
    public <T> void getAndSave(Class<T> clazz, CustomRepoListener<T> listener, boolean showLoadingDialogue) {
        repository.getAndSave(clazz, listener, showLoadingDialogue);
    }

    /* (non-Javadoc)
     * @see aasaanjobs.com.aasaan_http_core.repositories.CustomService#getAndSave(java.lang.Class, aasaanjobs.com.aasaan_http_core.utils.Listeners.CustomRepoListener)
     */
    @Override
    public <T> void getAndSave(Class<T> clazz, CustomRepoListener<T> listener) {
        repository.getAndSave(clazz, listener);
    }

    /* (non-Javadoc)
     * @see aasaanjobs.com.aasaan_http_core.repositories.CustomService#getString(aasaanjobs.com.aasaan_http_core.utils.Listeners.CustomRepoListener, java.lang.String)
     */
    @Override
    public void getString(CustomRepoListener<String> customRepoListener, String url) {
        repository.getString(customRepoListener, url);
    }

    @Override
    public void getString(CustomRepoListener<String> customRepoListener, String url, boolean showLoadingDialogue) {
        repository.getString(customRepoListener, url, showLoadingDialogue);
    }

    /* (non-Javadoc)
     * @see aasaanjobs.com.aasaan_http_core.repositories.CustomService#uploadFile(java.lang.Class, java.io.File, java.lang.String, aasaanjobs.com.aasaan_http_core.utils.Listeners.CustomRepoListener)
     */
    @Override
    public <T> void uploadFile(Class<T> c, File file, String url, CustomRepoListener<T> listener) {
        repository.uploadFile(c, file, url, listener);
    }

    @Override
    public <T> void uploadFile(Class<T> c, File file, HashMap<String, String> params, String url, CustomRepoListener<T> listener) {
        repository.uploadFile(c, file, params, url, listener);
    }

    @Override
    public <T> void uploadFile(Class<T> c, File file, HashMap<String, String> params, String url, CustomRepoListener<T> listener, long fileLength, MultiPartRequest.MultipartProgressListener progressListener) {
        repository.uploadFile(c, file, params, url, listener, fileLength, progressListener);
    }

    /* (non-Javadoc)
     * @see aasaanjobs.com.aasaan_http_core.repositories.CustomService#downloadFile(java.lang.String, int, aasaanjobs.com.aasaan_http_core.utils.Listeners.CustomRepoListener)
     */
    @Override
    public void downloadFile(String url, int type, CustomRepoListener<File> listener) {
        repository.downloadFile(url, type, listener);
    }


}

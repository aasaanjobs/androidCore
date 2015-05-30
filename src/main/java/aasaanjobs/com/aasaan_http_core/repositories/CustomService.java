package aasaanjobs.com.aasaan_http_core.repositories;

import android.content.Context;

import org.json.JSONObject;

import java.io.File;

import aasaanjobs.com.aasaan_http_core.models.BaseDO;
import aasaanjobs.com.aasaan_http_core.models.BaseResponseDO;
import aasaanjobs.com.aasaan_http_core.utils.Listeners.CustomRepoListListener;
import aasaanjobs.com.aasaan_http_core.utils.Listeners.CustomRepoListener;


/**
 * Created by dineshsingh on 21/02/15.
 */
public interface CustomService extends BaseService {

    public void setContext(Context context);

    public <T> void get(Class<T> c, CustomRepoListener<T> listener);

    public <T> void get(Class<T> c, CustomRepoListener<T> listener, String url);

    public <T> void get(Class<T> c, JSONObject requestObject, CustomRepoListener<T> listener);

    public <T> void getList(Class<T> c, CustomRepoListListener<T> listener);


    //public <T > void add(Class<T> c, CustomRepoListener<T> listener);
    public <T> void add(Class<T> c, T request, CustomRepoListener<T> listener);

    public <T> void add(Class<T> c, JSONObject request, CustomRepoListener<T> listener);

    public <T> void patch(Class<T> c, T request, CustomRepoListener<T> listener);

    public <T> void patch(Class<T> c, JSONObject request, CustomRepoListener<T> listener);

    public <T extends BaseDO, P extends BaseResponseDO> void add(Class<P> c, T request, CustomRepoListener<P> listener);


    //public <T > void update(Class<T> c, CustomRepoListener<T> listener);
    public <T> void update(Class<T> c, T request, CustomRepoListener<T> listener);

    public <T> void delete(Class<T> c, T request, CustomRepoListener<T> listener);

    // public void getAndSave(Class<SearchDO> searchDOClass, CustomRepoListener<SearchDO> customRepoListener);
    //public <T > void delete(Class<T> c, CustomRepoListener<T> listener);

    public <T> void getAndSave(Class<T> clazz, CustomRepoListener<T> listener, boolean showLoadingDialogue);

    public <T> void getAndSave(Class<T> clazz, CustomRepoListener<T> listener);

    void getString(CustomRepoListener<String> customRepoListener, String url);

    public <T> void uploadFile(Class<T> c, File file, String url, CustomRepoListener<T> listener);

    public void downloadFile(String url, int type, CustomRepoListener<File> listener);


}

package aasaanjobs.com.aasaan_http_core.repositories;

import java.util.HashMap;

/**
 * Created by dineshsingh on 21/02/15.
 */
interface CustomRepository extends BaseRepository {
    void setHeaders(HashMap<String, String> headers);


    //public  void getAndSave(Class<SearchDO> searchDOClass, CustomRepoListener<SearchDO> customRepoListener);
}

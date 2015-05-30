package aasaanjobs.com.aasaan_http_core.models;


import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by dineshsingh on 19/02/15.
 */

/**
 * Base Class for all Data Object
 *
 * @param <T>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BaseDO<T extends BasePayload> {

    protected String url;
    protected String getURL;
    protected String putURL;
    protected String postURL;
    protected String patchURL;
    protected String deleteURL;
    protected MetaDO meta;
    protected List<T> objects;

    public List<T> getObjects() {
        return objects;
    }

    public void setObjects(List<T> objects) {
        this.objects = objects;
    }

    public MetaDO getMeta() {
        return meta;
    }

    public void setMeta(MetaDO meta) {
        this.meta = meta;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGetURL() {
        return getURL;
    }

    public void setGetURL(String getURL) {
        this.getURL = getURL;
    }

    public String getPutURL() {
        return putURL;
    }

    public void setPutURL(String putURL) {
        this.putURL = putURL;
    }

    public String getPostURL() {
        return postURL;
    }

    public void setPostURL(String postURL) {
        this.postURL = postURL;
    }

    public String getDeleteURL() {
        return deleteURL;
    }

    public void setDeleteURL(String deleteURL) {
        this.deleteURL = deleteURL;
    }

    public void setPostURLById(String id) {
        setPostURL(getUrl() + id + "/");
    }

    public void updateUrlWithAuth(String authUrl) {
        setUrl(getUrl() + "?" + authUrl);
    }

    public String getPatchURL() {
        return patchURL;
    }

    public void setPatchURL(String patchURL) {
        this.patchURL = patchURL;
    }
}

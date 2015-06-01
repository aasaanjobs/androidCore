package aasaanjobs.com.aasaan_http_core.models;


import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * Created by dineshsingh on 19/02/15.
 *
 * @param <T> the generic type
 */

/**
 * Base Class for all Data Object
 *
 * @param <T>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BaseDO<T extends BasePayload> {

    /** The url. */
    protected String url;
    
    /** The get url. */
    protected String getURL;
    
    /** The put url. */
    protected String putURL;
    
    /** The post url. */
    protected String postURL;
    
    /** The patch url. */
    protected String patchURL;
    
    /** The delete url. */
    protected String deleteURL;
    
    /** The meta. */
    protected MetaDO meta;
    
    /** The objects. */
    protected List<T> objects;

    /**
     * Gets the objects.
     *
     * @return the objects
     */
    public List<T> getObjects() {
        return objects;
    }

    /**
     * Sets the objects.
     *
     * @param objects the new objects
     */
    public void setObjects(List<T> objects) {
        this.objects = objects;
    }

    /**
     * Gets the meta.
     *
     * @return the meta
     */
    public MetaDO getMeta() {
        return meta;
    }

    /**
     * Sets the meta.
     *
     * @param meta the new meta
     */
    public void setMeta(MetaDO meta) {
        this.meta = meta;
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
     * Gets the gets the url.
     *
     * @return the gets the url
     */
    public String getGetURL() {
        return getURL;
    }

    /**
     * Sets the gets the url.
     *
     * @param getURL the new gets the url
     */
    public void setGetURL(String getURL) {
        this.getURL = getURL;
    }

    /**
     * Gets the put url.
     *
     * @return the put url
     */
    public String getPutURL() {
        return putURL;
    }

    /**
     * Sets the put url.
     *
     * @param putURL the new put url
     */
    public void setPutURL(String putURL) {
        this.putURL = putURL;
    }

    /**
     * Gets the post url.
     *
     * @return the post url
     */
    public String getPostURL() {
        return postURL;
    }

    /**
     * Sets the post url.
     *
     * @param postURL the new post url
     */
    public void setPostURL(String postURL) {
        this.postURL = postURL;
    }

    /**
     * Gets the delete url.
     *
     * @return the delete url
     */
    public String getDeleteURL() {
        return deleteURL;
    }

    /**
     * Sets the delete url.
     *
     * @param deleteURL the new delete url
     */
    public void setDeleteURL(String deleteURL) {
        this.deleteURL = deleteURL;
    }

    /**
     * Sets the post url by id.
     *
     * @param id the new post url by id
     */
    public void setPostURLById(String id) {
        setPostURL(getUrl() + id + "/");
    }

    /**
     * Update url with auth.
     *
     * @param authUrl the auth url
     */
    public void updateUrlWithAuth(String authUrl) {
        setUrl(getUrl() + "?" + authUrl);
    }

    /**
     * Gets the patch url.
     *
     * @return the patch url
     */
    public String getPatchURL() {
        return patchURL;
    }

    /**
     * Sets the patch url.
     *
     * @param patchURL the new patch url
     */
    public void setPatchURL(String patchURL) {
        this.patchURL = patchURL;
    }
}

package aasaanjobs.com.aasaan_http_core.models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

// TODO: Auto-generated Javadoc
/**
 * Created by dineshsingh on 05/03/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BasePayload {

    /** The id. */
    String id;

    /**
     * Gets the id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(String id) {
        this.id = id;
    }
}

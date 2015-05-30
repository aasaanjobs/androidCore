package aasaanjobs.com.aasaan_http_core.models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Created by dineshsingh on 05/03/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BasePayload {

    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

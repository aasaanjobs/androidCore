package aasaanjobs.com.aasaan_http_core.repositories;

import java.util.HashMap;

/**
 * Created by aditya on 9/2/15.
 */
public class CustomHeaders {

    private final static HashMap<String, String> headers = new HashMap<>();

    public static HashMap<String, String> getHeaders() {
        return headers;
    }
}

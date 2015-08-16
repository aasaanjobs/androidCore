package aasaanjobs.com.aasaan_http_core.repositories;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

// TODO: Auto-generated Javadoc

/**
 * Created by dineshsingh on 01/04/15.
 */
public class CustomJsonObjectRequest extends JsonObjectRequest {

    /**
     * The Constant EXTRA_SOCKET_TIMEOUT_MS.
     */
    private static final int EXTRA_SOCKET_TIMEOUT_MS = 15000;

    /**
     * The Constant NUMBER_OF_RETRIES.
     */
    private static final int NUMBER_OF_RETRIES = 0;
    /**
     * The default retry policy.
     */
    private final DefaultRetryPolicy defaultRetryPolicy;

    /**
     * Instantiates a new custom json object request.
     *
     * @param method        the method
     * @param url           the url
     * @param jsonRequest   the json request
     * @param listener      the listener
     * @param errorListener the error listener
     */
    public CustomJsonObjectRequest(int method, String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
        defaultRetryPolicy = new DefaultRetryPolicy(EXTRA_SOCKET_TIMEOUT_MS, NUMBER_OF_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        setRetryPolicy(defaultRetryPolicy);
    }

    /**
     * Instantiates a new custom json object request.
     *
     * @param url           the url
     * @param jsonRequest   the json request
     * @param listener      the listener
     * @param errorListener the error listener
     */
    public CustomJsonObjectRequest(String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(url, jsonRequest, listener, errorListener);
        defaultRetryPolicy = new DefaultRetryPolicy(EXTRA_SOCKET_TIMEOUT_MS, NUMBER_OF_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        setRetryPolicy(defaultRetryPolicy);
    }

    /**
     * Parses the network error.
     *
     * @param volleyError the volley error
     * @return the volley error
     */
    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError) {
        if (volleyError.networkResponse != null && volleyError.networkResponse.data != null) {


            try {
                VolleyError error = new VolleyError(new String(volleyError.networkResponse.data));
                String responseString = error.getMessage();
                JSONObject responseObject = new JSONObject(responseString);
                error = new VolleyError(responseObject.getString("message"));
                volleyError = error;
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

        }

        return volleyError;
    }


    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        final Response<JSONObject> networkResponse = super.parseNetworkResponse(response);
        try {
            networkResponse.result.put("responseCode", response.statusCode);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return networkResponse;
    }

}


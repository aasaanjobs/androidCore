package aasaanjobs.com.aasaan_http_core.repositories;

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


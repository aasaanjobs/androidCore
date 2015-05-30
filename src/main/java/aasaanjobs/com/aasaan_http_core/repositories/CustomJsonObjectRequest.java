package aasaanjobs.com.aasaan_http_core.repositories;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dineshsingh on 01/04/15.
 */
public class CustomJsonObjectRequest extends JsonObjectRequest {
    public CustomJsonObjectRequest(int method, String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
    }

    public CustomJsonObjectRequest(String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(url, jsonRequest, listener, errorListener);
    }

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
}


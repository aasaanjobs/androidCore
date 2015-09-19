package aasaanjobs.com.aasaan_http_core.repositories;

/**
 * Created by dineshsingh on 15/09/15.
 */

import android.net.Uri;
import android.os.AsyncTask;

import com.android.volley.toolbox.HttpClientStack;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import aasaanjobs.com.aasaan_http_core.utils.Listeners.CustomRepoListener;


/**
 * param 0 : url
 * param 1 : request body eg. KEY : VALUE
 */
public class PatchTask<T> extends AsyncTask<String, Void, T> {

    private final CustomRepoListener<T> customRepoListener;
    private Class<T> clazz;
    private boolean isSuccesfull = false;

    public PatchTask(CustomRepoListener repoListener, Class<T> clazz) {
        this.customRepoListener = repoListener;
        this.clazz = clazz;
    }

    @Override
    protected T doInBackground(String... params) {
        JSONObject obj = null;
        try {
            obj = new JSONObject(params[1]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url = params[0];

        HttpClient httpClient = new DefaultHttpClient();
        HttpClientStack.HttpPatch httpPatch = new HttpClientStack.HttpPatch(url);

        StringEntity se = null;
        try {
            if (obj != null)
                se = new StringEntity(obj.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        final String user_name, api_key;
        Uri uri = Uri.parse(url);
        if (uri != null) {
            user_name = uri.getQueryParameter("username");
            api_key = uri.getQueryParameter("api_key");
            if (user_name != null & api_key != null) {
                httpPatch.setHeader("Authorization", "ApiKey " + user_name + ":" + api_key);
            }
        }
        httpPatch.setEntity(se);
        httpPatch.setHeader("Accept", "application/json");
        httpPatch.setHeader("Content-type", "application/json");
        try {
            BasicHttpResponse response = (BasicHttpResponse) httpClient.execute(httpPatch);
            if (response != null) {
                int responseCode = response.getStatusLine().getStatusCode();
                if (responseCode == 202 || responseCode == 200) {
                    isSuccesfull = true;
                    try {
                        final String responseText = EntityUtils.toString(response.getEntity());
                        ObjectMapper mapper = new ObjectMapper();
                        T t = null;
                        t = mapper.readValue(responseText, clazz);
                        return t;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(T response) {
        if (isSuccesfull) {
            customRepoListener.onSuccess(response);
        } else {
            customRepoListener.onError(new Exception("Response object null"));
        }
    }
}

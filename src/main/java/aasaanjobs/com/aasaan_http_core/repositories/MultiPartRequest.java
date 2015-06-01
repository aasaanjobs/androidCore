package aasaanjobs.com.aasaan_http_core.repositories;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;

import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class MultiPartRequest.
 */
public class MultiPartRequest extends Request<JSONObject> {

// private MultipartEntity entity = new MultipartEntity();

    /** The Constant FILE_PART_NAME. */
private static final String FILE_PART_NAME = "file";
    
    /** The m listener. */
    private final Response.Listener<JSONObject> mListener;
    
    /** The m file part. */
    private final File mFilePart;
    
    /** The m string part. */
    private final Map<String, String> mStringPart;
    
    /** The entity. */
    MultipartEntityBuilder entity = MultipartEntityBuilder.create();
    
    /** The httpentity. */
    HttpEntity httpentity;

    /**
     * Instantiates a new multi part request.
     *
     * @param url the url
     * @param errorListener the error listener
     * @param listener the listener
     * @param file the file
     * @param mStringPart the m string part
     */
    public MultiPartRequest(String url, Response.ErrorListener errorListener,
                            Response.Listener<JSONObject> listener, File file,
                            Map<String, String> mStringPart) {
        super(Method.POST, url, errorListener);

        mListener = listener;
        mFilePart = file;
        this.mStringPart = mStringPart;
        entity.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        buildMultipartEntity();
    }

    /**
     * Adds the string body.
     *
     * @param param the param
     * @param value the value
     */
    public void addStringBody(String param, String value) {
        mStringPart.put(param, value);
    }

    /**
     * Builds the multipart entity.
     */
    private void buildMultipartEntity() {
        entity.addPart(FILE_PART_NAME, new FileBody(mFilePart));
        if (mStringPart != null) {
            for (Map.Entry<String, String> entry : mStringPart.entrySet()) {
                entity.addTextBody(entry.getKey(), entry.getValue());
            }
        }
    }


    /**
     * Gets the body content type.
     *
     * @return the body content type
     */
    @Override
    public String getBodyContentType() {
        return httpentity.getContentType().getValue();
    }

    /**
     * Gets the body.
     *
     * @return the body
     * @throws AuthFailureError the auth failure error
     */
    @Override
    public byte[] getBody() throws AuthFailureError {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            httpentity = entity.build();
            httpentity.writeTo(bos);
        } catch (IOException e) {
            VolleyLog.e("IOException writing to ByteArrayOutputStream");
        }
        return bos.toByteArray();
    }

    /**
     * Parses the network response.
     *
     * @param response the response
     * @return the response
     */
    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString =
                    new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(new JSONObject(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }

    /**
     * Deliver response.
     *
     * @param response the response
     */
    @Override
    protected void deliverResponse(JSONObject response) {
        mListener.onResponse(response);
    }
}
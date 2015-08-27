package aasaanjobs.com.aasaan_http_core.repositories;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
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
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;


public class MultiPartRequest extends Request<JSONObject> {

// private MultipartEntity entity = new MultipartEntity();

    /**
     * The Constant FILE_PART_NAME.
     */
    private static final String FILE_PART_NAME = "file";

    /**
     * The m listener.
     */
    private final Response.Listener<JSONObject> mListener;

    /**
     * The m file part.
     */
    private final File mFilePart;
    private long fileLength = 0L;

    private MultipartProgressListener multipartProgressListener;
    /**
     * The m string part.
     */
    private final Map<String, String> mStringPart;

    /**
     * The entity.
     */
    MultipartEntityBuilder entity = MultipartEntityBuilder.create();

    /**
     * The httpentity.
     */
    HttpEntity httpentity;

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
     * Instantiates a new multi part request.
     *
     * @param url           the url
     * @param errorListener the error listener
     * @param listener      the listener
     * @param file          the file
     * @param mStringPart   the m string part
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
        defaultRetryPolicy = new DefaultRetryPolicy(EXTRA_SOCKET_TIMEOUT_MS, NUMBER_OF_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        setRetryPolicy(defaultRetryPolicy);
    }

    public MultiPartRequest(String url, Response.ErrorListener errorListener,
                            Response.Listener<JSONObject> listener, File file,
                            Map<String, String> mStringPart, long fileLength, MultipartProgressListener progressListner) {

        super(Method.POST, url, errorListener);

        mListener = listener;
        mFilePart = file;
        this.mStringPart = mStringPart;
        this.multipartProgressListener = progressListner;
        this.fileLength = fileLength;
        entity.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        buildMultipartEntity();
        defaultRetryPolicy = new DefaultRetryPolicy(EXTRA_SOCKET_TIMEOUT_MS, NUMBER_OF_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        setRetryPolicy(defaultRetryPolicy);
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

            if (multipartProgressListener != null) {
                httpentity.writeTo(new CountingOutputStream(bos, fileLength,
                        multipartProgressListener));
            } else {
                httpentity.writeTo(bos);
            }
        } catch (IOException e) {
            VolleyLog.e("IOException writing to ByteArrayOutputStream");
        }
        return bos.toByteArray();
    }

    public static interface MultipartProgressListener {
        void transferred(long transfered, int progress);
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

    public static class CountingOutputStream extends FilterOutputStream {
        private final MultipartProgressListener progListener;
        private long transferred;
        private long fileLength;

        public CountingOutputStream(final OutputStream out, long fileLength,
                                    final MultipartProgressListener listener) {
            super(out);
            this.fileLength = fileLength;
            this.progListener = listener;
            this.transferred = 0;
        }

        public void write(byte[] b, int off, int len) throws IOException {
            out.write(b, off, len);
            if (progListener != null) {
                this.transferred += len;
                int prog = (int) (transferred * 100 / fileLength);
                this.progListener.transferred(this.transferred, prog);
            }
        }

        public void write(int b) throws IOException {
            out.write(b);
            if (progListener != null) {
                this.transferred++;
                int prog = (int) (transferred * 100 / fileLength);
                this.progListener.transferred(this.transferred, prog);
            }
        }

    }

}
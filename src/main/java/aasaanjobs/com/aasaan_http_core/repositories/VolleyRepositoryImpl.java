package aasaanjobs.com.aasaan_http_core.repositories;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;


import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.JsonObject;


import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import aasaanjobs.com.aasaan_http_core.models.BaseDO;
import aasaanjobs.com.aasaan_http_core.models.BaseResponseDO;
import aasaanjobs.com.aasaan_http_core.utils.Listeners.CustomRepoListListener;
import aasaanjobs.com.aasaan_http_core.utils.Listeners.CustomRepoListener;

// TODO: Auto-generated Javadoc
/**
 * Created by dineshsingh on 19/02/15.
 *
 * @param <P> the generic type
 */
class VolleyRepositoryImpl<P extends BaseDO> extends AbstractCustomRepository implements VolleyRepository {


    /** The Constant EXTRA_SOCKET_TIMEOUT_MS. */
    private static final int EXTRA_SOCKET_TIMEOUT_MS = 15000;

    /** The Constant NUMBER_OF_RETRIES. */
    private static final int NUMBER_OF_RETRIES = 0;

    /** The Constant IMAGE_FILE. */
    private static final int IMAGE_FILE = 0;

    /** The Constant IMAGE_NAME. */
    private static final String IMAGE_NAME = "profile_pic";

    /** The default retry policy. */
    private final DefaultRetryPolicy defaultRetryPolicy;

    /** The url. */
    private String url;


    /**
     * Instantiates a new volley repository impl.
     *
     * @param context the context
     * @param model the model
     */
    public VolleyRepositoryImpl(Context context, BaseDO model) {
        setContext(context);
        url = "";
        this.model = model;

        HttpsTrustManager.allowAllSSL();
        defaultRetryPolicy = new DefaultRetryPolicy(EXTRA_SOCKET_TIMEOUT_MS, NUMBER_OF_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

    }

    /**
     * Gets the filename.
     *
     * @return the filename
     */
    public static String getFilename() {
        File file = new File(Environment.getExternalStorageDirectory().getPath(), "AasaanJobs/Profile photos");
        if (!file.exists()) {
            file.mkdirs();
        }
        String uriSting = (file.getAbsolutePath() + "/" + IMAGE_NAME + ".jpg");
        return uriSting;

    }

    /* (non-Javadoc)
     * @see aasaanjobs.com.aasaan_http_core.repositories.AbstractRepository#getUrl()
     */
    public String getUrl() {
        return url;
    }

    /* (non-Javadoc)
     * @see aasaanjobs.com.aasaan_http_core.repositories.AbstractRepository#setUrl(java.lang.String)
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /* (non-Javadoc)
     * @see aasaanjobs.com.aasaan_http_core.repositories.BaseRepository#get(java.lang.Class, aasaanjobs.com.aasaan_http_core.utils.Listeners.CustomRepoListener)
     */
    @Override
    public <T> void get(Class<T> clazz, CustomRepoListener<T> listener,boolean showLoadingDialogue ) {
        setUrl(model.getGetURL());

        setCustomRepoListener(listener);
        callMethodByRequestType(Request.Method.GET, clazz, showLoadingDialogue);


    }

    @Override
    public <T> void get(Class<T> clazz, String url, CustomRepoListener<T> listener, boolean showLoadingDialogue) {
        setUrl(model.getGetURL());

        setCustomRepoListener(listener);
        callMethodByRequestType(Request.Method.GET, clazz, showLoadingDialogue);
    }

    @Override
    public <T> void get(Class<T> clazz, CustomRepoListener<T> listener) {
        setUrl(model.getGetURL());

        setCustomRepoListener(listener);
        callMethodByRequestType(Request.Method.GET, clazz, true);


    }

    /* (non-Javadoc)
     * @see aasaanjobs.com.aasaan_http_core.repositories.BaseRepository#getList(java.lang.Class, aasaanjobs.com.aasaan_http_core.utils.Listeners.CustomRepoListListener)
     */
    @Override
    public <T> void getList(final Class<T> clazz, final CustomRepoListListener<T> listener) {
        initiateProgressDialogue();
        setUrl(model.getGetURL());
        JsonArrayRequest j = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                dismissProgressDialogue();
                List<T> t = null;
                try {
                    t = getListFromJsonArray(clazz, response, listener);
                } catch (Exception e) {
                    e.printStackTrace();
                    listener.onError(e);
                }
                listener.onSuccess(t);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dismissProgressDialogue();
                listener.onError(error);
            }
        });

        VolleySingleton.getInstance(context).addToRequestQueue(j);
    }

    @Override
    public <T> void getList(final Class<T> clazz, final CustomRepoListListener<T> listener, final boolean showLoadingDialogue) {
        if(showLoadingDialogue)
            initiateProgressDialogue();
        setUrl(model.getGetURL());
        JsonArrayRequest j = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(showLoadingDialogue)
                    dismissProgressDialogue();
                List<T> t = null;
                try {
                    t = getListFromJsonArray(clazz, response, listener);
                } catch (Exception e) {
                    e.printStackTrace();
                    listener.onError(e);
                }
                listener.onSuccess(t);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(showLoadingDialogue)
                    dismissProgressDialogue();
                listener.onError(error);
            }
        });

        VolleySingleton.getInstance(context).addToRequestQueue(j);
    }

    /**
     * Gets the list from json array.
     *
     * @param <T> the generic type
     * @param clazz the clazz
     * @param response the response
     * @param listener the listener
     * @return the list from json array
     * @throws JSONException the JSON exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private <T> List<T> getListFromJsonArray(Class<T> clazz, JSONArray response, CustomRepoListListener<T> listener) throws JSONException, IOException {
        List<T> t = new ArrayList<T>();
        int c = 0;

        while (c < response.length()) {

            t.add(getObjectFromJsonObject(response.getJSONObject(c), clazz));

            c++;
        }
        return t;
    }

    /**
     * Gets the object from json object.
     *
     * @param <T> the generic type
     * @param j the j
     * @param clazz the clazz
     * @return the object from json object
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private <T> T getObjectFromJsonObject(JSONObject j, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        T t = null;
        t = mapper.readValue(j.toString(), clazz);

        return t;
    }

    /* (non-Javadoc)
     * @see aasaanjobs.com.aasaan_http_core.repositories.BaseRepository#post(Class, JSONObject, CustomRepoListener)
     */
    @Override
    public <T> void post(final Class<T> clazz, JSONObject requestObject, final CustomRepoListener<T> listener) {

        setUrl(model.getPostURL());

        initiateProgressDialogue();
        CustomJsonObjectRequest r = new CustomJsonObjectRequest(Request.Method.POST, url, requestObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                dismissProgressDialogue();
                ObjectMapper mapper = new ObjectMapper();
                T t = null;
                try {
                    t = mapper.readValue(response.toString(), clazz);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                listener.onSuccess(t);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                dismissProgressDialogue();


                listener.onError(error);

            }
        });
        r.setRetryPolicy(defaultRetryPolicy);

        VolleySingleton.getInstance(context).addToRequestQueue(r);
    }

    @Override
    public <T> void post(final Class<T> clazz, JSONObject requestObject, final CustomRepoListener<T> listener, final boolean showLoadingDialogue) {
        setUrl(model.getPostURL());
        if(showLoadingDialogue)
            initiateProgressDialogue();
        CustomJsonObjectRequest r = new CustomJsonObjectRequest(Request.Method.POST, url, requestObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if(showLoadingDialogue)
                    dismissProgressDialogue();
                ObjectMapper mapper = new ObjectMapper();
                T t = null;
                try {
                    t = mapper.readValue(response.toString(), clazz);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                listener.onSuccess(t);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (showLoadingDialogue)
                    dismissProgressDialogue();


                listener.onError(error);

            }
        });
        r.setRetryPolicy(defaultRetryPolicy);

        VolleySingleton.getInstance(context).addToRequestQueue(r);
    }

    /**
     * Dismiss progress dialogue.
     */
    private void dismissProgressDialogue() {
        try {
            progressDialog.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initiate progress dialogue.
     */
    private void initiateProgressDialogue() {

        try {
            progressDialog = ProgressDialog.show(context, "Please wait", "");
        } catch (Exception e) {

        }
    }

    /* (non-Javadoc)
     * @see aasaanjobs.com.aasaan_http_core.repositories.BaseRepository#post(Class, T, CustomRepoListener)
     */
    @Override
    public <T> void post(Class<T> c, T request, CustomRepoListener<T> listener) {


        try {
            post(c, getJSONFromObject(request), listener);
        } catch (IOException e) {
            e.printStackTrace();
            listener.onError(e);
        } catch (JSONException e) {
            e.printStackTrace();
            listener.onError(e);
        }

    }

    @Override
    public <T> void post(Class<T> c, T request, CustomRepoListener<T> listener, boolean showLoadingDialogue){
        try {
            post(c, getJSONFromObject(request), listener, showLoadingDialogue);
        } catch (IOException e) {
            e.printStackTrace();
            listener.onError(e);
        } catch (JSONException e) {
            e.printStackTrace();
            listener.onError(e);
        }
    }

    /**
     * Gets the JSON from object.
     *
     * @param <T> the generic type
     * @param request the request
     * @return the JSON from object
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws JSONException the JSON exception
     */
    private <T> JSONObject getJSONFromObject(T request) throws IOException, JSONException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
        String temp = null;
        temp = mapper.writeValueAsString(request);
        JSONObject j = new JSONObject(temp);

        return j;
    }

    /* (non-Javadoc)
     * @see aasaanjobs.com.aasaan_http_core.repositories.BaseRepository#put(Class, JSONObject, CustomRepoListener)
     */
    @Override
    public <T> void put(final Class<T> clazz, JSONObject requestObject, final CustomRepoListener<T> listener) {

        setUrl(model.getPutURL());

        initiateProgressDialogue();

        CustomJsonObjectRequest r = new CustomJsonObjectRequest(Request.Method.PUT, url, requestObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                dismissProgressDialogue();
                ObjectMapper mapper = new ObjectMapper();
                T t = null;
                try {
                    t = mapper.readValue(response.toString(), clazz);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                listener.onSuccess(t);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dismissProgressDialogue();

                listener.onError(error);
            }
        });
        r.setRetryPolicy(defaultRetryPolicy);
        VolleySingleton.getInstance(context).addToRequestQueue(r);
    }

    @Override
    public <T> void put(final Class<T> clazz, JSONObject requestObject, final CustomRepoListener<T> listener, final boolean showLoadingDialogue) {
        setUrl(model.getPutURL());

        if (showLoadingDialogue)
            initiateProgressDialogue();

        CustomJsonObjectRequest r = new CustomJsonObjectRequest(Request.Method.PUT, url, requestObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (showLoadingDialogue)
                    dismissProgressDialogue();
                ObjectMapper mapper = new ObjectMapper();
                T t = null;
                try {
                    t = mapper.readValue(response.toString(), clazz);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                listener.onSuccess(t);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (showLoadingDialogue)
                    dismissProgressDialogue();

                listener.onError(error);
            }
        });
        r.setRetryPolicy(defaultRetryPolicy);
        VolleySingleton.getInstance(context).addToRequestQueue(r);
    }



    /* (non-Javadoc)
     * @see aasaanjobs.com.aasaan_http_core.repositories.BaseRepository#put(java.lang.Class, java.lang.Object, aasaanjobs.com.aasaan_http_core.utils.Listeners.CustomRepoListener)
     */
    @Override
    public <T> void put(Class<T> c, T request, CustomRepoListener<T> listener) {

        setUrl(model.getPutURL());

        try {
            put(c, getJSONFromObject(request), listener);
        } catch (IOException e) {
            e.printStackTrace();
            listener.onError(e);
        } catch (JSONException e) {
            e.printStackTrace();
            listener.onError(e);
        }

    }

    @Override
    public <T> void put(Class<T> c, T request, CustomRepoListener<T> listener, boolean showLoadingDialogue) {
        setUrl(model.getPutURL());

        try {
            put(c, getJSONFromObject(request), listener, showLoadingDialogue);
        } catch (IOException e) {
            e.printStackTrace();
            listener.onError(e);
        } catch (JSONException e) {
            e.printStackTrace();
            listener.onError(e);
        }
    }

//    @Override
//    public <T> void patch(Class<T> clazz, String url, JSONObject requestObject, CustomRepoListener<T> listener) {
//
//    }

    /* (non-Javadoc)
 * @see aasaanjobs.com.aasaan_http_core.repositories.BaseRepository#patch(Class, JSONObject, CustomRepoListener)
 */
    @Override
    public <T> void patch(final Class<T> clazz, JSONObject requestObject, final CustomRepoListener<T> listener) {
        setUrl(model.getPatchURL());

        initiateProgressDialogue();
        CustomJsonObjectRequest r = new CustomJsonObjectRequest(Request.Method.PATCH, url, requestObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                dismissProgressDialogue();
                ObjectMapper mapper = new ObjectMapper();
                T t = null;
                try {
                    t = mapper.readValue(response.toString(), clazz);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                listener.onSuccess(t);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                dismissProgressDialogue();


                listener.onError(error);

            }
        });
        r.setRetryPolicy(defaultRetryPolicy);

        VolleySingleton.getInstance(context).addToRequestQueue(r);
//        Volley.newRequestQueue(context, new OkHttpStack()).add(r);
    }

    @Override
    public <T> void patch(final Class<T> clazz, JSONObject requestObject, final CustomRepoListener<T> listener, final boolean showLoadingDialogue) {
        setUrl(model.getPatchURL());

        if (showLoadingDialogue)
            initiateProgressDialogue();
        CustomJsonObjectRequest r = new CustomJsonObjectRequest(Request.Method.PATCH, url, requestObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (showLoadingDialogue)
                    dismissProgressDialogue();
                ObjectMapper mapper = new ObjectMapper();
                T t = null;
                try {
                    t = mapper.readValue(response.toString(), clazz);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                listener.onSuccess(t);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (showLoadingDialogue)
                    dismissProgressDialogue();


                listener.onError(error);

            }
        });
        r.setRetryPolicy(defaultRetryPolicy);

        VolleySingleton.getInstance(context).addToRequestQueue(r);
    }



    /* (non-Javadoc)
     * @see aasaanjobs.com.aasaan_http_core.repositories.BaseRepository#patch(Class, T, CustomRepoListener)
     */
    @Override
    public <T> void patch(Class<T> c, T request, CustomRepoListener<T> listener) {
        try {
            patch(c, getJSONFromObject(request), listener);
        } catch (IOException e) {
            e.printStackTrace();
            listener.onError(e);
        } catch (JSONException e) {
            e.printStackTrace();
            listener.onError(e);
        }
    }

    @Override
    public <T> void patch(Class<T> c, T request, CustomRepoListener<T> listener, boolean showLoadingDialogue) throws IOException, JSONException {
        try {
            patch(c, getJSONFromObject(request), listener, showLoadingDialogue);
        } catch (IOException e) {
            e.printStackTrace();
            listener.onError(e);
        } catch (JSONException e) {
            e.printStackTrace();
            listener.onError(e);
        }
    }

    /* (non-Javadoc)
     * @see aasaanjobs.com.aasaan_http_core.repositories.BaseRepository#delete(Class, JSONObject, CustomRepoListener)
     */
    @Override
    public <T> void delete(Class<T> clazz, JSONObject requestObject, CustomRepoListener<T> listener) {
        setUrl(model.getDeleteURL());
        setCustomRepoListener(listener);
        callMethodByRequestType(Request.Method.DELETE, requestObject, clazz);


    }

    @Override
    public <T> void delete(Class<T> clazz, JSONObject requestObject, CustomRepoListener<T> listener, boolean showLoadingDialogue) {
        setUrl(model.getDeleteURL());
        setCustomRepoListener(listener);
        callMethodByRequestType(Request.Method.DELETE, requestObject, clazz, showLoadingDialogue);
    }



    /* (non-Javadoc)
     * @see aasaanjobs.com.aasaan_http_core.repositories.BaseRepository#delete(java.lang.Class, java.lang.Object, aasaanjobs.com.aasaan_http_core.utils.Listeners.CustomRepoListener)
     */
    @Override
    public <T> void delete(Class<T> c, T request, CustomRepoListener<T> listener) {
        setUrl(model.getDeleteURL());
        try {
            delete(c, getJSONFromObject(request), listener);
        } catch (IOException e) {
            e.printStackTrace();
            listener.onError(e);
        } catch (JSONException e) {
            e.printStackTrace();
            listener.onError(e);
        }
    }

    @Override
    public <T> void delete(Class<T> c, T request, CustomRepoListener<T> listener, boolean showLoadingDialogue) {
        setUrl(model.getDeleteURL());
        try {
            delete(c, getJSONFromObject(request), listener, showLoadingDialogue);
        } catch (IOException e) {
            e.printStackTrace();
            listener.onError(e);
        } catch (JSONException e) {
            e.printStackTrace();
            listener.onError(e);
        }
    }

    @Override
    public <T> void sendRequest(Class<T> clazz, String url, int requestType, CustomRepoListener<T> listener, boolean showLoadingDialogue) {

    }

    @Override
    public <T> void sendRequest(Class<T> clazz, int requestType, CustomRepoListener<T> listener, boolean showLoadingDialogue) {

    }



    /* (non-Javadoc)
     * @see aasaanjobs.com.aasaan_http_core.repositories.BaseRepository#get(Class, JSONObject, CustomRepoListener)
     */
    @Override
    public <T> void get(Class<T> clazz, JSONObject requestObject, CustomRepoListener<T> listener) {
        setUrl(model.getGetURL());

        setCustomRepoListener(listener);
        callMethodByRequestType(Request.Method.GET, requestObject, clazz);


    }

    @Override
    public <T> void get(Class<T> clazz, JSONObject requestObject, CustomRepoListener<T> listener, boolean showLoadingDialogue) {
        setUrl(model.getGetURL());

        setCustomRepoListener(listener);
        callMethodByRequestType(Request.Method.GET, requestObject, clazz, true);
    }



    /* (non-Javadoc)
     * @see aasaanjobs.com.aasaan_http_core.repositories.BaseRepository#callMethodByRequestType(int, JSONObject, Class)
     */
    @Override
    public <T> void callMethodByRequestType(final int requestType, JSONObject requestObject, final Class<T> clazz) {

        initiateProgressDialogue();
        try {
            CustomJsonObjectRequest r = new CustomJsonObjectRequest(requestType, url, requestObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    dismissProgressDialogue();
                    sendResponse(response, clazz);


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    dismissProgressDialogue();
                    customRepoListener.onError(error);
                }
            });

            r.setRetryPolicy(defaultRetryPolicy);
            VolleySingleton.getInstance(context).addToRequestQueue(r);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T> void callMethodByRequestType(int requestType, JSONObject requestObject, final Class<T> clazz, final boolean showLoadingDialogue) {
        if(showLoadingDialogue)
            initiateProgressDialogue();
        try {
            CustomJsonObjectRequest r = new CustomJsonObjectRequest(requestType, url, requestObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if (showLoadingDialogue)
                        dismissProgressDialogue();
                    sendResponse(response, clazz);


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (showLoadingDialogue)
                        dismissProgressDialogue();
                    customRepoListener.onError(error);
                }
            });

            r.setRetryPolicy(defaultRetryPolicy);
            VolleySingleton.getInstance(context).addToRequestQueue(r);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* (non-Javadoc)
     * @see aasaanjobs.com.aasaan_http_core.repositories.BaseRepository#callMethodByRequestTypeAndSave(int, java.lang.Class, boolean)
     */
    @Override
    public <T> void callMethodByRequestTypeAndSave(final int requestType, final Class<T> clazz, final boolean showLoadingDialogue) {
        if (showLoadingDialogue)
            initiateProgressDialogue();
        try {

            CustomJsonObjectRequest r = new CustomJsonObjectRequest(requestType, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if (showLoadingDialogue)
                        dismissProgressDialogue();
                    Log.i("", "");
                    //context.getSharedPreferences("
                    // Pref",0).edit().putString("searchResponse",response.toString()).commit();
                    sendResponse(response, clazz);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (showLoadingDialogue)
                        dismissProgressDialogue();
                    customRepoListener.onError(error);
                }
            });

            r.setRetryPolicy(defaultRetryPolicy);

            VolleySingleton.getInstance(context).addToRequestQueue(r);
        } catch (Exception E) {
            E.printStackTrace();


        }
    }

    /* (non-Javadoc)
     * @see aasaanjobs.com.aasaan_http_core.repositories.BaseRepository#get(java.lang.Class, aasaanjobs.com.aasaan_http_core.utils.Listeners.CustomRepoListener, java.lang.String)
     */
    @Override
    public <T> void get(Class<T> c, CustomRepoListener<T> listener, String url, boolean showLoadingDialogue) {
        setUrl(url);

        setCustomRepoListener(listener);
        callMethodByRequestType(Request.Method.GET, c, showLoadingDialogue);

    }

    @Override
    public <T> void get(Class<T> c, CustomRepoListener<T> listener, String url) {
        setUrl(url);

        setCustomRepoListener(listener);
        callMethodByRequestType(Request.Method.GET, c, true);

    }

    /* (non-Javadoc)
     * @see aasaanjobs.com.aasaan_http_core.repositories.BaseRepository#post(java.lang.Class, aasaanjobs.com.aasaan_http_core.models.BaseDO, aasaanjobs.com.aasaan_http_core.utils.Listeners.CustomRepoListener)
     */
    @Override
    public <T extends BaseDO, P extends BaseResponseDO> void post(Class<P> c, T request, CustomRepoListener<P> listener) {
        setUrl(model.getPostURL());
        setCustomRepoListener(listener);
        JSONObject requestObject = null;
        try {
            requestObject = getJSONFromObject(request);
            callMethodByRequestType(Request.Method.POST, requestObject, c);
        } catch (IOException e) {
            e.printStackTrace();
            listener.onError(e);
        } catch (JSONException e) {
            e.printStackTrace();
            listener.onError(e);
        }

    }

    @Override
    public <T extends BaseDO, P extends BaseResponseDO> void post(Class<P> c, T request, CustomRepoListener<P> listener, boolean showLoadingDialogue) {
        setUrl(model.getPostURL());
        setCustomRepoListener(listener);
        JSONObject requestObject = null;
        try {
            requestObject = getJSONFromObject(request);
            callMethodByRequestType(Request.Method.POST, requestObject, c, showLoadingDialogue);
        } catch (IOException e) {
            e.printStackTrace();
            listener.onError(e);
        } catch (JSONException e) {
            e.printStackTrace();
            listener.onError(e);
        }
    }

    /* (non-Javadoc)
     * @see aasaanjobs.com.aasaan_http_core.repositories.BaseRepository#getString(aasaanjobs.com.aasaan_http_core.utils.Listeners.CustomRepoListener, java.lang.String)
     */
    @Override
    public void getString(final CustomRepoListener<String> customRepoListener, String url) {
        CustomJsonObjectRequest r = new CustomJsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                ObjectMapper mapper = new ObjectMapper();

                customRepoListener.onSuccess(response.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                customRepoListener.onError(error);

            }
        });
        r.setRetryPolicy(defaultRetryPolicy);
        VolleySingleton.getInstance(context).addToRequestQueue(r);
    }

    @Override
    public void getString(final CustomRepoListener<String> customRepoListener, String url, boolean showLoadingDialogue) {
        CustomJsonObjectRequest r = new CustomJsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                ObjectMapper mapper = new ObjectMapper();

                customRepoListener.onSuccess(response.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                customRepoListener.onError(error);

            }
        });
        r.setRetryPolicy(defaultRetryPolicy);
        VolleySingleton.getInstance(context).addToRequestQueue(r);
    }

    /* (non-Javadoc)
     * @see aasaanjobs.com.aasaan_http_core.repositories.BaseRepository#uploadFile(java.lang.Class, java.io.File, java.lang.String, aasaanjobs.com.aasaan_http_core.utils.Listeners.CustomRepoListener)
     */
    @Override
    public <T> void uploadFile(final Class<T> c, File file, String url, final CustomRepoListener<T> listener) {
//        initiateProgressDialogue();
        this.customRepoListener = listener;
        MultiPartRequest multiPartRequest = new MultiPartRequest(url, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                dismissProgressDialogue();
                listener.onError(error);
            }
        }, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
//                dismissProgressDialogue();
                sendResponse(response, c);
            }
        }, file, null);
        multiPartRequest.setRetryPolicy(defaultRetryPolicy);
        VolleySingleton.getInstance(context).addToRequestQueue(multiPartRequest);
    }


    @Override
    public <T> void uploadFile(final Class<T> c, File file, HashMap<String,String> params, String url, final CustomRepoListener<T> listener) {
        // initiateProgressDialogue();
        this.customRepoListener = listener;
        MultiPartRequest multiPartRequest = new MultiPartRequest(url, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                dismissProgressDialogue();
                listener.onError(error);
            }
        }, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
//                dismissProgressDialogue();
                sendResponse(response, c);
            }
        }, file,params);
        multiPartRequest.setRetryPolicy(defaultRetryPolicy);

        VolleySingleton.getInstance(context).addToRequestQueue(multiPartRequest);
    }

    @Override
    public <T> void uploadFile(final Class<T> c, File file, HashMap<String,String> params, String url,final CustomRepoListener<T> listener, long fileLength,MultiPartRequest.MultipartProgressListener progressListener) {
        // initiateProgressDialogue();
        this.customRepoListener = listener;
        MultiPartRequest multiPartRequest = new MultiPartRequest(url, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                dismissProgressDialogue();
                listener.onError(error);
            }
        }, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
//                dismissProgressDialogue();
                sendResponse(response, c);
            }
        }, file,params, fileLength, progressListener);
        multiPartRequest.setRetryPolicy(defaultRetryPolicy);

        VolleySingleton.getInstance(context).addToRequestQueue(multiPartRequest);
    }

    /* (non-Javadoc)
     * @see aasaanjobs.com.aasaan_http_core.repositories.BaseRepository#downloadFile(java.lang.String, int, aasaanjobs.com.aasaan_http_core.utils.Listeners.CustomRepoListener)
     */
    @Override
    public void downloadFile(String url, int type, final CustomRepoListener<File> listener) {
        ImageRequest imageRequest = null;
        if (type == IMAGE_FILE) {
            imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap response) {
                    convertBitmapToFile(response, listener);
                }
            }, 0, 0, null, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    listener.onError(error);
                }
            });
        }
        VolleySingleton.getInstance(context).addToRequestQueue(imageRequest);

    }

    /**
     * Convert bitmap to file.
     *
     * @param response the response
     * @param listener the listener
     */
    private void convertBitmapToFile(Bitmap response, CustomRepoListener<File> listener) {
        //create a file to write bitmap data

        File f = new File(getFilename());
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

//Convert bitmap to byte array
        Bitmap bitmap = response;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
        byte[] bitmapdata = bos.toByteArray();

//write the bytes in file
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(f);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
            listener.onSuccess(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /* (non-Javadoc)
     * @see aasaanjobs.com.aasaan_http_core.repositories.BaseRepository#callMethodByRequestType(int, java.lang.Class)
     */
    @Override
    public <T> void callMethodByRequestType(final int requestType, final Class<T> clazz, final boolean showLoadingDialogue) {
        if(showLoadingDialogue)
            initiateProgressDialogue();

        try {

            CustomJsonObjectRequest r = new CustomJsonObjectRequest(requestType, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if(showLoadingDialogue)
                        dismissProgressDialogue();
                    Log.i("", "");
                    //  context.getSharedPreferences("searchPref",0).edit().putString("searchResponse",response.toString()).commit();
                    sendResponse(response, clazz);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (showLoadingDialogue)
                        dismissProgressDialogue();

                    customRepoListener.onError(error);
                }
            });
            r.setRetryPolicy(defaultRetryPolicy);
            VolleySingleton.getInstance(context).addToRequestQueue(r);
        } catch (Exception E) {
            E.printStackTrace();
            dismissProgressDialogue();


        }

    }


    /**
     * Send response.
     *
     * @param <T> the generic type
     * @param response the response
     * @param clazz the clazz
     */
    private <T> void sendResponse(JSONObject response, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        T t = null;
        try {
            t = mapper.readValue(response.toString(), clazz);
            customRepoListener.onSuccess(t);
        } catch (Exception e) {
            e.printStackTrace();
            customRepoListener.onError(e);
        }


    }

    /**
     * Send object response.
     *
     * @param <T> the generic type
     * @param response the response
     * @param clazz the clazz
     */
    private <T> void sendObjectResponse(JsonObject response, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        T t = null;
        try {
            t = mapper.readValue(response.toString(), clazz);
            customRepoListener.onSuccess(t);
        } catch (Exception e) {
            e.printStackTrace();
            customRepoListener.onError(e);
        }


    }

    /* (non-Javadoc)
     * @see aasaanjobs.com.aasaan_http_core.repositories.BaseRepository#getAndSave(java.lang.Class, aasaanjobs.com.aasaan_http_core.utils.Listeners.CustomRepoListener, boolean)
     */
    @Override
    public <T> void getAndSave(final Class<T> clazz, final CustomRepoListener<T> listener, boolean showLoadingDialogue) {
        setUrl(model.getGetURL());

        setCustomRepoListener(listener);
        callMethodByRequestTypeAndSave(Request.Method.GET, clazz, showLoadingDialogue);
    }

    /* (non-Javadoc)
     * @see aasaanjobs.com.aasaan_http_core.repositories.BaseRepository#getAndSave(java.lang.Class, aasaanjobs.com.aasaan_http_core.utils.Listeners.CustomRepoListener)
     */
    @Override
    public <T> void getAndSave(final Class<T> clazz, final CustomRepoListener<T> listener) {
        setUrl(model.getGetURL());

        setCustomRepoListener(listener);
        callMethodByRequestTypeAndSave(Request.Method.GET, clazz, true);
    }
}

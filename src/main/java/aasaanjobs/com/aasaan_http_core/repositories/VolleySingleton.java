package aasaanjobs.com.aasaan_http_core.repositories;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

// TODO: Auto-generated Javadoc

/**
 * Created by dineshsingh on 21/02/15.
 */
public class VolleySingleton {

    /**
     * The m instance.
     */
    private static volatile VolleySingleton mInstance;

    /**
     * The m ctx.
     */
    private static Context mCtx;

    /**
     * The m request queue.
     */
    private RequestQueue mRequestQueue;

    /**
     * The m image loader.
     */
    private ImageLoader mImageLoader;


    /**
     * Instantiates a new volley singleton.
     *
     * @param context the context
     */
    private VolleySingleton(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();

        mImageLoader = new ImageLoader(mRequestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
    }

    /**
     * Gets the single instance of VolleySingleton.
     *
     * @param context the context
     * @return single instance of VolleySingleton
     */
    public static synchronized VolleySingleton getInstance(Context context) {
        if (mInstance == null) {
            synchronized (VolleySingleton.class) {
                if (mInstance == null) {
                    mInstance = new VolleySingleton(context);
                }
            }
        }
        return mInstance;
    }

    /**
     * Gets the request queue.
     *
     * @return the request queue
     */
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    /**
     * Adds the to request queue.
     *
     * @param <T> the generic type
     * @param req the req
     */
    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    /**
     * Gets the image loader.
     *
     * @return the image loader
     */
    public ImageLoader getImageLoader() {
        return mImageLoader;
    }
}


package aasaanjobs.com.aasaan_http_core.business;

import android.content.Context;

/**
 * Created by dineshsingh on 19/02/15.
 */
public abstract class AbstractLogic<T> implements BaseLogic {

    protected Context context;
    protected T model;


    public AbstractLogic(Context context, T model) {
        this.context = context;
        this.model = model;

    }
}

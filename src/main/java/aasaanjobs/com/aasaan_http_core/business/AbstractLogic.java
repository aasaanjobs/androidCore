package aasaanjobs.com.aasaan_http_core.business;

import android.content.Context;

// TODO: Auto-generated Javadoc
/**
 * Created by dineshsingh on 19/02/15.
 *
 * @param <T> the generic type
 */
public abstract class AbstractLogic<T> implements BaseLogic {

    /** The context. */
    protected Context context;
    
    /** The model. */
    protected T model;


    /**
     * Instantiates a new abstract logic.
     *
     * @param context the context
     * @param model the model
     */
    public AbstractLogic(Context context, T model) {
        this.context = context;
        this.model = model;

    }
}

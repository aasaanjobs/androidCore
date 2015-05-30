package aasaanjobs.com.aasaan_http_core.repositories;


import aasaanjobs.com.aasaan_http_core.exceptions.BaseException;

/**
 * Created by dineshsingh on 01/04/15.
 */
public interface CustomErrorListener {
    public <T extends BaseException> void onError(Exception error);
}

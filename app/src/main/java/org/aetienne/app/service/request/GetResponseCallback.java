package org.aetienne.app.service.request;

import com.android.volley.NoConnectionError;
import com.android.volley.VolleyError;

/**
 * Class definition for a callback to be invoked when the response data for the
 * GET call is available.
 */
public abstract class GetResponseCallback<T> {

    public enum REQUEST_ERROR{
        UNKNOWN,
        NO_CONNECTION,
        UNAUTHORIZED,
        NOT_FOUND,
        REQUEST_BAD_JSON_FORMAT
    }
    /**
     * Called when the response data for the REST call is ready. <br/>
     * This method is guaranteed to execute on the UI thread.
     *
     * @param obj The {@code T} that was received from the server.
     */
    public abstract void onDataReceived(T obj);

    public abstract void onFailure(REQUEST_ERROR error);

    public static REQUEST_ERROR catchVolleyError(VolleyError error){
        if(error instanceof NoConnectionError) return REQUEST_ERROR.NO_CONNECTION;
        else return REQUEST_ERROR.UNKNOWN;
    }


}

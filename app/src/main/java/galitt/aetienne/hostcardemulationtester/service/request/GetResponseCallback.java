package galitt.aetienne.hostcardemulationtester.service.request;

import com.android.volley.VolleyError;

/**
 * Class definition for a callback to be invoked when the response data for the
 * GET call is available.
 */
public abstract class GetResponseCallback<T> {

    /**
     * Called when the response data for the REST call is ready. <br/>
     * This method is guaranteed to execute on the UI thread.
     *
     * @param obj The {@code T} that was received from the server.
     */
    public abstract void onDataReceived(T obj);

    public abstract void onFailure(VolleyError error);
}

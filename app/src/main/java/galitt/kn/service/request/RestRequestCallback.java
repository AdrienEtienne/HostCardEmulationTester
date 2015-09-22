package galitt.kn.service.request;

/**
 * Created by Adrien on 22/09/2015.
 */
public abstract class RestRequestCallback {
    /**
     * Called when the HTTP request completes.
     *
     * @param result The result of the HTTP request.
     */
    public abstract void onRequestComplete(String result);
}

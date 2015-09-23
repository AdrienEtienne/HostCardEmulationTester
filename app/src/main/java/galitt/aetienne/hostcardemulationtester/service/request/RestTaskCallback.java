package galitt.aetienne.hostcardemulationtester.service.request;

/**
 * Created by Adrien on 22/09/2015.
 */
public abstract class RestTaskCallback {
    /**
     * Called when the HTTP request completes.
     *
     * @param result The result of the HTTP request.
     */
    public abstract void onTaskComplete(String result);
}

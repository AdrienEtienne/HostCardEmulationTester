package galitt.kn.service.request;

import galitt.kn.service.authentication.User;
import galitt.kn.service.hce.Transaction;

/**
 * Class definition for a callback to be invoked when the response data for the
 * GET call is available.
 */
public abstract class GetResponseCallback{

    /**
     * Called when the response data for the REST call is ready. <br/>
     * This method is guaranteed to execute on the UI thread.
     *
     * @param user The {@code User} that was received from the server.
     */
    public abstract void onUserReceived(User user);

    /**
     * Called when the response data for the REST call is ready. <br/>
     * This method is guaranteed to execute on the UI thread.
     *
     * @param transaction The {@code Transaction} that was received from the server.
     */
    public abstract void onTransactionReceived(Transaction transaction);

    /*
     * Additional methods like onPreGet() or onFailure() can be added with default implementations.
     * This is why this has been made and abstract class rather than Interface.
     */
    public abstract void onFailure(String error);
}

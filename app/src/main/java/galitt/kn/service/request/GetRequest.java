package galitt.kn.service.request;

import android.os.AsyncTask;

/**
 * Created by Adrien on 22/09/2015.
 */
public class GetRequest extends AsyncTask<String, String, String> {
    private String mRestUrl;
    private RestRequestCallback mCallback;

    /**
     * Creates a new instance of GetTask with the specified URL and callback.
     *
     * @param restUrl The URL for the REST API.
     * @param callback The callback to be invoked when the HTTP request
     *            completes.
     *
     */
    public GetRequest(String restUrl, RestRequestCallback callback){
        this.mRestUrl = restUrl;
        this.mCallback = callback;
    }

    @Override
    protected String doInBackground(String... params) {
        String response = null;
        //Use HTTP Client APIs to make the call.
        //Return the HTTP Response body here.
        return response;
    }

    @Override
    protected void onPostExecute(String result) {
        mCallback.onTaskComplete(result);
        super.onPostExecute(result);
    }
}

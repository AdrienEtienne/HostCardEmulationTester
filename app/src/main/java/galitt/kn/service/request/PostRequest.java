package galitt.kn.service.request;

import android.os.AsyncTask;

/**
 * Created by Adrien on 22/09/2015.
 */
public class PostRequest extends AsyncTask<String, String, String>{
    private String mRestUrl;
    private RestRequestCallback mCallback;
    private String mRequestBody;

    /**
     * Creates a new instance of PostTask with the specified URL, callback, and
     * request body.
     *
     * @param restUrl The URL for the REST API.
     * @param callback The callback to be invoked when the HTTP request
     *            completes.
     * @param requestBody The body of the POST request.
     *
     */
    public PostRequest(String restUrl, String requestBody, RestRequestCallback callback){
        this.mRestUrl = restUrl;
        this.mRequestBody = requestBody;
        this.mCallback = callback;
    }

    @Override
    protected String doInBackground(String... arg0) {
        //Use HTTP client API's to do the POST
        //Return response.
        return "response";
    }

    @Override
    protected void onPostExecute(String result) {
        mCallback.onRequestComplete(result);
        super.onPostExecute(result);
    }
}

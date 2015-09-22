package galitt.kn.service;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import galitt.kn.service.authentication.User;
import galitt.kn.service.request.GetResponseCallback;

/**
 * Created by Adrien on 22/09/2015.
 */
public class HttpClientHceServiceSingleton extends Application{
    public static final String TAG = HttpClientHceServiceSingleton.class.getSimpleName();
    private final String HTTP_PRE_ADDRESS = "http://";
    private final String API_USER = "/api/users";
    private static String mAddress = "127.0.0.1";
    private static int mPort = 8080;

    private RequestQueue mRequestQueue;
    private static HttpClientHceServiceSingleton mInstance;

    protected enum Apis{
        AUTHENTICATION("/api/users"),
        HCE("/api/hce");

        private final String value;

        Apis(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    };

    public static synchronized HttpClientHceServiceSingleton getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public String GetAddress(){
        return HTTP_PRE_ADDRESS + this.mAddress;
    }

    public int GetPort(){
        return this.mPort;
    }

    public void SetAddressAndPort(String address, int port){
        this.mAddress = address;
        this.mPort = port;
    }

    protected String getUrl(Apis api, String arguments) {
        return HTTP_PRE_ADDRESS + mAddress + ":" + mPort + api.getValue() + "/" + arguments;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    /**
     * Request a User Profile from the REST server.
     * @param userName The user name for which the profile is to be requested.
     * @param cbSuccess Callback to execute when the profile is available.
     */
    public void getUserProfile(String userName, final GetResponseCallback cbSuccess, final GetResponseCallback cbError){
        String url = getUrl(Apis.AUTHENTICATION, userName);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // Display the first 500 characters of the response string.
                        cbSuccess.onUserReceived(new User(response));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                cbError.onFailure(error.toString());
            }
        });

        mRequestQueue.add(stringRequest);
    }

}


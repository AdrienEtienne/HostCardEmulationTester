package org.aetienne.app.service;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONObject;
import com.android.volley.Request.Method;
import com.google.gson.Gson;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.aetienne.app.service.request.GetTask;
import org.aetienne.app.service.request.RestTaskCallback;
import org.aetienne.app.service.authentication.User;
import org.aetienne.app.service.request.GetResponseCallback;

/**
 * Created by Adrien on 22/09/2015.
 */
public class ApiHCEApplication extends Application{
    private static final String TAG = ApiHCEApplication.class.getSimpleName();
    private final String HTTP_PRE_ADDRESS = "http://";
    private String mAddress = "127.0.0.1";
    private int mPort = 8080;

    private RequestQueue mRequestQueue;
    private static ApiHCEApplication mInstance = new ApiHCEApplication();

    protected enum Apis{
        AUTHENTICATION("/api/users/user"),
        HCE("/api/hce");

        private final String value;

        Apis(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mRequestQueue = Volley.newRequestQueue(this);
    }

    public static synchronized ApiHCEApplication getInstance() {
        if(mInstance == null){
            mInstance = new ApiHCEApplication();
        }

        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(this);
        }

        return mRequestQueue;
    }

    public String getAddress(){
        return HTTP_PRE_ADDRESS + mAddress;
    }

    public int getPort(){
        return this.mPort;
    }

    public void setAddressAndPort(String address, int port){
        mAddress = address;
        mPort = port;
    }

    protected String getUrl(Apis api, String arguments) {
        return getAddress() + ":" + mPort + api.getValue() + arguments;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(String tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    /**
     * Request a User Profile from the REST server.
     * @param userName The user name for which the profile is to be requested.
     * @param callback Callback to execute when the user is available.
     */
    public void getUser(String userName, final GetResponseCallback<User> callback){
        String url = getUrl(Apis.AUTHENTICATION, "/" + userName);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    // Display the first 500 characters of the response string.
                    Gson gson = new Gson();
                    User obj = gson.fromJson(response.toString(), User.class);
                    callback.onDataReceived(obj);
                }
            },

            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    callback.onFailure(error);
                }
            }
        );

        getInstance().getRequestQueue().add(request);
    }

    public void getUser2(String userName, final GetResponseCallback callback){
        String url = getUrl(Apis.AUTHENTICATION, "/" + userName);
        new GetTask(url, new RestTaskCallback (){
            @Override
            public void onTaskComplete(String response){
                User u = new User(response);
                callback.onDataReceived(u);
            }
        }).execute();
    }

}


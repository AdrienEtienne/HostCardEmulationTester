package org.aetienne.app.service;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.aetienne.app.service.entity.EntityFactory;
import org.aetienne.app.service.entity.Workspace;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Map;
import java.util.HashMap;

import com.android.volley.toolbox.Volley;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.aetienne.app.service.entity.User;
import org.aetienne.app.service.request.GetResponseCallback;

import java.util.ArrayList;
import java.util.List;

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
        AUTHENTICATION("/api/users"),
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

    public void setAddress(String address){
        mAddress = address;
    }

    public void setPort(int port){
        mPort = port;
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
     * @param user The user name for which the profile is to be requested.
     * @param callback Callback to execute when the user is available.
     */
    public void AuthenticateUser(User user, final GetResponseCallback<User> callback){
        String url = getUrl(Apis.HCE, "/users/" + user.getName());

        final User u = user;
        JSONObject jsonBody;
        try {
            jsonBody = new JSONObject(EntityFactory.fromJson(user));
        } catch (JSONException e) {
            callback.onFailure(GetResponseCallback.REQUEST_ERROR.REQUEST_BAD_JSON_FORMAT);
            return;
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonBody,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        u.setAuthenticated(true);
                        callback.onDataReceived(u);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        callback.onFailure(GetResponseCallback.catchVolleyError(error));
                    }
                })
        {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", u.getName());
                params.put("code", u.getCode());
                return params;
            }
        };

        getInstance().getRequestQueue().add(request);
    }

    /**
     * Request a User Profile from the REST server.
     * @param callback Callback to execute when the user is available.
     */
    public void getWorkspaces(final GetResponseCallback<List<Workspace>> callback){
        String url = getUrl(Apis.HCE, "/workspaces");
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<Workspace> lstWorkspaces = new ArrayList<Workspace>();

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Workspace workspace = EntityFactory.getWorkspace(jsonObject.toString());
                                lstWorkspaces.add(workspace);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        callback.onDataReceived(lstWorkspaces);
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onFailure(GetResponseCallback.catchVolleyError(error));
                    }
                }
        );

        getInstance().getRequestQueue().add(request);
    }

}


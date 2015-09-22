package galitt.kn.service;

import java.net.HttpURLConnection;
import java.net.URL;

import galitt.kn.service.request.GetRequest;
import galitt.kn.service.request.GetResponseCallback;
import galitt.kn.service.request.RestRequestCallback;

/**
 * Created by Adrien on 22/09/2015.
 */
public class HttpClientHceService {
    private final String HTTP_PRE_ADDRESS = "http://";

    private static String _address = "127.0.0.1";
    private static int _port = 8080;

    private HttpClient;

    private static HttpClientHceService ourInstance = new HttpClientHceService();

    public static HttpClientHceService getInstance() {
        return ourInstance;
    }

    private HttpClientHceService() {
        URL url = new Url();
        httpclient = new HttpURLConnection();
    }

    public String GetAddress(){
        return HTTP_PRE_ADDRESS + this._address;
    }

    public int GetPort(){
        return this._port;
    }

    public void SetAddressAndPort(String address, int port){
        this._address = address;
        this._port = port;
    }

    /**
     * Request a User Profile from the REST server.
     * @param userName The user name for which the profile is to be requested.
     * @param callback Callback to execute when the profile is available.
     */
    public void getUserProfile(String userName, final GetResponseCallback callback){
        String restUrl = Utils.constructRestUrlForProfile(userName);
        new GetRequest(restUrl, new RestRequestCallback(){
            @Override
            public void onTaskComplete(String response){
                Profile profile = Utils.parseResponseAsProfile(response);
                callback.onDataReceive(profile);
            }
        }).execute();
    }


}


package galitt.kn.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import galitt.kn.service.hce.Transaction;
import galitt.kn.service.authentication.User;

/**
 * Created by Adrien on 22/09/2015.
 */
public class BeanFactory {
    public static Transaction GetTransaction(String json){
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        Transaction t = gson.fromJson(json, Transaction.class);

        return t;
    }

    public static User GetUser(String json){
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        User t = gson.fromJson(json, User.class);

        return t;
    }

    public static String GetJsonString(Object obj){
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        return json;
    }
}

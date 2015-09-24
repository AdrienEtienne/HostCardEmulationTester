package org.aetienne.app.service.entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by Adrien on 22/09/2015.
 */
public class EntityFactory {

    public static Transaction getTransaction(String json){
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        Transaction t = gson.fromJson(json, Transaction.class);
        return t;
    }

    public static User getUser(String json){
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        User t = gson.fromJson(json, User.class);

        return t;
    }

    public static Workspace getWorkspace(String json){
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        Workspace t = gson.fromJson(json, Workspace.class);

        return t;
    }

    public static String fromJson(IEntity json){
        Gson gson = new Gson();
        return gson.toJson(json);
    }
}

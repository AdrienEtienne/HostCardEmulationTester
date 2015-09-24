package org.aetienne.app.service.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Adrien on 22/09/2015.
 */
public class User implements IEntity {

    @SerializedName("name")
    private String mName;
    @SerializedName("code")
    private String mCode;

    private boolean mAuthenticated;

    public User(String name, String code){
        mName = name;
        mCode = code;
        mAuthenticated = false;
    }

    public String getName(){
        return mName;
    }

    public String getCode(){
        return mCode;
    }

    public boolean isAuthenticated(){
        return mAuthenticated;
    }

    public void setAuthenticated(boolean authenticated){
        mAuthenticated = authenticated;
    }
}

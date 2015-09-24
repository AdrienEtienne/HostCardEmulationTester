package org.aetienne.app.service.authentication;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Adrien on 22/09/2015.
 */
public class User {

    @SerializedName("name")
    private String mName;
    private int mCode;

    public User(String name, int code){
        mName = name;
        mCode = code;
    }

    public String getName(){
        return mName;
    }
}

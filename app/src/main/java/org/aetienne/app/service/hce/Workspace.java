package org.aetienne.app.service.hce;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Adrien on 24/09/2015.
 */
public class Workspace {
    @SerializedName("name")
    private String mName;

    @SerializedName("active")
    private boolean mActive;

    public Workspace(String name, boolean active){
        mName = name;
        mActive = active;
    }
    public String getName(){
        return mName;
    }
    public boolean isActive(){
        return mActive;
    }
}

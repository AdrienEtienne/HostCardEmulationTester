package org.aetienne.app.service.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Adrien on 24/09/2015.
 */
public class Workspace implements IEntity {
    @SerializedName("_id")
    private String mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("active")
    private boolean mActive;

    @SerializedName("port")
    private int mPort;

    public Workspace(String name, boolean active){
        mName = name;
        mActive = active;
        mPort = 9000;
        mId = "id";
    }
    public String getName(){
        return mName;
    }
    public String getId(){
        return mId;
    }
    public int getPort(){
        return mPort;
    }
    public boolean isActive(){
        return mActive;
    }
}

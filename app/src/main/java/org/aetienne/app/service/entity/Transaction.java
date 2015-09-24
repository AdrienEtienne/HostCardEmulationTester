package org.aetienne.app.service.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Adrien on 22/09/2015.
 */
public class Transaction implements IEntity{

    @SerializedName("apdu")
    private String mApdu;

    public Transaction(String apdu){
        mApdu = apdu;
    }
    public String getApdu(){
        return mApdu;
    }
}

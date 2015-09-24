package org.aetienne.app.service.hce;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Adrien on 22/09/2015.
 */
public class Transaction {

    @SerializedName("apdu")
    private String mApdu;

    public Transaction(String apdu){
        mApdu = apdu;
    }
    public String getApdu(){
        return mApdu;
    }
}

package galitt.aetienne.hostcardemulationtester.service.authentication;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Adrien on 22/09/2015.
 */
public class User {

    @SerializedName("name")
    private String mName;

    public User(String name){
        mName = name;
    }

    public String getName(){
        return mName;
    }
}

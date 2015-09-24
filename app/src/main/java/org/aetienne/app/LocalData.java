package org.aetienne.app;

import android.content.SharedPreferences;
import android.content.Context;
import android.preference.PreferenceManager;

import org.aetienne.app.service.entity.User;

/**
 * Created by Adrien on 23/09/2015.
 */
public class LocalData {
    //Constants
    private static final String FILE_NAME = "hcePrefs";

    private static final String USER_NAME = "user_name";
    private static final String USER_CODE = "user_code";

    public static User getUser(Context context){
        SharedPreferences settings = context.getSharedPreferences(FILE_NAME, 0);
        String username = settings.getString(USER_NAME, null);
        String code = settings.getString(USER_CODE, null);

        User u = null;

        if(username != null)
        {
            u = new User(username, code);
        }

        return u;
    }

    public static void setUser(Context context, User user){
        SharedPreferences settings = context.getSharedPreferences(FILE_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(USER_NAME, user.getName());

        // Apply the edits!
        editor.apply();
    }

    public static String getAddress(Context context){
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(context);
        return SP.getString("service_address", "127.0.0.1");
    }

    public static int getPort(Context context){
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(context);
        String port = SP.getString("service_port", "8080");
        return Integer.parseInt(port);
    }
}

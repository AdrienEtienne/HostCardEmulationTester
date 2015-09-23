package org.aetienne.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.aetienne.app.service.ApiHCEApplication;
import org.aetienne.app.service.authentication.User;
import org.aetienne.app.service.request.GetResponseCallback;

import org.aetienne.app.R;

public class SplashScreenActivity extends Activity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);

        TextView textView = (TextView) findViewById(R.id.textViewAppName);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/quizma.otf");
        textView.setTypeface(font);

        final Activity that = this;

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                ApiHCEApplication api = ApiHCEApplication.getInstance();
                api.setAddressAndPort("192.168.1.29", 9000);

                api.getUser("Admin", new GetResponseCallback<User>() {

                    @Override
                    public void onDataReceived(User obj) {
                        Toast.makeText(getApplicationContext(), "User " + obj.getName() + " found", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Fail to connect user Admin", Toast.LENGTH_LONG).show();
                        Intent mainIntent = new Intent(that, SettingsActivity.class);
                        that.startActivity(mainIntent);
                        that.finish();
                    }
                });
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

}

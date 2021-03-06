package org.aetienne.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import org.aetienne.app.LocalData;
import org.aetienne.app.service.ApiHCEApplication;
import org.aetienne.app.service.entity.User;

import org.aetienne.app.R;

public class SplashScreenActivity extends Activity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);

        TextView textView = (TextView) findViewById(R.id.textViewAppName);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/quizma.otf");
        textView.setTypeface(font);

        final Activity that = this;

        String address = LocalData.getAddress(this);
        int port = LocalData.getPort(this);
        ApiHCEApplication.getInstance().setAddressAndPort(address, port);

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                User user = LocalData.getUser(that.getApplicationContext());
                Intent intent;

                if (user == null) {
                    intent = new Intent(that, LoginActivity.class);
                } else {
                    intent = new Intent(that, MainActivity.class);
                }

                that.startActivity(intent);
                that.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

}

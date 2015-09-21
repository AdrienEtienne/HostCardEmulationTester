package galitt.aetienne.hostcardemulationtester;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class SplashScreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);

        TextView textView = (TextView) findViewById(R.id.textViewAppName);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/quizma.otf");
        textView.setTypeface(font);

    }

}

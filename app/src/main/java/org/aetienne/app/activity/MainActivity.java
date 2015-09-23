package org.aetienne.app.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.content.Intent;
import android.widget.Button;

import com.android.volley.VolleyError;

import org.aetienne.app.LocalData;
import org.aetienne.app.R;
import org.aetienne.app.service.ApiHCEApplication;
import org.aetienne.app.service.authentication.User;
import org.aetienne.app.service.request.GetResponseCallback;

public class MainActivity extends AppCompatActivity {

    User mUser;
    ApiHCEApplication mApi;

    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mApi = ApiHCEApplication.getInstance();

        getInformation();

        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getInformation();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            this.startActivity(settingsIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void setConfiguration(){
        User user = LocalData.getUser(this);
        mUser = user;

        if(mApi != null){
            String address = LocalData.getAddress(this);
            int port = LocalData.getPort(this);
            mApi.setAddressAndPort(address, port);
        }
    }

    void getInformation(){
        setConfiguration();

        mApi.getUser("Admin", new GetResponseCallback<User>() {

            @Override
            public void onDataReceived(User obj) {
                Toast.makeText(getApplicationContext(), "User " + obj.getName() + " found", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Fail to connect user Admin", Toast.LENGTH_LONG).show();

            }
        });
    }
}

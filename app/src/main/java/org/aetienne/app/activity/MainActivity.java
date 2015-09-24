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
import org.aetienne.app.service.hce.Workspace;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    User mUser;
    ApiHCEApplication mApi;

    String tag = "main_activity";

    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mApi = ApiHCEApplication.getInstance();

        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getInformation();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        getInformation();
    }

    @Override
    protected void onStop() {
        super.onStop();

        mApi.cancelPendingRequests(tag);
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
        getWorkspaces();
    }

    void catchVolleyError(VolleyError error, String msg){
        if (error.getClass().getName().equals("com.android.volley.NoConnectionError")) {
            Toast.makeText(getApplicationContext(), "Fail to connect to server. " + msg, Toast.LENGTH_SHORT).show();
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            this.startActivity(settingsIntent);
        } else if (error.getClass().getName().equals("com.android.volley.ErrorRequest")) {
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }

    void getWorkspaces(){
        mApi.getWorkspaces(new GetResponseCallback<List<Workspace>>() {
            @Override
            public void onDataReceived(List<Workspace> lstWorkspaces) {
                Toast.makeText(getApplicationContext(), "Get workspaces", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(VolleyError error) {
                catchVolleyError(error, "Get workspaces");
            }
        });
    }

    void getUser(){
        mApi.getUser("Admin", new GetResponseCallback<User>() {

            @Override
            public void onDataReceived(User obj) {
                Toast.makeText(getApplicationContext(), "User " + obj.getName() + " found", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(VolleyError error) {
                catchVolleyError(error, "Get user");
            }
        });
    }
}

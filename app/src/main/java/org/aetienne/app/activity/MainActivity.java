package org.aetienne.app.activity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import org.aetienne.app.LocalData;
import org.aetienne.app.R;
import org.aetienne.app.activity.ListItemSimpleFragment.OnFragmentInteractionListener;
import org.aetienne.app.animation.DropDownAnimation;
import org.aetienne.app.modelAdapter.WorkspaceItemSimpleAdapter;
import org.aetienne.app.service.ApiHCEApplication;
import org.aetienne.app.service.entity.User;
import org.aetienne.app.service.entity.Workspace;
import org.aetienne.app.service.request.GetResponseCallback;
import org.aetienne.app.viewmodel.ListItemSimpleAbstract;


public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener{

    User mUser;

    private String TAG = "main_activity";

    private Button mButton;
    private View bandConnection;

    private ListItemSimpleFragment mFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getInformation();
            }
        });

        bandConnection = findViewById(R.id.layoutBandConnection);

        if (savedInstanceState == null) {
            mFrag = ListItemSimpleFragment.newInstance("Workspace");
            getFragmentManager().beginTransaction().add(R.id.container, (Fragment) mFrag).commit();
        }

        List<Workspace> lst = new ArrayList<Workspace>();
        lst.add(new Workspace("first", true));
        setWorkspaces(lst);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mFrag.addItem(new WorkspaceItemSimpleAdapter("delay onCreate","delay onCreate","delay onCreate"));
            }
        }, 1000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //getInformation();
        List<Workspace> lst = new ArrayList<Workspace>();
        lst.add(new Workspace("onstart", true));
        setWorkspaces(lst);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mFrag.addItem(new WorkspaceItemSimpleAdapter("delay onResume", "delay onResume", "delay onResume"));
            }
        }, 1000);
    }

    @Override
    protected void onStop() {
        super.onStop();

        ApiHCEApplication.getInstance().cancelPendingRequests(TAG);
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
    }

    void setWorkspaces(List<Workspace> workspaces){
        for(Workspace workspace: workspaces){
            mFrag.addItem(new WorkspaceItemSimpleAdapter(
                    workspace.getId(),
                    workspace.getName(),
                    ""+workspace.getPort()));
        }
    }

    void getInformation(){
        hideConnectionBand();
        setConfiguration();
        getWorkspaces();
    }

    void catchRequestError(GetResponseCallback.REQUEST_ERROR error, String msg){
        switch(error){
            case NO_CONNECTION:
            {
                showConnectionBand();
                Toast.makeText(getApplicationContext(), "Fail to connect to server. " + msg, Toast.LENGTH_SHORT).show();
                break;
            }
            default:
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }

    void getWorkspaces(){
        ApiHCEApplication.getInstance().getWorkspaces(new GetResponseCallback<List<Workspace>>() {
            @Override
            public void onDataReceived(List<Workspace> lstWorkspaces) {
                Toast.makeText(getApplicationContext(), "Get workspaces : " + lstWorkspaces.size(), Toast.LENGTH_SHORT).show();
                //setWorkspaces(lstWorkspaces);
                //getUser();
            }

            @Override
            public void onFailure(REQUEST_ERROR error) {
                mFrag.addItem(new ListItemSimpleAbstract("id", "No workspaces", "") {});
                catchRequestError(error, "Get workspaces");
            }
        });
    }

    void getUser(){
        if(mUser == null){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return;
        }

        ApiHCEApplication.getInstance().AuthenticateUser(mUser, new GetResponseCallback<User>() {

            @Override
            public void onDataReceived(User obj) {
                Toast.makeText(getApplicationContext(), "User " + obj.getName() + " found", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(REQUEST_ERROR error) {
                catchRequestError(error, "Get user");
            }
        });
    }

    public void hideConnectionBand(){
        DropDownAnimation a = new DropDownAnimation(bandConnection, bandConnection.getHeight(), false);
        a.setDuration(600);
        bandConnection.startAnimation(a);
    }

    public void showConnectionBand(){
        DropDownAnimation a = new DropDownAnimation(bandConnection, 180, true);
        a.setDuration(600);
        bandConnection.setVisibility(bandConnection.INVISIBLE);
        bandConnection.startAnimation(a);
    }

    public void onFragmentInteraction(String position){
        getApplicationContext();
    }
}

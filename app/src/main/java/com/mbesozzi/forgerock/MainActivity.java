package com.mbesozzi.forgerock;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.forgerock.android.auth.FRAuth;
import org.forgerock.android.auth.FRListener;
import org.forgerock.android.auth.FRUser;
import org.forgerock.android.auth.Logger;
import org.forgerock.android.auth.UserInfo;
import org.forgerock.android.auth.exception.AuthenticationRequiredException;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

import static android.view.View.INVISIBLE;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static FragmentActivity activity = null;
    private TokenModel tokenModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Logger.set(Logger.Level.DEBUG);
        FRAuth.start(this);
        MainActivity.activity = this;
        tokenModel = new ViewModelProvider(this).get(TokenModel.class);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logger.debug(TAG,"Login...");
                //tokenModel = new ViewModelProvider(MainActivity.activity).get(TokenModel.class);
                if (FRUser.getCurrentUser() != null) {
                    Logger.debug(TAG,"User is already authenticated...");
                    try {
                        tokenModel.setJsonTokens(FRUser.getCurrentUser().getAccessToken().toJson());
                        callUserinfo();
                        Snackbar.make(view,  "Access token obtained", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();

                    } catch (AuthenticationRequiredException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    FRUser.browser().appAuthConfigurer()
                            .authorizationRequest(r -> {
                                r.setLoginHint("demo");
                            })
                            .customTabsIntent(t -> {
                                t.setShowTitle(false);
                                //t.enableUrlBarHiding();
                            }).done()
                            .login(MainActivity.activity, new FRListener<FRUser>() {
                                @Override
                                public void onSuccess(FRUser result) {
                                    Snackbar.make(view, result.toString(), Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                    callUserinfo();
                                };
                                @Override
                                public void onException(Exception e) {
                                    Log.d(TAG,"Error found login call: " + e.getMessage());

                                }
                            });
                }
            }
        });
    }
    public void callUserinfo(){
        FRUser.getCurrentUser().getUserInfo(new FRListener<UserInfo>() {
            @Override
            public void onSuccess(final UserInfo result) {
                try {

                    Log.d(TAG,"Getting name: " + result.getName());
                    tokenModel.postItem(result.getName());
                    Log.d(TAG,"Userinfo: " +  result.getRaw().toString(2));
                } catch (JSONException e) {
                    onException(e);
                }
            }
            @Override
            public void onException(Exception e) {
                Log.d(TAG,"Error found userinfo call: " + e.getMessage());
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

        Logger.debug(TAG,"Item selected" + item + " id: " + item.getItemId());

        int id = item.getItemId();

        switch (item.getItemId()) {
            case R.id.action_logout:
                if (FRUser.getCurrentUser() != null) {
                    FRUser.getCurrentUser().logout();
                }
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
package dave.example.com.trinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;

import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.facebook.widget.LoginButton.UserInfoChangedCallback;

import dave.example.com.trinder.Utils.API;

import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class LoginActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private UiLifecycleHelper uiHelper;

    @InjectView(R.id.facebookButton) LoginButton facebookLoginButton;
    @InjectView(R.id.progressSpinner) ProgressBar progressSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

        toolbar=(Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        uiHelper = new UiLifecycleHelper(this,statusCallback);
        uiHelper.onCreate((savedInstanceState));
        
        progressSpinner.setVisibility(View.GONE);

        facebookLoginButton.setReadPermissions(Arrays.asList("public_profile"));
        facebookLoginButton.setUserInfoChangedCallback(new UserInfoChangedCallback() {
            @Override
            public void onUserInfoFetched(GraphUser user) {
                if (user != null) {
                    Log.d("Log in", "You are currently logged in as " + user.getName());
                } else {
                    Log.d("Log in", "You are not logged in.");
                }
            }
        });

    }

    private Session.StatusCallback statusCallback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState state, Exception exception) {
            if (state.isOpened()) {
                Log.d("LoginActivity", "Facebook session opened.");
                // Get access token from session and send to API
                // TODO: store trinder access token
                // Start loading icon.
                progressSpinner.setVisibility(View.VISIBLE);
                try {
                    APIClient.getInstance().authenticateWithFacebookAccessToken(session.getAccessToken(), new Callback<Boolean>() {              
                        public void execute(Boolean didAuthenticate) {
                            if (!didAuthenticate) {
                                // go to verify email activity
                                Intent intent = new Intent(this, VerifyEmailActivity.class);
                                startActivity(intent);
                            }    
                            else {
                                // go to update profile activity if this is the first time using the app.
                                // otherwise go to main activity
                            }
                        }
                    });   
                }
                catch(JSONException e) {
                    // tell user to try again. Stop loading icon.
                    progressSpinner.setVisibility(View.GONE);
                }
            } else if (state.isClosed()) {
                Log.d("LoginActivity", "Facebook session closed.");
            }
        }
    };
    @Override
    public void onResume() {
        super.onResume();
        uiHelper.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        uiHelper.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uiHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onSaveInstanceState(Bundle savedState) {
        super.onSaveInstanceState(savedState);
        uiHelper.onSaveInstanceState(savedState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


}


package dave.example.com.trinder;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.InjectView;

import com.facebook.widget.LoginButton;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.widget.LoginButton.UserInfoChangedCallback;
import com.facebook.model.GraphUser;



public class LoginActivity extends ActionBarActivity {

    private Toolbar toolbar;

    @InjectView(R.id.facebookButton) LoginButton facebookLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

        toolbar=(Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer,(DrawerLayout)findViewById(R.id.drawer_layout),toolbar);

        facebookLoginButton.setReadPermissions(Arrays.asList("public_profie"));
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
            } else if (state.isClosed()) {
                Log.d("LoginActivity", "Facebook session closed.");
            }
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


}


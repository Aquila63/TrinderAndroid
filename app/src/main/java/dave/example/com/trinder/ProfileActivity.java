package dave.example.com.trinder;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.applidium.shutterbug.utils.ShutterbugManager;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class ProfileActivity extends ActionBarActivity {

    @InjectView(R.id.image)LinearLayout backgroundImage;
    @InjectView(R.id.name) TextView nameTextView;
    @InjectView(R.id.relationship_status) TextView relationshipTextView;
    @InjectView(R.id.course) TextView courseTextView;
    @InjectView(R.id.bio) TextView bioTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.inject(this);
        Toolbar toolbar= (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Person person = getIntent().getExtras().getParcelable("Person");

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (person.getPhotoURLs().length > 0) {

            ShutterbugManager.getSharedImageManager(getApplicationContext()).download(person.getPhotoURLs()[0], new ShutterbugManager.ShutterbugManagerListener() {
                public void onImageSuccess(ShutterbugManager imageManager, Bitmap bitmap, String url) {
                    BitmapDrawable background = new BitmapDrawable(bitmap);
                    backgroundImage.setBackgroundDrawable(background);
                }

                public void onImageFailure(ShutterbugManager imageManager, String url) {
                    // todo
                }
            });
        }
        nameTextView.setText(person.getName());
        relationshipTextView.setText(person.getStatus());
        courseTextView.setText(person.getCourse());
        bioTextView.setText(person.getDescription());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.edit_profile) {
            return true;
        }
        if(id==android.R.id.home){
            startActivity(new Intent(this, MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

}

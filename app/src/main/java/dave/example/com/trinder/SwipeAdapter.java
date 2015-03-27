package dave.example.com.trinder;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.applidium.shutterbug.utils.ShutterbugManager;

import java.util.List;

public class SwipeAdapter extends ArrayAdapter<Person> {

    Context context;
    int resourceId;
    
    public SwipeAdapter(Context context, int resourceId, List<Person> items) {
        super(context,R.layout.fragment_card_view, items);
        this.context = context;
        this.resourceId = resourceId;
    }
    
    
    /*private view holder class*/
    private class ViewHolder {
        LinearLayout backgroundImageLayout;
        TextView name;
        TextView relationshipStatus;
        TextView course;
        TextView bio;
        TextView courseTitle;
        TextView aboutTitle;
        Button viewMore;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        Person person = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.fragment_card_view, parent, false);

            
            holder = new ViewHolder();
            holder.backgroundImageLayout = (LinearLayout) convertView.findViewById(R.id.image);
            holder.name                  = (TextView) convertView.findViewById(R.id.name);
            holder.relationshipStatus    = (TextView) convertView.findViewById(R.id.relationship_status);
            holder.course                = (TextView) convertView.findViewById(R.id.course);
            holder.bio                   = (TextView) convertView.findViewById(R.id.bio);
            holder.courseTitle           = (TextView) convertView.findViewById(R.id.courseTitle);
            holder.aboutTitle            = (TextView) convertView.findViewById(R.id.aboutTitle);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        
        // set holder.backgroundImageLayout image
        if (person.getPhotoURLs().length > 0) {
            final ViewHolder finalHolder = holder;
            ShutterbugManager.getSharedImageManager(context).download(person.getPhotoURLs()[0], new ShutterbugManager.ShutterbugManagerListener() {
                public void onImageSuccess(ShutterbugManager imageManager, Bitmap bitmap, String url) {
                    BitmapDrawable background = new BitmapDrawable(bitmap);
                    finalHolder.backgroundImageLayout.setBackgroundDrawable(background);
                }
                
                public void onImageFailure(ShutterbugManager imageManager, String url) {
                    // todo
                }
            });
        }
        
        holder.name.setText(person.getName());
        holder.relationshipStatus.setText(person.getStatus());
        holder.course.setText(person.getCourse());
        holder.bio.setText(person.getDescription());
        holder.courseTitle.setText("Course");
        holder.aboutTitle.setText("About");
        
        return convertView;
    }
}

package dave.example.com.trinder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.applidium.shutterbug.utils.ShutterbugManager;

import java.util.List;



public class CardPersonAdapter extends RecyclerView.Adapter<CardPersonAdapter.ViewHolder>{

    private List<Person> people;
    private int cardLayout;
    private Context mContext;

    public CardPersonAdapter(Context context, int cardLayout, List<Person> people) {
        this.people = people;
        this.cardLayout = cardLayout;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(cardLayout, viewGroup, false);
        return new ViewHolder(v);
    }



    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Person person = people.get(i);
        if (person.getPhotoURLs().length > 0) {
            final ViewHolder finalHolder = viewHolder;
            ShutterbugManager.getSharedImageManager(mContext).download(person.getPhotoURLs()[0], new ShutterbugManager.ShutterbugManagerListener() {
                public void onImageSuccess(ShutterbugManager imageManager, Bitmap bitmap, String url) {
                    BitmapDrawable background = new BitmapDrawable(bitmap);
                    finalHolder.backgroundImageLayout.setBackgroundDrawable(background);
                }

                public void onImageFailure(ShutterbugManager imageManager, String url) {
                    // todo
                }
            });
        }
        viewHolder.name.setText(person.getName());
        viewHolder.relationshipStatus.setText(person.getStatus());
        viewHolder.course.setText(person.getCourse());
        viewHolder.bio.setText(person.getDescription());
        viewHolder.courseTitle.setText("Course");
        viewHolder.aboutTitle.setText("About");
    }

    @Override
    public int getItemCount() {
        return people == null ? 0 : people.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout backgroundImageLayout;
        public TextView name;
        public TextView relationshipStatus;
        public TextView course;
        public TextView bio;
        public TextView courseTitle;
        public TextView aboutTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            backgroundImageLayout = (LinearLayout) itemView.findViewById(R.id.image);
            name                  = (TextView)     itemView.findViewById(R.id.name);
            relationshipStatus    = (TextView)     itemView.findViewById(R.id.relationship_status);
            course                = (TextView)     itemView.findViewById(R.id.course);
            bio                   = (TextView)     itemView.findViewById(R.id.bio);
            courseTitle           = (TextView)     itemView.findViewById(R.id.courseTitle);
            aboutTitle            = (TextView)     itemView.findViewById(R.id.aboutTitle);
        }

    }
}
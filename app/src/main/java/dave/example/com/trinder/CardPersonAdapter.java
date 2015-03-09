import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CardPersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder>{

    private List<Person> people;
    private int cardLayout;
    private Context mContext;

    public CardPersonAdapter(Context context, int cardLayout, List<People> people) {
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
        // set image
        viewHolder.name.setText(person.getName());
        viewHolder.relationshipStatus.setText(person.getStatus());
        viewHolder.course.setText(person.getCourse());
        viewHolder.bio.setText(person.getDescription());
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
        public Button viewMore;

        public ViewHolder(View itemView) {
            super(itemView);
            backgroundImageLayout = (LinearLayout) convertView.findViewById(R.id.image);
            name                  = (TextView)     convertVIew.findViewById(R.id.name);
            relationshipStatus    = (TextView)     convertView.findViewById(R.id.relationship_status);
            course                = (TextView)     convertView.findViewById(R.id.course);
            bio                   = (TextView)     convertView.findViewById(R.id.bio);
        }

    }
}

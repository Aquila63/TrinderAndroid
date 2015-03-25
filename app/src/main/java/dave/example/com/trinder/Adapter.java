package dave.example.com.trinder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by Dave on 07/02/15.
 */
public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder>{
    private LayoutInflater inflater;
    private ClickListener clickListener;
    private Context context;
    List<Info> data = Collections.emptyList();
    public Adapter(Context context,List<Info> data){
        this.context=context;
        inflater=LayoutInflater.from(context);
        this.data=data;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row, parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    public void setClickListener(ClickListener clickListener){
        this.clickListener=clickListener;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {    // Fill rows with data from Info
        Info current=data.get(position);
        holder.title.setText(current.title);
        holder.icon.setImageResource(current.iconId);
    }

    @Override
    public int getItemCount() {

        return data.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title;
        ImageView icon;
        public MyViewHolder(View itemView) {

            super(itemView);
            itemView.setOnClickListener(this);
            title = (TextView) itemView.findViewById(R.id.listText);
            icon = (ImageView) itemView.findViewById(R.id.listImage);
        }

        @Override
        public void onClick(View v) {
            if(clickListener!=null){
                clickListener.itemClicked(v,getPosition());
                /**Info info = data.get(getPosition());
                ActionBarActivity activity = null;
                Class klass = null;
                switch(info.title) {
                    case: "Matches" {
                        klass = MatchesActivity;
                        break;
                    }
                    case: "History" {
                        klass = HistoryActivity;
                        break;
                    }
                    default: {
                        break;
                    }
                }

                Intent intent = new Intent(this, klass);
                startActivity(intent);
                 */
            }
        }
    }
    public interface ClickListener{
        public void itemClicked(View view,int position);
    }
}

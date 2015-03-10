import butterknife.ButterKnife;
import butterknife.InjectView;

public class SwipeCardView extends CardView {
    
    @InjectView(R.id.image) LinearLayout backgroundImageLayout;
    @InjectView(R.id.name) TextView nameTextView;
    @InjectView(R.id.relationship_status) TextView relationshipStatusTextView;
    @InjectView(R.id.course) TextView courseTextView;
    @InjectView(R.id.bio) TextView bioTextView;
    // view more button
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.card_view, container, false);
        return view;
    }
    
    public LinearLayout getBackgroundImageLayout() {
        return backgroundImageLayout;
    }
    
    public TextView getNameTextView() {
        return nameTextView;
    }
    
    public TextView getRelationshipStatusTextView() {
        return relationshipStatusTextView;
    }
    
    public TextView getCourseTextView() {
        return courseTextView;
    }
    
    public TextView getBioTextView() {
        return bioTextView;
    }
}

package sg.edu.rp.c346.id20018354.mywatchedlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList <WatchList> watchLists;

    public CustomAdapter(Context context,int resource, ArrayList<WatchList> objects){
        super(context, resource, objects);
        parent_context=context;
        layout_id=resource;
        watchLists=objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvtitle = rowView.findViewById(R.id.textViewTitle);
        TextView tvDescription = rowView.findViewById(R.id.textViewDescription);
        TextView tvCategory= rowView.findViewById(R.id.textViewCategory);

        // Obtain the Android Version information based on the position
        WatchList currentwatchList = watchLists.get(position);

        // Set values to the TextView to display the corresponding information
        tvtitle.setText(currentwatchList.getTitle());
        tvDescription.setText(currentwatchList.getDescription());
        tvCategory.setText(currentwatchList.getCategory());

        return rowView;
    }

}

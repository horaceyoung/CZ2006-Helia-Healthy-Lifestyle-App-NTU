package com.zy.helia.Activities;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;


import com.zy.helia.R;
import com.zy.helia.Event_Data.DatabaseHelp;

import java.util.ArrayList;
import java.util.List;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.ViewHolder> {

    private Context context;

    public EventListAdapter(Context adapterContext) {
        context = adapterContext;
    }

    private Cursor typeds = new DatabaseHelp(context).viewPopularEvents(0);
    private ArrayList<String> EventName = new ArrayList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public Button button;
        public ViewHolder(View v) {
            super(v);
            button = v.findViewById(R.id.button);
        }
    }

    @NonNull
    @Override
    public EventListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_event_list_adapter, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull EventListAdapter.ViewHolder holder, int position) {


        while (typeds.moveToNext())
        {
            int eventIndex = typeds.getColumnIndex("Event_Name");
            String eventName = typeds.getString(eventIndex);
            EventName.add(eventName);
        }

        holder.button.setText(EventName.get(position));
    }

    @Override
    public int getItemCount() {
        return EventName.size();
    }
}

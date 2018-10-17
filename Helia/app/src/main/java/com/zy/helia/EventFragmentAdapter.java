package com.zy.helia;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.zy.helia.Activities.EventDetail;
import com.zy.helia.Event_Data.DatabaseHelp;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class EventFragmentAdapter extends RecyclerView.Adapter<EventFragmentAdapter.ViewHolder> {

    private static Context context;
    private Button button;

    private Cursor eventds;
    private ArrayList<String> EventName = new ArrayList<>();


    public EventFragmentAdapter(Context context) {
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ViewHolder(final View v) {
            super(v);
        }
    }


    @Override
    public EventFragmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_event_adapter, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final EventFragmentAdapter.ViewHolder holder, final int position) {
        try {
            DatabaseHelp db = new DatabaseHelp(context);
            eventds = db.viewPendingEvents();
            if(eventds==null)
                Log.d(TAG, "NULL");
        }
        catch (Exception e){
            Log.d(TAG, "ERROR");
        }
        while (eventds.moveToNext())
        {
            int eventIndex = eventds.getColumnIndex("Event_Name");
            String eventName = eventds.getString(eventIndex);
            Log.d(TAG, "Event name" +eventName);
            EventName.add(eventName);
        }
        button.setText(EventName.get(position));

        button = holder.itemView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView name = holder.itemView.findViewById(R.id.name);
                name.setText(EventName.get(position));
                Intent startNewActivity = new Intent(context,EventDetail.class);
                context.startActivity(startNewActivity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return EventName.size();
    }
}


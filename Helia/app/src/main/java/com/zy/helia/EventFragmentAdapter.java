package com.zy.helia;

import android.content.Context;
import android.content.Intent;
<<<<<<< HEAD
=======
import android.database.Cursor;
>>>>>>> a76da3f59dc818fc76068dbf7ce9e5cb8900fb70
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.zy.helia.Activities.EventDetail;
import java.util.ArrayList;


public class EventFragmentAdapter extends RecyclerView.Adapter<EventFragmentAdapter.ViewHolder> {

    private static Context context;
    private Button button;

<<<<<<< HEAD
    // two parameter for this class - context and an ArrayList containing the EventName to be in the recyclerView
    public EventFragmentAdapter(Context context, ArrayList<String> EventName) {
        this.context = context;
        // get the number of EventName when the class is constructed
        this.totalCount = EventName.size();
        this.EventName = EventName;
=======
    private Cursor eventds;
    private ArrayList<String> EventName = new ArrayList<>();


    public EventFragmentAdapter(Context context) {
        this.context = context;
>>>>>>> a76da3f59dc818fc76068dbf7ce9e5cb8900fb70
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
<<<<<<< HEAD
        holder.button.setText(EventName.get(position));

        holder.button.setOnClickListener(new View.OnClickListener() {
=======
        Toast.makeText(context,"WuZiang",Toast.LENGTH_LONG).show();
        Toast.makeText(context, "onBindViewHolder", Toast.LENGTH_SHORT).show();

        try {
            DatabaseHelp db = new DatabaseHelp(context);
            eventds = db.viewPendingEvents();
            if(eventds==null)
                Toast.makeText(context,"Null",Toast.LENGTH_LONG).show();
        }
        catch (Exception e){
            Toast.makeText(context,"Error",Toast.LENGTH_LONG).show();
        }
        while (eventds.moveToNext())
        {
            int eventIndex = eventds.getColumnIndex("Event_Name");
            String eventName = eventds.getString(eventIndex);
            Toast.makeText(context,eventName,Toast.LENGTH_LONG).show();
            EventName.add(eventName);
        }
        button.setText(EventName.get(position));

        button = holder.itemView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
>>>>>>> a76da3f59dc818fc76068dbf7ce9e5cb8900fb70
            @Override
            public void onClick(View v) {
                // pass information to another activity started by click the button
                Intent startNewActivity = new Intent(context,EventDetail.class);
<<<<<<< HEAD

                startNewActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startNewActivity.putExtra("EventName", EventName.get(position));

                context.startActivity(startNewActivity);

=======
                context.startActivity(startNewActivity);
>>>>>>> a76da3f59dc818fc76068dbf7ce9e5cb8900fb70
            }
        });
    }

    @Override
    // the getItemCount should return the size of the ArrayList
    public int getItemCount() {
        return EventName.size();
    }
}


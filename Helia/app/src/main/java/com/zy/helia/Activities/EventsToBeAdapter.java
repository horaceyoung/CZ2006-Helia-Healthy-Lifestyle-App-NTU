package com.zy.helia.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.support.v7.widget.RecyclerView.Adapter;

import com.zy.helia.R;

import java.util.ArrayList;

public class EventsToBeAdapter extends RecyclerView.Adapter<EventsToBeAdapter.ViewHolder> {

    private static Context context;
    private int totalCount;
    private ArrayList<String> EventName;

    public EventsToBeAdapter(Context context,ArrayList<String> EventName) {
        this.context = context;
        // get the number of EventName when the class is constructed
        this.totalCount = EventName.size();
        this.EventName = EventName;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public Button button;
        public ViewHolder(View v) {
            super(v);
            button = v.findViewById(R.id.eventTobeButton);
        }
    }


    @NonNull
    @Override
    public EventsToBeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_events_to_be_adapter, parent, false);
        EventsToBeAdapter.ViewHolder vh = new EventsToBeAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull EventsToBeAdapter.ViewHolder holder, final int position) {
        holder.button.setText(EventName.get(position));

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass information to another activity started by click the button
                Intent startNewActivity = new Intent(context,EventDetail.class);

                startNewActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startNewActivity.putExtra("EventName", EventName.get(position));

                context.startActivity(startNewActivity);

            }
        });
    }

    @Override
    public int getItemCount() {
        return totalCount;
    }
}

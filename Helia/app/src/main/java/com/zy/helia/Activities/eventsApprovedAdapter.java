package com.zy.helia.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.EventLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.support.v7.widget.RecyclerView.Adapter;

import com.zy.helia.R;

import java.util.ArrayList;
import java.util.List;

public class eventsApprovedAdapter extends RecyclerView.Adapter<eventsApprovedAdapter.ViewHolder> {

    private static Context context;
    private int totalCount;
    private ArrayList<String> EventName;
    private List<Integer> EventID;

    public eventsApprovedAdapter(Context context,ArrayList<String> EventName, List<Integer> EventID) {
        this.context = context;
        // get the number of EventName when the class is constructed
        this.totalCount = EventName.size();
        this.EventName = EventName;
        this.EventID = EventID;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public Button button;
        public ViewHolder(View v) {
            super(v);
            button = v.findViewById(R.id.eventApprovedButton);
        }
    }


    @NonNull
    @Override
    public eventsApprovedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_events_approved_adapter, parent, false);
        eventsApprovedAdapter.ViewHolder vh = new eventsApprovedAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull eventsApprovedAdapter.ViewHolder holder, final int position) {
        holder.button.setText(EventName.get(position));

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass information to another activity started by click the button
                Intent startNewActivity = new Intent(context,eventDetailApproved.class);

                startNewActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startNewActivity.putExtra("EventID", Integer.toString(EventID.get(position)));

                context.startActivity(startNewActivity);

            }
        });
    }

    @Override
    public int getItemCount() {
        return totalCount;
    }
}
package com.zy.helia.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.zy.helia.R;

import java.util.ArrayList;
import java.util.List;

public class MeRegisteredEventListAdapter extends RecyclerView.Adapter<MeRegisteredEventListAdapter.ViewHolder> {


    private static Context context;
    private int totalCount;
    private ArrayList<String> EventName;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public Button button;
        public ViewHolder(View v) {
            super(v);
            button = v.findViewById(R.id.registeredEventButton);
        }
    }

    public MeRegisteredEventListAdapter(Context context, ArrayList<String> EventName) {
        this.context = context;
        // get the number of EventName when the class is constructed
        this.totalCount = EventName.size();
        this.EventName = EventName;
    }

    @NonNull
    @Override
    public MeRegisteredEventListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_me_registered_event_list_adapter, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MeRegisteredEventListAdapter.ViewHolder holder, final int position) {
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

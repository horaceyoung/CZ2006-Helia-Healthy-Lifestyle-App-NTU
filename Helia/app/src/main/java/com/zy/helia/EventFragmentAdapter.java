package com.zy.helia;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zy.helia.Activities.CreateEvent;
import com.zy.helia.Activities.eventTypes;

public class EventFragmentAdapter extends RecyclerView.Adapter<EventFragmentAdapter.ViewHolder> {

    private String[] mDataset = {"Event#1","Event#2","Event#3","Event#4","Event#5"};

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public Button button;
        public ViewHolder(View v) {
            super(v);
            button = v.findViewById(R.id.button);
        }

    }

    public EventFragmentAdapter() {

    }

    @NonNull
    @Override
    public EventFragmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_event_adapter, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull EventFragmentAdapter.ViewHolder holder, int position) {
        holder.button.setText(mDataset[position]);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}


package com.zy.helia.Activities;

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

public class MeMyEventsListAdapter extends RecyclerView.Adapter<MeMyEventsListAdapter.ViewHolder> {
    private int[] mDataset = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public Button button;
        public ViewHolder(View v) {
            super(v);
            button = v.findViewById(R.id.meMyEventsButton);
        }
    }
    public MeMyEventsListAdapter() {
    }

    @NonNull
    @Override
    public MeMyEventsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_me_my_events_list_adapter, parent, false);
        MeMyEventsListAdapter.ViewHolder vh = new MeMyEventsListAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MeMyEventsListAdapter.ViewHolder holder, int position) {
        holder.button.setText(Integer.toString(mDataset[position]));
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}

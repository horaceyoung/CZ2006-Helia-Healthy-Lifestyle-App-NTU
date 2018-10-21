package com.zy.helia;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class EventFragmentType extends RecyclerView.Adapter<EventFragmentType.ViewHolder> {

    private String[] mDataset = {"Running", "Swimming", "Basketball", "Soccer"};

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public Button button;
        public ViewHolder(View v) {
            super(v);
            button = v.findViewById(R.id.button);
        }
    }

    public EventFragmentType() {

    }

    @NonNull
    @Override
    public EventFragmentType.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_event_type, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull EventFragmentType.ViewHolder holder, int position) {
        holder.button.setText(mDataset[position]);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
package com.zy.helia;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.zy.helia.R;

import java.util.List;

public class EventFragmentAdapter extends RecyclerView.Adapter<EventFragmentAdapter.ViewHolder> {

    private int[] mDataset = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

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
        holder.button.setText(Integer.toString(mDataset[position]));
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}


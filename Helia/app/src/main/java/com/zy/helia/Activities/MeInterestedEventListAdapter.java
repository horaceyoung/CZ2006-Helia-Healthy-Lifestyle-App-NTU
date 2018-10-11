package com.zy.helia.Activities;

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

public class MeInterestedEventListAdapter extends RecyclerView.Adapter<MeInterestedEventListAdapter.ViewHolder> {

    private int[] mDataset = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public Button button;
        public ViewHolder(View v) {
            super(v);
            button = v.findViewById(R.id.button);
        }
    }

    public MeInterestedEventListAdapter() {

    }

    @NonNull
    @Override
    public MeInterestedEventListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_me_interested_event_list_adapter, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MeInterestedEventListAdapter.ViewHolder holder, int position) {
        holder.button.setText(Integer.toString(mDataset[position]));
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}

package com.zy.helia.Activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import com.zy.helia.Activities.CreateEvent;
import com.zy.helia.Event_Data.DatabaseHelp;
import com.zy.helia.R;

import java.util.List;

public class eventTypesAdapter extends RecyclerView.Adapter<eventTypesAdapter.ViewHolder> {

    private int[] etmDataset = {0, 1, 2, 3, 4, 5, 6,7};

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public Button eventTypeSelection;
        public ViewHolder(View v) {
            super(v);
            eventTypeSelection = v.findViewById(R.id.typesBut);
        }
    }

    public eventTypesAdapter() {

    }


    @NonNull
    @Override
    public eventTypesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_event_types_adapter, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(@NonNull eventTypesAdapter.ViewHolder holder, int position) {
        holder.eventTypeSelection.setText(Integer.toString(etmDataset[position]));
    }

    @Override
    public int getItemCount() {
        return etmDataset.length;
    }


}

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

public class TypeListAdapter extends RecyclerView.Adapter<TypeListAdapter.ViewHolder> {

    private static Context context;
    private String[] Types = {"Aerobics", "Badminton", "Basketball", "Running", "Soccer", "Swimming"};
    private int[] TypeIDs = {6, 3, 2, 4, 1, 5};

    public TypeListAdapter(Context context) {
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public Button button;
        public ViewHolder(View v) {
            super(v);
            button = v.findViewById(R.id.button);
        }
    }

    public TypeListAdapter() {

    }

    @NonNull
    @Override
    public TypeListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_type_list_adapter, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull TypeListAdapter.ViewHolder holder, final int position) {
        holder.button.setText(Types[position]);

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass information to another activity started by click the button
                Intent startNewActivity = new Intent(context,EventList.class);

                startNewActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startNewActivity.putExtra("TypeID", Integer.toString(TypeIDs[position]));

                context.startActivity(startNewActivity);

            }
        });
    }

    @Override
    public int getItemCount() {
        return Types.length;
    }
}

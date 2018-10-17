package com.zy.helia;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zy.helia.Activities.EventDetail;
import com.zy.helia.Event_Data.DatabaseHelp;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class EventFragmentAdapter extends RecyclerView.Adapter<EventFragmentAdapter.ViewHolder> {

    private static Context context;
    private int totalCount;
    private ArrayList<String> EventName;

    private Button button;





    public EventFragmentAdapter(Context context, ArrayList<String> EventName) {

        this.context = context;
        this.totalCount = EventName.size();
        this.EventName = EventName;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public Button button;
        // each data item is just a string in this case
        public ViewHolder(final View v) {
            super(v);
            button = v.findViewById(R.id.button);
        }
    }


    @Override
    public EventFragmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_event_adapter, parent, false);
        ViewHolder vh = new ViewHolder(v);



        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final EventFragmentAdapter.ViewHolder holder, final int position) {


        holder.button.setText(EventName.get(position));

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView name = holder.itemView.findViewById(R.id.name);
                name.setText(EventName.get(position));
                Intent startNewActivity = new Intent(context,EventDetail.class);
                context.startActivity(startNewActivity);
            }
        });




    }

    @Override
    public int getItemCount() {
        return totalCount;
    }
}


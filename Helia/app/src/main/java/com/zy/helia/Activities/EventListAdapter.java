package com.zy.helia.Activities;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.zy.helia.R;

import java.util.List;

public class EventListAdapter extends RecyclerView.Adapter <EventListAdapter.MyViewHolder>{
    private int[] displayNum = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public Button button;
        public MyViewHolder(Button v){
            super(v);
            button = v;
        }
    }

    public EventListAdapter(){

    }
    @NonNull
    @Override
    public EventListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        ConstraintLayout ConsLayout = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_event_list_adapter, parent, false);
        Button btn = ConsLayout.findViewById(R.id.imgBtn);
        MyViewHolder vh = new MyViewHolder(btn);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull EventListAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.button.setText(displayNum[i]);
    }

    @Override
    public int getItemCount() {
        return displayNum.length;
    }
}

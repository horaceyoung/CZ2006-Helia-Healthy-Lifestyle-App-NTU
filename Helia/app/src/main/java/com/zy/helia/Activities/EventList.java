package com.zy.helia.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageButton;
import com.zy.helia.R;
import android.support.v7.widget.RecyclerView;

public class EventList extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        mRecyclerView = (RecyclerView) findViewById(R.id.eventListRV);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager((mLayoutManager));

        mAdapter = new EventListAdapter();
        mRecyclerView.setAdapter(mAdapter);

    }

}

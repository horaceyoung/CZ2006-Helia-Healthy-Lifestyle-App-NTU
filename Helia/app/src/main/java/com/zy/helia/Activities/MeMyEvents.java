package com.zy.helia.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.Log;

import com.zy.helia.Activities.MeMyEventsListAdapter;
import com.zy.helia.Event_Data.DatabaseHelp;
import com.zy.helia.MeIREvents;
import com.zy.helia.R;

import java.util.ArrayList;
import java.util.List;

public class MeMyEvents extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<String> EventName = new ArrayList<>();
    private List<Integer> EventID = new ArrayList<Integer>();
    @Override
    public void onBackPressed() {
        Intent goBack = new Intent(MeMyEvents.this, MainActivity.class);
        goBack.putExtra("id", 2);
        startActivity(goBack);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me__my_events);


        DatabaseHelp dbHelper = new DatabaseHelp(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor eventCursor = dbHelper.viewMyEvent(LoginActivity.getUserID());

        while (eventCursor.moveToNext())
        {
            int eventIndex = eventCursor.getColumnIndex("Event_Name");
            int statusIndex = eventCursor.getColumnIndex("Event_Approval_Status");
            String eventName = eventCursor.getString(eventIndex)+" ("+eventCursor.getString(statusIndex)+")";
            EventName.add(eventName);

            int IDIndex = eventCursor.getColumnIndex("Event_ID");
            int eventID = eventCursor.getInt(IDIndex);
            EventID.add(eventID);
        }
        db.close();

        mRecyclerView = (RecyclerView) findViewById(R.id.MeEventListRV);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager((mLayoutManager));

        mAdapter = new MeMyEventsListAdapter(getBaseContext(), EventName, EventID);
        mRecyclerView.setAdapter(mAdapter);




    }

}

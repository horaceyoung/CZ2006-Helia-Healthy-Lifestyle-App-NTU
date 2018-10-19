package com.zy.helia.Activities;

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
import com.zy.helia.R;

import java.util.ArrayList;

public class MeMyEvents extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Cursor eventds;
    private ArrayList<String> EventName = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me__my_events);


//for RV from ziang+++++++++++++++++++++++++++++++++++++++++++++++=

        DatabaseHelp dbHelper = new DatabaseHelp(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor eventCursor = dbHelper.viewPendingEvents(db);

        Log.d("EventActivity", "This is called" );
        while (eventCursor.moveToNext())
        {
            int eventIndex = eventCursor.getColumnIndex("Event_Name");
            String eventName = eventCursor.getString(eventIndex);
            Log.d("EventActivity", "Event name 1233" +eventName);
            EventName.add(eventName);
        }
        db.close();
//block end+++++++++++++++++++++++++++++++++++++++++++++++++++++++
        mRecyclerView = (RecyclerView) findViewById(R.id.MeEventListRV);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager((mLayoutManager));

        mAdapter = new MeMyEventsListAdapter(getBaseContext(), EventName);
        mRecyclerView.setAdapter(mAdapter);




    }

}

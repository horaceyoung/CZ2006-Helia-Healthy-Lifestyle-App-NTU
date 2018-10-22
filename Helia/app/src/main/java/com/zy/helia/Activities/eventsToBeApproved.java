package com.zy.helia.Activities;

import android.content.Intent;
import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.LinearLayoutManager;
        import android.util.Log;
        import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

        import com.zy.helia.Event_Data.DatabaseHelp;
        import com.zy.helia.R;
        import android.support.v7.widget.RecyclerView;

        import java.util.ArrayList;
import java.util.List;

public class eventsToBeApproved extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<String> EventName = new ArrayList<>();
    private List<Integer> EventID = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_to_be_approved);



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

            int IDIndex = eventCursor.getColumnIndex("Event_ID");
            int eventID = eventCursor.getInt(IDIndex);
            EventID.add(eventID);
        }
        db.close();


        mRecyclerView = (RecyclerView) findViewById(R.id.TobeEventListRV);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager((mLayoutManager));

        mAdapter = new EventsToBeAdapter(getBaseContext(), EventName, EventID);
        mRecyclerView.setAdapter(mAdapter);

    }


}
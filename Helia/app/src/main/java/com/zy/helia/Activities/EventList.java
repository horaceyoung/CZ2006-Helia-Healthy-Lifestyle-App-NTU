package com.zy.helia.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageButton;

import com.zy.helia.Event_Data.DatabaseHelp;
import com.zy.helia.R;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class EventList extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private int TypeID;
    private ArrayList<String> EventName = new ArrayList<>();
    private List<Integer> EventID = new ArrayList<Integer>();

    private TextView typename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        Intent intent = getIntent();

        TypeID = Integer.parseInt(intent.getStringExtra("TypeID"));

        typename = (TextView) findViewById(R.id.eventlist);
        switch (TypeID){
            case 1:
                typename.setText("Soccer");
                break;
            case 2:
                typename.setText("Basketball");
                break;
            case 3:
                typename.setText("Badminton");
                break;
            case 4:
                typename.setText("Running");
                break;
            case 5:
                typename.setText("Swimming");
                break;
            case 6:
                typename.setText("Aerobics");
                break;
            default:
                typename.setText("Event List");
                break;
        }

        DatabaseHelp dbHelper = new DatabaseHelp(getBaseContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor eventCursor = dbHelper.retrieveFilteredEvents(db, TypeID);

        EventName.clear();
        EventID.clear();

        while (eventCursor!=null && eventCursor.moveToNext())
        {
            int eventIndex = eventCursor.getColumnIndex("Event_Name");
            String eventName = eventCursor.getString(eventIndex);
            EventName.add(eventName);

            int IDIndex = eventCursor.getColumnIndex("Event_ID");
            int eventID = eventCursor.getInt(IDIndex);
            EventID.add(eventID);
        }
        db.close();

        mRecyclerView = (RecyclerView) findViewById(R.id.eventListRV);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager((mLayoutManager));

        mAdapter = new EventListAdapter(this, EventName, EventID);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onBackPressed() {
        Intent goBack = new Intent(EventList.this, MainActivity.class);
        goBack.putExtra("id", 1);
        startActivity(goBack);
    }

}

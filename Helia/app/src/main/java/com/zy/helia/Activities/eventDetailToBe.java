package com.zy.helia.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zy.helia.Event_Data.DatabaseHelp;
import com.zy.helia.R;

public class eventDetailToBe extends AppCompatActivity {

    private int eventID;
    private int userID;
    private TextView eventName;
    private TextView eventDescription;

    private Button approve;
    private Button reject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail_to_be);
        Intent intent = getIntent();

        eventID = Integer.parseInt(intent.getStringExtra("EventID"));
        userID = LoginActivity.getUserID();

        DatabaseHelp dbHelper = new DatabaseHelp(this);
        Cursor eventCursor = dbHelper.viewEvent(eventID);

        eventCursor.moveToNext();

        int eventIndex = eventCursor.getColumnIndex("Event_Name");
        String name = eventCursor.getString(eventIndex);

        eventName = (TextView) findViewById(R.id.name);
        eventName.setText(name);

        int descriptionIndex = eventCursor.getColumnIndex("Event_Description");
        String description = eventCursor.getString(descriptionIndex);

        eventDescription= (TextView) findViewById(R.id.descprition);
        eventDescription.setText(description);

        approve = (Button) findViewById(R.id.approve);
        approve.setText("Approve event");
        approve.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    DatabaseHelp DBHelper = new DatabaseHelp(getBaseContext());
                    DBHelper.approveEvent(eventID);
                    approve.setClickable(false);
                    approve.setText("Event approved");
                }
            });


        reject = (Button) findViewById(R.id.reject);
        reject.setText("Reject event");
        reject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    DatabaseHelp DBHelper = new DatabaseHelp(getBaseContext());
                    DBHelper.rejectEvent(eventID);
                    reject.setClickable(false);
                    reject.setText("Event rejected");
                }
            });

    }
}


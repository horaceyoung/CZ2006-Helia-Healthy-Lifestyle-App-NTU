package com.zy.helia.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zy.helia.Event_Data.DatabaseHelp;
import com.zy.helia.R;

public class eventDetailToBe extends AppCompatActivity {

    private int eventID;
    private int userID;
    private TextView eventName;
    private TextView eventDescription;
    private TextView eventNumber;
    private TextView eventDuration;

    private Button approve;
    private Button reject;
    private ImageView image;

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

        int durationIndex = eventCursor.getColumnIndex("Event_Duration");
        String duration = "Duration: "+eventCursor.getString(durationIndex);

        eventDuration= (TextView) findViewById(R.id.duration);
        eventDuration.setText(duration);

        int numberIndex = eventCursor.getColumnIndex("Number_Of_People");
        String number = "Number of Participants: "+ dbHelper.countRegistered(eventID)+" / "+Integer.toString(eventCursor.getInt(numberIndex));

        eventNumber= (TextView) findViewById(R.id.number);
        eventNumber.setText(number);

        approve = (Button) findViewById(R.id.interest);
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


        reject = (Button) findViewById(R.id.register);
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

        image = (ImageView) findViewById(R.id.imageEvent);
        int typeIndex = eventCursor.getColumnIndex("Event_Category_ID");
        int typeID = eventCursor.getInt(typeIndex);

        switch (typeID){
            case 1:
                image.setImageResource(R.drawable.type_soccer);
                break;
            case 2:
                image.setImageResource(R.drawable.type_basketball);
                break;
            case 3:
                image.setImageResource(R.drawable.type_badminton);
                break;
            case 4:
                image.setImageResource(R.drawable.type_running);
                break;
            case 5:
                image.setImageResource(R.drawable.type_swimming);
                break;
            case 6:
                image.setImageResource(R.drawable.type_aerobics);
                break;
            default:
                image.setImageResource(R.drawable.helia_white);
                break;
        }
    }
}


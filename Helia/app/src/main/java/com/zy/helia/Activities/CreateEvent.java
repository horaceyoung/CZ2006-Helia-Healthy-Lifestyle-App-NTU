package com.zy.helia.Activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zy.helia.Event_Data.DatabaseHelp;
import com.zy.helia.R;

public class CreateEvent extends AppCompatActivity {

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private EditText ce_Event_name;
    private EditText ce_EventDescription;
    //  private EditText ce_EventCategoryID;
    private EditText ce_EventLocation;
    private EditText ce_NumberOfPeople;
    private EditText ce_EventDuration;
    //  private EditText ce_EventPhoto;
    private EditText ce_UserID;

    private Button selectTypeBut;
    private Button submitBut;
    private Button test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        ce_Event_name = findViewById(R.id.ecEventName);
        ce_EventDescription = findViewById(R.id.ecEventDescription);
        ce_NumberOfPeople = findViewById(R.id.ecNumberOfParticipants);
        ce_EventLocation = findViewById(R.id.ecLocation);
        ce_EventDuration = findViewById(R.id.ecDurationEstimated);
        ce_UserID = findViewById(R.id.ecEmailAddress);

        submitBut = findViewById(R.id.submitBut);
        test = findViewById(R.id.cancelBut);
        selectTypeBut = findViewById(R.id.selectTypeBut);


        submitBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_eventname = ce_Event_name.getText().toString().trim();
                String str_eventdescription = ce_EventDescription.getText().toString().trim();
                String str_numberofpeople = ce_NumberOfPeople.getText().toString();
                int int_numberofpeople=Integer.parseInt(str_numberofpeople);
                String str_eventlocation = ce_EventLocation.getText().toString().trim();
                String str_eventduration = ce_EventDuration.getText().toString().trim();
                String str_email = ce_UserID.getText().toString().trim();

                createNewEvent(str_eventname, str_eventdescription, 1, str_eventlocation, int_numberofpeople, str_eventduration, 2, str_email);
            }
        });

        selectTypeBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent typeSelection = new Intent(CreateEvent.this, eventTypes.class);
                startActivity(typeSelection);
            }
        });

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent test = new Intent(CreateEvent.this, AdminMainPage.class);
                startActivity(test);
            }
        });


    }


    private void createNewEvent(String Event_Name, String Event_Description, int Event_Category_ID, String Event_Location, int Number_Of_People, String Event_Duration, int Event_Photo, String User_ID){

        // Create database helper
        DatabaseHelp ceDbHelper = new DatabaseHelp(this);
        // Gets the database in write mode
        SQLiteDatabase cedb = ceDbHelper.getWritableDatabase();

        Integer int_userid=ceDbHelper.checkUserIDByEmail(User_ID);

        // lack int categoryid & photo->1-5   ??
        ceDbHelper.createEvent(Event_Name, Event_Description, Event_Category_ID, Event_Location, Number_Of_People, Event_Duration, Event_Photo, int_userid );
    }



}

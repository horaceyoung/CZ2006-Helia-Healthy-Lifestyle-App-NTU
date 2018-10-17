package com.zy.helia.Activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.server.converter.StringToIntConverter;
import com.zy.helia.Event_Data.DatabaseHelp;
import com.zy.helia.R;

public class CreateEvent extends AppCompatActivity {

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public EditText ce_Event_name;
    public EditText ce_EventDescription;
    public int ce_EventCategoryID;
    private EditText ce_EventLocation;
    private EditText ce_NumberOfPeople;
    private EditText ce_EventDuration;
    //  private EditText ce_EventPhoto;
    private EditText ce_UserID;

    private Button submitBut;
    private Button test;


    Spinner etDropdownList;
    Spinner Array;
    String eventtypes[]={"Soccer","Basketball","Badminton","Running","Swimming","Aerobics"};
    ArrayAdapter<String>arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);


        etDropdownList=(Spinner)findViewById(R.id.events_dl);
        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,eventtypes);
        etDropdownList.setAdapter(arrayAdapter);






        etDropdownList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),"Your selection is: "+ eventtypes[i],Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });




        ce_Event_name = findViewById(R.id.ecEventName);
        ce_EventDescription = findViewById(R.id.ecEventDescription);
        ce_NumberOfPeople = findViewById(R.id.ecNumberOfParticipants);
        ce_EventLocation = findViewById(R.id.ecLocation);
        ce_EventDuration = findViewById(R.id.ecDurationEstimated);
        ce_UserID = findViewById(R.id.ecEmailAddress);
        etDropdownList=(Spinner)findViewById(R.id.events_dl);

        submitBut = findViewById(R.id.submitBut);
        test = findViewById(R.id.cancelBut);


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






                //int int_ce_EventCategoryID = (Integer)etDropdownList.getSelectedItem();


                //createNewEvent(str_eventname, str_eventdescription, int_ce_EventCategoryID, str_eventlocation, int_numberofpeople, str_eventduration, 2, str_email);

                //Toast.makeText(getBaseContext(), etDropdownList.getSelectedItem().toString(), Toast.LENGTH_LONG).show();


                //createNewEvent(str_eventname, str_eventdescription, 1, str_eventlocation, int_numberofpeople, str_eventduration, 2, str_email);


                //Toast.makeText(getBaseContext(), etDropdownList.getSelectedItem().toString(), Toast.LENGTH_LONG).show();


                createNewEvent(str_eventname, str_eventdescription, 0, str_eventlocation, int_numberofpeople, str_eventduration, 2,str_email);


                Intent submit = new Intent(CreateEvent.this, CreateEvent.class);
                startActivity(submit);
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


    private void createNewEvent(String Event_Name, String Event_Description, int Category_Id, String Event_Location, int Number_Of_People, String Event_Duration, int Event_Picture,String Email){

        // Create database helper
        DatabaseHelp ceDbHelper = new DatabaseHelp(this);
        // Gets the database in write mode
        SQLiteDatabase cedb = ceDbHelper.getWritableDatabase();


        //ceDbHelper.createEventCategory("Soccer", "Fun");

        //ceDbHelper.createEventCategory("Soccer", "Fun");
        //Integer int_userid = ceDbHelper.checkUserIDByEmail(Email);

        // lack int categoryid & photo->1-5   ??
        ceDbHelper.createEvent(Event_Name, Event_Description, 0, Event_Location, Number_Of_People, Event_Duration, Event_Picture, 9 );
    }



}

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


import com.zy.helia.Event_Data.DatabaseHelp;
import com.zy.helia.Activities.LoginActivity;
import com.zy.helia.R;

public class CreateEvent extends AppCompatActivity {

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private EditText ce_Event_name;
    private EditText ce_EventDescription;
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
                //String str_email = ce_UserID.getText().toString().trim();



                etDropdownList=(Spinner)findViewById(R.id.events_dl);
                String str_categoryId = etDropdownList.getSelectedItem().toString();
                switch (str_categoryId){
                    case "Soccer":
                        ce_EventCategoryID=1;
                        break;
                    case "Basketball":
                        ce_EventCategoryID=2;
                        break;
                    case "Badminton":
                        ce_EventCategoryID=3;
                        break;
                    case "Running":
                        ce_EventCategoryID=4;
                        break;
                    case "Swimming":
                        ce_EventCategoryID=5;
                        break;
                    case "Aerobics":
                        ce_EventCategoryID=6;
                        break;
                    default:
                        ce_EventCategoryID=0;
                        break;
                }

                createNewEvent(str_eventname, str_eventdescription, ce_EventCategoryID, str_eventlocation, int_numberofpeople, str_eventduration, 2,LoginActivity.getUserID());


                onBackPressed();
            }
        });


        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }


    private void createNewEvent(String Event_Name, String Event_Description, int Category_Id, String Event_Location, int Number_Of_People, String Event_Duration, int Event_Picture,int User_Id){

        // Create database helper
        DatabaseHelp ceDbHelper = new DatabaseHelp(this);
        // Gets the database in write mode
        SQLiteDatabase cedb = ceDbHelper.getWritableDatabase();


        // lack userId photo->1-5   ??
        ceDbHelper.createEvent(Event_Name, Event_Description, Category_Id, Event_Location, Number_Of_People, Event_Duration, Event_Picture, User_Id );
    }



}

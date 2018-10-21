package com.zy.helia.Activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zy.helia.Event_Data.DatabaseHelp;
import com.zy.helia.R;

import java.util.ArrayList;
import java.util.HashMap;


public class EventDetail extends AppCompatActivity {

    private static ArrayList<String> interestedlist = new ArrayList<String>();
    private static ArrayList<String> registeredlist = new ArrayList<String>();

    private int eventID;
    private int userID;
    private TextView eventName;
    private TextView eventDescription;

    private Button register;
    private Button interested;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        Intent intent = getIntent();

        eventName = (TextView) findViewById(R.id.name);
        String name = intent.getStringExtra("EventName");
        eventName.setText(name);

        eventDescription= (TextView) findViewById(R.id.descprition);
        String description = intent.getStringExtra("EventDescription");
        eventDescription.setText(description);

        eventID = Integer.parseInt(intent.getStringExtra("EventID"));
        userID = LoginActivity.getUserID();

        String event = "EventID"+eventID+"UserID"+userID;

        register = (Button) findViewById(R.id.register);
<<<<<<< HEAD
        if ((!registeredlist.isEmpty())&&(registeredlist.indexOf(event)!=-1)) {
            register.setClickable(false);
            register.setText("Successfully Registered");
        }
        else {
            registeredlist.add(event);
            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Create database helper
                    DatabaseHelp DBHelper = new DatabaseHelp(getBaseContext());
                    // Gets the database in write mode
                    SQLiteDatabase DB = DBHelper.getWritableDatabase();

                    DBHelper.addRegistered(eventID, userID);
                    Toast.makeText(getBaseContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
                    register.setClickable(false);
                    register.setText("Successfully Registered");
                }
            });
        }
=======
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create database helper
                DatabaseHelp DBHelper = new DatabaseHelp(getBaseContext());
                // Gets the database in write mode
                SQLiteDatabase DB = DBHelper.getWritableDatabase();
                if(register.getText().equals("Register")){
                    DBHelper.addRegistered(eventID, LoginActivity.getUserID());
                    register.setText("Registered");
                    Toast.makeText(getBaseContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
                }
                if(register.getText().equals("Registered")){
                    DBHelper.removeRegistered(eventID, LoginActivity.getUserID());
                    register.setText("Register");
                    Toast.makeText(getBaseContext(), "Registration Cancelled", Toast.LENGTH_SHORT).show();
                }
            }
        });
>>>>>>> 64c8ab342aec3d4cdf0c0d0545f62149ec6a1a41

        interested = (Button) findViewById(R.id.interest);
        if ((!interestedlist.isEmpty())&&(interestedlist.indexOf(event)!=-1)){
            interested.setClickable(false);
            interested.setText("Added into Interested List");
        }
        else{
            interestedlist.add(event);
            interested.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Create database helper
                    DatabaseHelp DBHelper = new DatabaseHelp(getBaseContext());
                    // Gets the database in write mode
                    SQLiteDatabase DB = DBHelper.getWritableDatabase();

                    DBHelper.addInterested(eventID, userID);
                    Toast.makeText(getBaseContext(), "Added to Interested List", Toast.LENGTH_SHORT).show();

                    interested.setClickable(false);
                    interested.setText("Added into Interested List");
                }
            });
        }

    }
}

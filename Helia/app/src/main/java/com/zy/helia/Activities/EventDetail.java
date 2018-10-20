package com.zy.helia.Activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zy.helia.Event_Data.DatabaseHelp;
import com.zy.helia.R;


public class EventDetail extends AppCompatActivity {

    private int eventID;
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

        register = (Button) findViewById(R.id.register);
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

        interested = (Button) findViewById(R.id.interest);
        interested.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create database helper
                DatabaseHelp DBHelper = new DatabaseHelp(getBaseContext());
                // Gets the database in write mode
                SQLiteDatabase DB = DBHelper.getWritableDatabase();

                DBHelper.addInterested(eventID, LoginActivity.getUserID());
                Toast.makeText(getBaseContext(), "Added to Interested List", Toast.LENGTH_SHORT).show();
            }
        });

    }
}

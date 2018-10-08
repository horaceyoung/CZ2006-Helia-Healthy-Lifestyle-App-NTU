package com.zy.helia.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import com.zy.helia.R;


public class EventList extends AppCompatActivity {

    private ImageButton rank1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        rank1 = (ImageButton) findViewById(R.id.rank1);

        rank1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEventDetail();
            }
        });

    }

    public void openEventDetail() {
        Intent intent = new Intent(this, EventDetail.class);
        startActivity(intent);
    }
}

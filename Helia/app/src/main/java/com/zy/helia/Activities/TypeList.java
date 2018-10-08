package com.zy.helia.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import com.zy.helia.R;


public class TypeList extends AppCompatActivity {

    private ImageButton typeA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_list);

        typeA = (ImageButton) findViewById(R.id.typeA);

        typeA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEventList();
            }
        });

    }

    public void openEventList() {
        Intent intent = new Intent(this, EventList.class);
        startActivity(intent);
    }
}

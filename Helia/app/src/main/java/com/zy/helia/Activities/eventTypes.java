package com.zy.helia.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import com.zy.helia.R;
import android.support.v7.widget.RecyclerView;

public class eventTypes extends AppCompatActivity {

    private RecyclerView etmRecyclerView;
    private RecyclerView.Adapter etmAdapter;
    private RecyclerView.LayoutManager etmLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_types);

        etmRecyclerView = (RecyclerView) findViewById(R.id.eventTypesRV);
        etmRecyclerView.setHasFixedSize(true);
        etmLayoutManager = new LinearLayoutManager(this);
        etmRecyclerView.setLayoutManager((etmLayoutManager));

        etmAdapter = new eventTypesAdapter();
        etmRecyclerView.setAdapter(etmAdapter);

    }

    @Override
    public void onBackPressed() {
        Intent goBack = new Intent(eventTypes.this, MainActivity.class);
        goBack.putExtra("id", 1);
        startActivity(goBack);
    }
}

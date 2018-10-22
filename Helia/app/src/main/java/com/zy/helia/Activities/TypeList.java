package com.zy.helia.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import com.zy.helia.R;
import android.support.v7.widget.RecyclerView;

public class TypeList extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_list);

        mRecyclerView = (RecyclerView) findViewById(R.id.typeListRV);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager((mLayoutManager));

        mAdapter = new TypeListAdapter(getBaseContext());
        mRecyclerView.setAdapter(mAdapter);

    }

}

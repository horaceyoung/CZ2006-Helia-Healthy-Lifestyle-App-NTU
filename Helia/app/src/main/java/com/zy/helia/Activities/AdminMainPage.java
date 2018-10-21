package com.zy.helia.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.zy.helia.R;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class AdminMainPage extends AppCompatActivity {

    public Button toBeApproved;

    public void initToBeApproved(){
        toBeApproved=(Button)findViewById(R.id.toBeApprovedBut);
        toBeApproved.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
              Intent toBeApproved = new Intent(AdminMainPage.this, eventsToBeApproved.class);
                startActivity(toBeApproved);
            }
        });

    }



    public Button approved;

    public void initApproved(){
        approved=(Button)findViewById(R.id.approvedBut);
        approved.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent approved = new Intent(AdminMainPage.this, eventsApproved.class);
                startActivity(approved);
            }
        });

    }


    public Button logOut;

    public void initLogOut(){
        logOut=(Button)findViewById(R.id.logOutBut);
        logOut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent logOut = new Intent(AdminMainPage.this, adminLogOut.class);
                startActivity(logOut);
            }
        });

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main_page);
        initToBeApproved();
        initApproved();
        initLogOut();
    }
}

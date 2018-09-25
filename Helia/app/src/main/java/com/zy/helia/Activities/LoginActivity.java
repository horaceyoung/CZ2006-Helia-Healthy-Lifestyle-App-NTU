package com.zy.helia.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zy.helia.R;

public class LoginActivity extends AppCompatActivity {
    private Button mRegisterButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mRegisterButton = findViewById(R.id.registerButton);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jumpRegisterIntent = new Intent(getBaseContext(), RegistrationActivity.class);
                startActivity(jumpRegisterIntent);
            }
        });
    }


}

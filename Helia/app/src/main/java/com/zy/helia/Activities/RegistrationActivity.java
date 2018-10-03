package com.zy.helia.Activities;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zy.helia.Account_Data.AccountContract.AccountEntry;
import com.zy.helia.Account_Data.AccountDBHelper;
import com.zy.helia.R;

public class RegistrationActivity extends AppCompatActivity {
    private EditText mUsername;
    private EditText mPassword;
    private EditText mEmail;
    private Button mRegiterSubmitBtn;
    private Button mCancelBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        mUsername = findViewById(R.id.usernameRegi);
        mPassword = findViewById(R.id.passwordRegi);
        mEmail = findViewById(R.id.EmailRegi);

        mRegiterSubmitBtn = findViewById(R.id.registerButton);
        mCancelBtn = findViewById(R.id.cancelButton);


        mRegiterSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUsername.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String email = mEmail.getText().toString().trim();
                if(checkAccountInfoEligibility(username, password, email)){
                    createNewAccount(username, password, email);
                }
            }
        });

        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private boolean checkAccountInfoEligibility(String username, String password, String email){
        //To be completed later (for now don't check is duplicate usernames exist)
        return true;
    }

    private void createNewAccount(String username, String password, String email){
        // Create database helper
        AccountDBHelper mDbHelper = new AccountDBHelper(this);

        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and pet attributes from the editor are the values.
        ContentValues accountValues = new ContentValues();
        accountValues.put(AccountEntry.COLUMN_USERNAME, username);
        accountValues.put(AccountEntry.COLUMN_PASSWORD, password);
        accountValues.put(AccountEntry.COLUMN_EMAIL, email);

        // Insert a new row for pet in the database, returning the ID of that new row.
        long newRowId = db.insert(AccountEntry.TABLE_NAME, null, accountValues);
        // Show a toast message depending on whether or not the insertion was successful
        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with Account Creating", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "Account Created with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }
}

package com.zy.helia.Event_Data;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.Closeable;



public class DatabaseHelp extends SQLiteOpenHelper implements Closeable{

    public static final String DATABASE_NAME = "Helia.db";

    public DatabaseHelp(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE User (User_ID INTEGER PRIMARY KEY AUTOINCREMENT, Username TEXT, Password TEXT, Email TEXT, Avatar INTEGER)");
        db.execSQL("CREATE TABLE Event_Category (Event_Category_ID INTEGER PRIMARY KEY AUTOINCREMENT, Event_Category_Name TEXT, Event_Category_Description TEXT)");
        db.execSQL("CREATE TABLE Event (Event_ID INTEGER PRIMARY KEY AUTOINCREMENT, Event_Name TEXT, Event_Description TEXT, Event_Category_ID INTEGER, Number_Of_People integer, Event_Location text, Event_Duration text, Event_Picture integer, Event_Approval_Status INTEGER, User_ID INTEGER, FOREIGN KEY(Event_Category_ID) REFERENCES Event_Category(Event_Category_ID), FOREIGN KEY(User_ID) REFERENCES User(User_ID))");
        db.execSQL("CREATE TABLE Interested (Interested_ID integer PRIMARY KEY AUTOINCREMENT, User_ID Integer, Event_ID integer, FOREIGN KEY(User_ID) REFERENCES User(User_ID), FOREIGN KEY (Event_ID) REFERENCES Event(Event_ID))");
        db.execSQL("CREATE TABLE Registered (Registered_ID integer PRIMARY KEY AUTOINCREMENT, User_ID Integer, Event_ID integer, FOREIGN KEY(User_ID) REFERENCES User(User_ID), FOREIGN KEY (Event_ID) REFERENCES Event(Event_ID))");
        db.execSQL("CREATE TABLE Activity (Activity_ID integer PRIMARY KEY AUTOINCREMENT, Activity_Name Text, Description Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS User");
        db.execSQL("DROP TABLE IF EXISTS Event_Category");
        db.execSQL("DROP TABLE IF EXISTS Event");
        db.execSQL("DROP TABLE IF EXISTS Interested");
        db.execSQL("DROP TABLE IF EXISTS Registered");
        db.execSQL("DROP TABLE IF EXISTS Activity");
        onCreate(db);
    }

//User Account Start

    public boolean registerNewUser(String Username, String Password, String Email, Integer Avatar){ //returns true if created, returns false if not
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("Username",Username);
        contentValue.put("Password",Password);
        contentValue.put("Email",Email);
        contentValue.put("Avatar",Avatar);

        long result = db.insert("User", null, contentValue);

        if(result == -1) {
            db.close();
            return false;
        }
        else{
            db.close();
            return true;
        }
    }

	


    public int checkUsername(String Username){          //returns 0 if Username doesnt exist, returns 1 if it does
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from User Where Username = '"+ Username +"'";
        Cursor c = db.rawQuery(query,null);
        if (c.getCount() <= 0) {
            c.close();
            db.close();
            return 0;
        }
        else{
            c.close();
            db.close();
            return 1;
        }
    } //end

    public int checkEmail(String Email){          //returns 0 if Email doesnt exist, returns 1 if it does
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from User Where Email = '"+ Email +"'";
        Cursor c = db.rawQuery(query,null);
        if (c.getCount() <= 0) {
            c.close();
            db.close();
            return 0;
        }
        else{
            c.close();
            db.close();
            return 1;
        }
    } //end
	
	 public int checkUserIDByUsername(String Username){          //returns 0 if Username doesnt exist, returns UserID if it does
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from User Where Username = '"+ Username +"'";
        Cursor c = db.rawQuery(query,null);
        if (c.getCount() <= 0) {
            c.close();
            db.close();
            return 0;
        }
        else{
            int index = c.getColumnIndexOrThrow("UserID");
			int userID = c.getInt(index);
			c.close();
			db.close();
            return userID;
        }
    } //end

    public int checkUserIDByEmail(String Email){          //returns 0 if Email doesnt exist, returns UserID if it does
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from User Where Email = '"+ Email +"'";
        Cursor c = db.rawQuery(query,null);
        if (c.getCount() <= 0) {
            c.close();
            db.close();
            return 0;
        }
        else{
			int index = c.getColumnIndexOrThrow("UserID");
			int userID = c.getInt(index);
            c.close();
            db.close();
            return userID;
        }
    } //end


    public int login(String Username, String Password){    // returns 0 if Username doesnt exist, returns 1 if Password matches,
        // returns 2 if Password doesnt match

        if(checkUsername(Username) == 0)
        {
            return 0;
        }
        else {
            SQLiteDatabase db = this.getWritableDatabase();
            String query =" Select * from User Where Username = '" + Username + "' AND Password = '"+Password +"'";
            Cursor c= db.rawQuery(query,null);
            if( c.getCount() <= 0 ){
                c.close();
                db.close();
                return 2;
            }

            else
            {
                c.close();
                db.close();
                return 1;
            }

        }

    }//end

    public boolean editUserInformation(String Username, String Password, String Email, int Avatar){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("Password", Password);
        contentValue.put("Email", Email);
        contentValue.put("Avatar", Avatar);
        db.update("User",contentValue, "Username = ?", new String[] {Username});
        return true;
    }//end
//User Account End

//Interested and Registered Start

    public int countInterested(int Event_ID){ // Count the number of people Interested
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from Interested Where Event_ID = "+ Event_ID;
        Cursor c = db.rawQuery(query,null);
        int count = c.getCount();
        c.close();
        db.close();
        return count;
    }//end

    public int countRegistered(int Event_ID){ //  Count the number of people Registered
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from Registered Where Event_ID = "+ Event_ID;
        Cursor c = db.rawQuery(query,null);
        int count = c.getCount();
        c.close();
        db.close();
        return count;
    }//end

    public boolean addInterested(int Event_ID, int User_ID){ // Add Event into Users interested list
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("Event_ID", Event_ID);
        contentValue.put("User_ID", User_ID);
        long result = db.insert("Interested", null, contentValue);

        if(result == -1) {
            db.close();
            return false;
        }
        else{
            db.close();
            return true;
        }

    }//end

    public boolean addRegistered(int Event_ID, int User_ID){// Add Event into Users Registered list
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("Event_ID", Event_ID);
        contentValue.put("User_ID", User_ID);
        long result = db.insert("Registered", null, contentValue);

        if(result == -1) {
            db.close();
            return false;
        }
        else{
            db.close();
            return true;
        }

    }//end

    public boolean removeInterested(int Event_ID, int User_ID){ // Remove Event into Users interested list
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Delete from Interested Where Event_ID = " + Event_ID + " AND User_ID = " + User_ID;
        db.execSQL(query);
        db.close();
        return true;
    }//end

    public boolean removeRegistered(int Event_ID, int User_ID){ // Remove Event into Users Registered list
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Delete from Registered Where Event_ID = " + Event_ID + " AND User_ID = " + User_ID;
        db.execSQL(query);
        db.close();
        return true;
    }//end

    public Cursor viewInterested(int Event_ID, int User_ID){ // View the list of Events that the User is Interested in
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select Event.* from Event Inner join Interested on Event.Event_ID = Interested.Event_ID Where Interested.User_ID = " + User_ID;
        Cursor c = db.rawQuery(query,null);
        db.close();
        return c;
    }//end

    public Cursor viewRegistered(int Event_ID, int User_ID){// View the list of Events that the User is Registered for
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select Event.* from Event Inner join Registered on Event.Event_ID = Registered.Event_ID Where Registered.User_ID =" + User_ID;
        Cursor c = db.rawQuery(query,null);
        db.close();
        return c;
    }//end


//Interested and Registered End


    //Event Start
/// Event Photo confirmation
    public boolean createEvent(String Event_Name, String Event_Description, int Event_Category_ID, String Event_Location, int Number_Of_People, String Event_Duration, int Event_Picture, int User_ID){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("Event_Name", Event_Name);
        contentValue.put("Event_Description", Event_Description);
        contentValue.put("Event_Category_ID", Event_Category_ID);
        contentValue.put("Event_Location", Event_Location);
        contentValue.put("Number_Of_People", Number_Of_People);
        contentValue.put("Event_Duration", Event_Duration);
        contentValue.put("Event_Picture", Event_Picture);
        contentValue.put("Event_Approval_Status", "Pending");
        contentValue.put("User_ID", User_ID);
        long result = db.insert("Event", null, contentValue);

        if(result == -1) {
            db.close();
            return false;
        }
        else{
            db.close();
            return true;
        }
    }//end

    public Cursor viewEvent(int Event_ID){ // View Event Details
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from Event Where Event_ID = " + Event_ID;
        Cursor c = db.rawQuery(query,null);
        db.close();
        return c;
    }//end

    public Cursor viewMyEvent(int User_ID){ // View the list of Events created by the User
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from Event Where User_ID = " + User_ID;
        Cursor c = db.rawQuery(query, null);
        db.close();
        return c;

    }//end

    public boolean editEvent(int Event_ID,String Event_Name, String Event_Description, int Event_Category_ID, String Event_Location, int Number_Of_People, String Event_Duration, int Event_Picture, int User_ID) { // Edit Event
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("Event_Name", Event_Name);
        contentValue.put("Event_Description", Event_Description);
        contentValue.put("Event_Category_ID", Event_Category_ID);
        contentValue.put("Event_Location", Event_Location);
        contentValue.put("Number_Of_People", Number_Of_People);
        contentValue.put("Event_Duration", Event_Duration);
        contentValue.put("Event_Picture", Event_Picture);
        contentValue.put("User_ID", User_ID);
        db.update("Event",contentValue, "Where Event_ID = " +Event_ID,null );
        db.close();
        return true;
    }//end

    public Cursor viewApprovedEvents(){ // View the list of Approved Events
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from Event Where Event_Approval_Status = 'Approved'";
        Cursor c = db.rawQuery(query, null);
        db.close();
        return c;

    }//end

    public Cursor viewPendingEvents(){ // View the list of Pending Events
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from Event Where Event_Approval_Status = 'Pending'";
        Cursor c = db.rawQuery(query, null);
        db.close();
        return c;

    }//end

    public boolean removeEvent(int Event_ID){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Delete from Event Where Event_ID = " + Event_ID;
        db.execSQL(query);
        db.close();
        return true;
    }//end

    public boolean approveEvent(int Event_ID){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("Event_Approval_Status","Approved");
        db.update("Event",contentValue,"Where Event_ID = "+ Event_ID, null);
        db.close();
        return true;
    }//end

    public boolean rejectEvent(int Event_ID){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("Event_Approval_Status","Reject");
        db.update("Event",contentValue,"Where Event_ID = "+ Event_ID, null);
        db.close();
        return true;
    }//end


    public Cursor retrieveEvents(){ // Retrieve Events which the number of people Registered has not exceed the number of people allowed
        // If there isnt any it will return a null Cursor
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from Event";
        Cursor c = db.rawQuery(query,null); // Retrieve the entire Event List
        c.moveToFirst();
        Cursor n = null;
        int[] ListOfEventID = new int[Integer.MAX_VALUE];
        int count = 0;
        while(c.moveToNext()){
            int index;

            index = c.getColumnIndexOrThrow("Number_Of_People");
            int check = c.getInt(index);
            index = c.getColumnIndexOrThrow("Event_ID");
            int event_ID = c.getInt(index);
            index = c.getColumnIndexOrThrow("Event_Approval_Status");
            String Approval =  c.getString(index);

            int NumOfPeople = countRegistered(event_ID);
            if(check > NumOfPeople && Approval == "Approved"){  // Check if the number of people Registered has exceeded the number of people allowed
                // Also check if Event has been approved

                ListOfEventID[count] = event_ID;    // If it hasnt, add the Event ID into the approved list
                count++;
            }

        }
        if(ListOfEventID.length > 0){ //If there is 1 event
            String query2 = "Select * from Event where Event_ID = "; // Modifying the Select SQL statement to add the entire list of events allowed


            for (int i = 0; i < ListOfEventID.length; i++) {

                query2 += ListOfEventID[i]; // adds the Event ID to the where clause

                if( i != ListOfEventID.length-1) // Checks if this is the last event
                {
                    query2 += "OR Event_ID = "; // If it isnt the last event, add another Where clause
                }
            }
            n = db.rawQuery(query2,null);
            db.close();
            return n;
        }
        else {
            db.close();
            return n;
        }
    }// End of Retrieve Events

    public Cursor retrieveFilteredEvents(int Event_Category_ID){ // Retrieve filtered Events base on Category
        // If there isnt any it will return a null Cursor
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from Event";
        Cursor c = db.rawQuery(query,null); // Retrieve the entire Event List
        c.moveToFirst();
        Cursor n = null;
        int[] ListOfEventID = new int[Integer.MAX_VALUE];
        int count = 0;
        while(c.moveToNext()){
            int index;

            index = c.getColumnIndexOrThrow("Number_Of_People");
            int check = c.getInt(index);
            index = c.getColumnIndexOrThrow("Event_ID");
            int event_ID = c.getInt(index);
            index = c.getColumnIndexOrThrow("Event_Category_ID");
            int Category_ID = c.getInt(index);
            index = c.getColumnIndexOrThrow("Event_Approval_Status");
            String Approval =  c.getString(index);

            int NumOfPeople = countRegistered(event_Id);
            if(check > NumOfPeople && Category_ID == Event_Category_ID && Approval == "Approved"){  // Check if the number of people Registered has exceeded the number of people allowed
                // Also check if Category ID matches with the given Event Category ID
                // Also check if Event has been approved

                ListOfEventID[count] = event_ID;    // Add the Event ID into the approved list
                count++;
            }

        }
        if(ListOfEventID.length > 0){ //If there is more than 1 event
            String query2 = "Select * from Event where Event_ID = "; // Modifying the Select SQL statement to add the entire list of events allowed


            for (int i = 0; i < ListOfEventID.length; i++) {

                query2 += ListOfEventID[i]; // adds the Event ID to the where clause

                if( i != ListOfEventID.length-1) // Checks if this is the last event
                {
                    query2 += "OR Event_ID = "; // If it isnt the last event, add another Where clause
                }
            }
            n = db.rawQuery(query2,null);

            db.close();
            return n;
        }
        else {
            db.close();
            return n;
        }
    }// End of Retrieve Filtered Events

    public Cursor viewPopularEvents(int Event_Category_ID){ // Retrieve Events that is sorted base on Popularity
        // If there isnt any it will return a null Cursor
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from Event";
        Cursor c = db.rawQuery(query,null); // Retrieve the entire Event List
        c.moveToFirst();
        Cursor n = null;
        int[] ListOfEventID = new int[Integer.MAX_VALUE];
        int count = 0;
        while(c.moveToNext()){
            int index;

            index = c.getColumnIndexOrThrow("Number_Of_People");
            int check = c.getInt(index);
            index = c.getColumnIndexOrThrow("Event_ID");
            int event_ID = c.getInt(index);
            index = c.getColumnIndexOrThrow("Event_Approval_Status");
            String Approval =  c.getString(index);

            int NumOfPeople = countRegistered(event_ID);
            if(check > NumOfPeople && Approval == "Approved"){  // Check if the number of people Registered has exceeded the number of people allowed
                // Also check if Event has been approved

                ListOfEventID[count] = event_ID;    // Add the Event ID into the approved list
                count++;
            }

        }
        if(ListOfEventID.length > 0){ //If there is more than 1 event
            // Modifying the Select SQL statement to add the entire list of events allowed
            String query2 = "Select Event.*,Count(Event.Event_ID) from Event Inner join Registered on Event.Event_ID = Registered.Event_ID WHERE EVENT.EVENT_ID = ";



            for (int i = 0; i < ListOfEventID.length; i++) {

                query2 += ListOfEventID[i]; // adds the Event ID to the where clause

                if( i != ListOfEventID.length-1) // Checks if this is the last event
                {
                    query2 += "OR Event.Event_ID = "; // If it isnt the last event, add another Where clause
                }
            }

            query2 += " Group By Event.Event_ID Order by Count(Event.Event_ID) desc "; // Grouping the Events and sorting out in order base on the most popular events
            n = db.rawQuery(query2,null);

            db.close();
            return n;
        }
        else {
            db.close();
            return n;
        }
    }// End of View Popular Events

    // End of Event
// Start of Event Category
    public Cursor viewEventCategory(){ // Retrieve the list of Event Category for the User to select

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from Event_Category";
        Cursor c = db.rawQuery(query, null);
        db.close();
        return c;

    }//end

    public Cursor viewEventCategory(int Event_Category_ID){ // Retrieve the list of Event Category for the User to select

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from Event_Category where Event_Category_ID = " + Event_Category_ID;
        Cursor c = db.rawQuery(query, null);
        db.close();
        return c;

    }//end

    public boolean createEventCategory(String Event_Category_Name,String Event_Category_Description){ // Creating an Event Category
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("Event_Category_Name",Event_Category_Name);
        contentValue.put("Event_Category_Description",Event_Category_Description);


        long result = db.insert("Event_Category", null, contentValue);

        if(result == -1) {
            db.close();
            return false;
        }
        else{
            db.close();
            return true;
        }

    }//end
    // End of Event Category
// Start of Activity
    public Cursor viewActivities(){ // Retrieve the list of Activities

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from Activity";
        Cursor c = db.rawQuery(query, null);
        db.close();
        return c;

    }//end

    public boolean createActivity(String Activity_Name,String Description){ // Creating an Activity
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("Activity_Name",Activity_Name);
        contentValue.put("Description",Description);


        long result = db.insert("Activity", null, contentValue);

        if(result == -1) {
            db.close();
            return false;
        }
        else{
            db.close();
            return true;
        }

    }//end
// End of Activity
}

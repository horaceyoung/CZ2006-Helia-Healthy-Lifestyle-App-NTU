package com.zy.helia.Interface_classes;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public interface DatabaseInterface {

     void onCreate(SQLiteDatabase db) ;

     void onUpgrade(SQLiteDatabase db, int i, int i1);

//User Account Start

     boolean registerNewUser(String Username, String Password, String Email, Integer Avatar);




     int checkUsername(String Username);

     int checkEmail(String Email);

     int checkUserIDByUsername(String Username);

     int checkUserIDByEmail(String Email);




     int login(String Username, String Password);

     boolean editUserInformation(String Username, String Password, String Email, int Avatar);
//User Account End

//Interested and Registered Start

     int countInterested(int Event_ID);

     int countRegistered(int Event_ID);

     boolean addInterested(int Event_ID, int User_ID);

     boolean addRegistered(int Event_ID, int User_ID);

     boolean removeInterested(int Event_ID, int User_ID);

     boolean removeRegistered(int Event_ID, int User_ID);

     Cursor viewInterested(int User_ID);

    //Deleted parameter int Event_ID - Yanxi
     Cursor viewRegistered(int User_ID);

     boolean isRegistered(int Event_ID, int User_ID);

     boolean isInterested(int Event_ID, int User_ID);


//Interested and Registered End


    //Event Start
/// Event Photo confirmation
     boolean createEvent(String Event_Name, String Event_Description, int Event_Category_ID, String Event_Location, int Number_Of_People, String Event_Duration, int Event_Picture, int User_ID);

     Cursor viewEvent(int Event_ID);

     Cursor viewMyEvent(int User_ID);

     boolean editEvent(int Event_ID,String Event_Name, String Event_Description, int Event_Category_ID, String Event_Location, int Number_Of_People, String Event_Duration, int Event_Picture, int User_ID);

     Cursor viewApprovedEvents();

     Cursor viewPendingEvents(SQLiteDatabase db);

     boolean removeEvent(int Event_ID);

     boolean approveEvent(int Event_ID);

     boolean rejectEvent(int Event_ID);


     Cursor retrieveEvents(SQLiteDatabase db);// End of Retrieve Events

     Cursor retrieveFilteredEvents(SQLiteDatabase db, int Event_Category_ID);// End of View Popular Events

    // End of Event
// Start of Event Category
     Cursor viewEventCategory();

     Cursor viewEventCategory(int Event_Category_ID);

    boolean createEventCategory(String Event_Category_Name,String Event_Category_Description);
    // End of Event Category
// Start of Activity
    Cursor viewActivities();//end




    }//end


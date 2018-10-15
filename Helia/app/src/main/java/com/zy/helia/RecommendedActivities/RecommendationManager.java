package com.zy.helia.RecommendedActivities;

import android.util.Log;
import android.widget.Toast;

import org.json.simple.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.net.*;
public class RecommendationManager {
    private static final String TAG = "RecommendationManager";
    private ArrayList<RecommendedActivities> activitiesList = new ArrayList<RecommendedActivities>();
    private URL urlUVLight = null;
    private float UVLightIndex = 0;
    public RecommendationManager(){
        CreateActivities();
    }
    private void CreateActivities(){
        activitiesList.add(new RecommendedActivities("Jogging", true, 35, 2, 8, 100));
        activitiesList.add(new RecommendedActivities("Swimming", true, 35, 2, 8, 100));
        activitiesList.add(new RecommendedActivities("Single Tennis", true, 35, 2, 8, 100));
        activitiesList.add(new RecommendedActivities("Basketball", true, 35, 2, 8, 100));
        activitiesList.add(new RecommendedActivities("Soccer", true, 35, 2, 8, 100));
        activitiesList.add(new RecommendedActivities("Walking", true, 35, 2, 8, 100));
        activitiesList.add(new RecommendedActivities("Cycling", true, 35, 2, 8, 100));
        activitiesList.add(new RecommendedActivities("Running", false, 35, 2, 8, 100));
        activitiesList.add(new RecommendedActivities("Yoga", false, 35, 2, 8, 100));
        activitiesList.add(new RecommendedActivities("Jump Rope", false, 35, 2, 8, 100));
        activitiesList.add(new RecommendedActivities("Cardio Workout", false, 35, 2, 8, 100));
    }

    public void AcquireUVlight(){
        JSONObject UVLightReturned;


    }


}


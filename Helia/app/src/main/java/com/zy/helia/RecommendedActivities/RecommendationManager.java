package com.zy.helia.RecommendedActivities;

import android.content.Context;
import android.util.Log;
import android.view.textclassifier.TextLinks;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.simple.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.ToDoubleBiFunction;
import java.time.*;

public class RecommendationManager {
    private static final String TAG = "RecommendationManager";
    private ArrayList<RecommendedActivities> activitiesList = new ArrayList<RecommendedActivities>();
    //UV Light Attributes
    private URL urlUVLight = null;
    private float UVLightIndex = 0;

    private Context context;
    public RecommendationManager(Context context){
        CreateActivities();
        this.context = context;
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
        String responseUVlight;

        RequestQueue UVLightRequestQueue = Volley.newRequestQueue(context);
        String UVLightUrl = "https:api.data.gov.sg/v1/environment/uv-index";
        StringRequest UVLightRequest = new StringRequest(Request.Method.GET, UVLightUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(context, response.toString(), Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
            }
        }){
            /**
             * Returns a Map of parameters to be used for a POST or PUT request. Can throw {@link
             * AuthFailureError} as authentication may be required to provide these values.
             * <p>
             * <p>Note that you can directly override {@link #getBody()} for custom data.
             *
             * @throws AuthFailureError in the event of auth failure
             */
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> paraMap = new HashMap<>();
                paraMap.put("data_time", "2018-10-21T19:28:30");
                return paraMap;
            }
        };

        UVLightRequestQueue.add(UVLightRequest);

    }


}


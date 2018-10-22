package com.zy.helia.RecommendedActivities;
import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.util.Log;
import android.view.textclassifier.TextLinks;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.*;

import java.util.Calendar;
import java.text.SimpleDateFormat;

import java.io.InputStream;
import java.util.ArrayList;
import java.net.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.ToDoubleBiFunction;
import java.time.*;
import com.zy.helia.R;

public class RecommendationManager {
    private static final String TAG = "RecommendationManager";
    private ArrayList<RecommendedActivities> activitiesList = new ArrayList<RecommendedActivities>();
    //UV Light Attributes
    private URL urlTemperature = null;
    private float UVLightIndex = 0;
    private Random rand = new Random();

    private Context context;

    public RecommendationManager(Context context){
        CreateActivities();
        this.context = context;
    }
    private void CreateActivities(){
        activitiesList.add(new RecommendedActivities("Jogging", true, 35, 2, 8, 100, R.drawable.jog));
        activitiesList.add(new RecommendedActivities("Swimming", true, 35, 2, 8, 100,R.drawable.swim));
        activitiesList.add(new RecommendedActivities("Single Tennis", true, 35, 2, 8, 100,R.drawable.tennis));
        activitiesList.add(new RecommendedActivities("Basketball", true, 35, 2, 8, 100,R.drawable.basketball));
        activitiesList.add(new RecommendedActivities("Soccer", true, 35, 2, 8, 100,R.drawable.soccer));
        activitiesList.add(new RecommendedActivities("Walking", true, 35, 2, 8, 100,R.drawable.walk));
        activitiesList.add(new RecommendedActivities("Cycling", true, 35, 2, 8, 100,R.drawable.cycle));
        activitiesList.add(new RecommendedActivities("Running", false, 35, 2, 8, 100, R.drawable.running));
        activitiesList.add(new RecommendedActivities("Yoga", false, 35, 2, 8, 100, R.drawable.yoga));
        activitiesList.add(new RecommendedActivities("Jump Rope", false, 35, 2, 8, 100,R.drawable.rope_jumping));
        activitiesList.add(new RecommendedActivities("Cardio Workout", false, 35, 2, 8, 100,R.drawable.cardio_workout));
    }

    public RecommendedActivities GetRandomAvailableActivity(float temperature, float UVLightIndex, float PSI){
        int activityIndex;
        boolean outdoor = true;
        if(temperature>=35||UVLightIndex>=8||PSI>=100){
            outdoor = false;
        }

        if(!outdoor){
            activityIndex = rand.nextInt(7)+4;
        }
        else{
            activityIndex = rand.nextInt(activitiesList.size());
        }
        return activitiesList.get(activityIndex);
    }

    public interface VolleyCallback{
        void onSuccess(String result);
    }

    private void RequestUVlight(final VolleyCallback callback){
        RequestQueue UVLightRequestQueue = Volley.newRequestQueue(context);
        String UVLightUrl = "https:api.data.gov.sg/v1/environment/uv-index";
        StringRequest UVLightRequest = new StringRequest(Request.Method.GET, UVLightUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> paraMap = new HashMap<>();
                String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
                String currentTime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
                String currentDateTime = currentDate + 'T' + currentTime;
                paraMap.put("data_time", currentDateTime);
                return paraMap;
            }
        };

        UVLightRequestQueue.add(UVLightRequest);

    }

    public void AcquireUVLight(final TextView UVLightIndexText, final TextView UVLightStatusText){
        RequestUVlight(new VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                try{
                    JSONObject UVLightIndexJSON = new JSONObject(result);
                    String UVLightIndex = UVLightIndexJSON.getJSONArray("items").getJSONObject(0).getJSONArray("index").getJSONObject(0).getString("value");
                    String UVLightStatus = UVLightIndexJSON.getJSONObject("api_info").getString("status");
                    UVLightIndexText.setText(UVLightIndex);
                    UVLightStatusText.setText(UVLightStatus);
                }
                catch (JSONException e){
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void RequestTemperature(final VolleyCallback callback){
        RequestQueue PSIRequestQueue = Volley.newRequestQueue(context);
        String PSIUrl = "https://api.data.gov.sg/v1/environment/air-temperature";
        StringRequest PSIRequest = new StringRequest(Request.Method.GET, PSIUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> paraMap = new HashMap<>();
                String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
                String currentTime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
                String currentDateTime = currentDate + 'T' + currentTime;
                paraMap.put("data_time", currentDateTime);
                return paraMap;
            }
        };

        PSIRequestQueue.add(PSIRequest);

    }

    public void AcquireTemperature(final TextView TemperatureText, final TextView TemperatureStatusText){
        RequestTemperature(new VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                try{
                    JSONObject temperatureJSON = new JSONObject(result);
                    String currentTemperature = temperatureJSON.getJSONArray("items").getJSONObject(0).getJSONArray("readings").getJSONObject(0).getString("value");
                    String temperatureStatus = temperatureJSON.getJSONObject("api_info").getString("status");
                    TemperatureText.setText(currentTemperature);
                    TemperatureStatusText.setText(temperatureStatus);

                }
                catch (JSONException e){
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void RequestPSI(final VolleyCallback callback){
        RequestQueue PSIRequestQueue = Volley.newRequestQueue(context);
        String PSIUrl = "https://api.data.gov.sg/v1/environment/psi";
        StringRequest PSIRequest = new StringRequest(Request.Method.GET, PSIUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> paraMap = new HashMap<>();
                String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
                String currentTime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
                String currentDateTime = currentDate + 'T' + currentTime;
                paraMap.put("data_time", currentDateTime);
                return paraMap;
            }
        };

        PSIRequestQueue.add(PSIRequest);

    }

    public void AcquirePSI(final TextView PSIText, final TextView PSIStatusText){
        RequestPSI(new VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                try{
                    JSONObject PSIJSON = new JSONObject(result);
                    String currentPSI = PSIJSON.getJSONArray("items").getJSONObject(0).getJSONObject("readings").getJSONObject("psi_twenty_four_hourly").getString("national");
                    String PSIStatus = PSIJSON.getJSONObject("api_info").getString("status");
                    PSIText.setText(currentPSI);
                    PSIStatusText.setText(PSIStatus);

                }
                catch (JSONException e){
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });
    }


}


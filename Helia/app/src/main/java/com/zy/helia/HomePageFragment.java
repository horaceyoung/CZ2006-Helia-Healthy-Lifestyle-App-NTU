package com.zy.helia;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zy.helia.Activities.HealthierEatActivity;
import com.zy.helia.RecommendedActivities.RecommendationManager;
import com.zy.helia.RecommendedActivities.RecommendedActivities;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomePageFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomePageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomePageFragment extends Fragment implements View.OnClickListener {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private Button home_btnHealthierEateries;
    private ImageView swimming;

    private RecommendationManager ma;

    private TextView recommendationText;
    private TextView UVLightIndexText;
    private TextView UVLightStatusText;
    private TextView temperatureText;
    private TextView temperatureStatusText;
    private TextView PSIText;
    private TextView PSIStausText;

    private ImageView activityImage;

    private RecommendationManager recommendationManager;


    public HomePageFragment() {
        // Required empty public constructor
    }
    public static HomePageFragment newInstance(String param1, String param2) {
        HomePageFragment fragment = new HomePageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }





    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_page, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        recommendationManager = new RecommendationManager(getContext());


        home_btnHealthierEateries = getView().findViewById(R.id.button_healthier_eateries);
        recommendationText = getView().findViewById(R.id.recommend);
        UVLightIndexText  = getView().findViewById(R.id.UVLightIndex);
        UVLightStatusText = getView().findViewById(R.id.UVLightStatus);
        temperatureText = getView().findViewById(R.id.temperatureText);
        temperatureStatusText = getView().findViewById(R.id.temperatureStatus);
        PSIText = getView().findViewById(R.id.PSIText);
        PSIStausText = getView().findViewById(R.id.PSIStatus);

        activityImage = getView().findViewById(R.id.activityImage);
        activityImage.setImageResource(recommendationManager.GetRandomAvailableActivity(Float.valueOf(temperatureText.getText().toString().trim()),
                Float.valueOf(UVLightIndexText.getText().toString().trim()), Float.valueOf(PSIText.getText().toString().trim())).getImageReference());

        UpdateEnvrionment(recommendationManager);

        home_btnHealthierEateries.setOnClickListener(this);
        swimming = getView().findViewById(R.id.activityImage);
        swimming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateEnvrionment(recommendationManager);
                RecommendedActivities getActivity = recommendationManager.GetRandomAvailableActivity(Float.valueOf(temperatureText.getText().toString().trim()),
                        Float.valueOf(UVLightIndexText.getText().toString().trim()), Float.valueOf(PSIText.getText().toString().trim()));
                activityImage.setImageResource(getActivity.getImageReference());
                recommendationText.setText("Today is a fine day for " + getActivity.getName());
            }
        });
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        Intent healthierEateriesIntent = new Intent(getContext(), HealthierEatActivity.class);
        startActivity(healthierEateriesIntent);
    }

    private void UpdateEnvrionment(RecommendationManager ma){
        ma.AcquireUVLight(UVLightIndexText, UVLightStatusText);
        ma.AcquireTemperature(temperatureText, temperatureStatusText);
        ma.AcquirePSI(PSIText, PSIStausText);
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

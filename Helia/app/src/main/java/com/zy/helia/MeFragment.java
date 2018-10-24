package com.zy.helia;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;
import com.zy.helia.Activities.LoginActivity;
import com.zy.helia.Activities.MainActivity;
import com.zy.helia.Activities.MeMyEvents;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MeFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public Button Personal;
    public Button MyEvents;
    public Button IREvents;
    public Button LogOut;
    private TextView userid;
    private TextView userEmail;
    private OnFragmentInteractionListener mListener;
    private ImageView Avatar;
    public MeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MeFragment newInstance(String param1, String param2) {
        MeFragment fragment = new MeFragment();
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

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle args) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        Personal = view.findViewById(R.id.Personal);
        Personal.setOnClickListener(this);

        LogOut = view.findViewById(R.id.LogOut);
        LogOut.setOnClickListener(this);
        IREvents =  view.findViewById(R.id.IREvents);
        IREvents.setOnClickListener(this);
        MyEvents = view.findViewById(R.id.MyEvents);
        MyEvents.setOnClickListener(this);
        Avatar=view.findViewById(R.id.UserDp);
        switch (LoginActivity.getAvatarChoice()){
            case 1:
                Avatar.setImageResource(R.drawable.m01);
                break;
            case 2:
                Avatar.setImageResource(R.drawable.m02);
                break;
            case 3:
                Avatar.setImageResource(R.drawable.f01);
                break;
            case 4:
                Avatar.setImageResource(R.drawable.f02);
                break;
        }

        userid = view.findViewById(R.id.UserId);
        userid.setText(LoginActivity.getUsername());
        userEmail = view.findViewById(R.id.UserEmail);
        userEmail.setText(LoginActivity.getEmail());


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.Personal:
                Intent startNewActivity = new Intent(getContext(),MeChangePersonalInfo.class);
                startActivity(startNewActivity);
                break;

            case R.id.IREvents:
                Intent startNewActivity2 = new Intent(getContext(),MeIREvents.class);
                startActivity(startNewActivity2);
                break;

            case R.id.MyEvents:
                Intent startNewActivity3 = new Intent(getContext(),MeMyEvents.class);
                startActivity(startNewActivity3);
                break;

            case R.id.LogOut:
                Intent startNewActivity4 = new Intent(getContext(),LoginActivity.class);
                startActivity(startNewActivity4);
                break;

            default:
                break;
        }


    }

    @Override
    public void onResume() {
/*        int id = getActivity().getIntent().getIntExtra("id", 0);
        if(id==2){
            MainActivity.mainViewPager.setCurrentItem(2);
        }*/
        super.onResume();
    }


}


package com.zy.helia;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zy.helia.Activities.*;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EventFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EventFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private Button mostPopular;
    private Button create;

    private RecyclerView mRecyclerView_Type;
    private RecyclerView.Adapter mAdapter_Type;
    private RecyclerView.LayoutManager mLayoutManager_Type;
    private RecyclerView mRecyclerView_Event;
    private RecyclerView.Adapter mAdapter_Event;
    private RecyclerView.LayoutManager mLayoutManager_Event;

    public EventFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EventFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EventFragment newInstance(String param1, String param2) {
        EventFragment fragment = new EventFragment();
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
        return inflater.inflate(R.layout.fragment_event, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mRecyclerView_Event.setAdapter(mAdapter_Event);
        mRecyclerView_Type.setLayoutManager((mLayoutManager_Type));
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        mRecyclerView_Event = (RecyclerView) getView().findViewById(R.id.eventrankingRV);
        mRecyclerView_Event.setHasFixedSize(true);
        mLayoutManager_Event = new LinearLayoutManager(getContext());
        mRecyclerView_Event.setLayoutManager((mLayoutManager_Event));

        mAdapter_Event = new EventFragmentAdapter(getContext());
        mRecyclerView_Event.setAdapter(mAdapter_Event);

        mRecyclerView_Type = (RecyclerView) getView().findViewById(R.id.typerankingRV);
        mRecyclerView_Type.setHasFixedSize(true);
        mLayoutManager_Type = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView_Type.setLayoutManager((mLayoutManager_Type));

        mAdapter_Type = new EventFragmentType();
        mRecyclerView_Type.setAdapter(mAdapter_Type);

        mostPopular = getView().findViewById(R.id.mostPopular);
        mostPopular.setOnClickListener(this);
//        more = getView().findViewById(R.id.more);
//        more.setOnClickListener(this);
        create = getView().findViewById(R.id.create);
        create.setOnClickListener(this);
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.mostPopular:
                Intent startNewActivity = new Intent(getContext(),EventDetail.class);
                startActivity(startNewActivity);
                break;

            case R.id.create:
                Intent startNewActivity3 = new Intent(getContext(),CreateEvent.class);
                startActivity(startNewActivity3);
                break;

            default:
                break;
        }
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
}

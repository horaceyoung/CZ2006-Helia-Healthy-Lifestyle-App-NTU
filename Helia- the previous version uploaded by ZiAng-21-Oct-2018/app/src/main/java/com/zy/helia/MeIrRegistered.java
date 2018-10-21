package com.zy.helia;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zy.helia.Activities.LoginActivity;
import com.zy.helia.Activities.MeInterestedEventListAdapter;
import com.zy.helia.Activities.MeRegisteredEventListAdapter;
import com.zy.helia.Event_Data.DatabaseHelp;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MeIrRegistered.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MeIrRegistered#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MeIrRegistered extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<String> EventName = new ArrayList<>();
    private List<Integer> EventID = new ArrayList<Integer>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MeIrRegistered() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MeIrRegistered.
     */
    // TODO: Rename and change types and number of parameters
    public static MeIrRegistered newInstance(String param1, String param2) {
        MeIrRegistered fragment = new MeIrRegistered();
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


        DatabaseHelp dbHelper = new DatabaseHelp(getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor eventCursor = dbHelper.viewRegistered(LoginActivity.getUserID());


        Log.d("EventActivity", "This is called" );
        while (eventCursor.moveToNext())
        {
            int eventIndex = eventCursor.getColumnIndex("Event_Name");
            String eventName = eventCursor.getString(eventIndex);
            Log.d("EventActivity", "Event name 1233" +eventName);
            EventName.add(eventName);

            int IDIndex = eventCursor.getColumnIndex("Event_ID");
            int eventID = eventCursor.getInt(IDIndex);
            EventID.add(eventID);
        }
        db.close();
        //newly added section
        View view = inflater.inflate(R.layout.fragment_me__ir__registered, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.RegisteredEventListRV);

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager((mLayoutManager));

        mAdapter = new MeRegisteredEventListAdapter(getContext(),EventName, EventID);
        mRecyclerView.setAdapter(mAdapter);
        //end of newly added section

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
}
package com.zy.helia;

import android.content.Intent;
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
import android.widget.Button;
import android.widget.ImageButton;

import com.zy.helia.Activities.*;
import com.zy.helia.Event_Data.DatabaseHelp;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

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

    private ImageButton mostPopular;
    private Button create;
    private Button more;

    private RecyclerView mRecyclerView_Type;
    private RecyclerView.Adapter mAdapter_Type;
    private RecyclerView.LayoutManager mLayoutManager_Type;
    private RecyclerView mRecyclerView_Event;
    private RecyclerView.Adapter mAdapter_Event;
    private RecyclerView.LayoutManager mLayoutManager_Event;

    private ArrayList<String> EventName = new ArrayList<>();
    private List<Integer> EventID = new ArrayList<Integer>();

    private String popularName;
    private int popularID;

    private DatabaseHelp dbHelper;
    private SQLiteDatabase db;

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
        dbHelper = new DatabaseHelp(getContext());
        db = dbHelper.getReadableDatabase();
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
            //mRecyclerView_Type.setLayoutManager((mLayoutManager_Type));

            // Get all pending events
            Cursor eventCursor = dbHelper.retrieveEvents(db);

            EventName.clear();
            EventID.clear();

            if (eventCursor != null) {
                while (eventCursor.moveToNext()) {
                    int eventIndex = eventCursor.getColumnIndex("Event_Name");
                    String eventName = eventCursor.getString(eventIndex);
                    Log.d("Event_Name", eventName);
                    EventName.add(eventName);

                    int IDIndex = eventCursor.getColumnIndex("Event_ID");
                    int eventID = eventCursor.getInt(IDIndex);
                    EventID.add(eventID);
                }
            }
            db.close();

            if (!EventName.isEmpty()) {
                popularName = EventName.get(0);
                popularID = EventID.get(0);
                mostPopular.setOnClickListener(this);
            }

            for (int i = 0; i < EventName.size(); i++)
                Log.d("Event_Name", EventName.get(i));

            mAdapter_Event = new EventFragmentAdapter(getActivity().getBaseContext(), EventName, EventID);
            mRecyclerView_Event.setAdapter(mAdapter_Event);

        // Block End
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        mRecyclerView_Event = (RecyclerView) getView().findViewById(R.id.eventrankingRV);
        mRecyclerView_Event.setHasFixedSize(true);
        mLayoutManager_Event = new LinearLayoutManager(getContext());
        mRecyclerView_Event.setLayoutManager((mLayoutManager_Event));

//        mRecyclerView_Type = (RecyclerView) getView().findViewById(R.id.typerankingRV);
//        mRecyclerView_Type.setHasFixedSize(true);
//        mLayoutManager_Type = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
//        mRecyclerView_Type.setLayoutManager((mLayoutManager_Type));
//
//        mAdapter_Type = new EventFragmentType();
//        mRecyclerView_Type.setAdapter(mAdapter_Type);

        mostPopular = getView().findViewById(R.id.imageButton2);

        create = getView().findViewById(R.id.create);
        create.setOnClickListener(this);

        more = getView().findViewById(R.id.more);
        more.setOnClickListener(this);
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.imageButton2:
                Intent startNewActivity = new Intent(getContext(),EventDetail.class);
                startNewActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startNewActivity.putExtra("EventID", Integer.toString(popularID));
                startActivity(startNewActivity);
                break;

            case R.id.create:
                Intent startNewActivity2 = new Intent(getContext(),CreateEvent.class);
                startActivity(startNewActivity2);
                break;

            case R.id.more:
                Intent startNewActivity3 = new Intent(getContext(),TypeList.class);
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

package com.example.stuti.taskstuti.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.stuti.taskstuti.R;

import butterknife.ButterKnife;

public class ListFragment extends Fragment {

    //XML variables.


    //Class variables
    private static final String TAG = ListFragment.class.getSimpleName();
    private Context context = null;
    private static final int OPEN_CHAT_ACTIVITY = 100;
    private static final String TYPE = "TYPE";
    private String userType = "";

    public ListFragment() {
    }

    public static ListFragment newInstance(String param) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putString(TYPE, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getActivity();

    }

    /**
     * Method used to create view of fragment.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);

        // return view.
        return view;
    }

}

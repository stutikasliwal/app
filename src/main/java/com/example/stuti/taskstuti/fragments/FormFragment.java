package com.example.stuti.taskstuti.fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.stuti.taskstuti.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FormFragment extends Fragment {

    //XML variables.


    //Class variables
    private static final String TAG = FormFragment.class.getSimpleName();
    private Context context = null;
    private static final int OPEN_CHAT_ACTIVITY = 100;
    private static final String TYPE = "TYPE";
    private String userType = "";

    public FormFragment() {
    }

    public static FormFragment newInstance(String param) {
        FormFragment fragment = new FormFragment();
        Bundle args = new Bundle();
        args.putString(TYPE, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userType = getArguments().getString(TYPE);
        }
        context = getActivity();

    }

    /**
     * Method used to create view of fragment.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_form, container, false);
        ButterKnife.bind(this, view);

        // return view.
        return view;
    }

}

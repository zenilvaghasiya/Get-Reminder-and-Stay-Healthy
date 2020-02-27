package com.universalgamestudio.getreminderandstayhealthy;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class AlarmFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private MoreFragment.OnFragmentInteractionListener mListener;

    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private String userId;
    private DatabaseReference mDatabase;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mDatabase = FirebaseDatabase.getInstance().getReference();

        LayoutInflater lf = getActivity().getLayoutInflater();
        View view1 = lf.inflate(R.layout.fragment_trace, container, false);
        Button btnSave = (Button) view1.findViewById(R.id.save);
        Button btnDelete=(Button)view1.findViewById(R.id.remove);
        final EditText inputDate=(EditText)view1.findViewById(R.id.in_date);
        final EditText inputTime =(EditText)view1.findViewById(R.id.in_time);
        final EditText inputName= (EditText)view1.findViewById(R.id.new_name);
        final EditText inputDescription =(EditText)view1.findViewById(R.id.new_description);





        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = inputName.getText().toString();
                String time = inputTime.getText().toString();
                String date= inputDate.getText().toString();
                String description= inputDescription.getText().toString();

                // Check for already existed userId
                    User user = new User(date,time,name,description);

                    mFirebaseDatabase.child(userId).setValue(user);

                
            }


        });


        return view1;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}


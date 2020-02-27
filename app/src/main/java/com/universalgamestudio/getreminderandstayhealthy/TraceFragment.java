package com.universalgamestudio.getreminderandstayhealthy;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;


public class TraceFragment extends Fragment {
    private static final String TAG = MainActivity.class.getSimpleName();

    private ListView lvMain;

    private FirebaseDatabase mFirebaseInstance;
    private OnFragmentInteractionListener mListener;
    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mGetReference = mDatabase.getReference();

    private ArrayList<SingleEntry> entries;

    public TraceFragment() {
        entries = new ArrayList<>();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view = lf.inflate(R.layout.fragment_trace, container, false);

        lvMain = view.findViewById(R.id.lvMain);
        lvMain.setAdapter(new MyAdapter(getActivity(), entries));

        mFirebaseInstance = FirebaseDatabase.getInstance();
        // mFirebaseInstance.getReference("Value").setValue("Causeway Live Status");

        mFirebaseInstance.getReference("Variable/Value").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e(TAG, "Firebase entry ");
                String boxstatus = null;
                String appTitle = dataSnapshot.getValue(String.class);
                if(appTitle.equals("0")){
                    boxstatus="Pill Box is Close";
                }
                else if(appTitle.equals("1")){
                    boxstatus="Pill Box is Open";
                }
                
                synchronized (this) {
                    String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
                    entries.add(new SingleEntry(boxstatus, mydate));
                    ((BaseAdapter) lvMain.getAdapter()).notifyDataSetChanged();
                }

                // update toolbar title

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read app title value.", error.toException());
            }
        });
        return view;
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}


class MyAdapter extends BaseAdapter {

    private ArrayList<SingleEntry> entries;
    private Context c;
    private LayoutInflater inflater;

    MyAdapter(Context c, ArrayList<SingleEntry> entries) {
        this.c = c;
        this.entries = entries;
        this.inflater = ((Activity) c).getLayoutInflater();
    }

    @Override
    public int getCount() {
        return entries.size();
    }

    @Override
    public Object getItem(int position) {
        return entries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView != null) {
            view = convertView;
        } else {
            view = inflater.inflate(R.layout.single_entry, parent, false);
        }

        TextView tvValue = view.findViewById(R.id.lvValue);
        tvValue.setText(entries.get(position).getValue());

        TextView tvDate = view.findViewById(R.id.lvDate);
        tvDate.setText(entries.get(position).getDate());

        return view;
    }
}
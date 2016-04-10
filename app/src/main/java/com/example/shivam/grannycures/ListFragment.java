package com.example.shivam.grannycures;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {

    ArrayList<String> myAilments = new ArrayList<>();

    Activity mActivity;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity =  activity;
    }

    AilmentAdapter myAdapter ;




    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        DatabaseHelper myHelper = new DatabaseHelper(mActivity);
        myAilments = myHelper.getAilmentName();
        myAdapter = new AilmentAdapter(mActivity,0,myAilments);
        ListView listView = (ListView) rootView.findViewById(R.id.ailments_list);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detailIntent = new Intent(mActivity, DetailActivity.class);
                detailIntent.putExtra("position", position);
                startActivity(detailIntent);
            }
        });

        return rootView;
    }




}

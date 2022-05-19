package com.exmple.savecontactsandmessages.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.exmple.savecontactsandmessages.ContactInformation;
import com.exmple.savecontactsandmessages.DatabaseHandler;
import com.exmple.savecontactsandmessages.R;
import com.exmple.savecontactsandmessages.RecyclerViewAdapter;

import java.util.ArrayList;

public class ViewContactFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_contact, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        com.exmple.savecontactsandmessages.DatabaseHandler db = new com.exmple.savecontactsandmessages.DatabaseHandler(getActivity());
        Cursor cursor = db.getData();

        ArrayList<ContactInformation> arrayList = new ArrayList<>();

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);


        if(cursor.getCount() == 0) {
            Toast.makeText(getActivity(),"No Record Found",Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                arrayList.add(new com.exmple.savecontactsandmessages.ContactInformation(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                        cursor.getString(4)));
            }
        }

        com.exmple.savecontactsandmessages.RecyclerViewAdapter recyclerViewAdapter = new com.exmple.savecontactsandmessages.RecyclerViewAdapter(arrayList,getActivity());
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
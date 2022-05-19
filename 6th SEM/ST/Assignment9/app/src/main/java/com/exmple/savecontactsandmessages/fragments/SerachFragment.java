package com.exmple.savecontactsandmessages.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

public class SerachFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_serach, container, false);
    }

    private EditText etQuery;
    private Button submitSearch;
    private RecyclerView listViewId;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etQuery = view.findViewById(R.id.etQuery);
        submitSearch = view.findViewById(R.id.searchButton);
        listViewId = view.findViewById(R.id.listViewId);

        submitSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = etQuery.getText().toString().trim();

                if (id.isEmpty()) {
                    Toast.makeText(getActivity(), "Enter valid input ", Toast.LENGTH_SHORT).show();
                    listViewId.setAdapter(null);
                    return;
                }

                DatabaseHandler db = new DatabaseHandler(getActivity());
                Cursor cursor = db.getDataById(id);
                if (cursor.getCount() == 0) {
                    Toast.makeText(getActivity(), "No record Found", Toast.LENGTH_SHORT).show();
                    listViewId.setAdapter(null);
                } else {

                    ArrayList<ContactInformation> arrayList = new ArrayList<>();
                    while (cursor.moveToNext()) {
                        arrayList.add(new ContactInformation(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                                cursor.getString(4)));
                    }
                    listViewId.setAdapter(new RecyclerViewAdapter(arrayList, getActivity()));
                    listViewId.setLayoutManager(new LinearLayoutManager(getActivity()));
                }
                etQuery.setText("");
            }
        });
    }
}
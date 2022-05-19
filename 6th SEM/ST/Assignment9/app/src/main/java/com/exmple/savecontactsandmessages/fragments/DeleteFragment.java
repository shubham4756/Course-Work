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

public class DeleteFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delete, container, false);
    }

    private EditText etDeleteId;
    private RecyclerView recyclerViewDelete;
    private Button btnDeleteFind,btnDeleteDelete;
    private String id;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etDeleteId = view.findViewById(R.id.etDeleteId);
        recyclerViewDelete = view.findViewById(R.id.deleteRecyclerList);
        btnDeleteDelete = view.findViewById(R.id.deleteButton);
        btnDeleteFind = view.findViewById(R.id.deleteSearchButton);

        btnDeleteFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = etDeleteId.getText().toString().trim();

                recyclerViewDelete.setVisibility(View.INVISIBLE);
                btnDeleteDelete.setVisibility(View.INVISIBLE);

                if(id.isEmpty()) {
                    Toast.makeText(getActivity(),"Enter valid input ",Toast.LENGTH_SHORT).show();
                    recyclerViewDelete.setAdapter(null);
                    return;
                }

                DatabaseHandler db = new DatabaseHandler(getActivity());
                Cursor cursor = db.getDataById(id);
                if(cursor.getCount() == 0) {
                    Toast.makeText(getActivity(),"No record Found",Toast.LENGTH_SHORT).show();
                    recyclerViewDelete.setAdapter(null);
                } else {

                    ArrayList<ContactInformation> arrayList = new ArrayList<>();
                    while (cursor.moveToNext()) {
                        arrayList.add(new ContactInformation(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                                cursor.getString(4)));
                    }
                    recyclerViewDelete.setAdapter(new RecyclerViewAdapter(arrayList,getActivity()));
                    recyclerViewDelete.setLayoutManager(new LinearLayoutManager(getActivity()));

                    recyclerViewDelete.setVisibility(View.VISIBLE);
                    btnDeleteDelete.setVisibility(View.VISIBLE);
                }
                etDeleteId.setText("");
            }
        });

        btnDeleteDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandler db = new DatabaseHandler(getActivity());

                try {
                    db.deleteData(id);
                    Toast.makeText(getActivity(),"Deleted Successful ",Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }


                recyclerViewDelete.setVisibility(View.INVISIBLE);
                btnDeleteDelete.setVisibility(View.INVISIBLE);
            }
        });

    }
}
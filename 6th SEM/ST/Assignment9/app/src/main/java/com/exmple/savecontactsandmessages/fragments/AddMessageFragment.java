package com.exmple.savecontactsandmessages.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.exmple.savecontactsandmessages.ContactInformation;
import com.exmple.savecontactsandmessages.DatabaseHandler;
import com.exmple.savecontactsandmessages.R;
import com.exmple.savecontactsandmessages.RecyclerViewAdapter;

import java.util.ArrayList;

public class AddMessageFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_message, container, false);
    }

    public EditText etUserId, etMessage;
    public Button addMessage;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etUserId = view.findViewById(R.id.etUserId);
        etMessage = view.findViewById(R.id.etUserMessage);
        addMessage = view.findViewById(R.id.addMessage);


        addMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = etUserId.getText().toString().trim();
                String message = etMessage.getText().toString().trim();

                if (id.isEmpty() || message.isEmpty()) {
                    Toast.makeText(getActivity(), "Enter valid input ", Toast.LENGTH_SHORT).show();
                    return;
                }

                DatabaseHandler db = new DatabaseHandler(getActivity());
                Cursor cursor = db.getDataById(id);
                if (cursor.getCount() == 0) {
                    Toast.makeText(getActivity(), "No record Found", Toast.LENGTH_SHORT).show();
                } else {
                    boolean flag = db.insertMessage(message, Integer.parseInt(id));

                    if (flag) {
                        Toast.makeText(view.getContext(), "Successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(view.getContext(), "Something Went Wrong!", Toast.LENGTH_SHORT).show();
                    }
                }

                etUserId.setText("");
                etMessage.setText("");
            }
        });


    }
}
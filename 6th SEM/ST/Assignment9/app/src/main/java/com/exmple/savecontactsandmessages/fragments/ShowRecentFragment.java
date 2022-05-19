package com.exmple.savecontactsandmessages.fragments;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.exmple.savecontactsandmessages.DatabaseHandler;
import com.exmple.savecontactsandmessages.R;

public class ShowRecentFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_recent, container, false);
    }

    private EditText etShowMessages;
    private Button find;
    private TextView messages;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etShowMessages = view.findViewById(R.id.etShowMessageId);
        find = view.findViewById(R.id.findMessages);
        messages = view.findViewById(R.id.messages);

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                messages.setText("");
                String personId = etShowMessages.getText().toString().trim();

                if (personId.isEmpty()) {
                    Toast.makeText(getActivity(), "Enter valid input ", Toast.LENGTH_SHORT).show();
                    return;
                }

                DatabaseHandler db = new DatabaseHandler(getActivity());
                Cursor cursor = db.resentMessage(personId);

                if(cursor.getCount() == 0) {
                    Toast.makeText(getActivity(),"No Record Found",Toast.LENGTH_SHORT).show();
                } else {
                    String t = "";

                    while(cursor.moveToNext()) {
                        t = cursor.getInt(0)  +  ".    " + cursor.getString(1) + "\n";
                    }
                    messages.setVisibility(View.VISIBLE);
                    messages.setText(t);
                }

                etShowMessages.setText("");
            }
        });
    }
}
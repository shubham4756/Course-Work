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
import android.widget.Toast;

import com.exmple.savecontactsandmessages.DatabaseHandler;
import com.exmple.savecontactsandmessages.R;

public class UpdateMessageFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_message, container, false);
    }

    public EditText etUserId, etMessageId, etMessage;
    public Button updateMessage;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etUserId = view.findViewById(R.id.etUserId);
        etMessageId = view.findViewById(R.id.etMessageId);
        etMessage = view.findViewById(R.id.etMessageRead);
        updateMessage = view.findViewById(R.id.updateMessage);

        updateMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = etUserId.getText().toString().trim();
                String messageId = etMessageId.getText().toString().trim();

                if (id.isEmpty() || messageId.isEmpty()) {
                    Toast.makeText(getActivity(), "Enter valid input ", Toast.LENGTH_SHORT).show();
                    return;
                }

                DatabaseHandler db = new DatabaseHandler(getActivity());
                Cursor cursor = db.getDataById(id);

                if(!etMessage.getText().toString().isEmpty()) {

                    if (cursor.getCount() == 0) {
                        Toast.makeText(getActivity(), "No record Found", Toast.LENGTH_SHORT).show();
                        etMessage.setVisibility(View.GONE);
                    } else {
                        String temp = etMessage.getText().toString();

                        try {
                            db.updateData(Integer.parseInt(messageId),temp, id);
                            Toast.makeText(getActivity(), "Data Updated Successfully ", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }

                    etMessage.setText("");
                    etMessage.setVisibility(View.GONE);
                    etUserId.setText("");
                    etMessageId.setText("");

                } else {
                    if (cursor.getCount() == 0) {
                        Toast.makeText(getActivity(), "No record Found", Toast.LENGTH_SHORT).show();
                        etMessage.setVisibility(View.GONE);
                    } else {
                        Toast.makeText(getActivity(), messageId + " " + id,Toast.LENGTH_LONG).show();
                        Cursor cursor1 = db.getMessageById(messageId, id);

                        String message = "";
                        while (cursor1.moveToNext()) {
                            message = cursor1.getString(1);

                        }
                        etMessage.setVisibility(View.VISIBLE);
                        etMessage.setText(message);

                    }
                }


            }
        });


    }
}
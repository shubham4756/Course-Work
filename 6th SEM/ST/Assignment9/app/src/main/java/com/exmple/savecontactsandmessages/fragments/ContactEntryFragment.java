package com.exmple.savecontactsandmessages.fragments;

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

import com.exmple.savecontactsandmessages.DatabaseHandler;
import com.exmple.savecontactsandmessages.R;

public class ContactEntryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contact_entry, container, false);
    }

    private EditText nameEt, phoneEt, emailEt, addressEt;
    private Button submitBtn;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nameEt = view.findViewById(R.id.nameEt);
        phoneEt = view.findViewById(R.id.phoneEt);
        emailEt = view.findViewById(R.id.emailEt);
        addressEt = view.findViewById(R.id.addressEt);

        submitBtn = view.findViewById(R.id.submitBtn);


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = phoneEt.getText().toString().trim();
                String name = nameEt.getText().toString().trim();
                String email = emailEt.getText().toString().trim();
                String address = addressEt.getText().toString().trim();


                if(phone.isEmpty() || name.isEmpty() || email.isEmpty() || address.isEmpty()) {
                    Toast.makeText(view.getContext(), "All Fields are requierd", Toast.LENGTH_LONG).show();
                } else {

                    try {

                        com.exmple.savecontactsandmessages.DatabaseHandler db = new com.exmple.savecontactsandmessages.DatabaseHandler(view.getContext());
                        boolean flag = db.insertData(name, phone, email, address);

                        if (flag) {
                            Toast.makeText(view.getContext(), "Successful", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(view.getContext(), "Something Went Wrong!", Toast.LENGTH_SHORT).show();
                        }

                        nameEt.setText("");
                        phoneEt.setText("");
                        emailEt.setText("");
                        addressEt.setText("");
                    } catch (Exception e) {

                    }

                }

            }
        });

    }
}
package com.exmple.savecontacts.fragments;

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

import com.exmple.savecontacts.DatabaseHandler;
import com.exmple.savecontacts.R;

public class UpdateFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update, container, false);
    }

    private EditText etUpdateId, etUpdateName,etUpdatePhone,etUpdateEmail, etUpdateAddress;
    private Button btnFind,btnUpdate;
    private TextView tvId;
    private TextView tvUpdateIdShow, tvUpdateName, tvUpdatePhone, tvUpdateEmail, tvUpdateAddress;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etUpdateId = view.findViewById(R.id.etUpdateId);
        etUpdateName = view.findViewById(R.id.etUpdateName);
        etUpdatePhone = view.findViewById(R.id.etUpdatePhone);
        etUpdateEmail = view.findViewById(R.id.etUpdateEmail);
        etUpdateAddress = view.findViewById(R.id.etUpdateAddress);

        btnFind = view.findViewById(R.id.updateSearchButton);
        btnUpdate = view.findViewById(R.id.etUpdateButton);

        tvUpdateIdShow = view.findViewById(R.id.tvUpdateIdShow);
        tvId = view.findViewById(R.id.tvUpdateId);

        tvUpdateName = view.findViewById(R.id.tvUpdateName);
        tvUpdatePhone = view.findViewById(R.id.tvUpdatePhone);
        tvUpdateEmail = view.findViewById(R.id.tvUpdateEmail);
        tvUpdateAddress = view.findViewById(R.id.tvUpdateAddress);


        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeInvisible();

                String id = etUpdateId.getText().toString().trim();

                if(id.isEmpty()) {
                    Toast.makeText(getActivity(),"Enter valid input ",Toast.LENGTH_SHORT).show();
                    return;
                }

                DatabaseHandler db = new DatabaseHandler(getActivity());
                Cursor cursor = db.getDataById(id);
                if(cursor.getCount() == 0) {
                    Toast.makeText(getActivity(),"No record Found",Toast.LENGTH_SHORT).show();
                } else {
                    makeVisible();

                    while (cursor.moveToNext()) {

                        tvId.setText(cursor.getString(0));
                        etUpdateName.setText(cursor.getString(1));
                        etUpdatePhone.setText(cursor.getString(2));
                        etUpdateEmail.setText(cursor.getString(3));
                        etUpdateAddress.setText(cursor.getString(4));
                    }

                }
                etUpdateId.setText("");
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = tvId.getText().toString().trim();
                String name = etUpdateName.getText().toString().trim();
                String phone = etUpdatePhone.getText().toString().trim();
                String email = etUpdateEmail.getText().toString().trim();
                String address = etUpdateAddress.getText().toString().trim();

                if(name.isEmpty() || phone.isEmpty() || email.isEmpty() || address.isEmpty()) {
                    Toast.makeText(getActivity(),"All fields Are requierd ",Toast.LENGTH_SHORT).show();
                    return;
                }

                DatabaseHandler db = new DatabaseHandler(getActivity());
                try {
                    db.updateData(Integer.parseInt(id), name, phone, email, address);
                    Toast.makeText(getActivity(), "Data Updated Successfully ", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }

                makeInvisible();
            }
        });

    }

    private void makeInvisible() {
        etUpdateName.setVisibility(View.GONE);
        etUpdatePhone.setVisibility(View.GONE);
        etUpdateEmail.setVisibility(View.GONE);
        etUpdateAddress.setVisibility(View.GONE);

        btnUpdate.setVisibility(View.GONE);

        tvUpdateIdShow.setVisibility(View.GONE);
        tvId.setVisibility(View.GONE);
        tvUpdateName.setVisibility(View.GONE);
        tvUpdatePhone.setVisibility(View.GONE);
        tvUpdateEmail.setVisibility(View.GONE);
        tvUpdateAddress.setVisibility(View.GONE);
    }

    private void makeVisible() {
        etUpdateName.setVisibility(View.VISIBLE);
        etUpdatePhone.setVisibility(View.VISIBLE);
        etUpdateEmail.setVisibility(View.VISIBLE);
        etUpdateAddress.setVisibility(View.VISIBLE);

        btnUpdate.setVisibility(View.VISIBLE);

        tvUpdateIdShow.setVisibility(View.VISIBLE);
        tvId.setVisibility(View.VISIBLE);
        tvUpdateName.setVisibility(View.VISIBLE);
        tvUpdatePhone.setVisibility(View.VISIBLE);
        tvUpdateEmail.setVisibility(View.VISIBLE);
        tvUpdateAddress.setVisibility(View.VISIBLE);
    }
}
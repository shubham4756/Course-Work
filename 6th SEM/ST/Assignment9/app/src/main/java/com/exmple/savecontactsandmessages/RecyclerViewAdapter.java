package com.exmple.savecontactsandmessages;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<com.exmple.savecontactsandmessages.ContactInformation> info;
    private Context mContext;

    public RecyclerViewAdapter(ArrayList<com.exmple.savecontactsandmessages.ContactInformation> info, Context mContext) {
        this.info = info;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.idTv.setText("Id : " + info.get(position).getId());
        holder.nameTv.setText("Name: " + info.get(position).getName());
        holder.phoneTv.setText("Phone: " + info.get(position).getPhone());
        holder.emailTv.setText("Email: " + info.get(position).getEmail());
        holder.addressTv.setText("Address: " + info.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return info.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView idTv, nameTv, phoneTv, emailTv, addressTv;
        LinearLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            idTv = itemView.findViewById(R.id.idDb);
            nameTv = itemView.findViewById(R.id.nameTv);
            phoneTv = itemView.findViewById(R.id.phoneTv);
            emailTv = itemView.findViewById(R.id.emailTv);
            addressTv = itemView.findViewById(R.id.addressTv);

        }
    }
}

package com.example.project2_sos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactViewHolder>{
    ArrayList<Contact> mLanguages;
    public ContactsAdapter(ArrayList<Contact> mLanguages){
        this.mLanguages = mLanguages;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View languageView = inflater.inflate(R.layout.contact_item, parent, false);
        ContactViewHolder viewHolder = new ContactViewHolder(languageView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact currentContact = mLanguages.get(position);
        holder.txtname.setText(currentContact.name);
        holder.txtmobile.setText(currentContact.mobile);
    }

    @Override
    public int getItemCount() {
        return mLanguages.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView txtname, txtmobile;
        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            txtname = itemView.findViewById(R.id.txt_cname);
            txtmobile = itemView.findViewById(R.id.txt_cmobile);
        }

    }
}

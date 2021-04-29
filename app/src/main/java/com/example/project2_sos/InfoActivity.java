package com.example.project2_sos;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class InfoActivity extends AppCompatActivity {
    EditText name, blood, contact, contact_no;
    Button btn_save;
    DatabaseHelper myDb;

    public void fillRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        ArrayList<Contact> allContacts = myDb.getAllContacts();
        ContactsAdapter langaugeAdapter = new ContactsAdapter(allContacts);
        recyclerView.setAdapter(langaugeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        myDb=new DatabaseHelper(this);

        name = findViewById(R.id.txt_name);
        blood = findViewById(R.id.txt_blood);
        contact = findViewById(R.id.txt_contactName);
        contact_no = findViewById(R.id.txt_contact);

        //SET NAME, BLOOD GROUP
        SharedPreferences sp=getApplicationContext().getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);
        String sp_name = sp.getString("name", "");
        String sp_blood = sp.getString("blood", "");
        name.setText(sp_name);
        blood.setText(sp_blood);

        //SET CONTACTS LIST
        fillRecyclerView();

        btn_save = findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
            @Override
            public void onClick(View v) {

                String v_name = name.getText().toString();
                String v_blood = blood.getText().toString();
                String v_cname = contact.getText().toString();
                String v_cno = contact_no.getText().toString();

                // can't set person info as empty
                if (v_name.isEmpty() || v_blood.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please enter your health information", Toast.LENGTH_SHORT).show();
                    return;
                }

                // update personal info
                SharedPreferences.Editor editor=sp.edit();
                editor.putString("name",v_name);
                editor.putString("blood",v_blood);
                editor.commit();

                //update contacts
                if (!v_cname.isEmpty() && !v_cno.isEmpty()){
                    Contact contact = new Contact();
                    contact.name = v_cname;
                    contact.mobile = v_cno;
                    myDb.insertContact(contact);
                    fillRecyclerView();
                }
                Toast.makeText(getApplicationContext(), "Changes Saved", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
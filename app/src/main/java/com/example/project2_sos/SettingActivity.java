package com.example.project2_sos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Set;

public class SettingActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        myDb=new DatabaseHelper(this);

        editText = findViewById(R.id.txt_newMessage);
        editText.setText(myDb.getMessage());

        Button btn_changes = findViewById(R.id.btn_change);
        btn_changes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newMessage = editText.getText().toString();
                myDb.updateMessage(newMessage);
                Toast.makeText(getApplicationContext(), "Message updated", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SettingActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
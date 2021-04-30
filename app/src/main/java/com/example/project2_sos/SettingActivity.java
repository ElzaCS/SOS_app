package com.example.project2_sos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Set;

import yuku.ambilwarna.AmbilWarnaDialog;

public class SettingActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editText;
    int mDefaultColor;
    ConstraintLayout background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        myDb=new DatabaseHelper(this);

        SharedPreferences sp=getApplicationContext().getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);
        background = findViewById(R.id.settings_layout);
        background.setBackgroundColor(sp.getInt("bg_color", Color.WHITE));
        mDefaultColor = Color.WHITE;

        editText = findViewById(R.id.txt_newMessage);
        editText.setText(myDb.getMessage());

        Button mButton = (Button) findViewById(R.id.btn_bgColor);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openColorPicker();
            }
        });

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

        ToggleButton toggleButton = findViewById(R.id.toggleButton);

        int defaultChecked = sp.getInt("detect", 1);
        if (defaultChecked==1)
            toggleButton.setChecked(true);
        else
            toggleButton.setChecked(false);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor=sp.edit();
                if (isChecked)
                    editor.putInt("detect",1);
                else
                    editor.putInt("detect",0);

                editor.commit();
            }
        });
    }

    public void openColorPicker() {
        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(this, mDefaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
            }
            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                mDefaultColor = color;
                background.setBackgroundColor(mDefaultColor);

                SharedPreferences sp=getApplicationContext().getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                editor.putInt("bg_color",mDefaultColor);
                editor.commit();
            }
        });
        colorPicker.show();
    }
}
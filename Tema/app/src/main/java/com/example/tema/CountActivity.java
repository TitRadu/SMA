package com.example.tema;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CountActivity extends AppCompatActivity {
    private Button numberButton,readButton,writeButton;
    private EditText keyView, dataView;
    private TextView countText,showView;
    int extraInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);
        initializeViews();
        Intent intent = getIntent();
        extraInfo = intent.getIntExtra("count",0);
        setOnClickListeners();
    }

    private void initializeViews(){
        numberButton=findViewById(R.id.numberBtn);
        countText = findViewById(R.id.countText);
        readButton = findViewById(R.id.readBtn);
        writeButton = findViewById(R.id.writeBtn);
        keyView = findViewById(R.id.keyView);
        dataView = findViewById(R.id.dataView);
        showView = findViewById(R.id.showView);


    }

    private void setOnClickListeners(){
        numberButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                countText.setText(String.valueOf(extraInfo));
                countText.setTextSize(30);

            }
        });

        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readPreferences();
            }
        });

        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writePreferences();
            }
        });

    }

    public boolean keyViewCheckIsEmpty(){
        String key = keyView.getText().toString();
        if(key.isEmpty()){
            Toast.makeText(this, "Nu ati introdus o cheie!", Toast.LENGTH_SHORT).show();
            return true;
        }

        return false;

    }

    public boolean dataViewCheckIsEmpty(){
        String data = dataView.getText().toString();
        if(data.isEmpty()){
            Toast.makeText(this, "Nu ati introdus data!", Toast.LENGTH_SHORT).show();
            return true;
        }

        return false;

    }

    public void readPreferences(){
        if(keyViewCheckIsEmpty()){
            return;

        }
        String key = keyView.getText().toString();
        SharedPreferences prefs = getSharedPreferences("preferences.txt", MODE_PRIVATE);
        String data = prefs.getString(key,"Key not found!");
        showView.setText(data);

    }


    public void writePreferences(){
        if(keyViewCheckIsEmpty()){
            return;

        }

        if(dataViewCheckIsEmpty()){
            return;

        }
        String keyValue = keyView.getText().toString();
        String dataValue = dataView.getText().toString();
        SharedPreferences.Editor editor = getSharedPreferences("preferences.txt", MODE_PRIVATE).edit();
        editor.putString(keyValue,dataValue);
        editor.apply();

    }

}

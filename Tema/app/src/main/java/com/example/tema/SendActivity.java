package com.example.tema;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SendActivity extends AppCompatActivity {
    Button nameButton;
    TextView showName;
    String extraInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        initializeViews();
        Intent intent = getIntent();
        extraInfo = intent.getStringExtra("name");
        setOnClickListeners();
    }

    private void setOnClickListeners(){
        nameButton.setOnClickListener(v -> showName.setText(extraInfo));


    }

    private void initializeViews(){
        nameButton = findViewById(R.id.nameBtn);
        showName = findViewById(R.id.showName);

    }

}
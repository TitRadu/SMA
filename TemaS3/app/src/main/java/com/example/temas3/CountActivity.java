package com.example.temas3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CountActivity extends AppCompatActivity {
    private Button numberButton;
    private TextView countText;
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

    }

    private void setOnClickListeners(){
        numberButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                countText.setText(""+extraInfo);
                countText.setTextSize(30);

            }
        });
    }

}
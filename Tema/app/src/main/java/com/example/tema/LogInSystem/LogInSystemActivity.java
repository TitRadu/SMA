package com.example.tema.LogInSystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tema.R;

public class LogInSystemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_system);
    }

    public void logInActivity(View view){
        Intent intent = new Intent(this, LogInActivity.class);
        startActivity(intent);

    }

    public void registerActivity(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);

    }

}
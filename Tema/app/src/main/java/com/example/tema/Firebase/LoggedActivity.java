package com.example.tema.Firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tema.Firebase.Storage.StorageActivity;
import com.example.tema.Firebase.TeamList.TeamsDBActivity;
import com.example.tema.R;

public class LoggedActivity extends AppCompatActivity {
    Button teamsDBButton;
    TextView signedAsView;
    String extraInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        extraInfo = intent.getStringExtra("email");
        setContentView(R.layout.activity_logged);
        initializeViews();

    }

    private void initializeViews(){
        signedAsView = findViewById(R.id.signedAsView);
        signedAsView.setText("Signed as " + extraInfo + "!");
        teamsDBButton = findViewById(R.id.teamsDBBtn);

    }

    public void teamsDBActivity(View view){
        Intent intent = new Intent(getBaseContext(), TeamsDBActivity.class);
        startActivity(intent);

    }

    public void storageActivity(View view){
        Intent intent = new Intent(getBaseContext(), StorageActivity.class);
        startActivity(intent);

    }

}
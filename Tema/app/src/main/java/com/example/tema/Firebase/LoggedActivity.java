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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoggedActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    Button teamsDBButton;
    TextView signedAsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged);
        initializeFirebaseObjects();
        initializeViews();

    }

    private void initializeFirebaseObjects(){
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

    }

    private void initializeViews(){
        signedAsView = findViewById(R.id.signedAsView);
        signedAsView.setText("Signed as " + firebaseUser.getEmail() + "!");
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

    public void signOutActivity(View view){
        firebaseAuth.signOut();
        finishAndRemoveTask();

    }
}
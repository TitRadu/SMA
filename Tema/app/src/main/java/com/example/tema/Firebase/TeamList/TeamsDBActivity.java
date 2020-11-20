package com.example.tema.Firebase.TeamList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tema.Firebase.FirebaseHelper;
import com.example.tema.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TeamsDBActivity extends AppCompatActivity {
    FirebaseHelper firebaseHelper;
    private EditText teamNameInput, countryInput, cityInput;
    private String teamName, country, city;
    private List<Team> teamsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams_d_b);
        firebaseHelper = FirebaseHelper.getInstance();
        initializeViews();
        getTeams();

    }

    private void initializeViews(){
        teamNameInput = findViewById(R.id.teamNameInput);
        countryInput = findViewById(R.id.countryInput);
        cityInput = findViewById(R.id.cityInput);

    }

    private void initializeList(){
        teamsList = new ArrayList<>();

    }

    public void inputCheck(View view){
        teamName = teamNameInput.getText().toString();
        country = countryInput.getText().toString();
        city = cityInput.getText().toString();


        if(teamName.isEmpty()){
            Toast.makeText(this,"Introduce a team name!",Toast.LENGTH_SHORT).show();
            return;
        }


        if(country.isEmpty()){
            Toast.makeText(this,"Introduce a country!",Toast.LENGTH_SHORT).show();
            return;
        }

        if(city.isEmpty()){
            Toast.makeText(this,"Introduce a city!",Toast.LENGTH_SHORT).show();
            return;
        }

        Team newTeam = new Team(teamName, country, city);
        addTeam(newTeam);

    }

    private void addTeam(Team team){
        firebaseHelper.teamDatabaseReference.child(UUID.randomUUID().toString()).setValue(team);

    }

    private void getTeams(){
        FirebaseHelper.teamDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                initializeList();
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    Team team = dataSnapshot1.getValue(Team.class);
                    teamsList.add(team);

                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(TeamsDBActivity.this, "GetTeamsError!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void showTeamsActivity(View view){
        Intent intent = new Intent(getBaseContext(), ShowTeamsActivity.class);
        intent.putExtra("content", (Serializable) teamsList);
        startActivity(intent);

    }

}
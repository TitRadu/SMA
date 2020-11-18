package com.example.tema.Firebase.TeamList;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tema.R;

public class TeamViewHolder extends RecyclerView.ViewHolder{
    private TextView teamNameView;
    private TextView countryView;
    private TextView cityView;


    public TeamViewHolder(@NonNull View itemView) {
        super(itemView);
        initializeViews();

    }

    private void initializeViews(){
        teamNameView = itemView.findViewById(R.id.team_name);
        countryView = itemView.findViewById(R.id.country);
        cityView = itemView.findViewById(R.id.city);

    }

    @SuppressLint("SetTextI18n")
    public void setValues(String team, String country, String city){
        teamNameView.setText("Team:" + team);
        countryView.setText("Country:" + country);
        cityView.setText("City:" + city);

    }


}

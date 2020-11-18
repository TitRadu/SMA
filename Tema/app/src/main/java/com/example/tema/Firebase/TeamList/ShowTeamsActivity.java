package com.example.tema.Firebase.TeamList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.tema.PieceDataBase.Piece;
import com.example.tema.PieceDataBase.PieceAdapter;
import com.example.tema.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ShowTeamsActivity extends AppCompatActivity {
    private List<Team> content;
    private RecyclerView teamsListRV;
    private TeamAdapter teamAdapter;
    private List<Team> extraInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_teams);
        Intent intent = getIntent();
        extraInfo = (List<Team>) intent.getSerializableExtra("content");
        initializeViews();
        initializeList();
        setRecyclerView();
        showContent();
    }

    private void initializeViews(){
        teamsListRV = findViewById(R.id.rv_teams_list);

    }

    private void initializeList(){
        content = new ArrayList<>();

    }

    private void setRecyclerView(){
        teamAdapter = new TeamAdapter(content);
        teamsListRV.setLayoutManager(new LinearLayoutManager(this));
        teamsListRV.setAdapter(teamAdapter);

    }

    void showContent(){
        content.addAll((Collection<? extends Team>) extraInfo);
        teamAdapter.notifyDataSetChanged();

    }

}
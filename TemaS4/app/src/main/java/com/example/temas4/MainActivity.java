package com.example.temas4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String extraInfo;
    private RecyclerView pieceListRV;
    private PieceAdapter pieceAdapter;
    private List<Piece> piecesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
       // Intent intent = getIntent();
      //  extraInfo = intent.getStringExtra(AppConstants.NAVIGATION_KEY_1);
        initializeList();
        setRecyclerView();
    }

    private void initializeList(){
        piecesList = new ArrayList<>();
        piecesList.add(new Piece("Motor electric","Bosch",400));
        piecesList.add(new Piece("Motor Diesel","Bosch",450));

    }

    private void setRecyclerView(){
        pieceAdapter = new PieceAdapter(piecesList);
        pieceListRV.setLayoutManager(new LinearLayoutManager(this));
        pieceListRV.setAdapter(pieceAdapter);

    }

    private void initializeViews(){
        pieceListRV = findViewById(R.id.rv_pieces_list);

    }

}
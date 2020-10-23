package com.example.tema;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DataBaseContent extends AppCompatActivity {
    private List<Piece> content;
    private RecyclerView pieceListRV;
    private PieceAdapter pieceAdapter;
    private List<Piece> extraInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base_content);
        Intent intent = getIntent();
        extraInfo = (List<Piece>) intent.getSerializableExtra("content");
        initializeViews();
        initializeList();
        setRecyclerView();
        showContent();
    }

    private void initializeViews(){
        pieceListRV = findViewById(R.id.rv_pieces_list);

    }

    private void initializeList(){
        content = new ArrayList<>();

    }

    private void setRecyclerView(){
        pieceAdapter = new PieceAdapter(content);
        pieceListRV.setLayoutManager(new LinearLayoutManager(this));
        pieceListRV.setAdapter(pieceAdapter);

    }

    void showContent(){
        content.addAll((Collection<? extends Piece>) extraInfo);
        pieceAdapter.notifyDataSetChanged();

    }

}
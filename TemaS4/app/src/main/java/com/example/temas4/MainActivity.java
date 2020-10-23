package com.example.temas4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String extraInfo;
    private RecyclerView pieceListRV;
    private PieceAdapter pieceAdapter;
    private List<Piece> piecesList;
    private Button addButton, delButton;
    private TextView nameView, producerView, priceView, nameViewDel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        initializeList();
        setOnClickListeners();
        setRecyclerView();

    }

    private void initializeList(){
        piecesList = new ArrayList<>();

    }

    private void setRecyclerView(){
        pieceAdapter = new PieceAdapter(piecesList);
        pieceListRV.setLayoutManager(new LinearLayoutManager(this));
        pieceListRV.setAdapter(pieceAdapter);

    }

    private void initializeViews(){
        pieceListRV = findViewById(R.id.rv_pieces_list);
        addButton = findViewById(R.id.addBtn);
        nameView = findViewById(R.id.nameView);
        producerView = findViewById(R.id.producerView);
        priceView = findViewById(R.id.priceView);
        delButton = findViewById(R.id.delBtn);
        nameViewDel = findViewById(R.id.nameViewDel);

    }

    private void setOnClickListeners(){

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPiece();

            }
        });

        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removePiece();

            }
        });

    }

    private void addPiece(){
        String nameViewText = nameView.getText().toString();
        String producerViewText= producerView.getText().toString();
        String priceViewText = priceView.getText().toString();

        if(nameViewText.isEmpty()){
            Toast.makeText(this,"Introdu un nume!", Toast.LENGTH_SHORT).show();
            return;

        }else{
            for(Piece p : piecesList ){
                if(p.getName().equals(nameViewText)){
                    Toast.makeText(this,"Piesa exista!", Toast.LENGTH_SHORT).show();
                    return;

                }
            }

        }

        if(producerViewText.isEmpty()){
            Toast.makeText(this,"Introdu un producator!", Toast.LENGTH_SHORT).show();
            return;

        }

        if(priceViewText.isEmpty()){
            Toast.makeText(this,"Introdu un pret!", Toast.LENGTH_SHORT).show();
            return;

        }

        try {
            piecesList.add(new Piece(nameView.getText().toString(), producerView.getText().toString(), Float.parseFloat(priceView.getText().toString())));
            pieceAdapter.notifyDataSetChanged();
        }
        catch(NumberFormatException e){
            Toast.makeText(this,"Introdu un pret valid!", Toast.LENGTH_SHORT).show();
            return;

        }

    }

    private void removePiece(){
        String nameViewText = nameViewDel.getText().toString();

        for(Piece p : piecesList ){
            if(p.getName().equals(nameViewText)){
                piecesList.remove(p);
                pieceAdapter.notifyDataSetChanged();
                return;

            }
        }

    }

}
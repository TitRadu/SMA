package com.example.tema.PieceDataBase;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tema.R;


public class PieceViewHolder extends RecyclerView.ViewHolder {
    private TextView pieceName;
    private TextView producerName;
    private TextView priceView;

    public PieceViewHolder(@NonNull View itemView) {
        super(itemView);
        initializeViews();
    }

    public void initializeViews(){
        pieceName = itemView.findViewById(R.id.piece_name);
        producerName = itemView.findViewById(R.id.producer_name);
        priceView = itemView.findViewById(R.id.price);

    }

    @SuppressLint("SetTextI18n")
    public void setValues(String piece, String producer, float price){
        pieceName.setText("Piesa:" + piece);
        producerName.setText("Producator:" + producer);
        priceView.setText("Pret:" + price);

    }

}

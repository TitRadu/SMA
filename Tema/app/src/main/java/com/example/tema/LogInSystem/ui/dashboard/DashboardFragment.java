package com.example.tema.LogInSystem.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tema.PieceDataBase.Piece;
import com.example.tema.PieceDataBase.PieceAdapter;
import com.example.tema.R;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private RecyclerView pieceListRV;
    private PieceAdapter pieceAdapter;
    private List<Piece> piecesList;
    private Button addButton, delButton;
    private TextView nameView, producerView, priceView, nameViewDel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        initializeList();
        initializeViews(root);
        setRecyclerView();
        setOnClickListeners();
        return root;
    }

    private void initializeList(){
        piecesList = new ArrayList<>();

    }

    private void setRecyclerView(){
        pieceAdapter = new PieceAdapter(piecesList);
        pieceListRV.setLayoutManager(new LinearLayoutManager(getContext()));
        pieceListRV.setAdapter(pieceAdapter);

    }

    private void initializeViews(View root){
        pieceListRV = root.findViewById(R.id.rv_pieces_list);
        addButton = root.findViewById(R.id.addBtn);
        nameView = root.findViewById(R.id.nameView);
        producerView = root.findViewById(R.id.producerView);
        priceView = root.findViewById(R.id.priceView);
        delButton = root.findViewById(R.id.delBtn);
        nameViewDel = root.findViewById(R.id.nameViewDel);

    }


    private void setOnClickListeners(){
        addButton.setOnClickListener(v -> addPiece());
        delButton.setOnClickListener(v -> removePiece());

    }

    private void addPiece(){
        String nameViewText = nameView.getText().toString();
        String producerViewText= producerView.getText().toString();
        String priceViewText = priceView.getText().toString();

        if(nameViewText.isEmpty()){
            Toast.makeText(getContext(),"Introdu un nume!", Toast.LENGTH_SHORT).show();
            return;

        }else{
            for(Piece p : piecesList ){
                if(p.getPieceName().equals(nameViewText)){
                    Toast.makeText(getContext(),"Piesa exista!", Toast.LENGTH_SHORT).show();
                    return;

                }
            }

        }

        if(producerViewText.isEmpty()){
            Toast.makeText(getContext(),"Introdu un producator!", Toast.LENGTH_SHORT).show();
            return;

        }

        if(priceViewText.isEmpty()){
            Toast.makeText(getContext(),"Introdu un pret!", Toast.LENGTH_SHORT).show();
            return;

        }

        try {
            piecesList.add(new Piece(nameView.getText().toString(), producerView.getText().toString(), Float.parseFloat(priceView.getText().toString())));
            pieceAdapter.notifyDataSetChanged();
        }
        catch(NumberFormatException e){
            Toast.makeText(getContext(),"Introdu un pret valid!", Toast.LENGTH_SHORT).show();
            return;

        }

    }

    private void removePiece(){
        String nameViewText = nameViewDel.getText().toString();

        for(Piece p : piecesList ){
            if(p.getPieceName().equals(nameViewText)){
                piecesList.remove(p);
                pieceAdapter.notifyDataSetChanged();
                return;

            }
        }

    }

}
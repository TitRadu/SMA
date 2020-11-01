package com.example.tema.PieceDataBase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tema.R;

import java.util.List;

public class PieceAdapter extends RecyclerView.Adapter<PieceViewHolder>{
    private List<Piece> piecesList;
    private Context context;

    @NonNull
    @Override
    public PieceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.piece_row_list, parent, false);
        PieceViewHolder viewHolder = new PieceViewHolder(contactView);
        return viewHolder;
    }

    public PieceAdapter(List<Piece> piecesList){
        this.piecesList = piecesList;

    }

    @Override
    public void onBindViewHolder(@NonNull PieceViewHolder holder, int position) {
        final Piece glassModel = piecesList.get(position);
        holder.setValues(glassModel.getPieceName(),glassModel.getProducer(),glassModel.getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){


            }

        });
    }

    @Override
    public int getItemCount() {
        return piecesList.size();
    }
}

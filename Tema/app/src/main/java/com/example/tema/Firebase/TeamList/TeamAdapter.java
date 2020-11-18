package com.example.tema.Firebase.TeamList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tema.R;

import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamViewHolder> {

    private List<Team> teamsList;
    private Context context;

    @NonNull
    @Override
    public TeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.team_row_list, parent, false);
        TeamViewHolder viewHolder = new TeamViewHolder(contactView);
        return viewHolder;
    }

    public TeamAdapter(List<Team> teamList){
        this.teamsList = teamList;

    }

    @Override
    public void onBindViewHolder(@NonNull TeamViewHolder holder, int position) {
        final Team glassModel = teamsList.get(position);
        holder.setValues(glassModel.getTeamName(),glassModel.getCountry(),glassModel.getCity());

        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){


            }

        });
    }

    @Override
    public int getItemCount() {
        return teamsList.size();
    }

}


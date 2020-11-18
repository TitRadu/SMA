package com.example.tema.Firebase.TeamList;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Team implements Serializable {
    @ColumnInfo
    public String teamName;
    @ColumnInfo
    public String country;
    @ColumnInfo
    public String city;

    public Team(String teamName, String country, String city) {
        this.teamName = teamName;
        this.country = country;
        this.city = city;
    }

    @Ignore
    public Team() {
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}

package com.example.tema.Firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseHelper {
    private static FirebaseHelper firebaseHelper;
    public static DatabaseReference teamDatabaseReference;

    private FirebaseHelper(){

    }

    public static FirebaseHelper getInstance(){
        if(firebaseHelper == null){
            firebaseHelper = new FirebaseHelper();
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            teamDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Teams");

        }

        return firebaseHelper;
    }

}

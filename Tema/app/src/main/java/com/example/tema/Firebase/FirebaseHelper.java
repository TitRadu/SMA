package com.example.tema.Firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseHelper {
    private static FirebaseHelper firebaseHelper;
    public static DatabaseReference teamDatabaseReference;
    public static DatabaseReference userDatabaseReference;

    private FirebaseHelper(){

    }

    public static FirebaseHelper getInstance(){
        if(firebaseHelper == null){
            firebaseHelper = new FirebaseHelper();
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            teamDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Teams");
            userDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Users");

        }

        return firebaseHelper;
    }

}

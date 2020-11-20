package com.example.tema.Firebase.Storage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tema.Firebase.LogInFBActivity;
import com.example.tema.Firebase.TeamList.Team;
import com.example.tema.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class StorageActivity extends AppCompatActivity {
    private FirebaseStorage firebaseStorage;
    private static final String uploadPath = "/storage/emulated/0/DCIM/Camera/";
    private static final String downloadPath = "/storage/emulated/0/Download/";

    EditText uploadInput, downloadInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
        initializeViews();

    }

    private void initializeViews(){
        firebaseStorage = FirebaseStorage.getInstance();
        uploadInput = findViewById(R.id.uploadInput);
        downloadInput = findViewById(R.id.downloadInput);

    }

    public void uploadFile(View view){
        String fileName = uploadInput.getText().toString();

        if(fileName.isEmpty()){
            Toast.makeText(this,"Introduce a upload file!",Toast.LENGTH_SHORT).show();
            return;

        }

        String filePath = uploadPath + fileName;

        Uri file = Uri.fromFile(new File(filePath));
        StorageReference imagesRef = firebaseStorage.getReference().child("images/" + fileName);

        imagesRef.putFile(file)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        Toast.makeText(getBaseContext(), "Upload successfully!", Toast.LENGTH_SHORT).show();
                        Uri downloadUrl = taskSnapshot.getUploadSessionUri();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                            Toast.makeText(getBaseContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();

                    }

                });

    }

    public void downloadFile(View view) throws IOException {
        String fileName = downloadInput.getText().toString();

        if(fileName.isEmpty()){
            Toast.makeText(this,"Introduce a download file!",Toast.LENGTH_SHORT).show();
            return;

        }

        StorageReference bucketRef = firebaseStorage.getReferenceFromUrl("gs://labsma-1e05a.appspot.com/images");
        StorageReference download = bucketRef.child(fileName);

        File localFile = new File(downloadPath, fileName);


        download.getFile(localFile)
                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getBaseContext(), "Download successfully!",Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    Toast.makeText(getBaseContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void showPicturesActivity(View view){
        Intent intent = new Intent(this, ShowPicturesActivity.class);
        startActivity(intent);

    }

}
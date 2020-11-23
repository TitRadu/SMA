package com.example.tema.Firebase.Storage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.tema.Firebase.TeamList.Team;
import com.example.tema.Firebase.TeamList.TeamAdapter;
import com.example.tema.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShowPicturesActivity extends AppCompatActivity {
    private static final int RESULT_LOAD_IMAGE = 1543;
    private List<Uri> content;
    private RecyclerView picturesListRV;
    private PictureAdapter picturesAdapter;
    private FirebaseStorage firebaseStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_pictures);
        initializeViews();

    }

    private void initializeViews(){
        picturesListRV = findViewById(R.id.rv_pictures_list);
        content = new ArrayList<>();
        firebaseStorage = FirebaseStorage.getInstance();

    }

    private void setRecyclerView(){
        if(content != null) {
            picturesAdapter = new PictureAdapter(content);
        }
        picturesListRV.setHasFixedSize(true);
        picturesListRV.setLayoutManager(new LinearLayoutManager(this));
        picturesListRV.setAdapter(picturesAdapter);

    }

    public void uploadFile(Uri uri){
        StorageReference imagesRef = firebaseStorage.getReference().child("images/" + UUID.randomUUID().toString());

        imagesRef.putFile(uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
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

    public void goToPhotoGallery(View view){
        Intent mIntent = new Intent();
        mIntent.setType("image/*");
        mIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        mIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(mIntent,"Select photos"), RESULT_LOAD_IMAGE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode== RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null && data.getClipData() != null)
        {
            content.clear();
            ClipData clipData = data.getClipData();
            for(int i = 0; i < clipData.getItemCount(); i++){

                Uri mUri = clipData.getItemAt(i).getUri();
                content.add(mUri);
                uploadFile(mUri);
            }
            setRecyclerView();
        }
    }

    public void finish(View view){
        finishAndRemoveTask();

    }

}
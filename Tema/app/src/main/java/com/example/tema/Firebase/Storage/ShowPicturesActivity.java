package com.example.tema.Firebase.Storage;

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

import com.example.tema.Firebase.TeamList.Team;
import com.example.tema.Firebase.TeamList.TeamAdapter;
import com.example.tema.R;

import java.util.ArrayList;
import java.util.List;

public class ShowPicturesActivity extends AppCompatActivity {
    private static final int RESULT_LOAD_IMAGE = 1543;
    private List<Uri> content;
    private RecyclerView picturesListRV;
    private PictureAdapter picturesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_pictures);
        initializeViews();

    }

    private void initializeViews(){
        picturesListRV = findViewById(R.id.rv_pictures_list);
        content = new ArrayList<>();

    }

    private void setRecyclerView(){
        if(content != null) {
            picturesAdapter = new PictureAdapter(content);
        }
        picturesListRV.setHasFixedSize(true);
        picturesListRV.setLayoutManager(new LinearLayoutManager(this));
        picturesListRV.setAdapter(picturesAdapter);

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
            }
            setRecyclerView();
        }
    }

    public void finish(View view){
        finishAndRemoveTask();

    }

}
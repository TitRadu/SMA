package com.example.temas3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button countButton,textButton,sendButton;
    private TextView hiText;
    private EditText editText;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        setOnClickListeners();
    }

    private void setOnClickListeners(){
        countButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                countActivity();
            }
        });

        textButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                nameAdd();

            }

        });

        sendButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                nameSend();

            }

        });
    }

    private void initializeViews(){
        countButton=findViewById(R.id.countBtn);
        textButton=findViewById(R.id.textBtn);
        hiText=findViewById(R.id.hiText);
        editText=findViewById(R.id.editText);
        sendButton=findViewById(R.id.sendBtn);
        count = 0;

    }

    private void countActivity(){
        count++;
        Intent intent = new Intent(this,CountActivity.class);
        intent.putExtra("count",count);
        startActivity(intent);

    }

    @SuppressLint("SetTextI18n")
    private void nameAdd(){
        String name = editText.getText().toString();

        if(editText.getText().toString().isEmpty()) {
            Toast.makeText(this,"Nu ati introdus un nume!",Toast.LENGTH_SHORT).show();
        }else{
            hiText.setText("Hi," + name + "!");
        }
    }

    private void nameSend(){
        String sendName = editText.getText().toString();
        if(sendName.isEmpty()) {
            Toast.makeText(this, "Nu ati introdus un nume!", Toast.LENGTH_SHORT).show();
        } else{
            Intent intent = new Intent(this, SendActivity.class);
            intent.putExtra("name",sendName);
            startActivity(intent);

        }

    }

}
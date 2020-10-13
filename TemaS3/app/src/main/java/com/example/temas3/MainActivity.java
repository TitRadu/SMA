package com.example.temas3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button countButton,okButton,cancelButton,showButton,addButton;
    private TextView hiText;
    private EditText editText;
    private LinearLayout hideLayout;
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
        okButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                nameSend();

            }

        });
        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                cancel();

            }

        });
        showButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                show();

            }

        });
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                nameAdd();

            }

        });

    }

    private void initializeViews(){
        countButton=findViewById(R.id.countBtn);
        hideLayout=findViewById(R.id.hideLayout);
        okButton=findViewById(R.id.okBtn);
        cancelButton=findViewById(R.id.cancelBtn);
        showButton=findViewById(R.id.showBtn);
        hiText=findViewById(R.id.hiText);
        editText=findViewById(R.id.editText);
        addButton=findViewById(R.id.addBtn);
        count = 0;

    }

    private void countActivity(){
        count++;
        Intent intent = new Intent(this,CountActivity.class);
        intent.putExtra("count",count);
        startActivity(intent);

    }

    private void nameSend(){
        String sendName = editText.getText().toString();
        if(sendName.isEmpty()) {
            Toast.makeText(this, "Nu ati introdus un nume!", Toast.LENGTH_SHORT).show();
        } else{
            Intent intent = new Intent(this, SendActivity.class);
            intent.putExtra("name",sendName);
            startActivity(intent);
            editText.setText("");
            hiText.setText("Hi!");
            hideLayout.setVisibility(View.INVISIBLE);

        }

    }

    private void cancel(){
        Toast.makeText(this,"Dialog ascuns!",Toast.LENGTH_SHORT).show();
        hideLayout.setVisibility(View.INVISIBLE);

    }

    private void show(){
        Toast.makeText(this,"Dialog vizibil!",Toast.LENGTH_SHORT).show();
        hideLayout.setVisibility(View.VISIBLE);

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

}
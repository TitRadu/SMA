package com.example.tema;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileIOActivity extends AppCompatActivity {
    private Button readButton, writeButton;
    private TextView readView;
    private EditText writeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_i_o);
        initializeViews();
        setOnClickListeners();

    }

    public void initializeViews(){
        readButton = findViewById(R.id.readBtn);
        writeButton = findViewById(R.id.writeBtn);
        readView = findViewById(R.id.readView);
        writeView = findViewById(R.id.writeView);

    }

    private void setOnClickListeners() {
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFromFile();

            }

        });
        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeInFile();

            }

        });

    }

    public boolean writeViewCheckIsEmpty(){
        String data = writeView.getText().toString();
        if(data.isEmpty()){
            Toast.makeText(this, "Nu ati introdus data!", Toast.LENGTH_SHORT).show();
            return true;
        }

        return false;

    }

    public void readFromFile(){
        String stringFromFile = "";
        try{
            InputStream inputStream = this.openFileInput(AppConstants.FISIER_TEXT);

            if( inputStream !=null){
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while((receiveString = bufferedReader.readLine()) != null ){
                    stringBuilder.append(receiveString);

                }
                inputStream.close();
                stringFromFile = stringBuilder.toString();
                readView.setText(stringFromFile);
            }
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "File not found!", Toast.LENGTH_SHORT).show();
            return;
        } catch (IOException e) {
            Toast.makeText(this, "Can not read the file!", Toast.LENGTH_SHORT).show();
        }

    }

    public void writeInFile(){
        if(writeViewCheckIsEmpty()){
            return;

        }

        String data = writeView.getText().toString();
        try{
            OutputStreamWriter outputStreamWriter =
                    new OutputStreamWriter(this.openFileOutput(AppConstants.FISIER_TEXT, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();

        } catch (IOException e) {
            Toast.makeText(this, "Can not write the file!", Toast.LENGTH_SHORT).show();
            return;
        }

    }

}


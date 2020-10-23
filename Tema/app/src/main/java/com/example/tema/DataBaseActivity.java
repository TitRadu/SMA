package com.example.tema;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataBaseActivity extends AppCompatActivity {

    private PieceDatabase pieceDatabase;
    private List<Piece> pieceList;
    private String pieceName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base);
        initializers();
        pieceList = new ArrayList<>();
    }

    private void initializers()
    {
        pieceDatabase = PieceDatabase.getInstance(this);
    }

    public void addToDatabase(View view) {
        final TextInputEditText inputEditText;
        TextInputLayout textInputLayout;
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        new AlertDialog.Builder(this, R.style.InputDialogTheme);
        View viewInflated = LayoutInflater.from(this).inflate(R.layout.view_input_dialog, (ViewGroup) findViewById(R.id.et_input_dialog) , false);
        inputEditText = viewInflated.findViewById(R.id.et_input_dialog);
        textInputLayout = viewInflated.findViewById(R.id.til_input_dialog);
        alert.setView(viewInflated);
        alert.setTitle("Add some value in the database");
        textInputLayout.setHint("Value");
        alert.setPositiveButton("Add value", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                if (inputEditText.getText() == null || inputEditText.getText().toString().isEmpty())
                {
                    return;
                }
                pieceName = inputEditText.getText().toString();
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        insertToDatabase(pieceName);
                    }
                });
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        alert.show();
    }

    private void insertToDatabase(final String name)
    {
        class InsertValue extends AsyncTask<Void, Void, Piece> {

            @Override
            protected Piece doInBackground(Void... voids) {
                Piece piece = new Piece(name, "Bosch");
                pieceDatabase.pieceDao().insertAllPieces(piece);
                return piece;
            }

            @Override
            protected void onPostExecute(Piece piece) {
                super.onPostExecute(piece);
            }
        }

        InsertValue insertTask = new InsertValue();
        insertTask.execute();
    }

    public void getFromDatabase(View view) {
        class GetValue extends AsyncTask<Void, Void, Piece> {

            @Override
            protected Piece doInBackground(Void... voids) {
                Piece piece = new Piece(pieceName, "Bosch");
                pieceList = pieceDatabase.pieceDao().getAll();
                dataBaseContentActivity();
                return piece;
            }

            @Override
            protected void onPostExecute(Piece piece) {
                super.onPostExecute(piece);
            }
        }

        GetValue insertTask = new GetValue();
        insertTask.execute();
    }

    private void dataBaseContentActivity(){
        Intent intent = new Intent(this,DataBaseContent.class);
        intent.putExtra("content", (Serializable) pieceList);
        startActivity(intent);

    }

}
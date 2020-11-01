package com.example.tema.LogInSystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tema.AppExecutors;
import com.example.tema.LogInSystem.BottomNavigationActivity;
import com.example.tema.LogInSystem.UserDataBase.User;
import com.example.tema.LogInSystem.UserDataBase.UserDatabase;
import com.example.tema.PieceDataBase.Piece;
import com.example.tema.PieceDataBase.PieceDatabase;
import com.example.tema.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class LogInActivity extends AppCompatActivity {
    Button logInButton;

    private UserDatabase userDatabase;
    private List<User> userList;
    private String userName;
    private String password;
    private EditText userNameInput, passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        initializeViews();
        setOnClickListeners();

    }

    private void initializeViews(){
        logInButton = findViewById(R.id.logInBtn);
        userDatabase = UserDatabase.getInstance(this);
        userNameInput = findViewById(R.id.userNameInput);
        passwordInput = findViewById(R.id.passwordInput);

    }

    private void setOnClickListeners(){
        logInButton.setOnClickListener(v -> bottomNavigationActivity());

    }

    private void bottomNavigationActivity(){
        userList = null;

        String userName = userNameInput.getText().toString();
        String password = passwordInput.getText().toString();

        if(userName.isEmpty()){
            Toast.makeText(this,"Introduceti un nume de utilizator!",Toast.LENGTH_SHORT).show();
            return;
        }
        if(password.isEmpty()){
            Toast.makeText(this,"Introduceti o parola!",Toast.LENGTH_SHORT).show();
            return;
        }

        getFromDatabase(userName);

        while(userList == null){
            Log.d("lala","zuzu");
        }

        if(userList.size() == 0){
            Toast.makeText(this,"User sau parola gresita!",Toast.LENGTH_SHORT).show();
            return;

        }
        String userPassword = userList.get(0).getPassword().toString();
        if(userPassword.equals(password)){
            Toast.makeText(this,"Logare reusita!",Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this,"User sau parola gresita!",Toast.LENGTH_SHORT).show();
            return;

        }

        Intent intent = new Intent(this, BottomNavigationActivity.class);
        startActivity(intent);

    }


    public void addToDatabase(View view) {
        final TextInputEditText nameInputEditText;
        final TextInputEditText passwordInputEditText;
        TextInputLayout textInputLayoutUserName;
        TextInputLayout textInputLayoutPassword;
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        new AlertDialog.Builder(this, R.style.InputDialogTheme);
        View viewInflatedUserName = LayoutInflater.from(this).inflate(R.layout.user_data_base_dialog, (ViewGroup) findViewById(R.id.username_input_dialog) , false);
        View viewInflatedPassword = LayoutInflater.from(this).inflate(R.layout.user_data_base_dialog, (ViewGroup) findViewById(R.id.password_input_dialog) , false);
        nameInputEditText = viewInflatedUserName.findViewById(R.id.username_input_dialog);
        passwordInputEditText = viewInflatedPassword.findViewById(R.id.password_input_dialog);
        textInputLayoutUserName = viewInflatedUserName.findViewById(R.id.tilUserName_input_dialog);
        textInputLayoutPassword = viewInflatedPassword.findViewById(R.id.tilPassword_input_dialog);
        alert.setView(viewInflatedUserName);
        alert.setTitle("Register in App");
        textInputLayoutUserName.setHint("UserName");

        alert.setPositiveButton("Register", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                if (nameInputEditText.getText() == null || nameInputEditText.getText().toString().isEmpty())
                {
                    return;
                }
                getFromDatabase(nameInputEditText.getText().toString());
                userList = null;
                while(userList == null);
                if(userList.size() == 1){
                    Toast.makeText(getBaseContext(),"Nume de utilizator indisponibil!",Toast.LENGTH_SHORT).show();
                    return;

                }
                userName = nameInputEditText.getText().toString();

                textInputLayoutPassword.setVisibility(View.VISIBLE);
                alert.setView(viewInflatedPassword);
                alert.setTitle("Register in App");
                textInputLayoutPassword.setHint("Password");

                alert.setPositiveButton("Register", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (passwordInputEditText.getText() == null || passwordInputEditText.getText().toString().isEmpty())
                        {
                            return;
                        }
                        password = passwordInputEditText.getText().toString();

                        AppExecutors.getInstance().diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                insertToDatabase(userName,password);
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
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        alert.show();
    }

    private void insertToDatabase(final String name,final String password)
    {
        class InsertValue extends AsyncTask<Void, Void, User> {

            @Override
            protected User doInBackground(Void... voids) {
                User user = new User(name, password);
                userDatabase.userDao().insertAllUsers(user);
                return user;
            }

            @Override
            protected void onPostExecute(User user) {
                super.onPostExecute(user);
            }
        }

        InsertValue insertTask = new InsertValue();
        insertTask.execute();
    }

    public void getFromDatabase(String name) {
        class GetValue extends AsyncTask<Void, Void, User> {

            @Override
            protected User doInBackground(Void... voids) {
                User user = new User(userName, password);
                userList = userDatabase.userDao().findByUserName(name);
                return user;
            }

            @Override
            protected void onPostExecute(User user) {
                super.onPostExecute(user);
            }
        }

        GetValue insertTask = new GetValue();
        insertTask.execute();
    }

}
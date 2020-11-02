package com.example.tema.LogInSystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.tema.LogInSystem.UserDataBase.User;
import com.example.tema.LogInSystem.UserDataBase.UserDatabase;
import com.example.tema.R;


import java.util.List;

public class LogInActivity extends AppCompatActivity {
    private Button logInButton, saveButton, continueButton;
    private LinearLayout sharedPreferencesLayout;

    private UserDatabase userDatabase;
    private List<User> userList;

    private String userNameLogIn;
    private String passwordLogIn;
    private EditText userNameInput, passwordInput;
    String userNameData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        initializeViews();
        setOnClickListeners();
        setInitialData();

    }

    private void initializeViews(){
        logInButton = findViewById(R.id.logInBtn);
        userDatabase = UserDatabase.getInstance(this);
        userNameInput = findViewById(R.id.userNameInput);
        passwordInput = findViewById(R.id.passwordInput);
        saveButton = findViewById(R.id.saveBtn);
        continueButton = findViewById(R.id.continueBtn);
        sharedPreferencesLayout = findViewById(R.id.spLayout);

    }

    private void setOnClickListeners(){
        logInButton.setOnClickListener(v -> bottomNavigationActivity());
        continueButton.setOnClickListener(v -> continueButtonAction());
        saveButton.setOnClickListener(v -> saveAccount());

    }

    private void setInitialData(){
        SharedPreferences prefs = getSharedPreferences("preferences.txt", MODE_PRIVATE);
        userNameData = prefs.getString("username","User not found!");
        String passwordData = prefs.getString("password","Password not found!");
        if(!userNameData.equals("User not found!")){
            userNameInput.setText(userNameData);
            passwordInput.setText(passwordData);
        }

    }

    private void bottomNavigationActivity(){
        userList = null;

        userNameLogIn = userNameInput.getText().toString();
        passwordLogIn = passwordInput.getText().toString();


        if(userNameLogIn.isEmpty()){
            Toast.makeText(this,"Introduceti un nume de utilizator!",Toast.LENGTH_SHORT).show();
            return;
        }


        if(passwordLogIn.isEmpty()){
            Toast.makeText(this,"Introduceti o parola!",Toast.LENGTH_SHORT).show();
            return;
        }

        getFromDatabaseLogIn(userNameLogIn);
    }

    public void bottomNavigationActivityLastPart(){
        if(userList.size() == 0){
            Toast.makeText(this,"User sau parola gresita!",Toast.LENGTH_SHORT).show();
            return;

        }
        String userPassword = userList.get(0).getPassword().toString();
        if(userPassword.equals(passwordLogIn)){
            Toast.makeText(this,"Logare reusita!",Toast.LENGTH_SHORT).show();
            Log.d(userPassword,passwordLogIn);
        }else{
            Toast.makeText(this,"User sau parola gresita!",Toast.LENGTH_SHORT).show();
            return;

        }

        if(userNameData.equals(userNameInput.getText().toString())){
            continueButtonAction();
            return;
        }

        sharedPreferencesLayout.setVisibility(View.VISIBLE);

    }

    public void getFromDatabaseLogIn(String name) {
        class GetValueLogIn extends AsyncTask<Void, Void, User> {

            @Override
            protected User doInBackground(Void... voids) {
                User user = new User(userNameLogIn, passwordLogIn,"","","",0);
                userList = userDatabase.userDao().findByUserName(name);
                return user;
            }

            @Override
            protected void onPostExecute(User user) {
                super.onPostExecute(user);
                bottomNavigationActivityLastPart();
            }
        }
        GetValueLogIn insertTask = new GetValueLogIn();
        insertTask.execute();

    }



    public void continueButtonAction(){
        String userName;
        userName = userList.get(0).getUserName();
        Intent intent = new Intent(this, BottomNavigationActivity.class);
        intent.putExtra("user",userName);
        startActivity(intent);
        sharedPreferencesLayout.setVisibility(View.INVISIBLE);

    }

    public void saveAccount(){
        String userNameValue = userNameInput.getText().toString();
        String passwordValue = passwordInput.getText().toString();
        SharedPreferences.Editor editor = getSharedPreferences("preferences.txt", MODE_PRIVATE).edit();
        editor.putString("username",userNameValue);
        editor.putString("password",passwordValue);
        editor.apply();
        Toast.makeText(this,"Contul a fost retinut cu succes!",Toast.LENGTH_SHORT).show();

    }

}
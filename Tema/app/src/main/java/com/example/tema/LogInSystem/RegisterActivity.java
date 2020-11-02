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
import com.example.tema.LogInSystem.UserDataBase.User;
import com.example.tema.LogInSystem.UserDataBase.UserDatabase;
import com.example.tema.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class RegisterActivity extends AppCompatActivity {
    private AlertDialog.Builder alert;
    TextInputLayout textInputLayoutUserName;
    TextInputLayout textInputLayoutPassword;
    View viewInflatedUserName;
    View viewInflatedPassword;
    TextInputEditText nameInputEditText;
    TextInputEditText passwordInputEditText;

    String firstNameText, lastNameText, emailText;
    int ageTextInt;
    EditText firstNameInput, lastNameInput, emailInput, ageInput;

    private UserDatabase userDatabase;
    private List<User> userList;

    private String userNameRegister;
    private String passwordRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initializeViews();

    }

    private void initializeViews(){

        userDatabase = UserDatabase.getInstance(this);
        alert = new AlertDialog.Builder(this);
        new AlertDialog.Builder(this, R.style.InputDialogTheme);
        firstNameInput = findViewById(R.id.firstNameInput);
        lastNameInput = findViewById(R.id.lastNameInput);
        emailInput = findViewById(R.id.emailInput);
        ageInput = findViewById(R.id.ageInput);

    }


    public void addToDatabase(View view) {
        firstNameText = firstNameInput.getText().toString();
        lastNameText = lastNameInput.getText().toString();
        emailText = emailInput.getText().toString();
        ageTextInt = Integer.parseInt(ageInput.getText().toString());

        viewInflatedUserName = LayoutInflater.from(this).inflate(R.layout.user_data_base_dialog, (ViewGroup) findViewById(R.id.username_input_dialog) , false);
        viewInflatedPassword = LayoutInflater.from(this).inflate(R.layout.user_data_base_dialog, (ViewGroup) findViewById(R.id.password_input_dialog) , false);

        textInputLayoutUserName = viewInflatedUserName.findViewById(R.id.tilUserName_input_dialog);
        textInputLayoutPassword = viewInflatedPassword.findViewById(R.id.tilPassword_input_dialog);

        nameInputEditText = viewInflatedUserName.findViewById(R.id.username_input_dialog);
        passwordInputEditText = viewInflatedPassword.findViewById(R.id.password_input_dialog);

        alert.setView(viewInflatedUserName);
        alert.setTitle("Register in App");
        textInputLayoutUserName.setHint("UserName");

        alert.setPositiveButton("Register", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                if (nameInputEditText.getText() == null || nameInputEditText.getText().toString().isEmpty())
                {
                    return;
                }
                userNameRegister = nameInputEditText.getText().toString();
                getFromDatabaseRegister(userNameRegister);



            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        alert.show();
    }

    private int userNameRegisterCheck(){
        if(userList.size() == 1){
            Toast.makeText(getBaseContext(),"Nume de utilizator indisponibil!",Toast.LENGTH_SHORT).show();
            return 1;

        }

        return 0;

    }

    private void passwordRegisterInput(){
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
                passwordRegister = passwordInputEditText.getText().toString();

                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        insertToDatabase(userNameRegister,passwordRegister, firstNameText, lastNameText, emailText, ageTextInt);
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

    private void insertToDatabase(final String name,final String password, final String firstName, final String lastName, final String email, final int age)
    {
        class InsertValue extends AsyncTask<Void, Void, User> {

            @Override
            protected User doInBackground(Void... voids) {
                User user = new User(name, password, firstName, lastName, email, age);
                userDatabase.userDao().insertAllUsers(user);
                return user;
            }

            @Override
            protected void onPostExecute(User user) {
                super.onPostExecute(user);
                Toast.makeText(getBaseContext(),"Contul a fost creat cu succes!",Toast.LENGTH_SHORT).show();
            }
        }

        InsertValue insertTask = new InsertValue();
        insertTask.execute();
    }

    public void getFromDatabaseRegister(String name) {
        class GetValueRegister extends AsyncTask<Void, Void, User> {

            @Override
            protected User doInBackground(Void... voids) {
                User user = new User(userNameRegister,passwordRegister,firstNameText,lastNameText,emailText,ageTextInt);
                userList = userDatabase.userDao().findByUserName(name);
                return user;
            }

            @Override
            protected void onPostExecute(User user) {
                super.onPostExecute(user);
                if(userNameRegisterCheck() == 1)
                    return;
                passwordRegisterInput();
            }
        }

        GetValueRegister insertTask = new GetValueRegister();
        insertTask.execute();
    }

}
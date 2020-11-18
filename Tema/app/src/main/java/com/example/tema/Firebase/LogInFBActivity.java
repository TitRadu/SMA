package com.example.tema.Firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tema.LogInSystem.UserDataBase.UserDatabase;
import com.example.tema.PieceDataBase.DataBaseActivity;
import com.example.tema.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogInFBActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button logInButton, registerButton;
    private String emailLogIn, passwordLogIn;
    private EditText emailInput, passwordInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_f_b);
        mAuth = FirebaseAuth.getInstance();
        initializeViews();
        setOnClickListeners();

    }

    private void initializeViews(){
        logInButton = findViewById(R.id.logInBtn);
        registerButton = findViewById(R.id.registerBtn);
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);

    }

    private void setOnClickListeners(){
        logInButton.setOnClickListener(v -> LogIn());
        registerButton.setOnClickListener(v-> registerActivity());

    }

    private void registerActivity(){
        Intent intent = new Intent(this, RegisterFBActivity.class);
        startActivity(intent);

    }

    private void LogIn(){
        emailLogIn = emailInput.getText().toString();
        passwordLogIn = passwordInput.getText().toString();


        if(emailLogIn.isEmpty()){
            Toast.makeText(this,"Introduce an email!",Toast.LENGTH_SHORT).show();
            return;
        }


        if(passwordLogIn.isEmpty()){
            Toast.makeText(this,"Introduce a password!",Toast.LENGTH_SHORT).show();
            return;
        }

        signIn(emailLogIn, passwordLogIn);

    }

    private void signIn(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(getBaseContext(), "Succes!", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getBaseContext(), "Email or password incorrect!", Toast.LENGTH_SHORT).show();

                        }

                    }
                });

    }

    private void updateUI(FirebaseUser firebaseUser){
        Intent intent = new Intent(this, LoggedActivity.class);
        intent.putExtra("email", emailLogIn);
        startActivity(intent);

    }

}
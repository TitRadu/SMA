package com.example.tema.Firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tema.AppExecutors;
import com.example.tema.LogInSystem.UserDataBase.UserDatabase;
import com.example.tema.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.UUID;

public class RegisterFBActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseHelper firebaseHelper;

    private AlertDialog.Builder alert;
    TextInputLayout textInputLayoutUserName;
    TextInputLayout textInputLayoutPassword;
    View viewInflatedEmail;
    View viewInflatedPassword;
    TextInputEditText emailInputEditText;
    TextInputEditText passwordInputEditText;

    private String emailRegister;
    private String passwordRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_f_b);
        mAuth = FirebaseAuth.getInstance();
        firebaseHelper = FirebaseHelper.getInstance();
        initializeViews();

    }

    private void initializeViews(){
        alert = new AlertDialog.Builder(this);
        new AlertDialog.Builder(this, R.style.InputDialogTheme);

    }

    public void register(View view) {
        viewInflatedEmail = LayoutInflater.from(this).inflate(R.layout.user_data_base_dialog, (ViewGroup) findViewById(R.id.username_input_dialog) , false);
        viewInflatedPassword = LayoutInflater.from(this).inflate(R.layout.user_data_base_dialog, (ViewGroup) findViewById(R.id.password_input_dialog) , false);

        textInputLayoutUserName = viewInflatedEmail.findViewById(R.id.tilUserName_input_dialog);
        textInputLayoutPassword = viewInflatedPassword.findViewById(R.id.tilPassword_input_dialog);

        emailInputEditText = viewInflatedEmail.findViewById(R.id.username_input_dialog);
        passwordInputEditText = viewInflatedPassword.findViewById(R.id.password_input_dialog);

        alert.setView(viewInflatedEmail);
        alert.setTitle("Register in App");
        textInputLayoutUserName.setHint("UserName");

        alert.setPositiveButton("Register", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                if (emailInputEditText.getText() == null || emailInputEditText.getText().toString().isEmpty())
                {
                    return;
                }
                emailRegister = emailInputEditText.getText().toString();
                passwordRegisterInput(emailRegister);

            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        alert.show();
    }

    private void passwordRegisterInput(String email){
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
                createAccount(email,passwordRegister);

            }

        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        alert.show();

    }

    private void createAccount(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            RegisteredUser registeredUser = new RegisteredUser(email);
                            firebaseHelper.userDatabaseReference.child(UUID.randomUUID().toString()).setValue(registeredUser);

                            Toast.makeText(getBaseContext(), "Inregistrare reusita!", Toast.LENGTH_SHORT).show();

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getBaseContext(), "Inregistrare esuata!", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }

}
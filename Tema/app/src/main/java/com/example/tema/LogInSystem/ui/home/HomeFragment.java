package com.example.tema.LogInSystem.ui.home;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.tema.LogInSystem.BottomNavigationActivity;
import com.example.tema.LogInSystem.UserDataBase.User;
import com.example.tema.LogInSystem.UserDataBase.UserDatabase;
import com.example.tema.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class HomeFragment extends Fragment {
    private UserDatabase userDatabase;
    private List<User> userList;

    TextView userNameView, firstNameView, lastNameView, emailView, ageView;
    EditText firstNameEdit, lastNameEdit, emailEdit, ageEdit;
    String userName;

    Button updateButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        initializeViews(root);
        obtainInitialData();
        setOnClickListeners();
        return root;
    }

    private void initializeViews(View root){
        userDatabase = UserDatabase.getInstance(getContext());
        userNameView = root.findViewById(R.id.userNameView);
        firstNameView = root.findViewById(R.id.firstNameView);
        lastNameView = root.findViewById(R.id.lastNameView);
        emailView = root.findViewById(R.id.emailView);
        ageView = root.findViewById(R.id.ageView);

        firstNameEdit = root.findViewById(R.id.firstNameEdit);
        lastNameEdit = root.findViewById(R.id.lastNameEdit);
        emailEdit = root.findViewById(R.id.emailEdit);
        ageEdit = root.findViewById(R.id.ageEdit);

        updateButton = root.findViewById(R.id.updateBtn);

    }

    private void setOnClickListeners(){
        updateButton.setOnClickListener(v -> update());

    }

    private void obtainInitialData(){
        userName = BottomNavigationActivity.userNameReceived;
        getFromDatabaseData(userName);

    }

    private void setData(){
        userNameView.setText("Current user-name:" + userList.get(0).getUserName());

        firstNameView.setText("Current first name:" + userList.get(0).getFirstName());

        lastNameView.setText("Current last name:" + userList.get(0).getLastName());

        emailView.setText("Current email:" + userList.get(0).getEmail());

        ageView.setText("Current age:" + userList.get(0).getAge());

    }

    public void update(){
        String firstNameToUpdate = firstNameEdit.getText().toString();
        if(firstNameToUpdate.isEmpty()){
            firstNameToUpdate = userList.get(0).getFirstName();

        }

        String lastNameToUpdate = lastNameEdit.getText().toString();
        if(lastNameToUpdate.isEmpty()){
            lastNameToUpdate = userList.get(0).getLastName();

        }


        String emailToUpdate = emailEdit.getText().toString();
        if(emailToUpdate.isEmpty()){
            emailToUpdate = userList.get(0).getEmail();

        }


        String ageToUpdate = ageEdit.getText().toString();
        int ageToUpdateInt;
        if(ageToUpdate.isEmpty()){
            ageToUpdateInt = userList.get(0).getAge();

        }else{
            ageToUpdateInt = Integer.parseInt(ageToUpdate);

        }

        updateDatabaseAllFields(userName,firstNameToUpdate,lastNameToUpdate,emailToUpdate,ageToUpdateInt);

    }

    public void getFromDatabaseData(String name) {
        class GetValue extends AsyncTask<Void, Void, User> {

            @Override
            protected User doInBackground(Void... voids) {
                User user = new User("","","","","",0);
                userList = userDatabase.userDao().findByUserName(name);
                return user;
            }

            @Override
            protected void onPostExecute(User user) {
                super.onPostExecute(user);
                setData();

            }
        }

        GetValue insertTask = new GetValue();
        insertTask.execute();
    }

    public void updateDatabaseAllFields(String userName, String firstName, String lastName, String email, int age) {
        class GetValue extends AsyncTask<Void, Void, User> {

            @Override
            protected User doInBackground(Void... voids) {
                User user = new User("","","","","",0);
                userDatabase.userDao().updateFirstName(userName, firstName, lastName, email, age);
                return user;
            }

            @Override
            protected void onPostExecute(User user) {
                super.onPostExecute(user);
                getFromDatabaseData(userName);

            }
        }

        GetValue insertTask = new GetValue();
        insertTask.execute();
    }

}
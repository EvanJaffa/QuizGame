package com.example.login_sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class Register extends AppCompatActivity implements View.OnClickListener{

    //Standard procedure for declarations of all objects for our views
    Button registerButton;
    EditText etFName, etLName, etDOB, etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Standard procedure for assigning each View object to its appropriate view in the UI
        etFName = (EditText)findViewById(R.id.editTextFName);
        etLName = (EditText)findViewById(R.id.editTextLName);
        etDOB = (EditText)findViewById(R.id.editTextDOB);
        etEmail = (EditText)findViewById(R.id.editTextUsername);
        etPassword = (EditText)findViewById(R.id.editTextPassword);
        registerButton = (Button)findViewById(R.id.button);

        registerButton.setOnClickListener(this);

    }

    //The OnClick method that handles Register button
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button:
                if(!inputRequirements()){
                    break;
                }
                registerUser();
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }

    // Utility method used to detect empty EditText fields
    public static boolean isEmpty(EditText myEditText) {
        return myEditText.getText().toString().trim().length() == 0;
    }

    boolean inputRequirements(){

        if(!allFieldsFull() || !nameRequirements() || !Login.emailRegexTest(etEmail.getText().toString())){   //Check to make sure no EditTexts are empty
            return false;
        }
        return true;
    }

    // allFieldsFull() uses the isEmpty() method to make sure no EditTexts are empty before the user submits the form
    public boolean allFieldsFull(){

        if(isEmpty(etFName)){
            Toast.makeText(Register.this, "Please enter a first name.", Toast.LENGTH_LONG).show();
            return false;
        }
        else if(isEmpty(etLName)){
            Toast.makeText(Register.this, "Please enter a last name.", Toast.LENGTH_LONG).show();
            return false;
        }
        else if(isEmpty(etDOB)){
            Toast.makeText(Register.this, "Please enter a date of birth.", Toast.LENGTH_LONG).show();
            return false;
        }
        else if(isEmpty(etEmail)){
            Toast.makeText(Register.this, "Please enter an email address.", Toast.LENGTH_LONG).show();
            return false;
        }
        else if(isEmpty(etPassword)){
            Toast.makeText(Register.this, "Please enter a password.", Toast.LENGTH_LONG).show();
            return false;
        }
        else{
            return true;
        }
    }

    boolean nameRequirements(){
        if(etFName.getText().toString().length() < 3) {
            Toast.makeText(Register.this, "First name cannot be less than 3 characters", Toast.LENGTH_LONG).show();
        }
        else if(etFName.getText().toString().length() > 30){
            Toast.makeText(Register.this, "First name cannot be more than 30 characters", Toast.LENGTH_LONG).show();
        }
        else if(etLName.getText().toString().length() > 30){
            Toast.makeText(Register.this, "Last name cannot be more than 30 characters", Toast.LENGTH_LONG).show();
        }
        return true;
    }

    public void registerUser(){
        String fName = etFName.getText().toString();
        String lName = etFName.getText().toString();
        int dob = Integer.parseInt(etDOB.getText().toString());
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        User newlyRegisteredUser = new User(fName, lName, dob, email, password);
    }
}
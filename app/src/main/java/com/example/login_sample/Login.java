package com.example.login_sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class Login extends AppCompatActivity implements View.OnClickListener {

    //Standard procedure for declarations of all objects for our views
    Button loginButton;
    EditText etEmail, etPassword;
    TextView regLink;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Standard procedure for assigning each View object to its appropriate view in the UI
        etEmail = (EditText)findViewById(R.id.editTextUsername);
        etPassword = (EditText)findViewById(R.id.editTextPassword);
        loginButton = (Button)findViewById(R.id.button);
        loginButton.setOnClickListener(this);
        regLink = (TextView)findViewById(R.id.register_link);
        regLink.setOnClickListener(this);
        userLocalStore = new UserLocalStore(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button:

                if(!inputRequirements()){
                    break;
                }


                User user = new User(etEmail.getText().toString(), etPassword.getText().toString());

                userLocalStore.storeUserData(user);
                userLocalStore.setUserLoggedIn(true);
                MainActivity.changeLoggedInStatus(true);
                startActivity(new Intent(this, MainActivity.class));
                Login.this.finish();
                break;
            case R.id.register_link:
                startActivity(new Intent(this, Register.class));
                break;
        }
    }

    public boolean inputRequirements(){
        if(!allFieldsFull()){
            return false;
        }
        if(!Login.emailRegexTest(etEmail.getText().toString())){
            Toast.makeText(Login.this, "Please enter a valid email address", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public boolean allFieldsFull(){
        if(Register.isEmpty(etEmail)){
            Toast.makeText(Login.this, "Please enter an email address.", Toast.LENGTH_LONG).show();
            return false;
        }
        else if(Register.isEmpty(etPassword)){
            Toast.makeText(Login.this, "Please enter a password.", Toast.LENGTH_LONG).show();
            return false;
        }
        else{
            return true;
        }
    }

    public static boolean emailRegexTest(String emailText){
        return emailText.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
    }
}
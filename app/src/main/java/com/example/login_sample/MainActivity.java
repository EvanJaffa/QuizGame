package com.example.login_sample;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //Standard procedure for declarations of all objects for our views
    Button loginButton, registerButton, playButton;
    EditText etFName, etLName, etDOB, etEmail;
    UserLocalStore userLocalStore;
    TextView textbar;
    //this static boolean will control whether this activity recognizes us as logged in or not
    static boolean loggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Standard procedure for assigning each View object to its appropriate view in the UI
        etFName = (EditText)findViewById(R.id.editTextFName);
        etLName = (EditText)findViewById(R.id.editTextLName);
        etDOB = (EditText)findViewById(R.id.editTextDOB);
        etEmail = (EditText)findViewById(R.id.editTextUsername);
        loginButton = (Button)findViewById(R.id.button);
        registerButton = (Button)findViewById(R.id.button2);
        playButton = (Button)findViewById(R.id.playButton);
        textbar = (TextView)findViewById(R.id.textView6);

        //Set the onClickListener for both the Login and Register buttons
        loginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);
        playButton.setOnClickListener(this);
        //Declare a userLocalStore instance for this activity
        userLocalStore = new UserLocalStore(this);
    }


    @Override
    protected void onStart(){
        super.onStart();

        //As the activity starts, we adjust the text of the Login button and the instructional TextView directly above it
        if(loggedIn){
            //If the Login activity has set the local boolean loggedin to TRUE, then
            User user = userLocalStore.getLoggedInUser();
            textbar.setText("You are logged in as\n" + user.email.toString()); //Set the TextView to display the email that was used to log in
            loginButton.setText("Log Out");  //Set the button to LOG OUT mode
        }
        else if(!loggedIn){
            //If the Login activity has set the local boolean loggedin to FALSE (or it is still the default value), then
            textbar.setText("Please log in or register below"); //Set the TextView to display a "Please login or register" message
            loginButton.setText("Log In");  //Set the button to LOG IN mode
            playButton.setVisibility(View.GONE);
        }

        /*  Deprecated method used while developing
        if(authenticate()){
            //displayUserDetails();
        }
         */

    }

    // This static method serves to allow any other activity to change this MainActivity's login status to TRUE or FALSE
    public static void changeLoggedInStatus(boolean input){
        if(input){
            loggedIn = true;
        } else{
            loggedIn = false;
        }
    }

    /*      Deprecated method used while developing
    private boolean authenticate(){
        return userLocalStore.getUserLoggedIn();
    }

     */

    // Deprecated method used while developing
    private void displayUserDetails(){
        User user = userLocalStore.getLoggedInUser();

        etFName.setText((user.firstName));
        etLName.setText((user.lastName));
        etDOB.setText((user.dob));
        etEmail.setText((user.email));
    }

    // Utility method used to detect empty EditText fields
    public boolean isEmpty(EditText myEditText) {
        return myEditText.getText().toString().trim().length() == 0;
    }

    //The OnClick method that handles both the Login and Register buttons
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button:
                startActivity(new Intent(this, Login.class));
                break;
            case R.id.button2:
                startActivity(new Intent(this, Register.class));
                break;
            case R.id.playButton:
                Log.i("running", "playButton detected a click");
                //createSampleDialog();
                Intent playIntent = new Intent(MainActivity.this, Rules.class);
                startActivity(playIntent);
                break;
        }
    }

    private void createSampleDialog() {
        AlertDialog.Builder alertD = new AlertDialog.Builder(this);
        alertD.setMessage("Are you sure you want to play?");
        alertD.setCancelable(false);
        alertD.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i("running", "alertD Yes clicked");
            }
        });
        alertD.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i("running", "alertD No clicked");
            }
        });
        alertD.create().show();
    }
}
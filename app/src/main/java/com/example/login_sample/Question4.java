package com.example.login_sample;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Question4 extends AppCompatActivity implements View.OnClickListener {
    Button responseBtn1, responseBtn2, responseBtn3, responseBtn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question4);

        //Standard procedure for declarations of all objects for our views
        responseBtn1 = (Button)findViewById(R.id.responseBtn1);
        responseBtn2 = (Button)findViewById(R.id.responseBtn2);
        responseBtn3 = (Button)findViewById(R.id.responseBtn3);
        responseBtn4 = (Button)findViewById(R.id.responseBtn4);
        //Setting the onClickListener for our 4 answer buttons
        responseBtn1.setOnClickListener(this);
        responseBtn2.setOnClickListener(this);
        responseBtn3.setOnClickListener(this);
        responseBtn4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // Each of the incorrect answer choices will launch createAnswerConfirmationA()
            case R.id.responseBtn2:
            case R.id.responseBtn3:
            case R.id.responseBtn4:
                createAnswerConfirmationA();
                break;
            // Meanwhile, the correct answer choice will launch createAnswerConfirmationB()
            case R.id.responseBtn1:
                createAnswerConfirmationB();
                break;
        }
    }

    // This method, createAnswerConfirmationA(), will run when an incorrect answer is selected
    private void createAnswerConfirmationA(){
        Log.i("running", "createAnswerConfirmationA() launched");
        //We will display an alert to the user to confirm their answer, constructed using AlertDialog.Builder
        AlertDialog.Builder confirmDlg = new AlertDialog.Builder(this);
        confirmDlg.setMessage("Are you sure? Click Yes to confirm your answer.");
        confirmDlg.setCancelable(false);
        //When the user clicks 'Yes', an overriding OnClick method will run
        confirmDlg.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i("running", "within createAnswerConfirmationA(), 'Yes' clicked");
                // We will launch into WrongActivity.class, as this method runs for all INCORRECT answer choices
                Intent wrongAnsIntent = new Intent(Question4.this, WrongAnswer.class);
                startActivity(wrongAnsIntent);
                Question4.this.finish();
            }
        });
        // If the user clicks 'No' in the confirmation box, the dialog simply closes with no extra effect.
        confirmDlg.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i("running", "within createAnswerConfirmationA(), 'No' clicked");
            }
        });
        confirmDlg.create().show();
    }

    // This method, createAnswerConfirmationA(), will run when an incorrect answer is selected
    private void createAnswerConfirmationB(){
        Log.i("running", "createAnswerConfirmationB() launched");
        //We will display an alert to the user to confirm their answer, constructed using AlertDialog.Builder
        AlertDialog.Builder confirmDlg = new AlertDialog.Builder(this);
        confirmDlg.setMessage("Are you sure? Click Yes to confirm your answer.");
        confirmDlg.setCancelable(false);
        //When the user clicks 'Yes', an overriding OnClick method will run
        confirmDlg.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i("running", "within createAnswerConfirmationB(), 'Yes' clicked");
                // We will display a Toast to acknowledge the correct answer, then launch into the next question
                Toast.makeText(Question4.this, "Correct!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(Question4.this, Question5.class));
                Question4.this.finish();
            }
        });
        // If the user clicks 'No' in the confirmation box, the dialog simply closes with no extra effect.
        confirmDlg.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i("running", "within createAnswerConfirmationB(), 'No' clicked");
            }
        });
        confirmDlg.create().show();
    }

    // Here we implement a simple confirmation dialog to appear when the user uses the back button
    @Override
    public void onBackPressed() {
        createReturnConfirmation();
    }

    // This method uses AlertDialog.builder to implement a confirmation dialog much like the above methods createAnswerConfirmationA() and createAnswerConfirmationB()
    private void createReturnConfirmation(){
        AlertDialog.Builder returnDlg = new AlertDialog.Builder(this);
        returnDlg.setMessage("Are you sure you want to go back?\nYou will be logged out.");
        returnDlg.setCancelable(false);
        returnDlg.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //If the user confirms this message, they will be logged out and returned to the main activity
                Log.i("running", "returnDlg Yes clicked");
                MainActivity.changeLoggedInStatus(false);
                Question4.super.onBackPressed();
            }
        });
        returnDlg.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i("running", "returnDlg No clicked");
            }
        });
        returnDlg.create().show();
    }
}
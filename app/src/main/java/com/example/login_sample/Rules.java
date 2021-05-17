package com.example.login_sample;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Rules extends AppCompatActivity implements View.OnClickListener{
    private static int TIMEOUT = 3000;
    Button playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        getSupportActionBar().hide();
        playButton = (Button)findViewById(R.id.playButton);
        playButton.setVisibility(View.INVISIBLE);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                playButton.setVisibility(View.VISIBLE);
            }

        }, TIMEOUT);
        playButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.playButton:
                startActivity(new Intent(this, Question1.class));
                Rules.this.finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        createReturnConfirmation();
    }

    private void createReturnConfirmation(){
        AlertDialog.Builder returnDlg = new AlertDialog.Builder(this);
        returnDlg.setMessage("Are you sure you want to go back?\nYou will be logged out.");
        returnDlg.setCancelable(false);
        returnDlg.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i("running", "returnDlg Yes clicked");
                MainActivity.changeLoggedInStatus(false);
                Rules.super.onBackPressed();
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
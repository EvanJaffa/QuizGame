package com.example.login_sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WrongAnswer extends AppCompatActivity implements View.OnClickListener{
    Button homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrong_answer);
        homeButton = (Button)findViewById(R.id.homeButton);
        homeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.homeButton:
                startActivity(new Intent(this, MainActivity.class));
                this.finish();
        }
    }
}
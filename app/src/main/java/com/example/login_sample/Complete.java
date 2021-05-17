package com.example.login_sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class Complete extends AppCompatActivity implements View.OnClickListener {
    private static int TIMEOUT = 2000;
    Button playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);

        getSupportActionBar().hide();
        playButton = (Button) findViewById(R.id.playButton);
        playButton.setVisibility(View.INVISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                playButton.setVisibility(View.VISIBLE);
            }

        }, TIMEOUT);
        playButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.playButton:
                startActivity(new Intent(this, MainActivity.class));
                Complete.this.finish();
                break;
        }
    }
}
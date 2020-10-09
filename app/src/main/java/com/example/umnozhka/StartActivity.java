package com.example.umnozhka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {
    Button buttonSettings, buttonGame, buttonGrades,buttonEnd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        buttonSettings = findViewById(R.id.buttonSettings);
        buttonGame = findViewById(R.id.buttonGame);
        buttonGrades = findViewById(R.id.buttonGrades);
        buttonEnd = findViewById(R.id.buttonEnd);

        buttonSettings.setOnClickListener(this);
        buttonGame.setOnClickListener(this);
        buttonGrades.setOnClickListener(this);
        buttonEnd.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

            switch (v.getId()) {
                case R.id.buttonSettings:
                    // вызов настроек
                    break;
                case R.id.buttonGame:
                    // игра

                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    break;
                case R.id.buttonGrades:
                    // журнал оценок
                    break;
                case R.id.buttonEnd:
                    this.finish();
                    break;

            }

    }
}
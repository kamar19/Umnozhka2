package com.example.umnozhka;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class FinishLeassonActivity extends AppCompatActivity implements View.OnClickListener {
    Button finishButtonSave, finishButtonTakeFoto;
    EditText finishStringUserName;
    TextView finishStringPrimerovTasks, finishStringActions, finishStringMultiplyNumbers;
//    MyLesson myLesson;
    LessonSummary lessonSummary; // на Основе myLesson и MySettings
//    SurfaceView surfaceView;
    ImageView finishImageView;
    private List<Bitmap> bitmaps;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_leasson);
        lessonSummary = MainActivity.lessonSummary;

        finishButtonSave = findViewById(R.id.finishButtonSave);
        finishButtonTakeFoto = findViewById(R.id.finishButtonTakeFoto);
        finishStringUserName = findViewById(R.id.finishStringUserName);
        finishStringPrimerovTasks = findViewById(R.id.finishStringPrimerovTasks);
        finishStringActions = findViewById(R.id.finishStringActions);
        finishStringMultiplyNumbers = findViewById(R.id.finishStringMultiplyNumbers);
        finishImageView= findViewById(R.id.finishImageView);

        if (lessonSummary.getImage().equals("noname")) {
//            lessonSummary.
//            finishImageView.setImageBitmap();
        }


        finishStringUserName.setText(lessonSummary.getNameUser());
        finishStringPrimerovTasks.setText(lessonSummary.getStringPrimerovTasks());
        finishStringActions.setText(lessonSummary.getStringMDSA());
        finishStringMultiplyNumbers.setText(lessonSummary.getStringMultiplyNumbers());

        finishButtonSave.setOnClickListener(this);
        finishButtonTakeFoto.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

    }
}
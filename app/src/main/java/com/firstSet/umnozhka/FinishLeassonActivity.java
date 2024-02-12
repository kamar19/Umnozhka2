package com.firstSet.umnozhka;

import static com.firstSet.umnozhka.GradebookActivity.nameDb;
import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FinishLeassonActivity extends AppCompatActivity implements View.OnClickListener {
    Button finishButtonSave;
    EditText finishStringUserName;
    TextView finishStringPrimerovTasks, finishStringActions, finishStringMultiplyNumbers, finishTextValueViewPoints, finishTextViewPoints;
    LessonSummary lessonSummary; // на Основе myLesson и MySettings
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_leasson);
        sharedPreferences =  PreferenceManager.getDefaultSharedPreferences(this);
        lessonSummary = new LessonSummary(sharedPreferences);

        finishButtonSave = findViewById(R.id.finishButtonSave);
        finishStringUserName = findViewById(R.id.finishStringUserName);
        finishStringPrimerovTasks = findViewById(R.id.finishStringPrimerovTasks);
        finishStringActions = findViewById(R.id.finishStringActions);
        finishStringMultiplyNumbers = findViewById(R.id.finishStringMultiplyNumbers);
        finishTextValueViewPoints = findViewById(R.id.finishTextValueViewPoints);
        finishTextViewPoints = findViewById(R.id.finishTextViewPoints);
        finishStringUserName.setText(lessonSummary.getNameUser());
        finishStringPrimerovTasks.setText(lessonSummary.getStringPrimerovTasks());
        finishStringActions.setText(lessonSummary.getStringMDSA());
        finishStringMultiplyNumbers.setText(lessonSummary.getStringMultiplyNumbers());
        finishTextValueViewPoints.setText(String.valueOf(lessonSummary.getCountPoints()));
        finishButtonSave.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.finishButtonSave:
                if (finishStringUserName.getText().length() > 0) {
                    lessonSummary.setNameUser(finishStringUserName.getText().toString());
                    DBHelper db = new DBHelper(nameDb, this);
                    db.saveLessonSummaryToDB(lessonSummary);
                    finish();
                } else
                    Toast.makeText(getApplicationContext(), R.string.finishLeassonActivityNotUserName,
                            Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

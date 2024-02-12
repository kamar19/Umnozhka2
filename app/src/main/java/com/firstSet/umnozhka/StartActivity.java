package com.firstSet.umnozhka;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import java.util.Locale;

public class StartActivity extends Activity implements OnClickListener {
    Button buttonSettings, buttonGame, buttonGrades, buttonEnd;
    SharedPreferences sharedPreferences;
    MySettings mySettings;
    MyLesson myLesson;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mySettings = new MySettings();
        myLesson = new MyLesson();

        if (sharedPreferences != null) {
            mySettings.loadValuesSettings(sharedPreferences);
            changeDisplayLanguage(mySettings.getSettingsLanguage());
        } else {
            // Первая загрузка значений по умолчанию
            // нужно проверить какая локаль в системе по умолчанию
            mySettings.setNewDefaultSettings();
            mySettings.setSettingsLanguage(getResources().getConfiguration().locale.getDisplayLanguage());
            mySettings.savePreferences(sharedPreferences);
        }
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

    private void changeDisplayLanguage(String langCode) {
        if (!getResources().getConfiguration().locale.getDisplayLanguage().equals(langCode))
        // Если текущая локаль и в параметрах локаль отличаются, то поменять локаль.
        {
            Locale locale = new Locale(langCode);
            Locale.setDefault(locale);
            Configuration configuration = new Configuration((getResources().getConfiguration()));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                configuration.setLocale(locale);
            } else {
                configuration.locale = locale;
            }
            getBaseContext().getResources()
                    .updateConfiguration(configuration,
                            getBaseContext()
                                    .getResources()
                                    .getDisplayMetrics());
        }
        Button buttonSettingsRef = findViewById(R.id.buttonSettings);
        if (buttonSettingsRef != null) {
            buttonSettingsRef.setText(R.string.buttonSettingsTitle);
            Button buttonGameRef = findViewById(R.id.buttonGame);
            buttonGameRef.setText(R.string.buttonGameTitle);
            Button buttonGradesRef = findViewById(R.id.buttonGrades);
            buttonGradesRef.setText(R.string.buttonGradesTitle);
            Button buttonEndRef = findViewById(R.id.buttonEnd);
            buttonEndRef.setText(R.string.buttonEndTitle);
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.buttonSettings:
                // вызов настроек
                Intent intent = new Intent(this, PrefActivity.class);
                startActivity(intent);
                break;
            case R.id.buttonGame:
                // Если игра не завершена
                // продолжить игру? начать новую?
                myLesson.loadValuesLesson(sharedPreferences);
                if (myLesson.isEndGame()==false) {
                    //Если урок не закончен
                    showDialog();
                } else
                //Если урок закончен, нужно начинать сначала

                {
                    //нужно передать обьект myLesson c начальными параметрами;
                    myLesson.startNewLesson();
                    myLesson.saveValuesMyLesson(sharedPreferences);

                    Intent intent2 = new Intent(this, MainActivity.class);
                    startActivity(intent2);
                }
                break;
            case R.id.buttonGrades:
                // журнал оценок
//                    setContentView(R.layout.activity_start);
                Intent intent3 = new Intent(this, GradebookActivity.class);
                startActivity(intent3);

                break;
            case R.id.buttonEnd:
                this.finish();
                break;
        }
    }

    private void showDialog() {
        CustomDialog dialogFragment = new CustomDialog();
        DialogInterface.OnClickListener okListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // продолжить игру
                Intent intent = new Intent( getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        };
        DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // начать занова
                myLesson.startNewLesson();
                myLesson.setEndGame(true);
                myLesson.saveValuesMyLesson(sharedPreferences);
                Intent intent = new Intent( context, MainActivity.class);
                startActivity(intent);
            }
        };
        DialogInterface.OnClickListener neutralListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        };

        dialogFragment.showDialog(okListener,cancelListener,neutralListener, context);
    }

    @Override
    public void onResume() {
        super.onResume();
        mySettings.loadValuesSettings(sharedPreferences);
        changeDisplayLanguage(mySettings.getSettingsLanguage());
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mySettings.savePreferences(sharedPreferences);
        super.onDestroy();
    }

    public MySettings getMySettings() {
        return mySettings;
    }

}
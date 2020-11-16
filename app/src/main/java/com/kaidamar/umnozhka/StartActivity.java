package com.kaidamar.umnozhka;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

//import androidx.fragment.app.DialogFragment;

import com.example.umnozhka.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StartActivity extends Activity implements OnClickListener {
    Button buttonSettings, buttonGame, buttonGrades, buttonEnd;
    boolean isSharedPreferences;

    public static SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public static final String STRING_COUNT_ALL_PRIMEROV = "StringCountAllPrimerov";

    //    DialogFragment dialogFragment;
    private static SharedPreferences sharedPreferences;
    public static MySettings mySettings;
    public static MyLesson myLesson;
    public static LessonSummary startLessonSummary;

    final String LOG_TAG = "StartActivityLogs";
//    public static Locale locale;
//    public static Configuration configuration;

    public static LessonSummary getStartLessonSummary() {
        return startLessonSummary;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        sharedPreferences =  getSharedPreferences(PREFERENCES_SETTINGS_NAME, MODE_PRIVATE);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mySettings = new MySettings(sharedPreferences);
        myLesson = new MyLesson();

        if (sharedPreferences != null) {
            // по умолчанию, если настроеки есть загружаются, иначе создаются
//            mySettings.loadValuesMySettings(sharedPreferences);
            changeDisplayLanguage(mySettings.getSettingsLanguage());
//            getResources().getConfiguration().locale.getDisplayLanguage().toString().
//            textView.setText(getResources().getConfiguration().locale.getDisplayLanguage().toString());
//            textView2.setText(SettingsLanguage);
//            Toast toast = Toast.makeText(getApplicationContext(),
//                    "Настройки загруженны!", Toast.LENGTH_SHORT);
//            toast.show();
////            textViewAnswerShow5.setVisibility(View.VISIBLE) ;
//            textViewAnswerShow5.setText(R.string.SettingsLoad);
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
                // вызов настроек для исключения лишних сохранений  SharedPreferences
                if (!mySettings.isSharedPreferences()) {
                    // если в первый раз
                    mySettings.setSharedPreferences(true);
                    mySettings.savePreferences(sharedPreferences);
                }
                Intent intent = new Intent(this, PrefActivity.class);
                startActivity(intent);
                break;
            case R.id.buttonGame:
                myLesson.loadValuesMyLesson(sharedPreferences);
//                if (myLesson.isEndGame() == false) {
                if (myLesson.isBeginGame() == true) {
                    // Три состояния,
                    // 1) сеанс начат, но не завершен,isEndGame==false,
                    // то предлагается диалог либо продолжаем либо новый урок
                    MyDialog dialogFragment = new MyDialog();
                    dialogFragment.show(getFragmentManager(), "tt");
//                        Log.d(LOG_TAG, "dialogFragment.show");
                } else
                    if (myLesson.isBeginFinishLeassonActivity()) {
                        // 2) Если сеанс закончен isEndGame=true, была вызвана FinishLeassonActivity
                        // Если была вызвана FinishLeassonActivity и при этом не сохранены результаты занятия
                        String stringDate = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
                        startLessonSummary = new LessonSummary(myLesson.getUserNameDefault(), stringDate, myLesson.getCountPoints(), mySettings.getStringMDSA(), mySettings.getStringMultiplyNumbers());
//                        myLesson.setEndGame(true);
                        Intent intent3 = new Intent(this, FinishLeassonActivity.class);
                        intent3.putExtra(STRING_COUNT_ALL_PRIMEROV, myLesson.getStringCountAllPrimerov());
                        startActivity(intent3);
//                        Log.d(LOG_TAG, "startActivity(FinishLeassonActivity)");
                    } else {
                        // 3) сеан был сохранен, Новое занятие
                        // нужно передать обьект myLesson c начальными параметрами;
                        myLesson.startNewLesson();//тут beginGame = true
                        myLesson.saveValuesMyLesson(sharedPreferences);
                        Intent intent2 = new Intent(this, MainActivity.class);
                        startActivity(intent2);
//                    Log.d(LOG_TAG, "startActivity(MainActivity) если ");
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
//        Log.d(LOG_TAG, "end of onClick");
        }


        @Override
        public void onResume () {
            super.onResume();
            mySettings.loadValuesMySettings(sharedPreferences);
            changeDisplayLanguage(mySettings.getSettingsLanguage());
        }

        @Override
        public void onPause () {
            //       savePreferences();
            super.onPause();
        }

        @Override
        public void onDestroy () {
            mySettings.savePreferences(sharedPreferences);
            super.onDestroy();
        }

        public MySettings getMySettings () {
            return mySettings;
        }

    }
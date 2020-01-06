package com.example.umnozhka;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    SharedPreferences sharedPreferences;
    private static final String PREFERENCES_SETTINGS_NAME = "umnozhka_Settings";
//    CheckBoxPreference chb3;
//    PreferenceCategory categ2;


    private static boolean SETTINGS_SUBTRAC;    // Сложение
    private static boolean SETTINGS_ADD;        // Вычитание
    private static boolean SETTINGS_MULTIPLY;   // Умножение
    private static boolean SETTINGS_DIVIDE;     // Деление
    private static int SETTINGS_ADD_RANGE_MIN;  // Начало диапозона сложения
    private static int SETTINGS_ADD_RANGE_MAX;  // Конец диапозона сложения
    private static boolean SETTINGS_MULTIPLY_1; // Умножение на 1
    private static boolean SETTINGS_MULTIPLY_2; // Умножение на 2
    private static boolean SETTINGS_MULTIPLY_3; //
    private static boolean SETTINGS_MULTIPLY_4; //
    private static boolean SETTINGS_MULTIPLY_5; //
    private static boolean SETTINGS_MULTIPLY_6; //
    private static boolean SETTINGS_MULTIPLY_7; //
    private static boolean SETTINGS_MULTIPLY_8; //
    private static boolean SETTINGS_MULTIPLY_9; //
    private static boolean SETTINGS_MULTIPLY_10;//
    private static int SETTINGS_TIME_BETWEEN_SESSIONS; // Время между сеансами
    private static int SETTINGS_COUNT_TASK;            // Задач в сеанс
    private static boolean SETTINGS_RECORD;            // На выживание (на рекорд)
    private static  int SETTINGS_TIME_TASK;         // Время на одну задачу
    private static  int SETTINGS_TIME_SESSION;      // Время на один сеанс, после уменьшается

    Button buttonDigit1,buttonDigit2,buttonDigit3,buttonDigit4,buttonDigit5,buttonDigit6,buttonDigit7,buttonDigit8,buttonDigit9,
            buttonDigit0,buttonEnter;
    TextView textViewAnswerShow1,textViewAnswerShow2,textViewAnswerShow3,textViewAnswerShow4,textViewAnswerShow5,textViewAnswerShow6,
            textViewAnswerShow7,textViewAnswerShow8,textViewAnswerShow9,textViewAnswerShow10,textViewAnswerShow11,textViewAnswerShow12,
            textViewQuestion, textViewAnswerShowBasic;
    int currentOneUnit,currentTwoUnit;

//    PrefActivity prefActivity = new PrefActivity();


    public int getCurrentOneUnit() {
        return currentOneUnit;
    }

    public void setCurrentOneUnit(int currentOneUnit) {
        this.currentOneUnit = currentOneUnit;
    }

    public int getCurrentTwoUnit() {
        return currentTwoUnit;
    }

    public void setCurrentTwoUnit(int currentTwoUnit) {
        this.currentTwoUnit = currentTwoUnit;
    }


    /** Called when the activity is first created. */




    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonDigit1 = findViewById(R.id.buttonDigit1);
        buttonDigit2 = findViewById(R.id.buttonDigit2);
        buttonDigit3 = findViewById(R.id.buttonDigit3);
        buttonDigit4 = findViewById(R.id.buttonDigit4);
        buttonDigit5 = findViewById(R.id.buttonDigit5);
        buttonDigit6 = findViewById(R.id.buttonDigit6);
        buttonDigit7 = findViewById(R.id.buttonDigit7);
        buttonDigit8 = findViewById(R.id.buttonDigit8);
        buttonDigit9 = findViewById(R.id.buttonDigit9);
        buttonDigit0 = findViewById(R.id.buttonDigit0);
        buttonEnter = findViewById(R.id.buttonEnter);
        textViewAnswerShow1 = findViewById(R.id.textViewAnswerShow1);
        textViewAnswerShow1.setText("");
        textViewAnswerShow2 = findViewById(R.id.textViewAnswerShow2);
        textViewAnswerShow2.setText("");
        textViewAnswerShow3 = findViewById(R.id.textViewAnswerShow3);
        textViewAnswerShow3.setText("");
        textViewAnswerShow4 = findViewById(R.id.textViewAnswerShow4);
        textViewAnswerShow4.setText("");
        textViewAnswerShow5 = findViewById(R.id.textViewAnswerShow5);
        textViewAnswerShow5.setText("");
        textViewAnswerShow6 = findViewById(R.id.textViewAnswerShow6);
        textViewAnswerShow6.setText("");
        textViewAnswerShow7 = findViewById(R.id.textViewAnswerShow7);
        textViewAnswerShow7.setText("");
        textViewAnswerShow8 = findViewById(R.id.textViewAnswerShow8);
        textViewAnswerShow8.setText("");
        textViewAnswerShow9 = findViewById(R.id.textViewAnswerShow9);
        textViewAnswerShow9.setText("");
        textViewAnswerShow10 = findViewById(R.id.textViewAnswerShow10);
        textViewAnswerShow10.setText("");
        textViewAnswerShow11 = findViewById(R.id.textViewAnswerShow11);
        textViewAnswerShow11.setText("");
        textViewAnswerShow12 = findViewById(R.id.textViewAnswerShow12);
        textViewAnswerShow12.setText("");
        textViewAnswerShowBasic = findViewById(R.id.textViewAnswerShowBasic);

        textViewQuestion  = findViewById(R.id.textViewQuestion);

        buttonDigit1.setOnClickListener(this);
        buttonDigit2.setOnClickListener(this);
        buttonDigit3.setOnClickListener(this);
        buttonDigit4.setOnClickListener(this);
        buttonDigit5.setOnClickListener(this);
        buttonDigit6.setOnClickListener(this);
        buttonDigit7.setOnClickListener(this);
        buttonDigit8.setOnClickListener(this);
        buttonDigit9.setOnClickListener(this);
        buttonDigit0.setOnClickListener(this);
        buttonEnter.setOnClickListener(this);

        sharedPreferences = getSharedPreferences(PREFERENCES_SETTINGS_NAME, Context.MODE_PRIVATE);

        if(sharedPreferences!= null) {
            // по умолчанию, если нет настроек создаются
//            Toast toast = Toast.makeText(getApplicationContext(),
//                    "Настройки Созданы!", Toast.LENGTH_SHORT);
//            toast.show();
            textViewAnswerShow5.setVisibility(View.VISIBLE) ;
            textViewAnswerShow5.setText(R.string.SettingsLoad);
            LoadPreferences();


        } else {

//            Toast toast = Toast.makeText(getApplicationContext(),
//                    "Настройки Загружены!", Toast.LENGTH_SHORT);
//            toast.show();
            // устанавливаем значение констант по умолчанию и сохраняем их в настройках
            SETTINGS_MULTIPLY = true;
            SETTINGS_DIVIDE = false;
            SETTINGS_SUBTRAC = false;
            SETTINGS_ADD = false;
            SETTINGS_ADD_RANGE_MIN = 1;
            SETTINGS_ADD_RANGE_MAX = 100;
            SETTINGS_MULTIPLY_1 = false;
            SETTINGS_MULTIPLY_2 = true;
            SETTINGS_MULTIPLY_3 = true;
            SETTINGS_MULTIPLY_4 = true;
            SETTINGS_MULTIPLY_5 = true;
            SETTINGS_MULTIPLY_6 = true;
            SETTINGS_MULTIPLY_7 = true;
            SETTINGS_MULTIPLY_8 = true;
            SETTINGS_MULTIPLY_9 = true;
            SETTINGS_MULTIPLY_10 = true;
//            sharedPreferences.getInt("SETTINGS_TIME_BETWEEN_SESSIONS", 100);
//            sharedPreferences.getInt("SETTINGS_COUNT_TASK", 12);
            SETTINGS_RECORD = true;
//            sharedPreferences.getInt("SETTINGS_TIME_TASK", 30);
//            sharedPreferences.getInt("SETTINGS_TIME_SESSION", 360);
            textViewAnswerShow5.setVisibility(View.VISIBLE) ;
            textViewAnswerShow5.setText(R.string.SettingsSave);

            SavePreferences();

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuItem mi = menu.add(0, 1, 0, R.string.menu_Preferences);

//        menu.add(0, 2, 0, R.string.menu_Exit);
//
//        mi.setIntent(new Intent(this, PrefActivity.class));
//        return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.um_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_Preferences :
//                item.setIntent(new Intent(this, PrefActivity.class));
                Intent intent = new Intent(this, PrefActivity.class);
                startActivity(intent);
                return true;
            case R.id.save_Exit:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        String tempQuestion= textViewAnswerShowBasic.getText().toString();
        switch (v.getId()){
            case R.id.buttonDigit1:
                textViewAnswerShowBasic.setText(tempQuestion+"1");
                break;
            case R.id.buttonDigit2:
                textViewAnswerShowBasic.setText(tempQuestion+"2");
                break;
            case R.id.buttonDigit3:
                textViewAnswerShowBasic.setText(tempQuestion+"3");
                break;
            case R.id.buttonDigit4:
                textViewAnswerShowBasic.setText(tempQuestion+"4");
                break;
            case R.id.buttonDigit5:
                textViewAnswerShowBasic.setText(tempQuestion+"5");
                break;
            case R.id.buttonDigit6:
                textViewAnswerShowBasic.setText(tempQuestion+"6");
                break;
            case R.id.buttonDigit7:
                textViewAnswerShowBasic.setText(tempQuestion+"7");
                break;
            case R.id.buttonDigit8:
                textViewAnswerShowBasic.setText(tempQuestion+"8");
                break;
            case R.id.buttonDigit9:
                textViewAnswerShowBasic.setText(tempQuestion+"9");
                break;
            case R.id.buttonDigit0:
                textViewAnswerShowBasic.setText(tempQuestion+"0");
                break;
            case R.id.buttonEnter:
                //выполняется проверка Ответа на математический Вопрос

//                textViewQuestion.setText(textViewQuestion.getText()+"1");
                break;
            case R.id.buttonBackSpace:
            {
                String tempSatring = tempQuestion.substring(0,tempQuestion.length()-2);
                textViewAnswerShowBasic.setText("1");
//                android:background="@drawable/baselinebackspaceblack18dp"
                break;
            }



        }
    }
    private void SavePreferences() {
        SharedPreferences.Editor editorSharedPreferences = sharedPreferences.edit();
        editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY", SETTINGS_MULTIPLY);
        editorSharedPreferences.putBoolean("SETTINGS_DIVIDE", SETTINGS_DIVIDE);
        editorSharedPreferences.putBoolean("SETTINGS_SUBTRAC", SETTINGS_SUBTRAC);
        editorSharedPreferences.putBoolean("SETTINGS_ADD", SETTINGS_ADD);
        editorSharedPreferences.putInt("SETTINGS_ADD_RANGE_MIN", SETTINGS_ADD_RANGE_MIN);
        editorSharedPreferences.putInt("SETTINGS_ADD_RANGE_MAX", SETTINGS_ADD_RANGE_MAX);
        editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY_1", SETTINGS_MULTIPLY_1);
        editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY_2", SETTINGS_MULTIPLY_2);
            editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY_3", SETTINGS_MULTIPLY_3);
            editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY_4", SETTINGS_MULTIPLY_4);
            editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY_5", SETTINGS_MULTIPLY_5);
            editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY_6", SETTINGS_MULTIPLY_6);
            editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY_7", SETTINGS_MULTIPLY_7);
            editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY_8", SETTINGS_MULTIPLY_8);
            editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY_9", SETTINGS_MULTIPLY_9);
            editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY_10", SETTINGS_MULTIPLY_10);
//            editorSharedPreferences.putBoolean("SETTINGS_TIME_BETWEEN_SESSIONS", SETTINGS_TIME_BETWEEN_SESSIONS);
//            editorSharedPreferences.putBoolean("SETTINGS_COUNT_TASK", SETTINGS_COUNT_TASK);
        editorSharedPreferences.putBoolean("SETTINGS_RECORD", SETTINGS_RECORD);
//            editorSharedPreferences.putBoolean("SETTINGS_TIME_TASK", SETTINGS_TIME_TASK);
//            editorSharedPreferences.putBoolean("SSETTINGS_TIME_SESSION", SETTINGS_TIME_SESSION);
//            editorSharedPreferences.putBoolean("SETTINGS_TIME_SESSION", SETTINGS_MULTIPLY_10);
        editorSharedPreferences.apply();

    }
    private void LoadPreferences() {
        // Думаю, что не нужно устанавливать в ручную переключатели и другие элементы в Preference Активности
        // Должны сами устанавливаться по значению констант настроек

        sharedPreferences.getBoolean("SETTINGS_MULTIPLY", true);
        sharedPreferences.getBoolean("SETTINGS_DIVIDE", false);
        sharedPreferences.getBoolean("SETTINGS_SUBTRAC", false);
        sharedPreferences.getBoolean("SETTINGS_ADD", false);
        sharedPreferences.getInt("SETTINGS_ADD_RANGE_MIN", 1);
        sharedPreferences.getInt("SETTINGS_ADD_RANGE_MAX", 100);
        sharedPreferences.getBoolean("SETTINGS_MULTIPLY_1", false);
        sharedPreferences.getBoolean("SETTINGS_MULTIPLY_2", true);
        sharedPreferences.getBoolean("SETTINGS_MULTIPLY_3", true);
        sharedPreferences.getBoolean("SETTINGS_MULTIPLY_4", true);
        sharedPreferences.getBoolean("SETTINGS_MULTIPLY_5", true);
        sharedPreferences.getBoolean("SETTINGS_MULTIPLY_6", true);
        sharedPreferences.getBoolean("SETTINGS_MULTIPLY_7", true);
        sharedPreferences.getBoolean("SETTINGS_MULTIPLY_8", true);
        sharedPreferences.getBoolean("SETTINGS_MULTIPLY_9", true);
        sharedPreferences.getBoolean("SETTINGS_MULTIPLY_10", false);
//            sharedPreferences.getInt("SETTINGS_TIME_BETWEEN_SESSIONS", 100);
//            sharedPreferences.getInt("SETTINGS_COUNT_TASK", 12);
        sharedPreferences.getBoolean("SETTINGS_RECORD", true);
//            sharedPreferences.getInt("SETTINGS_TIME_TASK", 30);
//            sharedPreferences.getInt("SETTINGS_TIME_SESSION", 360);

    }


}


package com.example.umnozhka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {
    Button buttonSettings, buttonGame, buttonGrades,buttonEnd;
    private static SharedPreferences sharedPreferences;
    private static final String PREFERENCES_SETTINGS_NAME = "umnozhka_Settings";
    public static boolean[] SETTINGS_MULTIPLYS = {false, false, false, false, false, false, false, false, false, false};
    public static boolean SETTINGS_SUBTRAC;    // Сложение
    public static boolean SETTINGS_ADD;        // Вычитание
    public static boolean SETTINGS_MULTIPLY;   // Умножение
    public static boolean SETTINGS_DIVIDE;     // Деление
    public static int SETTINGS_ADD_RANGE_MIN;  // Начало диапозона сложения
    public static int SETTINGS_ADD_RANGE_MAX;  // Конец диапозона сложения
    public static boolean SETTINGS_RECORD;            // На выживание (на рекорд)



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

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        if (sharedPreferences != null) {
            // по умолчанию, если настроеки есть загружаются, иначе создаются

            loadPreferences();
//            Toast toast = Toast.makeText(getApplicationContext(),
//                    "Настройки загруженны!", Toast.LENGTH_SHORT);
//            toast.show();
////            textViewAnswerShow5.setVisibility(View.VISIBLE) ;
//            textViewAnswerShow5.setText(R.string.SettingsLoad);
        } else {
            SETTINGS_MULTIPLY = true;
            SETTINGS_DIVIDE = false;
            SETTINGS_SUBTRAC = false;
            SETTINGS_ADD = false;
            SETTINGS_ADD_RANGE_MIN = 1;
            SETTINGS_ADD_RANGE_MAX = 100;
            SETTINGS_MULTIPLYS[0] = false;
            SETTINGS_MULTIPLYS[1] = true;
            SETTINGS_MULTIPLYS[2] = true;
            SETTINGS_MULTIPLYS[3] = true;
            SETTINGS_MULTIPLYS[4] = true;
            SETTINGS_MULTIPLYS[5] = true;
            SETTINGS_MULTIPLYS[6] = true;
            SETTINGS_MULTIPLYS[7] = true;
            SETTINGS_MULTIPLYS[8] = true;
            SETTINGS_MULTIPLYS[9] = true;
             savePreferences();
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
                    // игра
                    Intent intent2 = new Intent(this, MainActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.buttonGrades:
                    // журнал оценок
                    break;
                case R.id.buttonEnd:
                    this.finish();
                    break;

            }

    }
    private void loadPreferences() {
        // Думаю, что не нужно устанавливать в ручную переключатели и другие элементы в Preference Активности
        // Должны сами устанавливаться по значению констант настроек
        SETTINGS_MULTIPLY = sharedPreferences.getBoolean("SETTINGS_MULTIPLY", true);
        SETTINGS_DIVIDE = sharedPreferences.getBoolean("SETTINGS_DIVIDE", false);
        SETTINGS_SUBTRAC = sharedPreferences.getBoolean("SETTINGS_SUBTRAC", false);
        SETTINGS_ADD = sharedPreferences.getBoolean("SETTINGS_ADD", false);
        SETTINGS_MULTIPLYS[0] = sharedPreferences.getBoolean("SETTINGS_MULTIPLY_1", false);
        SETTINGS_MULTIPLYS[1] = sharedPreferences.getBoolean("SETTINGS_MULTIPLY_2", true);
        SETTINGS_MULTIPLYS[2] = sharedPreferences.getBoolean("SETTINGS_MULTIPLY_3", true);
        SETTINGS_MULTIPLYS[3] = sharedPreferences.getBoolean("SETTINGS_MULTIPLY_4", true);
        SETTINGS_MULTIPLYS[4] = sharedPreferences.getBoolean("SETTINGS_MULTIPLY_5", true);
        SETTINGS_MULTIPLYS[5] = sharedPreferences.getBoolean("SETTINGS_MULTIPLY_6", true);
        SETTINGS_MULTIPLYS[6] = sharedPreferences.getBoolean("SETTINGS_MULTIPLY_7", true);
        SETTINGS_MULTIPLYS[7] = sharedPreferences.getBoolean("SETTINGS_MULTIPLY_8", true);
        SETTINGS_MULTIPLYS[8] = sharedPreferences.getBoolean("SETTINGS_MULTIPLY_9", true);
        SETTINGS_MULTIPLYS[9] = sharedPreferences.getBoolean("SETTINGS_MULTIPLY_10", true);

        // НУЖНА проверка на сисловое или строковое значение
        // была ошибка когда значение было по по умолчанию = "0"
        // я поставил преобразование из троки в число
        // потом поменял значение на другое и ошибка вышла
        // на попытку преобразования числа в число, ну или я так понял

        SETTINGS_ADD_RANGE_MIN = Integer.valueOf(sharedPreferences.getString("SETTINGS_ADD_RANGE_MIN", "1"));
        SETTINGS_ADD_RANGE_MAX = Integer.valueOf(sharedPreferences.getString("SETTINGS_ADD_RANGE_MAX", "100"));

        SETTINGS_RECORD = sharedPreferences.getBoolean("SETTINGS_RECORD", true);
       }


    private void savePreferences() {
        SharedPreferences.Editor editorSharedPreferences = sharedPreferences.edit();
//        sharedPreferences.edit();
//        sharedPreferences.
        editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY", SETTINGS_MULTIPLY);
        editorSharedPreferences.putBoolean("SETTINGS_DIVIDE", SETTINGS_DIVIDE);
        editorSharedPreferences.putBoolean("SETTINGS_SUBTRAC", SETTINGS_SUBTRAC);
        editorSharedPreferences.putBoolean("SETTINGS_ADD", SETTINGS_ADD);
        editorSharedPreferences.putString("SETTINGS_ADD_RANGE_MIN", String.valueOf(SETTINGS_ADD_RANGE_MIN));
        editorSharedPreferences.putString("SETTINGS_ADD_RANGE_MAX", String.valueOf(SETTINGS_ADD_RANGE_MAX));
        editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY_1", SETTINGS_MULTIPLYS[0]);
        editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY_2", SETTINGS_MULTIPLYS[1]);
        editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY_3", SETTINGS_MULTIPLYS[2]);
        editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY_4", SETTINGS_MULTIPLYS[3]);
        editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY_5", SETTINGS_MULTIPLYS[4]);
        editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY_6", SETTINGS_MULTIPLYS[5]);
        editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY_7", SETTINGS_MULTIPLYS[6]);
        editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY_8", SETTINGS_MULTIPLYS[7]);
        editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY_9", SETTINGS_MULTIPLYS[8]);
        editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY_10", SETTINGS_MULTIPLYS[9]);
//            editorSharedPreferences.putBoolean("SETTINGS_TIME_BETWEEN_SESSIONS", SETTINGS_TIME_BETWEEN_SESSIONS);
//            editorSharedPreferences.putBoolean("SETTINGS_COUNT_TASK", SETTINGS_COUNT_TASK);
        editorSharedPreferences.putBoolean("SETTINGS_RECORD", SETTINGS_RECORD);
//            editorSharedPreferences.putBoolean("SETTINGS_TIME_TASK", SETTINGS_TIME_TASK);
//            editorSharedPreferences.putBoolean("SSETTINGS_TIME_SESSION", SETTINGS_TIME_SESSION);
//            editorSharedPreferences.putBoolean("SETTINGS_TIME_SESSION", SETTINGS_MULTIPLY_10);
//        editorSharedPreferences.putInt("PREFERENCES_SETTINGS_HEARTSLIVECOUNT", PREFERENCES_SETTINGS_HEARTSLIVECOUNT);
//        editorSharedPreferences.putInt("CURRENT_HEARTSLIVECOUNT", heartLiveCount);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            editorSharedPreferences.apply();
        } else editorSharedPreferences.commit();

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadPreferences();

    }

    @Override
    protected void onDestroy() {
        savePreferences();
        super.onDestroy();
    }
    public static int getMinValue_SETTINGS_MULTIPLY() {
        // Пусть нижняя граница имеет придел, тогда
        // верхняя, должна вычисляться по количеству игровых чисел
        //
        if (SETTINGS_MULTIPLYS[0]) {
            return 1;
        } else if (SETTINGS_MULTIPLYS[1]) {
            return 2;
        } else if (SETTINGS_MULTIPLYS[2]) {
            return 3;
        } else if (SETTINGS_MULTIPLYS[3]) {
            return 4;
        } else if (SETTINGS_MULTIPLYS[4]) {
            return 5;
        } else if (SETTINGS_MULTIPLYS[5]) {
            return 6;
        } else if (SETTINGS_MULTIPLYS[6]) {
            return 7;
        } else if (SETTINGS_MULTIPLYS[7]) {
            return 8;
        } else if (SETTINGS_MULTIPLYS[8]) {
            return 9;
        } else return 10;
    }
    public static int getMaxValue_SETTINGS_MULTIPLY() {
        // Пусть нижняя граница имеет придел, тогда
        // верхняя, должна вычисляться по количеству игровых чисел
        int maxValue = 0;
        //Считаем коилчество значений.
        if (SETTINGS_MULTIPLYS[9]) maxValue = 10;
        else if (SETTINGS_MULTIPLYS[8]) maxValue = 9;
        else if (SETTINGS_MULTIPLYS[7]) maxValue = 8;
        else if (SETTINGS_MULTIPLYS[6]) maxValue = 7;
        else if (SETTINGS_MULTIPLYS[5]) maxValue = 6;
        else if (SETTINGS_MULTIPLYS[4]) maxValue = 5;
        else if (SETTINGS_MULTIPLYS[3]) maxValue = 4;
        else if (SETTINGS_MULTIPLYS[2]) maxValue = 3;
        else if (SETTINGS_MULTIPLYS[1]) maxValue = 2;
        else if (SETTINGS_MULTIPLYS[0]) maxValue = 1;
        return maxValue;
    }

}
package com.example.umnozhka;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class StartActivity extends Activity implements View.OnClickListener {
    Button buttonSettings, buttonGame, buttonGrades, buttonEnd;
    //    TextView textView, textView2;
    private static SharedPreferences sharedPreferences;
    private static final String PREFERENCES_SETTINGS_NAME = "umnozhka_Settings";
    public static String SettingsLanguage;
    public static boolean[] SETTINGS_MULTIPLYS = {false, false, false, false, false, false, false, false, false, false};
    public static boolean SETTINGS_SUBTRAC;    // Сложение
    public static boolean SETTINGS_ADD;        // Вычитание
    public static boolean SETTINGS_MULTIPLY;   // Умножение
    public static boolean SETTINGS_DIVIDE;     // Деление
    public static int SETTINGS_ADD_RANGE_MIN;  // Начало диапозона сложения
    public static int SETTINGS_ADD_RANGE_MAX;  // Конец диапозона сложения
    public static boolean SETTINGS_RECORD;            // На выживание (на рекорд)
    public static boolean SETTINGS_SOUND;             // Включение звуковых эффектов


//    public static Locale locale;
//    public static Configuration configuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        if (sharedPreferences != null) {
            // по умолчанию, если настроеки есть загружаются, иначе создаются

            loadPreferences();
            changeDisplayLanguage(SettingsLanguage);
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
            SettingsLanguage = getResources().getConfiguration().locale.getDisplayLanguage();
            SETTINGS_SOUND    = true;
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
                // игра
                Intent intent2 = new Intent(this, MainActivity.class);
                startActivity(intent2);
                break;
            case R.id.buttonGrades:
                // журнал оценок
//                    setContentView(R.layout.activity_start);
                break;
            case R.id.buttonEnd:
                this.finish();
                break;

        }

    }

    private void loadPreferences() {
        // Думаю, что не нужно устанавливать в ручную переключатели и другие элементы в Preference Активности
        // Должны сами устанавливаться по значению констант настроек

        SettingsLanguage = sharedPreferences.getString("settingsLanguage", "en");
        SETTINGS_SOUND =  sharedPreferences.getBoolean("settingsSound", true);
        SETTINGS_MULTIPLY = sharedPreferences.getBoolean("settingsMultiply", true);
        SETTINGS_DIVIDE = sharedPreferences.getBoolean("settingsDivide", false);
        SETTINGS_SUBTRAC = sharedPreferences.getBoolean("settingsSubtrac", false);
        SETTINGS_ADD = sharedPreferences.getBoolean("settingsAdd", false);
        SETTINGS_MULTIPLYS[0] = sharedPreferences.getBoolean("settingsMultiplyNumber1", false);
        SETTINGS_MULTIPLYS[1] = sharedPreferences.getBoolean("settingsMultiplyNumber2", true);
        SETTINGS_MULTIPLYS[2] = sharedPreferences.getBoolean("settingsMultiplyNumber3", true);
        SETTINGS_MULTIPLYS[3] = sharedPreferences.getBoolean("settingsMultiplyNumber4", true);
        SETTINGS_MULTIPLYS[4] = sharedPreferences.getBoolean("settingsMultiplyNumber5", true);
        SETTINGS_MULTIPLYS[5] = sharedPreferences.getBoolean("settingsMultiplyNumber6", true);
        SETTINGS_MULTIPLYS[6] = sharedPreferences.getBoolean("settingsMultiplyNumber7", true);
        SETTINGS_MULTIPLYS[7] = sharedPreferences.getBoolean("settingsMultiplyNumber8", true);
        SETTINGS_MULTIPLYS[8] = sharedPreferences.getBoolean("settingsMultiplyNumber9", true);
        SETTINGS_MULTIPLYS[9] = sharedPreferences.getBoolean("settingsMultiplyNumber10", true);

        // НУЖНА проверка на сисловое или строковое значение
        // была ошибка когда значение было по по умолчанию = "0"
        // я поставил преобразование из троки в число
        // потом поменял значение на другое и ошибка вышла
        // на попытку преобразования числа в число, ну или я так понял

        SETTINGS_ADD_RANGE_MIN = Integer.valueOf(sharedPreferences.getString("settingsAddRangeMin", "1"));
        SETTINGS_ADD_RANGE_MAX = Integer.valueOf(sharedPreferences.getString("settingsAddRangeMax", "100"));

        SETTINGS_RECORD = sharedPreferences.getBoolean("settingsRecord", true);
        changeDisplayLanguage(SettingsLanguage);
//        recreate();

//        textView.setText(Locale.getDefault().getLanguage());
//        textView2.setText(SettingsLanguage);
    }


    private void savePreferences() {
        SharedPreferences.Editor editorSharedPreferences = sharedPreferences.edit();
        editorSharedPreferences.putString("settingsLanguage", String.valueOf(SettingsLanguage));
        editorSharedPreferences.putBoolean("settingsSound", SETTINGS_SOUND);
        editorSharedPreferences.putBoolean("settingsMultiply", SETTINGS_MULTIPLY);
        editorSharedPreferences.putBoolean("settingsDivide", SETTINGS_DIVIDE);
        editorSharedPreferences.putBoolean("settingsSubtrac", SETTINGS_SUBTRAC);
        editorSharedPreferences.putBoolean("settingsAdd", SETTINGS_ADD);
        editorSharedPreferences.putString("settingsAddRangeMin", String.valueOf(SETTINGS_ADD_RANGE_MIN));
        editorSharedPreferences.putString("settingsAddRangeMax", String.valueOf(SETTINGS_ADD_RANGE_MAX));
        editorSharedPreferences.putBoolean("settingsMultiplyNumber1", SETTINGS_MULTIPLYS[0]);
        editorSharedPreferences.putBoolean("settingsMultiplyNumber2", SETTINGS_MULTIPLYS[1]);
        editorSharedPreferences.putBoolean("settingsMultiplyNumber3", SETTINGS_MULTIPLYS[2]);
        editorSharedPreferences.putBoolean("settingsMultiplyNumber4", SETTINGS_MULTIPLYS[3]);
        editorSharedPreferences.putBoolean("settingsMultiplyNumber5", SETTINGS_MULTIPLYS[4]);
        editorSharedPreferences.putBoolean("settingsMultiplyNumber6", SETTINGS_MULTIPLYS[5]);
        editorSharedPreferences.putBoolean("settingsMultiplyNumber7", SETTINGS_MULTIPLYS[6]);
        editorSharedPreferences.putBoolean("settingsMultiplyNumber8", SETTINGS_MULTIPLYS[7]);
        editorSharedPreferences.putBoolean("settingsMultiplyNumber9", SETTINGS_MULTIPLYS[8]);
        editorSharedPreferences.putBoolean("settingsMultiplyNumber10", SETTINGS_MULTIPLYS[9]);
        editorSharedPreferences.putBoolean("settingsRecord", SETTINGS_RECORD);
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
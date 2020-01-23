package com.example.umnozhka;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;

import java.util.prefs.Preferences;

public class PrefActivity extends PreferenceActivity {
    SharedPreferences sharedPreferences;

    CheckBoxPreference checkBoxPreference_SETTINGS_MULTIPLY;
    CheckBoxPreference checkBoxPreference_SETTINGS_DIVIDE;
    CheckBoxPreference checkBoxPreference_SETTINGS_ADD;
    CheckBoxPreference checkBoxPreference_SETTINGS_SUBTRAC;
    CheckBoxPreference checkBoxPreference_SETTINGS_MULTIPLY_1;
    CheckBoxPreference checkBoxPreference_SETTINGS_MULTIPLY_2;
    CheckBoxPreference checkBoxPreference_SETTINGS_MULTIPLY_3;
    CheckBoxPreference checkBoxPreference_SETTINGS_MULTIPLY_4;
    CheckBoxPreference checkBoxPreference_SETTINGS_MULTIPLY_5;
    CheckBoxPreference checkBoxPreference_SETTINGS_MULTIPLY_6;
    CheckBoxPreference checkBoxPreference_SETTINGS_MULTIPLY_7;
    CheckBoxPreference checkBoxPreference_SETTINGS_MULTIPLY_8;
    CheckBoxPreference checkBoxPreference_SETTINGS_MULTIPLY_9;
    CheckBoxPreference checkBoxPreference_SETTINGS_MULTIPLY_10;
    CheckBoxPreference checkBoxPreference_SETTINGS_TIME_BETWEEN_SESSIONS;
    CheckBoxPreference checkBoxPreference_SETTINGS_COUNT_TASK;
    CheckBoxPreference checkBoxPreference_SETTINGS_TIME_TASK;
    CheckBoxPreference checkBoxPreference_SETTINGS_TIME_SESSION;
    CheckBoxPreference checkBoxPreference_PREFERENCES_SETTINGS_HEARTSLIVECOUNT;
    EditTextPreference editTextPreference_SETTINGS_ADD_RANGE_MIN;
    EditTextPreference editTextPreference_SETTINGS_ADD_RANGE_MAX;
    SwitchPreference switchPreference_SETTINGS_RECORD;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref);
        PreferenceManager.setDefaultValues(PrefActivity.this, R.xml.pref, false);

        sharedPreferences = getSharedPreferences( MainActivity.PREFERENCES_SETTINGS_NAME, Context.MODE_PRIVATE);

        checkBoxPreference_SETTINGS_MULTIPLY = (CheckBoxPreference) findPreference("SETTINGS_MULTIPLY");
//        checkBoxPreference_SETTINGS_MULTIPLY = findViewById(R.xml.pref);
        checkBoxPreference_SETTINGS_DIVIDE = (CheckBoxPreference) findPreference("SETTINGS_DIVIDE");
        checkBoxPreference_SETTINGS_ADD = (CheckBoxPreference) findPreference("SETTINGS_ADD");
        checkBoxPreference_SETTINGS_SUBTRAC = (CheckBoxPreference) findPreference("SETTINGS_SUBTRAC");
        checkBoxPreference_SETTINGS_MULTIPLY_1 = (CheckBoxPreference) findPreference("SETTINGS_MULTIPLY_1");
        checkBoxPreference_SETTINGS_MULTIPLY_2 = (CheckBoxPreference) findPreference("SETTINGS_MULTIPLY_2");
        checkBoxPreference_SETTINGS_MULTIPLY_3 = (CheckBoxPreference) findPreference("SETTINGS_MULTIPLY_3");
        checkBoxPreference_SETTINGS_MULTIPLY_4 = (CheckBoxPreference) findPreference("SETTINGS_MULTIPLY_4");
        checkBoxPreference_SETTINGS_MULTIPLY_5 = (CheckBoxPreference) findPreference("SETTINGS_MULTIPLY_5");
        checkBoxPreference_SETTINGS_MULTIPLY_6 = (CheckBoxPreference) findPreference("SETTINGS_MULTIPLY_6");
        checkBoxPreference_SETTINGS_MULTIPLY_7 = (CheckBoxPreference) findPreference("SETTINGS_MULTIPLY_7");
        checkBoxPreference_SETTINGS_MULTIPLY_8 = (CheckBoxPreference) findPreference("SETTINGS_MULTIPLY_8");
        checkBoxPreference_SETTINGS_MULTIPLY_9 = (CheckBoxPreference) findPreference("SETTINGS_MULTIPLY_9");
        checkBoxPreference_SETTINGS_MULTIPLY_10 = (CheckBoxPreference) findPreference("SETTINGS_MULTIPLY_10");
        checkBoxPreference_SETTINGS_TIME_BETWEEN_SESSIONS = (CheckBoxPreference) findPreference("SETTINGS_TIME_BETWEEN_SESSIONS");
        checkBoxPreference_SETTINGS_COUNT_TASK = (CheckBoxPreference) findPreference("SETTINGS_COUNT_TASK");
        checkBoxPreference_SETTINGS_TIME_TASK = (CheckBoxPreference) findPreference("SETTINGS_TIME_TASK");
        checkBoxPreference_SETTINGS_TIME_SESSION = (CheckBoxPreference) findPreference("SETTINGS_TIME_SESSION");
        checkBoxPreference_PREFERENCES_SETTINGS_HEARTSLIVECOUNT = (CheckBoxPreference) findPreference("PREFERENCES_SETTINGS_HEARTSLIVECOUNT");
        editTextPreference_SETTINGS_ADD_RANGE_MIN = (EditTextPreference) findPreference("SETTINGS_ADD_RANGE_MIN");
        editTextPreference_SETTINGS_ADD_RANGE_MAX = (EditTextPreference) findPreference("SETTINGS_ADD_RANGE_MAX");
        switchPreference_SETTINGS_RECORD = (SwitchPreference) findPreference("SETTINGS_RECORD");
        getPreferences();
//        OnPreferenceClickListener onPreferenceClick;
        checkBoxPreference_SETTINGS_MULTIPLY.setOnPreferenceClickListener(new OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                checkBoxPreference_SETTINGS_MULTIPLY.setEnabled(checkBoxPreference_SETTINGS_MULTIPLY.isChecked());
                MainActivity.SETTINGS_MULTIPLY = checkBoxPreference_SETTINGS_MULTIPLY.isChecked();
                return false;
            }
        });
        checkBoxPreference_SETTINGS_DIVIDE.setOnPreferenceClickListener(new OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                checkBoxPreference_SETTINGS_DIVIDE.setEnabled(checkBoxPreference_SETTINGS_DIVIDE.isChecked());
                MainActivity.SETTINGS_DIVIDE = checkBoxPreference_SETTINGS_DIVIDE.isChecked();
                return false;
            }
        });
        checkBoxPreference_SETTINGS_ADD.setOnPreferenceClickListener(new OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                checkBoxPreference_SETTINGS_ADD.setEnabled(checkBoxPreference_SETTINGS_ADD.isChecked());
                MainActivity.SETTINGS_ADD = checkBoxPreference_SETTINGS_ADD.isChecked();
                return false;
            }
        });
        checkBoxPreference_SETTINGS_SUBTRAC.setOnPreferenceClickListener(new OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                checkBoxPreference_SETTINGS_SUBTRAC.setEnabled(checkBoxPreference_SETTINGS_SUBTRAC.isChecked());
                MainActivity.SETTINGS_SUBTRAC = checkBoxPreference_SETTINGS_SUBTRAC.isChecked();
                return false;
            }
        });

    }


    private void getPreferences(){


        // Думаю, что не нужно устанавливать в ручную переключатели и другие элементы в Preference Активности
        // Должны сами устанавливаться по значению констант настроек
//        if (sharedPreferences.contains("SETTINGS_MULTIPLY")) {
////            sharedPreferences.contains(key);
//            SETTINGS_MULTIPLY = sharedPreferences.getBoolean("SETTINGS_MULTIPLY", true);
//        } else {
//            SETTINGS_MULTIPLY = true;
//            SharedPreferences.Editor editorSharedPreferences = sharedPreferences.edit();
//            editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY", SETTINGS_MULTIPLY);
//            editorSharedPreferences.apply();
//
//        }
//        if (sharedPreferences.contains("SETTINGS_DIVIDE")) {
////            sharedPreferences.contains(key);
//            SETTINGS_DIVIDE = sharedPreferences.getBoolean("SETTINGS_DIVIDE", false);
//        } else {
//            SETTINGS_DIVIDE = false;
//            SharedPreferences.Editor editorSharedPreferences = sharedPreferences.edit();
//            editorSharedPreferences.putBoolean("SETTINGS_DIVIDE", SETTINGS_DIVIDE);
//            editorSharedPreferences.apply();
//
//        }                                SETTINGS_MULTIPLY
//        checkBoxPreference_SETTINGS_MULTIPLY
        MainActivity.SETTINGS_MULTIPLY = sharedPreferences.getBoolean("SETTINGS_MULTIPLY", true);
        MainActivity.SETTINGS_DIVIDE = sharedPreferences.getBoolean("SETTINGS_DIVIDE", false);
        MainActivity.SETTINGS_SUBTRAC = sharedPreferences.getBoolean("SETTINGS_SUBTRAC", false);
        MainActivity.SETTINGS_ADD = sharedPreferences.getBoolean("SETTINGS_ADD", false);
        MainActivity.SETTINGS_ADD_RANGE_MIN = sharedPreferences.getInt("SETTINGS_ADD_RANGE_MIN", 1);
        MainActivity.SETTINGS_ADD_RANGE_MAX = sharedPreferences.getInt("SETTINGS_ADD_RANGE_MAX", 100);
        MainActivity.SETTINGS_MULTIPLY_1 = sharedPreferences.getBoolean("SETTINGS_MULTIPLY_1", false);
        MainActivity.SETTINGS_MULTIPLY_2 = sharedPreferences.getBoolean("SETTINGS_MULTIPLY_2", true);
        MainActivity.SETTINGS_MULTIPLY_3 = sharedPreferences.getBoolean("SETTINGS_MULTIPLY_3", true);
        MainActivity.SETTINGS_MULTIPLY_4 = sharedPreferences.getBoolean("SETTINGS_MULTIPLY_4", true);
        MainActivity.SETTINGS_MULTIPLY_5 = sharedPreferences.getBoolean("SETTINGS_MULTIPLY_5", true);
        MainActivity.SETTINGS_MULTIPLY_6 = sharedPreferences.getBoolean("SETTINGS_MULTIPLY_6", true);
        MainActivity.SETTINGS_MULTIPLY_7 = sharedPreferences.getBoolean("SETTINGS_MULTIPLY_7", true);
        MainActivity.SETTINGS_MULTIPLY_8 = sharedPreferences.getBoolean("SETTINGS_MULTIPLY_8", true);
        MainActivity.SETTINGS_MULTIPLY_9 = sharedPreferences.getBoolean("SETTINGS_MULTIPLY_9", true);
        MainActivity.SETTINGS_MULTIPLY_10 = sharedPreferences.getBoolean("SETTINGS_MULTIPLY_10", false);
        //            sharedPreferences.getInt("SETTINGS_TIME_BETWEEN_SESSIONS", 100);
        //            sharedPreferences.getInt("SETTINGS_COUNT_TASK", 12);
        MainActivity.SETTINGS_RECORD = sharedPreferences.getBoolean("SETTINGS_RECORD", true);
//            sharedPreferences.getInt("SETTINGS_TIME_TASK", 30);
//            sharedPreferences.getInt("SETTINGS_TIME_SESSION", 360);
        MainActivity.PREFERENCES_SETTINGS_HEARTSLIVECOUNT = sharedPreferences.getInt("PREFERENCES_SETTINGS_HEARTSLIVECOUNT", 5);
        MainActivity.heartLiveCount = sharedPreferences.getInt("heartLiveCount", 0);


    };

    private void putPreferences() {
        SharedPreferences.Editor editorSharedPreferences = sharedPreferences.edit();
        editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY", MainActivity.SETTINGS_MULTIPLY);
        editorSharedPreferences.putBoolean("SETTINGS_DIVIDE", MainActivity.SETTINGS_DIVIDE);
        editorSharedPreferences.putBoolean("SETTINGS_SUBTRAC", MainActivity.SETTINGS_SUBTRAC);
        editorSharedPreferences.putBoolean("SETTINGS_ADD", MainActivity.SETTINGS_ADD);
        editorSharedPreferences.putInt("SETTINGS_ADD_RANGE_MIN", MainActivity.SETTINGS_ADD_RANGE_MIN);
        editorSharedPreferences.putInt("SETTINGS_ADD_RANGE_MAX", MainActivity.SETTINGS_ADD_RANGE_MAX);
        editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY_1", MainActivity.SETTINGS_MULTIPLY_1);
        editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY_2", MainActivity.SETTINGS_MULTIPLY_2);
        editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY_3", MainActivity.SETTINGS_MULTIPLY_3);
        editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY_4", MainActivity.SETTINGS_MULTIPLY_4);
        editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY_5", MainActivity.SETTINGS_MULTIPLY_5);
        editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY_6", MainActivity.SETTINGS_MULTIPLY_6);
        editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY_7", MainActivity.SETTINGS_MULTIPLY_7);
        editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY_8", MainActivity.SETTINGS_MULTIPLY_8);
        editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY_9", MainActivity.SETTINGS_MULTIPLY_9);
        editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY_10", MainActivity.SETTINGS_MULTIPLY_10);
//            editorSharedPreferences.putBoolean("SETTINGS_TIME_BETWEEN_SESSIONS", SETTINGS_TIME_BETWEEN_SESSIONS);
//            editorSharedPreferences.putBoolean("SETTINGS_COUNT_TASK", SETTINGS_COUNT_TASK);
        editorSharedPreferences.putBoolean("SETTINGS_RECORD", MainActivity.SETTINGS_RECORD);
//            editorSharedPreferences.putBoolean("SETTINGS_TIME_TASK", SETTINGS_TIME_TASK);
//            editorSharedPreferences.putBoolean("SSETTINGS_TIME_SESSION", SETTINGS_TIME_SESSION);
//            editorSharedPreferences.putBoolean("SETTINGS_TIME_SESSION", SETTINGS_MULTIPLY_10);
        editorSharedPreferences.putInt("PREFERENCES_SETTINGS_HEARTSLIVECOUNT", MainActivity.PREFERENCES_SETTINGS_HEARTSLIVECOUNT);
        editorSharedPreferences.putInt("CURRENT_HEARTSLIVECOUNT", MainActivity.heartLiveCount);

        editorSharedPreferences.apply();

    }
    @Override
    protected void onPause() {
//        savePreferences();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPreferences();

    }

    @Override
    protected void  onDestroy(){
        //savePreferences();
        putPreferences();
        super.onDestroy();
    }


}






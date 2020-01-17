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
import android.preference.SwitchPreference;

public class PrefActivity extends PreferenceActivity {
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref);

//        sharedPreferences = getSharedPreferences( MainActivity.PREFERENCES_SETTINGS_NAME, Context.MODE_PRIVATE);
//        loadPreferences();
        checkBoxPreference_SETTINGS_MULTIPLY = (CheckBoxPreference) findPreference("SETTINGS_MULTIPLY");
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
        checkBoxPreference_SETTINGS_MULTIPLY = (CheckBoxPreference) findPreference("SETTINGS_MULTIPLY");
        checkBoxPreference_SETTINGS_MULTIPLY = (CheckBoxPreference) findPreference("SETTINGS_MULTIPLY");
        checkBoxPreference_SETTINGS_MULTIPLY = (CheckBoxPreference) findPreference("SETTINGS_MULTIPLY");

//        PreferenceActivity
//                SETTINGS_SUBTRAC = findViewById(R.id. .string.S .textViewAnswerShow7);

//        chb3 = (CheckBoxPreference) findPreference("chb3");
//        categ2  = (PreferenceCategory) findPreference("categ2");
//        categ2.setEnabled(chb3.isChecked());
//
//        chb3.setOnPreferenceClickListener(new OnPreferenceClickListener() {
//            public boolean onPreferenceClick(Preference preference) {
//                categ2.setEnabled(chb3.isChecked());
//                return false;
//            }
//        });
    }
//    private void loadPreferences() {
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
/*        SETTINGS_MULTIPLY_PREF = sharedPreferences.getBoolean("SETTINGS_MULTIPLY", true);
        SETTINGS_DIVIDE_PREF = sharedPreferences.getBoolean("SETTINGS_DIVIDE", false);
        SETTINGS_SUBTRAC_PREF = sharedPreferences.getBoolean("SETTINGS_SUBTRAC", false);
        SETTINGS_ADD_PREF = sharedPreferences.getBoolean("SETTINGS_ADD", false);
        SETTINGS_ADD_RANGE_MIN_PREF = sharedPreferences.getInt("SETTINGS_ADD_RANGE_MIN", 1);
        SETTINGS_ADD_RANGE_MAX_PREF = sharedPreferences.getInt("SETTINGS_ADD_RANGE_MAX", 100);
        SETTINGS_MULTIPLY_1_PREF = sharedPreferences.getBoolean("SETTINGS_MULTIPLY_1", false);
        SETTINGS_MULTIPLY_2_PREF = sharedPreferences.getBoolean("SETTINGS_MULTIPLY_2", true);
        SETTINGS_MULTIPLY_3_PREF = sharedPreferences.getBoolean("SETTINGS_MULTIPLY_3", true);
        SETTINGS_MULTIPLY_4_PREF = sharedPreferences.getBoolean("SETTINGS_MULTIPLY_4", true);
        SETTINGS_MULTIPLY_5_PREF = sharedPreferences.getBoolean("SETTINGS_MULTIPLY_5", true);
        SETTINGS_MULTIPLY_6_PREF = sharedPreferences.getBoolean("SETTINGS_MULTIPLY_6", true);
        SETTINGS_MULTIPLY_7_PREF = sharedPreferences.getBoolean("SETTINGS_MULTIPLY_7", true);
        SETTINGS_MULTIPLY_8_PREF = sharedPreferences.getBoolean("SETTINGS_MULTIPLY_8", true);
        SETTINGS_MULTIPLY_9_PREF = sharedPreferences.getBoolean("SETTINGS_MULTIPLY_9", true);
        SETTINGS_MULTIPLY_10_PREF = sharedPreferences.getBoolean("SETTINGS_MULTIPLY_10", false);
        //            sharedPreferences.getInt("SETTINGS_TIME_BETWEEN_SESSIONS", 100);
        //            sharedPreferences.getInt("SETTINGS_COUNT_TASK", 12);
        SETTINGS_RECORD_PREF = sharedPreferences.getBoolean("SETTINGS_RECORD", true);
//            sharedPreferences.getInt("SETTINGS_TIME_TASK", 30);
//            sharedPreferences.getInt("SETTINGS_TIME_SESSION", 360);
        PREFERENCES_SETTINGS_HEARTSLIVECOUNT_PREF = sharedPreferences.getInt("PREFERENCES_SETTINGS_HEARTSLIVECOUNT", 5);
        MainActivity.heartLiveCount =  sharedPreferences.getInt("heartLiveCount", 0);
*/
    }


/*    private void savePreferences() {
        SharedPreferences.Editor editorSharedPreferences = sharedPreferences.edit();
        editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY", SETTINGS_MULTIPLY_PREF);
        editorSharedPreferences.putBoolean("SETTINGS_DIVIDE", SETTINGS_DIVIDE_PREF);
        editorSharedPreferences.putBoolean("SETTINGS_SUBTRAC", SETTINGS_SUBTRAC_PREF);
        editorSharedPreferences.putBoolean("SETTINGS_ADD", SETTINGS_ADD_PREF);
        editorSharedPreferences.putInt("SETTINGS_ADD_RANGE_MIN", SETTINGS_ADD_RANGE_MIN_PREF);
        editorSharedPreferences.putInt("SETTINGS_ADD_RANGE_MAX", SETTINGS_ADD_RANGE_MAX_PREF);
        editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY_1", SETTINGS_MULTIPLY_1_PREF);
        editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY_2", SETTINGS_MULTIPLY_2_PREF);
        editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY_3", SETTINGS_MULTIPLY_3_PREF);
        editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY_4", SETTINGS_MULTIPLY_4_PREF);
        editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY_5", SETTINGS_MULTIPLY_5_PREF);
        editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY_6", SETTINGS_MULTIPLY_6_PREF);
        editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY_7", SETTINGS_MULTIPLY_7_PREF);
        editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY_8", SETTINGS_MULTIPLY_8_PREF);
        editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY_9", SETTINGS_MULTIPLY_9_PREF);
        editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY_10", SETTINGS_MULTIPLY_10_PREF);
//            editorSharedPreferences.putBoolean("SETTINGS_TIME_BETWEEN_SESSIONS", SETTINGS_TIME_BETWEEN_SESSIONS);
//            editorSharedPreferences.putBoolean("SETTINGS_COUNT_TASK", SETTINGS_COUNT_TASK);
        editorSharedPreferences.putBoolean("SETTINGS_RECORD", SETTINGS_RECORD_PREF);
//            editorSharedPreferences.putBoolean("SETTINGS_TIME_TASK", SETTINGS_TIME_TASK);
//            editorSharedPreferences.putBoolean("SSETTINGS_TIME_SESSION", SETTINGS_TIME_SESSION);
//            editorSharedPreferences.putBoolean("SETTINGS_TIME_SESSION", SETTINGS_MULTIPLY_10);
        editorSharedPreferences.putInt("PREFERENCES_SETTINGS_HEARTSLIVECOUNT", PREFERENCES_SETTINGS_HEARTSLIVECOUNT_PREF);
        editorSharedPreferences.putInt("CURRENT_HEARTSLIVECOUNT", MainActivity.heartLiveCount);

        editorSharedPreferences.apply();

    }
    @Override
    protected void onPause() {
        savePreferences();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadPreferences();

    }

 */
//}

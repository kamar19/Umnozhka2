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
//    CheckBoxPreference checkBoxPreference_SETTINGS_MULTIPLY;
//    CheckBoxPreference checkBoxPreference_SETTINGS_DIVIDE;
//    CheckBoxPreference checkBoxPreference_SETTINGS_ADD;
//    CheckBoxPreference checkBoxPreference_SETTINGS_SUBTRAC;
//    CheckBoxPreference checkBoxPreference_SETTINGS_MULTIPLY_1;
//    CheckBoxPreference checkBoxPreference_SETTINGS_MULTIPLY_2;
//    CheckBoxPreference checkBoxPreference_SETTINGS_MULTIPLY_3;
//    CheckBoxPreference checkBoxPreference_SETTINGS_MULTIPLY_4;
//    CheckBoxPreference checkBoxPreference_SETTINGS_MULTIPLY_5;
//    CheckBoxPreference checkBoxPreference_SETTINGS_MULTIPLY_6;
//    CheckBoxPreference checkBoxPreference_SETTINGS_MULTIPLY_7;
//    CheckBoxPreference checkBoxPreference_SETTINGS_MULTIPLY_8;
//    CheckBoxPreference checkBoxPreference_SETTINGS_MULTIPLY_9;
//    CheckBoxPreference checkBoxPreference_SETTINGS_MULTIPLY_10;
//    EditTextPreference editTextPreference_SETTINGS_ADD_RANGE_MIN;
//    EditTextPreference editTextPreference_SETTINGS_ADD_RANGE_MAX;
//    SwitchPreference switchPreference_SETTINGS_RECORD;
//    EditTextPreference editTextPreference_SETTINGS_COUNT_TASK;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref);

//        sharedPreferences = getSharedPreferences( MainActivity.PREFERENCES_SETTINGS_NAME, Context.MODE_PRIVATE);
//        loadPreferences();                                                             SETTINGS_MULTIPLY
//
//        checkBoxPreference_SETTINGS_MULTIPLY = (CheckBoxPreference) findPreference("settingsMultiply");
//        checkBoxPreference_SETTINGS_DIVIDE   = (CheckBoxPreference) findPreference("settingsDivide");
//        checkBoxPreference_SETTINGS_ADD      = (CheckBoxPreference) findPreference("settingsAdd");
//        checkBoxPreference_SETTINGS_SUBTRAC  = (CheckBoxPreference) findPreference("settingsSubtrac");
//        checkBoxPreference_SETTINGS_MULTIPLY_1  = (CheckBoxPreference) findPreference("settingsMultiplyNumber1");
//        checkBoxPreference_SETTINGS_MULTIPLY_2  = (CheckBoxPreference) findPreference("settingsMultiplyNumber2");
//        checkBoxPreference_SETTINGS_MULTIPLY_3  = (CheckBoxPreference) findPreference("settingsMultiplyNumber3");
//        checkBoxPreference_SETTINGS_MULTIPLY_4  = (CheckBoxPreference) findPreference("settingsMultiplyNumber4");
//        checkBoxPreference_SETTINGS_MULTIPLY_5  = (CheckBoxPreference) findPreference("settingsMultiplyNumber5");
//        checkBoxPreference_SETTINGS_MULTIPLY_6  = (CheckBoxPreference) findPreference("settingsMultiplyNumber6");
//        checkBoxPreference_SETTINGS_MULTIPLY_7  = (CheckBoxPreference) findPreference("settingsMultiplyNumber7");
//        checkBoxPreference_SETTINGS_MULTIPLY_8  = (CheckBoxPreference) findPreference("settingsMultiplyNumber8");
//        checkBoxPreference_SETTINGS_MULTIPLY_9  = (CheckBoxPreference) findPreference("settingsMultiplyNumber9");
//        checkBoxPreference_SETTINGS_MULTIPLY_10 = (CheckBoxPreference) findPreference("settingsMultiplyNumber10");
//        editTextPreference_SETTINGS_ADD_RANGE_MIN = (EditTextPreference) findPreference("settingsAddRangeMin");
////        editTextPreference_SETTINGS_COUNT_TASK = (EditTextPreference) findPreference("SETTINGS_COUNT_TASK");
//        editTextPreference_SETTINGS_ADD_RANGE_MAX = (EditTextPreference) findPreference("settingsAddRangeMax");
//        switchPreference_SETTINGS_RECORD          = (SwitchPreference)   findPreference("settingsRecord");

    }

}


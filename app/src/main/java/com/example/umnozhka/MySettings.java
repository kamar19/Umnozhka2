package com.example.umnozhka;

import android.content.SharedPreferences;
import android.os.Build;

public class MySettings {
    private String settingsLanguage;
    private boolean[] settingsMultiplys = {false, false, false, false, false, false, false, false, false, false};
    private boolean settingsSubstrac;    // Сложение
    private boolean settingsAdd;        // Вычитание
    private boolean settingsMultiply;   // Умножение
    private boolean settingsDivide;     // Деление
    private int settingsAddRangeMin;  // Начало диапозона сложения
    private int settingsAddRangeMax;  // Конец диапозона сложения
    private boolean settingsRecord;            // На выживание (на рекорд)
    private boolean settingsSound;             // Включение звуковых эффектов
//    private boolean endGame = false;

    public String getSettingsLanguage() {
        return settingsLanguage;
    }

    public boolean[] getSettingsMultiplys() {
        return settingsMultiplys;
    }

    public boolean isSettingsSubstrac() {
        return settingsSubstrac;
    }

    public boolean isSettingsAdd() {
        return settingsAdd;
    }

    public boolean isSettingsMultiply() {
        return settingsMultiply;
    }

    public boolean isSettingsDivide() {
        return settingsDivide;
    }

    public int getSettingsAddRangeMin() {
        return settingsAddRangeMin;
    }


    public String getStringMDSA() {

        //    private String stringMDSA;//Multiply + Divide + Substrac + Add
        //    private String stringMultiplyNumbers; //MultiplyNumber1+...MultiplyNumber10
        String string="";
        if (isSettingsMultiply()) string="*, ";
        if (isSettingsDivide()) string=string+ "/, ";
        if (isSettingsSubstrac()) string=string+ "+, ";
        if (isSettingsDivide()) string=string+ "- ";
        return string;

    }

    public String getStringMultiplyNumbers() {
        String string="";
        for (int i = 0; i <settingsMultiplys.length-1 ; i++) {
            if (settingsMultiplys[i]) string=string+i+" ,";
        }
           return string; //MultiplyNumber1+...MultiplyNumber10
    }


    public int getSettingsAddRangeMax() {
        return settingsAddRangeMax;
    }

    public boolean isSettingsRecord() {
        return settingsRecord;
    }

    public boolean isSettingsSound() {
        return settingsSound;
    }

    public void setSettingsLanguage(String settingsLanguage) {
        settingsLanguage = settingsLanguage;
    }

    public void setNewDefaultSettings() {
        settingsLanguage = "en";
        settingsSound = true;
        settingsMultiply = true;
        settingsDivide = false;
        settingsSubstrac = false;
        settingsAdd = false;
        settingsAddRangeMin = 1;
        settingsAddRangeMax = 100;
        settingsRecord = true;
        settingsMultiplys[0] = false;
        settingsMultiplys[1] = true;
        settingsMultiplys[2] = true;
        settingsMultiplys[3] = true;
        settingsMultiplys[4] = true;
        settingsMultiplys[5] = true;
        settingsMultiplys[6] = true;
        settingsMultiplys[7] = true;
        settingsMultiplys[8] = true;
        settingsMultiplys[9] = true;
    }

    public void loadValuesMySettings(SharedPreferences sharedPreferences) {
        // Думаю, что не нужно устанавливать в ручную переключатели и другие элементы в Preference Активности
        // Должны сами устанавливаться по значению констант настроек
        settingsLanguage = sharedPreferences.getString("settingsLanguage", "en");
        settingsSound = sharedPreferences.getBoolean("settingsSound", true);
        settingsMultiply = sharedPreferences.getBoolean("settingsMultiply", true);
        settingsDivide = sharedPreferences.getBoolean("settingsDivide", false);
        settingsSubstrac = sharedPreferences.getBoolean("settingsSubtrac", false);
        settingsAdd = sharedPreferences.getBoolean("settingsAdd", false);
        settingsMultiplys[0] = sharedPreferences.getBoolean("settingsMultiplyNumber1", false);
        settingsMultiplys[1] = sharedPreferences.getBoolean("settingsMultiplyNumber2", true);
        settingsMultiplys[2] = sharedPreferences.getBoolean("settingsMultiplyNumber3", true);
        settingsMultiplys[3] = sharedPreferences.getBoolean("settingsMultiplyNumber4", true);
        settingsMultiplys[4] = sharedPreferences.getBoolean("settingsMultiplyNumber5", true);
        settingsMultiplys[5] = sharedPreferences.getBoolean("settingsMultiplyNumber6", true);
        settingsMultiplys[6] = sharedPreferences.getBoolean("settingsMultiplyNumber7", true);
        settingsMultiplys[7] = sharedPreferences.getBoolean("settingsMultiplyNumber8", true);
        settingsMultiplys[8] = sharedPreferences.getBoolean("settingsMultiplyNumber9", true);
        settingsMultiplys[9] = sharedPreferences.getBoolean("settingsMultiplyNumber10", true);
        // НУЖНА проверка на числовое или строковое значение
        // была ошибка когда значение было по по умолчанию = "0"
        // я поставил преобразование из троки в число
        // потом поменял значение на другое и ошибка вышла
        // на попытку преобразования числа в число, ну или я так понял
        settingsAddRangeMin = Integer.valueOf(sharedPreferences.getString("settingsAddRangeMin", "1"));
        settingsAddRangeMax = Integer.valueOf(sharedPreferences.getString("settingsAddRangeMax", "100"));
        settingsRecord = sharedPreferences.getBoolean("settingsRecord", true);
    }

    public void savePreferences(SharedPreferences sharedPreferences) {
        SharedPreferences.Editor editorSharedPreferences = sharedPreferences.edit();
        editorSharedPreferences.putString("settingsLanguage", String.valueOf(settingsLanguage));
        editorSharedPreferences.putBoolean("settingsSound", settingsSound);
        editorSharedPreferences.putBoolean("settingsMultiply", settingsMultiply);
        editorSharedPreferences.putBoolean("settingsDivide", settingsDivide);
        editorSharedPreferences.putBoolean("settingsSubtrac", settingsSubstrac);
        editorSharedPreferences.putBoolean("settingsAdd", settingsAdd);
        if (settingsAddRangeMin <1) settingsAddRangeMin =1;
        if (settingsAddRangeMax <1) settingsAddRangeMax =1;
        if (settingsAddRangeMin >99) settingsAddRangeMin =99;
        if (settingsAddRangeMax >100) settingsAddRangeMax =100;
        editorSharedPreferences.putString("settingsAddRangeMin", String.valueOf(settingsAddRangeMin));
        editorSharedPreferences.putString("settingsAddRangeMax", String.valueOf(settingsAddRangeMax));
        editorSharedPreferences.putBoolean("settingsMultiplyNumber1", settingsMultiplys[0]);
        editorSharedPreferences.putBoolean("settingsMultiplyNumber2", settingsMultiplys[1]);
        editorSharedPreferences.putBoolean("settingsMultiplyNumber3", settingsMultiplys[2]);
        editorSharedPreferences.putBoolean("settingsMultiplyNumber4", settingsMultiplys[3]);
        editorSharedPreferences.putBoolean("settingsMultiplyNumber5", settingsMultiplys[4]);
        editorSharedPreferences.putBoolean("settingsMultiplyNumber6", settingsMultiplys[5]);
        editorSharedPreferences.putBoolean("settingsMultiplyNumber7", settingsMultiplys[6]);
        editorSharedPreferences.putBoolean("settingsMultiplyNumber8", settingsMultiplys[7]);
        editorSharedPreferences.putBoolean("settingsMultiplyNumber9", settingsMultiplys[8]);
        editorSharedPreferences.putBoolean("settingsMultiplyNumber10", settingsMultiplys[9]);
        editorSharedPreferences.putBoolean("settingsRecord", settingsRecord);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            editorSharedPreferences.apply();
        } else editorSharedPreferences.commit();
    }

    public int getMinValue_SETTINGS_MULTIPLY() {
        // Пусть нижняя граница имеет придел, тогда
        // верхняя, должна вычисляться по количеству игровых чисел
        //
        if (settingsMultiplys[0]) {
            return 1;
        } else if (settingsMultiplys[1]) {
            return 2;
        } else if (settingsMultiplys[2]) {
            return 3;
        } else if (settingsMultiplys[3]) {
            return 4;
        } else if (settingsMultiplys[4]) {
            return 5;
        } else if (settingsMultiplys[5]) {
            return 6;
        } else if (settingsMultiplys[6]) {
            return 7;
        } else if (settingsMultiplys[7]) {
            return 8;
        } else if (settingsMultiplys[8]) {
            return 9;
        } else return 10;
    }

    public int getMaxValue_SETTINGS_MULTIPLY() {
        // Пусть нижняя граница имеет придел, тогда
        // верхняя, должна вычисляться по количеству игровых чисел
        int maxValue = 0;
        //Считаем коилчество значений.
        if (settingsMultiplys[9]) maxValue = 10;
        else if (settingsMultiplys[8]) maxValue = 9;
        else if (settingsMultiplys[7]) maxValue = 8;
        else if (settingsMultiplys[6]) maxValue = 7;
        else if (settingsMultiplys[5]) maxValue = 6;
        else if (settingsMultiplys[4]) maxValue = 5;
        else if (settingsMultiplys[3]) maxValue = 4;
        else if (settingsMultiplys[2]) maxValue = 3;
        else if (settingsMultiplys[1]) maxValue = 2;
        else if (settingsMultiplys[0]) maxValue = 1;
        return maxValue;
    }

}

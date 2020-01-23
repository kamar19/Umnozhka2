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


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    SharedPreferences sharedPreferences;
    public static final String PREFERENCES_SETTINGS_NAME = "umnozhka_Settings";
    public static int heartLiveCount;

    public static boolean SETTINGS_SUBTRAC;    // Сложение
    public static boolean SETTINGS_ADD;        // Вычитание
    public static boolean SETTINGS_MULTIPLY;   // Умножение
    public static boolean SETTINGS_DIVIDE;     // Деление
    public static int SETTINGS_ADD_RANGE_MIN;  // Начало диапозона сложения
    public static int SETTINGS_ADD_RANGE_MAX;  // Конец диапозона сложения
    public static boolean SETTINGS_MULTIPLY_1; // Умножение на 1
    public static boolean SETTINGS_MULTIPLY_2; // Умножение на 2
    public static boolean SETTINGS_MULTIPLY_3; //
    public static boolean SETTINGS_MULTIPLY_4; //
    public static boolean SETTINGS_MULTIPLY_5; //
    public static boolean SETTINGS_MULTIPLY_6; //
    public static boolean SETTINGS_MULTIPLY_7; //
    public static boolean SETTINGS_MULTIPLY_8; //
    public static boolean SETTINGS_MULTIPLY_9; //
    public static boolean SETTINGS_MULTIPLY_10;//
    public static int SETTINGS_TIME_BETWEEN_SESSIONS; // Время между сеансами
    public static int SETTINGS_COUNT_TASK;            // Задач в сеанс
    public static boolean SETTINGS_RECORD;            // На выживание (на рекорд)
    public static int SETTINGS_TIME_TASK;         // Время на одну задачу
    public static int SETTINGS_TIME_SESSION;      // Время на один сеанс, после уменьшается
    public static int PREFERENCES_SETTINGS_HEARTSLIVECOUNT;

    Button buttonDigit1, buttonDigit2, buttonDigit3, buttonDigit4, buttonDigit5, buttonDigit6, buttonDigit7, buttonDigit8, buttonDigit9,
            buttonDigit0, buttonEnter, buttonBackSpace;
    TextView textViewAnswerShow1, textViewAnswerShow2, textViewAnswerShow3, textViewAnswerShow4, textViewAnswerShow5, textViewAnswerShow6,
            textViewAnswerShow7, textViewAnswerShow8, textViewAnswerShow9, textViewAnswerShow10, textViewAnswerShow11, textViewAnswerShow12,
            textViewQuestion, textViewAnswerShowBasic;
    int currentOneUnit, currentTwoUnit;

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


    /**
     * Called when the activity is first created.
     */


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
        buttonBackSpace = findViewById(R.id.buttonBackSpace);

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

        textViewQuestion = findViewById(R.id.textViewQuestion);

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
        buttonBackSpace.setOnClickListener(this);

        sharedPreferences = getSharedPreferences( PREFERENCES_SETTINGS_NAME, Context.MODE_PRIVATE);

        getPreferences();
//        } else {
//            SETTINGS_MULTIPLY = true;
//            SETTINGS_DIVIDE = false;
//            SETTINGS_SUBTRAC = false;
//            SETTINGS_ADD = false;
//            SETTINGS_ADD_RANGE_MIN = 1;
//            SETTINGS_ADD_RANGE_MAX = 100;
//            SETTINGS_MULTIPLY_1 = false;
//            SETTINGS_MULTIPLY_2 = true;
//            SETTINGS_MULTIPLY_3 = true;
//            SETTINGS_MULTIPLY_4 = true;
//            SETTINGS_MULTIPLY_5 = true;
//            SETTINGS_MULTIPLY_6 = true;
//            SETTINGS_MULTIPLY_7 = true;
//            SETTINGS_MULTIPLY_8 = true;
//            SETTINGS_MULTIPLY_9 = true;
//            SETTINGS_MULTIPLY_10 = true;
//            heartLiveCount=0;
//            PREFERENCES_SETTINGS_HEARTSLIVECOUNT=5;
//
//            SavePreferences();
//            if (sharedPreferences.contains(PREFERENCES_SETTINGS_HEARTSLIVECOUNT)) {
//                // Получаем число из настроек
//                heartLiveCount = sharedPreferences.getInt(PREFERENCES_SETTINGS_HEARTSLIVECOUNT, 0);

                // Настройки приходят пустые, все значения по нулям

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
        switch (item.getItemId()) {
            case R.id.menu_report:
//                пока не реализованно, но нужно сделать отчет, как бы журнал с ответами
                return true;
            case R.id.menu_Preferences:
//                item.setIntent(new Intent(this, PrefActivity.class));
                Intent intent = new Intent(this, PrefActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_Exit:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        String tempQuestion = textViewAnswerShowBasic.getText().toString();
        switch (v.getId()) {
            case R.id.buttonDigit1:
                textViewAnswerShowBasic.setText(tempQuestion + "1");
                break;
            case R.id.buttonDigit2:
                textViewAnswerShowBasic.setText(tempQuestion + "2");
                break;
            case R.id.buttonDigit3:
                textViewAnswerShowBasic.setText(tempQuestion + "3");
                break;
            case R.id.buttonDigit4:
                textViewAnswerShowBasic.setText(tempQuestion + "4");
                break;
            case R.id.buttonDigit5:
                textViewAnswerShowBasic.setText(tempQuestion + "5");
                break;
            case R.id.buttonDigit6:
                textViewAnswerShowBasic.setText(tempQuestion + "6");
                break;
            case R.id.buttonDigit7:
                textViewAnswerShowBasic.setText(tempQuestion + "7");
                break;
            case R.id.buttonDigit8:
                textViewAnswerShowBasic.setText(tempQuestion + "8");
                break;
            case R.id.buttonDigit9:
                textViewAnswerShowBasic.setText(tempQuestion + "9");
                break;
            case R.id.buttonDigit0:
                textViewAnswerShowBasic.setText(tempQuestion + "0");
                break;
            case R.id.buttonBackSpace:
//                String tempSatring = ;
                textViewAnswerShowBasic.setText(tempQuestion.substring(0, tempQuestion.length() - 1));
//                android:background="@drawable/baselinebackspaceblack18dp"
                break;
            case R.id.buttonEnter:
                //выполняется проверка Ответа на математический Вопрос
                refrishDate();
//                textViewQuestion.setText(textViewQuestion.getText()+"1");
                break;
        }
    }


    private void refrishIconLive() {
        View view1, view2, view3, view4, view5;
        view1 = findViewById(R.id.imageView1);
        view2 = findViewById(R.id.imageView2);
        view3 = findViewById(R.id.imageView3);
        view4 = findViewById(R.id.imageView4);
        view5 = findViewById(R.id.imageView5);
        if (heartLiveCount > 5) heartLiveCount = 5;
        if (heartLiveCount < 0) heartLiveCount = 0;


        switch (heartLiveCount) {
            case 0:
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.INVISIBLE);
                view3.setVisibility(View.INVISIBLE);
                view4.setVisibility(View.INVISIBLE);
                view5.setVisibility(View.INVISIBLE);
                break;
            case 1:
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.INVISIBLE);
                view3.setVisibility(View.INVISIBLE);
                view4.setVisibility(View.INVISIBLE);
                view5.setVisibility(View.INVISIBLE);
                break;
            case 2:
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.VISIBLE);
                view3.setVisibility(View.INVISIBLE);
                view4.setVisibility(View.INVISIBLE);
                view5.setVisibility(View.INVISIBLE);
                break;
            case 3:
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.VISIBLE);
                view3.setVisibility(View.VISIBLE);
                view4.setVisibility(View.INVISIBLE);
                view5.setVisibility(View.INVISIBLE);
                break;
            case 4:
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.VISIBLE);
                view3.setVisibility(View.VISIBLE);
                view4.setVisibility(View.VISIBLE);
                view5.setVisibility(View.INVISIBLE);
                break;
            case 5:
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.VISIBLE);
                view3.setVisibility(View.VISIBLE);
                view4.setVisibility(View.VISIBLE);
                view5.setVisibility(View.VISIBLE);
                break;
        }
    }

    private int getAct() {
        int Variants = 0;
        int resultAct = 0;

        if (SETTINGS_MULTIPLY) Variants++;
        if (SETTINGS_DIVIDE) Variants++;
        if (SETTINGS_ADD) Variants++;
        if (SETTINGS_SUBTRAC) Variants++;
        switch (Variants) {
            case 0:
                Variants++;
//            break;
            case 1:
                if ((SETTINGS_MULTIPLY) && (!SETTINGS_DIVIDE) && (!SETTINGS_ADD) && (!SETTINGS_SUBTRAC)) {
                    resultAct = 1;
                } else if ((!SETTINGS_MULTIPLY) && (SETTINGS_DIVIDE) && (!SETTINGS_ADD) && (!SETTINGS_SUBTRAC)) {
                    resultAct = 2;
                } else if ((!SETTINGS_MULTIPLY) && (!SETTINGS_DIVIDE) && (SETTINGS_ADD) && (!SETTINGS_SUBTRAC)) {
                    resultAct = 3;
                } else if ((!SETTINGS_MULTIPLY) && (!SETTINGS_DIVIDE) && (!SETTINGS_ADD) && (SETTINGS_SUBTRAC)) {
                    resultAct = 4;
                }
                ;
                break;
            case 2:
                resultAct = (int) Math.random() * 2;
                if (resultAct == 1) {
                    if (SETTINGS_MULTIPLY) {
                        resultAct = 1;
                    } else if (SETTINGS_DIVIDE) {
                        resultAct = 2;
                    } else
                        resultAct = 3;
                }
                if (resultAct == 2) {
                    if (SETTINGS_SUBTRAC) {
                        resultAct = 4;
                    } else if (SETTINGS_ADD) {
                        resultAct = 3;
                    } else
                        resultAct = 2;
                }
                break;
            case 3:
                resultAct = (int) Math.random() * 3;
                if (!SETTINGS_MULTIPLY) {
                    // Пропускаем 1-е действие, т.е если не используется умножение, то
                    // включены 2,3,4 действия
                    resultAct++;
                } else if (!SETTINGS_DIVIDE) {
                    // пропускаем 2-е действие
                    if (resultAct == 2) {
                        resultAct = 3;
                    } else if (resultAct == 3) resultAct = 4;
                } else if (!SETTINGS_ADD) {
                    // 1, 2 действия оставляем
                    // пропускаем 3-е действие
                    if (resultAct == 3) resultAct = 4;
                }
                ;
                // 1,2,3 Оставляем значение действия тем же, что по рандому.

                break;
            case 4:
                resultAct = (int) Math.random() * 4;
                //Действия

                break;
            default:
                resultAct = 1;

        }

        return resultAct;
    }

    private void refrishDate() {
// Определяем действие
// MULTIPLY - Deistvie = 1;
// DIVIDE   - Deistvie = 2;
// ADD      - Deistvie = 3;
// SUBTRAC  - Deistvie = 4;

//    double Deistvie = Math.random()*3;

//    int Deistvie;

        int currentAct = getAct();

// Определяем значения выражения
// s5  Начало диапо
// s6  Конец диапоз
//    Randomize;
        int countValue = SETTINGS_ADD_RANGE_MAX - SETTINGS_ADD_RANGE_MIN;
        int numberOne = SETTINGS_ADD_RANGE_MIN + (int) (Math.random() * countValue);//0..9, 10..50 = 10-0, 50-10=40
        int numberTwo = SETTINGS_ADD_RANGE_MIN + (int) (Math.random() * countValue);//0..9, 10..50 = 10-0, 50-10=40
        String currentAcString;
        switch (currentAct) {
            case 1:
                currentAcString = " * ";
                break;
            case 2:
                currentAcString = " / ";
                // Используем только делимые числа
                numberOne = numberOne * numberTwo;
                break;
            case 3:
                currentAcString = " + ";
                break;
            case 4:
                currentAcString = " - ";
                if (numberOne < numberTwo) {
                    int tempInt = numberOne;
                    numberOne = numberTwo;
                    numberTwo = tempInt;
                }
                break;
            default:
                currentAct = 1;
                currentAcString = " * ";
        }
        ;
        textViewQuestion.setText(Integer.toString(numberOne) + currentAcString + Integer.toString(numberTwo) + " = ");



};


    private void getPreferences() {
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
//        }
        SETTINGS_MULTIPLY = sharedPreferences.getBoolean("SETTINGS_MULTIPLY", true);
        SETTINGS_DIVIDE= sharedPreferences.getBoolean("SETTINGS_DIVIDE", false);
        SETTINGS_SUBTRAC = sharedPreferences.getBoolean("SETTINGS_SUBTRAC", false);
        SETTINGS_ADD = sharedPreferences.getBoolean("SETTINGS_ADD", false);
        SETTINGS_ADD_RANGE_MIN = sharedPreferences.getInt("SETTINGS_ADD_RANGE_MIN", 1);
        SETTINGS_ADD_RANGE_MAX = sharedPreferences.getInt("SETTINGS_ADD_RANGE_MAX", 100);
        SETTINGS_MULTIPLY_1 = sharedPreferences.getBoolean("SETTINGS_MULTIPLY_1", false);
        SETTINGS_MULTIPLY_2 = sharedPreferences.getBoolean("SETTINGS_MULTIPLY_2", true);
        SETTINGS_MULTIPLY_3 = sharedPreferences.getBoolean("SETTINGS_MULTIPLY_3", true);
        SETTINGS_MULTIPLY_4 = sharedPreferences.getBoolean("SETTINGS_MULTIPLY_4", true);
        SETTINGS_MULTIPLY_5 = sharedPreferences.getBoolean("SETTINGS_MULTIPLY_5", true);
        SETTINGS_MULTIPLY_6 = sharedPreferences.getBoolean("SETTINGS_MULTIPLY_6", true);
        SETTINGS_MULTIPLY_7 = sharedPreferences.getBoolean("SETTINGS_MULTIPLY_7", true);
        SETTINGS_MULTIPLY_8 = sharedPreferences.getBoolean("SETTINGS_MULTIPLY_8", true);
        SETTINGS_MULTIPLY_9 = sharedPreferences.getBoolean("SETTINGS_MULTIPLY_9", true);
        SETTINGS_MULTIPLY_10 = sharedPreferences.getBoolean("SETTINGS_MULTIPLY_10", false);
    //            sharedPreferences.getInt("SETTINGS_TIME_BETWEEN_SESSIONS", 100);
    //            sharedPreferences.getInt("SETTINGS_COUNT_TASK", 12);
            sharedPreferences.getBoolean("SETTINGS_RECORD", true);
//            sharedPreferences.getInt("SETTINGS_TIME_TASK", 30);
//            sharedPreferences.getInt("SETTINGS_TIME_SESSION", 360);
            sharedPreferences.getInt("PREFERENCES_SETTINGS_HEARTSLIVECOUNT", 5);
            sharedPreferences.getInt("heartLiveCount", 0);

//        SETTINGS_MULTIPLY = sharedPreferences.getBoolean("SETTINGS_MULTIPLY", true);
//        SETTINGS_DIVIDE = sharedPreferences.getBoolean("SETTINGS_DIVIDE", false);
//        SETTINGS_SUBTRAC = sharedPreferences.getBoolean("SETTINGS_SUBTRAC", false);
//        SETTINGS_ADD = sharedPreferences.getBoolean("SETTINGS_ADD", false);
//        SETTINGS_ADD_RANGE_MIN = sharedPreferences.getInt("SETTINGS_ADD_RANGE_MIN", 1);
//        SETTINGS_ADD_RANGE_MAX = sharedPreferences.getInt("SETTINGS_ADD_RANGE_MAX", 100);
//        SETTINGS_MULTIPLY_1 = sharedPreferences.getBoolean("SETTINGS_MULTIPLY_1", false);
//        SETTINGS_MULTIPLY_2 = sharedPreferences.getBoolean("SETTINGS_MULTIPLY_2", true);
//        SETTINGS_MULTIPLY_3 = sharedPreferences.getBoolean("SETTINGS_MULTIPLY_3", true);
//        SETTINGS_MULTIPLY_4 = sharedPreferences.getBoolean("SETTINGS_MULTIPLY_4", true);
//        SETTINGS_MULTIPLY_5 = sharedPreferences.getBoolean("SETTINGS_MULTIPLY_5", true);
//        SETTINGS_MULTIPLY_6 = sharedPreferences.getBoolean("SETTINGS_MULTIPLY_6", true);
//        SETTINGS_MULTIPLY_7 = sharedPreferences.getBoolean("SETTINGS_MULTIPLY_7", true);
//        SETTINGS_MULTIPLY_8 = sharedPreferences.getBoolean("SETTINGS_MULTIPLY_8", true);
//        SETTINGS_MULTIPLY_9 = sharedPreferences.getBoolean("SETTINGS_MULTIPLY_9", true);
//        SETTINGS_MULTIPLY_10 = sharedPreferences.getBoolean("SETTINGS_MULTIPLY_10", false);
//        //            sharedPreferences.getInt("SETTINGS_TIME_BETWEEN_SESSIONS", 100);
//        //            sharedPreferences.getInt("SETTINGS_COUNT_TASK", 12);
//        SETTINGS_RECORD = sharedPreferences.getBoolean("SETTINGS_RECORD", true);
////            sharedPreferences.getInt("SETTINGS_TIME_TASK", 30);
////            sharedPreferences.getInt("SETTINGS_TIME_SESSION", 360);
//        PREFERENCES_SETTINGS_HEARTSLIVECOUNT = sharedPreferences.getInt("PREFERENCES_SETTINGS_HEARTSLIVECOUNT", 5);
//        heartLiveCount = sharedPreferences.getInt("heartLiveCount", 0);

        }


//    private void savePreferences() {
//        SharedPreferences.Editor editorSharedPreferences = sharedPreferences.edit();
//        editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY", SETTINGS_MULTIPLY);
//        editorSharedPreferences.putBoolean("SETTINGS_DIVIDE", SETTINGS_DIVIDE);
//        editorSharedPreferences.putBoolean("SETTINGS_SUBTRAC", SETTINGS_SUBTRAC);
//        editorSharedPreferences.putBoolean("SETTINGS_ADD", SETTINGS_ADD);
//        editorSharedPreferences.putInt("SETTINGS_ADD_RANGE_MIN", SETTINGS_ADD_RANGE_MIN);
//        editorSharedPreferences.putInt("SETTINGS_ADD_RANGE_MAX", SETTINGS_ADD_RANGE_MAX);
//        editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY_1", SETTINGS_MULTIPLY_1);
//        editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY_2", SETTINGS_MULTIPLY_2);
//        editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY_3", SETTINGS_MULTIPLY_3);
//        editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY_4", SETTINGS_MULTIPLY_4);
//        editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY_5", SETTINGS_MULTIPLY_5);
//        editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY_6", SETTINGS_MULTIPLY_6);
//        editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY_7", SETTINGS_MULTIPLY_7);
//        editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY_8", SETTINGS_MULTIPLY_8);
//        editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY_9", SETTINGS_MULTIPLY_9);
//        editorSharedPreferences.putBoolean("SETTINGS_MULTIPLY_10", SETTINGS_MULTIPLY_10);
////            editorSharedPreferences.putBoolean("SETTINGS_TIME_BETWEEN_SESSIONS", SETTINGS_TIME_BETWEEN_SESSIONS);
////            editorSharedPreferences.putBoolean("SETTINGS_COUNT_TASK", SETTINGS_COUNT_TASK);
//        editorSharedPreferences.putBoolean("SETTINGS_RECORD", SETTINGS_RECORD);
////            editorSharedPreferences.putBoolean("SETTINGS_TIME_TASK", SETTINGS_TIME_TASK);
////            editorSharedPreferences.putBoolean("SSETTINGS_TIME_SESSION", SETTINGS_TIME_SESSION);
////            editorSharedPreferences.putBoolean("SETTINGS_TIME_SESSION", SETTINGS_MULTIPLY_10);
//        editorSharedPreferences.putInt("PREFERENCES_SETTINGS_HEARTSLIVECOUNT", PREFERENCES_SETTINGS_HEARTSLIVECOUNT);
//        editorSharedPreferences.putInt("CURRENT_HEARTSLIVECOUNT", heartLiveCount);
//
//        editorSharedPreferences.apply();
//
//    }
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
        super.onDestroy();
    }


}
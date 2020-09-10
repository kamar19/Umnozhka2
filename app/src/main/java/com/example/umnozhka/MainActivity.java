package com.example.umnozhka;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static SharedPreferences sharedPreferences;
    public static final String PREFERENCES_SETTINGS_NAME = "umnozhka_Settings";
    public static int countHeartLive=0;
    public static int countAllPrimerov=0;
    public static int countRightTask=0,countWrongTask=0;
    public static int countCurrentRightTask=0, countCurrentWrongTask=0;


    private static boolean SETTINGS_SUBTRAC;    // Сложение
    private static boolean SETTINGS_ADD;        // Вычитание
    private static boolean SETTINGS_MULTIPLY;   // Умножение
    private static boolean SETTINGS_DIVIDE;     // Деление
    private static int SETTINGS_ADD_RANGE_MIN;  // Начало диапозона сложения
    private static int SETTINGS_ADD_RANGE_MAX;  // Конец диапозона сложения

    private static boolean [] SETTINGS_MULTIPLYS={false,false,false,false,false,false,false,false,false,false};
//    private static boolean SETTINGS_MULTIPLY_1; // Умножение на 1
//    private static boolean SETTINGS_MULTIPLY_2; // Умножение на 2
//    private static boolean SETTINGS_MULTIPLY_3; //
//    private static boolean SETTINGS_MULTIPLY_4; //
//    private static boolean SETTINGS_MULTIPLY_5; //
//    private static boolean SETTINGS_MULTIPLY_6; //
//    private static boolean SETTINGS_MULTIPLY_7; //
//    private static boolean SETTINGS_MULTIPLY_8; //
//    private static boolean SETTINGS_MULTIPLY_9; //
//    private static boolean SETTINGS_MULTIPLY_10;//
//    private static int SETTINGS_TIME_BETWEEN_SESSIONS; // Время между сеансами
//    private static int SETTINGS_COUNT_TASK;            // Задач в сеанс
    private static boolean SETTINGS_RECORD;            // На выживание (на рекорд)
//    private static int SETTINGS_TIME_TASK;         // Время на одну задачу
//    private static int SETTINGS_TIME_SESSION;      // Время на один сеанс, после уменьшается
//    private static int PREFERENCES_SETTINGS_HEARTSLIVECOUNT;

    final static String name_value_textViewAnswerShow1 = "value_textViewAnswerShow1";
    final static String name_value_textViewAnswerShow2 = "value_textViewAnswerShow2";
    final static String name_value_textViewAnswerShow3 = "value_textViewAnswerShow3";
    final static String name_value_textViewAnswerShow4 = "value_textViewAnswerShow4";
    final static String name_value_textViewAnswerShow5 = "value_textViewAnswerShow5";
    final static String name_value_textViewAnswerShow6 = "value_textViewAnswerShow6";
    final static String name_value_textViewAnswerShow7 = "value_textViewAnswerShow7";
    final static String name_value_textViewAnswerShow8 = "value_textViewAnswerShow8";
    final static String name_value_textViewAnswerShow9 = "value_textViewAnswerShow9";
    final static String name_value_textViewAnswerShow10 = "value_textViewAnswerShow10";
    final static String name_value_textViewAnswerShow11 = "value_textViewAnswerShow11";
    final static String name_value_textViewAnswerShow12 = "value_textViewAnswerShow12";
    final static String name_value_textViewAnswerShowBasic = "value_textViewAnswerShowBasic";

    Button buttonDigit1, buttonDigit2, buttonDigit3, buttonDigit4, buttonDigit5, buttonDigit6, buttonDigit7, buttonDigit8, buttonDigit9,
            buttonDigit0, buttonEnter, buttonBackSpace;
    TextView textViewAnswerShow1, textViewAnswerShow2, textViewAnswerShow3, textViewAnswerShow4, textViewAnswerShow5, textViewAnswerShow6,
            textViewAnswerShow7, textViewAnswerShow8, textViewAnswerShow9, textViewAnswerShow10, textViewAnswerShow11, textViewAnswerShow12,
            textViewQuestion, textViewAnswerShowBasic,textViewAnswerCount;
    ImageView view1, view2, view3, view4, view5;
    ProgressBar progressBar;
    private int progressBarTime=300;
    private int countPrimerov=1;
    private String stringCurrentAct="*";
    private boolean endGame=false;
    private   MyTask currentTask;

//    PrefActivity prefActivity = new PrefActivity();

//    public MyNumber getCurrentOneUnit() {
//        return currentOneUnit;
//    }

//    public void setCurrentOneUnit(int currentOneUnit) {
//        this.currentOneUnit = currentOneUnit;
//    }

//    public MyNumber getCurrentTwoUnit() {
//        return currentTwoUnit;
//    }

//    public void setCurrentTwoUnit(int currentTwoUnit) {
//        this.currentTwoUnit = currentTwoUnit;
//    }


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


        view1 = findViewById(R.id.imageView1);
//        view1.setVisibility(View.VISIBLE);
        view1.setImageResource(R.drawable.heart);
//        view1.setBackgroundResource(R.color.trans);

        view2 = findViewById(R.id.imageView2);
//        view2.setVisibility(View.VISIBLE);
        view2.setImageResource(R.drawable.heart);

        view3 = findViewById(R.id.imageView3);
//        view3.setVisibility(View.VISIBLE);
        view3.setImageResource(R.drawable.heart);

        view4 = findViewById(R.id.imageView4);
//        view4.setVisibility(View.VISIBLE);
        view4.setImageResource(R.drawable.heart);

        view5 = findViewById(R.id.imageView5);
//        view5.setVisibility(View.VISIBLE);
        view5.setImageResource(R.drawable.heart);

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
        textViewAnswerShow12.setText("1");
        textViewAnswerCount = findViewById(R.id.textViewAnswerCount);
        textViewAnswerCount.setText(String.valueOf(countPrimerov));
        textViewAnswerShowBasic = findViewById(R.id.textViewAnswerShowBasic);

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

        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(progressBarTime);

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
            countHeartLive = 0;
//            PREFERENCES_SETTINGS_HEARTSLIVECOUNT = 5;
//            SETTINGS_COUNT_TASK = 10;

            savePreferences();

//            if (sharedPreferences.contains(PREFERENCES_SETTINGS_HEARTSLIVECOUNT)) {
//                // Получаем число из настроек
//                heartLiveCount = sharedPreferences.getInt(PREFERENCES_SETTINGS_HEARTSLIVECOUNT, 0);

            // Настройки приходят пустые, все значения по нулям

        }
//        progressBar.setMax(SETTINGS_COUNT_TASK);

        if (SETTINGS_RECORD) {
            progressBar.setVisibility(ProgressBar.VISIBLE);

            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    // TODO Auto-generated method stub
//                    while (progressBar.getProgress() < progressBar.getProgress()) {
                        progressBar.incrementProgressBy(2);
                        if (progressBar.getProgress()>=progressBarTime)
                            endGame=true;
//                    }
                }
            }, 1000, 1000);//progressBarTime

        }
        else {
            progressBar.setVisibility(ProgressBar.INVISIBLE);
        }

        act_to_currentTask();
//        textViewQuestion.setText(currentTask.getCurrentOneUnit().toString() + currentTask.getCurrentAct().toString() + currentTask.getCurrentTwoUnit().toString() + " = ");
        textViewQuestion.setText(currentTask.getCurrentAct().toString()  );

        //        textViewQuestion.setText("99");

        refrishIconLive();


    }

    public void act_to_currentTask() {
        MyAct currentAct = new MyAct(SETTINGS_MULTIPLY, SETTINGS_DIVIDE, SETTINGS_ADD, SETTINGS_SUBTRAC);
        //временный currentAct, созданый что бы получить значение действия - getMyAct()
        // а потом уже изходя из действия создать currentTask


        if ((currentAct.getMyAct() == Act.ADD) | (currentAct.getMyAct() == Act.SUBTRAC))
            // Если Сложение или Вычитание
            this.currentTask = new MyTask(SETTINGS_ADD_RANGE_MIN, SETTINGS_ADD_RANGE_MAX, currentAct, SETTINGS_MULTIPLYS );
        else
            // Если Умножение или деление
            this.currentTask = new MyTask(getMinValue_SETTINGS_MULTIPLY(), getMaxValue_SETTINGS_MULTIPLY(), currentAct,SETTINGS_MULTIPLYS);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
        if (!endGame) {
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
                    showAnswer();
                    //refrishDate();
                    act_to_currentTask();
                    textViewQuestion.setText(currentTask.getCurrentOneUnit().toString() + currentTask.getCurrentAct().toString() + currentTask.getCurrentTwoUnit().toString() + " = ");
                    textViewAnswerShowBasic.setText("");
                    break;
            }
        }
    }

    private String setRightTask(){
        // Если правельных ответов в подряд 5, то дается одна жизнь.
        countRightTask++;
        countCurrentRightTask++;
        if (countCurrentRightTask>4) {
            if (countHeartLive<5) {
                countHeartLive++;
                countCurrentRightTask = 0;
                countCurrentWrongTask = 0;
                refrishIconLive();
            }
        }
        return getString(R.string.textRightTask);
    }
    private String setWrongTask(){
        // Установка ошибки
        // Если неправельных ответов в подряд 3, то отнимается одна жизнь.
        countWrongTask++;
        countCurrentWrongTask++;
        countCurrentRightTask = 0;
        if (countCurrentWrongTask>2) {
            if (countHeartLive>0) {
                countHeartLive--;
                countCurrentWrongTask = 0;
                refrishIconLive();
            }
        }

        return getString(R.string.textWrongTask);
    }

    private void showAnswer(){
        int intAnswer;
        String answer="";
        try
        {
            intAnswer = Integer.parseInt(textViewAnswerShowBasic.getText().toString());
        }
        catch (NumberFormatException nfe)
        {
            intAnswer=0;
        };
        if (intAnswer!=0) {
            switch (currentTask.getCurrentAct().getMyAct()) {
                case MULTIPLY: {
                    if (currentTask.getCurrentOneUnit().getValue() * currentTask.getCurrentTwoUnit().getValue() == intAnswer) answer = setRightTask();
                    else answer = setWrongTask();
                    break; }
                case DIVIDE: {
                    if (currentTask.getCurrentOneUnit().getValue()  / currentTask.getCurrentTwoUnit().getValue() == intAnswer)                 // размещаем ответ
                        answer = setRightTask();
                    else
                        answer = setWrongTask();
                    break;
                }
                case ADD: {
                    if (currentTask.getCurrentOneUnit().getValue()   + currentTask.getCurrentTwoUnit().getValue() == intAnswer)                 // размещаем ответ
                        answer = setRightTask();
                    else
                        answer = setWrongTask();
                    break;
                }
                case SUBTRAC: {
                    if (currentTask.getCurrentOneUnit().getValue()  - currentTask.getCurrentTwoUnit().getValue() == intAnswer)                 // размещаем ответ
                        answer = setRightTask();
                    else
                        answer = setWrongTask();
                    break;
                }
            }
        }
        else
             answer = setWrongTask();
        showViewAnswerShow(answer,intAnswer);
        textViewAnswerCount.setText(getString(R.string.titleAllTask)+" "+countAllPrimerov+getString(R.string.titleRightTask)+" "
                  +countRightTask+getString(R.string.titleWrongTask)+" "+countWrongTask+"    ");

//            progressBar.setProgress(countPrimerov);

            countPrimerov++;
            countAllPrimerov++;
}

   private void invisibleTextViewAnswer(){
       // первичная установка textViewAnswerShow, 1-й делаем видимым, остальные textViewAnswerShow делаем невидимыми
       textViewAnswerShow1.setVisibility(View.VISIBLE);
       textViewAnswerShow2.setVisibility(View.INVISIBLE);
       textViewAnswerShow3.setVisibility(View.INVISIBLE);
       textViewAnswerShow4.setVisibility(View.INVISIBLE);
       textViewAnswerShow5.setVisibility(View.INVISIBLE);
       textViewAnswerShow6.setVisibility(View.INVISIBLE);
       textViewAnswerShow7.setVisibility(View.INVISIBLE);
       textViewAnswerShow8.setVisibility(View.INVISIBLE);
       textViewAnswerShow9.setVisibility(View.INVISIBLE);
       textViewAnswerShow10.setVisibility(View.INVISIBLE);
       textViewAnswerShow11.setVisibility(View.INVISIBLE);
       textViewAnswerShow12.setVisibility(View.INVISIBLE);
   }



   private void showViewAnswerShow(String prav,int intAnswer) {
       // Делаем видимым соответствующий textViewAnswerShow
       // и показываем в нем результат
       // String prav - текстовая оценка результата, ошибка или верно.
       // int intAnswer - числое значение результата
       Act deist = currentTask.getCurrentAct().getMyAct();
       switch (countPrimerov) {
           case 1: {
               invisibleTextViewAnswer();
               textViewAnswerShow1.setText(currentTask.getCurrentOneUnit().toString() + deist.getAct() +  currentTask.getCurrentTwoUnit().toString() + '=' + String.valueOf(intAnswer) + " " + prav);
               break;
           }
           case 2: {
               textViewAnswerShow4.setVisibility(View.VISIBLE);
               textViewAnswerShow4.setText(currentTask.getCurrentOneUnit().toString()  +  deist.getAct() + currentTask.getCurrentTwoUnit().toString() + '=' + String.valueOf(intAnswer) + " " + prav);
               break;
           }
           case 3: {
               textViewAnswerShow7.setVisibility(View.VISIBLE);
               textViewAnswerShow7.setText(currentTask.getCurrentOneUnit().toString()  +  deist.getAct() + currentTask.getCurrentTwoUnit().toString() + '=' + String.valueOf(intAnswer) + " " + prav);
               break;
           }
           case 4: {
               textViewAnswerShow10.setVisibility(View.VISIBLE);
               textViewAnswerShow10.setText(currentTask.getCurrentOneUnit().toString()  +  deist.getAct() + currentTask.getCurrentTwoUnit().toString() + '=' + String.valueOf(intAnswer) + " " + prav);
               break;
           }
           case 5: {
               textViewAnswerShow2.setVisibility(View.VISIBLE);
               textViewAnswerShow2.setText(currentTask.getCurrentOneUnit().toString()  +  deist.getAct() + currentTask.getCurrentTwoUnit().toString()  + '=' + String.valueOf(intAnswer) + " " + prav);
               break;
           }
           case 6: {
               textViewAnswerShow5.setVisibility(View.VISIBLE);
               textViewAnswerShow5.setText(currentTask.getCurrentOneUnit().toString()  +  deist.getAct() + currentTask.getCurrentTwoUnit().toString()  + '=' + String.valueOf(intAnswer) + " " + prav);
               break;
           }
           case 7: {
               textViewAnswerShow8.setVisibility(View.VISIBLE);
               textViewAnswerShow8.setText(currentTask.getCurrentOneUnit().toString()  +  deist.getAct() + currentTask.getCurrentTwoUnit().toString()  + '=' + String.valueOf(intAnswer) + " " + prav);
               break;
           }
           case 8: {
               textViewAnswerShow11.setVisibility(View.VISIBLE);
               textViewAnswerShow11.setText(currentTask.getCurrentOneUnit().toString()  +  deist.getAct() + currentTask.getCurrentTwoUnit().toString()  + '=' + String.valueOf(intAnswer) + " " + prav);
               break;
           }
           case 9: {
               textViewAnswerShow3.setVisibility(View.VISIBLE);
               textViewAnswerShow3.setText(currentTask.getCurrentOneUnit().toString()  +  deist.getAct() + currentTask.getCurrentTwoUnit().toString()  + '=' + String.valueOf(intAnswer) + " " + prav);
               break;
           }
           case 10: {
               textViewAnswerShow6.setVisibility(View.VISIBLE);
               textViewAnswerShow6.setText(currentTask.getCurrentOneUnit().toString()  +  deist.getAct() + currentTask.getCurrentTwoUnit().toString()  + '=' + String.valueOf(intAnswer) + " " + prav);
               break;
           }
           case 11: {
               textViewAnswerShow9.setVisibility(View.VISIBLE);
               textViewAnswerShow9.setText(currentTask.getCurrentOneUnit().toString()  +  deist.getAct() + currentTask.getCurrentTwoUnit().toString()  + '=' + String.valueOf(intAnswer) + " " + prav);
               break;
           }
           case 12: {
               textViewAnswerShow12.setVisibility(View.VISIBLE);
               textViewAnswerShow12.setText(currentTask.getCurrentOneUnit().toString()  +  deist.getAct() + currentTask.getCurrentTwoUnit().toString()  + '=' + String.valueOf(intAnswer) + " " + prav);
               countPrimerov=0;
               break;
           }
       }
   }

    private void refrishIconLive() {

   if (countHeartLive > 5) countHeartLive = 5;
   if (countHeartLive < 0) countHeartLive = 0;

    if (SETTINGS_RECORD) {
        switch (countHeartLive) {
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
    } else {
        view1.setVisibility(View.INVISIBLE);
        view2.setVisibility(View.INVISIBLE);
        view3.setVisibility(View.INVISIBLE);
        view4.setVisibility(View.INVISIBLE);
        view5.setVisibility(View.INVISIBLE);
    }
    }

    private int getMaxValue_SETTINGS_MULTIPLY(){
        // Пусть нижняя граница имеет придел, тогда
        // верхняя, должна вычисляться по количеству игровых чисел
        int maxValue=0;
        // Считаем коилчество значений.
        if  (SETTINGS_MULTIPLYS[9]) maxValue=10;
        else if  (SETTINGS_MULTIPLYS[8]) maxValue=9;
        else if  (SETTINGS_MULTIPLYS[7]) maxValue=8;
        else if  (SETTINGS_MULTIPLYS[6]) maxValue=7;
        else if  (SETTINGS_MULTIPLYS[5]) maxValue=6;
        else if  (SETTINGS_MULTIPLYS[4]) maxValue=5;
        else if  (SETTINGS_MULTIPLYS[3]) maxValue=4;
        else if  (SETTINGS_MULTIPLYS[2]) maxValue=3;
        else if  (SETTINGS_MULTIPLYS[1]) maxValue=2;
        else if  (SETTINGS_MULTIPLYS[0]) maxValue=1;
//        int countMaxValue=0, maxValue=0;
//        // Считаем коилчество значений.
//        if  (SETTINGS_MULTIPLYS[9]) { countMaxValue++;maxValue=10;}
//        else if  (SETTINGS_MULTIPLYS[8]) { countMaxValue++;maxValue=9;}
//        else if  (SETTINGS_MULTIPLYS[7]) { countMaxValue++;maxValue=8;}
//        else if  (SETTINGS_MULTIPLYS[6]) { countMaxValue++;maxValue=7;}
//        else if  (SETTINGS_MULTIPLYS[5]) { countMaxValue++;maxValue=6;}
//        else if  (SETTINGS_MULTIPLYS[4]){ countMaxValue++;maxValue=5;}
//        else if  (SETTINGS_MULTIPLYS[3]){ countMaxValue++;maxValue=4;}
//        else if  (SETTINGS_MULTIPLYS[2]) { countMaxValue++;maxValue=3;}
//        else if  (SETTINGS_MULTIPLYS[1]) { countMaxValue++;maxValue=2;}
//        else if  (SETTINGS_MULTIPLYS[0]) { countMaxValue++;maxValue=1;}
        // Если количесмтво значений равно с максимальным значением,
        // то возвращаем результат, иначе
        // нужен пересчет
//        if (countMaxValue==maxValue)
            return maxValue;
//        else {
//            return countMaxValue;
            //MyNumberOneTen myNumberOneTen = new MyNumberOneTen(min )
//        }
    }

    private int getMinValue_SETTINGS_MULTIPLY(){
        // Пусть нижняя граница имеет придел, тогда
        // верхняя, должна вычисляться по количеству игровых чисел
        //
        if (SETTINGS_MULTIPLYS[0]) { return 1;}
        else if (SETTINGS_MULTIPLYS[1]) { return 2;}
        else if (SETTINGS_MULTIPLYS[2]) { return 3;}
        else if (SETTINGS_MULTIPLYS[3]) { return 4;}
        else if (SETTINGS_MULTIPLYS[4]) { return 5;}
        else if (SETTINGS_MULTIPLYS[5]) { return 6;}
        else if (SETTINGS_MULTIPLYS[6]) { return 7;}
        else if (SETTINGS_MULTIPLYS[7]) { return 8;}
        else if (SETTINGS_MULTIPLYS[8]) { return 9;}
        else return 10;
    }


    private void loadPreferences() {
        // Думаю, что не нужно устанавливать в ручную переключатели и другие элементы в Preference Активности
        // Должны сами устанавливаться по значению констант настроек
        SETTINGS_MULTIPLY = sharedPreferences.getBoolean("SETTINGS_MULTIPLY", true);
        SETTINGS_DIVIDE= sharedPreferences.getBoolean("SETTINGS_DIVIDE", false);
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
        if (SETTINGS_RECORD)
            progressBar.setVisibility(ProgressBar.VISIBLE);
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
    protected void onSaveInstanceState(Bundle outState) {

        outState.putString(name_value_textViewAnswerShow1, textViewAnswerShow1.getText().toString() );
        outState.putString(name_value_textViewAnswerShow2, textViewAnswerShow2.getText().toString() );
        outState.putString(name_value_textViewAnswerShow3, textViewAnswerShow3.getText().toString() );
        outState.putString(name_value_textViewAnswerShow4, textViewAnswerShow4.getText().toString() );
        outState.putString(name_value_textViewAnswerShow5, textViewAnswerShow5.getText().toString() );
        outState.putString(name_value_textViewAnswerShow6, textViewAnswerShow6.getText().toString() );
        outState.putString(name_value_textViewAnswerShow7, textViewAnswerShow7.getText().toString() );
        outState.putString(name_value_textViewAnswerShow8, textViewAnswerShow8.getText().toString() );
        outState.putString(name_value_textViewAnswerShow9, textViewAnswerShow9.getText().toString() );
        outState.putString(name_value_textViewAnswerShow10, textViewAnswerShow10.getText().toString() );
        outState.putString(name_value_textViewAnswerShow11, textViewAnswerShow11.getText().toString() );
        outState.putString(name_value_textViewAnswerShow12, textViewAnswerShow12.getText().toString() );
        outState.putString(name_value_textViewAnswerShowBasic, textViewAnswerShowBasic.getText().toString() );
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        textViewAnswerShow1.setText(savedInstanceState.getString(name_value_textViewAnswerShow1));
        textViewAnswerShow2.setText(savedInstanceState.getString(name_value_textViewAnswerShow2));
        textViewAnswerShow3.setText(savedInstanceState.getString(name_value_textViewAnswerShow3));
        textViewAnswerShow4.setText(savedInstanceState.getString(name_value_textViewAnswerShow4));
        textViewAnswerShow5.setText(savedInstanceState.getString(name_value_textViewAnswerShow5));
        textViewAnswerShow6.setText(savedInstanceState.getString(name_value_textViewAnswerShow6));
        textViewAnswerShow6.setText(savedInstanceState.getString(name_value_textViewAnswerShow7));
        textViewAnswerShow7.setText(savedInstanceState.getString(name_value_textViewAnswerShow8));
        textViewAnswerShow8.setText(savedInstanceState.getString(name_value_textViewAnswerShow9));
        textViewAnswerShow9.setText(savedInstanceState.getString(name_value_textViewAnswerShow10));
        textViewAnswerShow10.setText(savedInstanceState.getString(name_value_textViewAnswerShow11));
        textViewAnswerShow11.setText(savedInstanceState.getString(name_value_textViewAnswerShow12));
        textViewAnswerShowBasic.setText(savedInstanceState.getString(name_value_textViewAnswerShowBasic));
    }
//    private void saveTextViewAnswerShow(){
////         textViewAnswerShow7
//    }

    @Override
    protected void onPause() {
//        savePreferences();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadPreferences();

    }

    @Override
    protected void  onDestroy(){
        savePreferences();
        super.onDestroy();
    }


}
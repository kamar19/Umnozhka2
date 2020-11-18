package com.firstSet.MultiplyIt;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firstSet.umnozhka.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends Activity implements View.OnClickListener, SoundPool.OnLoadCompleteListener {
    // не сохраняемые
    Button buttonDigit1, buttonDigit2, buttonDigit3, buttonDigit4, buttonDigit5, buttonDigit6, buttonDigit7, buttonDigit8, buttonDigit9,
            buttonDigit0, buttonEnter, buttonBackSpace;
    private static SharedPreferences sharedPreferences;
    //    private static final String PREFERENCES_SETTINGS_NAME = "umnozhkaMain";
    ImageView view1, view2, view3, view4, view5;
    ProgressBar progressBar;
    private MyTask currentTask;

    private SoundPool soundPool;
//    private final int MAX_STREAMS = 5;
    private AssetManager assetManager;
    private final String LOG_TAG = "myLogs";
    private int soundIdExplosionYes, soundIdExplosionRemoveLive, soundIdExplosionAddLive;
//    private int  soundIdExplosion[]= new int  [10];

    private MyLesson myLesson;
    private MySettings mySettings;
//    public static LessonSummary lessonSummary;

//    private static int SETTINGS_TIME_BETWEEN_SESSIONS; // Время между сеансами
//    private static int SETTINGS_COUNT_TASK;            // Задач в сеанс
//    private static int SETTINGS_TIME_TASK;         // Время на одну задачу
//    private static int SETTINGS_TIME_SESSION;      // Время на один сеанс, после уменьшается
//    private static int PREFERENCES_SETTINGS_HEARTSLIVECOUNT;

    // параметры для данной активности, сохраняются в sharedPreferences, "umnozhkaMain"
    TextView textViewAnswerShow1, textViewAnswerShow2, textViewAnswerShow3, textViewAnswerShow4, textViewAnswerShow5, textViewAnswerShow6,
            textViewAnswerShow7, textViewAnswerShow8, textViewAnswerShow9, textViewAnswerShow10, textViewAnswerShow11, textViewAnswerShow12,
            textViewQuestion, textViewAnswerShowBasic, textViewAnswerCount, textViewPoints;

    @SuppressLint("SetTextI18n")
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

        view1 = findViewById(R.id.imageView1);
        view1.setImageResource(R.drawable.heart);
        view2 = findViewById(R.id.imageView2);
        view2.setImageResource(R.drawable.heart);
        view3 = findViewById(R.id.imageView3);
        view3.setImageResource(R.drawable.heart);
        view4 = findViewById(R.id.imageView4);
        view4.setImageResource(R.drawable.heart);
        view5 = findViewById(R.id.imageView5);
        view5.setImageResource(R.drawable.heart);

        textViewAnswerShow1 = findViewById(R.id.textViewAnswerShow1);
        textViewAnswerShow2 = findViewById(R.id.textViewAnswerShow2);
        textViewAnswerShow3 = findViewById(R.id.textViewAnswerShow3);
        textViewAnswerShow4 = findViewById(R.id.textViewAnswerShow4);
        textViewAnswerShow5 = findViewById(R.id.textViewAnswerShow5);
        textViewAnswerShow6 = findViewById(R.id.textViewAnswerShow6);
        textViewAnswerShow7 = findViewById(R.id.textViewAnswerShow7);
        textViewAnswerShow8 = findViewById(R.id.textViewAnswerShow8);
        textViewAnswerShow9 = findViewById(R.id.textViewAnswerShow9);
        textViewAnswerShow10 = findViewById(R.id.textViewAnswerShow10);
        textViewAnswerShow11 = findViewById(R.id.textViewAnswerShow11);
        textViewAnswerShow12 = findViewById(R.id.textViewAnswerShow12);
        textViewAnswerCount = findViewById(R.id.textViewAnswerCount);
        textViewAnswerShowBasic = findViewById(R.id.textViewAnswerShowBasic);
        textViewAnswerShowBasic = findViewById(R.id.textViewAnswerShowBasic);
        textViewQuestion = findViewById(R.id.textViewQuestion);
        String textViewQuestionString = textViewQuestion.getText().toString();
        textViewPoints = findViewById(R.id.textViewPoints);
        progressBar = findViewById(R.id.progressBar);
        mySettings = StartActivity.mySettings;
//        countHeartLive = 0;
        newSoundPools();
//        sharedPreferences = getSharedPreferences(PREFERENCES_SETTINGS_NAME, MODE_PRIVATE);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        myLesson = StartActivity.myLesson;
        mySettings = StartActivity.mySettings;

//        if (myLesson.isBeginGame()==true && myLesson.isEndGame()==false )
//            startNewLessonMainActivity();

        //нужно проверить если игры была продолжена и есть вопрос, то именно это вопрос и должен
        // быть задан, но проверка не в textViewQuestion, так как там значения по умолчанию
        if (myLesson.isBeginGame())
        // текущий сеанс был не завершен и не вызван BeginFinishLeassonActivity
        // когда myLesson.isEndGame()==true значит игра была завершена, но возможно не сохранена

        {
            loadValues();
            // Загружаем тексты, устанавливаем видимости в textView, view
            // myLesson.loadValuesMyLesson грузим в loadValues()
            // progressBar обновляем в конце конце onCreate();

            String stringIntotextViewQuestion = textViewQuestion.getText().toString();
//когда бывает loadValues загрузка?
//            сохраннехы значений
//                    не загружается textViewQuestion

            MyAct myAct = getCurrentTaskIntoQuestion(stringIntotextViewQuestion);
            switch (myAct.getMyAct()){
                case MULTIPLY:
                case DIVIDE:
                    currentTask = new MyTask(mySettings.getMinValue_SETTINGS_MULTIPLY(), mySettings.getMaxValue_SETTINGS_MULTIPLY(), myAct, mySettings.getSettingsMultiplys());
                    break;
                case ADD:
                case SUBTRAC:
                    currentTask = new MyTask(mySettings.getSettingsAddRangeMin(), mySettings.getSettingsAddRangeMax(), myAct, mySettings.getSettingsMultiplys());
                    break;
            }
            setCurrentTaskIntoQuestion(stringIntotextViewQuestion);
            //берем старый новый вопрос
        } else {
            // Если Игра не начато, т.е. новая
            act_to_currentTask();
            //генерируем новый вопрос
            textViewQuestion.setText(currentTask.getCurrentOneUnit().toString() + currentTask.getCurrentAct().toString() + currentTask.getCurrentTwoUnit().toString() + " = ");
//            if (myLesson.getCountAllPrimerov() == 0)
            // Если игра новая, то обновляем все поля
            startNewLessonMainActivity();
        }
        progressBar.setProgress(myLesson.getProgressBarCount());

        if (mySettings.isSettingsRecord()) {
            // создается таймер, нужно ли его переносить из конструктора?
            // наверное.... Создал класс MyService туда вставил Timer
            // пример взял из https://startandroid.ru/ru/uroki/vse-uroki-spiskom/163-urok-98-service-lokalnyj-binding.html
            // убрал из класса, так как не увидел смысла в нем.
            progressBar.setVisibility(ProgressBar.VISIBLE);
            isMyThreadRun = true;
            new Thread(myThread).start();
        } else {
            progressBar.setVisibility(ProgressBar.INVISIBLE);
        }
        myLesson.setEndGame(false);
        myLesson.setBeginGame(true);
        myLesson.saveValuesBeginAndEndGame(sharedPreferences);

        refrishIconLive();
    }

    private MyAct getCurrentTaskIntoQuestion(String stringQuestion) {
        MyAct myActResult = null;
        int countPosAct = getPosAct(stringQuestion, Act.MULTIPLY);
        if (countPosAct > 0) myActResult = new MyAct(Act.MULTIPLY);
        else {
            countPosAct = getPosAct(stringQuestion, Act.DIVIDE);
            if (countPosAct > 0) myActResult = new MyAct(Act.DIVIDE);
            else {
                countPosAct = getPosAct(stringQuestion, Act.ADD);
                if (countPosAct > 0) myActResult = new MyAct(Act.ADD);
                else {
                    countPosAct = getPosAct(stringQuestion, Act.SUBTRAC);
                    if (countPosAct > 0) myActResult = new MyAct(Act.SUBTRAC);
                }
            }
        }
        return myActResult;
    }

    private void setCurrentTaskIntoQuestion(String stringQuestion) {
        MyAct myActResult = null;
        int countPosAct = getPosAct(stringQuestion, Act.MULTIPLY);
        if (countPosAct > 0) myActResult = new MyAct(Act.MULTIPLY);
        else {
            countPosAct = getPosAct(stringQuestion, Act.DIVIDE);
            if (countPosAct > 0) myActResult = new MyAct(Act.DIVIDE);
            else {
                countPosAct = getPosAct(stringQuestion, Act.ADD);
                if (countPosAct > 0) myActResult = new MyAct(Act.ADD);
                else {
                    countPosAct = getPosAct(stringQuestion, Act.SUBTRAC);
                    if (countPosAct > 0) myActResult = new MyAct(Act.SUBTRAC);
                }
            }
        }
        // вытаскиваем значения чисел до знака "*/+-" и удаление пробелов
        String s = stringQuestion.substring(0, countPosAct).replaceAll("\\s", "");
        ;
        int oneUnit = Integer.parseInt(s);
        // вытаскиваем значения чисел после знака "*/+-", до "=" и удаление пробелов
        String s2 = stringQuestion.substring(countPosAct + 1, stringQuestion.indexOf("=")).replaceAll("\\s", "");
        ;
        int twoUnit = Integer.parseInt(s2);

        assert myActResult != null;
        if ((myActResult.getMyAct() == Act.ADD) | (myActResult.getMyAct() == Act.SUBTRAC))
        // Если Сложение или Вычитание
        {
            this.currentTask.setCurrentOneUnit(new MyNumberAddSub(1, 100, oneUnit));
            this.currentTask.setCurrentTwoUnit(new MyNumberAddSub(1, 100, twoUnit));
        } else {
            this.currentTask.setCurrentOneUnit(new MyNumberOneTen(1, 100, oneUnit));
            this.currentTask.setCurrentTwoUnit(new MyNumberOneTen(1, 100, twoUnit));
        }

    }

    private int getPosAct(String stringQuestion, Act act) {
        return stringQuestion.indexOf(act.getAct());
    }

    public void act_to_currentTask() {
        //временный currentAct, созданый что бы получить значение действия - getMyAct()
        // а потом уже изходя и0з действия создать currentTask

        MyAct currentAct = new MyAct(mySettings.isSettingsMultiply(), mySettings.isSettingsDivide(), mySettings.isSettingsAdd(), mySettings.isSettingsSubstrac());
        // сохраняем значения currentTask, для проверки на схожесть значений цифр и действий
        MyTask tempCurrentTask = currentTask;


        if ((currentAct.getMyAct() == Act.ADD) | (currentAct.getMyAct() == Act.SUBTRAC))
            // Если Сложение или Вычитание
            this.currentTask = new MyTask(mySettings.getSettingsAddRangeMin(), mySettings.getSettingsAddRangeMax(), currentAct, mySettings.getSettingsMultiplys());
        else
            // Если Умножение или деление
            this.currentTask = new MyTask(mySettings.getMinValue_SETTINGS_MULTIPLY(), mySettings.getMaxValue_SETTINGS_MULTIPLY(), currentAct, mySettings.getSettingsMultiplys());
        // проверить количество разрешенных цифр>2
        // два числа currentOneUnit и currentTwoUnit имеют один тип данных, и getCountBool() будет одинаковый
        if (currentTask.getCurrentTwoUnit().getCountBool() > 2)
            if (tempCurrentTask != null)
                if (currentTask.getCurrentAct() == tempCurrentTask.getCurrentAct() && currentTask.getCurrentOneUnit() == tempCurrentTask.getCurrentOneUnit() && currentTask.getCurrentTwoUnit() == tempCurrentTask.getCurrentTwoUnit()) {
                    // вызываем рекурсию, генерируем другие значения
                    act_to_currentTask();
                }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
//        if (!myLesson.isEndGame()||myLesson.isBeginGame()) {

        if (progressBar.getProgress() >= myLesson.getProgressBarTime())
            myLesson.setLastGame(true);

        if (!myLesson.isEndGame()) {
            // кнопочки должны нажиматься,но засчитыватьсяс значения не должны?
            // или сделать что бы при завершенном занятии сразу переход был на финишАктивити
            // endGame = false
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
                    if (tempQuestion.length()>0)
                    textViewAnswerShowBasic.setText(tempQuestion.substring(0, tempQuestion.length() - 1));
//                android:background="@drawable/baselinebackspaceblack18dp"
                    break;
                case R.id.buttonEnter:
                    //выполняется проверка Ответа на математический Вопрос
                    showAnswer();
                    //refrishDate();

                    if (myLesson.isLastGame()) {
                        // завершился progressBar LastGame=true
                        if (myLesson.getCountHeartLive() > 0) {
                            // Если есть жизни, то уменьшаем жизнь и ...
                            myLesson.setCountHeartLive(myLesson.getCountHeartLive() - 1);
                            myLesson.setCountCurrentWrongTask(0);
                            myLesson.setLastGame(false);
                            // продолжаем сеанс
                            myLesson.setProgressBarCount((int) myLesson.getProgressBarTime() / 3);
                            progressBar.setProgress(myLesson.getProgressBarCount());
                            refrishIconLive();
                        } else {
                            // Если урок закончен
                            String stringDate = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
                            StartActivity.startLessonSummary = new LessonSummary(myLesson.getUserNameDefault(), stringDate, myLesson.getCountPoints(), mySettings.getStringMDSA(), mySettings.getStringMultiplyNumbers());
                            myLesson.setEndGame(true);
                            // Останавливаем игру
                            saveValues();
                            // сохранение результатов и загрузка FinishLeassonActivity
                            Intent intent = new Intent(this, FinishLeassonActivity.class);
                            intent.putExtra(StartActivity.STRING_COUNT_ALL_PRIMEROV, myLesson.getStringCountAllPrimerov());
                            startActivity(intent);
                            isMyThreadRun = false;
                            // закрытие окна MainActivity
                            finish();
                        }
                    }
                    act_to_currentTask();
                    // получаем новые значения для currentTask
                    // выводим вопрос в текстовое поле
                    textViewQuestion.setText(currentTask.getCurrentOneUnit().toString() + currentTask.getCurrentAct().toString() + currentTask.getCurrentTwoUnit().toString() + " = ");
                    textViewAnswerShowBasic.setText("");

                    break;
            }
        }
    }

    private String setRightTask(int countPoints) {
        // Если правельных ответов в подряд 5, то дается одна жизнь.
        myLesson.setCountRightTask(myLesson.getCountRightTask() + 1);
        myLesson.setCountCurrentRightTask(myLesson.getCountCurrentRightTask() + 1);
        myLesson.setCountPoints(myLesson.getCountPoints() + countPoints);


        if (myLesson.getCountCurrentRightTask() > 4) {
            if (myLesson.getCountHeartLive() < 5) {
                myLesson.setCountHeartLive(myLesson.getCountHeartLive() + 1);
                if (soundPool != null) {
                    soundPool.play(soundIdExplosionAddLive, 1, 1, 1, 0, 1);
                }
                //                sp.play(soundIdExplosion, 1, 1, 0, 0, 1);
                myLesson.setCountCurrentRightTask(0);
                myLesson.setCountCurrentWrongTask(0);
                refrishIconLive();
            }
            myLesson.setProgressBarSpeed(myLesson.getProgressBarSpeed() * 2);
        } else if (soundPool != null) {
//            soundPool.play(soundIdExplosion[(int) (Math.random() * 9)], 1, 1, 1, 0, 1);
            soundPool.play(soundIdExplosionYes, 1, 1, 1, 0, 1);
        }

        return getString(R.string.textRightTask);
    }

    private String setWrongTask() {
        // Установка ошибки
        // Если неправельных ответов в подряд 3, то отнимается одна жизнь.
        myLesson.setCountWrongTask(myLesson.getCountWrongTask() + 1);
        myLesson.setCountCurrentWrongTask(myLesson.getCountCurrentWrongTask() + 1);
        myLesson.setCountCurrentRightTask(0);
        if (myLesson.getCountCurrentWrongTask() > 2) {
            if (myLesson.getCountHeartLive() > 0) {
                myLesson.setCountHeartLive(myLesson.getCountHeartLive() - 1);
                if (soundPool != null)
                    soundPool.play(soundIdExplosionRemoveLive, 1, 1, 1, 0, 1);
                myLesson.setCountCurrentWrongTask(0);
                refrishIconLive();

            }
            if (myLesson.getCountCurrentWrongTask() > 5) {
                if (myLesson.getProgressBarSpeed() > 2)
                    myLesson.setProgressBarSpeed((int) myLesson.getProgressBarSpeed() / 2);
            }
        }
        return getString(R.string.textWrongTask);
    }


    private void showAnswer() {
        if (!myLesson.isEndGame()) {
            // если занятие не завершено
            int intAnswer;
            String answer = "";
            try {
                intAnswer = Integer.parseInt(textViewAnswerShowBasic.getText().toString());
            } catch (NumberFormatException nfe) {
                intAnswer = 0;
            }
            ;
            if (intAnswer != 0) {
                switch (currentTask.getCurrentAct().getMyAct()) {
                    case MULTIPLY: {
                        if (currentTask.getCurrentOneUnit().getValue() * currentTask.getCurrentTwoUnit().getValue() == intAnswer)
                            answer = setRightTask(intAnswer);
                        else answer = setWrongTask();
                        break;
                    }
                    case DIVIDE: {
                        if (currentTask.getCurrentOneUnit().getValue() / currentTask.getCurrentTwoUnit().getValue() == intAnswer)                 // размещаем ответ
                            answer = setRightTask(currentTask.getCurrentOneUnit().getValue());
                        else
                            answer = setWrongTask();
                        break;
                    }
                    case ADD: {
                        if (currentTask.getCurrentOneUnit().getValue() + currentTask.getCurrentTwoUnit().getValue() == intAnswer)                 // размещаем ответ
                            answer = setRightTask(intAnswer);
                        else
                            answer = setWrongTask();
                        break;
                    }
                    case SUBTRAC: {
                        if (currentTask.getCurrentOneUnit().getValue() - currentTask.getCurrentTwoUnit().getValue() == intAnswer)                 // размещаем ответ
                            answer = setRightTask(currentTask.getCurrentOneUnit().getValue());
                        else
                            answer = setWrongTask();
                        break;
                    }
                }
            } else
                answer = setWrongTask();
            showViewAnswerShow(answer, intAnswer);
            myLesson.setCountPrimerov(myLesson.getCountPrimerov() + 1);
            myLesson.setCountAllPrimerov(myLesson.getCountAllPrimerov() + 1);
            textViewAnswerCount.setText(getString(R.string.titleAllTask) + " " + myLesson.getCountAllPrimerov() + getString(R.string.titleRightTask) + " "
                    + myLesson.getCountRightTask() + getString(R.string.titleWrongTask) + " " + myLesson.getCountWrongTask() + "    ");
            textViewPoints.setText(String.valueOf(myLesson.getCountPoints()));
        }

    }

    private void invisibleTextViewAnswer() {
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


    private void showViewAnswerShow(String prav, int intAnswer) {
        // Делаем видимым соответствующий textViewAnswerShow
        // и показываем в нем результат
        // String prav - текстовая оценка результата, ошибка или верно.
        // int intAnswer - числое значение результата
        Act deist = currentTask.getCurrentAct().getMyAct();
        String tempText = currentTask.getCurrentOneUnit().toString() + deist.getAct() + currentTask.getCurrentTwoUnit().toString() + '=' + String.valueOf(intAnswer);
        if (tempText.length()<9)
            tempText = tempText+ " " + prav;
        else tempText = tempText+ " " + prav.substring(0,2);

        switch (myLesson.getCountPrimerov()) {
            // можно попробовать убрать громоздкую конгструкцию из 12 textViewAnswerShow1
            // и сделать массив из textViewAnswerShow, но это усложнит их наименование через string.xml
            case 1: {
                invisibleTextViewAnswer();
                textViewAnswerShow1.setText(tempText);
                break;
            }
            case 2: {
                textViewAnswerShow4.setVisibility(View.VISIBLE);
                textViewAnswerShow4.setText(tempText);
                break;
            }
            case 3: {
                textViewAnswerShow7.setVisibility(View.VISIBLE);
                textViewAnswerShow7.setText(tempText);
                break;
            }
            case 4: {
                textViewAnswerShow10.setVisibility(View.VISIBLE);
                textViewAnswerShow10.setText(tempText);
                break;
            }
            case 5: {
                textViewAnswerShow2.setVisibility(View.VISIBLE);
                textViewAnswerShow2.setText(tempText);
                break;
            }
            case 6: {
                textViewAnswerShow5.setVisibility(View.VISIBLE);
                textViewAnswerShow5.setText(tempText);
                break;
            }
            case 7: {
                textViewAnswerShow8.setVisibility(View.VISIBLE);
                textViewAnswerShow8.setText(tempText);
                break;
            }
            case 8: {
                textViewAnswerShow11.setVisibility(View.VISIBLE);
                textViewAnswerShow11.setText(tempText);
                ;
                break;
            }
            case 9: {
                textViewAnswerShow3.setVisibility(View.VISIBLE);
                textViewAnswerShow3.setText(tempText);
                break;
            }
            case 10: {
                textViewAnswerShow6.setVisibility(View.VISIBLE);
                textViewAnswerShow6.setText(tempText);
                break;
            }
            case 11: {
                textViewAnswerShow9.setVisibility(View.VISIBLE);
                textViewAnswerShow9.setText(tempText);
                break;
            }
            case 12: {
                textViewAnswerShow12.setVisibility(View.VISIBLE);
                textViewAnswerShow12.setText(tempText);
                myLesson.setCountPrimerov(0);
                break;
            }
        }
    }

    private void refrishIconLive() {

        if (myLesson.getCountHeartLive() > 5) myLesson.setCountHeartLive(5);
        if (myLesson.getCountHeartLive() < 0) myLesson.setCountHeartLive(0);

        if (mySettings.isSettingsRecord()) {
            switch (myLesson.getCountHeartLive()) {
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

    private boolean isMyThreadRun = false;

    private Runnable myThread = new Runnable() {
        @Override
        public void run() {
//            while (!myLesson.isLastGame()||myLesson.isEndGame()) {

            while (!myLesson.isLastGame() && isMyThreadRun) {
                // пока myLesson.isLastGame()=false
                try {
                    myHandler.sendMessage(myHandler.obtainMessage());
                    Thread.sleep(1000);
                    //секундный интервал, что бы был тайминг
                    if (progressBar.getProgress() >= myLesson.getProgressBarTime()) {
//                      // когда progressBar завершается LastGame = true
                        myLesson.setLastGame(true);

                    }
                } catch (Throwable t) {
                }
            }
//            if (myLesson.isLastGame())

        }

        final Handler myHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                myLesson.upInterval(myLesson.getProgressBarSpeed());
                if (myLesson.getProgressBarCount() >= myLesson.getProgressBarTime()) {
                    if (myLesson.getCountHeartLive() > 0) {
                        myLesson.setCountHeartLive(myLesson.getCountHeartLive() - 1);
                        myLesson.setProgressBarCount((int) myLesson.getProgressBarTime() / 3);
                        refrishIconLive();
                        if (soundPool != null)
                            soundPool.play(soundIdExplosionRemoveLive, 1, 1, 1, 0, 1);

                    }
                }
                progressBar.setProgress(myLesson.getProgressBarCount());
            }
        };
    };

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    public Object onRetainNonConfigurationInstance() {
        return R.layout.activity_main;
    }

    private void loadValues() {
        if (sharedPreferences == null)
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        textViewAnswerShow1.setText(sharedPreferences.getString("valueTextViewAnswerShow1", ""));
        textViewAnswerShow2.setText(sharedPreferences.getString("valueTextViewAnswerShow2", ""));
        textViewAnswerShow3.setText(sharedPreferences.getString("valueTextViewAnswerShow3", ""));
        textViewAnswerShow4.setText(sharedPreferences.getString("valueTextViewAnswerShow4", ""));
        textViewAnswerShow5.setText(sharedPreferences.getString("valueTextViewAnswerShow5", ""));
        textViewAnswerShow6.setText(sharedPreferences.getString("valueTextViewAnswerShow6", ""));
        textViewAnswerShow7.setText(sharedPreferences.getString("valueTextViewAnswerShow7", ""));
        textViewAnswerShow8.setText(sharedPreferences.getString("valueTextViewAnswerShow8", ""));
        textViewAnswerShow9.setText(sharedPreferences.getString("valueTextViewAnswerShow9", ""));
        textViewAnswerShow10.setText(sharedPreferences.getString("valueTextViewAnswerShow10", ""));
        textViewAnswerShow11.setText(sharedPreferences.getString("valueTextViewAnswerShow11", ""));
        textViewAnswerShow12.setText(sharedPreferences.getString("valueTextViewAnswerShow12", ""));
        textViewAnswerCount.setText(sharedPreferences.getString("valueTextViewAnswerCount", ""));
        textViewAnswerShowBasic.setText(sharedPreferences.getString("valueTextViewAnswerShowBasic", ""));
        textViewQuestion.setText(sharedPreferences.getString("valueTextViewQuestion", ""));
        textViewPoints.setText(sharedPreferences.getString("valueTextViewPoints", ""));
        textViewAnswerShow1.setVisibility(Integer.valueOf(sharedPreferences.getString("visibleTextViewAnswerShow1", "0")));
        textViewAnswerShow2.setVisibility(Integer.valueOf(sharedPreferences.getString("visibleTextViewAnswerShow2", "0")));
        textViewAnswerShow3.setVisibility(Integer.valueOf(sharedPreferences.getString("visibleTextViewAnswerShow3", "0")));
        textViewAnswerShow4.setVisibility(Integer.valueOf(sharedPreferences.getString("visibleTextViewAnswerShow4", "0")));
        textViewAnswerShow5.setVisibility(Integer.valueOf(sharedPreferences.getString("visibleTextViewAnswerShow5", "0")));
        textViewAnswerShow6.setVisibility(Integer.valueOf(sharedPreferences.getString("visibleTextViewAnswerShow6", "0")));
        textViewAnswerShow7.setVisibility(Integer.valueOf(sharedPreferences.getString("visibleTextViewAnswerShow7", "0")));
        textViewAnswerShow8.setVisibility(Integer.valueOf(sharedPreferences.getString("visibleTextViewAnswerShow8", "0")));
        textViewAnswerShow9.setVisibility(Integer.valueOf(sharedPreferences.getString("visibleTextViewAnswerShow9", "0")));
        textViewAnswerShow10.setVisibility(Integer.valueOf(sharedPreferences.getString("visibleTextViewAnswerShow10", "0")));
        textViewAnswerShow11.setVisibility(Integer.valueOf(sharedPreferences.getString("visibleTextViewAnswerShow11", "0")));
        textViewAnswerShow12.setVisibility(Integer.valueOf(sharedPreferences.getString("visibleTextViewAnswerShow12", "0")));
        view1.setVisibility(Integer.valueOf(sharedPreferences.getString("visibleView1", "1")));
        view2.setVisibility(Integer.valueOf(sharedPreferences.getString("visibleView2", "1")));
        view3.setVisibility(Integer.valueOf(sharedPreferences.getString("visibleView3", "1")));
        view4.setVisibility(Integer.valueOf(sharedPreferences.getString("visibleView4", "1")));
        view5.setVisibility(Integer.valueOf(sharedPreferences.getString("visibleView5", "1")));
        mySettings.loadValuesMySettings(sharedPreferences);
        myLesson.loadValuesMyLesson(sharedPreferences);
        progressBar.setMax(myLesson.getProgressBarTime());
    }

    private void saveValues() {
        SharedPreferences.Editor editorSharedPreferences = sharedPreferences.edit();
        editorSharedPreferences.putString("valueTextViewAnswerShow1", textViewAnswerShow1.getText().toString());
        editorSharedPreferences.putString("valueTextViewAnswerShow2", textViewAnswerShow2.getText().toString());
        editorSharedPreferences.putString("valueTextViewAnswerShow3", textViewAnswerShow3.getText().toString());
        editorSharedPreferences.putString("valueTextViewAnswerShow4", textViewAnswerShow4.getText().toString());
        editorSharedPreferences.putString("valueTextViewAnswerShow5", textViewAnswerShow5.getText().toString());
        editorSharedPreferences.putString("valueTextViewAnswerShow6", textViewAnswerShow6.getText().toString());
        editorSharedPreferences.putString("valueTextViewAnswerShow7", textViewAnswerShow7.getText().toString());
        editorSharedPreferences.putString("valueTextViewAnswerShow8", textViewAnswerShow8.getText().toString());
        editorSharedPreferences.putString("valueTextViewAnswerShow9", textViewAnswerShow9.getText().toString());
        editorSharedPreferences.putString("valueTextViewAnswerShow10", textViewAnswerShow10.getText().toString());
        editorSharedPreferences.putString("valueTextViewAnswerShow11", textViewAnswerShow11.getText().toString());
        editorSharedPreferences.putString("valueTextViewAnswerShow12", textViewAnswerShow12.getText().toString());
        editorSharedPreferences.putString("valueTextViewAnswerCount", textViewAnswerCount.getText().toString());
        editorSharedPreferences.putString("valueTextViewAnswerShowBasic", textViewAnswerShowBasic.getText().toString());
        editorSharedPreferences.putString("valueTextViewQuestion", textViewQuestion.getText().toString());
        editorSharedPreferences.putString("valueTextViewPoints", textViewPoints.getText().toString());
        editorSharedPreferences.putString("visibleTextViewAnswerShow1", String.valueOf(textViewAnswerShow1.getVisibility()));
        editorSharedPreferences.putString("visibleTextViewAnswerShow2", String.valueOf(textViewAnswerShow2.getVisibility()));
        editorSharedPreferences.putString("visibleTextViewAnswerShow3", String.valueOf(textViewAnswerShow3.getVisibility()));
        editorSharedPreferences.putString("visibleTextViewAnswerShow4", String.valueOf(textViewAnswerShow4.getVisibility()));
        editorSharedPreferences.putString("visibleTextViewAnswerShow5", String.valueOf(textViewAnswerShow5.getVisibility()));
        editorSharedPreferences.putString("visibleTextViewAnswerShow6", String.valueOf(textViewAnswerShow6.getVisibility()));
        editorSharedPreferences.putString("visibleTextViewAnswerShow7", String.valueOf(textViewAnswerShow7.getVisibility()));
        editorSharedPreferences.putString("visibleTextViewAnswerShow8", String.valueOf(textViewAnswerShow8.getVisibility()));
        editorSharedPreferences.putString("visibleTextViewAnswerShow9", String.valueOf(textViewAnswerShow9.getVisibility()));
        editorSharedPreferences.putString("visibleTextViewAnswerShow10", String.valueOf(textViewAnswerShow10.getVisibility()));
        editorSharedPreferences.putString("visibleTextViewAnswerShow11", String.valueOf(textViewAnswerShow11.getVisibility()));
        editorSharedPreferences.putString("visibleTextViewAnswerShow12", String.valueOf(textViewAnswerShow12.getVisibility()));
        editorSharedPreferences.putString("visibleView1", String.valueOf(view1.getVisibility()));
        editorSharedPreferences.putString("visibleView2", String.valueOf(view2.getVisibility()));
        editorSharedPreferences.putString("visibleView3", String.valueOf(view3.getVisibility()));
        editorSharedPreferences.putString("visibleView4", String.valueOf(view4.getVisibility()));
        editorSharedPreferences.putString("visibleView5", String.valueOf(view5.getVisibility()));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            editorSharedPreferences.apply();
        } else editorSharedPreferences.commit();
        myLesson.saveValuesMyLesson(sharedPreferences);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadValues();
        isMyThreadRun = true;
    }

    @Override
    protected void onDestroy() {
        saveValues();
        super.onDestroy();
        isMyThreadRun = false;

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        saveValues();
        isMyThreadRun = false;
    }

    @Override
    protected void onPause() {
        saveValues();
        super.onPause();
        isMyThreadRun = false;
    }

    private void startNewLessonMainActivity() {
        myLesson.startNewLesson();
        textViewAnswerShow1.setText("");
        textViewAnswerShow2.setText("");
        textViewAnswerShow3.setText("");
        textViewAnswerShow4.setText("");
        textViewAnswerShow5.setText("");
        textViewAnswerShow6.setText("");
        textViewAnswerShow7.setText("");
        textViewAnswerShow8.setText("");
        textViewAnswerShow9.setText("");
        textViewAnswerShow10.setText("");
        textViewAnswerShow11.setText("");
        textViewAnswerShow12.setText("");
        textViewPoints.setText("");
        textViewAnswerCount.setText(getString(R.string.titleAllTask) + " " + myLesson.getCountAllPrimerov() + getString(R.string.titleRightTask) + " "
                + myLesson.getCountRightTask() + getString(R.string.titleWrongTask) + " " + myLesson.getCountWrongTask() + "    ");
//        textViewAnswerShowBasic.getText().toString());
//        textViewQuestion.getText().toString());
        textViewAnswerShow1.setVisibility(TextView.INVISIBLE);
        textViewAnswerShow2.setVisibility(TextView.INVISIBLE);
        textViewAnswerShow3.setVisibility(TextView.INVISIBLE);
        textViewAnswerShow4.setVisibility(TextView.INVISIBLE);
        textViewAnswerShow5.setVisibility(TextView.INVISIBLE);
        textViewAnswerShow6.setVisibility(TextView.INVISIBLE);
        textViewAnswerShow7.setVisibility(TextView.INVISIBLE);
        textViewAnswerShow8.setVisibility(TextView.INVISIBLE);
        textViewAnswerShow9.setVisibility(TextView.INVISIBLE);
        textViewAnswerShow10.setVisibility(TextView.INVISIBLE);
        textViewAnswerShow11.setVisibility(TextView.INVISIBLE);
        textViewAnswerShow12.setVisibility(TextView.INVISIBLE);
        view1.setVisibility(View.INVISIBLE);
        view2.setVisibility(View.INVISIBLE);
        view3.setVisibility(View.INVISIBLE);
        view4.setVisibility(View.INVISIBLE);
        view5.setVisibility(View.INVISIBLE);

        saveValues();
        refrishIconLive();
    }

    private int loadSound(String fileName) {
        AssetFileDescriptor afd;
        try {
            assetManager = getAssets();
            afd = assetManager.openFd(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Не могу загрузить файл " + fileName,
                    Toast.LENGTH_SHORT).show();
            return -1;
        }
        return soundPool.load(afd, 1);
    }

    public void newSoundPools() {
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            // Для устройств до Android 5
            createOldSoundPool();
        } else {
            // Для новых устройств
            createNewSoundPool();
//            soundIdExplosionYes = loadSound("shot.ogg");
            soundIdExplosionYes = loadSound("shot.ogg");

            soundIdExplosionAddLive = loadSound("Sound_11086.wav");
            soundIdExplosionRemoveLive = loadSound("Sound_11500.wav");

        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void createNewSoundPool() {
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        soundPool = new SoundPool.Builder()
                .setAudioAttributes(attributes)
                .build();
    }

    @SuppressWarnings("deprecation")
    private void createOldSoundPool() {
        soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
    }

    @Override
    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
        Log.d(LOG_TAG, "onLoadComplete, sampleId = " + sampleId + ", status = " + status);

    }


    public MyLesson getMyLesson() {
        return myLesson;
    }

//    private String createImageNameFile() throws IOException {
//        // Create an image file name
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String imageFileName = "foto_" + timeStamp + "_";
//        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//        File image = File.createTempFile(
//                imageFileName,  /* prefix */
//                ".jpg",         /* suffix */
//                storageDir      /* directory */
//        );
//
//        // Save a file: path for use with ACTION_VIEW intents
////        this.imageFileName = image.getAbsolutePath();
//        return image.getAbsolutePath();
//    }

}
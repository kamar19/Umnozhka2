package com.example.umnozhka;

import android.annotation.TargetApi;
import android.app.Activity;
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

import java.io.IOException;


public class MainActivity extends Activity implements View.OnClickListener, SoundPool.OnLoadCompleteListener {
    // не сохраняемые
    Button buttonDigit1, buttonDigit2, buttonDigit3, buttonDigit4, buttonDigit5, buttonDigit6, buttonDigit7, buttonDigit8, buttonDigit9,
            buttonDigit0, buttonEnter, buttonBackSpace;
    private static SharedPreferences sharedPreferences;
    //    private static final String PREFERENCES_SETTINGS_NAME = "umnozhkaMain";
    ImageView view1, view2, view3, view4, view5;
    ProgressBar progressBar;
    private MyTask currentTask;
    private AssetManager assetManager;
    private SoundPool soundPool;
    //    private int soundIdShot;
    private int soundIdExplosion, soundIdExplosion2;
    private final int MAX_STREAMS = 5;
    private final String LOG_TAG = "myLogs";

    // общие параметры для приложения, сохраняются в sharedPreferences, PREFERENCES_SETTINGS_NAME = "umnozhka_Settings"
    private static boolean[] SETTINGS_MULTIPLYS = {false, false, false, false, false, false, false, false, false, false};
    private static boolean SETTINGS_SUBTRAC;    // Сложение
    private static boolean SETTINGS_ADD;        // Вычитание
    private static boolean SETTINGS_MULTIPLY;   // Умножение
    private static boolean SETTINGS_DIVIDE;     // Деление
    private static int SETTINGS_ADD_RANGE_MIN;  // Начало диапозона сложения
    private static int SETTINGS_ADD_RANGE_MAX;  // Конец диапозона сложения
    private static boolean SETTINGS_RECORD;
    private static boolean SETTINGS_SOUND;
//    private static int SETTINGS_TIME_BETWEEN_SESSIONS; // Время между сеансами
//    private static int SETTINGS_COUNT_TASK;            // Задач в сеанс
//    private static int SETTINGS_TIME_TASK;         // Время на одну задачу
//    private static int SETTINGS_TIME_SESSION;      // Время на один сеанс, после уменьшается
//    private static int PREFERENCES_SETTINGS_HEARTSLIVECOUNT;

    // параметры для данной активности, сохраняются в sharedPreferences, "umnozhkaMain"
    TextView textViewAnswerShow1, textViewAnswerShow2, textViewAnswerShow3, textViewAnswerShow4, textViewAnswerShow5, textViewAnswerShow6,
            textViewAnswerShow7, textViewAnswerShow8, textViewAnswerShow9, textViewAnswerShow10, textViewAnswerShow11, textViewAnswerShow12,
            textViewQuestion, textViewAnswerShowBasic, textViewAnswerCount;
    private static int countHeartLive = 0;
    private static int countAllPrimerov = 0;
    private static int countRightTask = 0, countWrongTask = 0;
    private static int countCurrentRightTask = 0, countCurrentWrongTask = 0;

    private int progressBarTime = 300;
    private int progressBarCount = 1;
    private int countPrimerov = 1;
    private int progressBarSpeed = 1;
    private String stringCurrentAct = "*";
    private boolean endGame = false;
    private boolean lastGame = false;

//    private static String valueTextViewAnswerShow1 = "value_textViewAnswerShow1";
//    private static String valueTextViewAnswerShow2 = "value_textViewAnswerShow2";
//    private static String valueTextViewAnswerShow3 = "value_textViewAnswerShow3";
//    private static String valueTextViewAnswerShow4 = "value_textViewAnswerShow4";
//    private static String valueTextViewAnswerShow5 = "value_textViewAnswerShow5";
//    private static String valueTextViewAnswerShow6 = "value_textViewAnswerShow6";
//    private static String valueTextViewAnswerShow7 = "value_textViewAnswerShow7";
//    private static String valueTextViewAnswerShow8 = "value_textViewAnswerShow8";
//    private static String valueTextViewAnswerShow9 = "value_textViewAnswerShow9";
//    private static String valueTextViewAnswerShow10 = "value_textViewAnswerShow10";
//    private static String valueTextViewAnswerShow11 = "value_textViewAnswerShow11";
//    private static String valueTextViewAnswerShow12 = "value_textViewAnswerShow12";
//    private static String valueTextViewAnswerShowBasic = "value_textViewAnswerShowBasic";


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
        progressBar = findViewById(R.id.progressBar);


        SETTINGS_MULTIPLYS = StartActivity.SETTINGS_MULTIPLYS;
        SETTINGS_SUBTRAC = StartActivity.SETTINGS_SUBTRAC;    // Сложение
        SETTINGS_ADD = StartActivity.SETTINGS_ADD;        // Вычитание
        SETTINGS_MULTIPLY = StartActivity.SETTINGS_MULTIPLY;   // Умножение
        SETTINGS_DIVIDE = StartActivity.SETTINGS_DIVIDE;     // Деление
        SETTINGS_ADD_RANGE_MIN = StartActivity.SETTINGS_ADD_RANGE_MIN;  // Начало диапозона сложения
        SETTINGS_ADD_RANGE_MAX = StartActivity.SETTINGS_ADD_RANGE_MAX;  // Конец диапозона сложения
        SETTINGS_RECORD = StartActivity.SETTINGS_RECORD;
        SETTINGS_SOUND = StartActivity.SETTINGS_SOUND;
        countHeartLive = 0;
        newSoundPools();
//        sharedPreferences = getSharedPreferences(PREFERENCES_SETTINGS_NAME, MODE_PRIVATE);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        if (sharedPreferences != null) {
            if (!endGame) {
                loadValues();
            } else
                startNewGame();
        } else {
            // Первая загрузка значений по умолчанию

//            saveValues();
        }

        if (SETTINGS_RECORD) {
            // создается таймер, нужно ли его переносить из конструктора?
            // наверное.... Создал класс MyService туда вставил Timer
            // пример взял из https://startandroid.ru/ru/uroki/vse-uroki-spiskom/163-urok-98-service-lokalnyj-binding.html
            // убрал из класса, так как не увидел смысла в нем.

            progressBar.setVisibility(ProgressBar.VISIBLE);
            new Thread(myThread).start();

        } else {
            progressBar.setVisibility(ProgressBar.INVISIBLE);
        }
        act_to_currentTask();
        textViewQuestion.setText(currentTask.getCurrentOneUnit().toString() + currentTask.getCurrentAct().toString() + currentTask.getCurrentTwoUnit().toString() + " = ");
        refrishIconLive();
    }

    public void act_to_currentTask() {
        MyAct currentAct = new MyAct(SETTINGS_MULTIPLY, SETTINGS_DIVIDE, SETTINGS_ADD, SETTINGS_SUBTRAC);
        //временный currentAct, созданый что бы получить значение действия - getMyAct()
        // а потом уже изходя из действия создать currentTask
        if ((currentAct.getMyAct() == Act.ADD) | (currentAct.getMyAct() == Act.SUBTRAC))
            // Если Сложение или Вычитание
            this.currentTask = new MyTask(SETTINGS_ADD_RANGE_MIN, SETTINGS_ADD_RANGE_MAX, currentAct, SETTINGS_MULTIPLYS);
        else
            // Если Умножение или деление
            this.currentTask = new MyTask(StartActivity.getMinValue_SETTINGS_MULTIPLY(), StartActivity.getMaxValue_SETTINGS_MULTIPLY(), currentAct, SETTINGS_MULTIPLYS);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.um_menu, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.menu_report:
////                пока не реализованно, но нужно сделать отчет, как бы журнал с ответами
//                return true;
//            case R.id.menu_Preferences:
////                item.setIntent(new Intent(this, PrefActivity.class));
//                Intent intent = new Intent(this, PrefActivity.class);
//                startActivity(intent);
//                return true;
//            case R.id.menu_Exit:
//                this.finish();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

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
                    if (lastGame)
                        endGame = true;
                    break;
            }
        }
    }

    private String setRightTask() {
        // Если правельных ответов в подряд 5, то дается одна жизнь.
        countRightTask++;
        countCurrentRightTask++;
        if (soundPool != null) {
            soundPool.play(soundIdExplosion, 1, 1, 1, 0, 1);
        }

        if (countCurrentRightTask > 4) {
            if (countHeartLive < 5) {
                countHeartLive++;
                if (soundPool != null) {
                    soundPool.play(soundIdExplosion2, 1, 1, 1, 0, 1);
                }
                //                sp.play(soundIdExplosion, 1, 1, 0, 0, 1);
                countCurrentRightTask = 0;
                countCurrentWrongTask = 0;
                refrishIconLive();
            }
            progressBarSpeed = progressBarSpeed * 2;
        }
        return getString(R.string.textRightTask);
    }

    private String setWrongTask() {
        // Установка ошибки
        // Если неправельных ответов в подряд 3, то отнимается одна жизнь.
        countWrongTask++;
        countCurrentWrongTask++;
        countCurrentRightTask = 0;
        if (countCurrentWrongTask > 2) {
            if (countHeartLive > 0) {
                countHeartLive--;
                countCurrentWrongTask = 0;
                refrishIconLive();
            }
            if (countCurrentWrongTask > 5) {
                if (progressBarSpeed > 2) progressBarSpeed = (int) progressBarSpeed / 2;
            }
        }

        return getString(R.string.textWrongTask);
    }

    private void showAnswer() {
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
                        answer = setRightTask();
                    else answer = setWrongTask();
                    break;
                }
                case DIVIDE: {
                    if (currentTask.getCurrentOneUnit().getValue() / currentTask.getCurrentTwoUnit().getValue() == intAnswer)                 // размещаем ответ
                        answer = setRightTask();
                    else
                        answer = setWrongTask();
                    break;
                }
                case ADD: {
                    if (currentTask.getCurrentOneUnit().getValue() + currentTask.getCurrentTwoUnit().getValue() == intAnswer)                 // размещаем ответ
                        answer = setRightTask();
                    else
                        answer = setWrongTask();
                    break;
                }
                case SUBTRAC: {
                    if (currentTask.getCurrentOneUnit().getValue() - currentTask.getCurrentTwoUnit().getValue() == intAnswer)                 // размещаем ответ
                        answer = setRightTask();
                    else
                        answer = setWrongTask();
                    break;
                }
            }
        } else
            answer = setWrongTask();
        showViewAnswerShow(answer, intAnswer);
        textViewAnswerCount.setText(getString(R.string.titleAllTask) + " " + countAllPrimerov + getString(R.string.titleRightTask) + " "
                + countRightTask + getString(R.string.titleWrongTask) + " " + countWrongTask + "    ");

//            progressBar.setProgress(countPrimerov);

        countPrimerov++;
        countAllPrimerov++;
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
        String tempText = currentTask.getCurrentOneUnit().toString() + deist.getAct() + currentTask.getCurrentTwoUnit().toString() + '=' + String.valueOf(intAnswer) + " " + prav;
        switch (countPrimerov) {
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
                countPrimerov = 0;
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

    private Runnable myThread = new Runnable() {
        @Override
        public void run() {
            while (!lastGame) {
                try {
                    myHandler.sendMessage(myHandler.obtainMessage());
                    Thread.sleep(1000);
                    //секундный интервал, что бы был тайминг
                    if (progressBar.getProgress() >= progressBarTime) {
//                        // Сюда нужно встаивть вызов окна завершения игры
                        lastGame = true;
                    }
                } catch (Throwable t) {
                }
            }
        }

        Handler myHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                upInterval(progressBarSpeed);
                if (progressBarCount >= progressBarTime) {
                    if (countHeartLive > 0) {
                        countHeartLive--;
                        progressBarCount = (int) progressBarTime / 6;
                        refrishIconLive();
                    }
                }

                progressBar.setProgress(progressBarCount);

            }
        };
    };

    public int upInterval(int step) {
        progressBarCount = progressBarCount + step;
        return progressBarCount;
    }

    public int downInterval(int step) {
        progressBarCount = progressBarCount - step;
        if (progressBarCount < 0) progressBarCount = 0;
        return progressBarCount;
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

    public boolean newSoundPools() {
        if (SETTINGS_SOUND) {
            if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                // Для устройств до Android 5
                createOldSoundPool();
            } else {
                // Для новых устройств
                createNewSoundPool();
            }
//            soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
//            soundPool.setOnLoadCompleteListener(this);

//        soundIdShot = sp.load(this, getAssets().openFd("explosion.ogg"), 1);
//        getAssets().openFd("explosion.ogg"),
//        Log.d(LOG_TAG, "soundIdShot = " + soundIdShot);
//            soundIdExplosion = loadSound("bcushp_ui-149.wav");
            soundIdExplosion = loadSound("shot.ogg");
//            soundIdExplosion2 = loadSound("bcushp_ui-149.wav");
            soundIdExplosion2 = loadSound("explosion.ogg");

            //        Log.d(LOG_TAG, "soundIdExplosion = " + soundIdExplosion);
        }
        return SETTINGS_SOUND;
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


    @Override
    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
        Log.d(LOG_TAG, "onLoadComplete, sampleId = " + sampleId + ", status = " + status);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // но если нажимается кнопка back - возврат, то не сохраняются значения.
        // попробую все значения переменных и объектов сохранять в  sharedPreferences

//        outState.putString(valueTextViewAnswerShow1, textViewAnswerShow1.getText().toString());
//        outState.putString(valueTextViewAnswerShow2, textViewAnswerShow2.getText().toString());
//        outState.putString(valueTextViewAnswerShow3, textViewAnswerShow3.getText().toString());
//        outState.putString(valueTextViewAnswerShow4, textViewAnswerShow4.getText().toString());
//        outState.putString(valueTextViewAnswerShow5, textViewAnswerShow5.getText().toString());
//        outState.putString(valueTextViewAnswerShow6, textViewAnswerShow6.getText().toString());
//        outState.putString(valueTextViewAnswerShow7, textViewAnswerShow7.getText().toString());
//        outState.putString(valueTextViewAnswerShow8, textViewAnswerShow8.getText().toString());
//        outState.putString(valueTextViewAnswerShow9, textViewAnswerShow9.getText().toString());
//        outState.putString(valueTextViewAnswerShow10, textViewAnswerShow10.getText().toString());
//        outState.putString(valueTextViewAnswerShow11, textViewAnswerShow11.getText().toString());
//        outState.putString(valueTextViewAnswerShow12, textViewAnswerShow12.getText().toString());
//        outState.putString(valueTextViewAnswerShowBasic, textViewAnswerShowBasic.getText().toString());
//
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
//        textViewAnswerShow1.setText(savedInstanceState.getString(valueTextViewAnswerShow1));
//        textViewAnswerShow2.setText(savedInstanceState.getString(valueTextViewAnswerShow2));
//        textViewAnswerShow3.setText(savedInstanceState.getString(valueTextViewAnswerShow3));
//        textViewAnswerShow4.setText(savedInstanceState.getString(valueTextViewAnswerShow4));
//        textViewAnswerShow5.setText(savedInstanceState.getString(valueTextViewAnswerShow5));
//        textViewAnswerShow6.setText(savedInstanceState.getString(valueTextViewAnswerShow6));
//        textViewAnswerShow6.setText(savedInstanceState.getString(valueTextViewAnswerShow7));
//        textViewAnswerShow7.setText(savedInstanceState.getString(valueTextViewAnswerShow8));
//        textViewAnswerShow8.setText(savedInstanceState.getString(valueTextViewAnswerShow9));
//        textViewAnswerShow9.setText(savedInstanceState.getString(valueTextViewAnswerShow10));
//        textViewAnswerShow10.setText(savedInstanceState.getString(valueTextViewAnswerShow11));
//        textViewAnswerShow11.setText(savedInstanceState.getString(valueTextViewAnswerShow12));
//        textViewAnswerShowBasic.setText(savedInstanceState.getString(valueTextViewAnswerShowBasic));
    }


//        Log.d(LOG_TAG, "onSaveInstanceState");

    public Object onRetainNonConfigurationInstance() {
        return R.layout.activity_main;
    }

    private void loadValues() {

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
        countHeartLive = Integer.valueOf(sharedPreferences.getString("valueCountHeartLive", "0"));
        countAllPrimerov = Integer.valueOf(sharedPreferences.getString("valueCountAllPrimerov", "0"));
        countRightTask = Integer.valueOf(sharedPreferences.getString("valueCountRightTask", "0"));
        countWrongTask = Integer.valueOf(sharedPreferences.getString("valueCountWrongTask", "0"));
        countCurrentRightTask = Integer.valueOf(sharedPreferences.getString("valueCountCurrentRightTask", "0"));
        progressBarTime = Integer.valueOf(sharedPreferences.getString("valueProgressBarTime", "300"));
        progressBar.setMax(progressBarTime);
        progressBarCount = Integer.valueOf(sharedPreferences.getString("valueProgressBarCount", "1"));
        progressBarSpeed = Integer.valueOf(sharedPreferences.getString("valueProgressBarSpeed", "1"));
        countPrimerov = Integer.valueOf(sharedPreferences.getString("valueCountPrimerov", "1"));
        stringCurrentAct = sharedPreferences.getString("valueStringCurrentAct", "*");
        endGame = sharedPreferences.getBoolean("valueEndGame", false);
        lastGame = sharedPreferences.getBoolean("valueLastGame", false);
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

        editorSharedPreferences.putString("valueCountHeartLive", String.valueOf(countHeartLive));
        editorSharedPreferences.putString("valueCountAllPrimerov", String.valueOf(countAllPrimerov));
        editorSharedPreferences.putString("valueCountRightTask", String.valueOf(countRightTask));
        editorSharedPreferences.putString("valueCountWrongTask", String.valueOf(countWrongTask));
        editorSharedPreferences.putString("valueCountCurrentRightTask", String.valueOf(countCurrentRightTask));
        editorSharedPreferences.putString("valueCountCurrentWrongTask", String.valueOf(countCurrentWrongTask));

        editorSharedPreferences.putString("valueProgressBarTime", String.valueOf(progressBarTime));
        editorSharedPreferences.putString("valueProgressBarCount", String.valueOf(progressBarCount));
        editorSharedPreferences.putString("valueProgressBarSpeed", String.valueOf(progressBarSpeed));
        editorSharedPreferences.putString("valueCountPrimerov", String.valueOf(countPrimerov));
        editorSharedPreferences.putString("valueStringCurrentAct", String.valueOf(stringCurrentAct));
        editorSharedPreferences.putBoolean("valueEndGame", endGame);
        editorSharedPreferences.putBoolean("valueLastGame", lastGame);

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
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadValues();
    }

    @Override
    protected void onDestroy() {
        saveValues();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        saveValues();
        super.onPause();
    }

    private void startNewGame() {
        countHeartLive = 0;
        countAllPrimerov = 0;
        countRightTask = 0;
        countWrongTask = 0;
        countCurrentRightTask = 0;
        countCurrentWrongTask = 0;
        progressBarTime = 300;
        progressBarCount = 1;
        countPrimerov = 1;
        progressBarSpeed = 1;
        stringCurrentAct = "*";
        endGame = false;
        lastGame = false;
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
        saveValues();
        refrishIconLive();
    }


}
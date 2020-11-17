package com.firstSet.MultiplyIt;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.umnozhka.R;

import java.util.Objects;

public class FinishLeassonActivity extends Activity implements View.OnClickListener {
    Button finishButtonSave;
    //            finishButtonTakeFoto;
    EditText finishStringUserName;
    TextView finishStringPrimerovTasks, finishStringActions, finishStringMultiplyNumbers,
            finishTextViewPoints, finishTextValueViewPoints;
//    private SQLiteDatabase db;
    LessonSummary lessonSummary; // на Основе myLesson и MySettings
//    private List<LessonSummary> lessonSummaries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_leasson);
        lessonSummary = StartActivity.getStartLessonSummary();
        finishButtonSave = findViewById(R.id.finishButtonSave);
        finishStringUserName = findViewById(R.id.finishStringUserName);
        finishStringPrimerovTasks = findViewById(R.id.finishStringPrimerovTasks);
        finishStringActions = findViewById(R.id.finishStringActions);
        finishStringMultiplyNumbers = findViewById(R.id.finishStringMultiplyNumbers);
        finishTextViewPoints = findViewById(R.id.finishTextViewPoints);
        finishTextValueViewPoints = findViewById(R.id.finishTextValueViewPoints);
        finishButtonSave.setOnClickListener(this);

        // устанавливаем BeginFinishLeassonActivity в  true
        // это значит, что была вызвана FinishLeassonActivity,


        String stringCountAllPrimerov = StartActivity.STRING_COUNT_ALL_PRIMEROV;
        Bundle arguments = getIntent().getExtras();
        assert arguments != null;
        String string2 = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            string2 = Objects.requireNonNull(arguments.get(stringCountAllPrimerov)).toString();
        }
        // Передача этой строки только одля проверки механизма передачи данных с Intent
        StartActivity.myLesson.setBeginFinishLeassonActivity(true);
        StartActivity.myLesson.setBeginGame(false);

        StartActivity.myLesson.saveValuesFinishLeassonActivity(finishStringUserName.getText().toString());
        //сохраняем
//
//        if (StartActivity.myLesson.isBeginFinishLeassonActivity()){
//        // Если FinishLeassonActivity была вызвана из StartActivity,
//        // т.е  isBeginFinishLeassonActivity=true
//            // нет значений lessonSummary и берем для отображения из
//            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//            finishStringUserName.setText(sharedPreferences.getString("valueUserNameDefault", "noname"));
//            finishStringPrimerovTasks.setText(string2);
//            finishStringActions.setText(StartActivity.mySettings.getStringMDSA());
//            finishStringMultiplyNumbers.setText(StartActivity.mySettings.getStringMultiplyNumbers());
//            finishTextValueViewPoints.setText(sharedPreferences.getString("valueCountPoints", "0"));
//
//        } else {
            finishStringUserName.setText(lessonSummary.getNameUser());
            finishStringPrimerovTasks.setText(string2);
            finishStringActions.setText(lessonSummary.getStringMDSA());
            finishStringMultiplyNumbers.setText(lessonSummary.getStringMultiplyNumbers());
            finishTextValueViewPoints.setText(String.valueOf(lessonSummary.getCountPoints()));
            //не выводим дату



    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.finishButtonSave) {// завершена текущая игра
            if (finishStringUserName.getText().length() > 0) {
                lessonSummary.setNameUser(finishStringUserName.getText().toString());
                SQLiteDatabase db = getBaseContext().openOrCreateDatabase("lessonSummary.db", MODE_PRIVATE, null);
                saveLessonSummaryToDB(db);
                // сохраняем результаты в БД
                StartActivity.myLesson.setBeginFinishLeassonActivity(false);
                // переходим на startActivity, без её вызова, так как ее окно активно
                StartActivity.myLesson.saveValuesFinishLeassonActivity(finishStringUserName.getText().toString());
                // сохраняем имя пользователя для имени по умолчанию
                // и состояние закрытой корректно FinishLeassonActivity
                finish();
                // закрываем текущую активность
            } else
                Toast.makeText(getApplicationContext(), R.string.finishLeassonActivityNotUserName,
                        Toast.LENGTH_SHORT).show();
            //            case R.id.finishButtonCancel:
//                StartActivity.myLesson.setBeginFinishLeassonActivity(false);
//                // переходим на startActivity, без её вызова, так как ее окно активно
//                StartActivity.myLesson.saveValuesFinishLeassonActivity(finishStringUserName.getText().toString());
//                // сохраняем имя пользователя для имени по умолчанию
//                // и состояние закрытой корректно FinishLeassonActivity
//                finish();
//                // закрываем текущую активность
//                break;
//
        } else {
            throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }


    public void saveLessonSummaryToDB(SQLiteDatabase db) {
//        ContentValues contentValues = new ContentValues();
//        SQLiteDatabase db = openOrCreateDatabase("lessons.db", Context.MODE_PRIVATE, null);
//        SQLiteDatabase db = new SQLiteDatabase();
//        String string0=""
        // Удалю таблицу, через создание новой БД?
//      db.delete("lessons", null, null);
//        if (db.isOpen()) db.close();
        //проверка наличия БД на устройстве


        String stringCreateTable = "CREATE TABLE IF NOT EXISTS lessons3 ('id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                "'nameUser' TEXT, 'dateLesson' TEXT, 'countPoints' INTEGER," +
//                "'imageFileName' TEXT, " +
//                " 'stringPrimerovTasks' TEXT, " +
                "'stringMDSA' TEXT, 'stringMultiplyNumbers' TEXT )";
        db.execSQL(stringCreateTable);
        String stringInsertInto = "INSERT INTO lessons3 VALUES ( 'id'=?, '" + lessonSummary.getNameUser() + "', '" + lessonSummary.getDateLesson() + "' , '" + lessonSummary.getCountPoints() + "' , '" +
//                lessonSummary.getImage()
//                + "', '" + lessonSummary.getStringPrimerovTasks() + "', '" +
                lessonSummary.getStringMDSA() + "', '" + lessonSummary.getStringMultiplyNumbers() + "')";
        db.execSQL(stringInsertInto);
        //нужно ли в БД первую запись делать с ноунем и ноуфото?
        db.close();

//        String string = "CREATE TABLE IF NOT EXISTS lessons ('id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
//                "nameUser TEXT, countPoints INTEGER, dateLesson TEXT, image INTEGER, idResultLesson INTEGER, " +
//                " stringPrimerovTasks TEXT, stringMDSA TEXT, stringMultiplyNumbers TEXT )";
//        db.execSQL(string);
//        String string2 ="INSERT INTO lessons VALUES ('" + this.nameUser + "', " + this.countPoints +", '" + this.dateLesson + "' , " + this.imageFileName
//                + ", '" + this.stringPrimerovTasks + "', '" + this.stringMDSA + "', '" + this.stringMultiplyNumbers + "')";
//                db.execSQL(string2);
////        нужно ли в БД первую запись делать с ноунем и ноуфото?
//        db.close ();
    }
}

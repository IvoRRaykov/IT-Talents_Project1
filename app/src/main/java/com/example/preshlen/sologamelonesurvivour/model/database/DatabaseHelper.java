package com.example.preshlen.sologamelonesurvivour.model.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by Presshlen on 3/8/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper instance;


    // database name and version
    public static final String DATABASE_NAME = "NA_IVO_BAZATA4";
    public static final int DATABASE_VERSION = 1;

    // tables

    public static final String USERS = "users";
    public static final String QUESTIONS = "questions";
    public static final String USERS_QUESTIONS = "users_questions";

    //common fields

    public static final String USER_ID = "user_id";
    public static final String QUESTION_ID = "question_id";

    // USERS table colmns

    public static final String USERNAME = "username";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";

    // USERS CREATE statement
    private static final String CREATE_USERS_TABLE = "CREATE TABLE IF NOT EXISTS " + USERS + " ("
            + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
            + USERNAME + " VARCHAR(255) NOT NULL, "
            + EMAIL + " VARCHAR (255) NOT NULL, "
            + PASSWORD + " VARCHAR(255) NOT NULL "
            + ") ";


    public static final String QUESTION_TEXT = "question_text";
    public static final String RIGHT_ANS = "right_answer";
    public static final String WRONG_ANS_1 = "wrong_answer_1";
    public static final String WRONG_ANS_2 = "wrong_answer_2";
    public static final String WRONG_ANS_3 = "wrong_answer_3";

    private static final String CREATE_QUESTIONS_TABLE = "CREATE TABLE " + QUESTIONS + " ("
            + QUESTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + QUESTION_TEXT + " VARCHAR(255) NOT NULL, "
            + RIGHT_ANS + " VARCHAR(255) NOT NULL, "
            + WRONG_ANS_1 + " VARCHAR(255) NOT NULL, "
            + WRONG_ANS_2 + " VARCHAR(255) NOT NULL, "
            + WRONG_ANS_3 + " VARCHAR(255) NOT NULL "
            + ");";


    private static String QUESTION_COLS = "("
            + QUESTION_TEXT + ", "
            + RIGHT_ANS + ", "
            + WRONG_ANS_1 + ", "
            + WRONG_ANS_2 + ", "
            + WRONG_ANS_3 + ")";


    private static final String FILL_QUESTIONS = "INSERT INTO " + QUESTIONS + " " + QUESTION_COLS + " VALUES"
            + " (" + "\"question1\", " + "\"answerr11\", " + "\"answer12\", " + "\"answer13\", " + "\"answer14\" ), "
            + " (" + "\"question2\", " + "\"answerr21\", " + "\"answer22\", " + "\"answer23\", " + "\"answer24\" ), "
            + " (" + "\"question3\", " + "\"answerr31\", " + "\"answer32\", " + "\"answer33\", " + "\"answer34\" ), "
            + " (" + "\"question4\", " + "\"answerr41\", " + "\"answer42\", " + "\"answer43\", " + "\"answer44\" ), "
            + " (" + "\"question5\", " + "\"answerr11\", " + "\"answer12\", " + "\"answer13\", " + "\"answer14\" ), "
            + " (" + "\"question6\", " + "\"answerr11\", " + "\"answer12\", " + "\"answer13\", " + "\"answer14\" ), "
            + " (" + "\"question7\", " + "\"answerr11\", " + "\"answer12\", " + "\"answer13\", " + "\"answer14\" ), "
            + " (" + "\"question8\", " + "\"answerr11\", " + "\"answer12\", " + "\"answer13\", " + "\"answer14\" ), "
            + " (" + "\"question9\", " + "\"answerr11\", " + "\"answer12\", " + "\"answer13\", " + "\"answer14\" ), "
            + " (" + "\"question10\", " + "\"answerr11\", " + "\"answer12\", " + "\"answer13\", " + "\"answer14\" ), "
            + " (" + "\"question11\", " + "\"answerr11\", " + "\"answer12\", " + "\"answer13\", " + "\"answer14\" ), "
            + " (" + "\"question12\", " + "\"answerr11\", " + "\"answer12\", " + "\"answer13\", " + "\"answer14\" ), "
            + " (" + "\"question13\", " + "\"answerr11\", " + "\"answer12\", " + "\"answer13\", " + "\"answer14\" ), "
            + " (" + "\"question14\", " + "\"answerr11\", " + "\"answer12\", " + "\"answer13\", " + "\"answer14\" ), "
            + " (" + "\"question15\", " + "\"answerr11\", " + "\"answer12\", " + "\"answer13\", " + "\"answer14\" ), "
            + " (" + "\"question16\", " + "\"answerr11\", " + "\"answer12\", " + "\"answer13\", " + "\"answer14\" ), "
            + " (" + "\"question17\", " + "\"answerr11\", " + "\"answer12\", " + "\"answer13\", " + "\"answer14\" ), "
            + " (" + "\"question18\", " + "\"answerr11\", " + "\"answer12\", " + "\"answer13\", " + "\"answer14\" ), "
            + " (" + "\"question19\", " + "\"answerr11\", " + "\"answer12\", " + "\"answer13\", " + "\"answer14\" ), "
            + " (" + "\"question20\", " + "\"answerr11\", " + "\"answer12\", " + "\"answer13\", " + "\"answer14\" ), "
            + " (" + "\"question21\", " + "\"answerr11\", " + "\"answer12\", " + "\"answer13\", " + "\"answer14\" ), "
            + " (" + "\"question22\", " + "\"answerr11\", " + "\"answer12\", " + "\"answer13\", " + "\"answer14\" ), "
            + " (" + "\"question23\", " + "\"answerr11\", " + "\"answer12\", " + "\"answer13\", " + "\"answer14\" ), "
            + " (" + "\"question24\", " + "\"answerr11\", " + "\"answer12\", " + "\"answer13\", " + "\"answer14\" ), "
            + " (" + "\"question25\", " + "\"answerr11\", " + "\"answer12\", " + "\"answer13\", " + "\"answer14\" ), "
            + " (" + "\"question26\", " + "\"answerr11\", " + "\"answer12\", " + "\"answer13\", " + "\"answer14\" ), "
            + " (" + "\"question27\", " + "\"answerr11\", " + "\"answer12\", " + "\"answer13\", " + "\"answer14\" ), "
            + " (" + "\"question28\", " + "\"answerr11\", " + "\"answer12\", " + "\"answer13\", " + "\"answer14\" ), "
            + " (" + "\"question29\", " + "\"answerr11\", " + "\"answer12\", " + "\"answer13\", " + "\"answer14\" );";





    public static final String HOLDER_ID = "holder_id";
    private static final String CREATE_USERS_QUESTIONS_TABLE = "CREATE TABLE IF NOT EXISTS " + USERS_QUESTIONS + " ("
            + HOLDER_ID + " INTEGER NOT NULL, "
            + QUESTION_ID + " INTEGER NOT NULL, "
            + "FOREIGN KEY ("+ HOLDER_ID +") REFERENCES "+ USERS +"("+ USER_ID+"), "
            + "FOREIGN KEY ("+ QUESTION_ID +") REFERENCES "+ QUESTIONS +"("+ QUESTION_ID +")"
            +") ";

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DatabaseHelper getInstance(Context context) {
        if (instance == null)
            instance = new DatabaseHelper(context);
        return instance;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_QUESTIONS_TABLE);
        db.execSQL(CREATE_USERS_QUESTIONS_TABLE);
        db.execSQL(FILL_QUESTIONS);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + USERS);
        db.execSQL("DROP TABLE IF EXISTS " + USERS_QUESTIONS);
        db.execSQL("DROP TABLE IF EXISTS " + QUESTIONS);


        //create new tables
        onCreate(db);
    }


    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

}
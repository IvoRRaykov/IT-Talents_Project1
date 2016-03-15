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
    public static final String DATABASE_NAME = "NA_IVO_BAZATA6";
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
            + " (" + "\"kak se kazva na ivo kotkata\", " + "\"kiki\", " + "\"miki\", " + "\"kotka\", " + "\"random\" ), "
            + " (" + "\"ot koga ivo ne igrae dota\", " + "\"ot kakto pochna talanti\", " + "\"ot vchera\", " + "\"igrae dota\", " + "\"predi 17 dni\" ), "
            + " (" + "\"ot koga ivo ne igrae voleybol\", " + "\"ot kakto pochna talanti\", " + "\"nikoga ne e igral!?\", " + "\"oshte igrae\", " + "\"predi mesec\" ), "
            + " (" + "\"kak se kazva ivo\", " + "\"ivo\", " + "\"ivelin\", " + "\"ivaylo\", " + "\"ne znam!?\" ), "
            + " (" + "\"kak se kazva na donika zaeka\", " + "\"zaek\", " + "\"misho\", " + "\"koko\", " + "\"shteryo\" ), "
            + " (" + "\"kakva e na mitko kolata\", " + "\"mercedes\", " + "\"pejo\", " + "\"bmw\", " + "\"alfaromeo?\" ), "
            + " (" + "\"kakvo pravq v svobodnoto si vreme\", " + "\"nqmam\", " + "\"igraq voley\", " + "\"igraq futbol\", " + "\"gledam filmi\" ), "
            + " (" + "\"kakvo pravi donika v svobodnoto si vreme\", " + "\"qde\", " + "\"kodi\", " + "\"uchi\", " + "\"pishe si fb\" ), "
            + " (" + "\"kakvo obicham nay mnogo\", " + "\"koda\", " + "\"piene\", " + "\"qdene\", " + "\"pushene\" ), "
            + " (" + "\"kolko ciagri pushi ivo na den\", " + "\"0\", " + "\"10\", " + "\"20\", " + "\"5\" ), "
            + " (" + "\"kolko struva na ivo surceto\", " + "\"bezcenno e\", " + "\"20lv\", " + "\"bez pari\", " + "\"po pazurluk\" ), "
            + " (" + "\"kude e skril ivo klyucha za surceto si\", " + "\"vuv surceto si ?\", " + "\"pod legloto\", " + "\"na tavana\", " + "\"v moreto\" ), "
            + " (" + "\"kolko epizoda na twilight e gledal ivo\", " + "\"0\", " + "\"baq\", " + "\"mnogo\", " + "\"vsichki\" ), "
            + " (" + "\"kolko epizoda na prisonbreak e gledal ivo\", " + "\"vsichki\", " + "\"baq\", " + "\"mnogo\", " + "\"malko\" ), "
            + " (" + "\"kolko epizoda na shaman king e gledal ivo\", " + "\"vsichki\", " + "\"malko\", " + "\"0\", " + "\"mnogo\" ), "
            + " (" + "\"kolko epizoda na GOT e gledal ivo\", " + "\"vs\", " + "\"0\", " + "\"1\", " + "\"2\" ), "
            + " (" + "\"kak spi ivo\", " + "\"po lice\", " + "\"pogrub\", " + "\"nastrani\", " + "\"na drugata strana\" ), "
            + " (" + "\"kak spi donika\", " + "\"vsqkak, stiga da spi :D\", " + "\"pogrub\", " + "\"police\", " + "\"nastrani\" ), "
            + " (" + "\"kude obicha da spi ivo\", " + "\"u tqh\", " + "\"na kvartira\", " + "\"v obshtaka\", " + "\"v karavana\" ), "
            + " (" + "\"kolko tochno voda pie ivo na den\", " + "\"malko\", " + "\"baq\", " + "\"ne pie\", " + "\"vsichkata voda ot cheshmata\" ), "
            + " (" + "\"kude obicha da hodi ivo\", " + "\"v maniqta da igrae dota\", " + "\"na diskoteka\", " + "\"na kunki\", " + "\"u donika\" ), "
            + " (" + "\"kak se kazva na ivo sukvartiranta\", " + "\"ivan\", " + "\"mariq\", " + "\"iveta\", " + "\"krisi\" ), "
            + " (" + "\"koy pomogna na ivo s bazata ?!?!?\", " + "\"DIDIII\", " + "\"tati\", " + "\"mama\", " + "\"kaka\" ), "
            + " (" + "\"kogo go bolqt ochichkite ot tiq vuprosi\", " + "\"ivcho\", " + "\"krasi (Opredeleno krasi :D )\", " + "\"mitko\", " + "\"dyanko\" ), "
            + " (" + "\"kolko shte struva tova prilojenie\", " + "\"null\", " + "\"milioni$\", " + "\"baq\", " + "\"mnogo\" ), "
            + " (" + "\"kakuv e laptopa na ivo\", " + "\"asus\", " + "\"lenovo\", " + "\"nqkuv slab\", " + "\"dell\" ), "
            + " (" + "\"s kakvo idva ivo ot talanti\", " + "\"88 -> 102 -> 7\", " + "\"102\", " + "\"88\", " + "\"77\" ), "
            + " (" + "\"znae l iivo kak da insertva v bazata\", " + "\"ne\", " + "\"da\", " + "\"absolyutno\", " + "\"malko\" ), "
            + " (" + "\"kolko hora obichat ivo\", " + "\"0\", " + "\"baq\", " + "\"mnogo\", " + "\"malko\" ), "
            + " (" + "\"shte ima li ivo budeshte?\", " + "\"ne\", " + "\"da\", " + "\"absolyutno\", " + "\"slabo...\" ), "
            + " (" + "\"kolko puti ivo si e chupil ruakta\", " + "\"1\", " + "\"2\", " + "\"3\", " + "\"4\" ), "
            + " (" + "\"kakuv cvqt e kotkata na ivo\", " + "\"bqlo-cherna\", " + "\"bqla\", " + "\"cherna\", " + "\"sharena\" ), "
            + " (" + "\"kolko pari struva kotkata na ivo\", " + "\"bezcenna\", " + "\"2lv\", " + "\"malko\", " + "\"4lv\" ), "
            + " (" + "\"znae li ivo kak da si pishe koda\", " + "\"ne\", " + "\"absolyutno\", " + "\"da\", " + "\"malko\" );";





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
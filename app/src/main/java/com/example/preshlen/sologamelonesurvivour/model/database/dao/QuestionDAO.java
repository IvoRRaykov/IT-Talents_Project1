package com.example.preshlen.sologamelonesurvivour.model.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.preshlen.sologamelonesurvivour.model.classes.Answer;
import com.example.preshlen.sologamelonesurvivour.model.classes.Question;
import com.example.preshlen.sologamelonesurvivour.model.classes.User;
import com.example.preshlen.sologamelonesurvivour.model.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by Preshlen on 3/11/2016.
 */
public class QuestionDAO implements IQuestionDAO{


    private DatabaseHelper dbh;
    private Context context;

    public QuestionDAO(Context context) {
        this.context = context;
        this.dbh = DatabaseHelper.getInstance(context);
    }

    public HashMap<String, List<Answer>> selectAllQuestions() {
        HashMap<String, List<Answer>> questions = new HashMap<String, List<Answer>>();
        String query = "SELECT * FROM " + dbh.QUESTIONS;

        SQLiteDatabase db = dbh.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                int questionID = c.getInt(c.getColumnIndex(dbh.QUESTION_ID));
                String text = c.getString(c.getColumnIndex(dbh.QUESTION_TEXT));
                String rightAnswer = c.getString(c.getColumnIndex(dbh.RIGHT_ANS));
                String wrongAnswer1 = c.getString(c.getColumnIndex(dbh.WRONG_ANS_1));
                String wrongAnswer2 = c.getString(c.getColumnIndex(dbh.WRONG_ANS_2));
                String wrongAnswer3 = c.getString(c.getColumnIndex(dbh.WRONG_ANS_3));
                String questionIDString = String.valueOf(questionID);
                ArrayList<Answer> answers = new ArrayList<>();

                answers.add(new Answer(rightAnswer, true));
                answers.add(new Answer(wrongAnswer1, false));
                answers.add(new Answer(wrongAnswer2, false));
                answers.add(new Answer(wrongAnswer3, false));
                answers.add(new Answer(questionIDString, false));
                questions.put(text, answers);


            } while (c.moveToNext());
        }
        db.close();
        return questions;
    }

    private Question sellectQuestion(int qID) {
        SQLiteDatabase db = dbh.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + dbh.QUESTIONS
                + " WHERE " + dbh.QUESTION_ID + " = \"" + qID + "\"";

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        int questionID = c.getInt(c.getColumnIndex(dbh.QUESTION_ID));
        String text = c.getString(c.getColumnIndex(dbh.QUESTION_TEXT));
        String rightAnswer = c.getString(c.getColumnIndex(dbh.RIGHT_ANS));
        String wrongAnswer1 = c.getString(c.getColumnIndex(dbh.WRONG_ANS_1));
        String wrongAnswer2 = c.getString(c.getColumnIndex(dbh.WRONG_ANS_2));
        String wrongAnswer3 = c.getString(c.getColumnIndex(dbh.WRONG_ANS_3));
        ArrayList<Answer> answers = new ArrayList<>();

        answers.add(new Answer(rightAnswer, true));
        answers.add(new Answer(wrongAnswer1, false));
        answers.add(new Answer(wrongAnswer2, false));
        answers.add(new Answer(wrongAnswer3, false));

        Question question = new Question(text);
        question.setAnswers(answers);
        question.setQuestionId(questionID);


        db.close();
        return question;
    }


    public TreeMap<Integer, Question> getAllQuestionsForUser(User player) {

        String query = "SELECT * FROM " + dbh.USERS_QUESTIONS
                + " WHERE " + dbh.HOLDER_ID + " = \"" + player.getUserId() + "\"";

        SQLiteDatabase db = dbh.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        TreeMap<Integer, Question> questions = new TreeMap<>();
        int i =1;
        if (c.moveToFirst()) {
            do {
                int questionID = c.getInt(c.getColumnIndex(dbh.QUESTION_ID));
                questions.put(i++, sellectQuestion(questionID));
            } while (c.moveToNext());

        }
        return questions;
    }


    public boolean checkIFPlayerHasQuestions(User player) {
        SQLiteDatabase db = dbh.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + dbh.USERS_QUESTIONS
                + " WHERE " + dbh.HOLDER_ID + " = \"" + player.getUserId() + "\"";

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null && c.moveToFirst()) {
            db.close();
            return true;
        } else {
            db.close();
            return false;
        }
    }

    public long addQuestionToPlayer(User player, Question question) {
        SQLiteDatabase db = dbh.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(dbh.HOLDER_ID, player.getUserId());
        values.put(dbh.QUESTION_ID, question.getQuestionId());


        long pairID = db.insert(dbh.USERS_QUESTIONS, null, values);
        db.close();
        return pairID;
    }
}

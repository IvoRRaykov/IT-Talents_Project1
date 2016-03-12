package com.example.preshlen.sologamelonesurvivour.model;

import android.content.Context;
import android.database.CursorIndexOutOfBoundsException;
import android.util.Log;


import com.example.preshlen.sologamelonesurvivour.model.dao.QuestionDAO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


public class QuestionManager {


    private static final int START_QUESTIONS = 30;
    private static QuestionManager ourInstance;
    QuestionDAO questionDAO;

    private QuestionManager(Context context) {
        this.questionDAO = new QuestionDAO(context);
    }

    public static QuestionManager getInstance(Context context) {
        if (ourInstance == null)
            ourInstance = new QuestionManager(context);
        return ourInstance;
    }


    public void fillAllQuestions(User player) {

        try {
            questionDAO.getAllQuestionsForUser(player);

        } catch (CursorIndexOutOfBoundsException ex) {

            HashMap<String, List<Answer>> qs = questionDAO.selectAllQuestions();
            Set<String> someQuestions = qs.keySet();
            for (int i = 1; i <= START_QUESTIONS; i++) {

                ArrayList<String> questionsArray = new ArrayList<String>(someQuestions);
                Collections.shuffle(questionsArray);
                if (questionsArray.get(0) != null) {
                    Question q = new Question(questionsArray.get(0));
                    List<Answer> answers = qs.get(q.getText());
                    if (answers.size() != 4) {
                        q.setQuestionId(Integer.parseInt(answers.remove(answers.size() - 1).toString()));
                    }
                    q.setAnswers(answers);
                    questionDAO.addQuestionToPlayer(player, q);
                    player.addToAllQuestions(i, q);
                    questionsArray.remove(0);

                } else {
                    i--;
                    continue;
                }
            }


        }

    }


}

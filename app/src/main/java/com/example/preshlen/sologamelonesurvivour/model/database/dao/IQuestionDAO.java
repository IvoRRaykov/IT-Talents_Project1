package com.example.preshlen.sologamelonesurvivour.model.database.dao;

import com.example.preshlen.sologamelonesurvivour.model.classes.Answer;
import com.example.preshlen.sologamelonesurvivour.model.classes.Question;
import com.example.preshlen.sologamelonesurvivour.model.classes.User;

import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by Preshlen on 3/13/2016.
 */
public interface IQuestionDAO {

    HashMap<String, List<Answer>> selectAllQuestions();
    TreeMap<Integer, Question> getAllQuestionsForUser(User player);
    boolean checkIFPlayerHasQuestions(User player);
    long addQuestionToPlayer(User player, Question question);


}

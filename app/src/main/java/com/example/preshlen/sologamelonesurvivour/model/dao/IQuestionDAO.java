package com.example.preshlen.sologamelonesurvivour.model.dao;

import com.example.preshlen.sologamelonesurvivour.model.Answer;
import com.example.preshlen.sologamelonesurvivour.model.Question;
import com.example.preshlen.sologamelonesurvivour.model.User;

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

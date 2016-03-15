package com.example.preshlen.sologamelonesurvivour.model.managers;

import android.content.Context;

import com.example.preshlen.sologamelonesurvivour.model.classes.Answer;
import com.example.preshlen.sologamelonesurvivour.model.classes.Question;
import com.example.preshlen.sologamelonesurvivour.model.classes.User;
import com.example.preshlen.sologamelonesurvivour.model.database.dao.QuestionDAO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class QuestionManager {

    private HashMap<String, List<Answer>> qs = new HashMap<>();
    private HashMap<Integer, Question> enemyQuestions = new HashMap<>();
    private static final int START_QUESTIONS = 16;
    private static QuestionManager ourInstance;
    QuestionDAO questionDAO;

    private QuestionManager(Context context) {

        this.questionDAO = new QuestionDAO(context);
        this.qs = questionDAO.selectAllQuestions();
    }

    public static QuestionManager getInstance(Context context) {
        if (ourInstance == null)
            ourInstance = new QuestionManager(context);
        return ourInstance;
    }

    public void addQuestoinToPlayersDatabase(User player, Question q) {
        if(!UserManager.hasPlayerQuestion(q)) {
            questionDAO.addQuestionToPlayer(player, q);
        }
    }

    public void createQuestionPackFroUser(User player) {
        if (ifUserHasQuestions(player)) {
            fillAllQuestionsFromPreviousGames(player);
        } else {
            fillAllQuestions(player);
        }
        fillBotQuestions();
    }

    private void fillBotQuestions(){
        int i =0;
        for(Map.Entry<String, List<Answer>> question: this.qs.entrySet()){
            if(!UserManager.hasPlayerQuestion(question.getKey())){
                Question q = new Question(question.getKey());
                List<Answer> answers = question.getValue();
                if (answers.size() != 4) {
                    q.setQuestionId(Integer.parseInt(answers.remove(answers.size() - 1).toString()));
                }
                Collections.shuffle(answers);
                q.setAnswers(answers);
                enemyQuestions.put(i++, q);
            }
        }
    }

    private void fillAllQuestions(User player) {
        Set<String> someQuestions = qs.keySet();
        for (int i = 1; i <= START_QUESTIONS; i++) {
            ArrayList<String> questionsArray = new ArrayList<String>(someQuestions);
            Collections.shuffle(questionsArray);
            if (questionsArray.get(0) != null) {
                Question q = new Question(questionsArray.get(0));
                List<Answer> answers = qs.get(q.getText());
                if (answers.size() != 4) {
                    q.setQuestionId(Integer.parseInt(answers.remove(answers.size() - 1).toString()));
                } else {
                    i--;
                    continue;
                }
                Collections.shuffle(answers);
                q.setAnswers(answers);
                player.addToAllQuestions(i, q);
                questionsArray.remove(0);
            } else {
                i--;
                continue;
            }
        }
        addAllQuestionsToTable(player);
    }

    private void fillAllQuestionsFromPreviousGames(User player) {
        player.setAllQuestions(questionDAO.getAllQuestionsForUser(player));
    }


    private void addAllQuestionsToTable(User player) {
        for (int i = 1; i < player.getAllQuestions().size(); i++) {
            questionDAO.addQuestionToPlayer(player, player.getAllQuestions().get(i));
        }
    }


    private boolean ifUserHasQuestions(User player) {
        return questionDAO.checkIFPlayerHasQuestions(player);
    }

    public HashMap<Integer, Question> getEnemyQuestions() {
        return enemyQuestions;
    }

    public void removeQuestionFromEnemiesQuestions(Question question) {
        for(Map.Entry<Integer, Question> entry: enemyQuestions.entrySet()){
            if(entry.getValue().equals(question)){
                enemyQuestions.remove(question);
                return;
            }
        }
    }
}

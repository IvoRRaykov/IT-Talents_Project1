package com.example.preshlen.sologamelonesurvivour.model.managers;


import android.content.Context;

import com.example.preshlen.sologamelonesurvivour.model.classes.Question;
import com.example.preshlen.sologamelonesurvivour.model.classes.User;
import com.example.preshlen.sologamelonesurvivour.model.database.dao.QuestionDAO;
import com.example.preshlen.sologamelonesurvivour.model.database.dao.UserDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class UserManager {
    public static final int MAX_POINTS = 1000;
    public static User player;
    public static final int DECK_SIZE = 12;

    private static UserManager ourInstance;
    UserDAO userDAO;

    private UserManager(Context context) {

        this.userDAO = new UserDAO(context);
    }

    public static UserManager getInstance(Context context) {
        if (ourInstance == null)
            ourInstance = new UserManager(context);
        return ourInstance;
    }

    public static void addQuestionToDeck(String s) {
        for (Map.Entry<Integer, Question> entry : player.getAllQuestions().entrySet()) {
            if (entry.getValue().getText().equals(s)) {
                player.addQuestionToDeck(entry.getValue());
                return;
            }
        }
    }

    public static boolean isDeckFull() {
        if (getDeckFreeSpaceLeft() == 0) {
            return true;
        }
        return false;
    }


    public static boolean hasPlayerQuestion(Question q){
        return player.getAllQuestions().containsValue(q);
    }

    public static boolean hasPlayerQuestion(String q){
        for(Map.Entry<Integer,Question> entry: player.getAllQuestions().entrySet()){
            if(entry.getValue().getText().equals(q)){
                return true;
            }
        }
        return false;
    }

    public static int getDeckFreeSpaceLeft() {
        return DECK_SIZE - (player.getDeck().size()+1);
    }

    public boolean existUsername(String username) {

        return userDAO.checkUsername(username);
    }

    public boolean existEmail(String email) {

        return userDAO.checkUserEmail(email);
    }

    public long register(User user) {
        long id = userDAO.addUser(user);
        if (id != -1)
            user.setUserId(id);
        return id;
    }

    public User login(String email, String password) {
        User temp = userDAO.checkLogin(email, password);
        player = temp;
        return temp;
    }

    public static User getPlayer() {
        return player;
    }

    public static String[] getAllQuestions() {
        List<String> list = new ArrayList<>();
        String[] contents = new String[player.getAllQuestions().size()];
        for(Map.Entry<Integer, Question> entry: player.getAllQuestions().entrySet()) {
            list.add(entry.getValue().getText());
        }
        return list.toArray(contents);
    }

    public static String[] getDeck(){
        List<String> list = new ArrayList<>();
        String[] contents = new String[player.getDeck().size()];
        for(Question question : player.getDeck()) {
            list.add(question.getText());
        }
        return list.toArray(contents);
    }

    public static Question getQuestionFromDeck(String question) {
        Question quest = null;
        for(Question q : player.getDeck()) {
            if(q.getText().equals(question)) {
                quest = q;
                player.removeQuestionFromDeck(q);
                break;
            }
        }
        return quest;
    }

    public static int getPlayersPoints(){
        return player.getPoints();
    }

    public static void addPlayerPoints(int points){
        player.setPoints(player.getPoints() + points);
    }
    public static int getEnemysPoints(){
        return player.getEnemyPoints();
    }

    public static void addEnemyPoints(int points){
        player.setEnemyPoints(player.getEnemyPoints() + points);
    }
}



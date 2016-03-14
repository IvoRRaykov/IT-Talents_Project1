package com.example.preshlen.sologamelonesurvivour.model.managers;


import android.content.Context;

import com.example.preshlen.sologamelonesurvivour.model.classes.Question;
import com.example.preshlen.sologamelonesurvivour.model.classes.User;
import com.example.preshlen.sologamelonesurvivour.model.database.dao.UserDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class UserManager {
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

    //kato klikne se vika taq glupost v BUILDDECK
    public static void addQuestionToDeck(String s) {
        for (Map.Entry<Integer, Question> entry : player.getAllQuestions().entrySet()) {
            if (entry.getValue().getText().equals(s)) {
                player.addQuestionToDeck(entry.getValue());
                return;
            }
        }
    }

    //pri pulnene na testeto
    public static boolean isDeckFull() {
        if (getDeckFreeSpaceLeft() == 0) {
            return true;
        }
        return false;
    }

    //pri ataka
    public static Question removeQuestionFromDeck(String s) {
        Question q = null;
        for (Map.Entry<Integer, Question> entry : player.getAllQuestions().entrySet()) {
            if (entry.getValue().getText().equals(s)) {
                q = entry.getValue();
                player.removeQuestionFromDeck(q);
            }
        }
        return q;
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
        for(Question q : player.getDeck()) {
            if(q.getText().equals(question)) {
                return q;
            }
        }
        return null;
    }
}



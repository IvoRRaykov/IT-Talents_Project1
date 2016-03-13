package com.example.preshlen.sologamelonesurvivour.model;


import android.content.Context;

import com.example.preshlen.sologamelonesurvivour.model.dao.UserDAO;

import java.util.TreeMap;


public class UserManager {
    public static User player;

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
}



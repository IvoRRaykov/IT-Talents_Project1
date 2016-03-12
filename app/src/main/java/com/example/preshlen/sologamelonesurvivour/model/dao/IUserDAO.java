package com.example.preshlen.sologamelonesurvivour.model.dao;


import com.example.preshlen.sologamelonesurvivour.model.User;

/**
 * Created by Presshlen on 3/8/2016.
 */
public interface IUserDAO {

    long addUser(User user);
    User getUser(String username);
    User getUser(long id);
    // long updateUser(User user);
    boolean checkUsername(String username);
    boolean checkUserEmail(String email);
    User checkLogin (String email, String password);

}

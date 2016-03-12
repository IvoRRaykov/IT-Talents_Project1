package com.example.preshlen.sologamelonesurvivour.model;

import android.util.Patterns;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Created by Presshlen on 3/8/2016.
 */
public class User {
    private long userId;
    private String email;//this field is required
    private String password;//this field is required
    private String userName;//this field is required

    ArrayList<Zone> zones = new ArrayList<>();
    private TreeMap<Integer, Question> allQuestions = new TreeMap<>();


    public User(String mail, String password, String userName) throws IllegalArgumentException {
        this.setEmail(mail);
        this.setPassword(password);
        this.setUserName(userName);

    }


    public void addToAllQuestions(int i, Question q){
        this.allQuestions.put(i,q);
    }

    public TreeMap<Integer, Question> getAllQuestions() throws NullPointerException {
        return allQuestions;
    }

    public void setAllQuestions(TreeMap<Integer, Question> allQuestions) {
        this.allQuestions = allQuestions;
    }

    public ArrayList<Zone> getZones() {
        return zones;
    }

    public void setZones(ArrayList<Zone> zones) {
        this.zones = zones;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws IllegalArgumentException {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            //TODO check in databese for existing email.
            this.email = email;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        //TODO check in database
        this.userName = userName;
    }

    public void setUserId(long id) {
        this.userId = id;
    }

    public long getUserId() {
        return userId;
    }
}
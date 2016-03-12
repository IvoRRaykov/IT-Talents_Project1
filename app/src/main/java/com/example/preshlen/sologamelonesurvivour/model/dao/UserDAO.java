package com.example.preshlen.sologamelonesurvivour.model.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.preshlen.sologamelonesurvivour.model.User;
import com.example.preshlen.sologamelonesurvivour.model.database.DatabaseHelper;


public class UserDAO implements IUserDAO {

    private DatabaseHelper dbh;
    private Context context;

    public UserDAO(Context context) {
        this.context = context;
        this.dbh = DatabaseHelper.getInstance(context);
    }

    @Override
    public long addUser(User user) {
        SQLiteDatabase db = dbh.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(dbh.USERNAME, user.getUserName());
        values.put(dbh.EMAIL, user.getEmail());
        values.put(dbh.PASSWORD, user.getPassword());

        long userId = db.insert(dbh.USERS, null, values);
        db.close();
        return userId;
    }

    @Override
    public User getUser(String username) {
        SQLiteDatabase db = dbh.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + dbh.USERS
                + "WHERE " + dbh.USERNAME + " = \"" + username + "\"";

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        String uname = c.getString(c.getColumnIndex(dbh.USERNAME));
        String password = c.getString(c.getColumnIndex(dbh.PASSWORD));
        String email = c.getString(c.getColumnIndex(dbh.EMAIL));

        User user = new User(uname, password, email);
        db.close();
        return user;
    }

    @Override
    public User getUser(long id) {
        SQLiteDatabase db = dbh.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + dbh.USERS
                + "WHERE " + dbh.USER_ID + " = \"" + id + "\"";

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        String uname = c.getString(c.getColumnIndex(dbh.USERNAME));
        String password = c.getString(c.getColumnIndex(dbh.PASSWORD));
        String email = c.getString(c.getColumnIndex(dbh.EMAIL));

        User user = new User(uname, password, email);
        db.close();
        return user;
    }


//    @Override
//    public long updateUser(User user) {
//        SQLiteDatabase db = dbh.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(dbh.USERNAME, user.getUserName());
//        values.put(dbh.EMAIL, user.getEmail());
//        values.put(dbh.PASSWORD, user.getPassword());
//
//        long userId = db.update(dbh.USERS, values, dbh.USERNAME + " = ? ", new String[]{user.getUserName()});
//        db.close();
//        return userId;
//    }

    @Override
    public boolean checkUsername(String username) {
        SQLiteDatabase db = dbh.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + dbh.USERS
                + " WHERE " + dbh.USERNAME + " = \"" + username + "\"";

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null && c.moveToFirst()) {
            db.close();
            return true;
        } else {
            db.close();
            return false;
        }
    }

    @Override
    public boolean checkUserEmail(String email) {
        SQLiteDatabase db = dbh.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + dbh.USERS
                + " WHERE " + dbh.EMAIL + " = \"" + email + "\"";

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null && c.moveToFirst()) {
            db.close();
            return true;
        } else {
            db.close();
            return false;
        }
    }

    public User checkLogin(String email, String password) {
        SQLiteDatabase db = dbh.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + dbh.USERS
                + " WHERE " + dbh.EMAIL + " = \"" + email
                + "\" AND " + dbh.PASSWORD + " = \"" + password + "\"";

        Cursor c = db.rawQuery(selectQuery, null);


        User user = null;

        if (c.moveToFirst()) {
            long id = c.getLong(c.getColumnIndex(dbh.USER_ID));
            String uname = c.getString(c.getColumnIndex(dbh.USERNAME));
            String upassword = c.getString(c.getColumnIndex(dbh.PASSWORD));
            String uemail = c.getString(c.getColumnIndex(dbh.EMAIL));

            user = new User(uemail, upassword, uname);
            user.setUserId(id);
        }

        db.close();
        return user;
    }
}

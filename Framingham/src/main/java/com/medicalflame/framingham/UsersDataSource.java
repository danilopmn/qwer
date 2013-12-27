package com.medicalflame.framingham;
import java.util.Collections;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class UsersDataSource {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_USER };
    private String[] allColumns2 = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_FRAN, MySQLiteHelper.COLUMN_DATE};

    private String[] allColumns3 = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_FRAN, MySQLiteHelper.COLUMN_DATE, MySQLiteHelper.COLUMN_IDADE,
            MySQLiteHelper.COLUMN_COLESTEROL_TOTAL, MySQLiteHelper.COLUMN_COLESTEROL_HDL,
            MySQLiteHelper.COLUMN_PRESSAO_PAS, MySQLiteHelper.COLUMN_PRESSAO_PAD,MySQLiteHelper.COLUMN_SEXO
        ,MySQLiteHelper.COLUMN_FUMA,MySQLiteHelper.COLUMN_DIABETES};


    public UsersDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public static final String COLUMN_IDADE = "idade";
    public static final String COLUMN_COLESTEROL_TOTAL = "ct";
    public static final String COLUMN_COLESTEROL_HDL = "chdl";
    public static final String COLUMN_PRESSAO_PAS = "pas";
    public static final String COLUMN_PRESSAO_PAD = "pad";
    public static final String COLUMN_SEXO = "sexo";
    public static final String COLUMN_FUMA = "fuma";
    public static final String COLUMN_DIABETES = "diabetes";

    public void insertFran(long id, String fran,String idade, String ct, String hdl, String pas, String pad, String sexo, String fuma, String diabetes) {
        ContentValues values = new ContentValues();
        values.put("userid", id);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        values.put(MySQLiteHelper.COLUMN_DATE, dateFormat.format(date));
        values.put(MySQLiteHelper.COLUMN_FRAN, fran);
        values.put(COLUMN_IDADE,idade);
        values.put(COLUMN_COLESTEROL_TOTAL,ct);
        values.put(COLUMN_COLESTEROL_HDL,hdl);
        values.put(COLUMN_PRESSAO_PAS,pas);
        values.put(COLUMN_PRESSAO_PAD,pad);
        values.put(COLUMN_SEXO,sexo);
        values.put(COLUMN_FUMA,fuma);
        values.put(COLUMN_DIABETES,diabetes);
        database.insert(MySQLiteHelper.TABLE_LIST, null,
                values);
    }

    public User createUser(String user, String fran, String idade, String ct, String hdl, String pas, String pad, String sexo, String fuma, String diabetes) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_USER, user);
        long insertId = database.insert(MySQLiteHelper.TABLE_USER, null,
                values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_USER,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        long userId = cursor.getLong(0);
        String name = cursor.getString(1);
        cursor.close();

        insertFran(userId,fran,idade,ct,hdl,pas,pad,sexo,fuma,diabetes);

        User newUser = new User(userId,name,fran);
        return newUser;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_USER,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            Cursor cursor2 = database.query(MySQLiteHelper.TABLE_LIST,
                    allColumns2, "userid = " + cursor.getLong(0), null, null, null,  MySQLiteHelper.COLUMN_DATE + " desc");
            cursor2.moveToFirst();
            String fran = cursor2.getString(1);
            String date = cursor2.getString(2);
            cursor2.close();

            User user = new User(cursor.getLong(0),cursor.getString(1),fran, date);
            users.add(user);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();

        Collections.sort(users);
        return users;
    }

    public List<String> getDates(long id){
        List<String> retorno = new ArrayList<String>();
        Cursor cursor = database.query(MySQLiteHelper.TABLE_LIST,
                allColumns2, "userid = " + id, null, null, null,MySQLiteHelper.COLUMN_DATE + " DESC");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            retorno.add(cursor.getString(2));
            cursor.moveToNext();
        }
        cursor.close();
        return retorno;
    }

    public List<String> getFrans(long id){
        List<String> retorno = new ArrayList<String>();
        Cursor cursor = database.query(MySQLiteHelper.TABLE_LIST,
                allColumns2, "userid = " + id, null, null, null, MySQLiteHelper.COLUMN_DATE + " DESC");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            retorno.add(cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();
        return retorno;
    }

    public List<Fran> getAllFrans(long id){
        List<Fran> retorno = new ArrayList<Fran>();
        Cursor cursor = database.query(MySQLiteHelper.TABLE_LIST,
                allColumns3, "userid = " + id, null, null, null, MySQLiteHelper.COLUMN_DATE + " DESC");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            retorno.add(new Fran(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getString(9),cursor.getString(10)));
            cursor.moveToNext();
        }
        cursor.close();
        return retorno;
    }

    public void removeUser(long id) {
        database.delete(MySQLiteHelper.TABLE_USER, MySQLiteHelper.COLUMN_ID + "=" + id, null);
    }

    public String getUserName(long id) {

        Cursor cursor = database.query(MySQLiteHelper.TABLE_USER,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + id, null,
                null, null, null);
        cursor.moveToFirst();
        String name = cursor.getString(1);
        cursor.close();
        return name;
    }
}
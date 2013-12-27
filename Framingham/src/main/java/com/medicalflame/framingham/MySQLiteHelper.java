package com.medicalflame.framingham;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_USER = "user";
    public static final String TABLE_LIST = "list";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_USER = "user";
    public static final String COLUMN_FRAN = "fran";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_IDADE = "idade";
    public static final String COLUMN_COLESTEROL_TOTAL = "ct";
    public static final String COLUMN_COLESTEROL_HDL = "chdl";
    public static final String COLUMN_PRESSAO_PAS = "pas";
    public static final String COLUMN_PRESSAO_PAD = "pad";
    public static final String COLUMN_SEXO = "sexo";
    public static final String COLUMN_FUMA = "fuma";
    public static final String COLUMN_DIABETES = "diabetes";


    private static final String DATABASE_NAME = "user.db";
    private static final int DATABASE_VERSION = 6;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_USER + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_USER
            + " text not null);";

    private static final String DATABASE2_CREATE = "create table "
            + TABLE_LIST + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_FRAN
            + " text not null, userid integer not null, " + COLUMN_DATE  +  " text not null, " +  COLUMN_IDADE
            +  " text not null, " +  COLUMN_COLESTEROL_TOTAL  +  " text not null, " +  COLUMN_COLESTEROL_HDL
            +  " text not null, " +  COLUMN_PRESSAO_PAD  +  " text not null, "
            +  COLUMN_PRESSAO_PAS  +  " text not null, " +  COLUMN_SEXO  +  " text not null, "
            +  COLUMN_FUMA  +  " text not null, " +  COLUMN_DIABETES  +  " text not null, "
            + " foreign key(userid) references user(_id));";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
        database.execSQL(DATABASE2_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIST);
        onCreate(db);
    }

} 
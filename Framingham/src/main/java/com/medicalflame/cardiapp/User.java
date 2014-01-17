package com.medicalflame.cardiapp;

public class User implements Comparable<User>{
    private String date;
    private long id;
    private String user;
    private String fran;


    public User(long id, String user, String fran){
        this.id = id;
        this.user = user;
        this.fran = fran;
    }

    public User(long aLong, String string, String fran, String date) {
        this(aLong,string,fran);
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return user;
    }

    public String getFran() {
        return fran;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int compareTo(User another) {
        return another.getDate().compareTo(date);
    }
}
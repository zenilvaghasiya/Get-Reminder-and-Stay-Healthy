package com.universalgamestudio.getreminderandstayhealthy;

import com.google.firebase.database.IgnoreExtraProperties;



@IgnoreExtraProperties
public class User {
    public String name;
    public String date;
    public String time;
    public String description;
    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public User() {
    }
    public User(String date, String time,String name, String description) {
        this.name = name;
        this.date = date;
        this.description=description;
        this.time=time;
    }
}

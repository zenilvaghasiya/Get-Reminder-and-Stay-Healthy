package com.universalgamestudio.getreminderandstayhealthy;

public class SingleEntry {
    private String value;
    private String date;

    public SingleEntry(String value, String date) {
        this.value = value;
        this.date = date;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

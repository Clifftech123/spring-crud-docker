package com.clifford.ChallagneApp;

public class Challenge {
    private  long id ;
    private  String month ;
    private  String descriptions;


    public Challenge(long id, String month, String descriptions) {
        this.id = id;
        this.month = month;
        this.descriptions = descriptions;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }
}

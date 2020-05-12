package com.example.testgraphs;

public class YourData {
    private String dailyconfirmed;
    private String totalconfirmed;
    private String date;

    public YourData() {
    }

    public YourData(String dailyconfirmed, String totalconfirmed, String date) {
        this.dailyconfirmed = dailyconfirmed;
        this.totalconfirmed = totalconfirmed;
        this.date = date;
    }

    public String getDailyconfirmed() {
        return dailyconfirmed;
    }

    public String getTotalconfirmed() {
        return totalconfirmed;
    }

    public String getDate() {
        return date;
    }
}

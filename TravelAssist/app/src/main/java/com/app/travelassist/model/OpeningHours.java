package com.app.travelassist.model;

/**
 * Created by aarokiax on 3/28/2017.
 */

public class OpeningHours {
    String open_now;
    String[] weekday_text;

    public String getOpen_now() {
        return open_now;
    }

    public void setOpen_now(String open_now) {
        this.open_now = open_now;
    }

    public String[] getWeekday_text() {
        return weekday_text;
    }

    public void setWeekday_text(String[] weekday_text) {
        this.weekday_text = weekday_text;
    }
}

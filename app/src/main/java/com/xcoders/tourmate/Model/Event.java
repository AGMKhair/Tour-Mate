package com.xcoders.tourmate.Model;

public class Event {
    private String eventid;
    private String name;
    private String todate;
    private String formdate;
    private String budget;

    public String getEventid() {
        return eventid;
    }

    public void setEventid(String eventid) {
        this.eventid = eventid;
    }

    public Event() {
    }

    public Event(String eventid,String name, String todate, String formdate, String budget) {
        this.eventid = eventid;
        this.name = name;
        this.todate = todate;
        this.formdate = formdate;
        this.budget = budget;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTodate() {
        return todate;
    }

    public void setTodate(String todate) {
        this.todate = todate;
    }

    public String getFormdate() {
        return formdate;
    }

    public void setFormdate(String formdate) {
        this.formdate = formdate;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }
}

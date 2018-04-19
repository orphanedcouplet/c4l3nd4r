package com.oliverandcode.c4l3nd4r.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Calendar;

@Entity
public class Event {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 1, max = 140)
    private String title;

    private String description;

    private Calendar dateAndTime;

    @ManyToOne
    private Location location;

    public Event() {}

    public Event(int id, String title, String description, Calendar dateAndTime, Location location) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dateAndTime = dateAndTime;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Calendar getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Calendar dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}

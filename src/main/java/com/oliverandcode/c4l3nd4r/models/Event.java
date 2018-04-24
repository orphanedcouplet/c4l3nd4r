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

    private Calendar startDateAndTime;

    private Calendar endDateAndTime;

    @ManyToOne
    private Location location;

    public Event() {}

    public Event(int id, String title, String description, Calendar startDateAndTime, Calendar endDateAndTime, Location location) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startDateAndTime = startDateAndTime;
        this.endDateAndTime = endDateAndTime;
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

    public Calendar getStartDateAndTime() {
        return startDateAndTime;
    }

    public void setStartDateAndTime(Calendar startDateAndTime) {
        this.startDateAndTime = startDateAndTime;
    }

    public int getStartYear() {
        return startDateAndTime.get(Calendar.YEAR);
    }

    public void setStartYear(int startYear) {
        this.startDateAndTime.set(Calendar.YEAR, startYear);
    }

    public int getStartMonth() {
        return startDateAndTime.get(Calendar.MONTH);
    }

    public void setStartMonth(int startMonth) {
        this.startDateAndTime.set(Calendar.MONTH, startMonth);
    }

    public int getStartDayOfMonth() {
        return startDateAndTime.get(Calendar.DAY_OF_MONTH);
    }

    public void setStartDayOfMonth(int startDayOfMonth) {
        this.startDateAndTime.set(Calendar.DAY_OF_MONTH, startDayOfMonth);
    }

    public int getStartHourOfDay() {
        return startDateAndTime.get(Calendar.HOUR_OF_DAY);
    }

    public void setStartHourOfDay(int startHourOfDay) {
        this.startDateAndTime.set(Calendar.HOUR_OF_DAY, startHourOfDay);
    }

    public int getStartMinute() {
        return startDateAndTime.get(Calendar.MINUTE);
    }

    public void setStartMinute(int startMinute) {
        this.startDateAndTime.set(Calendar.MINUTE, startMinute);
    }

    public Calendar getEndDateAndTime() {
        return endDateAndTime;
    }

    public void setEndDateAndTime(Calendar endDateAndTime) {
        this.endDateAndTime = endDateAndTime;
    }

    public int getEndYear() {
        return endDateAndTime.get(Calendar.YEAR);
    }

    public void setEndYear(int endYear) {
        this.endDateAndTime.set(Calendar.YEAR, endYear);
    }

    public int getEndMonth() {
        return endDateAndTime.get(Calendar.MONTH);
    }

    public void setEndMonth(int endMonth) {
        this.endDateAndTime.set(Calendar.MONTH, endMonth);
    }

    public int getEndDayOfMonth() {
        return endDateAndTime.get(Calendar.DAY_OF_MONTH);
    }

    public void setEndDayOfMonth(int endDayOfMonth) {
        this.endDateAndTime.set(Calendar.DAY_OF_MONTH, endDayOfMonth);
    }

    public int getEndHourOfDay() {
        return endDateAndTime.get(Calendar.HOUR_OF_DAY);
    }

    public void setEndHourOfDay(int endHourOfDay) {
        this.endDateAndTime.set(Calendar.HOUR_OF_DAY, endHourOfDay);
    }

    public int getEndMinute() {
        return endDateAndTime.get(Calendar.MINUTE);
    }

    public void setEndMinute(int endMinute) {
        this.endDateAndTime.set(Calendar.MINUTE, endMinute);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}

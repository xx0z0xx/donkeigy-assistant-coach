package com.donkeigy.coach.objects.ui;

/**
 * Created by cedric on 12/15/14.
 */
public class ComboBoxWeek
{
    int week;

    public ComboBoxWeek(int week)
    {
        this.week = week;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    @Override
    public String toString() {
        return "Week "+ week;
    }
}

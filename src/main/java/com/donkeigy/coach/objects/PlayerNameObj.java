package com.donkeigy.coach.objects;

/**
 * Created by cedric on 10/31/14.
 */
public class PlayerNameObj
{
    private int id;
    private String full;
    private String first;
    private String last;
    private String asciiFirst;
    private String asciiLast;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFull() {
        return full;
    }

    public void setFull(String full) {
        this.full = full;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getAsciiFirst() {
        return asciiFirst;
    }

    public void setAsciiFirst(String asciiFirst) {
        this.asciiFirst = asciiFirst;
    }

    public String getAsciiLast() {
        return asciiLast;
    }

    public void setAsciiLast(String asciiLast) {
        this.asciiLast = asciiLast;
    }
}

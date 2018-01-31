package com.test.journals;

import java.util.Calendar;

public final class Journal {
    private int id;
    private String name;
    private String genre;
    private Calendar dateRelease;
    private int pageCount;

    Journal(int id, String name, String genre, String dateRelease, int pageCount) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.dateRelease = DateUtils.toCalendar(dateRelease);
        this.pageCount = pageCount;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public Calendar getDateRelease() {
        return dateRelease;
    }

    public int getPageCount() {
        return pageCount;
    }
}

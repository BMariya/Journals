package com.test.journals;

import java.util.ArrayList;
import java.util.Calendar;

public final class Store {
    private static Store INSTANCE;
    public static Store getInstance() {
        if (INSTANCE == null) INSTANCE = new Store();
        return INSTANCE;
    }

    private final Calendar archiveDate;
    private Store() {
        archiveDate = DateUtils.toCalendar("31.12.2017");
    }

    private ArrayList<Journal> journalList;
    private ArrayList<Journal> journalListNew;
    private ArrayList<Journal> journalListArchive;
    public ArrayList<Journal> getJournals() {
        if (journalList == null) initJournals();
        return journalList;
    }

    private void initJournals() {
        journalList = new ArrayList<>();
        addJournal(7, "JournalName_7", "Genre_7","11.10.2013", 100);
        addJournal(8, "JournalName_8", "Genre_8","11.11.2014", 10);
        addJournal(9, "JournalName_9", "Genre_9","11.11.2015", 70);
        addJournal(10, "JournalName_10", "Genre_10","11.11.2016", 19);
        addJournal(11, "JournalName_11", "Genre_11","31.12.2017", 33);
        addJournal(12, "JournalName_12", "Genre_12","11.11.2018", 99);
        addJournal(13, "JournalName_13", "Genre_13","11.11.2018", 100);
        addJournal(14, "JournalName_14", "Genre_14","11.11.2018", 10);
        addJournal(15, "JournalName_15", "Genre_15","11.11.2018", 70);
        addJournal(18, "JournalName_18", "Genre_18","11.11.2018", 99);
        addJournal(19, "JournalName_19", "Genre_19","11.11.2018", 100);
        addJournal(20, "JournalName_20", "Genre_20","11.11.2018", 10);
        addJournal(21, "JournalName_21", "Genre_21","11.11.2018", 70);
        addJournal(22, "JournalName_22", "Genre_22","11.11.2018", 19);
        addJournal(23, "JournalName_23", "Genre_23","11.11.2018", 33);
        addJournal(24, "JournalName_24", "Genre_24","11.11.2018", 99);
    }
    private void addJournal(int id, String name, String genre, String dateRelease, int pageCount) {
        Journal journal = new Journal(id, name, genre, dateRelease, pageCount);
        journalList.add(journal);
    }

    public ArrayList<Journal> getJournalsNew() {
        if (journalListNew == null) initJournalsNewArchive();
        return journalListNew;
    }
    public ArrayList<Journal> getJournalsArchive() {
        if (journalListArchive == null) initJournalsNewArchive();
        return journalListArchive;
    }
    private void initJournalsNewArchive() {
        journalListNew = new ArrayList<>();
        journalListArchive = new ArrayList<>();
        for (Journal journal: getJournals()) {
            if (journal.getDateRelease().getTimeInMillis() >= archiveDate.getTimeInMillis()) {
                journalListNew.add(journal);
            } else journalListArchive.add(journal);
        }
    }

    public void removeJournal(int id) {
        for (Journal journal: journalList) {
            if (journal.getId() == id) {
                journalList.remove(journal);
                journalListNew.remove(journal);
                journalListArchive.remove(journal);
                return;
            }
        }
    }
}

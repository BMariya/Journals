package com.test.journals;

import java.util.ArrayList;

public final class JournalsModel {
    private Store store;

    public JournalsModel(Store journalStore) {
        store = journalStore;
    }

    public ArrayList<Journal> loadJournalsNew() {
        return store.getJournalsNew();
    }

    public ArrayList<Journal> loadJournalsArchive() {
       return store.getJournalsArchive();
    }

    public void removeJournal(int id) {
        store.removeJournal(id);
    }
}

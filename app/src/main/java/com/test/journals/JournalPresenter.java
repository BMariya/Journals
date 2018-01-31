package com.test.journals;

import java.util.List;

public final class JournalPresenter {
    private JournalsActivity view;
    private final JournalsModel model;

    public JournalPresenter(JournalsModel journalsModel) {
        model = journalsModel;
    }

    public void attachView(JournalsActivity journalsActivity) {
        view = journalsActivity;
    }

    public void detachView() {
        view = null;
    }

    public void loadJournals() {
        List<Journal> journals = model.loadJournalsNew();
        if (journals.isEmpty()) {
            view.hideJournalsNew();
        } else view.updateJournalsNew(journals);
        journals = model.loadJournalsArchive();
        if (journals.isEmpty()) {
            view.hideJournalsArchive();
        } else view.updateJournalsArchive(journals);
    }

    public void loadJournalsNew() {
        view.showJournalsNew();
    }

    public void loadJournalsArchive() {
        view.showJournalsArchive();
    }

    public void removeJournal(int id) {
        model.removeJournal(id);
        loadJournals();
        view.updateJournals();
    }
}

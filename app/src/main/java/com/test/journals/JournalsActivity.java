package com.test.journals;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

public final class JournalsActivity extends AppCompatActivity
        implements ConfirmDialog.ConfirmDialogListener {

    public enum State {New, Archive}
    private State state;
    private JournalPresenter presenter;
    private View btnNew, btnArchive;
    private RecyclerView recyclerViewNew, recyclerViewArchive;
    private JournalAdapter adapterNew, adapterArchive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journals);
        if (savedInstanceState != null) state = (State) savedInstanceState.get("state");
        else state = State.New;
        initView();
        setState(state);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("state", state);
        super.onSaveInstanceState(outState);
    }

    private void initView() {
        recyclerViewNew = findViewById(R.id.activity_main_recyclerViewNew);
        recyclerViewNew.setLayoutManager(new LinearLayoutManager(this));
        adapterNew = new JournalAdapter();
        adapterNew.setListItemListener(listItemListener);
        recyclerViewNew.setAdapter(adapterNew);

        recyclerViewArchive = findViewById(R.id.activity_main_recyclerViewArchive);
        recyclerViewArchive.setLayoutManager(new LinearLayoutManager(this));
        adapterArchive = new JournalAdapter();
        adapterArchive.setListItemListener(listItemListener);
        recyclerViewArchive.setAdapter(adapterArchive);

        btnNew = findViewById(R.id.activity_main_btnNew);
        btnNew.setOnClickListener(buttonClickListener);
        btnArchive = findViewById(R.id.activity_main_btnArchive);
        btnArchive.setOnClickListener(buttonClickListener);
        JournalsModel journalsModel = new JournalsModel(Store.getInstance());
        presenter = new JournalPresenter(journalsModel);
        presenter.attachView(this);
        presenter.loadJournals();
    }

    private JournalAdapter.ItemListener listItemListener = new JournalAdapter.ItemListener() {
        @Override
        public void onListItemRemove(int removeId) {
            showConfirmDialog(removeId);
        }
    };

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.activity_main_btnNew:
                    setState(State.New);
                    break;
                case R.id.activity_main_btnArchive:
                    setState(State.Archive);
                    break;
            }
        }
    };

    private void setState(State st) {
        state = st;
        switch (state) {
            case New:
                presenter.loadJournalsNew();
                break;
            case Archive:
                presenter.loadJournalsArchive();
                break;
        }
    }

    public void updateJournals() {
        setState(state);
    }

    public void showJournalsNew() {
        showState(State.New);
    }
    public void updateJournalsNew(List<Journal> journals) {
        adapterNew.setData(journals);
    }
    public void hideJournalsNew() {
        hideState(State.New);
    }
    public void showJournalsArchive() {
        showState(State.Archive);
    }
    public void updateJournalsArchive(List<Journal> journals) {
        adapterArchive.setData(journals);
    }
    public void hideJournalsArchive() {
        hideState(State.Archive);
    }
    private void showState(State state) {
        switch (state) {
            case New:
                btnNew.setEnabled(false);
                btnArchive.setEnabled(true);
                recyclerViewNew.setVisibility(View.VISIBLE);
                recyclerViewArchive.setVisibility(View.GONE);
                break;
            case Archive:
                btnNew.setEnabled(true);
                btnArchive.setEnabled(false);
                recyclerViewNew.setVisibility(View.GONE);
                recyclerViewArchive.setVisibility(View.VISIBLE);
                break;
        }
    }
    private void hideState(State state) {
        switch (state) {
            case New:
                btnNew.setVisibility(View.GONE);
                recyclerViewNew.setVisibility(View.GONE);
                if (btnArchive.getVisibility() == View.VISIBLE) {
                    setState(State.Archive);
                }
                break;
            case Archive:
                btnArchive.setVisibility(View.GONE);
                recyclerViewArchive.setVisibility(View.GONE);
                if (btnNew.getVisibility() == View.VISIBLE) {
                    setState(State.New);
                }
                break;
        }
    }

    private void showConfirmDialog(int removeId) {
        ConfirmDialog newFragment = ConfirmDialog.newInstance(removeId);
        newFragment.show(getSupportFragmentManager(), "dialog");
    }

    @Override
    public void onRemoveConfirm(int removeId) {
        presenter.removeJournal(removeId);
    }
}

package com.test.journals;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public final class JournalAdapter extends RecyclerView.Adapter<JournalAdapter.JournalViewHolder> {
    public interface ItemListener {
        void onListItemRemove(int removeId);
    }

    private List<Journal> dataList = new ArrayList<>();
    private ItemListener itemListener;

    @Override
    public JournalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_journal, parent, false);
        return new JournalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(JournalViewHolder holder, int position) {
        holder.bind(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void setData(List<Journal> journalList) {
        dataList.clear();
        dataList.addAll(journalList);
        notifyDataSetChanged();
    }

    public void setListItemListener(ItemListener listener) {
        itemListener = listener;
    }

//    public void remove(int position) {
//        dataList.remove(position);
//        notifyItemRemoved(position);
//    }

    class JournalViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvGenre;
        private TextView tvPageCount;
        private TextView tvDateRelease;
        private View ivRemove;

        private JournalViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.list_item_journal_tv_name);
            tvGenre = itemView.findViewById(R.id.list_item_journal_tv_genre);
            tvPageCount = itemView.findViewById(R.id.list_item_journal_tv_pageCount);
            tvDateRelease = itemView.findViewById(R.id.list_item_journal_tv_dateRelease);
            ivRemove = itemView.findViewById(R.id.list_item_iv_remove);
        }

        private void bind(Journal journal) {
            tvName.setText(journal.getName());
            tvGenre.setText(journal.getGenre());
            tvPageCount.setText(
                    String.format(Locale.getDefault(), "%d pages", journal.getPageCount()));
            tvDateRelease.setText(DateUtils.toDisplay(journal.getDateRelease()));
            if (itemListener != null) {
                ivRemove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        itemListener.onListItemRemove(
                                dataList.get(getAdapterPosition()).getId());
                    }
                });
            }
        }
    }
}

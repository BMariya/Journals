package com.test.journals;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

public class ConfirmDialog extends DialogFragment {
    public interface ConfirmDialogListener {
        void onRemoveConfirm(int removeId);
    }

    public static ConfirmDialog newInstance(int removeId) {
        ConfirmDialog confirmDialog = new ConfirmDialog();
        Bundle args = new Bundle();
        args.putInt("removeId", removeId);
        confirmDialog.setArguments(args);
        return confirmDialog;
    }

    private ConfirmDialogListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (ConfirmDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement ConfirmDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getContext())
                .setMessage(getResources().getString(R.string.dialog_confirm_delete))
                .setPositiveButton(
                        getResources().getString(R.string.dialog_confirm_yes),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                listener.onRemoveConfirm(getArguments().getInt("removeId"));
                            }
                        })
                .setNegativeButton(
                        getResources().getString(R.string.dialog_confirm_cancel),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {}
                        }).create();

    }
}

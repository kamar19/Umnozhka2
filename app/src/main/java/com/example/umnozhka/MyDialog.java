package com.example.umnozhka;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.DialogFragment;
import android.util.Log;
//import androidx.fragment.app.DialogFragment;

//public class MyDialog extends DialogFragment {
public class MyDialog extends DialogFragment implements DialogInterface.OnClickListener {

    private static int resultDialog = 0;
    final String LOG_TAG = "myDialogLogs";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        resultDialog = 0;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.alertDialogTitle);
        builder.setMessage(R.string.alertDialogMessage)
                .setPositiveButton(R.string.alertDialogPositiveButtonText, this)
//                .setPositiveButton(R.string.alertDialogPositiveButtonText, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // Продолжить
//                        resultDialog = 1;
//                        Log.d(LOG_TAG, "onClick: PositiveButton");
//
////                        dialog.dismiss();
//                    }
//                })
                .setNegativeButton(R.string.alertDialogNegativeButtonText, this)
//                        new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // Начать новый
//                        resultDialog = 2;
//                        Log.d(LOG_TAG, "onClick: NegativeButton");
//
//                        //                        dialog.dismiss();
//                    }
//                })
                .setNeutralButton(R.string.alertDialogNeutralButtonText, this);
//                        new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog,
//                                        int which) {
//                        // Отмена
//                        Log.d(LOG_TAG, "onClick: NeutralButton");
//                        resultDialog = 3;
////                        dialog.dismiss();
//                    }
//                });

//        AlertDialog ad = builder.create();
//        ad.show();
        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

        switch (which) {
            case Dialog.BUTTON_POSITIVE:
                // продолжить игру
                resultDialog = 1;
//                Log.d(LOG_TAG, "onClick: PositiveButton");
                Intent intent2 = new Intent( getActivity(), MainActivity.class);
                startActivity(intent2);
            dismiss();
            break;
            case Dialog.BUTTON_NEGATIVE:
                resultDialog = 2;
                // начать занова
//                Log.d(LOG_TAG, "onClick: NegativeButton");
                StartActivity.myLesson.startNewLesson();
                StartActivity.myLesson.setEndGame(true);
                Intent intent3 = new Intent( getActivity(), MainActivity.class);
                startActivity(intent3);
                dismiss();
                break;
            case Dialog.BUTTON_NEUTRAL:
                resultDialog = 3;
                Log.d(LOG_TAG, "onClick: NeutralButton");
                break;
        }
    }

    public int getResultDialog() {
        return this.resultDialog;
    }


    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
//        Log.d(LOG_TAG, "Dialog 2: onDismiss");
    }

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
//        Log.d(LOG_TAG, "Dialog 2: onCancel");
    }


}



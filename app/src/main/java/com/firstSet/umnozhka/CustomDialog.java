package com.firstSet.umnozhka;

import androidx.appcompat.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.app.DialogFragment;
public class CustomDialog extends DialogFragment{

    public void showDialog(DialogInterface.OnClickListener okListener, DialogInterface.OnClickListener cancelListener, DialogInterface.OnClickListener neutralListener, Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(R.string.alertDialogTitle));
        builder.setMessage(R.string.alertDialogMessage)
                .setPositiveButton(R.string.alertDialogPositiveButtonText, okListener)
                .setNegativeButton(R.string.alertDialogNegativeButtonText, cancelListener)
                .setNeutralButton(R.string.alertDialogNeutralButtonText, neutralListener);
        builder.create();
        builder.show();
    }
}
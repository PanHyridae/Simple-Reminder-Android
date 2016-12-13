package com.marlonjones.simplereminder;
/**
 * Java Class made by Marlon Jones (MJonesDev) on 12/12/2016.
 */
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.text.InputType;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import static java.security.AccessController.getContext;

public class ConfirmActivity extends AppCompatActivity {
    //TODO - ADD ANDROID WEAR AND TIME FUNCTIONALITY
    public static final int NOTIFICATION_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new MaterialDialog.Builder(this)
                .title(R.string.confirm_title)
                .content(R.string.confirm_content)
                .positiveText(R.string.confirm_pos)
                .negativeText(R.string.confirm_neg)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Intent reOpen = new Intent (getApplicationContext(), MainActivity.class);
                        getApplicationContext().startActivity(reOpen);
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.cancel(NOTIFICATION_ID);
                    }
                })
                .dismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                finish();
            }
        }).show();
    }
    //Closes the app when the back button is pressed
    public void onBackPressed(){
        finish();
    }
}

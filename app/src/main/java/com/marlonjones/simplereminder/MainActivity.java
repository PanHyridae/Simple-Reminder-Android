package com.marlonjones.simplereminder;
/**
 * Java Class made by Marlon Jones (MJonesDev) on 12/1/2016.
 */
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.text.InputType;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

public class MainActivity extends AppCompatActivity {
//TODO - ADD ANDROID WEAR AND TIME FUNCTIONALITY
    public static final int NOTIFICATION_ID = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Aidan's Library - Material Dialogs - Extends to .show(); and wraps around the Notification and other parts
        //Input Dialog
        new MaterialDialog.Builder(this)
                .title(R.string.input)
                .autoDismiss(false)
                .inputType(InputType.TYPE_CLASS_TEXT)
                .input(null, null, new MaterialDialog.InputCallback()

                {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        //Notification Click - Opens ConfirmActivity
                            Intent myIntent = new Intent(getApplicationContext(), ConfirmActivity.class);
                            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),
                                    0, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                        //notification body
                            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
                            builder.setStyle(new NotificationCompat.BigTextStyle().bigText(input.toString())); //BigText
                            builder.setOngoing(true); //Make persistent
                            builder.setContentIntent(pendingIntent);
                            builder.setSmallIcon(R.drawable.ic_small);
                            builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                            builder.setContentTitle("Remember!");
                            builder.setContentText(input.toString()); //Get text from dialog input
                            notificationManager.notify(NOTIFICATION_ID, builder.build());

                        //toast
                        Toast.makeText(MainActivity.this, "Done! Reminder has been set. Check your Notification Bar! :)",
                                Toast.LENGTH_LONG).show();

                        //Close app when done entering in text
                        finish();
                    }

                    //Close app when dialog is dismissed (ex: click outside of dialog)
                }).dismissListener(new DialogInterface.OnDismissListener() {
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
